package uni.isw.sigvitbackend.model;

public class LineaCompra {
    private int cantidad;
    private int precio;
    private SolicitudCompra solicitudCompra;
    private Producto producto;

    public LineaCompra(int cantidad, int precio) {
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public SolicitudCompra getSolicitudCompra() {
        return solicitudCompra;
    }

    public void setSolicitudCompra(SolicitudCompra solicitudCompra) {
        this.solicitudCompra = solicitudCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public double subtotal(){
        return (double) cantidad*precio;
    }
}
