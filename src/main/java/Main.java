public class Main {
    public static void main(final String[] args) {
        SudokuBoard sudokuBoard = new SudokuBoard();
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        sudokuSolver.solve(sudokuBoard);
        boolean boardState = sudokuBoard.checkBoard();
        if (boardState) {
            System.out.println("Tablica poprawna!");
        } else {
            System.out.println("Tablica zawiera bledy!");
        }
        sudokuBoard.draw();
    }
}
