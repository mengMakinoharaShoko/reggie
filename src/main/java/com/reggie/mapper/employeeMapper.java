package com.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YX200
 */
@Mapper
public interface employeeMapper extends BaseMapper<Employee> {
}
