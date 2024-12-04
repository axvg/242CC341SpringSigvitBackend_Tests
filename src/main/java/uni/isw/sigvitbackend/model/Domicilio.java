package uni.isw.sigvitbackend.model;

public class Domicilio {
    private String domicilio;
    private Pedido pedido;

    public Domicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
}
