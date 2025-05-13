package main;
import java.util.Scanner;

import core.MatrizDispersaEnlazada;

public class main { // Nombre de clase usualmente con Mayúscula inicial

    public static void main(String[] args) { // El método main
        Scanner scanner = new Scanner(System.in);

        // Matriz A de 2x2: A = [[1, 2], [3, 0]]
        int dimension = 2;
        MatrizDispersaEnlazada matrizA = new MatrizDispersaEnlazada(dimension, dimension);
        System.out.println("Creando Matriz A de " + dimension + "x" + dimension + " (ordenada):");
        matrizA.insertar(0, 0, 1); 
        matrizA.insertar(0, 1, 2); 
        matrizA.insertar(1, 0, 3);
        // (0,0,1) -> (0,1,2) -> (1,0,3)
        // matrizA.insertar(1, 1, 0); // No se insertaría o eliminaría si existiera
        System.out.print("Matriz A original: ");
        matrizA.imprimir();
        System.out.print("Ingrese el exponente entero (x) para calcular A^x (ej. 0, 1, 2, 3,...): ");
        int exponente;
        if (scanner.hasNextInt()) {
            exponente = scanner.nextInt();
        } else {
            System.out.println("Entrada inválida para el exponente. Usando exponente 2 por defecto.");
            exponente = 2;
            if(scanner.hasNext()) scanner.next(); 
        }

        if (exponente < 0) {
            System.out.println("El cálculo de potencia para exponentes negativos no está implementado aquí.");
        } else {
            System.out.println("\nCalculando A^" + exponente + " ...");
            MatrizDispersaEnlazada resultadoPotencia = matrizA.potencia(exponente);

            if (resultadoPotencia != null) {
                System.out.print("Matriz Resultado (A^" + exponente + "): ");
                resultadoPotencia.imprimir();
            } else {
                System.out.println("No se pudo calcular la potencia de la matriz.");
            }
        }
        System.out.println("--------------------------------------------------");
        
        scanner.close();
    }
}
