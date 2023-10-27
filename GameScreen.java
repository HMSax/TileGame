import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameScreen extends JFrame {

    // Konstanta variabler för storleken på spelbrädet
    private static final int GRID_SIZE = 4;
    private static final int NUM_TILES = GRID_SIZE * GRID_SIZE;

    // "Container" som håller alla spelplattor
    private final JPanel tileContainer;

    //Lista för brickornas nummer och ordning
    List<Integer> tileNumbers = new ArrayList<>();

    public GameScreen() {
        setTitle("Tile Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tileContainer = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        createGrid();  // Initialiserar spelbrädet vid start
        add(tileContainer, BorderLayout.CENTER);

        // Knapp för nytt spel
        JButton newGameButton = new JButton("Nytt spel");
        newGameButton.addActionListener(e -> newGame()); //Händelsehanterare för knappen
        add(newGameButton, BorderLayout.SOUTH);
    }

    private void createGrid() {
        // Tar bort tidigare brickor från containern
        tileContainer.removeAll();

        // Skapa en lista med nummer för varje bricka
        for (int i = 0; i < NUM_TILES; i++) {
            tileNumbers.add(i);
        }

        // Blandar siffrorna
        Collections.shuffle(tileNumbers);

        // Lägger till brickorna till containern
        for (Integer number : tileNumbers) {
            tileContainer.add(createTile(number));
        }

        // Uppdaterar containern för att visa nya brickor
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
    public static int getGridSize() {
        return GRID_SIZE;
    }

    public static int getNumTiles() {
        return NUM_TILES;
    }
}
