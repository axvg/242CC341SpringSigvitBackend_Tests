package uni.isw.sigvitbackend.model;

public class Factura extends MedioPago{
    private int ruc;

    public Factura(int ruc) {
        this.ruc = ruc;
    }

    public int getRuc() {
        return ruc;
    }

    public void setRuc(int ruc) {
        this.ruc = ruc;
    }
}
