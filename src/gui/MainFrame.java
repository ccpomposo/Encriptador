/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.Controlador;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author Usuario
 */
public class MainFrame extends JFrame {
    
    private Controlador controlador;
    private JButton btnChoose;
    private JButton btnSolve;
    private JButton btnDesen;
    private JPasswordField pssLlave;
    private JButton btnAceptar;
    private JLabel lblArchivo;
    
    public MainFrame() {
        super("Encriptador v1.0");
        this.controlador = new Controlador();
        super.setSize(500, 200);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        
        super.add(this.crearPanelBotones(),BorderLayout.SOUTH);
        super.add(this.crearPanelTrabajo(),BorderLayout.CENTER);
        super.setVisible(true);
    }
    
    private JPanel crearPanelTrabajo() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        this.pssLlave = new JPasswordField(20);
        this.btnAceptar = new JButton("Aceptar");
        this.lblArchivo = new JLabel("No se ha seleccionado ningún archivo");
        
        this.btnAceptar.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String pass = new String(MainFrame.this.pssLlave.getPassword());
                        MainFrame.this.controlador.setLlave(pass);
                        MainFrame.this.btnChoose.setEnabled(true);
                    }
                }).start();
            }
        });
        panel.add(new JLabel("Contraseña: "));
        panel.add(this.pssLlave);
        panel.add(this.btnAceptar);
        panel.add(this.lblArchivo);
        return panel;
    }
    
    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel();
        this.btnChoose = new JButton("Seleccionar archivo");
        this.btnSolve = new JButton("Encriptar");
        this.btnDesen = new JButton("Desencriptar");
        JFileChooser fileChooser = new JFileChooser();
        panel.setLayout(new FlowLayout());
        btnChoose.setEnabled(false);
        btnSolve.setEnabled(false);
        btnDesen.setEnabled(false);
        btnChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int returnVal = fileChooser.showOpenDialog(panel);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                        controlador.seleccionarArchivo(fileChooser.getSelectedFile().getAbsolutePath());
                        lblArchivo.setText("Archivo: "+fileChooser.getSelectedFile().getAbsolutePath());
                        btnSolve.setEnabled(true);
                        btnDesen.setEnabled(true);
                    }
            }
            }
        );
        btnSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    controlador.encriptar();
                    JOptionPane.showMessageDialog(MainFrame.this,String.format("El archivo se genero con exito en %s",controlador.getGestor().getiArchivo().getPath()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,"Ocurrio un error al encriptar el archivo");
                }
            }
        });
        
        btnDesen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    controlador.desencriptar();
                    JOptionPane.showMessageDialog(MainFrame.this,String.format("El archivo se genero con exito en %s",controlador.getGestor().getiArchivo().getPath()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,"Ocurrio un error al desencriptar el archivo");
                }
            }
        });
        
        panel.add(btnChoose);
        panel.add(btnSolve);
        panel.add(btnDesen);
        
        return panel;
    }
}

class Test {
    public static void main(String[] args) {
        new MainFrame();
    }      
}

