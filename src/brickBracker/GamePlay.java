// Déclaration du package pour cette classe
package brickBracker;

// Import de classes nécessaires
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Déclaration de la classe GamePlay qui étend la classe JPanel et implémente les interfaces KeyListener et ActionListener
public class GamePlay extends JPanel implements KeyListener, ActionListener {

    // Variables membres privées pour stocker l'état du jeu
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;

    // Timer pour gérer les mouvements de la balle
    private Timer time;
    private int delay = 8;

    // Positions de départ de la balle et du joueur
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;

    // Directions initiales de la balle
    private int ballXdir = -1;
    private int ballYdir = -2;

    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // Background
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692, 592);

        // borders
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);

        // Paddle (barre)
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        // Balle
        g.setColor(Color.yellow);
        g.fillRect(ballposX, ballposY, 20, 20);
    }

    // Implémentation de la méthode actionPerformed de l'interface ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    // Implémentation de la méthode keyPressed de l'interface KeyListener
    @Override
    public void keyPressed(KeyEvent e) {
        // Vérifie si la touche "flèche droite" a été pressée.
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Vérifie si la raquette est déjà à la position la plus à droite possible. Si c'est le cas, la position de la raquette est fixée à 600 pixels
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }
    }

    public void moveRight() {
        play = true;
        playerX +=20;
    }
    public void moveLeft() {
        play = true;
        playerX -=20;
    }


}

