package rpg.util;

// Clase contenedora que agrupa todas las excepciones personalizadas del juego RPG
// Usar una clase contenedora permite tenerlas organizadas en un único archivo
// y acceder a ellas con la sintaxis Excepciones.NombreExcepcion
public class Excepciones {

    // Excepción lanzada cuando se busca un personaje que no existe en la partida
    // Extiende Exception (Checked): obliga al compilador a que quien llame a buscarPersonaje()
    // en Juego.java la capture con try-catch o la declare con throws
    // Se elige Checked porque es un error recuperable: el programa puede seguir funcionando
    public static class PersonajeNoEncontradoException extends Exception {
        public PersonajeNoEncontradoException(String mensaje) {
            super(mensaje); // Pasa el mensaje a Exception para mostrarlo con getMessage()
        }
    }

    // Excepción lanzada cuando un personaje muerto intenta realizar una acción
    // Extiende RuntimeException (Unchecked): no obliga a capturarla en tiempo de compilación
    // Se elige Unchecked porque es un error de lógica del programa que no debería ocurrir
    // si el código comprueba estaVivo() antes de actuar
    public static class PersonajeMuertoException extends RuntimeException {
        public PersonajeMuertoException(String nombrePersonaje) {
            // Construye un mensaje descriptivo con el nombre del personaje implicado
            super("El personaje '" + nombrePersonaje + "' está muerto y no puede realizar esta acción.");
        }
    }

    // Excepción lanzada cuando un personaje intenta usar una habilidad sin mana suficiente
    // Extiende RuntimeException (Unchecked): el código debería comprobar el mana antes de actuar
    // Recibe tres parámetros para construir un mensaje detallado con el contexto del error:
    // quién intentó actuar, cuánto necesitaba y cuánto tenía realmente
    public static class ManaInsuficienteException extends RuntimeException {
        public ManaInsuficienteException(String nombrePersonaje, int manaRequerido, int manaActual) {
            super("'" + nombrePersonaje + "' no tiene suficiente mana. "
                    + "Requerido: " + manaRequerido + " | Actual: " + manaActual);
        }
    }

    // Excepción lanzada cuando se intenta curar a un personaje que ya tiene la salud al máximo
    // Extiende RuntimeException (Unchecked): es un error evitable comprobando salud antes de curar
    public static class SaludMaximaException extends RuntimeException {
        public SaludMaximaException(String nombrePersonaje) {
            super("El personaje '" + nombrePersonaje + "' ya tiene la salud al máximo.");
        }
    }

    // Excepción lanzada cuando se intenta registrar un personaje nulo o duplicado en el juego
    // Extiende RuntimeException (Unchecked): es responsabilidad del llamante enviar datos válidos
    // Recibe un mensaje genérico para poder reutilizarse en distintos casos de personaje inválido
    public static class PersonajeInvalidoException extends RuntimeException {
        public PersonajeInvalidoException(String mensaje) {
            super(mensaje);
        }
    }
}