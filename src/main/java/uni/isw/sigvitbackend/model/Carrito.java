package uni.isw.sigvitbackend.model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private Cliente cliente;
    private Pedido pedido;
    private List<Producto> productos;

    public void addProductToCar(Producto producto){
        this.productos = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void deleteProductToCar(Producto producto){
    }
    public List<Producto> showCar(){
        return List.of();
    }
    public void confirmar(){

    }
    public Pedido generarPedido(){
        return null;
    }

}
