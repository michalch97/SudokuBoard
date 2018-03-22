public class SudokuBoard {

    private SudokuField[] board = new SudokuField[81];

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
        return board[index].getFieldValue();
    }
    
    public SudokuField getField(int index) {
        return board[index];
    }

    public void set(int x, int y, int value) {
        set(getIndex(x, y), value);
    }
    
    public void set(int index, int value) {
        board[index] = new SudokuField(value);
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
            if (board[i] == null) {
                System.out.print(0);
            } else {
                System.out.print(board[i].getFieldValue() + " ");
            }
            if ((i + 1) % 3 == 0) {
                System.out.print("| ");
            }
        }
        System.out.println();
        System.out.println("-------------------------");
    }

    public void setNull(int i) {
        board[i] = null;
    }

    public boolean checkNull(int x, int y) {
        int index = (x - 1) + (y - 1) * 9;
        if (board[index] != null) {
            return true;
        } else {
            return false;
        }
    }
}
