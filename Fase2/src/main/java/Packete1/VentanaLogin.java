package Packete1;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class VentanaLogin extends JFrame implements ActionListener {
    Font fuenteLabel = new Font("Impact", Font.BOLD, 40);
    Font fuenteTextBox = new Font("Corbel", Font.BOLD, 25);

    private JButton iniciar;
    private JButton cancelar;
    private JTextField textFieldUser;
    private JTextField textFieldPassword;
    public VentanaLogin() {
         //Propiedades generales de la ventana
         this.setSize(1200, 850);
         this.setLocationRelativeTo(null);
         this.setTitle("Crear Usuario");
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
         this.setLayout(null);
    }

    public void crearLabel(){
        JLabel login = new JLabel("LOGIN");
        login.setBounds(550, 50, 200, 40);
        login.setFont(fuenteLabel);

        short posx=300;
        short posy=200;
        short sizeX = 150;
        short sizeY=35;

        JLabel user = new JLabel("Usuario");
        user.setBounds(posx, posy, sizeX, sizeY);
        user.setFont(fuenteTextBox);

        posy+=100;
        JLabel password = new JLabel("Password");
        password.setBounds(posx-20, posy, sizeX, sizeY);
        password.setFont(fuenteTextBox);

        this.add(password);
        this.add(user);
        this.add(login);
    }

    public void crearTextBox(){
        short posx=400;
        short posy=200;
        short sizeX = 200;
        short sizeY=35;
        this.textFieldUser = new JTextField();
        this.textFieldUser.setBounds(posx, posy, sizeX, sizeY);
        this.textFieldUser.setFont(fuenteTextBox);
        posy+=100;
        this.textFieldPassword = new JTextField();
        this.textFieldPassword.setBounds(posx, posy, sizeX, sizeY);
        this.textFieldPassword.setFont(fuenteTextBox);
        this.add(this.textFieldUser);
        this.add(this.textFieldPassword);

    }

    public void crearBotones(){
        short posx=400;
        short posy=400;
        short sizeX = 200;
        short sizeY=35;
        this.iniciar = new JButton("Iniciar Sesion");
        this.iniciar.setBounds(posx ,posy, sizeX, sizeY);
        this.iniciar.setFont(fuenteTextBox);
        this.iniciar.setForeground(Color.WHITE);
        this.iniciar.setBackground(Color.BLUE);;
        this.add(iniciar);

        posx+=250;

        this.cancelar = new JButton("Cancelar");
        this.cancelar.setBounds(posx ,posy, sizeX, sizeY);
        this.cancelar.setFont(fuenteTextBox);
        this.cancelar.setForeground(Color.WHITE);
        this.cancelar.setBackground(Color.RED);;
        this.add(cancelar);

        this.cancelar.addActionListener(this);
        this.iniciar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.iniciar){
            System.out.println("PRESIONADO INICIAR");
        }

        if(e.getSource()==this.cancelar){
            System.out.println("PRESIONADO CANCELAR");
        }
    }
    

    
}
