import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLogic {


    public void shuffleTiles(List<Integer> tileNumbers) {
        Collections.shuffle(tileNumbers);
    }

    private List<Integer> inOrderList;

    public GameLogic() {
        createInOrderList();
    }

    //Skapar en lista i nummerordning med 0 sist
    public void createInOrderList() {
        List<Integer> inOrderList = new ArrayList<>();
        for (int i = 1; i < GameScreen.getNumTiles(); i++) {
            inOrderList.add(i);
        }
        inOrderList.add(0);
        this.inOrderList = inOrderList;
    }

    //Jämför tileList med en lista i nummerordning
    public boolean checkNumericalOrder(List<Integer> tileNumbers) {
        return inOrderList.equals(tileNumbers);
    }

    //undersöker om brickan kan byta plats med den tomma brickan
    public boolean tileCanBeSwapped(int clickedPosition, int zeroPosition) {
        boolean swappable = false;

        for (int i = 1; i < GameScreen.getNumTiles() / GameScreen.getGridSize(); i++) {
            if ((clickedPosition == i * GameScreen.getGridSize() && zeroPosition == clickedPosition - 1) ||
                    (zeroPosition == i * GameScreen.getGridSize() && clickedPosition == zeroPosition - 1)) {
                return swappable;
            }
        }

        if (clickedPosition == zeroPosition + 1 || clickedPosition == zeroPosition + GameScreen.getGridSize() ||
                clickedPosition == zeroPosition - 1 || clickedPosition == zeroPosition - GameScreen.getGridSize()) {
            swappable = true;
        }
        return swappable;
    }
}
