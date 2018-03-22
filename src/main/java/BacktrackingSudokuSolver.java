import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// https://www.codeproject.com/Articles/23206/Sudoku-Algorithm-Generates-a-Valid-Sudoku-in
public class BacktrackingSudokuSolver implements SudokuSolver {
    private int counter;
    private Random rand;
    private SudokuBoard sudokuBoard;
    private List<List<Integer>> availableNumbersList;

    @Override
    public void solve(final SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
        fillBoard();
    }

    private void fillBoard() {
        availableNumbersList = new LinkedList<List<Integer>>();
        counter = 0;
        rand = new Random();
        
        for (int i = 1; i <= 81; i++) {
            List<Integer> availableNumbers = new LinkedList<Integer>();
            for (int j = 1; j <= 9; j++) {
                availableNumbers.add(j);
            }
            availableNumbersList.add(availableNumbers);
        }
        
        while (counter != 81) {
            int y = counter / 9 + 1;
            int x = counter - 9 * y + 10;
            
            if (!availableNumbersList.get(counter).isEmpty()) {
                int k;
                
                if (availableNumbersList.get(counter).size() == 1) {
                    k = 0;
                } else {
                    k = rand.nextInt(availableNumbersList.get(counter).size() - 1);
                }
                
                int z = availableNumbersList.get(counter).get(k);

                sudokuBoard.set(x, y, z);
                if (sudokuBoard.checkBoard()) {
                    availableNumbersList.get(counter).remove(k);
                    counter++;
                } else {
                    availableNumbersList.get(counter).remove(k);
                }
            } else {
                for (int j = 1; j <= 9; j++) {
                    availableNumbersList.get(counter).add(j);
                }
                sudokuBoard.setNull(counter);
                sudokuBoard.setNull(counter - 1);
                counter--;
            }
        }
    }
}
