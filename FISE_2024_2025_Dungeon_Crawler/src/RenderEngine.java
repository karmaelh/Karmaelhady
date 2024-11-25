import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList;
    private GameEngine gameEngine;

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<? extends Displayable> displayables) {
        for (Displayable displayable : displayables) {
            if (!renderList.contains(displayable)) {
                renderList.add(displayable);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Dessiner tous les objets
        for (Displayable renderObject : renderList) {
            renderObject.draw(g);
        }

        // Afficher les FPS
        if (gameEngine != null) {
            int fps = gameEngine.getFps();
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.setColor(Color.RED);
            g.drawString("FPS: " + fps, getWidth() - 100, 30); // Afficher les FPS en haut à droite
        }
    }

    @Override
    public void update() {
        this.repaint();
    }
}

