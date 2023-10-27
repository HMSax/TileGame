import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameScreen extends JFrame {
    // Konstanta variabler för storleken på spelbrädet
    private static final int GRID_SIZE = 4;
    private static final int NUM_TILES = GRID_SIZE * GRID_SIZE;

    // Konstruktor för klassen "GameScreen"
    public GameScreen() {

        setTitle("Puzzle Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Skapar en layout för fönstret
        setLayout(new BorderLayout());

        // Skapar en ruta för spelbrädet med ett grid layout
        JPanel puzzlePanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        createGrid(puzzlePanel); // Skapa spelbrädet
        add(puzzlePanel, BorderLayout.CENTER); // Lägg till spelbrädet i mitten av fönstret

        // Skapar en knapp med texten "Nytt spel"
        JButton newGameButton = new JButton("Nytt spel");
        add(newGameButton, BorderLayout.SOUTH); // Lägger till knappen längst ner i fönstret
    }

    // Metod för att skapa spelbrädet
    private void createGrid(JPanel panel) {
        // Skapa en lista med nummer för brickorna (0 till 15)
        ArrayList<Integer> tileNumberList = new ArrayList<>();
        for (int i = 0; i < NUM_TILES; i++) {
            tileNumberList.add(i);
        }

        // Blanda ordningen på brickorna slumpmässigt
        Collections.shuffle(tileNumberList);

        // Skapa och lägg till knappar för varje bricka i panelen
        for (int i = 0; i < NUM_TILES; i++) {
            int number = tileNumberList.get(i);
            JButton button = createTile(number);
            panel.add(button);
        }
    }

    // Metod för att skapa en bricka (knapp) med ett visst nummer
    private JButton createTile(int number) {
        String buttonText = "";
        if (number != 0) {
            buttonText = Integer.toString(number);
        }
        return new JButton(buttonText);
    }
}
