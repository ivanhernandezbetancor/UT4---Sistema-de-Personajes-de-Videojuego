package rpg.model;

import rpg.interfaces.Curable;
import rpg.interfaces.Magico;

//Clérigo: personaje mágico de soporte que cura y lanza hechizos divinos

public class Clerigo extends PersonajeMagico implements Curable, Magico {

    //atributo propio
    private int fe;            
    //atributo propio
    private int poderDivino;  

    public Clerigo(String nombre, int nivel) {
        super(nombre, nivel, 85, 100, 18);
        this.fe = 80;
        this.poderDivino = 25;
    }

    @Override
    public void atacar(Personaje objetivo) {
        lanzarHechizo(objetivo);
    }

    //Implementación de Curable
    @Override
    public void curar(Personaje objetivo) {
        int curacion = calcularCuracion();
        int coste = 15;
        if (tieneMana(coste)) {
            objetivo.setSalud(objetivo.getSalud() + curacion);
            System.out.println(getNombre() + " cura a " + objetivo.getNombre() + " por " + curacion + " puntos de salud.");
            gastarMana(coste);
        } else {
            System.out.println(getNombre() + " no tiene maná para curar.");
        }
    }

    @Override
    public int calcularCuracion() {
        return poderDivino + fe / 5 + (int)(Math.random() * 10);
    }

    //Implementación de Magico
    @Override
    public void lanzarHechizo(Personaje objetivo) {
        int coste = 18;
        if (tieneMana(coste)) {
            int dano = getInteligencia() + poderDivino / 2;
            System.out.println(getNombre() + " lanza '" + getNombreHechizo() + "' a " + objetivo.getNombre() + " causando " + dano + " de daño sagrado.");
            objetivo.recibirDanio(dano);
            gastarMana(coste);
        } else {
            System.out.println(getNombre() + " no tiene maná suficiente.");
        }
    }

    @Override
    public String getNombreHechizo() {
        return "Castigo Divino";
    }

    //Método propio adicional
    public void bendecir(Personaje aliado) {
        System.out.println(getNombre() + " bendice a " + aliado.getNombre() + " con la protección divina.");
        aliado.setSalud(Math.min(aliado.getSaludMaxima(), aliado.getSalud() + 20));
    }

    public int getFe() { 
        return fe; 
    }
    
    public int getPoderDivino() { 
        return poderDivino; 
    }
}
