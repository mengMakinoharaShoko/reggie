package com.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie.common.R;
import com.reggie.entity.OrderDetail;
import com.reggie.entity.Orders;
import com.reggie.entity.Setmeal;
import com.reggie.service.OrderDetailService;
import com.reggie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据：{}",orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }
    @GetMapping("/page")
    public R<Page<Orders>> page(int page, int pageSize){
        Page<Orders> pageOne = new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Orders::getOrderTime);
        orderService.page(pageOne,queryWrapper);
        return R.success(pageOne);
    }
    @PutMapping("/status/{status}")
    public R<String> Status(@PathVariable Integer status,@RequestParam List<Long> ids) {
        log.info("ids{}", ids);
        log.info("status", status);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Orders::getId,ids);
        List<Orders> list = orderService.list(queryWrapper);
        for(Orders dish:list){
            dish.setStatus(status);
        }
        orderService.updateBatchById(list);


        return R.success("成功");
    }
}