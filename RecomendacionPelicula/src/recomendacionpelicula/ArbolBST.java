package recomendacionpelicula;
/**
 * Descripción: Árbol Binario de Búsqueda ordenado por RATING
 *              Implementamos recursividad en todos sus métodos
 *              - Menor rating → izquierda
 *              - Mayor rating → derecha
 */

import java.util.ArrayList;
import java.util.List;

public class ArbolBST {

    // ATRIBUTO RAÍZ
    private Nodo raiz;

    // CONSTRUCTOR
    public ArbolBST() {
        this.raiz = null; // Árbol vacío al inicio
    }

    // INSERTAR película en el árbol
    public void insertar(Pelicula pelicula) {
        raiz = insertarRecursivo(raiz, pelicula);
    }

    // Método recursivo privado para insertar
    private Nodo insertarRecursivo(Nodo nodoActual, Pelicula pelicula) {
        // CASO BASE: si el nodo está vacío, crear nuevo nodo aquí
        if (nodoActual == null) {
            return new Nodo(pelicula);
        }

        // Si el rating es MENOR → insertar a la IZQUIERDA
        if (pelicula.getRating() < nodoActual.pelicula.getRating()) {
            nodoActual.izquierdo = insertarRecursivo(nodoActual.izquierdo, pelicula);
        }
        // Si el rating es MAYOR → insertar a la DERECHA
        else if (pelicula.getRating() > nodoActual.pelicula.getRating()) {
            nodoActual.derecho = insertarRecursivo(nodoActual.derecho, pelicula);
        }
        // Si el rating es IGUAL → insertar a la derecha igual
        else {
            nodoActual.derecho = insertarRecursivo(nodoActual.derecho, pelicula);
        }

        return nodoActual;
    }

    // BUSCAR película por título (recursivo)
    public Pelicula buscarPorTitulo(String titulo) {
        return buscarRecursivo(raiz, titulo);
    }

    private Pelicula buscarRecursivo(Nodo nodo, String titulo) {
        // CASO BASE: nodo vacío o título encontrado
        if (nodo == null) return null;
        if (nodo.pelicula.getTitulo().equalsIgnoreCase(titulo)) return nodo.pelicula;

        // Buscar en ambos subárboles
        Pelicula encontrada = buscarRecursivo(nodo.izquierdo, titulo);
        if (encontrada != null) return encontrada;
        return buscarRecursivo(nodo.derecho, titulo);
    }

    // RECORRIDO INORDEN: Izquierda → Raíz → Derecha
    // Resultado: películas ordenadas de MENOR a MAYOR rating
    public List<Pelicula> inorden() {
        List<Pelicula> lista = new ArrayList<>();
        inordenRecursivo(raiz, lista);
        return lista;
    }

    private void inordenRecursivo(Nodo nodo, List<Pelicula> lista) {
        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo, lista);  // 1. Visitar izquierda
            lista.add(nodo.pelicula);                  // 2. Procesar raíz
            inordenRecursivo(nodo.derecho, lista);     // 3. Visitar derecha
        }
    }

    // RECORRIDO PREORDEN: Raíz → Izquierda → Derecha
    // Resultado: útil para guardar/exportar el árbol
    public List<Pelicula> preorden() {
        List<Pelicula> lista = new ArrayList<>();
        preordenRecursivo(raiz, lista);
        return lista;
    }

    private void preordenRecursivo(Nodo nodo, List<Pelicula> lista) {
        if (nodo != null) {
            lista.add(nodo.pelicula);                   // 1. Procesar raíz
            preordenRecursivo(nodo.izquierdo, lista);   // 2. Visitar izquierda
            preordenRecursivo(nodo.derecho, lista);     // 3. Visitar derecha
        }
    }

    // RECORRIDO POSTORDEN: Izquierda → Derecha → Raíz
    // Resultado: útil para calcular promedios por rama
    public List<Pelicula> postorden() {
        List<Pelicula> lista = new ArrayList<>();
        postordenRecursivo(raiz, lista);
        return lista;
    }

    private void postordenRecursivo(Nodo nodo, List<Pelicula> lista) {
        if (nodo != null) {
            postordenRecursivo(nodo.izquierdo, lista);  // 1. Visitar izquierda
            postordenRecursivo(nodo.derecho, lista);    // 2. Visitar derecha
            lista.add(nodo.pelicula);                   // 3. Procesar raíz
        }
    }

    // ALTURA del árbol (recursivo)
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(Nodo nodo) {
        if (nodo == null) return 0;
        int altIzq = alturaRecursiva(nodo.izquierdo);
        int altDer = alturaRecursiva(nodo.derecho);
        return 1 + Math.max(altIzq, altDer);
    }

    // CONTAR nodos (recursivo)
    public int contarNodos() {
        return contarRecursivo(raiz);
    }

    private int contarRecursivo(Nodo nodo) {
        if (nodo == null) return 0;
        return 1 + contarRecursivo(nodo.izquierdo) + contarRecursivo(nodo.derecho);
    }

    // OBTENER RAÍZ (para dibujar el árbol en la interfaz)
    public Nodo getRaiz() {
        return raiz;
    }

    // VERIFICAR si el árbol está vacío
    public boolean estaVacio() {
        return raiz == null;
    }
}