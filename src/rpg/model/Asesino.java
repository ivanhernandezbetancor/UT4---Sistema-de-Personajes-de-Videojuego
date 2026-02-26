package rpg.model;

import rpg.interfaces.Defendible;

public class Asesino extends PersonajeFisico implements Defendible {

    private int sigilo;       // atributo propio
    private int veneno;       // atributo propio
    private boolean oculto;

    public Asesino(String nombre, int nivel) {
        super(nombre, nivel, 85, 16, 6);
        this.sigilo = 90;
        this.veneno = 5;
        this.oculto = false;
    }

    @Override
    public void atacar(Personaje objetivo) {
        if (oculto) {
            // Ataque crítico desde las sombras
            int dano = golpeBasico() * 3;
            System.out.println(getNombre() + " ¡ATAQUE SORPRESA desde las sombras a " + objetivo.getNombre() + "! Daño crítico: " + dano);
            objetivo.recibirDanio(dano);
            oculto = false;
        } else {
            int dano = golpeBasico() + veneno;
            System.out.println(getNombre() + " ataca sigilosamente a " + objetivo.getNombre() + " causando " + dano + " de daño (incluye veneno).");
            objetivo.recibirDanio(dano);
        }
    }

    // Método propio adicional: esconderse
    public void ocultarse() {
        this.oculto = true;
        System.out.println(getNombre() + " se funde con las sombras... ¡Está oculto!");
    }

    // Método propio adicional: envenenar
    public void envenenar(Personaje objetivo) {
        int danoVeneno = veneno * 3;
        System.out.println(getNombre() + " envenena a " + objetivo.getNombre() + " con " + danoVeneno + " de daño por veneno.");
        objetivo.recibirDanio(danoVeneno);
    }

    // Implementación de Defendible
    @Override
    public void defender() {
        System.out.println(getNombre() + " esquiva con agilidad el ataque entrante.");
    }

    @Override
    public int reducirDanio(int danioEntrante) {
        // El asesino esquiva un % del daño según su sigilo
        int reduccion = (danioEntrante * sigilo) / 200;
        int reducido = danioEntrante - reduccion;
        System.out.println(getNombre() + " esquiva parcialmente. Daño reducido: " + danioEntrante + " → " + reducido);
        return reducido;
    }

    public int getSigilo()  { return sigilo; }
    public int getVeneno()  { return veneno; }
    public boolean isOculto() { return oculto; }
}
