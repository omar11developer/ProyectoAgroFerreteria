package com.proyect.agroferreteria.models.mapper.mapstruct;

import com.proyect.agroferreteria.models.dto.ProductoDTO;
import com.proyect.agroferreteria.models.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    @Mappings({
            @Mapping(source = "id", target = "id_Producto"),
            @Mapping(source = "unitPrice", target = "unit_Price"),
            @Mapping(source = "unitWeight", target = "unit_Weight"),
            @Mapping(source = "supplier", target = "supplier"),
            @Mapping(source = "category", target = "category")
    })
    ProductoDTO mapProducto(Product product);
}
