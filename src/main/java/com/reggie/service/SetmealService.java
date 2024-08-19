package com.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reggie.dto.DishDto;
import com.reggie.dto.SetmealDto;
import com.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
    //根据id查询菜品信息和对应的口味信息

    public SetmealDto getByIdWithDish(Long id);
    //更新套餐信息，同时更新对应的口味信息

    public void updateWithDish(SetmealDto setmealDto);
}
