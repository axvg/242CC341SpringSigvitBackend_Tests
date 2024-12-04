package uni.isw.sigvitbackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.isw.sigvitbackend.dto.PedidoRequest;
import uni.isw.sigvitbackend.dto.PedidoResponse;
import uni.isw.sigvitbackend.service.PedidoService;
import uni.isw.sigvitbackend.utils.ErrorResponse;

@RestController
@RequestMapping(path = "api/v1/pedido")
public class CC_Pedido {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    PedidoService pedidoService;
    
    @PostMapping("/insert")
    public ResponseEntity<?> insertPedido(@RequestBody PedidoRequest pedidoRequest){
        logger.info(">insert "+ pedidoRequest.toString());
//        PedidoResponse pedidoResponse = null;
        try {
            //Verificar que haya suficiente Stock para el pedido
            pedidoService.insertPedido(pedidoRequest);
        } catch (Exception e) {
            logger.error("Error al insertar el pedido", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        if(productoResponse == null)
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Producto no insertado").build());
            
        return ResponseEntity.ok(ErrorResponse.builder().message("Pedido realizado con exito").build());
    }
    
    public void PrepararEntrega(){}

    public void PrepararPago(){}

    public void PrepararDetalle(){}

    public void ValidarTarjeta(){}

    public void Aceptado(){}

    public void AbrirComunicación(){}
}
