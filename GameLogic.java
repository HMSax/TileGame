import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    //Jämför tileList med en Lista i nummerordning
    public boolean checkNumericalOrder(List<Integer> tileNumbers) {
        List<Integer> inOrderList = new ArrayList<>();
        for (int i = 1; i < GameScreen.getNumTiles(); i++) {
            tileNumbers.add(i);
        }
        inOrderList.add(0);
        return inOrderList.equals(tileNumbers);
    }
}
