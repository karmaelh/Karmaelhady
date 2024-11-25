import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception {
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(1200, 1600);
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DynamicSprite hero = new DynamicSprite(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);

        SolidSprite trap1 = new SolidSprite(400, 300,
                ImageIO.read(new File("./img/trap.png")), 64, 64);
        SolidSprite trap2= new SolidSprite(500, 50,
                ImageIO.read(new File("./img/trap.png")), 64, 64);
        SolidSprite trap3= new SolidSprite(500, 600,
                ImageIO.read(new File("./img/trap.png")), 64, 64);
        SolidSprite trap4= new SolidSprite(90, 256,
                ImageIO.read(new File("./img/trap.png")), 64, 64);
        SolidSprite trap5= new SolidSprite(100, 600,
                ImageIO.read(new File("./img/trap.png")), 64, 64);

        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine(this);
        gameEngine = new GameEngine(hero);

        // Lier les composants entre eux
        renderEngine.setGameEngine(gameEngine);
        gameEngine.setRenderEngine(renderEngine);

        Timer renderTimer = new Timer(30, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(30, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(30, (time) -> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        renderEngine.addToRenderList(trap1);  // Ajouter le piège dans le moteur de rendu
        renderEngine.addToRenderList(trap2);  // Ajouter le piège dans le moteur de rendu
        renderEngine.addToRenderList(trap3);  // Ajouter le piège dans le moteur de rendu
        renderEngine.addToRenderList(trap4);  // Ajouter le piège dans le moteur de rendu
        renderEngine.addToRenderList(trap5);  // Ajouter le piège dans le moteur de rendu


        physicEngine.addToMovingSpriteList(hero);
        physicEngine.addEnemy(trap1);  // Ajouter le piège comme ennemi
        physicEngine.addEnemy(trap2);  // Ajouter le piège comme ennemi
        physicEngine.addEnemy(trap3);  // Ajouter le piège comme ennemi
        physicEngine.addEnemy(trap5);  // Ajouter le piège comme ennemi
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main(String[] args) {
        // Afficher la fenêtre de démarrage
        SwingUtilities.invokeLater(() -> {
            StartWindow startWindow = new StartWindow(() -> {
                // Lorsque le bouton "Start" est cliqué, lancer le jeu
                try {
                    new Main();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            startWindow.setVisible(true);
        });
    }

    public void showGameOverPanel() {
        int choice = JOptionPane.showOptionDialog(
                displayZoneFrame,
                "Game Over! Voulez-vous recommencer ou quitter ?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Recommencer", "Quitter"},
                "Quitter"
        );


        if (choice == JOptionPane.YES_OPTION) {
            try {
                new Main();  // Redémarre le jeu
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.exit(0);  // Quitte le jeu
        }
    }




}




