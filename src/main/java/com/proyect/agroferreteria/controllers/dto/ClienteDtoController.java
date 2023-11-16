package com.proyect.agroferreteria.controllers.dto;

import com.proyect.agroferreteria.models.dto.ClientDTO;
import com.proyect.agroferreteria.models.entity.Client;
import com.proyect.agroferreteria.models.mapper.mapstruct.ClienteMapper;
import com.proyect.agroferreteria.services.contracts.ClientDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class ClienteDtoController extends GenericoDtoController<Client, ClientDAO> {
    @Autowired
    private ClienteMapper mapper;
    public ClienteDtoController(ClientDAO service) {
        super(service, "Cliente");
    }
    @GetMapping("/")
    public ResponseEntity<?> obtenerClientes(){
        Map<String, Object> response = new HashMap<>();
        List<Client> clients = super.obtenerTodos();
        if(clients.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontro %ss cargadas", nombreEntidad));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        List<ClientDTO> clientDTOS = clients.stream().map(mapper::mapClient).collect(Collectors.toList());
        response.put("success", Boolean.TRUE);
        response.put("data", clientDTOS);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<Client> oCliente = super.obtenerPorId(id);
        if(oCliente.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No existe el %s con el id #%d", nombreEntidad, id));
        }
        Client client = oCliente.get();
        ClientDTO dto = mapper.mapClient(client);
        response.put("success", Boolean.TRUE);
        response.put("data",dto);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/saveClient")
    public ResponseEntity<?> gaurdarCliente(@Valid @RequestBody Client client, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        Optional<Client> exitCliente = service.findByIdentification(client.getIdentification());
        Optional<Client> exiEmail = service.findByEmail(client.getEmail());

        if (result.hasErrors()){
            response.put("success", Boolean.FALSE);
            response.put("message", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(response);
        }
        if(exitCliente.isPresent()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("El %s con el DNI %s  ya existe ",nombreEntidad, client.getIdentification()));
            return ResponseEntity.badRequest().body(response);
        } else if (exiEmail.isPresent()) {
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("El email %s ya existe ", client.getEmail()));
            return ResponseEntity.badRequest().body(response);
        }
        Client clientSave = super.altaEntidad(client);
        ClientDTO dto = mapper.mapClient(clientSave);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/editCliente/{id}")
    public ResponseEntity<?> editarCliente(@Valid @RequestBody Client client, BindingResult result, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<Client> clientLocal = super.obtenerPorId(id);
        if(clientLocal.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No exite un %s con el id %d", nombreEntidad, id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        if(result.hasErrors()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("El %s con el DNI %s  ya existe ",nombreEntidad, client.getIdentification()));
            return ResponseEntity.badRequest().body(response);
        }
        Client clientUpdate = clientLocal.get();
        clientUpdate.setName(client.getName());
        clientUpdate.setEmail(client.getEmail());
        clientUpdate.setAdress(client.getAdress());
        clientUpdate.setPhone(client.getPhone());
        clientUpdate.setLastName(client.getLastName());
        clientUpdate.setIdentification(client.getIdentification());
        boolean existIdentificacion = service.existsByIdentificationAndNotCurrentId(client.getIdentification(), clientUpdate.getIdClient());
        if (existIdentificacion){
                response.put("success", Boolean.FALSE);
                response.put("message", String.format("El %s con el DNI %s  ya existe ",nombreEntidad, client.getIdentification()));
                return ResponseEntity.badRequest().body(response);
        }
        boolean existEmail = service.existsByEmailAndNotCurrentId(client.getEmail(), clientUpdate.getIdClient());
        if (existEmail){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("El %s con el email %s  ya existe ",nombreEntidad, client.getEmail()));
            return ResponseEntity.badRequest().body(response);
        }

        super.altaEntidad(clientUpdate);
        ClientDTO dto = mapper.mapClient(clientUpdate);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/")
    public ResponseEntity<?> eliminarCliente(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<Client> client = super.obtenerPorId(id);
        if (client.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("El id #%d no existe", id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        super.eliminarPorId(id);
        ClientDTO dtoEliminado = mapper.mapClient(client.get());
        response.put("success", Boolean.TRUE);
        response.put("data", dtoEliminado);
        response.put("message", "Ciente eliminado con Exito");
        return ResponseEntity.ok(response);

    }


}
