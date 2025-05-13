import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrizDispersaEnlazada {
    NodoMatriz cabeza;
    int filasOriginales;
    int columnasOriginales;

    public MatrizDispersaEnlazada(int m, int n) {
        this.cabeza = null;
        this.filasOriginales = m;
        this.columnasOriginales = n;
    }

    // Inserta o actualiza un valor en la matriz, manteniendo la lista ordenada.
    // Si el valor es 0, el nodo (si existe) se elimina.
    public void insertar(int fila, int columna, int valor) {
        if (fila >= filasOriginales || columna >= columnasOriginales || fila < 0 || columna < 0) {
            System.out.println("Error: Elemento (" + fila + "," + columna + ") fuera de las dimensiones (" + filasOriginales + "x" + columnasOriginales + ") de la matriz.");
            return;
        }

        NodoMatriz actual = this.cabeza;
        NodoMatriz anterior = null;

        // Navegar hasta la posición correcta o encontrar el nodo existente
        while (actual != null && (actual.fila < fila || (actual.fila == fila && actual.columna < columna))) {
            anterior = actual;
            actual = actual.siguiente;
        }

        // Caso 1: El nodo ya existe en (fila, columna)
        if (actual != null && actual.fila == fila && actual.columna == columna) {
            if (valor == 0) { // Si el nuevo valor es 0, eliminar el nodo
                if (anterior == null) { // El nodo a eliminar es la cabeza
                    this.cabeza = actual.siguiente;
                } else { // El nodo a eliminar está en medio o al final
                    anterior.siguiente = actual.siguiente;
                }
            } else { // Actualizar el valor del nodo existente
                actual.valor = valor;
            }
        } else { // Caso 2: El nodo no existe, y el valor a insertar no es 0
            if (valor != 0) {
                NodoMatriz nuevoNodo = new NodoMatriz(fila, columna, valor);
                if (anterior == null) { // Insertar en la cabeza
                    nuevoNodo.siguiente = this.cabeza;
                    this.cabeza = nuevoNodo;
                } else { // Insertar después del nodo 'anterior'
                    nuevoNodo.siguiente = actual; // 'actual' es el nodo que va después del nuevo, o null
                    anterior.siguiente = nuevoNodo;
                }
            }
        }
    }

    // Método auxiliar para la multiplicación: inserta un valor o suma a un nodo existente.
    // Mantiene la lista ordenada. Si el valor resultante es 0, elimina el nodo.
    private void insertarOActualizarSuma(int fila, int columna, int valorASumar) {
        // No hacer nada si el valor a sumar es 0 y el nodo no existe (no crearía un nuevo nodo con 0)
        // Pero si el nodo existe, un valorASumar de 0 no cambia nada, así que podemos simplemente retornar.
        // Sin embargo, el chequeo principal es si la suma total da 0.

        NodoMatriz actual = this.cabeza;
        NodoMatriz anterior = null;

        // Navegar hasta la posición correcta o encontrar el nodo existente
        while (actual != null && (actual.fila < fila || (actual.fila == fila && actual.columna < columna))) {
            anterior = actual;
            actual = actual.siguiente;
        }

        // Caso 1: El nodo ya existe en (fila, columna)
        if (actual != null && actual.fila == fila && actual.columna == columna) {
            actual.valor += valorASumar; // Sumar al valor existente
            if (actual.valor == 0) { // Si el nuevo valor es 0, eliminar el nodo
                if (anterior == null) { // El nodo a eliminar es la cabeza
                    this.cabeza = actual.siguiente;
                } else { // El nodo a eliminar está en medio o al final
                    anterior.siguiente = actual.siguiente;
                }
            }
        } else { // Caso 2: El nodo no existe, insertar nuevo nodo si valorASumar no es 0
            if (valorASumar != 0) { // Solo insertar si el valor a sumar (que será el valor inicial del nodo) no es 0
                NodoMatriz nuevoNodo = new NodoMatriz(fila, columna, valorASumar);
                if (anterior == null) { // Insertar en la cabeza
                    nuevoNodo.siguiente = this.cabeza;
                    this.cabeza = nuevoNodo;
                } else { // Insertar después del nodo 'anterior'
                    nuevoNodo.siguiente = actual;
                    anterior.siguiente = nuevoNodo;
                }
            }
        }
    }


    public void imprimir() {
        if (cabeza == null) {
            System.out.println("Matriz vacía (NULL)");
            return;
        }
        NodoMatriz actual = cabeza;
        StringBuilder sb = new StringBuilder();
        while (actual != null) {
            sb.append(actual.toString());
            actual = actual.siguiente;
            if (actual != null) {
                sb.append(" -> ");
            }
        }
        sb.append(" -> NULL");
        System.out.println(sb.toString());
    }

    public MatrizDispersaEnlazada multiplicar(MatrizDispersaEnlazada otraMatriz) {
        if (this.columnasOriginales != otraMatriz.filasOriginales) {
            System.out.println("Error: Dimensiones incompatibles para la multiplicación.");
            return null;
        }

        int m_res = this.filasOriginales;
        int n_res = otraMatriz.columnasOriginales;
        MatrizDispersaEnlazada resultado = new MatrizDispersaEnlazada(m_res, n_res); // Matriz resultado inicialmente vacía

        NodoMatriz nodoA = this.cabeza;
        while (nodoA != null) { // Itera sobre A_ik (nodoA.fila, nodoA.columna, nodoA.valor)
            NodoMatriz nodoB = otraMatriz.cabeza;
            while (nodoB != null) { // Itera sobre B_kj (nodoB.fila, nodoB.columna, nodoB.valor)
                if (nodoA.columna == nodoB.fila) { // Si k (columna de A) == k (fila de B)
                    int filaRes = nodoA.fila;         // i
                    int colRes = nodoB.columna;       // j
                    int valorContribucion = nodoA.valor * nodoB.valor;

                    if (valorContribucion != 0) { // Solo procesar si la contribución no es cero
                        resultado.insertarOActualizarSuma(filaRes, colRes, valorContribucion);
                    }
                }
                nodoB = nodoB.siguiente;
            }
            nodoA = nodoA.siguiente;
        }
        return resultado;
    }

    public MatrizDispersaEnlazada potencia(int exponente) {
        if (this.filasOriginales != this.columnasOriginales) {
            System.out.println("Error: La potencia solo se puede calcular para matrices cuadradas.");
            return null;
        }
        if (exponente < 0) {
            System.out.println("Error: Exponente negativo no soportado.");
            return null;
        }

        int dim = this.filasOriginales;

        if (exponente == 0) {
            MatrizDispersaEnlazada identidad = new MatrizDispersaEnlazada(dim, dim);
            for (int i = 0; i < dim; i++) {
                identidad.insertar(i, i, 1);
            }
            return identidad;
        }

        // Crear una copia de la matriz base para las operaciones
        MatrizDispersaEnlazada matrizBaseCopia = new MatrizDispersaEnlazada(dim, dim);
        NodoMatriz nodoActualOriginal = this.cabeza;
        while (nodoActualOriginal != null) {
            matrizBaseCopia.insertar(nodoActualOriginal.fila, nodoActualOriginal.columna, nodoActualOriginal.valor);
            nodoActualOriginal = nodoActualOriginal.siguiente;
        }

        if (exponente == 1) {
            return matrizBaseCopia;
        }

        MatrizDispersaEnlazada resultadoParcial = matrizBaseCopia; // A^1

        for (int i = 2; i <= exponente; i++) {
            resultadoParcial = resultadoParcial.multiplicar(matrizBaseCopia);
            if (resultadoParcial == null) {
                System.out.println("Error durante la multiplicación en el paso " + i + " de la potencia.");
                return null;
            }
        }
        return resultadoParcial;
}
}