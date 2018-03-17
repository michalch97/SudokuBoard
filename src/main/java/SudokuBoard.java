public class SudokuBoard {

    private Field[] board = new Field[81];

    public boolean checkBoard() {
        for (Field f1 : board) {
            for (Field f2 : board) {
                if (f1 != f2) {
                    if ((f1.getXAxis() == f2.getXAxis()) || (f1.getYAxis() == f2.getYAxis()) || (f1.getSector() == f2.getSector())) {
                        if (f1.getValue() == f2.getValue()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public int get(int x, int y) {
        int index = (x - 1) + (y - 1) * 9;
        return get(index);
    }
    
    public int get(int index) {
        return board[index].getValue();
    }
    
    public Field getField(int index) {
        return board[index];
    }

    public void set(int x, int y, int value) {
        int index = (x - 1) + (y - 1) * 9;
        set(index, value);
    }
    
    public void set(int index, int value) {
        board[index] = new Field(index, value);
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
