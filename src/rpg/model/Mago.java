package rpg.model;

import rpg.interfaces.Magico;

//Mago: personaje mágico ofensivo

public class Mago extends PersonajeMagico implements Magico {

    //atributo propio
    private String escuelaHechizos; 
    //atributo propio
    private int poderMagico;         

    public Mago(String nombre, int nivel) {
        super(nombre, nivel, 70, 120, 22);
        this.escuelaHechizos = "Fuego";
        this.poderMagico = 30;
    }

    @Override
    public void atacar(Personaje objetivo) {
        concentrarse();
        lanzarHechizo(objetivo);
    }

    //Implementación de Magico
    @Override
    public void lanzarHechizo(Personaje objetivo) {
        int coste = 20;
        if (tieneMana(coste)) {
            int dano = getInteligencia() + poderMagico + (int)(Math.random() * 15);
            System.out.println(getNombre() + " lanza '" + getNombreHechizo() + "' a " + objetivo.getNombre() + " causando " + dano + " de daño mágico.");
            objetivo.recibirDanio(dano);
            gastarMana(coste);
        } else {
            System.out.println(getNombre() + " no tiene maná. Lanza un rayo básico.");
            objetivo.recibirDanio(getInteligencia() / 2);
        }
    }

    @Override
    public String getNombreHechizo() {
        return "Bola de " + escuelaHechizos;
    }

    //Método propio adicional
    public void cambiarEscuela(String escuela) {
        this.escuelaHechizos = escuela;
        System.out.println(getNombre() + " ahora domina la escuela de: " + escuela);
    }

    public String getEscuelaHechizos() { 
        return escuelaHechizos; 
    }
    public int getPoderMagico() { 
        return poderMagico; 
    }
}
