package rpg.model;

import rpg.interfaces.Curable;
import rpg.interfaces.Defendible;

// Paladin es una clase concreta que:
// - Hereda de PersonajeFisico (subclase abstracta intermedia de Personaje)
// - Implementa Curable y Defendible (dos interfaces a la vez)
// Esto demuestra que una clase puede heredar de una sola clase
// pero implementar múltiples interfaces (capacidades)
public class Paladin extends PersonajeFisico implements Curable, Defendible {

    // Atributos propios del Paladín (encapsulados como private)
    // santidad: valor espiritual que potencia tanto el daño como las curaciones
    // auraDivina: aura que rodea al Paladín (reservada para futuras habilidades)
    private int santidad;
    private int auraDivina;

    // Constructor: pasa los datos comunes a PersonajeFisico con super()
    // (nombre, nivel, saludMaxima, fuerza y armadura los gestiona la clase padre)
    // Inicializa los atributos propios del Paladín con valores fijos de partida
    public Paladin(String nombre, int nivel, int saludMaxima, int fuerza, int armadura) {
        super(nombre, nivel, saludMaxima, fuerza, armadura);
        this.santidad = 12;
        this.auraDivina = 8;
    }

    // Override obligatorio del método abstracto atacar() de Personaje
    // Polimorfismo: misma llamada atacar() → comportamiento distinto según el tipo real
    // El Paladín suma su santidad al daño, diferenciándolo de otros personajes físicos
    // getFuerza() y getNivel() son getters heredados de PersonajeFisico y Personaje
    @Override
    public void atacar(Personaje objetivo) {
        int danio = getFuerza() + (getNivel() * 2) + santidad;
        System.out.println(getNombre() + " golpea con su martillo sagrado a "
                + objetivo.getNombre() + " causando " + danio + " de daño.");
        objetivo.recibirDanio(danio); // Método heredado de Personaje
    }

    // Implementación del método curar() de la interfaz Curable
    // La curación escala con el nivel y la santidad del Paladín
    // setSalud() y getSalud() son métodos heredados de Personaje
    // La diferencia antes/después permite calcular la curación real aplicada
    // (puede ser menor que cantidadCura si el objetivo ya está cerca del máximo)
    @Override
    public void curar(Personaje objetivo) {
        int cantidadCura = 20 + (getNivel() * 3) + santidad;
        int saludAntes = objetivo.getSalud();
        objetivo.setSalud(objetivo.getSalud() + cantidadCura);
        System.out.println(getNombre() + " cura a " + objetivo.getNombre()
                + " restaurando " + (objetivo.getSalud() - saludAntes) + " puntos de salud.");
    }

    // Implementación del método calcularCuracion() de la interfaz Curable
    // Devuelve el valor máximo de curación que puede aplicar este Paladín
    // Útil para que la clase Juego pueda consultar cuánto cura sin ejecutar la curación
    @Override
    public int calcularCuracion() {
        return 25 + (santidad * 2);
    }

    // Implementación del método defender() de la interfaz Defendible
    // Activa la defensa del Paladín con su escudo sagrado
    @Override
    public void defender() {
        System.out.println(getNombre() + " levanta su escudo sagrado.");
    }

    // Implementación del método reducirDanio() de la interfaz Defendible
    // Calcula el daño final tras aplicar la reducción por armadura
    // getArmadura() es un getter heredado de PersonajeFisico
    // Se divide la armadura entre 2 para que no bloquee el daño completamente
    @Override
    public int reducirDanio(int danioEntrante) {
        int reducido = danioEntrante - (getArmadura() / 2);
        return reducido;
    }

    // Método propio adicional del Paladín: consagra el área de combate
    // No requiere recursos (mana, energía) y tiene efecto de zona
    public void consagrarZona() {
        System.out.println(getNombre() + " consagra el area.");
    }

    // Getters de los atributos propios
    // No hay setters porque santidad y auraDivina no se modifican desde fuera
    public int getSantidad() { return santidad; }
    public int getAuraDivina() { return auraDivina; }

    // toString sobrescrito: reutiliza el de PersonajeFisico (que reutiliza el de Personaje)
    // y añade la santidad como información extra característica del Paladín
    @Override
    public String toString() {
        return super.toString() + " | Santidad: " + santidad;
    }
}