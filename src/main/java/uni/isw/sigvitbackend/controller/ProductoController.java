/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.isw.sigvitbackend.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import uni.isw.sigvitbackend.utils.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uni.isw.sigvitbackend.dto.ProductoRequest;
import uni.isw.sigvitbackend.dto.ProductoResponse;
import uni.isw.sigvitbackend.service.ProductoService;

@RestController
@RequestMapping(path = "api/v1/producto")
public class ProductoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    ProductoService productoService;
    
    @GetMapping()
    public ResponseEntity<?> getProductos(){
        List<ProductoResponse> listaProductoResponse = null;
        try {
            listaProductoResponse = productoService.listProductos();
        } catch (Exception e) {
            logger.error("Error al traer la lista de productos", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaProductoResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Productos not Found").build());
            
        return ResponseEntity.ok(listaProductoResponse);
    }
    
    @GetMapping("/find")
    public ResponseEntity<?> findProductoById(@RequestBody Optional<ProductoRequest> productoRequest){
        logger.info(">find "+ productoRequest.toString());
        ProductoResponse productoResponse = null;
        try {
            long id = productoRequest.get().getIdProducto();
            productoResponse = productoService.findProducto(id);
        } catch (Exception e) {
            logger.error("Error al encontrar el producto", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(productoResponse == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Producto not found").build());
            
        return ResponseEntity.ok(productoResponse);
    }
    
    @PostMapping("/insert")
    public ResponseEntity<?> insertProducto(@RequestBody ProductoRequest productoRequest){
        logger.info(">insert "+ productoRequest.toString());
        ProductoResponse productoResponse = null;
        try {
            productoResponse = productoService.insertProducto(productoRequest);
        } catch (Exception e) {
            logger.error("Error al insertar el producto", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(productoResponse == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Producto no insertado").build());
            
        return ResponseEntity.ok(productoResponse);
    }
    
    @PutMapping("/update")
    public ResponseEntity<?> updateProducto(@RequestBody ProductoRequest productoRequest){
        logger.info(">update "+ productoRequest.toString());
        ProductoResponse productoResponse = null;
        try {
       
            productoResponse = productoService.findProducto((long)productoRequest.getIdProducto());
            if (productoResponse == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Producto not found").build());
            productoResponse = productoService.updateProducto(productoRequest);
        } catch (Exception e) {
            logger.error("Error al actualizar el producto", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(productoResponse == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Producto not updated").build());
            
        return ResponseEntity.ok(productoResponse);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProducto(@RequestBody Optional<ProductoRequest> productoRequest){
        logger.info(">delete "+ productoRequest.toString());
        ProductoResponse productoResponse = null;
        try {
            productoResponse = productoService.findProducto((long) productoRequest.get().getIdProducto());
            if(productoResponse == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Producto not found to delete").build());
            productoService.deleteProducto((long) productoRequest.get().getIdProducto());
        } catch (Exception e) {
            logger.error("Error al eliminar el producto", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(productoResponse);
    }
}
