package com.akash.blogApplication.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;
    @NotEmpty
    @Size(max = 20,message = "Category title must be less than 20 characters")
    private String categoryTitle;
    @NotEmpty
    private String categoryDescription;

}
