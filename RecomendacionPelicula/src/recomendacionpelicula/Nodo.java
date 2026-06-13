package recomendacionpelicula;
public class Nodo {
    // ATRIBUTOS
    Pelicula pelicula;   // Dato que almacena el nodo (una película)
    Nodo izquierdo;      // Hijo izquierdo (rating menor)
    Nodo derecho;        // Hijo derecho (rating mayor)

    // CONSTRUCTOR
    public Nodo(Pelicula pelicula) {
        this.pelicula = pelicula;
        this.izquierdo = null;
        this.derecho = null;
    }
}