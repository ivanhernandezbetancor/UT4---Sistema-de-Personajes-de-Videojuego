package rpg.model;

import rpg.interfaces.Curable;
import rpg.interfaces.Defendible;
import rpg.interfaces.Magico;
import rpg.util.Excepciones;
import java.util.ArrayList;

// Juego.java — Clase gestora del juego RPG
// Se encarga de registrar personajes, simular turnos y demostrar
// los conceptos de polimorfismo, herencia e interfaces del proyecto
public class Juego {

    // Lista de personajes del juego
    // ArrayList<Personaje> permite almacenar cualquier subclase gracias al polimorfismo
    private ArrayList<Personaje> personajes;
    private String nombrePartida;
    // Contador de turnos jugados, empieza en 0
    private int turnoActual = 0;

    // Constructor por defecto: crea una partida con nombre genérico
    public Juego() {
        this.nombrePartida = "Partida por defecto";
        this.personajes = new ArrayList<>();
    }

    // Constructor con nombre: permite personalizar el nombre de la partida
    public Juego(String nombrePartida) {
        this.nombrePartida = nombrePartida;
        this.personajes = new ArrayList<>();
    }

    // Añade un personaje a la partida si no es nulo
    public void agregarPersonaje(Personaje p) {
        if (p != null) {
            personajes.add(p);
            System.out.println("Sincronizando: " + p.getNombre() + " ha entrado al mundo.");
        }
    }

    // Muestra por pantalla el estado de todos los personajes registrados
    // Llama a toString() de cada uno — polimorfismo: cada clase tiene su propio toString()
    public void listarPersonajes() {
        System.out.println("\nLISTA DE PERSONAJES EN " + nombrePartida + "\n");
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        for (Personaje p : personajes) {
            System.out.println(p.toString());
        }
        System.out.println();
    }

    // Simula un turno de combate entre dos personajes consecutivos de la lista
    // Usa el operador módulo (%) para que la lista sea circular (el último ataca al primero)
    // Polimorfismo: atacar() se comporta diferente según el tipo real del objeto
    public void simularTurno() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para simular turno.");
            return;
        }

        // El atacante es el personaje del turno actual, el objetivo el siguiente
        Personaje atacante = personajes.get(turnoActual % personajes.size());
        Personaje objetivo = personajes.get((turnoActual + 1) % personajes.size());

        System.out.print("[TURNO " + (turnoActual + 1) + "] ");
        atacante.atacar(objetivo); // Llamada polimórfica: cada clase ejecuta su propio atacar()

        turnoActual++;
    }

    // Muestra qué capacidades (interfaces) tiene cada personaje
    // Usa instanceof para comprobar en tiempo de ejecución el tipo real del objeto
    // Esto es posible gracias al polimorfismo: la referencia es Personaje pero el objeto real puede ser más específico
    public void mostrarAcciones() {
        System.out.println("CAPACIDADES DE PERSONAJES\n");

        for (Personaje p : personajes) {
            System.out.println(p.getNombre() + ":");

            // Comprueba si implementa la interfaz Curable
            if (p instanceof Curable) {
                System.out.println("  - [CURABLE] Puede curar aliados");
            }

            // Comprueba si implementa la interfaz Defendible
            if (p instanceof Defendible) {
                System.out.println("  - [DEFENDIBLE] Puede defenderse y reducir danio");
            }

            // Comprueba si implementa la interfaz Magico
            if (p instanceof Magico) {
                System.out.println("  - [MAGICO] Puede lanzar hechizos");
            }

            // Comprueba la jerarquía de herencia: rama física o mágica
            if (p instanceof PersonajeFisico) {
                System.out.println("  - Tipo: FISICO - Usa fuerza y armadura");
            } else if (p instanceof PersonajeMagico) {
                System.out.println("  - Tipo: MAGICO - Usa inteligencia y mana");
            }
            System.out.println();
        }
    }

    // Busca un personaje por nombre (sin distinguir mayúsculas/minúsculas)
    // Lanza PersonajeNoEncontradoException si no existe — excepción personalizada de rpg.util
    public Personaje buscarPersonaje(String nombre) throws Excepciones.PersonajeNoEncontradoException {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        throw new Excepciones.PersonajeNoEncontradoException("El personaje '" + nombre + "' no existe.");
    }

    // Muestra estadísticas generales de la partida:
    // total de personajes, turno actual y cuántos siguen vivos
    public void mostrarEstadisticas() {
        System.out.println("\nESTADISTICAS GENERALES\n");
        System.out.println("Total de personajes: " + personajes.size());
        System.out.println("Partida: " + nombrePartida);
        System.out.println("Turno actual: " + turnoActual);

        // Recorre la lista y cuenta los personajes que siguen vivos (salud > 0)
        int vivos = 0;
        for (Personaje p : personajes) {
            if (p.estaVivo()) {
                vivos++;
            }
        }
        System.out.println("Personajes vivos: " + vivos + "/" + personajes.size());
        System.out.println();
    }

    // Demuestra el polimorfismo con colecciones:
    // todos los personajes llaman a atacar() pero cada uno lo ejecuta a su manera
    // Solo se procesan los 3 primeros para no saturar la salida
    public void demostrarPolimorfismo() {
        System.out.println("DEMOSTRACION DE POLIMORFISMO EN COLECCIONES\n");
        System.out.println("Todos los personajes atacan (pero cada uno de forma distinta):\n");

        for (int i = 0; i < personajes.size() && i < 3; i++) {
            Personaje atacante = personajes.get(i);
            // Módulo para que el último ataque al primero (lista circular)
            Personaje objetivo = personajes.get((i + 1) % personajes.size());

            System.out.println("[" + (i + 1) + "] Ataque polimorfismo:");
            atacante.atacar(objetivo); // Cada subclase ejecuta su propio atacar()
            System.out.println();
        }
    }

    // Cuenta cuántos personajes implementan cada interfaz
    // Útil para ver la distribución de capacidades en la partida
    public void contarPorCapacidad() {
        System.out.println("CONTEO POR CAPACIDADES\n");

        int curables = 0;
        int defendibles = 0;
        int magicos = 0;

        for (Personaje p : personajes) {
            if (p instanceof Curable) curables++;
            if (p instanceof Defendible) defendibles++;
            if (p instanceof Magico) magicos++;
        }

        System.out.println("Personajes CURABLES: " + curables);
        System.out.println("Personajes DEFENDIBLES: " + defendibles);
        System.out.println("Personajes MAGICOS: " + magicos);
        System.out.println();
    }

    // Hace que todos los personajes que implementan Curable curen al objetivo
    // Cast explícito a Curable para acceder al método curar()
    // (la referencia Personaje no lo tiene, solo lo tienen los que implementan la interfaz)
    public void ejecutarCuracionesEnGrupo(Personaje objetivo) {
        System.out.println("CURACIONES DE GRUPO\n");
        int curacionesTotales = 0;

        for (Personaje p : personajes) {
            if (p instanceof Curable) {
                Curable curante = (Curable) p; // Cast necesario para llamar a curar()
                System.out.println(p.getNombre() + " intenta curar...");
                curante.curar(objetivo);
                curacionesTotales++;
            }
        }

        System.out.println("Total de personajes que intentaron curar: " + curacionesTotales);
        System.out.println();
    }

    // Hace que todos los personajes que implementan Defendible activen su defensa
    // Mismo patrón que ejecutarCuracionesEnGrupo: instanceof + cast explícito
    public void ejecutarDefensasEnGrupo() {
        System.out.println("DEFENSAS DE GRUPO\n");
        int defensasActivadas = 0;

        for (Personaje p : personajes) {
            if (p instanceof Defendible) {
                Defendible defensor = (Defendible) p; // Cast para acceder a defender()
                System.out.println(p.getNombre() + " se prepara para defender:");
                defensor.defender();
                defensasActivadas++;
            }
        }

        System.out.println("Total de defensas activadas: " + defensasActivadas);
        System.out.println();
    }

    // Hace que todos los personajes que implementan Magico lancen su hechizo sobre el objetivo
    // Mismo patrón: instanceof + cast a Magico para acceder a lanzarHechizo()
    public void ejecutarHechizosEnGrupo(Personaje objetivo) {
        System.out.println("ASALTO MAGICO DE GRUPO\n");
        int hechizosLanzados = 0;

        for (Personaje p : personajes) {
            if (p instanceof Magico) {
                Magico mago = (Magico) p; // Cast para acceder a lanzarHechizo()
                System.out.println(p.getNombre() + " lanza un hechizo:");
                mago.lanzarHechizo(objetivo);
                hechizosLanzados++;
            }
        }

        System.out.println("Total de hechizos lanzados: " + hechizosLanzados);
        System.out.println();
    }

    // Getters y Setters
    public ArrayList<Personaje> getPersonajes() {
        return personajes;
    }

    public String getNombrePartida() {
        return nombrePartida;
    }

    public void setNombrePartida(String nombre) {
        this.nombrePartida = nombre;
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public int getTotalPersonajes() {
        return personajes.size();
    }
}