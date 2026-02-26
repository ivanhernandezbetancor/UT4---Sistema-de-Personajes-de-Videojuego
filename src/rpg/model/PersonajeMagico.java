package rpg.model;

//Subclase abstracta intermedia para personajes mágicos.
public abstract class PersonajeMagico extends Personaje {

    private int mana;
    private int manaMaximo;
    private int inteligencia;

    public PersonajeMagico(String nombre, int nivel, int saludMaxima, int manaMaximo, int inteligencia) {
        super(nombre, nivel, saludMaxima);
        this.manaMaximo = manaMaximo;
        this.mana = manaMaximo;
        this.inteligencia = inteligencia;
    }

    //Método no abstracto propio de PersonajeMagico
    public void regenerarMana(int cantidad) {
        this.mana = Math.min(manaMaximo, this.mana + cantidad);
        System.out.println(getNombre() + " regenera " + cantidad + " de maná. Maná actual: " + mana);
    }

    public void concentrarse() {
        System.out.println(getNombre() + " Concentrandose....");
        regenerarMana(15);
    }

    public boolean tieneMana(int coste) {
        return mana >= coste;
    }

    public void gastarMana(int cantidad) {
        this.mana = Math.max(0, this.mana - cantidad);
    }

    public int getMana() { 
        return mana; 
    }

    public int getManaMaximo() { 
        return manaMaximo; 
    }

    public int getInteligencia() { 
        return inteligencia; 
    }

    public void setInteligencia(int i) { 
        this.inteligencia = i; 
    }

    @Override
    public String toString() {
        return super.toString() + " | Maná: " + mana + "/" + manaMaximo;
    }
}
