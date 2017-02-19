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
public class Lista<T extends Comparable<T>> {
    private Nodo<T> inicio;
    
    public Lista(){
        this.inicio = null;
    }
    
    public void insertarInicio(Comparable<T> e) {
        Nodo nuevo = new Nodo(e, false);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
    }
    
    public void insertarFinal(Comparable<T> e) {
        Nodo nuevo = new Nodo(e, false);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo aux = inicio;
            while(aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
    }
    
    public void imprimir(){
        Nodo aux = inicio;
        while (aux != null) {
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }
    
    public void insertarOrdenado(Comparable<T> e) {
        Nodo<T> nuevo = new Nodo(e, false);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo<T> aux = inicio;
            if (aux.getDato().compareTo(nuevo.getDato())>0){
                nuevo.setSiguiente(inicio);
                inicio = nuevo;
            } else {
                Nodo anterior = null;
                while(aux != null && nuevo.getDato().compareTo(aux.getDato())>0) {
                    anterior = aux;
                    aux = aux.getSiguiente();
                }
                nuevo.setSiguiente(aux);
                anterior.setSiguiente(nuevo);
            }
        }
    }

    public Nodo<T> getInicio() {
        return inicio;
    }
    
    
}
