/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import model.*;

/**
 *
 * @author Usuario
 */
public class Controlador {
    private GestorDeArchivos gestor;
    private Llave<Character> llave;
    
    public Controlador() {
        this.llave = new Llave<Character>();
    }
    
    public void seleccionarArchivo(String ruta) {
        this.gestor = new GestorDeArchivos(ruta);
    }
    
    public void encriptar() throws IOException {
        this.gestor.leer();
        System.out.println("Termino de leer");
        Lista<Integer> archivo = this.gestor.getArchivo();
        Nodo<Character> aux = llave.getInicio();
        int valor = 0;
        Nodo<Integer> nAux = archivo.getInicio();
        while (nAux != null) {
            valor = nAux.getDato()+(int)aux.getDato();
            if(valor>255){
                valor -= 255;
            }
            nAux.setDato(valor);
            aux = aux.getSiguiente();
            nAux = nAux.getSiguiente();
        }
        System.out.println("Termino de escribir");
        this.gestor.escribir();
    }
    
    public void desencriptar() throws IOException {
        this.gestor.leer();
        Lista<Integer> archivo = this.gestor.getArchivo();
        Nodo<Character> aux = llave.getInicio();
        int valor = 0;
        Nodo<Integer> nAux = archivo.getInicio();
        while (nAux != null) {
            valor = nAux.getDato()-(int)aux.getDato();
            if(valor<0){
                valor += 255;
            }
            nAux.setDato(valor);
            aux = aux.getSiguiente();
            nAux = nAux.getSiguiente();
        }
        this.gestor.escribir();
    }

    public void setLlave(Llave<Character> llave) {
        this.llave = llave;
    }
    
    public void setLlave(String llave) {
        for (int i = 0; i < llave.length(); i++) {
            this.llave.insertar(llave.charAt(i));
        }
    }

    public GestorDeArchivos getGestor() {
        return gestor;
    }

    public Llave<Character> getLlave() {
        return llave;
    }
    
    
}