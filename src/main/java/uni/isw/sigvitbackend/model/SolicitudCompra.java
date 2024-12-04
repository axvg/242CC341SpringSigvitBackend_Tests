package uni.isw.sigvitbackend.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SolicitudCompra {
    private Timestamp fecha;
    private EmpleadoAlmacen empleadoAlmacen;
    private Administrador administrador;
    private List<LineaCompra> lineas;

    public SolicitudCompra(Timestamp fecha) {
        this.fecha = fecha;
        this.lineas = new ArrayList<>();
    }

    public EmpleadoAlmacen getEmpleadoAlmacen() {
        return empleadoAlmacen;
    }

    public void setEmpleadoAlmacen(EmpleadoAlmacen empleadoAlmacen) {
        this.empleadoAlmacen = empleadoAlmacen;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public List<LineaCompra> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaCompra> lineas) {
        this.lineas = lineas;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public boolean registrarSolicitud(){
        return true;
    }
}
