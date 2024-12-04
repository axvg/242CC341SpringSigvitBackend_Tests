package uni.isw.sigvitbackend.model;

import java.util.ArrayList;
import java.util.List;

public class Almacen {
    private List<Producto> productos;
    private List<EmpleadoAlmacen> empleadosAlmacen;
    public Almacen() {
        this.productos = new ArrayList<>();
        this.empleadosAlmacen = new ArrayList<>();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<EmpleadoAlmacen> getEmpleadosAlmacen() {
        return empleadosAlmacen;
    }

    public void setEmpleadosAlmacen(List<EmpleadoAlmacen> empleadosAlmacen) {
        this.empleadosAlmacen = empleadosAlmacen;
    }

    public int mostrarStock(Producto producto){
        return producto.getStock();
    }
}
