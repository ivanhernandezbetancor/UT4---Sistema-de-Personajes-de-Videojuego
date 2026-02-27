package rpg.interfaces;

import rpg.model.Personaje;

//Interfaz de capacidad: personajes que pueden lanzar hechizos
 
public interface Magico {
    void lanzarHechizo(Personaje objetivo);
    String getNombreHechizo();
}
