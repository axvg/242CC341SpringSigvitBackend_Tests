/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.isw.sigvitbackend.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.isw.sigvitbackend.dto.ClienteRequest;
import uni.isw.sigvitbackend.dto.ClienteResponse;
import uni.isw.sigvitbackend.service.ClienteService;
import uni.isw.sigvitbackend.utils.ErrorResponse;

@RestController
@RequestMapping(path = "api/v1/cliente")
public class ClienteController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    ClienteService clienteService;
    
    @GetMapping()
    public ResponseEntity<?> getClientes(){
        List<ClienteResponse> listaClienteResponse = null;
        try {
            listaClienteResponse = clienteService.listClientes();
        } catch (Exception e) {
            logger.error("Error al traer la lista de clientes", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaClienteResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Clientes not Found").build());
            
        return ResponseEntity.ok(listaClienteResponse);
    }
    //Se buscaran a los clientes por el email
    @GetMapping("/find")
    public ResponseEntity<?> findClienteByEmail(@RequestBody Optional<ClienteRequest> clienteRequest){
        logger.info(">find "+ clienteRequest.toString());
        ClienteResponse clienteResponse = null;
        try {
            clienteResponse = clienteService.findCliente(clienteRequest.get().getEmail());
        } catch (Exception e) {
            logger.error("Error al encontrar el cliente", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(clienteResponse == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cliente not found").build());
            
        return ResponseEntity.ok(clienteResponse);
    }
    
    @PostMapping("/insert")
    public ResponseEntity<?> insertCliente(@RequestBody ClienteRequest clienteRequest){
        logger.info(">insert "+ clienteRequest.toString());
        ClienteResponse clienteResponse = null;
        try {
            clienteResponse = clienteService.insertCliente(clienteRequest);
        } catch (Exception e) {
            logger.error("Error al insertar el cliente", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(clienteResponse == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cliente no insertado").build());
            
        return ResponseEntity.ok(clienteResponse);
    }
    
    @PutMapping("/update")
    public ResponseEntity<?> updateCliente(@RequestBody ClienteRequest clienteRequest){
        logger.info(">update "+ clienteRequest.toString());
        ClienteResponse clienteResponse = null;
        try {
            clienteResponse = clienteService.findCliente(clienteRequest.getEmail());
            if (clienteResponse == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cliente not found").build());
            clienteResponse = clienteService.updateCliente(clienteRequest);
        } catch (Exception e) {
            logger.error("Error al actualizar el cliente", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(clienteResponse == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cliente not updated").build());
            
        return ResponseEntity.ok(clienteResponse);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCliente(@RequestBody Optional<ClienteRequest> clienteRequest){
        logger.info(">delete "+ clienteRequest.toString());
        ClienteResponse clienteResponse = null;
        try {
            clienteResponse = clienteService.findCliente(clienteRequest.get().getEmail());
            if(clienteResponse == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Cliente not found to delete").build());
            clienteService.deleteCliente(clienteResponse.getIdCliente());
        } catch (Exception e) {
            logger.error("Error al eliminar el cliente", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(clienteResponse);
    }
}
