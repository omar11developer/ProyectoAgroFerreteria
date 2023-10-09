package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Client;
import com.proyect.agroferreteria.services.contracts.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ferreteria")
public class ClienteRestController {
    @Autowired
    private IClientService clienteService;

    @GetMapping("/clientes")
    public List<Client> index() {
        return clienteService.findAll();
    }

    @GetMapping("/clientes/{id}")//busca por Id de cliente
    public ResponseEntity<?> buscarporid(@PathVariable Long id) {
        Client client = null;
        Map<String, Object> response = new HashMap<>();

        try {
            client = clienteService.findById(id);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar la consulta en la base de datos");
            response.put("Error!", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (client == null) {
            response.put("Mensaje:", "el ID del cliente: ".concat(id.toString().concat(" No existe en la base de datos, por favor ingresa un ID valido!!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }


    @PostMapping("/clientes") // crea un cliente
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Client cliente, BindingResult result) {
        Client clienteNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> "El campo '" + error.getField() + "'" + error.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        // Validar el formato del correo electrónico
        if (clienteService.existsByEmail(cliente.getEmail())) {
            response.put("Mensaje", "Error: Este correo ya existe en la base de datos.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            // Validar si ya existe un cliente con la misma identificación
            if (clienteService.existsByIdentification(cliente.getIdentification())) {
                response.put("Mensaje", "Error: Ya existe un cliente con esta identificación.");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }

            clienteNew = clienteService.save(cliente);
        } catch (DataAccessException e) {
            response.put("Mensaje", "Error al realizar el insert en la base de datos");
            response.put("Error!", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("Mensaje", "El cliente ha sido creado con éxito!");
        response.put("cliente", clienteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }




    @PutMapping("/clientes/{id}")//sirve para actualizar
    public ResponseEntity<?> update(@Valid @RequestBody Client client, BindingResult result ,@PathVariable Long id) {

        Client clienteActual = clienteService.findById(id);
        Client clienteUpdate = null;

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){

            List<String>errors=result.getFieldErrors()
                    .stream()
                    .map(error->"El campo '"+error.getField()+"'"+error.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors",errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (clienteActual == null) {
            response.put("Mensaje:", "Error: no se pudo editar el cliente ya que el ID: ".concat(id.toString().concat(" No existe en la base de datos, por favor ingresa un ID valido!!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            clienteActual.setName(client.getName());
            clienteActual.setLastName(client.getLastName());
            clienteActual.setEmail(client.getEmail());
            clienteUpdate = clienteService.save(clienteActual);
        } catch (DataAccessException e) {
            response.put("Mensaje:", "Error al actualizar en la base de datos");
            response.put("Error!", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Mensaje:", "El cliente ha sido actualizado con exito!");
        response.put("cliente", clienteUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Client client = clienteService.findById(id);

        if (client == null) {
            response.put("mensaje", "El cliente con ID " + id + " no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            clienteService.delete(id);
            response.put("mensaje", "Cliente eliminado con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            response.put("mensaje", "No se puede eliminar el cliente. Existe una restricción de integridad.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el cliente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}


