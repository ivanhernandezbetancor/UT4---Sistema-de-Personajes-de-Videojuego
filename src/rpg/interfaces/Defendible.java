package rpg.interfaces;

// Interfaz que representa la capacidad de defenderse
public interface Defendible {
    
    // Acción de defensa 
    void defender();

    // Método para reducir el daño recibido
    int reducirDanio(int danioEntrante);
}