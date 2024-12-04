package uni.isw.sigvitbackend.model;

public class Tienda {
    private String direccion;
    private String nombre;
    private Tienda tienda;

    public Tienda(String direccion, String nombre) {
        this.direccion = direccion;
        this.nombre = nombre;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
