package brickBracker;

import java.awt.*;

public class MapGenerator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    public MapGenerator (int row, int col) {
        map = new int[row][col];  // Initialisation du tableau "map" avec une taille de "row" lignes et "col" colonnes
        for (int i=0; i < map.length; i++) {
            for (int j=0; j< map[0].length; j++){
                map[i][j] = 1; // Initialise chaque élément de "map" à 1 (brique active)
            }
        }
        brickWidth = 540/col;  // Calcule la largeur de chaque brique en divisant la largeur de la zone de jeu disponible par le nombre de colonnes
        brickHeight = 150/row; // Calcule la hauteur de chaque brique en divisant la hauteur de la zone de jeu disponible par le nombre de lignes
    }
    public void draw(Graphics2D g){
        for (int i=0; i < map.length; i++) {
            for (int j=0; j< map[0].length; j++) {
                if(map[i][j] > 0) {  // Si la brique est active (la valeur de l'élément dans "map" est supérieure à zéro)
                    g.setColor(Color.white); // Définit la couleur de dessin à blanc
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight); // Dessine la brique à la position appropriée
                }
            }
        }
    }
}
