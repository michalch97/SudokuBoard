import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class SudokuBoard implements Serializable, Cloneable {
    private static final long serialVersionUID = -3644754526372586835L;
    
    private List<SudokuField> board = Arrays.asList(new SudokuField[81]);

    public SudokuBoard() {
        
    }
    
    public SudokuBoard(List<SudokuField> board) {
        if (board.size() != 81) {
            throw new IllegalArgumentException("Passed list of wrong size");
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

    public int get(int x, int y) {
        return get(getIndex(x, y));
    }

    public int get(int index) {
        return board.get(index).getFieldValue();
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
            if (board.get(i) == null) {
                System.out.print(0);
            } else {
                System.out.print(board.get(i).getFieldValue() + " ");
            }
            if ((i + 1) % 3 == 0) {
                System.out.print("| ");
            }
        }
        System.out.println();
        System.out.println("-------------------------");
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

    public Object clone() throws CloneNotSupportedException {
        SudokuBoard sudoku = new SudokuBoard(this.board);
        return sudoku;
    }
}
