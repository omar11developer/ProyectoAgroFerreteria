package com.proyect.agroferreteria.models.mapper.mapstruct;

import com.proyect.agroferreteria.models.dto.CategoryDTO;
import com.proyect.agroferreteria.models.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "id", target = "id_Category")
    })
    CategoryDTO mapCategory(Category category);
    @Mappings({
            @Mapping(source = "id_Category", target = "id")
    })
    Category mapDTOCategory(CategoryDTO category);
}
