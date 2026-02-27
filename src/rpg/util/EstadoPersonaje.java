package rpg.util;



 // Enum para el estado de un personaje.
public enum EstadoPersonaje {

    VIVO("Vivo y en combate"),
    HERIDO("Herido, necesita curación"),
    MUERTO("Fuera de combate");

    private final String descripcion;

    // Constructor del enum
    EstadoPersonaje(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método de instancia
    public String getDescripcion() {
        return descripcion;
    }

    // Método estático: determina el estado según la salud
    public static EstadoPersonaje determinarEstado(int salud, int saludMaxima) {
        if (salud <= 0) {
            return MUERTO;
        } else if (salud < saludMaxima / 2) {
            return HERIDO;
        } else {
            return VIVO;
        }
    }
}
