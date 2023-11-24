package com.proyect.agroferreteria.controllers.dto;

import com.proyect.agroferreteria.models.dto.ItemBillDTO;
import com.proyect.agroferreteria.models.entity.ItemBill;
import com.proyect.agroferreteria.models.mapper.mapstruct.ItemBillMapper;
import com.proyect.agroferreteria.services.contracts.BillDAO;
import com.proyect.agroferreteria.services.contracts.ItemBillDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/itemFactura")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class ItemBillDtoController extends GenericoDtoController<ItemBill, ItemBillDAO>{
    @Autowired
    private ItemBillMapper mapper;

    private final BillDAO billDAO;
    public ItemBillDtoController(ItemBillDAO service, BillDAO billDAO) {
        super(service, "Factura item");

        this.billDAO = billDAO;
    }
    @GetMapping("/")
    public ResponseEntity<?> obtenerTodosLosItems(){
        Map<String, Object> response = new HashMap<>();
        List<ItemBill> itemBills = super.obtenerTodos();
        if (itemBills.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontraron %ss cargados", nombreEntidad));
            return ResponseEntity.badRequest().body(response);
        }
        List<ItemBillDTO> dtos = itemBills.stream().map(mapper::mapItemBill).collect(Collectors.toList());
        response.put("success", Boolean.TRUE);
        response.put("data", dtos);
        return  ResponseEntity.ok(response);
    }
    @PostMapping("/")
    public ResponseEntity<?> saveItemBill(@Valid @RequestBody ItemBill itemBill, BindingResult result){
        Map<String, Object> response = new HashMap<>();
     //   Optional<Inventories> oinventories = inventoriesDAO.findById(itemBill.getInventories().getId_Inventory());
       // Optional<Bill> oBill = billDAO.findById(itemBill.getBill().getIdBill());
        if(result.hasErrors()){
            response.put("success", Boolean.FALSE);
            response.put("messagge", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(response);
        }
        /*if(oinventories.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", "El producto agregado no exite");
            return ResponseEntity.badRequest().body(response);
        } else if (oBill.isEmpty()) {
            response.put("success", Boolean.FALSE);
            response.put("message", "La factura agregado no exite");
            return ResponseEntity.badRequest().body(response);
        }
        if(itemBill.getCantidad() > oinventories.get().getStock()){
            response.put("success", Boolean.FALSE);
            response.put("message", "No se puede efectuar la factura porque no hay cantidad de productos suficiente");
        }*/
       // itemBill.setBill(oBill.get());
       // itemBill.setInventories(oinventories.get());
        //itemBill.crearPrecioFinal();
        itemBill.caluclarMonto();
        itemBill.getPaymentMethod().setDebe(itemBill.getPriceTotal());
        itemBill.calcularPagos();
        ItemBill save = super.altaEntidad(itemBill);
        ItemBillDTO dto = mapper.mapItemBill(save);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarItemBill(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<ItemBill> itemBill = super.obtenerPorId(id);
       /* if (itemBill.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontro el %s con el id %d ", nombreEntidad, id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }*/
        super.eliminarPorId(id);
        response.put("success", Boolean.TRUE);
        response.put("message", String.format("El %s #%d ha sido eliminado", nombreEntidad, id));
        return ResponseEntity.ok(response);
    }


}
