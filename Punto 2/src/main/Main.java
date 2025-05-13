/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import core.ListaProcesador;
import core.NodoCircular;
import core.NodoDoble;

/**
 *
 * @author USUARIO
 */
public class Main {
    public static void main(String[] args) {
        NodoDoble[] ptr1 = new NodoDoble[1];
        ptr1[0] = null;
        ptr1[0] = ListaProcesador.insertarOrdenadoEnListaDoble(ptr1[0], 1);
        ptr1[0] = ListaProcesador.insertarOrdenadoEnListaDoble(ptr1[0], 3);
        ptr1[0] = ListaProcesador.insertarOrdenadoEnListaDoble(ptr1[0], 5);

        NodoCircular[] ptr2 = new NodoCircular[1];
        NodoCircular n1 = new NodoCircular(3);
        NodoCircular n2 = new NodoCircular(2);
        NodoCircular n3 = new NodoCircular(6);

        n1.sig = n2; n2.sig = n3; n3.sig = n1;
        ptr2[0] = n1;

        ListaProcesador.procesarListas(ptr1, ptr2);

        // Imprimir PTR1
        NodoDoble actual = ptr1[0];
        System.out.print("PTR1 final: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.sig;
        }
    }
}
