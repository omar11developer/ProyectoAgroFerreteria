package com.proyect.agroferreteria.models.mapper.mapstruct;

import com.proyect.agroferreteria.models.dto.InventorieDTO;
import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.services.contracts.InventoriesDAO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface InventoriesMapper {
    @Mappings({
            @Mapping(source = "id_Inventory", target = "id_Inventario"),
            @Mapping(source = "createAtOrder", target = "fecha_de_ingreso"),
            @Mapping(source = "salePrice", target = "precio_de_venta")
    })
    InventorieDTO mapInventario(Inventories inventario);
}
