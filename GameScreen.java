import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameScreen extends JFrame {
    private static final int GRID_SIZE = 4;
    private static final int NUM_TILES = GRID_SIZE * GRID_SIZE;
    private final JPanel tileContainer;

    public GameScreen() {
        setTitle("Tile Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

       tileContainer = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        createGrid();  // Initialiserar spelbrädet vid start
        add(tileContainer, BorderLayout.CENTER);

        JButton newGameButton = new JButton("Nytt spel");
        newGameButton.addActionListener(e -> newGame());
        add(newGameButton, BorderLayout.SOUTH);
    }

    private void createGrid() {
        tileContainer.removeAll();
        List<Integer> tileNumbers = new ArrayList<>();
        for (int i = 0; i < NUM_TILES; i++) {
            tileNumbers.add(i);
        }

        Collections.shuffle(tileNumbers);

        for (Integer number : tileNumbers) {
            tileContainer.add(createTile(number));
        }

        tileContainer.revalidate();
        tileContainer.repaint();
    }


    private void newGame() {
        createGrid();  // Initialiserar spelbrädet igen för nytt spel
    }

    private JButton createTile(int number) {
        String buttonText = "";
        if (number != 0) {
            buttonText = Integer.toString(number);
        }
        return new JButton(buttonText);
    }
}
