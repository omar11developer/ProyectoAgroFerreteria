package com.proyect.agroferreteria.controllers.dto;

import com.proyect.agroferreteria.models.dto.CategoryDTO;
import com.proyect.agroferreteria.models.entity.Category;
import com.proyect.agroferreteria.models.mapper.mapstruct.CategoryMapper;
import com.proyect.agroferreteria.services.contracts.CategoryDAO;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/category")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class CategoryDtoController extends GenericoDtoController<Category, CategoryDAO> {
    @Autowired
    private CategoryMapper mapper;

    public CategoryDtoController(CategoryDAO service) {
        super(service, "Category");
    }

    @ApiOperation(value = "Obtener todos los elementos", notes ="Este endpoint obtiene todo los elementos de Categoria")
    @GetMapping("/")
    public ResponseEntity<?> obtenerCategorias(){
        Map<String, Object> mensaje = new HashMap<>();
        List<Category> catogories = super.obtenerTodos();
        if(catogories.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontro %ss cargadas", nombreEntidad));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
        List<CategoryDTO> categoryDTOS = catogories
                .stream()
                .map(mapper::mapCategory)
                .collect(Collectors.toList());
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", categoryDTOS);
        return ResponseEntity.ok(mensaje);
    }
    @ApiOperation(value = "Obtener todos los elementos", notes ="Este endpoint obtiene todo los elementos de Categoria")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorID(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<Category> oCayegory = super.obtenerPorId(id);
        CategoryDTO dto = null;
        Category category;
        if(oCayegory.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("mensaje", String.format("No existe %s con ID %d ", nombreEntidad, id));
            return ResponseEntity.badRequest().body(response);
        }
        category = oCayegory.get();
        dto= mapper.mapCategory(category);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);
    }
    @ApiOperation(value = "Obtener todos los elementos", notes ="Este endpoint obtiene todo los elementos de Categoria")
    @GetMapping("/searchByName/{name}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String name){
        Map<String, Object> response = new HashMap<>();
        Category oCategory = service.getByName(name);
        CategoryDTO dto = null;
        if(oCategory == null){
            response.put("success", Boolean.FALSE);
            response.put("mensaje", String.format("No existe %s con el nombre %s", nombreEntidad, name));
            return ResponseEntity.badRequest().body(response);
        }
        dto = mapper.mapCategory(oCategory);
        response.put("success", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.ok(response);

    }
    @ApiOperation(value = "Obtener todos los elementos", notes ="Este endpoint obtiene todo los elementos de Categoria")
    @PostMapping("/")
    public ResponseEntity<?> saveCategory(@Valid @RequestBody Category category, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        CategoryDTO dto = null;
        Category categoriaLocal = service.getByName(category.getName());

        if (result.hasErrors()){
            response.put("succes", Boolean.FALSE);
            response.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(response);
        }else if(categoriaLocal != null){
            response.put("succes", Boolean.FALSE);
            response.put("validacion", String.format("La %s que desea crear ya existe", nombreEntidad));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Category oCategory = super.altaEntidad((Category) category);
        dto= mapper.mapCategory(oCategory);
        response.put("succes", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @ApiOperation(value = "Obtener todos los elementos", notes ="Este endpoint obtiene todo los elementos de Categoria")
    @PutMapping("/{id}")
    public ResponseEntity<?> editarCategoria(@Valid @RequestBody Category category, BindingResult result, @PathVariable Long id ){
        Map<String, Object> response = new HashMap<>();
        CategoryDTO dto = null;
        Optional<Category> oCategoria = super.obtenerPorId(id);
        Category categoryUpdate;
        if (result.hasErrors()){
            response.put("succes", Boolean.FALSE);
            response.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(response);
        }
        if(oCategoria.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("mensaje", String.format("La %s que desea editar con ID %d no existe", nombreEntidad, id));
            return ResponseEntity.badRequest().body(response);
        }
        categoryUpdate = oCategoria.get();
        categoryUpdate.setName(category.getName());
        Category save = super.altaEntidad(categoryUpdate);
        dto = mapper.mapCategory(save);
        response.put("succes", Boolean.TRUE);
        response.put("data", dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Optional<Category> category = super.obtenerPorId(id);
        if(category.isEmpty()){
            response.put("success", Boolean.FALSE);
            response.put("messagge", String.format("No se encontro una %s con el Id %d", nombreEntidad, id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        Category categoryEliminar = category.get();
        super.eliminarPorId(id);
        CategoryDTO dto = mapper.mapCategory(categoryEliminar);
        response.put("success", Boolean.TRUE);
        response.put("messagge", String.format("%s eliminada con exito", nombreEntidad));
        response.put("data", dto);
        return ResponseEntity.ok(response);
    }
    */

}
