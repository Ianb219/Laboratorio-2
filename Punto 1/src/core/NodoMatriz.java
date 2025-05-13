package core;
public class NodoMatriz {
    int fila;
    int columna;
    int valor;
    NodoMatriz siguiente;

    public NodoMatriz(int fila, int columna, int valor) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
        this.siguiente = null;
    }

    @Override
    public String toString() {
        return "(" + fila + "," + columna + "," + valor + ")";
    }
}
