package Packete1;

public class Main {
    public static String[] users = {"Diego", "Luis"};
    public static void main(String[] args) {
        System.out.println("a");
        VentanaLogin ventanaLogin = new VentanaLogin();
        ventanaLogin.crearLabel();
        ventanaLogin.crearTextBox();       
        ventanaLogin.crearBotones(); 
        ventanaLogin.setVisible(true);
        
    }
    
}
