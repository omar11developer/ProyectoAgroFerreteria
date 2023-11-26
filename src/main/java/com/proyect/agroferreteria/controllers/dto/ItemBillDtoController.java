package com.proyect.agroferreteria.controllers.dto;

import com.proyect.agroferreteria.models.dto.ItemBillDTO;
import com.proyect.agroferreteria.models.entity.Bill;
import com.proyect.agroferreteria.models.entity.Client;
import com.proyect.agroferreteria.models.entity.ItemBill;
import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.mapper.mapstruct.ItemBillMapper;
import com.proyect.agroferreteria.services.contracts.BillDAO;
import com.proyect.agroferreteria.services.contracts.ClientDAO;
import com.proyect.agroferreteria.services.contracts.ItemBillDAO;
import com.proyect.agroferreteria.services.contracts.ProductDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
    private final ProductDAO productDAO;

    private  final ClientDAO clientDAO;

    public ItemBillDtoController(ItemBillDAO service, BillDAO billDAO, ProductDAO productDAO, ClientDAO clientDAO) {
        super(service, "Factura item");

        this.billDAO = billDAO;
        this.productDAO = productDAO;
        this.clientDAO = clientDAO;
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
    @PostMapping("/facturaId/{idFactura}/")
    public ResponseEntity<?> saveItemBill(@Valid @RequestBody ItemBillDTO itemBill, BindingResult result,  @PathVariable Long idFactura){
        Map<String, Object> response = new HashMap<>();
        Optional<Bill> oBill = billDAO.findById(idFactura);
        Optional<Product> product = productDAO.findById(itemBill.getProduct().getId());
        if(result.hasErrors()){
            response.put("success", Boolean.FALSE);
            response.put("messagge", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(response);
        }
        if (oBill.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontro una factura con el id %d ",idFactura));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        if(product.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontro el producto  con el nombre %s ",itemBill.getProduct().getName()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        itemBill.setBill(oBill.get());
        itemBill.setProduct(product.get());
        itemBill.setPriceTotal(itemBill.getCantidad() * itemBill.getProduct().getSalePrice());

        ItemBill itemBillSave = super.altaEntidad(mapper.mapItemBill(itemBill));
        ItemBillDTO dto = mapper.mapItemBill(itemBillSave);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);


    }
    @PutMapping("/facturaId/{idFactura}/idItem/{id}")
    public ResponseEntity<?> editarItemBill(@Valid @RequestBody ItemBillDTO itemBill, BindingResult result,  @PathVariable Long idFactura, @PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<Bill> oBill = billDAO.findById(idFactura);
        Optional<Product> product = productDAO.findById(itemBill.getProduct().getId());
        Optional<ItemBill> oitemBill = super.obtenerPorId(id);
        if(result.hasErrors()){
            response.put("success", Boolean.FALSE);
            response.put("messagge", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(response);
        }
        if(oBill.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontro un item factura con el id %d ",id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        if (oBill.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontro una factura con el id %d ",idFactura));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        if(product.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontro el producto  con el nombre %s ",itemBill.getProduct().getName()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        ItemBill itemBillUpdate = oitemBill.get();
        itemBillUpdate.setBill(oBill.get());
        itemBillUpdate.setProduct(product.get());
        itemBillUpdate.setCantidad(itemBill.getCantidad());
        itemBillUpdate.getPaymentMethod().setNamePaymentMethod(itemBill.getPaymentMethod().getNamePaymentMethod());
        itemBillUpdate.setPriceTotal(itemBillUpdate.getCantidad() * itemBillUpdate.getProduct().getSalePrice());
        super.altaEntidad(itemBillUpdate);
        ItemBillDTO dto = mapper.mapItemBill(itemBillUpdate);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);


    }
    @DeleteMapping("/clienteFactura/{idCliente}/facturaId/{idFactura}/{id}")
    public ResponseEntity<?> eliminarItemBill(@PathVariable Long id, @PathVariable Long idCliente, @PathVariable Long idFactura){
        Map<String, Object> response = new HashMap<>();
        Optional<ItemBill> itemBill = super.obtenerPorId(id);
        Optional<Bill> oBill = billDAO.findById(idFactura);
        Optional<Client> oClient = clientDAO.findById(idCliente);
       if (itemBill.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontro el %s con el id %d ", nombreEntidad, id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        if (oBill.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontro una factura con el id %d ",idFactura));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        if (oClient.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("No se encontro una factura con el id %d ",idCliente));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        if(oBill.get().getId() != itemBill.get().getBill().getId()){
            super.eliminarPorId(id);
            response.put("success", Boolean.FALSE);
            response.put("message", String.format("El id de la factura no coinciden con la factura a eliminar"));
            return ResponseEntity.ok(response);
        }
        super.eliminarPorId(id);
        response.put("success", Boolean.TRUE);
        response.put("message", String.format("El %s #%d ha sido eliminado", nombreEntidad, id));
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> validacionID(MethodArgumentTypeMismatchException ex){
        Map<String, Object> response = new HashMap<>();
        response.put("success", Boolean.FALSE);
        response.put("Message", "El parametro 'id' debe ser un numero");
        return ResponseEntity.badRequest().body(response);
    }
}
