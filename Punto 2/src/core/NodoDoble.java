/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

/**
 *
 * @author USUARIO
 */
public class NodoDoble {
    public int dato;
    public NodoDoble ant;
    public NodoDoble sig;

    public NodoDoble(int dato) {
        this.dato = dato;
        this.ant = null;
        this.sig = null;
    }
}
