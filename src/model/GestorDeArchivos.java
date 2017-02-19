/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.*;

/**
 *
 * @author Usuario
 */
public class GestorDeArchivos {
    
    private FileInputStream iStream;
    private FileOutputStream oStream;
    private Lista<Integer> archivo;
    private File iArchivo;
    
    public GestorDeArchivos(String ruta) {
        this.iArchivo = new File(ruta);
        this.archivo = new Lista();
    }
    
    public void leer() throws FileNotFoundException, IOException{
        this.iStream = new FileInputStream(iArchivo);
        while(iStream.available()>0) {
            this.archivo.insertarFinal(iStream.read());
        }
        this.iStream.close();
    }
    
    public void escribir() throws FileNotFoundException, IOException {
        String nombre = this.iArchivo.getAbsolutePath();
        this.iArchivo.delete();
        this.iArchivo = new File(nombre);
        this.oStream = new FileOutputStream(iArchivo);
        Nodo<Integer> aux = this.archivo.getInicio();
        while(aux != null){
            Integer b = aux.getDato();
            this.oStream.write(b);
            aux = aux.getSiguiente();
        }
        this.oStream.close();
    }
    
    public boolean compararArchivos(File file) throws IOException {
        int a = 0;
        int b = 0;
        boolean flag = true;
        Llave<Character> llave = new Llave();
        llave.insertar('K');
        llave.insertar('E');
        llave.insertar('Y');
        Nodo<Character> aux = llave.getInicio();
        FileInputStream nStream = new FileInputStream(file);
        this.iStream = new FileInputStream(iArchivo);
        char c = 0;
        int d = 0;
        int e = 0;
        int f =0;
        while(iStream.available()>0 && nStream.available()>0) {
            a = this.iStream.read();
            b = nStream.read();
            c = aux.getDato();
            d = (int)aux.getDato();
            e = b+d;
            f = e-d;
            System.out.println(String.format("%d   %d   %s   %d   %d   %d", a,b,c,d,e,f));
            if( a == b) {
                flag = true;
            } else {
                flag = false;
            }
            aux = aux.getSiguiente();
        }
        iStream.close();
        nStream.close();
        return flag;
    }

    public Lista<Integer> getArchivo() {
        return archivo;
    }

    public File getiArchivo() {
        return iArchivo;
    }
    
    
}
