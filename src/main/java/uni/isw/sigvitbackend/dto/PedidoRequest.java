/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.isw.sigvitbackend.dto;

import java.util.List;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {
    //datos pedido
    int idPedido;//nullable
    int idCliente;
    int nroComprobante; //nullable
    String direccionEntrega;
    Timestamp fechaPedido;
    
    //Esta lista tendra como datos obligatorios el id del producto y 
    //en stock el numero de productos que se desean pedir
    List<ProductoRequest> productosRequest;
}
