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
        for (int i = 0; i < GameScreen.getNumTiles(); i++) {
            inOrderList.add(i);
        }
        inOrderList.add(0);
        this.inOrderList = inOrderList;
    }

    //Jämför tileList med en lista i nummerordning
    public boolean checkNumericalOrder(List<Integer> tileNumbers) {
        return inOrderList.equals(tileNumbers);
    }

}
