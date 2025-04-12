import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Buat form pembuka
            JDialog welcomeDialog = new JDialog();
            welcomeDialog.setTitle("Flappy Bird");
            welcomeDialog.setSize(360, 640);
            welcomeDialog.setLocationRelativeTo(null);
            welcomeDialog.setModal(true);

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10, 10, 10, 10);

            JLabel label = new JLabel("Welcome to Flappy Bird!");
            label.setFont(new Font("Arial", Font.BOLD, 18));
            panel.add(label, gbc);

            gbc.gridy++;
            JButton startButton = new JButton("Start Game");
            startButton.setFont(new Font("Arial", Font.PLAIN, 16));
            panel.add(startButton, gbc);

            welcomeDialog.add(panel);

            // Klik tombol mulai
            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    welcomeDialog.dispose();

                    // Buka game setelah ditutup
                    JFrame frame = new JFrame("Flappy Bird");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(360, 640);
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);

                    FlappyBird flappyBird = new FlappyBird();
                    frame.add(flappyBird);
                    frame.pack();
                    flappyBird.requestFocus();
                    frame.setVisible(true);
                }
            });

            welcomeDialog.setVisible(true);
        });
    }
}
