package Packete1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Funciones inicio = new Funciones();
//        clonar();
//        oo();
//        inicio.leerArchivo();
        inicio.menu();
//        inicio.crearGraphviz();
//        inicio.listas();

//        prueba();
    }
    public static void clonar(){
        ListaSimple listaSimple = new ListaSimple();
        listaSimple.insertarFinal(10);
        listaSimple.insertarFinal(11);
        listaSimple.insertarFinal(12);
        listaSimple.recorrer();
        System.out.println();
        ListaSimple s = new ListaSimple();
        try {
            s = (ListaSimple) listaSimple.clone();
        }catch (Exception e){

        }
        s.elimiinarInicio();
        s.recorrer();
        System.out.println();
        listaSimple.recorrer();



    }
    public static void prueba() {
        ListaSimple l1 = new ListaSimple();
        ListaSimple l2 = new ListaSimple();
        ListaSimple l3 = new ListaSimple();
        ListaSimple l4 = new ListaSimple();
        ListaSimple l = new ListaSimple();
        Cliente c0 = new Cliente(6, "6", 0, 5, 0, 5);
        Cliente c1 = new Cliente(0, "1", 2, 3, 2, 3);
        Cliente c2 = new Cliente(1, "2", 5, 1, 5, 1);
        Cliente c3 = new Cliente(5, "1", 2, 3, 2, 3);
        l1.insertarFinal(c0);
        l2.insertarFinal(c1);
        l3.insertarFinal(c2);
        l4.insertarFinal(c3);
        l.insertarFinal(l1);
        l.insertarFinal(l2);
        l.insertarFinal(l3);
//        l.insertarFinal(l4);
        l.eliminarEspera(c1);
        l.insertarFinal(l4);
        l.eliminarEspera(c0);
        l.insertarFinal(l1);
        l.eliminarEspera(c2);
//        l.insertarFinal(l1);
//        l.eliminarEspera(c0);
        Nodo aux = l.getPrimero();
        while (aux != null) {
            ListaSimple jj = (ListaSimple) aux.getDato();
            Cliente cliente = (Cliente) jj.getPrimero().getDato();
            System.out.println(cliente.getId_cliente());
            aux = aux.getSiguiente();
        }
    }

    public static void nombres() {
        String[] nombres = {
                "Ethelda",
                "Joey",
                "Rossie",
                "Sashenka",
                "Pippo",
                "Brana",
                "Archy",
                "Jaimie",
                "Shannen",
                "Betta",
                "Peggie",
                "Candis",
                "Shaina",
                "Pennie",
                "Annadiane",
                "Townie",
                "Dulciana",
                "Lyn",
                "Dmitri",
                "Daffy"};
        String[] apellidos = {
                        "Sapsforde" ,
                        "McSaul" ,
                        "Primo" ,
                        "Garnam" ,
                        "Goad" ,
                        "Colchett" ,
                        "Hirche" ,
                        "Camden" ,
                        "Cansdell" ,
                        "Webberley" ,
                        "Kings" ,
                        "Rivers" ,
                        "Bassingham" ,
                        "Furminger" ,
                        "Sebert" ,
                        "Yurukhin" ,
                        "Wagon" ,
                        "Stickney" ,
                        "Shearman" ,
                        "Holstein"
        };
        int min = 1;
        int max = 10;

        Random random = new Random();

//        int value = random.nextInt(max + min) + min;
//        System.out.println(value);


        for(int i = 1; i <=20; i++) {
            //Para hacer de 0 a n
            //int value = random.nextInt(n+1);
            //Para un intervalo de numeros, por ejemplo de 3 a 5
//            int value = (random.nextInt(5-3+1)+3);
            int value = random.nextInt(4);
            System.out.println(value);
        }
//        System.out.println(nombres.length+" "+ apellidos.length);

    }


}
