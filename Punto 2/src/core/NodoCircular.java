/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author USUARIO
 */
public class NodoCircular {
    public int dato;
    public NodoCircular sig;

    public NodoCircular(int dato) {
        this.dato = dato;
        this.sig = this; // por defecto se apunta a sí mismo
    }
}
