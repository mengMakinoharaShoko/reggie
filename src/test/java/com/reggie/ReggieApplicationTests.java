package com.reggie;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.reggie.dto.User;
import com.reggie.entity.Employee;

import com.reggie.service.EmployeeService;
import com.reggie.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ReggieApplicationTests {
@Resource
UserService userService;
    @Test
    void contextLoads() {

    }

}
