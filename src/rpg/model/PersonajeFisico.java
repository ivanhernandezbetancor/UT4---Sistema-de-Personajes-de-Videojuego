package rpg.model;

// Clase abstracta que agrupa a todos los personajes físicos.
public abstract class PersonajeFisico extends Personaje {
    
    private int fuerza;
    private int armadura;
    private boolean cargando;

    // Constructor.
    public PersonajeFisico(String nombre, int nivel, int saludMaxima, int fuerza, int armadura) {
        super(nombre, nivel, saludMaxima);

        this.fuerza = fuerza;
        this.armadura = armadura;
        this.cargando = false;
    }

    // Método compartido para preparar un ataque más potente (el siguiente golpe hará doble daño).
    public void cargarAtaque() {
        this.cargando = true;
        System.out.println(getNombre() + " carga un potente ataque físico...");
    }

    // Método común que calcula el daño.
    // Si el personaje estaba cargando, el daño se duplica.
    public int golpeBasico() {
        int danio = fuerza + (int)(Math.random() * 10);
        if (cargando) {
            danio *= 2;
            cargando = false;
            System.out.println("¡Golpe cargado! Daño doble: " + danio);
        }
        return danio;
    }

    // Getters y setters
    public int getFuerza() { 
        return fuerza; 
    }

    public int getArmadura() { 
        return armadura; 
    }

    public boolean isCargando() {
         return cargando;
    }

    public void setFuerza(int f) { 
        this.fuerza = f;
    }

    public void setArmadura(int a) {
         this.armadura = a; 
    }
}
