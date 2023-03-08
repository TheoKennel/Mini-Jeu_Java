// Déclaration du package pour cette classe
package brickBracker;

// Import de classes nécessaires
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;



// Déclaration de la classe GamePlay qui étend la classe JPanel et implémente les interfaces KeyListener et ActionListener
public class GamePlay extends JPanel implements KeyListener, ActionListener {

    // Variables membres privées pour stocker l'état du jeu
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;

    // Timer pour gérer les mouvements de la balle
    private Timer timer;
    private int delay = 26;

    // Positions de départ de la balle et du joueur
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;

    // Directions initiales de la balle
    private int ballXdir = -4;
    private int ballYdir = -8;

    private MapGenerator map;

    public GamePlay() {
        map = new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // Background
        g.setColor(Color.black);
        g.fillRect(1,1,692, 592);

        // Drawing Map
        map.draw((Graphics2D)g);

        // borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(689, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0, 0, 3, 592);

        // scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);

        // Paddle (barre)
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        // Balle
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);

        g.dispose(); // dispose permet de libéré des ressources et de nettoyer la mémoire.
    }

    // Implémentation de la méthode actionPerformed de l'interface ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start(); // Démarre le timer


        if(play) { // Si le jeu est en cours
            // Si la balle touche la raquette
            if(new Rectangle(ballposX, ballposY, 20,20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir; // Inverse la direction verticale de la balle

                // Augmente la vitesse balle + raquette à chaque fois que la balle touche la raquette.
                delay-= 2;
                timer.setDelay(delay);
            }

            // Boucle à travers les briques de la carte pour détecter les collisions avec la balle
            A: for(int i = 0; i<map.map.length; i++) {
                for(int j = 0; j<map.map[0].length; j++) {
                    // Vérifie si la brique est toujours présente sur la carte
                    if(map.map[i][j] > 0 ) {
                        // Calcule les coordonnées et les dimensions de la brique
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        // Crée un objet Rectangle pour la brique, la balle et la zone d'impact
                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;

                        // Vérifie si la balle a touché la brique
                        if(ballRect.intersects(brickRect)) {
                            // Met à jour la carte pour indiquer que la brique a été détruite
                            map.setBrickValue(0, i, j);
                            // Met à jour le nombre total de briques restantes sur la carte
                            totalBricks--;
                            // Augmente le score du joueur
                            score += 5;

                            // Calcule la direction de la balle après l'impact
                            if (ballposX + 19 <= brickRect.x || ballposX +1 >= brickRect.x + brickRect.width) {
                                ballXdir = -ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }
                            // Sort de la boucle pour éviter de gérer les collisions avec d'autres briques
                            break A;
                        }
                    }
                }
            }

            // Met à jour la position de la balle en ajoutant la direction horizontale et verticale
            ballposX += ballXdir;
            ballposY += ballYdir;

            if(ballposX < 0 ) { // Si la balle touche la bordure gauche
                ballXdir =- ballXdir; // Inverse la direction horizontale de la balle
            }
            if(ballposY < 0 ) { // Si la balle touche la bordure supérieure
                ballYdir =- ballYdir; // Inverse la direction verticale de la balle
            }
            if(ballposX > 670 ) { // Si la balle touche la bordure droite
                ballXdir =- ballXdir; // Inverse la direction horizontale de la balle
            }
        }

        repaint(); // Redessine la balle et la raquette
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
            if (playerX >= 595) {
                playerX = 595;
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

