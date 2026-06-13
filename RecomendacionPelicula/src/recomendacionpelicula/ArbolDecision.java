package recomendacionpelicula;
/**
 * Descripción: Motor de recomendación basado en Árbol de Decisiones
 *              Filtra películas según: edad + género + rating + año + historial
 *              Usa recursividad para recorrer los nodos de decisión
 */

import java.util.ArrayList;
import java.util.List;

public class ArbolDecision {

    // =========================================================
    // CLASE INTERNA: NodoDecision
    // Cada nodo representa una pregunta o decisión
    // =========================================================
    private static class NodoDecision {
        String criterio;        // Qué evalúa este nodo ("EDAD", "GENERO", "RATING", "ANIO")
        NodoDecision siguiente; // Siguiente decisión en la cadena
        
        NodoDecision(String criterio) {
            this.criterio = criterio;
            this.siguiente = null;
        }
    }

    // =========================================================
    // CLASE: PerfilUsuario
    // Almacena las preferencias del usuario
    // =========================================================
    public static class PerfilUsuario {
        public String nombre;
        public int edad;
        public String generoFavorito;
        public double ratingMinimo;
        public int anioDesde;        // Desde qué año quiere películas
        public List<String> historialVisto; // Títulos ya vistos

        public PerfilUsuario(String nombre, int edad, String generoFavorito,
                             double ratingMinimo, int anioDesde) {
            this.nombre = nombre;
            this.edad = edad;
            this.generoFavorito = generoFavorito;
            this.ratingMinimo = ratingMinimo;
            this.anioDesde = anioDesde;
            this.historialVisto = new ArrayList<>();
        }

        // Agregar película al historial de vistas
        public void agregarAlHistorial(String tituloPelicula) {
            historialVisto.add(tituloPelicula.toLowerCase());
        }

        // Verificar si ya vio una película
        public boolean yaVio(String tituloPelicula) {
            return historialVisto.contains(tituloPelicula.toLowerCase());
        }
    }

    // =========================================================
    // MÉTODO PRINCIPAL: Recomendar películas
    // Aplica el árbol de decisiones de forma recursiva
    // =========================================================
    public List<Pelicula> recomendar(List<Pelicula> todasLasPeliculas, PerfilUsuario usuario) {
        List<Pelicula> recomendadas = new ArrayList<>();
        
        // Aplicar filtros recursivamente
        filtrarRecursivo(todasLasPeliculas, usuario, recomendadas, 0);
        
        return recomendadas;
    }

    // =========================================================
    // FILTRO RECURSIVO: El corazón del Árbol de Decisiones
    // Cada "nivel" representa un criterio de decisión
    // Nivel 0: Filtro por EDAD/Clasificación
    // Nivel 1: Filtro por GÉNERO
    // Nivel 2: Filtro por RATING mínimo
    // Nivel 3: Filtro por AÑO
    // Nivel 4: Filtro por HISTORIAL (no vista antes)
    // =========================================================
    private void filtrarRecursivo(List<Pelicula> peliculas, PerfilUsuario usuario,
                                   List<Pelicula> resultado, int nivel) {
        // CASO BASE: no hay películas que filtrar
        if (peliculas == null || peliculas.isEmpty()) return;

        List<Pelicula> filtradas = new ArrayList<>();

        switch (nivel) {
            case 0: // DECISIÓN 1: Filtrar por EDAD y Clasificación
                System.out.println("Decisión 1: Filtrando por EDAD (" + usuario.edad + " años)...");
                for (Pelicula p : peliculas) {
                    if (clasificacionPermitida(p.getClasificacion(), usuario.edad)) {
                        filtradas.add(p);
                    }
                }
                // Llamada recursiva al siguiente nivel
                filtrarRecursivo(filtradas, usuario, resultado, 1);
                break;

            case 1: // DECISIÓN 2: Filtrar por GÉNERO favorito
                System.out.println("Decisión 2: Filtrando por GÉNERO (" + usuario.generoFavorito + ")...");
                for (Pelicula p : peliculas) {
                    if (p.getGenero().equalsIgnoreCase(usuario.generoFavorito)) {
                        filtradas.add(p);
                    }
                }
                // Si no hay películas del género exacto, ampliar búsqueda
                if (filtradas.isEmpty()) {
                    System.out.println("No hay del género exacto, ampliando búsqueda...");
                    filtradas.addAll(peliculas); // Pasar todas al siguiente filtro
                }
                filtrarRecursivo(filtradas, usuario, resultado, 2);
                break;

            case 2: // DECISIÓN 3: Filtrar por RATING mínimo
                System.out.println("Decisión 3: Filtrando por RATING (≥ " + usuario.ratingMinimo + ")...");
                for (Pelicula p : peliculas) {
                    if (p.getRating() >= usuario.ratingMinimo) {
                        filtradas.add(p);
                    }
                }
                filtrarRecursivo(filtradas, usuario, resultado, 3);
                break;

            case 3: // DECISIÓN 4: Filtrar por AÑO de estreno
                System.out.println("Decisión 4: Filtrando por AÑO (desde " + usuario.anioDesde + ")...");
                for (Pelicula p : peliculas) {
                    if (p.getAnio() >= usuario.anioDesde) {
                        filtradas.add(p);
                    }
                }
                // Si no hay películas recientes, aceptar todas las que pasaron antes
                if (filtradas.isEmpty()) {
                    filtradas.addAll(peliculas);
                }
                filtrarRecursivo(filtradas, usuario, resultado, 4);
                break;

            case 4: // DECISIÓN 5: Filtrar por HISTORIAL (no vistas)
                System.out.println("Decisión 5: Filtrando HISTORIAL (quitar ya vistas)...");
                for (Pelicula p : peliculas) {
                    if (!usuario.yaVio(p.getTitulo())) {
                        filtradas.add(p);
                    }
                }
                // CASO BASE FINAL: agregar al resultado
                resultado.addAll(filtradas);
                System.out.println("Total recomendaciones encontradas: " + resultado.size());
                break;

            default:
                // No hay más niveles de decisión
                resultado.addAll(peliculas);
                break;
        }
    }

    // =========================================================
    // MÉTODO AUXILIAR: Verificar clasificación según edad
    // =========================================================
    private boolean clasificacionPermitida(String clasificacion, int edad) {
        switch (clasificacion.toUpperCase()) {
            case "G":    return true;          // Apta para todos
            case "PG":   return edad >= 7;     // Con guía parental
            case "PG-13": return edad >= 13;   // Mayores de 13
            case "R":    return edad >= 18;    // Solo adultos
            default:     return edad >= 18;    // Por defecto, adultos
        }
    }

    // =========================================================
    // MÉTODO: Obtener clasificación recomendada según edad
    // =========================================================
    public String obtenerClasificacionPorEdad(int edad) {
        if (edad < 7)  return "G";
        if (edad < 13) return "PG";
        if (edad < 18) return "PG-13";
        return "R";
    }

    // =========================================================
    // MÉTODO: Sugerir género según edad (complemento IA)
    // =========================================================
    public String sugerirGeneroPorEdad(int edad) {
        if (edad < 13)       return "Animación";
        if (edad < 18)       return "Aventura";
        if (edad < 35)       return "Acción";
        if (edad < 50)       return "Drama";
        return "Documental";
    }
}