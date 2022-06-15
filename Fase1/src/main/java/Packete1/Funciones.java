package Packete1;

import com.google.gson.Gson;

import java.io.*;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
public class Funciones {
    private boolean colorDisponible = true;
    private boolean BNDisponible = true;
    private final ListaSimple listaVentanillas = new ListaSimple();
    private final ListaSimple colaRecepcion = new ListaSimple();
    private final ListaSimple colaColor = new ListaSimple();
    private final ListaSimple colaBN = new ListaSimple();
    private final ListaSimple clientesEnEspera = new ListaSimple();
    private final ListaSimple clientesAtendidos = new ListaSimple();
    private int contadorId = 0;
    private final String[] nombres = {
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
    private final String[] apellidos = {
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
//    private final
//    private Cliente clienteEnEspera = null;
    private int numeroPaso = 0;
    //    private ListaSimple listaVentanillas = new ListaSimple();


    public String leerArchivo() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String contenido = "";

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            Scanner Entradaruta = new Scanner(System.in);
            System.out.println("Carga Masiva de Clientes");
            System.out.print("Ingresar ruta del archivo:  ");
            String ruta = Entradaruta.nextLine();

            archivo = new File(ruta);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido += linea;
            }
//            System.out.println(contenido);
//            ListaSimple colaRecepcion = crearClientes(contenido);
//            ListaSimple listaVentanillas = crearVentanillas();
            ListaSimple colaImpresoras = new ListaSimple();
            crearClientes(contenido);
            crearVentanillas();
            menu();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();

            }
        }
        return contenido;
    }

    public ListaSimple crearClientes(String contenido) {
        ListaSimple colaRecepcion = new ListaSimple();

        try {
            Gson gson = new Gson();
            //Convirtiendo el contenido a diccionario, esto devuelve
            //Cliente1 que es otro json
            Map diccionarioClienteN = gson.fromJson(contenido, Map.class);
            Set<String> keys = diccionarioClienteN.keySet();
            int id_cliente = 0;
            for (String key : keys) {
                System.out.println(key);
                //Covertimos el diccionario a string para poder generar
                //otro diccionario pasando el string
                String json = gson.toJson(diccionarioClienteN.get(key));
                //Creamos el diccionario con los atributos de los clientes
                Map diccionarioValores = gson.fromJson(json, Map.class);
//            System.out.println(diccionarioValores);
//                System.out.println(diccionarioValores.get("id_cliente").getClass().getSimpleName());

//            Set<String> keysA = diccionarioValores.keySet();
//                id_cliente = Integer.parseInt(diccionarioValores.get("id_cliente").toString());
//                int img_bw = Integer.parseInt(diccionarioValores.get("img_bw").toString());
//                int img_color = Integer.parseInt(diccionarioValores.get("img_color").toString());
                double id = (double) diccionarioValores.get("id_cliente");
                double color = (double) diccionarioValores.get("img_color");
                double bw = (double) diccionarioValores.get("img_bw");

                id_cliente = (int) id;
                int img_bw = (int) bw;
                int img_color = (int) color;
                String nombre_cliente = diccionarioValores.get("nombre_cliente").toString();

                Cliente nuevoCliente = new Cliente(id_cliente, nombre_cliente, img_color, img_bw, img_color, img_bw);
                this.colaRecepcion.insertarFinal(nuevoCliente);
//            System.out.println(nuevoCliente.mostrarDatos());
                //Usar la siguiente sintaxis si no se saben las claves
//            for(String keyA:keysA){
//                System.out.print(keyA+" ");
//                String valor = diccionarioValores.get(keyA).toString();
//                System.out.print(valor+" ");
//            }
//            System.out.println();
            }
            this.contadorId = id_cliente;

            System.out.println("Clientes Cargados exitosamente");
//            verColaRecepcion();

        } catch (Exception e) {
            System.out.println("Error al cargar clientes");
            e.printStackTrace();

        }
        return colaRecepcion;
    }

    public void crearGraphviz(ListaSimple lista) {
        try {
            String ruta = "graph.dot";
            String contenido = "Contenido de ejemplo";
            StringBuilder nodos = new StringBuilder();
            StringBuilder conectarNodos = new StringBuilder();
            nodos.append("digraph ejemplo {\n");
            Nodo aux = lista.getPrimero();
            int id = 0;
            int idAnterior = 0;
            while (aux != null) {
                Cliente cliente = (Cliente) aux.getDato();
                nodos.append(String.format("%d [label=\"%s\"]", id, cliente.getNombre_cliente()));
                nodos.append("\n");
                aux = aux.getSiguiente();
                if (id > 0) {
                    conectarNodos.append(idAnterior + "->" + id + "\n");
                }
                idAnterior = id;
                id++;


            }
            nodos.append(conectarNodos);
            nodos.append("rankdir=TB\n}");
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(nodos.toString());
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String parametros[] = new String[5];
        parametros[0] = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        parametros[1] = "-Tpng";
        parametros[2] = "C:\\Users\\diego\\Desktop\\USAC\\EstructurasDeDatos\\EDD_UDRAWING_FASE_201903909\\Fase1\\graph.dot";
        parametros[3] = "-o";
        parametros[4] = "C:\\Users\\diego\\Desktop\\USAC\\EstructurasDeDatos\\EDD_UDRAWING_FASE_201903909\\Fase1\\g.png";

        try {
            Process proceso = Runtime.getRuntime().exec(parametros, null);
            proceso.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("errror");
            e.printStackTrace();
        }

    }

    public ListaSimple crearVentanillas() {
        System.out.println("Cantidad de ventanillas");
        Scanner EntradaNumeroVentanillas = new Scanner(System.in);
        System.out.print("Ingresar la cantidad de ventanillas:  ");
        int numeroVentanillas = EntradaNumeroVentanillas.nextInt();
        ListaSimple listaVentanillas = new ListaSimple();

        try {
            for (int i = 1; i <= numeroVentanillas; i++) {
                Ventanilla nuevaVentanilla = new Ventanilla("Ventanilla" + i);
                this.listaVentanillas.insertarFinal(nuevaVentanilla);
            }
            System.out.println("Ventanillas creadas exitosamente");
//            verListaVentanillas();
//            ejecutarPaso(listaVentanillas);
//            menu(null, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaVentanillas;
    }

    public void menu() {
        Scanner entradaEscaner = new Scanner(System.in);
        System.out.println("*****MENU*****");
        System.out.println("1. Parametros Iniciales");
        System.out.println("2. Ejecutar Paso");
        System.out.println("3. Estado en memoria de las estructuras");
        System.out.println("4. Reportes");
        System.out.println("5. Datos del estudiante");
        System.out.println("6. Salir");
        System.out.print("Seleccionar Opcion:  ");
        String opcion = entradaEscaner.nextLine();
        switch (opcion) {
            case "1":
                leerArchivo();
                break;
            case "2":
                ejecutarPaso();
                break;
            case "3":
                verColaImpresoras();
                verClientesAtendidos();
                verListaVentanillas();
                verColaRecepcion();
                verClientesEnEspera();
                menu();
                break;
            case "4":
//                Scanner opcionReporte = new Scanner(System.in);
                System.out.println("*****MENU REPORTES*****");
                System.out.println("1. Top 5 de clientes con mayor cantidad de imágenes a color.");
                System.out.println("2. Top 5 de clientes con menor cantidad de imágenes en blanco y negro.");
                System.out.println("3. Información del cliente que más pasos estuvo en el sistema.");
                System.out.println("4. Datos de un cliente en específico");
                System.out.print("Seleccionar Opcion:  ");
                String opcionElegida = entradaEscaner.nextLine();
                switch (opcionElegida){
                    case "1":
                        topClientesImgColor();
                        break;
                    case "2":
                        topClientesMenosImgBN();
                        break;
                    case "3":
                        try {
                            ListaSimple clienteConMasPasos = (ListaSimple) this.clientesAtendidos.clone();
                            clienteConMasPasos.clienteConMasPasos();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case "4":
//                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Ingresar id o nombre del cliente");
                        String dato = entradaEscaner.nextLine();
                        dato = dato.trim();
                        this.clientesAtendidos.buscarCliente(dato);
                        break;
                    default:
                        System.out.println("Opcion invalida");
                        break;
                }
                menu();
                break;
            case "5":
                System.out.println("-".repeat(10)+"Diego Alexander Acetun Chicol estudiante de Ingenieria en Sistemas"+"-".repeat(10));
                System.out.println("-".repeat(10)+"Carnet: 201903909"+"-".repeat(10));
                menu();
                break;

            case "6":
                break;
            default:
                System.out.println("Opcion no disponible");
                menu();
                break;

        }
        System.out.println();
    }

    public void verColaRecepcion() {
        try {
            String ruta = "colaRecepcion.dot";
            StringBuilder nodos = new StringBuilder();
            StringBuilder conectarNodos = new StringBuilder();
            nodos.append("digraph ejemplo {\nnode[shape=box]\nedge[arrowhead=none]\n");
            Nodo aux = this.colaRecepcion.getPrimero();
            int id = 0;
            int idAnterior = 0;
            while (aux != null) {
                Cliente cliente = (Cliente) aux.getDato();
                nodos.append(String.format("%d [label=\"%s\\nIMG C %d\\nIMG BN %d\"]", id,
                        cliente.getNombre_cliente(), cliente.getImg_color(), cliente.getImg_bw()
                ));
                nodos.append("\n");
                aux = aux.getSiguiente();
                if (id > 0) {
                    conectarNodos.append(idAnterior + "->" + id + "\n");
                }
                idAnterior = id;
                id++;


            }
            nodos.append(conectarNodos);
            nodos.append("rankdir=LR\n}");
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(nodos.toString());
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String parametros[] = new String[5];
        parametros[0] = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        parametros[1] = "-Tpng";
        parametros[2] = "C:\\Users\\diego\\Desktop\\USAC\\EstructurasDeDatos\\EDD_UDRAWING_FASE_201903909\\Fase1\\colaRecepcion.dot";
        parametros[3] = "-o";
        parametros[4] = "C:\\Users\\diego\\Desktop\\USAC\\EstructurasDeDatos\\EDD_UDRAWING_FASE_201903909\\Fase1\\colaRecepcion.png";

        try {
            Process proceso = Runtime.getRuntime().exec(parametros, null);
            proceso.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("errror");
            e.printStackTrace();
        }

    }

    public void verListaVentanillas() {
        try {
            String ruta = "listaVentanillas.dot";
            StringBuilder nodos = new StringBuilder();
            StringBuilder conectarNodos = new StringBuilder();
            StringBuilder rank = new StringBuilder();

            nodos.append("digraph listaVentanillas {\nnode[shape=box]\nedge[arrowhead=none]\n");
            Nodo aux = this.listaVentanillas.getPrimero();
            int id = 0;
            int idAnterior = 0;
            boolean esPrimeraVentanilla = true;
            while (aux != null) {
                Ventanilla ventanilla = (Ventanilla) aux.getDato();
                ventanilla.setIdGrafo(id);
                nodos.append(String.format("%d [label=%s]", id, ventanilla.getNumeroVentanilla()));
                nodos.append("\n");

                if (esPrimeraVentanilla==false) {
                    conectarNodos.append(idAnterior + "->" + id + "\n");
                }

                idAnterior = id;
                id++;
                if (ventanilla.getCliente()!=null){
                    nodos.append(String.format("%d [label=\"%s\\nIMG C %d\\nIMG BN %d\"]\n", id,
                            ventanilla.getCliente().getNombre_cliente(), ventanilla.getCliente().getImgColorConstante(),
                            ventanilla.getCliente().getImgBNConstante()
                    ));
                    conectarNodos.append(id + "->" + idAnterior + "\n");
                    rank.append(String.format("{rank=same; %d; %d}\n", id, idAnterior));
                    id++;
                    if (ventanilla.getListaImagenes()!=null){
                        int idAnteriorImg = idAnterior;
                        Nodo auxImg = ventanilla.getListaImagenes().getPrimero();
                        while (auxImg!=null){
                            Imagen imagen = (Imagen) auxImg.getDato();
                            nodos.append(String.format("%d[label=%s]\n", id, imagen.getTipo()));
                            conectarNodos.append(idAnteriorImg+"->"+id+"\n");
                            rank.append(String.format("{rank=same; %d; %d}\n", idAnteriorImg, id));
                            idAnteriorImg = id;
                            id++;
                            auxImg = auxImg.getSiguiente();

                        }
                    }

                }
                esPrimeraVentanilla=false;
                aux = aux.getSiguiente();

            }

            nodos.append(conectarNodos);
            nodos.append(rank);
            nodos.append("rankdir=TB\n}");
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(nodos.toString());
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String parametros[] = new String[5];
        parametros[0] = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        parametros[1] = "-Tpng";
        parametros[2] = "C:\\Users\\diego\\Desktop\\USAC\\EstructurasDeDatos\\EDD_UDRAWING_FASE_201903909\\Fase1\\listaVentanillas.dot";
        parametros[3] = "-o";
        parametros[4] = "C:\\Users\\diego\\Desktop\\USAC\\EstructurasDeDatos\\EDD_UDRAWING_FASE_201903909\\Fase1\\listaVentanillas.png";

        try {
            Process proceso = Runtime.getRuntime().exec(parametros, null);
            proceso.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("errror");
            e.printStackTrace();
        }
    }

    public void verClientesAtendidos(){
        try {
            String ruta = "clientesAtendidos.dot";
            StringBuilder nodos = new StringBuilder();
            StringBuilder conectarNodos = new StringBuilder();
            nodos.append("digraph clientesAtendidos {\nnode[shape=box]\nedge[arrowhead=none]\n");
            Nodo aux = this.clientesAtendidos.getPrimero();
            int id = 0;
            int idAnterior = 0;
            while (aux != null) {
                Cliente cliente = (Cliente) aux.getDato();
                nodos.append(String.format("%d [label=\"%s\\nIMG C %d\\nIMG BN %d\"]", id,
                        cliente.getNombre_cliente(), cliente.getImg_color(), cliente.getImg_bw()
                ));
                nodos.append("\n");
                aux = aux.getSiguiente();
                if (id > 0) {
                    conectarNodos.append(idAnterior + "->" + id + "\n");
                }
                idAnterior = id;
                id++;


            }
            nodos.append(conectarNodos);
            nodos.append("rankdir=LR\n}");
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(nodos.toString());
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String parametros[] = new String[5];
        parametros[0] = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        parametros[1] = "-Tpng";
        parametros[2] = "C:\\Users\\diego\\Desktop\\USAC\\EstructurasDeDatos\\EDD_UDRAWING_FASE_201903909\\Fase1\\clientesAtendidos.dot";
        parametros[3] = "-o";
        parametros[4] = "C:\\Users\\diego\\Desktop\\USAC\\EstructurasDeDatos\\EDD_UDRAWING_FASE_201903909\\Fase1\\clientesAtendidos.png";

        try {
            Process proceso = Runtime.getRuntime().exec(parametros, null);
            proceso.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("errror");
            e.printStackTrace();
        }
    }

    public void espera(){
        Nodo aux = this.clientesEnEspera.getPrimero();
        System.out.println("CLIENTES EN ESPERA");
        while (aux!=null){
            ListaSimple lis = (ListaSimple) aux.getDato();
            Cliente cliente = (Cliente) lis.getPrimero().getDato();
            System.out.println(cliente.getNombre_cliente());
            aux = aux.getSiguiente();
        }
    }
    public void ejecutarPaso() {
        //Generando nuevos clientes
        Random random = new Random();
        int numero = random.nextInt(4);
        for(int i=0; i<numero; i++){
            this.contadorId++;
            int nNombre = random.nextInt(this.nombres.length) ;
            int nApellido = random.nextInt(this.apellidos.length);
            int imagenesColor = random.nextInt(5);
            int imagenesBN = random.nextInt(5);
            String nombre = this.nombres[nNombre]+" "+this.apellidos[nApellido];
            Cliente nuevoCliente = new Cliente(contadorId, nombre, imagenesColor, imagenesBN, imagenesColor, imagenesBN);
            this.colaRecepcion.insertarFinal(nuevoCliente);
        }

        Nodo auxCola = this.colaRecepcion.getPrimero();
        while (auxCola!=null){
            Cliente cliente = (Cliente) auxCola.getDato();
            cliente.setPasos(cliente.getPasos()+1);
            auxCola = auxCola.getSiguiente();
        }

        Nodo auxClientesEspera = this.clientesEnEspera.getPrimero();
        while (auxClientesEspera!=null){
            ListaSimple listaCientesImg = (ListaSimple) auxClientesEspera.getDato();
            Cliente cliente = (Cliente) listaCientesImg.getPrimero().getDato();
            cliente.setPasos(cliente.getPasos()+1);
            auxClientesEspera = auxClientesEspera.getSiguiente();
        }

        this.numeroPaso++;
        System.out.println("-".repeat(10)+"PASO "+this.numeroPaso+"-".repeat(10));
        Nodo aux3 = this.clientesEnEspera.getPrimero();
        while (aux3!=null){
            ListaSimple listaClientesImgs = (ListaSimple) aux3.getDato();
            Cliente cliente = (Cliente) listaClientesImgs.getPrimero().getDato();
            if(cliente.isTieneTodasSusImgs()){
                System.out.println("El cliente "+cliente.getNombre_cliente()+" recibio todas sus imagenes en "+cliente.getPasos()
                +" pasos");
                this.clientesAtendidos.insertarFinal(cliente);
                this.clientesEnEspera.eliminarEspera(cliente);
            }
            aux3 = aux3.getSiguiente();
        }

        //El error esta en este metodo
//            Cliente clienteAInsertar = (Cliente) colaRecepcion.getPrimero().getDato();
        //Eliminamos el cliente de la cola de recepcion y se inserta en la lista de ventanillas
//            colaRecepcion.elimiinarInicio();
        Nodo aux = this.listaVentanillas.getPrimero();
//            Nodo aux2 = listaVentanillas.getPrimero();
        /*Lo primero es recorrer las ventanillas y ver cual esta vacia
         */
//        if (colaImpresoras.getPrimero()==null){
//            ListaSimple colaColor = new ListaSimple();
//            colaImpresoras.insertarFinal(colaColor);
//            ListaSimple colaBN = new ListaSimple();
//            colaImpresoras.insertarFinal(colaBN);
//        }
        boolean unSoloPaso = true;
        boolean unSoloCliente = true;

        while (aux != null) {
            Ventanilla ventanilla = (Ventanilla) aux.getDato();
            if (ventanilla.isEstaDisponible() && this.colaRecepcion.getPrimero() != null && unSoloCliente) {
                unSoloCliente=false;
                Cliente clienteAInsertar = (Cliente) this.colaRecepcion.getPrimero().getDato();
                System.out.println(clienteAInsertar.getNombre_cliente()+" Ingresa a la "+ventanilla.getNumeroVentanilla());
//                System.out.println(ventanilla.getNumeroVentanilla() + " disponible");
//                System.out.println("Cliente " + clienteAInsertar.getNombre_cliente() + " insertado");
                this.colaRecepcion.elimiinarInicio();
//                    listaVentanillas.insertarFinal(clienteAInsertar);
                ventanilla.setCliente(clienteAInsertar);
                ventanilla.setEstaDisponible(false);
                aux = aux.getSiguiente();
                continue;
            }

//                System.out.println(ventanilla.getNumeroVentanilla());
//                System.out.println("Tiene al cliente " + ventanilla.getCliente().getNombre_cliente());
//                System.out.println("Con " + ventanilla.getCliente().getImg_color() + " " + ventanilla.getCliente().getImg_bw());
                //Aqui recorro los clientes en las ventanillas
                if (ventanilla.getCliente()==null){
                    aux = aux.getSiguiente();
                    continue;
                }
                Cliente cliente = (Cliente) ventanilla.getCliente();
                cliente.setPasos(cliente.getPasos()+1);
                ListaSimple pilaImagenes;
                if (ventanilla.getListaImagenes() != null) {
                    pilaImagenes = ventanilla.getListaImagenes();

                } else {
//                    System.out.println("Lista imagenes de " + ventanilla.getNumeroVentanilla() + " es null");
                    pilaImagenes = new ListaSimple();
                }
                //Recibiendo imgs en ventanilla

                if (cliente.getImg_color() > 0) {
                    System.out.println("La "+ventanilla.getNumeroVentanilla()+" recibio una imagen");
//                    System.out.println("Cliente " + cliente.getNombre_cliente() + " Insertando imagen a color");
                    Imagen nuevaImg = new Imagen("Color", cliente);
                    nuevaImg.setPasos(2);
                    pilaImagenes.insertarInicio(nuevaImg);
                    ventanilla.setListaImagenes(pilaImagenes);
                    cliente.setImg_color(cliente.getImg_color() - 1);

                } else if (cliente.getImg_bw() > 0) {
                    System.out.println("La "+ventanilla.getNumeroVentanilla()+" recibio una imagen");
//                    System.out.println(cliente.getNombre_cliente() + " Insertando imagen BN");
                    Imagen nuevaImg = new Imagen("BN", cliente);
                    nuevaImg.setPasos(1);
                    pilaImagenes.insertarInicio(nuevaImg);
                    ventanilla.setListaImagenes(pilaImagenes);
                    cliente.setImg_bw(cliente.getImg_bw() - 1);

                }else{
                        Nodo auxImg = ventanilla.getListaImagenes().getPrimero();
                        while (auxImg!=null){
                            Imagen imagen = (Imagen) auxImg.getDato();
                            if(imagen.getTipo()=="Color"){
                                this.colaColor.insertarFinal(imagen);
                            }else if(imagen.getTipo()=="BN"){
                                this.colaBN.insertarFinal(imagen);
                            }
                            auxImg = auxImg.getSiguiente();
                        }
//                    colaImpresoras.insertarFinal(colaColor);
//                    colaImpresoras.insertarFinal(colaBN);
//                    System.out.println(ventanilla.getNumeroVentanilla() + " disponible");
                        ventanilla.setEstaDisponible(true);
                        ventanilla.setCliente(null);
//                    System.out.println("Se elimino el cliente nombre" + cliente.getNombre_cliente());
                        ventanilla.setListaImagenes(null);
                        System.out.println("El cliente"+cliente.getNombre_cliente()+" sale de la "+ventanilla.getNumeroVentanilla()+
                                " sus imagenes se envian a la cola de impresoras");
                        ListaSimple listaClienteImagenes = new ListaSimple();
                        listaClienteImagenes.insertarFinal(cliente);
                        this.clientesEnEspera.insertarFinal(listaClienteImagenes);
//                    colaImpresoras = pasoImpresora(colaImpresoras);
//                    verColaImpresoras(colaImpresoras);
                        continue;
                    }
            aux = aux.getSiguiente();


        }
//            verListaVentanillas(listaVentanillas);
        if (this.colaColor.getSize()>0 || this.colaBN.getSize()>0){
            pasoImpresora();
//            verClientesEnEspera();
//            verColaImpresoras();
        }
//        verClientesAtendidos();
//        espera();
//        verListaVentanillas();
        menu();
    }

    public void verColaImpresoras(){
        try{
            String ruta = "colaImpresoras.dot";
            StringBuilder nodos = new StringBuilder();
            StringBuilder conectarNodos = new StringBuilder();
            nodos.append("digraph colaImpresoras {\nnode[shape=box]\nedge[arrowhead=none]\n");
            int id = 0;
            int idAnterior = id;
            boolean esPrimerCliente = true;
            nodos.append(String.format("%d [label=\"IMPRESORA\\nColor\"]\n", id));
            int idColor = id;
            idAnterior = id;
            id++;
            Nodo aux = this.colaColor.getPrimero();
            while (aux!=null){
                nodos.append(String.format("%d [label=\"IMG\\nCOLOR\"]\n", id));
                conectarNodos.append(idAnterior+"->"+id+"\n");
                idAnterior = id;
                id++;
                aux=aux.getSiguiente();
            }
            nodos.append(String.format("%d [label=\"IMPRESORA\\nB Y N\"]\n", id));
            int idBN = id;
            idAnterior = id;
            id++;
            aux = this.colaBN.getPrimero();
            while (aux!=null){
                nodos.append(String.format("%d [label=\"IMG\\nBN\"]\n", id));
                conectarNodos.append(idAnterior+"->"+id+"\n");
                idAnterior = id;
                id++;
                aux=aux.getSiguiente();
            }
            nodos.append(conectarNodos);
            nodos.append(String.format("{rank=same; %d; %d}\n", idColor, idBN));
            nodos.append("rankdir=LR\n}");

            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(nodos.toString());
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String parametros[] = new String[5];
        parametros[0] = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
        parametros[1] = "-Tpng";
        parametros[2] = "C:\\Users\\diego\\Desktop\\USAC\\EstructurasDeDatos\\EDD_UDRAWING_FASE_201903909\\Fase1\\colaImpresoras.dot";
        parametros[3] = "-o";
        parametros[4] = "C:\\Users\\diego\\Desktop\\USAC\\EstructurasDeDatos\\EDD_UDRAWING_FASE_201903909\\Fase1\\colaImpresoras.png";

        try {
            Process proceso = Runtime.getRuntime().exec(parametros, null);
            proceso.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("errror");
            e.printStackTrace();
        }
    }

    public void pasoImpresora() {
        Imagen imagenColor = null;
        Imagen imagenBN = null;
        boolean yaSeLlamoMetodo = false;
        if (this.colorDisponible) {
            if (this.colaColor.getSize() > 0) {
                imagenColor = (Imagen) this.colaColor.getPrimero().getDato();
                if(!imagenColor.isSePuedeImprimir()){
                    //Con este metodo se obliga a que tenga que pasar un paso antes de que se puedan imprimir
                    //las imagenes
                    pasoParaImprimir();
                    return;
                }
                pasoParaImprimir();
                yaSeLlamoMetodo = true;
                this.colorDisponible = false;
            }

        } else {
            pasoParaImprimir();
            yaSeLlamoMetodo = true;
            this.colorDisponible = true;
            imagenColor = (Imagen) colaColor.getPrimero().getDato();
            imagenColor.setPasos(1);
            imagenColor.getCliente().setImg_color(imagenColor.getCliente().getImg_color()+1);
//            imagenColor.getCliente().setPasos(imagenColor.getCliente().getPasos()+1);
            System.out.println("Se imprimio una imagen a color del cliente " + imagenColor.getCliente().getNombre_cliente());
            Nodo aux = this.clientesEnEspera.getPrimero();
            //Se recorre la lsiat de clientes en espera para saber a quien corresponde la imagen
            while (aux!=null){
                ListaSimple listaClienteImagenes = (ListaSimple) aux.getDato();
                Cliente cliente = (Cliente) listaClienteImagenes.getPrimero().getDato();
                if (cliente == imagenColor.getCliente()){
                    listaClienteImagenes.insertarFinal(imagenColor);
                    break;
                }
                aux = aux.getSiguiente();
            }
            colaColor.elimiinarInicio();
        }

        if(this.colaBN.getSize()>0){
            imagenBN = (Imagen) colaBN.getPrimero().getDato();
            if(!imagenBN.isSePuedeImprimir()){
                pasoParaImprimir();
                return;
            }
            if(!yaSeLlamoMetodo){
                pasoParaImprimir();
            }
            Nodo aux = this.clientesEnEspera.getPrimero();
            imagenBN.getCliente().setImg_bw( imagenBN.getCliente().getImg_bw()+1 );
//            imagenBN.getCliente().setPasos(imagenBN.getCliente().getPasos()+1);
            System.out.println("Se imprimio una imagen BN del cliente "+imagenBN.getCliente().getNombre_cliente());
            //Se recorre la lsiat de clientes en espera para saber a quien corresponde la imagen
            while (aux!=null){
                ListaSimple listaClienteImagenes = (ListaSimple) aux.getDato();
                Cliente cliente = (Cliente) listaClienteImagenes.getPrimero().getDato();
                //ultimo que se trabajó, hacer metodo para graficar clientes en espera
                if (cliente == imagenBN.getCliente()){
                    listaClienteImagenes.insertarFinal(imagenBN);
                    break;
                }
                aux = aux.getSiguiente();
            }

            colaBN.elimiinarInicio();
        }
        if(imagenColor!=null){
            if(imagenColor.getCliente().getImg_color() == imagenColor.getCliente().getImgColorConstante() &&
                    imagenColor.getCliente().getImg_bw() == imagenColor.getCliente().getImgBNConstante()){
                imagenColor.getCliente().setTieneTodasSusImgs(true);
//                System.out.println("El cliente "+imagenColor.getCliente().getNombre_cliente()+" recibio todas sus imagenes");
                return;
            }
        }

        if(imagenBN!=null){
            if(imagenBN.getCliente().getImg_color() == imagenBN.getCliente().getImgColorConstante() &&
                    imagenBN.getCliente().getImg_bw() == imagenBN.getCliente().getImgBNConstante()){
                imagenBN.getCliente().setTieneTodasSusImgs(true);
//                System.out.println("El cliente "+imagenBN.getCliente().getNombre_cliente()+" recibio todas sus imagenes");
                return;
            }
        }

    }


    public void pasoParaImprimir(){
        Nodo aux = this.colaColor.getPrimero();
        Imagen imagen;
        while (aux!=null){
            imagen = (Imagen) aux.getDato();
            imagen.setSePuedeImprimir(true);
            aux=aux.getSiguiente();
        }

        aux = this.colaBN.getPrimero();
        while (aux!=null){
            imagen = (Imagen) aux.getDato();
            imagen.setSePuedeImprimir(true);
            aux=aux.getSiguiente();
        }
    }

    public void verClientesEnEspera(){
        try{
        String ruta = "clientesEnEspera.dot";
        StringBuilder nodos = new StringBuilder();
        StringBuilder conectarNodos = new StringBuilder();
        StringBuilder rank = new StringBuilder();
        nodos.append("digraph ClientesEnEspera {\nnode[shape=box]\nedge[arrowhead=none]\n");
        Nodo aux = this.clientesEnEspera.getPrimero();
        int id = 0;
        int idAnterior = id;
        int idClienteAnterior = id;
        boolean esPrimerCliente = true;
        while (aux!=null){
            ListaSimple listaClientesImagenes = (ListaSimple) aux.getDato();
            Nodo aux2 = listaClientesImagenes.getPrimero();
            //Creamos el nodo del cliente
            Cliente cliente = (Cliente) aux2.getDato();
            nodos.append(String.format("%d [label=\"%s\\nIMG C %d\\nIMG BN %d\"]\n", id,
                    cliente.getNombre_cliente(), cliente.getImgColorConstante(),
                    cliente.getImgBNConstante()
            ));

            if(!esPrimerCliente){
                conectarNodos.append(idClienteAnterior+"->"+id+"\n");
                rank.append(String.format("{rank=same; %d; %d}\n", idClienteAnterior, id));
            }else {
                esPrimerCliente=false;
            }
            idClienteAnterior = id;
            idAnterior = id;
            id++;
            //Aqui empiezan las imagenes
            aux2 = aux2.getSiguiente();
            while (aux2!=null){
                Imagen imagen = (Imagen) aux2.getDato();
                nodos.append(String.format("%d [label=%s]\n", id, imagen.getTipo()));
                conectarNodos.append(idAnterior+"->"+id+"\n");
                idAnterior=id;
                id++;
                aux2=aux2.getSiguiente();
            }
            aux = aux.getSiguiente();
        }


        nodos.append(conectarNodos);
        nodos.append(rank);
        nodos.append("rankdir=TB\n}");

        File file = new File(ruta);
        // Si el archivo no existe es creado
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(nodos.toString());
        bw.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    String parametros[] = new String[5];
    parametros[0] = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
    parametros[1] = "-Tpng";
    parametros[2] = "C:\\Users\\diego\\Desktop\\USAC\\EstructurasDeDatos\\EDD_UDRAWING_FASE_201903909\\Fase1\\clientesEnEspera.dot";
    parametros[3] = "-o";
    parametros[4] = "C:\\Users\\diego\\Desktop\\USAC\\EstructurasDeDatos\\EDD_UDRAWING_FASE_201903909\\Fase1\\clientesEnEspera.png";

        try {
        Process proceso = Runtime.getRuntime().exec(parametros, null);
        proceso.waitFor();
    } catch (IOException | InterruptedException e) {
        System.out.println("errror");
        e.printStackTrace();
    }

}

    public void topClientesImgColor(){
        ListaSimple topImgColor = new ListaSimple();
        try {
            topImgColor = (ListaSimple) this.colaRecepcion.clone();
            Nodo aux = topImgColor.getPrimero();
            while (aux!=null){
                Cliente cliente = (Cliente) aux.getDato();
                Nodo aux2 = topImgColor.getPrimero();
                Nodo aux3 = topImgColor.getPrimero().getSiguiente();
                while (aux3!=null){
                    Cliente cliente1 = (Cliente) aux2.getDato();
                    cliente = (Cliente) aux3.getDato();
                    if(cliente.getImgColorConstante()>cliente1.getImgColorConstante()){
                        Cliente tmp = cliente;
                        aux3.setDato(cliente1);
                        aux2.setDato(tmp);
                    }
                    aux2 = aux2.getSiguiente();
                    aux3 = aux3.getSiguiente();
                }
                aux = aux.getSiguiente();
            }
            System.out.println("-".repeat(10)+"TOP 5 CLIENTES CON MAS IMAGENES A COLOR"+"-".repeat(10));
            int i = 0;
            aux = topImgColor.getPrimero();
            while (i<=4 && aux!=null){
                Cliente cliente = (Cliente) aux.getDato();
                System.out.println(cliente.getNombre_cliente()+" "+cliente.getImgColorConstante()+" imagenes a color");
                aux = aux.getSiguiente();
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public void topClientesMenosImgBN(){
        ListaSimple topBN = new ListaSimple();
        try {
            topBN = (ListaSimple) this.colaRecepcion.clone();
            Nodo aux = topBN.getPrimero();
            while (aux!=null){
                Cliente cliente = (Cliente) aux.getDato();
                Nodo aux2 = topBN.getPrimero();
                Nodo aux3 = topBN.getPrimero().getSiguiente();
                while (aux3!=null){
                    Cliente cliente1 = (Cliente) aux2.getDato();
                    cliente = (Cliente) aux3.getDato();
                    if(cliente.getImgBNConstante()<cliente1.getImgBNConstante()){
                        Cliente tmp = cliente;
                        aux3.setDato(cliente1);
                        aux2.setDato(tmp);
                    }
                    aux2 = aux2.getSiguiente();
                    aux3 = aux3.getSiguiente();
                }
                aux = aux.getSiguiente();
            }
            System.out.println("-".repeat(10)+"TOP 5 CLIENTES CON MENOS IMAGENES BN"+"-".repeat(10));
            int i = 0;
            aux = topBN.getPrimero();
            while (i<=4 && aux!=null){
                Cliente cliente = (Cliente) aux.getDato();
                System.out.println(cliente.getNombre_cliente()+" "+cliente.getImgBNConstante()+" imagenes BN");
                aux = aux.getSiguiente();
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    public void listas() {
        ListaSimple lista = new ListaSimple();
        lista.insertarFinal("jajaj");
        lista.insertarFinal("ssx");
        lista.insertarFinal("xsx");
        lista.insertarFinal("ddd");
//        System.out.println(lista.primero.getDato());
//        lista.recorrer();

    }
}
