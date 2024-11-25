import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    private DynamicSprite hero;
    private boolean gameOver = false;
    private RenderEngine renderEngine;
    private long lastFpsTime; // Temps pour calculer les FPS
    private int fps; // Nombre d'images par seconde
    private int frameCount; // Nombre de frames en une seconde

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
        this.lastFpsTime = System.nanoTime();
        this.fps = 0;
        this.frameCount = 0;
    }

    public void setRenderEngine(RenderEngine renderEngine) {
        this.renderEngine = renderEngine;
    }

    @Override
    public void update() {
        frameCount++;
        long currentTime = System.nanoTime();

        // Calculer les FPS toutes les secondes
        if (currentTime - lastFpsTime >= 1_000_000_000L) { // 1 seconde en nanosecondes
            fps = frameCount;
            frameCount = 0;
            lastFpsTime = currentTime;
        }

        if (gameOver) return;

    }

    public int getFps() {
        return fps;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver) return;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z -> hero.setDirection(Direction.NORTH);
            case KeyEvent.VK_S -> hero.setDirection(Direction.SOUTH);
            case KeyEvent.VK_Q -> hero.setDirection(Direction.WEST);
            case KeyEvent.VK_D -> hero.setDirection(Direction.EAST);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}

