/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Usuario
 */
public class Llave<T> {
    private Nodo<T> inicio;
    
    public void insertar(T dato) {
        Nodo<T> nuevo = new Nodo(dato, true);
        if (this.inicio == null) {
            this.inicio = nuevo;
        } else {
            Nodo<T> aux = inicio;
            while (aux.getSiguiente() != this.inicio) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
            nuevo.setSiguiente(this.inicio);
        }
    }

    public Nodo<T> getInicio() {
        return inicio;
    }
    
    @Override
    public String toString() {
        Nodo<T> aux = this.inicio;
        String resultado = "";
        while(aux.getSiguiente() != this.inicio) {
            resultado += String.valueOf(aux.getDato());
            aux = aux.getSiguiente();
        }
        resultado += String.valueOf(aux.getDato());
        return resultado;
    }
}
