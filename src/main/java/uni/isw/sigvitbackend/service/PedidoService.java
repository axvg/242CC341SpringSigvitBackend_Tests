/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.isw.sigvitbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.sigvitbackend.dto.PedidoRequest;
import uni.isw.sigvitbackend.dto.ProductoRequest;
import uni.isw.sigvitbackend.model.Cliente;
import uni.isw.sigvitbackend.model.ComprobantePago;
import uni.isw.sigvitbackend.model.LineaRecibo;
import uni.isw.sigvitbackend.model.LineaReciboId;
import uni.isw.sigvitbackend.model.Pedido;
import uni.isw.sigvitbackend.model.Producto;
import uni.isw.sigvitbackend.repository.ClienteRepository;
import uni.isw.sigvitbackend.repository.ComprobantePagoRepository;
import uni.isw.sigvitbackend.repository.LineaReciboRepository;
import uni.isw.sigvitbackend.repository.PedidoRepository;
import uni.isw.sigvitbackend.repository.ProductoRepository;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    LineaReciboRepository lrRepository;
    @Autowired
    ComprobantePagoRepository cpRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProductoRepository productoRepository;
    
    public void insertPedido(PedidoRequest pr){
        //Guardamos el comprobante en la bd
        ComprobantePago comprobante = new ComprobantePago(
                pr.getNroComprobante(),
                pr.getFechaPedido(),
                null
        );
        cpRepository.save(comprobante);
        Cliente cliente = clienteRepository.findById(pr.getIdCliente()).get();
        
        //Guardamos el pedido en la bd
        Pedido pedido = new Pedido(
                pr.getIdPedido(),
                cliente,
                comprobante,
                pr.getDireccionEntrega()
        );
        pedidoRepository.save(pedido);
        
        for(ProductoRequest productoRequest: pr.getProductosRequest()){
            //Actualizamos el numero de productos disponibles en stock
            Producto producto = productoRepository.findById((long) productoRequest.getIdProducto()).get();
            producto.setStock(
                    producto.getStock() - productoRequest.getStock()
            );
            productoRepository.save(producto);
            
            LineaRecibo lineaRecibo = new LineaRecibo(
                    new LineaReciboId(
                            comprobante,
                            producto
                    ),
                    productoRequest.getStock(),
                    producto.getPrecioVenta()
            );
            lrRepository.save(lineaRecibo);
        }
        
        
        
//        Persona persona = null;
//        if(clienteRequest.getIdPersona()==0){
//            persona = new Persona(
//                    clienteRequest.getIdPersona(),
//                    clienteRequest.getNombre(),
//                    clienteRequest.getApellidoPaterno(),
//                    clienteRequest.getApellidoMaterno(),
//                    clienteRequest.getFechaNacimiento(),
//                    clienteRequest.getDireccion(),
//                    clienteRequest.getTelefono()
//            );
//            personaRepository.save(persona);
//        } else {
//            persona = personaRepository.findById(clienteRequest.getIdPersona()).get();
//        }
//        if (persona == null)
//            return new ClienteResponse();
//        
//        Cliente cliente = new Cliente(
//                clienteRequest.getIdCliente(),
//                clienteRequest.getPassword(),
//                clienteRequest.getEmail(),
//                persona
//        );
//        
//        clienteRepository.save(cliente);
//        return ClienteResponse.fromEntity(cliente);
    }
}
