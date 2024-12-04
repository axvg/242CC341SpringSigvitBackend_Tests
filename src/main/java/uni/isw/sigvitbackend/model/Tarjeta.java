package uni.isw.sigvitbackend.model;

public class Tarjeta extends MedioPago{
    private int nroTarjeta;
    private String titular;

    public Tarjeta(int nroTarjeta, String titular) {
        this.nroTarjeta = nroTarjeta;
        this.titular = titular;
    }

    public int getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(int nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
}
