import javax.swing.*;

public class GameMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameScreen game = new GameScreen();
            game.setVisible(true);
        });
    }
}