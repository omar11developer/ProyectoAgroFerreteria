package com.proyect.agroferreteria.models.mapper.mapstruct;

import com.proyect.agroferreteria.models.dto.ItemBillDTO;
import com.proyect.agroferreteria.models.entity.ItemBill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ItemBillMapper {
    @Mappings({
            @Mapping(source = "id", target = "id_Item_Bill"),
            @Mapping(source = "createAtOrder", target = "date_create")
    })
    ItemBillDTO mapItemBill(ItemBill itemBill);
    @Mappings({
            @Mapping(source = "id_Item_Bill", target = "id"),
            @Mapping(source = "date_create", target = "createAtOrder")
    })
    ItemBill mapItemBill(ItemBillDTO itemBillDTO);
}
