
package brickBracker;

import javax.swing.*;

public class main {

    // Cette ligne définit une méthode publique et statique nommée "main" qui prend en entrée un tableau de chaînes de caractères "args"
    public static void main(String[] args) {

        // Création d'un objet JFrame appelé "obj"
        JFrame obj = new JFrame();

        // Création d'un objet GamePlay appelé "gamePlay"
        GamePlay gamePlay = new GamePlay();

        // définit les dimensions de la fenêtre JFrame "obj"
        obj.setBounds(10, 10, 708, 600);

        // définit le titre de la fenêtre JFrame "obj"
        obj.setTitle("Breakout Ball");

        //  empêche la fenêtre JFrame "obj" d'être redimensionnée
        obj.setResizable(false);

        // rend la fenêtre JFrame "obj" visible à l'utilisateur
        obj.setVisible(true);

        // Cette ligne définit l'action à effectuer lorsque l'utilisateur ferme la fenêtre JFrame "obj"
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cette ligne ajoute l'objet GamePlay "gamePlay" à la fenêtre JFrame "obj"
        obj.add(gamePlay);
    }
}

