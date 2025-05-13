/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package punto3;

import java.util.Scanner;
import java.util.Stack;


public class BalanceadorParentesis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leer expresión
        System.out.print("Ingrese la expresión: ");
        String expresion = scanner.nextLine();

        // Verificar balance
        if (estaBalanceada(expresion)) {
            System.out.println("Balanceada");
        } else {
            System.out.println("No Balanceada");
        }

        scanner.close();
    }

    public static boolean estaBalanceada(String expresion) {
        Stack<Character> pila = new Stack<>();

        for (char c : expresion.toCharArray()) {
            if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                if (pila.isEmpty()) {
                    return false; // Cierre sin apertura correspondiente
                }
                pila.pop(); // Quitar la apertura correspondiente
            
            }
                        if (c == '{') {
                pila.push(c);
            } else if (c == '}') {
                if (pila.isEmpty()) {
                    return false; // Cierre sin apertura correspondiente
                }
                pila.pop(); // Quitar la apertura correspondiente
            
            }  if (c == '[') {
                pila.push(c);
            } else if (c == ']') {
                if (pila.isEmpty()) {
                    return false; // Cierre sin apertura correspondiente
                }
                pila.pop(); // Quitar la apertura correspondiente
            
            }
        }

        return pila.isEmpty(); // Si está vacía, todos los paréntesis se cerraron correctamente
    }
}

    
    

