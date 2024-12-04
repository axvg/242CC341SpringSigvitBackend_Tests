package uni.isw.sigvitbackend.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmpleadoAlmacen extends Empleado{
    private Almacen almacen;
    private List<SolicitudCompra> solicitudesCompra;
}
