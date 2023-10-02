package com.ecommerce.dto;

import java.util.List;

import com.ecommerce.model.Category;

import groovy.transform.ToString;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO extends BaseDTO {
    private Long id;
    private String name;
    private Long parentId;
    private List<Category> children;
    private Category parent;
}
