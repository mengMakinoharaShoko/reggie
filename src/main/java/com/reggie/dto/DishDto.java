package com.reggie.dto;


import com.reggie.entity.Dish;
import com.reggie.entity.DishFlavor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YX200
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
