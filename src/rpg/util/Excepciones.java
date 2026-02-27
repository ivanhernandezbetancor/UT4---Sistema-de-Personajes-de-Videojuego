package rpg.util;

// Clase que agrupa las excepciones personalizadas del juego RPG
public class Excepciones {

    // Excepción lanzada cuando un personaje intenta actuar pero está muerto
    public static class PersonajeMuertoException extends RuntimeException {
        public PersonajeMuertoException(String nombrePersonaje) {
            super("El personaje '" + nombrePersonaje + "' está muerto y no puede realizar esta acción.");
        }
    }

    // Excepción lanzada cuando no hay suficiente mana para ejecutar una habilidad
    public static class ManaInsuficienteException extends RuntimeException {
        public ManaInsuficienteException(String nombrePersonaje, int manaRequerido, int manaActual) {
            super("'" + nombrePersonaje + "' no tiene suficiente mana. "
                    + "Requerido: " + manaRequerido + " | Actual: " + manaActual);
        }
    }

    // Excepción lanzada cuando se intenta curar a un personaje que ya tiene la salud máxima
    public static class SaludMaximaException extends RuntimeException {
        public SaludMaximaException(String nombrePersonaje) {
            super("El personaje '" + nombrePersonaje + "' ya tiene la salud al máximo.");
        }
    }

    // Excepción lanzada cuando se intenta añadir un personaje nulo o duplicado al juego
    public static class PersonajeInvalidoException extends RuntimeException {
        public PersonajeInvalidoException(String mensaje) {
            super(mensaje);
        }
    }
}