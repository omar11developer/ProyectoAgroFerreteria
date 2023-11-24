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
            @Mapping(source = "category", target = "category"),
            @Mapping(source = "salePrice", target = "sale_price")
    })
    ProductoDTO mapProducto(Product product);
    @Mappings({
            @Mapping(source = "id_Producto", target = "id"),
            @Mapping(source = "unit_Price", target = "unitPrice"),
            @Mapping(source = "unit_Weight", target = "unitWeight"),
            @Mapping(source = "sale_price", target = "salePrice")
    })
    Product mapDtoProducto(ProductoDTO productoDTO);
}
