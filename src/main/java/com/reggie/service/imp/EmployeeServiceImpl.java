package com.reggie.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie.entity.Employee;
import com.reggie.mapper.employeeMapper;
import com.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author YX200
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<employeeMapper, Employee> implements EmployeeService {
}
