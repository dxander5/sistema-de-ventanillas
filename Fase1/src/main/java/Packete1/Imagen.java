package Packete1;

public class Imagen {
    private String tipo;
    private int pasos;
    private Cliente cliente;
    private boolean sePuedeImprimir;

    public Imagen(String tipo, Cliente cliente) {
        this.tipo = tipo;
//        this.idCliente = idCliente;
        this.pasos = 0;
        this.cliente = cliente;
        this.sePuedeImprimir = false;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public int getPasos() {
        return pasos;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isSePuedeImprimir() {
        return sePuedeImprimir;
    }

    public void setSePuedeImprimir(boolean sePuedeImprimir) {
        this.sePuedeImprimir = sePuedeImprimir;
    }
}
