package rpg.model;
import java.util.Objects;
import java.util.UUID;

// 1) Clase abstracta base Personaje (obligatoria)
public abstract class Personaje {

    // Encapsulación: atributos privados : nombre, nivel, salud y saludMaxima
    private final String id;
    private String nombre;
    private int nivel;
    private int salud;
    private int saludMaxima;

    // Constructor
    public Personaje(String nombre, int nivel, int saludMaxima) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.nivel = nivel;
        this.saludMaxima = saludMaxima;
        this.salud = saludMaxima;
    }

    // Método abstracto: cada subclase implementa su propio ataque => atacar(Personaje objetivo).
    public abstract void atacar(Personaje objetivo);

    // Método concreto compartido
    public void recibirDanio(int danio) {
        this.salud = Math.max(0, this.salud - danio);
    }

    public boolean estaVivo() {
        return this.salud > 0;
    }

    // equals y hashCode por id (obligatorio)
    @Override
    public boolean equals(Object obje) {
        if (this == obje)
            return true;
        if (!(obje instanceof Personaje))
            return false;
        Personaje p = (Personaje) obje;
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "] " + nombre + " | Nivel: " + nivel + " | Salud: " + salud + "/" + saludMaxima;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public int getSalud() {
        return salud;
    }

    public int getSaludMaxima() {
        return saludMaxima;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setSalud(int salud) {
        this.salud = Math.max(0, Math.min(salud, saludMaxima));
    }

    public void setSaludMaxima(int saludMaxima) {
        this.saludMaxima = saludMaxima;
    }
}