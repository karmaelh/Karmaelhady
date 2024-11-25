import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame {
    public StartWindow(Runnable onStart) {
        // Configurer la fenêtre
        setTitle("Start Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centre la fenêtre à l'écran
        setLayout(new BorderLayout());

        // Créer un label pour le titre
        JLabel titleLabel = new JLabel("Bienvenue dans le Jeu du Dongeon de Karma et Marie !!!!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.CENTER);

        // Créer un bouton Start
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.PLAIN, 18));
        add(startButton, BorderLayout.SOUTH);

        // Ajouter un écouteur d'événements au bouton
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la fenêtre de démarrage
                onStart.run(); // Lance le jeu
            }
        });
    }
}
