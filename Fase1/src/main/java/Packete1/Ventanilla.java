package Packete1;

public class Ventanilla {
    private String numeroVentanilla;
    private ListaSimple listaImagenes;
    private boolean estaDisponible;
    private int idGrafo;
    private Cliente cliente;
    public Ventanilla(String numeroVentanilla) {
        this.numeroVentanilla = numeroVentanilla;
        this.listaImagenes = null;
        this.estaDisponible = true;
        this.cliente = null;
        idGrafo = 0;
    }

    public String getNumeroVentanilla() {
        return numeroVentanilla;
    }

    public void setNumeroVentanilla(String numeroVentanilla) {
        this.numeroVentanilla = numeroVentanilla;
    }

    public ListaSimple getListaImagenes() {
        return listaImagenes;
    }

    public void setListaImagenes(ListaSimple listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    public boolean isEstaDisponible() {
        return estaDisponible;
    }

    public void setEstaDisponible(boolean estaDisponible) {
        this.estaDisponible = estaDisponible;
    }

    public int getIdGrafo() {
        return idGrafo;
    }

    public void setIdGrafo(int idGrafo) {
        this.idGrafo = idGrafo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
