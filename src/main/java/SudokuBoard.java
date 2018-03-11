import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// https://www.codeproject.com/Articles/23206/Sudoku-Algorithm-Generates-a-Valid-Sudoku-in
public class SudokuBoard {
    private Field[] board;
    private List<List<Integer>> availableNumbersList;
    private int counter;
    Random rand;

    public void fillBoard() {
        board = new Field[81];
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
            if (!availableNumbersList.get(counter).isEmpty()) {
                int k;
                if (availableNumbersList.get(counter).size() == 1) {
                    k = 0;
                } else {
                    k = rand.nextInt(availableNumbersList.get(counter).size() - 1);
                }
                int z = availableNumbersList.get(counter).get(k);
                Field tempField = new Field(counter, z);
                if (!conflict(board, tempField)) {
                    board[counter] = tempField;
                    availableNumbersList.get(counter).remove(k);
                    counter++;
                } else {
                    availableNumbersList.get(counter).remove(k);
                }
            } else {
                for (int j = 1; j <= 9; j++) {
                    availableNumbersList.get(counter).add(j);
                }
                board[counter - 1] = null;
                counter--;
            }
        }
    }

    private boolean conflict(final Field[] allFields, final Field field) {
        for (Field f : allFields) {
            if (f != null) {
                if ((f.getxAxis() != 0 && (f.getxAxis() == field.getxAxis())) || (f.getyAxis() != 0 && (f.getyAxis() == field.getyAxis())) || (f.getSector() != 0 && (f.getSector() == field.getSector()))) {
                    if (f.getValue() == field.getValue()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void draw() {
        for (int i = 0; i < 81; i++) {
            if (i % 9 == 0) {
                System.out.println();
                if (i % 27 == 0) {
                    System.out.println("-------------------------");
                    System.out.print("| ");
                } else {
                    System.out.print("| ");
                }
            }
            if (board[i] == null) {
                System.out.print(0);
            } else {
                System.out.print(board[i].getValue() + " ");
            }
            if ((i + 1) % 3 == 0) {
                System.out.print("| ");
            }
        }
        System.out.println();
        System.out.println("-------------------------");
    }

    public Field[] getBoard() {
        return board;
    }
}
