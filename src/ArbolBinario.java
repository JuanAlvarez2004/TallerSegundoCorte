public class ArbolBinario {
    Nodo raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public boolean buscarRec(Nodo nodo, int valor) {
        // Caso base: Si el nodo es null, el valor no está en el árbol
        if (nodo == null) {
            return false;
        }

        // Si el valor del nodo actual es igual al valor buscado
        if (nodo.valor == valor) {
            return true;
        }

        // Si el valor buscado es menor, busca en el subárbol izquierdo
        if (valor < nodo.valor) {
            return buscarRec(nodo.izquierda, valor);
        } else {
            // Si el valor buscado es mayor, busca en el subárbol derecho
            return buscarRec(nodo.derecha, valor);
        }
    }

    public void eliminar(int valor) {
        if (raiz == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        raiz = eliminarRec(raiz, valor);
    }

    public Nodo eliminarRec(Nodo nodo, int valor) {
        // Caso base: si el nodo es null, no hay nada que eliminar
        if (nodo == null) {
            return null;
        }

        // Buscar el nodo a eliminar
        if (valor < nodo.valor) {
            nodo.izquierda = eliminarRec(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = eliminarRec(nodo.derecha, valor);
        } else {
            // Encontramos el nodo a eliminar

            // Caso 1: Nodo hoja (sin hijos)
            if (nodo.izquierda == null && nodo.derecha == null) {
                return null;
            }

            // Caso 2: Nodo con solo hijo derecho
            if (nodo.izquierda == null) {
                return nodo.derecha;
            }

            // Caso 3: Nodo con solo hijo izquierdo
            if (nodo.derecha == null) {
                return nodo.izquierda;
            }

            // Caso 4: Nodo con dos hijos
            // Encontrar el sucesor (el menor valor en el subárbol derecho)
            Nodo sucesor = encontrarMinimo(nodo.derecha);
            // Copiar el valor del sucesor al nodo actual
            nodo.valor = sucesor.valor;
            // Eliminar el sucesor del subárbol derecho
            nodo.derecha = eliminarRec(nodo.derecha, sucesor.valor);
        }

        return nodo;
    }

    public Nodo encontrarMinimo(Nodo nodo) {
        while (nodo.izquierda != null) {
            nodo = nodo.izquierda;
        }
        return nodo;
    }

    public Nodo darRaiz() {
        return raiz;
    }

    //Recorrer el árbol para mostrar resultados (I,R,D)
    public void inorder(Nodo raiz) {
        if (raiz != null) {
            inorder(raiz.izquierda);
            System.out.print(raiz.valor + ", ");
            inorder(raiz.derecha);
        }
    }

    // Imprimir representación del árbol
    public void impArbol() {
        System.out.println("""
                     101
                    /   \\
                  33     105
                 / \\    /  \\
                9   39  104 144""");
    }

    // Inicializar el árbol con valores iniciales
    public void iniArbol() {
        raiz = new Nodo(101);
        raiz.izquierda = new Nodo(33);
        raiz.derecha = new Nodo(105);
        raiz.izquierda.izquierda = new Nodo(9);
        raiz.izquierda.derecha = new Nodo(39);
        raiz.derecha.izquierda = new Nodo(104);
        raiz.derecha.derecha = new Nodo(144);
    }

    public void reiniciarArbol() {
        this.raiz = null;
        iniArbol();
    }

    public void eliminarArbol() {
        this.raiz = null;
    }


    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        arbol.impArbol();
        arbol.iniArbol();
        System.out.println("Arbol en inorder: ");
        arbol.inorder(arbol.darRaiz());
        System.out.println("\n------------------------------------------");

        System.out.println("\nBuscar - Caso hoja (9)");
        System.out.println("Resultado: " + arbol.buscarRec(arbol.darRaiz(),9));
        System.out.println("\nBuscar - Caso padre (105)");
        System.out.println("Resultado: " + arbol.buscarRec(arbol.darRaiz(),105));
        System.out.println("\nBuscar - Caso raiz (101)");
        System.out.println("Resultado: " + arbol.buscarRec(arbol.darRaiz(),101));
        System.out.println("\nBuscar - Caso nodo inexistente (0)");
        System.out.println("Resultado: " + arbol.buscarRec(arbol.darRaiz(),0));

        System.out.println("------------------------------------------");
        arbol.eliminarArbol();
        System.out.println("Eliminar - Caso árbol vacío");
        arbol.eliminar(0);
        System.out.print("Resultado en inorder: ");
        arbol.inorder(arbol.darRaiz());
        arbol.reiniciarArbol();

        System.out.println();
        System.out.println("\nEliminar - Caso hoja (9)");
        arbol.eliminar(9);
        System.out.print("Resultado en inorder: ");
        arbol.inorder(arbol.darRaiz());
        arbol.reiniciarArbol();

        System.out.println();
        System.out.println("\nEliminar - Caso 2 hijos (105)");
        arbol.eliminar(105);
        System.out.print("Resultado en inorder: ");
        arbol.inorder(arbol.darRaiz());
        arbol.reiniciarArbol();
    }

}
