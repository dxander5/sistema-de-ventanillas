package Packete1;

public class ListaSimple implements Cloneable {
    private Nodo primero;
    private Nodo ultimo;
    private int size;

    public ListaSimple() {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }

    public void insertarInicio(Object dato){
        this.size+=1;
        if (estaVacia()){
            this.primero = this.ultimo = new Nodo(dato);
        }
        else {
            Nodo aux = this.primero;
            this.primero = new Nodo(dato);
            this.primero.setSiguiente(aux);
        }



    }

    public void insertarFinal(Object dato){
        this.size+=1;
        if(estaVacia()){
            this.primero = this.ultimo = new Nodo(dato);
        }
        else {
            Nodo aux = this.ultimo;
            this.ultimo = new Nodo(dato);
            aux.setSiguiente(this.ultimo);
        }

    }



//    public Object eliminarDato(Object dato){
//        Nodo aux = this.primero;
//        Nodo anterior = aux;
//        while (aux != null){
//            if (aux.getDato() == dato){
//                anterior.setSiguiente(aux.getSiguiente());
//                break;
//            }
//            anterior = aux;
//            aux = aux.getSiguiente();
//
//        }
//        return aux.getDato();
//    }
    public Object eliminarEspera(Cliente cliente){
//        this.size-=1;
        Nodo aux = this.primero;
//        ListaSimple listaClientesImgs = (ListaSimple) aux.getDato();
//        Cliente cliente1 = (Cliente) listaClientesImgs.primero.getDato();
//        if (cliente1 == cliente){
////            System.out.println(cliente1.getNombre_cliente()+" ELIMINADO");
//            elimiinarInicio();
//            return aux.getDato();
//        }
////        System.out.println("sigue");
        Nodo anterior = aux;
        while (aux != null){
            ListaSimple listaClientesImgs = (ListaSimple) aux.getDato();
            Cliente cliente1 = (Cliente) listaClientesImgs.getPrimero().getDato();
            if (cliente1 == cliente){
//                System.out.println(cliente1.getNombre_cliente()+" ELIMINADO");
                if(aux == this.primero){
                    elimiinarInicio();
                    return aux.getDato();
                }
                anterior.setSiguiente(aux.getSiguiente());
                this.size-=1;
                return aux.getDato();
            }
            anterior = aux;
            aux = aux.getSiguiente();

        }
        return aux.getDato();
    }
    public void recorrer(){
        Nodo aux = this.primero;
        while (aux != null){
            System.out.print(aux.getDato()+"-->");
            aux = aux.getSiguiente();

        }

    }


    public void recorrerColaRecepcion(){
        Nodo aux = this.primero;
        while (aux != null){
            Cliente cliente = (Cliente) aux.getDato();
            System.out.println(cliente.mostrarDatos());
            aux = aux.getSiguiente();

        }
    }

    public boolean estaVacia(){
        return this.primero == null;
    }


    public void elimiinarInicio(){
        this.primero = this.primero.getSiguiente();
        this.size-=1;

    }

    public Nodo getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void buscarCliente(String dato){
        Nodo aux = this.primero;
        boolean esNumero = dato.chars().allMatch( Character::isDigit );
        if(esNumero){
            int id = Integer.parseInt(dato);
            while (aux!=null){
                Cliente cliente = (Cliente) aux.getDato();
                if(cliente.getId_cliente()==id){

                    System.out.println(cliente.toString());
                    return;
                }
                aux = aux.getSiguiente();
            }
        }else{
            while (aux!=null){
                Cliente cliente = (Cliente) aux.getDato();
//                System.out.println("."+cliente.getNombre_cliente()+"."+dato);
                if(cliente.getNombre_cliente().equals(dato)){
                    System.out.println(cliente.toString());
                    return;
                }
                aux = aux.getSiguiente();
            }

        }

        System.out.println("Cliente no encontrado");
    }

    public void clienteConMasPasos(){
            Nodo aux = this.primero;
            while (aux!=null){
                Cliente cliente = (Cliente) aux.getDato();
                Nodo aux2 = this.primero;
                Nodo aux3 = this.primero.getSiguiente();
                while (aux3!=null){
                    Cliente cliente1 = (Cliente) aux2.getDato();
                    cliente = (Cliente) aux3.getDato();
                    if(cliente.getPasos()>cliente1.getPasos()){
                        Cliente tmp = cliente;
                        aux3.setDato(cliente1);
                        aux2.setDato(tmp);
                    }
                    aux2 = aux2.getSiguiente();
                    aux3 = aux3.getSiguiente();
                }
                aux = aux.getSiguiente();
            }



                Cliente cliente = (Cliente) this.primero.getDato();
                System.out.println("El cliente que mas pasos estuvo en el sistema fue el siguiente");
                System.out.println(cliente.toString());


    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }


}


