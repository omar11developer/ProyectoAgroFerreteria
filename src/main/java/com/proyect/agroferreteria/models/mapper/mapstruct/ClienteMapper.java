package com.proyect.agroferreteria.models.mapper.mapstruct;

import com.proyect.agroferreteria.models.dto.ClientDTO;
import com.proyect.agroferreteria.models.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mappings({
            @Mapping(source = "id", target = "id_Client"),
            @Mapping(source = "lastName", target = "last_name")
    })
    ClientDTO mapClient(Client client);
}
