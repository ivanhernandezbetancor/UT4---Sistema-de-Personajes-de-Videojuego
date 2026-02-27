package rpg.model;

import rpg.util.Excepciones;
import java.util.ArrayList;

// Juego.java — Clase gestora del juego RPG
// Se encarga de registrar personajes y gestionar los combates
public class Juego {

    // Lista de personajes registrados en el juego
    // Usamos ArrayList<Personaje> para aprovechar el polimorfismo:
    // podemos almacenar cualquier subclase de Personaje
    private ArrayList<Personaje> personajes;
    private String nombrePartida;

    // Constructor
    public Juego(String nombrePartida) {
        this.nombrePartida = nombrePartida;
        this.personajes = new ArrayList<>();
    }

    // Registrar un personaje en el juego
    // Lanza excepción si el personaje es nulo o ya está registrado (equals por id)
    public void registrarPersonaje(Personaje personaje) {
        if (personaje == null) {
            throw new Excepciones.PersonajeInvalidoException("No se puede registrar un personaje nulo.");
        }
        if (personajes.contains(personaje)) {
            throw new Excepciones.PersonajeInvalidoException(
                    "El personaje '" + personaje.getNombre() + "' ya está registrado en la partida.");
        }
        personajes.add(personaje);
        System.out.println(">> Personaje registrado: " + personaje.getNombre());
    }

    // Eliminar un personaje del juego por su id
    public void eliminarPersonaje(Personaje personaje) {
        if (personajes.remove(personaje)) {
            System.out.println(">> Personaje eliminado: " + personaje.getNombre());
        } else {
            System.out.println(">> El personaje '" + personaje.getNombre() + "' no se encontró en la partida.");
        }
    }

    // Simular un combate entre dos personajes
    // Polimorfismo: se llama a atacar() y el comportamiento varía según el tipo real del objeto
    public void simularCombate(Personaje atacante, Personaje defensor) {
        System.out.println("\n==============================");
        System.out.println("  COMBATE: " + atacante.getNombre() + " vs " + defensor.getNombre());
        System.out.println("==============================");

        // Verificar que ambos están vivos antes de combatir
        if (!atacante.estaVivo()) {
            throw new Excepciones.PersonajeMuertoException(atacante.getNombre());
        }
        if (!defensor.estaVivo()) {
            throw new Excepciones.PersonajeMuertoException(defensor.getNombre());
        }

        // Turno del atacante
        System.out.println("\n[Turno de " + atacante.getNombre() + "]");
        atacante.atacar(defensor);
        System.out.println("  Estado defensor → " + defensor);

        // Turno del defensor (si sigue vivo)
        if (defensor.estaVivo()) {
            System.out.println("\n[Turno de " + defensor.getNombre() + "]");
            defensor.atacar(atacante);
            System.out.println("  Estado atacante → " + atacante);
        } else {
            System.out.println("\n" + defensor.getNombre() + " ha sido derrotado.");
        }

        System.out.println("==============================\n");
    }

    // Mostrar el estado de todos los personajes registrados
    public void mostrarEstadoGeneral() {
        System.out.println("\n===== ESTADO DE LA PARTIDA: " + nombrePartida + " =====");
        if (personajes.isEmpty()) {
            System.out.println("  No hay personajes registrados.");
        } else {
            for (Personaje p : personajes) {
                System.out.println("  " + p);
            }
        }
        System.out.println("================================================\n");
    }

    // Getters
    public String getNombrePartida() {
        return nombrePartida;
    }

    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }
}
