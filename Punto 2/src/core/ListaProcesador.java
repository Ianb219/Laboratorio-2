
package core;

public class ListaProcesador {
    public static void procesarListas(NodoDoble[] ptr1, NodoCircular[] ptr2) {
        if (ptr2[0] == null) return;

        NodoCircular actual = ptr2[0];
        NodoCircular inicio = ptr2[0];

        do {
            NodoCircular siguiente = actual.sig;
            int valor = actual.dato;

            if (buscarEnListaDoble(ptr1[0], valor)) {
                ptr1[0] = eliminarDeListaDoble(ptr1[0], valor);
                ptr2[0] = eliminarDeListaCircular(ptr2[0], actual);
            } else {
                ptr1[0] = insertarOrdenadoEnListaDoble(ptr1[0], valor);
                ptr2[0] = eliminarDeListaCircular(ptr2[0], actual);
            }

            actual = siguiente;

        } while (ptr2[0] != null && actual != inicio);

        ptr2[0] = null; // destruir PTR2
        
    }
    
    public static boolean buscarEnListaDoble(NodoDoble ptr, int valor){
        while (ptr != null) {
            if (ptr.dato == valor) 
                return true;
            if (ptr.dato > valor) 
                break; // ya pasó el posible valor
            ptr = ptr.sig;
        }
        return false;
    }
    
     public static NodoDoble insertarOrdenadoEnListaDoble(NodoDoble ptr, int valor) {
        NodoDoble nuevo = new NodoDoble(valor);

        if (ptr == null || valor < ptr.dato) {
            nuevo.sig = ptr;
            if (ptr != null) ptr.ant = nuevo;
            return nuevo;
        }

        NodoDoble actual = ptr;
        while (actual.sig != null && actual.sig.dato < valor) {
            actual = actual.sig;
        }

        nuevo.sig = actual.sig;
        if (actual.sig != null) actual.sig.ant = nuevo;
        actual.sig = nuevo;
        nuevo.ant = actual;

        return ptr;
    }
     
     public static NodoDoble eliminarDeListaDoble(NodoDoble ptr, int valor) {
        NodoDoble actual = ptr;

        while (actual != null && actual.dato != valor) {
            actual = actual.sig;
        }

        if (actual == null) return ptr; // no encontrado

        if (actual.ant != null) actual.ant.sig = actual.sig;
        else ptr = actual.sig; // era el primero

        if (actual.sig != null) actual.sig.ant = actual.ant;

        return ptr;
    }
     
     public static NodoCircular eliminarDeListaCircular(NodoCircular ptr, NodoCircular nodo) {
        if (ptr == null || nodo == null) return ptr;

        if (ptr == nodo && ptr.sig == ptr) {
            return null; // era el único
        }

        NodoCircular actual = ptr;
        while (actual.sig != nodo) {
            actual = actual.sig;
        }

        actual.sig = nodo.sig;

        if (ptr == nodo) ptr = nodo.sig;

        return ptr;
    }
}

