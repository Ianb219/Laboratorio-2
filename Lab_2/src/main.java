public class main {
    public static void main(String[] args) throws Exception {
         // Datos del ejemplo: A es una matriz de 3x4 (m=3, n=4)
        // (0,1,-2); (0,2,1); (1,0,3); (2,2,-6); (2,3,-4) -> NULL

        // Para determinar m y n, observamos los índices máximos:
        // Max fila_idx = 2 => m = 3 filas (0, 1, 2)
        // Max col_idx = 3 => n = 4 columnas (0, 1, 2, 3)
        int m = 3; // Número de filas de A
        int n = 4; // Número de columnas de A

        Crear_Lista matrizA = new Crear_Lista(m, n);
        System.out.println("Insertando elementos en la Matriz A (" + m + "x" + n + "):");

        matrizA.insertar(0, 1, -2);
        matrizA.insertar(0, 2, 1);
        matrizA.insertar(1, 0, 3);
        matrizA.insertar(2, 2, -6);
        matrizA.insertar(2, 3, -4);
        //matrizA.insertar(0, 0, 0); // Ejemplo de inserción de cero (será ignorado)
        //matrizA.insertar(3, 0, 5); // Ejemplo de elemento fuera de rango (mostrará error y no insertará si System.exit no está activo)


        System.out.print("Matriz A representada como lista enlazada: ");
        matrizA.imprimir();
        System.out.println("--------------------------------------------------");

        System.out.println("Calculando A^T * A ...");
        Crear_Lista resultadoAtA = matrizA.multiplicarPorTranspuesta();

        System.out.print("Matriz Resultado (A^T * A) representada como lista enlazada: ");
        resultadoAtA.imprimir();

        System.out.println("--------------------------------------------------");
        System.out.println("Verificación con el ejemplo del documento:");
        System.out.println("Matriz A (ejemplo visual):");
        System.out.println("   c0 c1 c2 c3");
        System.out.println("r0 [ 0 -2  1  0]");
        System.out.println("r1 [ 3  0  0  0]");
        System.out.println("r2 [ 0  0 -6 -4]");
        System.out.println("\nMatriz A^T * A esperada (ejemplo visual):");
        System.out.println("   c0 c1 c2 c3");
        System.out.println("r0 [ 9  0  0  0]");
        System.out.println("r1 [ 0  4 -2  0]");
        System.out.println("r2 [-2 -2 37 24]");
        System.out.println("r3 [ 0  0 24 16]");

        System.out.println("\nSalida del programa (A^T * A):");
        System.out.println("Dimensiones: " + resultadoAtA.filasOriginales + "x" + resultadoAtA.columnasOriginales);
        resultadoAtA.imprimir();
        // Salida esperada (puede variar el orden de los nodos si la inserción no es ordenada,
        // pero con la ordenación de claves del HashMap debería coincidir):
        // (0,0,9) -> (1,1,4) -> (1,2,-2) -> (2,1,-2) -> (2,2,37) -> (2,3,24) -> (3,2,24) -> (3,3,16) -> NULL
        // Nota: el ejemplo en el papel A^T*A tiene (2,1,-2) y (1,2,-2) que son simétricos.
        // Mi cálculo de A^T*A debería producirlos.
              
       

    }
}
