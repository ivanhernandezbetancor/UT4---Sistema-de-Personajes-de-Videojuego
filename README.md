## UT4---Sistema-de-Personajes-de-Videojuego

## Autores : Iván , Izan , Estefania y Miguel

## Descripción                                                                                                                 
- Sistema de gestión de personajes para un videojuego RPG que demuestra conceptos avanzados de Programación Orientada a Objetos.

## Cómo ejecutar el programa
 - Ejecutar el java rpg.app.Main
   
## Reparto de tareas:

## Iván 

- App : Main.java
- model : Personaje.java , Asesino.java 
- util : EstadoPersonaje.java

## Estefania

-

## Izan 

-

## Miguel

-

## Diagrama simple de la jerarquía en Lista 

## Nivel 1 (Base):

 - Personaje (abstracta)

## Nivel 2 (Intermedias):

- PersonajeFisico (abstracta, extiende Personaje)
- PersonajeMagico (abstracta, extiende Personaje)

## Nivel 3 - Clases Concretas de PersonajeFisico:

- Guerrero (implementa Defendible)
- Arquero
- Asesino (implementa Defendible)
- Paladín (implementa Defendible + Curable)

## Nivel 3 - Clases Concretas de PersonajeMagico:

- Mago (implementa Magico)
- Clérigo (implementa Curable + Magico)
- Hechicero (implementa Magico)

## Interfaces (Capacidades):

- Defendible (Guerrero, Asesino, Paladín)
- Curable (Clérigo, Paladín)
- Magico (Mago, Clérigo, Hechicero)
