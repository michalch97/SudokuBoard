import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class SudokuBoard implements Serializable, Cloneable {
    private static final long serialVersionUID = -3644754526372586835L;
    private Random random = new Random();
    private List<SudokuField> board = Arrays.asList(new SudokuField[81]);
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public SudokuBoard() {

    }

    public SudokuBoard(final List<SudokuField> board) throws SudokuException {
        if (board.size() != 81) {
            throw new SudokuException("Passed list of wrong size");
        }

        SudokuField[] array = new SudokuField[81];
        board.toArray(array);
        this.board = Arrays.asList(array);
    }

    public boolean checkBoard() {
        final int BOARD_SIDE_LENGTH = 9;
        final int SECTOR_SIDE_LENGTH = 3;

        for (int i = 1; i <= BOARD_SIDE_LENGTH; i++) {
            if (!(getRow(i).verify() && getColumn(i).verify())) {
                return false;
            }
        }

        for (int i = 1; i <= SECTOR_SIDE_LENGTH; i++) {
            for (int j = 0; j < SECTOR_SIDE_LENGTH; j++) {
                if (!getBox(i * SECTOR_SIDE_LENGTH, j * SECTOR_SIDE_LENGTH).verify()) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkIfContainsEmptyFields() {
        for (SudokuField field : board) {
            if (field == null) {
                return true;
            }
        }

        return false;
    }

    public int get(int x, int y) {
        return get(getIndex(x, y));
    }

    public int get(int index) {
        if (board.get(index) != null) {
            return board.get(index).getFieldValue();
        } else {
            return 0;
        }
    }

    public SudokuField getField(int x, int y) {
        return getField(getIndex(x, y));
    }

    public SudokuField getField(int index) {
        return board.get(index);
    }

    public void set(int x, int y, int value) {
        set(getIndex(x, y), value);
    }

    public void set(int index, int value) {
        board.set(index, new SudokuField(value));
    }

    public void set(int index, int value, boolean isUserValue) {
        board.set(index, new SudokuField(value, isUserValue));
    }

    public SudokuRow getRow(int y) {
        return SudokuRow.createSudokuRow(board, y);
    }

    public SudokuColumn getColumn(int x) {
        return SudokuColumn.createSudokuColumn(board, x);
    }

    public SudokuBox getBox(int x, int y) {
        return SudokuBox.createSudokuBox(board, x, y);
    }

    public int getIndex(int x, int y) {
        return (x - 1) + (y - 1) * 9;
    }

    public void draw() {
        String line = "";
        for (int i = 0; i < 81; i++) {
            if (i % 9 == 0) {
                log.info(line + "\n");
                line = "";
                if (i % 27 == 0) {
                    log.info("-------------------------\n");
                    line += "| ";
                } else {
                    line += "| ";
                }
            }
            if (board.get(i) == null) {
                line += "  ";
            } else {
                line += (board.get(i).getFieldValue() + " ");
            }
            if ((i + 1) % 3 == 0) {
                line += "| ";
            }
        }
        log.info(line + "\n");
        log.info("-------------------------\n");
    }

    public void setNull(int i) {
        board.set(i, null);
    }

    public boolean checkNull(int x, int y) {
        int index = (x - 1) + (y - 1) * 9;
        if (board.get(index) != null) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return new ToStringBuilder(this).
                append("board", board).
                toString();
    }

    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        SudokuBoard rhs = (SudokuBoard) obj;
        EqualsBuilder builder = new EqualsBuilder();

        for (int i = 0; i < board.size(); i++) {
            builder.append(board.get(i), rhs.board.get(i));
        }

        return builder.isEquals();
    }

    public int hashCode() {
        // you pick a hard-coded, randomly chosen, non-zero, odd number
        // ideally different for each class
        HashCodeBuilder hashBuilder = new HashCodeBuilder(37, 57);

        for (int i = 0; i < board.size(); i++) {
            hashBuilder.append(board.get(i));
        }

        return hashBuilder.toHashCode();
    }

    public SudokuBoard clone() throws CloneNotSupportedException {
        SudokuBoard sudoku = new SudokuBoard();
        for (int i = 0; i < 81; i++) {
            sudoku.set(i, board.get(i).getFieldValue());
        }
        return sudoku;
    }

    public void prepareBoardToGame(final DifficultyLevel chosenDifficulty) {
        int fieldsToDelete = (chosenDifficulty.getValue() + 1) * 10;
        int[] indexesToDelete = new int[fieldsToDelete];
        int i = 0;
        while (i < fieldsToDelete) {
            final int index = random.nextInt(80);
            if (!IntStream.of(indexesToDelete).anyMatch(x -> x == index)) {
                indexesToDelete[i] = index;
                i++;
            }
        }
        for (int j = 0; j < fieldsToDelete; j++) {
            board.set(indexesToDelete[j], null);
        }
    }

    public Set<Integer> getIndexesOfUserIncorrectAnswers() {
        Set<Integer> indexes = new HashSet<Integer>();

        final int BOARD_SIDE_LENGTH = 9;
        final int SECTOR_SIDE_LENGTH = 3;

        for (int i = 1; i <= BOARD_SIDE_LENGTH; i++) {
            if (!getRow(i).verify()) {
                for (int j = 1; j <= BOARD_SIDE_LENGTH; j++) {
                    SudokuField field = getField(j, i);
                    if (field != null && field.getIsUserProvidedField() && field.isValid()) {
                        indexes.add(getIndex(j, i));
                    }
                }
            }
            if (!getColumn(i).verify()) {
                for (int j = 1; j <= BOARD_SIDE_LENGTH; j++) {
                    SudokuField field = getField(i, j);
                    if (field != null && field.getIsUserProvidedField() && field.isValid()) {
                        indexes.add(getIndex(i, j));
                    }
                }
            }
        }

        for (int i = 1; i <= SECTOR_SIDE_LENGTH; i++) {
            for (int j = 1; j <= SECTOR_SIDE_LENGTH; j++) {
                if (!getBox(i * SECTOR_SIDE_LENGTH, j * SECTOR_SIDE_LENGTH).verify()) {

                    for (int m = i * SECTOR_SIDE_LENGTH; m > i * SECTOR_SIDE_LENGTH - SECTOR_SIDE_LENGTH; m--) {
                        for (int n = j * SECTOR_SIDE_LENGTH; n > j * SECTOR_SIDE_LENGTH - SECTOR_SIDE_LENGTH; n--) {
                            if (getField(m, n) != null && getField(m, n).getIsUserProvidedField() && getField(m, n).isValid()) {
                                indexes.add(getIndex(m, n));
                            }
                        }
                    }
                }
            }
        }

        return indexes;
    }
}
