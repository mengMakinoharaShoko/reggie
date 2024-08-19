package com.reggie.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.reggie.common.CustomException;
import com.reggie.dto.DishDto;
import com.reggie.entity.Dish;
import com.reggie.entity.DishFlavor;
import com.reggie.mapper.DishMapper;
import com.reggie.service.DishFlavorService;
import com.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;

    @Transactional(rollbackFor =Exception.class)
    @Override
    public void saveWithFlavor(DishDto dishDto) {
        this.save(dishDto);
        Long dishID = dishDto.getId();
        List<DishFlavor> flavors = dishDto.getFlavors();
        //为餐品口味赋值dishId
        List<DishFlavor> flavorList = flavors.stream().map((item) -> {
            item.setDishId(dishID);
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavorList);

    }
    @Transactional(rollbackFor =Exception.class)
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品基本信息，从dish表查询
        Dish dishId = this.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dishId, dishDto);
        // 查询当前菜品对应的口味信息，从dish_flavor表查询

        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishId.getId());
        List<DishFlavor> list = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(list);

        return dishDto;
    }
    @Transactional(rollbackFor =Exception.class)
    @Override
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish表基本信息
        this.updateById(dishDto);
        // 清理当前菜品对应口味数据---dish_flavor表的delete操作
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);


        // 添加当前提交过来的口味数据---dish_flavor表的insert操作
        List<DishFlavor> flavors = dishDto.getFlavors();
        //给flavor添加dishId
        flavors.stream().map((item)->{
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);





    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeWithFlavor(List<Long> ids) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dish::getId,ids);
        queryWrapper.eq(Dish::getStatus,1);
        int count = this.count(queryWrapper);
        if (count > 0) {
            //如果不能删除，抛出一个业务异常
            throw new CustomException("餐品正在售卖中，不能删除");
        }
        this.removeByIds(ids);
        LambdaQueryWrapper<DishFlavor> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(DishFlavor::getDishId,ids);
        dishFlavorService.remove(wrapper);


    }
}
