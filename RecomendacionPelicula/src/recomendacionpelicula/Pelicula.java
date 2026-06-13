package recomendacionpelicula;
public class Pelicula {
    
    // ATRIBUTOS
    private int id;              // ID único de la película
    private String titulo;       // Título de la película
    private String genero;       // Género: Acción, Drama, Comedia, Terror, Sci-Fi, Romance, Documental
    private int anio;            // Año de estreno
    private double rating;       // Calificación del 1.0 al 10.0
    private String clasificacion; // G, PG, PG-13, R (para filtro por edad)
    private boolean vistaPor;    // Si el usuario ya la vio

    // CONSTRUCTOR COMPLETO
    public Pelicula(int id, String titulo, String genero, int anio, double rating, String clasificacion) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.anio = anio;
        this.rating = rating;
        this.clasificacion = clasificacion;
        this.vistaPor = false; // Por defecto no vista
    }

    // GETTERS
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public int getAnio() { return anio; }
    public double getRating() { return rating; }
    public String getClasificacion() { return clasificacion; }
    public boolean isVistaPor() { return vistaPor; }

    // SETTERS
    public void setVistaPor(boolean vistaPor) { this.vistaPor = vistaPor; }
    public void setRating(double rating) { this.rating = rating; }

    // MÉTODO toString
    // Muestra la información completa de la película
    @Override
    public String toString() {
        return String.format("[%d] %s | Género: %s | Año: %d | Rating: %.1f | Clasificación: %s %s",
                id, titulo, genero, anio, rating, clasificacion,
                vistaPor ? "(Ya vista)" : "");
    }

    // MÉTODO RESUMEN (para mostrar en interfaz)
    public String toResumen() {
        return String.format("★ %.1f | %s (%d) - %s", rating, titulo, anio, genero);
    }
}