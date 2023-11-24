package com.proyect.agroferreteria.models.mapper.mapstruct;

import com.proyect.agroferreteria.models.dto.SupplierDTO;
import com.proyect.agroferreteria.models.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    @Mappings({
            @Mapping(source = "id", target = "id_Supplier")
    })
    SupplierDTO mapSupplier(Supplier supplier);
    @Mappings({
            @Mapping(source = "id_Supplier", target = "id")
    })
    Supplier mapDtoSupplier(SupplierDTO supplier);
}
