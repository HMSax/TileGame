import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameScreen extends JFrame {

    // Konstanta variabler för storleken på spelbrädet
    private static final int GRID_SIZE = 4;
    private static final int NUM_TILES = GRID_SIZE * GRID_SIZE;

    // "Container" som håller alla spelplattor
    private final JPanel tileContainer;

    private final GameLogic gameLogic = new GameLogic();

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
        tileContainer.removeAll();
        tileNumbers.clear(); // Rensa och återskapa listan

        // Skapa en lista med nummer för varje bricka
        for (int i = 0; i < NUM_TILES; i++) {
            tileNumbers.add(i);
        }

        // Använder GameLogic för att blanda siffrorna
        gameLogic.shuffleTiles(tileNumbers);

        // Lägger till brickorna till containern
        for (Integer number : tileNumbers) {
            tileContainer.add(createTile(number));
        }

        tileContainer.revalidate();
        tileContainer.repaint();
    }


    private void newGame() {
        createGrid();  // Initialiserar spelbrädet igen för nytt spel
    }

    private void updateGrid() { //Uppdaterar spelbrädet
        tileContainer.removeAll();
        for (Integer number : tileNumbers) {
            tileContainer.add(createTile(number));
        }
        tileContainer.revalidate();
        tileContainer.repaint();
    }

    private JButton createTile(int number) {          //Skapar upp en knapp
        String buttonText = "";
        if (number != 0) {
            buttonText = Integer.toString(number);

        }
        JButton newJButton = new JButton(buttonText);

        newJButton.setOpaque(true);
        newJButton.setBorderPainted(true); // Gränsen är alltid synlig
        newJButton.setFocusPainted(false);
        newJButton.setContentAreaFilled(true);

        // Sätt bakgrund och ram
        newJButton.setBackground(Color.WHITE);
        newJButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        // MouseListener för att ändra färg vid hover
        String finalButtonText = buttonText;
        newJButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int zeroPosition = tileNumbers.indexOf(0);
                int currentTilePosition = tileNumbers.indexOf(number);

                if (!finalButtonText.isEmpty()) {
                    if (gameLogic.tileCanBeSwapped(currentTilePosition, zeroPosition)) {
                        // Grön om brickan kan flyttas
                        newJButton.setBackground(new Color(131, 191, 137)); // Grön färg
                    } else {
                        // Duvblå om brickan inte kan flyttas
                        newJButton.setBackground(new Color(175, 198, 233)); // Duvblå färg
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newJButton.setBackground(Color.WHITE); // Vit igen när musen inte är över
            }
        });

        newJButton.addActionListener(l -> {
            if (!Objects.equals(l.getActionCommand(), "")) {
                int clickedPosition = tileNumbers.indexOf(Integer.parseInt(l.getActionCommand()));
                int zeroPosition = tileNumbers.indexOf(0);
                if (gameLogic.tileCanBeSwapped(clickedPosition, zeroPosition)) {
                    Collections.swap(tileNumbers, zeroPosition, clickedPosition);
                    updateGrid();
                }
            }
        });

        return newJButton;
    }

    public static int getGridSize() {
        return GRID_SIZE;
    }

    public static int getNumTiles() {
        return NUM_TILES;
    }
}
