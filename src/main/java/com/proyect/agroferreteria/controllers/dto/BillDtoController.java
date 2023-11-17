package com.proyect.agroferreteria.controllers.dto;

import com.proyect.agroferreteria.models.dto.BillDTO;
import com.proyect.agroferreteria.models.entity.Bill;
import com.proyect.agroferreteria.models.entity.Client;
import com.proyect.agroferreteria.models.mapper.mapstruct.BillMapper;
import com.proyect.agroferreteria.services.contracts.BillDAO;
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
@RequestMapping("/factura")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class BillDtoController extends GenericoDtoController<Bill, BillDAO> {
    @Autowired
    private BillMapper mapper;

    private final ClientDAO clientDAO;
    public BillDtoController(BillDAO service, ClientDAO clientDAO) {
        super(service, "Factura");
        this.clientDAO = clientDAO;
    }
    @GetMapping("/")
    public ResponseEntity<?> obtenerBill(){
        Map<String, Object> response = new HashMap<>();
        List<Bill> bills = super.obtenerTodos();
        if(bills.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontro %ss cargadas", nombreEntidad));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        List<BillDTO> dtos = bills.stream().map(mapper::mapBill).collect(Collectors.toList());
        response.put("success", Boolean.TRUE);
        response.put("data", bills);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/")
    public ResponseEntity<?> saveBill(@Valid @RequestBody Bill bill, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        Optional<Client> oClient = clientDAO.buscarCliente(bill.getClient().getName());
        if(result.hasErrors()){
            response.put("success", Boolean.FALSE);
            response.put("messagge", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(response);
        }
        if(oClient.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("El Cliente que deseas agregar no existe"));
            return ResponseEntity.badRequest().body(response);
        }
        bill.setClient(oClient.get());
        Bill save = super.altaEntidad(bill);
        BillDTO dto = mapper.mapBill(save);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editarFactura(@PathVariable long id, @RequestBody Bill bill){
        Map<String, Object> response = new HashMap<>();
        Optional<Bill> oBill = super.obtenerPorId(id);
        if(oBill.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("La %s que deseas editar no existe", nombreEntidad));
            return ResponseEntity.badRequest().body(response);
        }
        Bill billUpdate = oBill.get();
        billUpdate.setDescription(bill.getDescription());
        billUpdate.setObservation(bill.getObservation());
        Optional<Client> client = clientDAO.buscarCliente(bill.getClient().getName());
        if(client.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("El cliente que edito no existe para poder editar"));
            return ResponseEntity.badRequest().body(response);
        }
        billUpdate.setClient(client.get());
        super.altaEntidad(billUpdate);
        BillDTO dto = mapper.mapBill(billUpdate);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPorID(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<Bill> bill = super.obtenerPorId(id);
        if(bill.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontro %s con el id %d", nombreEntidad, id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        super.eliminarPorId(id);
        BillDTO dto = mapper.mapBill(bill.get());
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        response.put("message", "Se elimino la factura correctamente");
        return ResponseEntity.ok(response);
    }

}
