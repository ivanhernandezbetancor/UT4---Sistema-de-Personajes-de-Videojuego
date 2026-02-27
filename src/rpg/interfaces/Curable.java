package rpg.interfaces;

import rpg.model.Personaje;

//Interfaz de capacidad: personajes que pueden curar

public interface Curable {
    void curar(Personaje objetivo);
    int calcularCuracion();
}
