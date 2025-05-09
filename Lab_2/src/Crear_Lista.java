public class Crear_Lista {

        NodoMatriz cabeza;
        int filasOriginales; // m para la matriz A
        int columnasOriginales; // n para la matriz A
    
        public Crear_Lista(int m, int n) {
            this.cabeza = null;
            this.filasOriginales = m;
            this.columnasOriginales = n;
        }
    
        
        public void insertar(int fila, int columna, int valor) {
            if (valor == 0) return; // Usualmente las matrices dispersas no almacenan ceros.
            NodoMatriz nuevoNodo = new NodoMatriz(fila, columna, valor);
            if (cabeza == null) {
                cabeza = nuevoNodo;
            } else {
                NodoMatriz actual = cabeza;
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevoNodo;
            }
        }
    
        // Método para imprimir la matriz (opcional, para verificación)
        public void imprimir() {
            NodoMatriz actual = cabeza;
            while (actual != null) {
                System.out.print("(" + actual.fila + "," + actual.columna + "," + actual.valor + ") -> ");
                actual = actual.siguiente;
            }
            System.out.println("NULL");
        }
        public Crear_Lista multiplicarPorTranspuesta() {
            // n es el número de columnas de A, que será la dimensión de A^T*A
            int n = this.columnasOriginales;
            Crear_Lista resultado = new Crear_Lista(n, n);
        
            // Para almacenar temporalmente los valores de la matriz resultado (col1, col2) -> valor
            // Si HashMap no está permitido, este paso es más complejo:
            // Se tendría que construir la lista 'resultado' directamente,
            // buscando si el nodo (c1,c2) ya existe para sumar el valor, o insertándolo.
            java.util.Map<String, Integer> acumulador = new java.util.HashMap<>();
        
            NodoMatriz nodo1 = this.cabeza;
            while (nodo1 != null) {
                NodoMatriz nodo2 = this.cabeza; // Reiniciar para cada nodo1 si la comparación es A_k_c1 * A_k_c2
                // OJO: si queremos A_k_c1 * A_k_c2, nodo2 debería empezar desde nodo1 o this.cabeza,
                // dependiendo de si el orden importa y si se quieren evitar duplicados de cálculo.
                // La lógica correcta es: C_c1c2 = sum_k (A_kc1 * A_kc2)
                // Iteramos por todos los elementos de A para A_kc1
                // Luego por todos los elementos de A para A_kc2
                // Y si sus filas son iguales (k), entonces sumamos su producto.
        
                // Mejor enfoque: iterar sobre los nodos de A para obtener (r, c1, v1)
                // Iterar sobre los nodos de A ANIDADO para obtener (r, c2, v2) (misma fila r)
                nodo1 = this.cabeza;
                while (nodo1 != null) {
                    nodo2 = this.cabeza; // Empezar desde la cabeza para encontrar otros elementos en la misma fila original
                    while (nodo2 != null) {
                        if (nodo1.fila == nodo2.fila) {
                            // Tenemos A[nodo1.fila][nodo1.columna] y A[nodo2.fila][nodo2.columna]
                            // Estos contribuyen a resultado[nodo1.columna][nodo2.columna]
                            int resFila = nodo1.columna;
                            int resCol = nodo2.columna;
                            int valorContribucion = nodo1.valor * nodo2.valor;
        
                            String clave = resFila + "," + resCol;
                            acumulador.put(clave, acumulador.getOrDefault(clave, 0) + valorContribucion);
                        }
                        nodo2 = nodo2.siguiente;
                    }
                    nodo1 = nodo1.siguiente;
                }
            }
        
        
            // Convertir el acumulador a la lista enlazada resultado
            // Es importante ordenar las claves si se quiere una lista ordenada, o definir un orden.
            // Por ejemplo, ordenar por fila y luego por columna.
            java.util.List<String> clavesOrdenadas = new java.util.ArrayList<>(acumulador.keySet());
            java.util.Collections.sort(clavesOrdenadas, (s1, s2) -> {
                String[] p1 = s1.split(",");
                String[] p2 = s2.split(",");
                int r1 = Integer.parseInt(p1[0]);
                int c1 = Integer.parseInt(p1[1]);
                int r2 = Integer.parseInt(p2[0]);
                int c2 = Integer.parseInt(p2[1]);
                if (r1 != r2) return r1 - r2;
                return c1 - c2;
            });
        
            for (String clave : clavesOrdenadas) {
                String[] partes = clave.split(",");
                int filaRes = Integer.parseInt(partes[0]);
                int colRes = Integer.parseInt(partes[1]);
                int valRes = acumulador.get(clave);
                if (valRes != 0) { // Solo insertar no-ceros
                    resultado.insertar(filaRes, colRes, valRes);
                }
            }
            return resultado;
        }
}
