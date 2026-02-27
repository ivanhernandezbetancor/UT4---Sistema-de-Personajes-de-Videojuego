package rpg.app;

import rpg.model.Guerrero;
import rpg.model.Arquero;
import rpg.model.Asesino;
import rpg.model.Paladin;
import rpg.model.Mago;
import rpg.model.Clerigo;
import rpg.model.Hechicero;
import rpg.model.Personaje;
import rpg.model.PersonajeFisico;
import rpg.model.PersonajeMagico;
import rpg.model.Juego;
import rpg.interfaces.Curable;
import rpg.interfaces.Defendible;
import rpg.interfaces.Magico;
import rpg.util.EstadoPersonaje;
import rpg.util.Excepciones;

public class Main {

    public static void main(String[] args) {
        
        System.out.println("\nJUEGO RPG - POO JAVA AVANZADA\n");

        System.out.println("SECCION 1: CREANDO PERSONAJES\n");
        
        Guerrero guerrero = new Guerrero("Aragorn", 10);
        Arquero arquero = new Arquero("Legolas", 9);
        Asesino asesino = new Asesino("Frodo", 8);
        Paladin paladin = new Paladin("Uther", 11, 110, 20, 18);
        Mago mago = new Mago("Gandalf", 12);
        Clerigo clerigo = new Clerigo("Elrond", 11);
        Hechicero hechicero = new Hechicero("Voldemort", 13, 80, 150);

        System.out.println("\nSECCION 2: REGISTRANDO PERSONAJES EN JUEGO\n");
        
        Juego juego = new Juego("Batalla Epica del Anillo");
        
        juego.agregarPersonaje(guerrero);
        juego.agregarPersonaje(arquero);
        juego.agregarPersonaje(asesino);
        juego.agregarPersonaje(paladin);
        juego.agregarPersonaje(mago);
        juego.agregarPersonaje(clerigo);
        juego.agregarPersonaje(hechicero);

        System.out.println("\nSECCION 3: LISTADO CON toString()\n");
        juego.listarPersonajes();

        System.out.println("\nSECCION 4: ATAQUES POLIMORFICOS\n");
        
        guerrero.atacar(mago);
        arquero.atacar(mago);
        asesino.atacar(mago);
        paladin.atacar(mago);
        mago.atacar(guerrero);
        clerigo.atacar(guerrero);
        hechicero.atacar(paladin);
        System.out.println();

        System.out.println("\nSECCION 5: POLIMORFISMO CON COLECCIONES\n");
        juego.demostrarPolimorfismo();

        System.out.println("\nSECCION 6: instanceof CON CRITERIO\n");
        juego.mostrarAcciones();

        System.out.println("\nSECCION 7: CONTEO POR CAPACIDADES\n");
        juego.contarPorCapacidad();

        System.out.println("\nSECCION 8: CAPACIDAD CURABLE\n");
        
        if (clerigo instanceof Curable) {
            System.out.println("Clerigo es Curable, curando a Guerrero:");
            clerigo.curar(guerrero);
        }
        if (paladin instanceof Curable) {
            System.out.println("\nPaladin es Curable, curando a Arquero:");
            paladin.curar(arquero);
        }
        
        System.out.println("\n");
        juego.ejecutarCuracionesEnGrupo(guerrero);

        System.out.println("\nSECCION 9: CAPACIDAD DEFENDIBLE\n");
        
        if (guerrero instanceof Defendible) {
            System.out.println("Guerrero es Defendible:");
            guerrero.defender();
        }
        if (asesino instanceof Defendible) {
            System.out.println("\nAsesino es Defendible:");
            asesino.defender();
        }
        if (paladin instanceof Defendible) {
            System.out.println("\nPaladin es Defendible:");
            paladin.defender();
        }
        
        System.out.println("\n");
        juego.ejecutarDefensasEnGrupo();

        System.out.println("\nSECCION 10: CAPACIDAD MAGICO\n");
        
        if (mago instanceof Magico) {
            System.out.println("Mago es Magico:");
            mago.lanzarHechizo(hechicero);
        }
        if (clerigo instanceof Magico) {
            System.out.println("\nClérigo es Magico:");
            clerigo.lanzarHechizo(guerrero);
        }
        if (hechicero instanceof Magico) {
            System.out.println("\nHechicero es Magico:");
            hechicero.lanzarHechizo(paladin);
        }
        
        System.out.println("\n");
        juego.ejecutarHechizosEnGrupo(paladin);

        System.out.println("\nSECCION 11: METODOS PROPIOS DE CLASES\n");
        
        guerrero.golpeFurioso(asesino);
        arquero.disparoRapido(mago);
        asesino.ocultarse();
        System.out.println();
        asesino.atacar(guerrero);
        asesino.envenenar(arquero);
        mago.cambiarEscuela("Hielo");
        System.out.println();
        mago.lanzarHechizo(asesino);
        System.out.println();
        clerigo.bendecir(paladin);
        hechicero.invocar();
        System.out.println();

        System.out.println("\nSECCION 12: ENUM ESTADO PERSONAJE\n");
        
        EstadoPersonaje estadoGuerrero = EstadoPersonaje.determinarEstado(
            guerrero.getSalud(), 
            guerrero.getSaludMaxima()
        );
        System.out.println("Estado de " + guerrero.getNombre() + ": " 
            + estadoGuerrero.name() + " - " + estadoGuerrero.getDescripcion());

        EstadoPersonaje estadoMago = EstadoPersonaje.determinarEstado(
            mago.getSalud(), 
            mago.getSaludMaxima()
        );
        System.out.println("Estado de " + mago.getNombre() + ": " 
            + estadoMago.name() + " - " + estadoMago.getDescripcion());
        System.out.println();

        System.out.println("\nSECCION 13: MANEJO DE EXCEPCIONES\n");
        
        try {
            System.out.println("Buscando 'Gandalf'...");
            Personaje encontrado = juego.buscarPersonaje("Gandalf");
            System.out.println("Encontrado: " + encontrado.getNombre());
            
            System.out.println("\nBuscando 'Pikachu'...");
            juego.buscarPersonaje("Pikachu");
            
        } catch (Excepciones.PersonajeNoEncontradoException e) {
            System.out.println("Excepcion capturada: " + e.getMessage());
        }
        System.out.println();

        System.out.println("\nSECCION 14: HERENCIA Y EQUALS/HASHCODE\n");
        
        System.out.println("Guerrero es PersonajeFisico: " + (guerrero instanceof PersonajeFisico));
        System.out.println("Mago es PersonajeMagico: " + (mago instanceof PersonajeMagico));
        System.out.println("Ambos heredan de Personaje: " + 
            (guerrero instanceof Personaje && mago instanceof Personaje));
        
        Personaje persona = guerrero;
        System.out.println("Guerrero.equals(ref a Guerrero): " + guerrero.equals(persona));
        System.out.println();

        System.out.println("\nSECCION 15: ESTADO FINAL\n");
        juego.mostrarEstadisticas();
        juego.listarPersonajes();

        System.out.println("\nFIN DE LA DEMOSTRACION\n");
    }
}