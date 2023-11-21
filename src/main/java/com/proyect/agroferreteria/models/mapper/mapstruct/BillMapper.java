package com.proyect.agroferreteria.models.mapper.mapstruct;

import com.proyect.agroferreteria.models.dto.BillDTO;
import com.proyect.agroferreteria.models.entity.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BillMapper {
    @Mappings(
            {
                    @Mapping(source = "id",target = "id_Factura"),
                    @Mapping(source = "creatAt", target = "date_create")
            }
    )
    BillDTO mapBill(Bill bill);
}
