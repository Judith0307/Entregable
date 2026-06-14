package recomendacionpelicula;

/**
 * Árbol Binario de Búsqueda ordenado por ID (único por película).
 * El ID garantiza que no haya colisiones, a diferencia del rating.
 * El rating se usa solo para análisis estadístico (top 10).
 */

import java.util.ArrayList;
import java.util.List;

public class ArbolBST {

    // ATRIBUTO RAÍZ
    private Nodo raiz;

    // CONSTRUCTOR
    public ArbolBST() {
        this.raiz = null;
    }

    // ─────────────────────────────────────────────────────────
    // INSERTAR — ordenado por ID (garantiza nodos únicos)
    // ─────────────────────────────────────────────────────────
    public void insertar(Pelicula pelicula) {
        raiz = insertarRecursivo(raiz, pelicula);
    }

    private Nodo insertarRecursivo(Nodo nodoActual, Pelicula pelicula) {
    // CASO BASE: posición vacía → crear nodo
    if (nodoActual == null) {
        return new Nodo(pelicula);
    }

    // Comparar títulos alfabéticamente
    int comparacion = pelicula.getTitulo().compareToIgnoreCase(nodoActual.pelicula.getTitulo());

    if (comparacion < 0) {
        // Título menor alfabéticamente → izquierda
        nodoActual.izquierdo = insertarRecursivo(nodoActual.izquierdo, pelicula);
    } else if (comparacion > 0) {
        // Título mayor alfabéticamente → derecha
        nodoActual.derecho = insertarRecursivo(nodoActual.derecho, pelicula);
    }
    // Título igual → ignorar (película duplicada)

    return nodoActual;
}

    // ─────────────────────────────────────────────────────────
    // BUSCAR por título (recorre todo el árbol)
    // ─────────────────────────────────────────────────────────
    public Pelicula buscarPorTitulo(String titulo) {
        return buscarRecursivo(raiz, titulo);
    }

    private Pelicula buscarRecursivo(Nodo nodo, String titulo) {
        if (nodo == null) return null;
        if (nodo.pelicula.getTitulo().equalsIgnoreCase(titulo)) return nodo.pelicula;

        Pelicula encontrada = buscarRecursivo(nodo.izquierdo, titulo);
        if (encontrada != null) return encontrada;
        return buscarRecursivo(nodo.derecho, titulo);
    }

    // ─────────────────────────────────────────────────────────
    // INORDEN: Izquierda → Raíz → Derecha  (películas por ID ascendente)
    // ─────────────────────────────────────────────────────────
    public List<Pelicula> inorden() {
        List<Pelicula> lista = new ArrayList<>();
        inordenRecursivo(raiz, lista);
        return lista;
    }

    private void inordenRecursivo(Nodo nodo, List<Pelicula> lista) {
        if (nodo != null) {
            inordenRecursivo(nodo.izquierdo, lista);
            lista.add(nodo.pelicula);
            inordenRecursivo(nodo.derecho, lista);
        }
    }

    // ─────────────────────────────────────────────────────────
    // PREORDEN: Raíz → Izquierda → Derecha
    // ─────────────────────────────────────────────────────────
    public List<Pelicula> preorden() {
        List<Pelicula> lista = new ArrayList<>();
        preordenRecursivo(raiz, lista);
        return lista;
    }

    private void preordenRecursivo(Nodo nodo, List<Pelicula> lista) {
        if (nodo != null) {
            lista.add(nodo.pelicula);
            preordenRecursivo(nodo.izquierdo, lista);
            preordenRecursivo(nodo.derecho, lista);
        }
    }

    // ─────────────────────────────────────────────────────────
    // POSTORDEN: Izquierda → Derecha → Raíz
    // ─────────────────────────────────────────────────────────
    public List<Pelicula> postorden() {
        List<Pelicula> lista = new ArrayList<>();
        postordenRecursivo(raiz, lista);
        return lista;
    }

    private void postordenRecursivo(Nodo nodo, List<Pelicula> lista) {
        if (nodo != null) {
            postordenRecursivo(nodo.izquierdo, lista);
            postordenRecursivo(nodo.derecho, lista);
            lista.add(nodo.pelicula);
        }
    }

    // ─────────────────────────────────────────────────────────
    // TOP 10 POR RATING — usa inorden (que da todas las películas)
    // y luego ordena por rating de mayor a menor, retorna máx. 10.
    // Toda la lógica es recursiva internamente (vía inorden).
    // ─────────────────────────────────────────────────────────
    public List<Pelicula> top10PorRating() {
        // 1. Obtenemos todas las películas via inorden (recursivo)
        List<Pelicula> todas = inorden();

        // 2. Ordenamos por rating descendente (burbuja recursiva)
        ordenarPorRatingDesc(todas, todas.size());

        // 3. Retornamos máximo 10
        int limite = Math.min(10, todas.size());
        return todas.subList(0, limite);
    }

    // Ordenamiento burbuja recursivo por rating descendente
    private void ordenarPorRatingDesc(List<Pelicula> lista, int n) {
        // CASO BASE: un solo elemento ya está ordenado
        if (n <= 1) return;

        // Una pasada de burbuja: mueve el menor al final
        for (int i = 0; i < n - 1; i++) {
            if (lista.get(i).getRating() < lista.get(i + 1).getRating()) {
                Pelicula temp = lista.get(i);
                lista.set(i, lista.get(i + 1));
                lista.set(i + 1, temp);
            }
        }

        // Llamada recursiva ignorando el último (ya está en su lugar)
        ordenarPorRatingDesc(lista, n - 1);
    }

    // ─────────────────────────────────────────────────────────
    // UTILIDADES
    // ─────────────────────────────────────────────────────────
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(Nodo nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(alturaRecursiva(nodo.izquierdo), alturaRecursiva(nodo.derecho));
    }

    public int contarNodos() {
        return contarRecursivo(raiz);
    }

    private int contarRecursivo(Nodo nodo) {
        if (nodo == null) return 0;
        return 1 + contarRecursivo(nodo.izquierdo) + contarRecursivo(nodo.derecho);
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }
}