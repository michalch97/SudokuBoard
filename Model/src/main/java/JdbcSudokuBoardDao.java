import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:sudoku.db";
    private Connection connection;
    private Statement statement;
    private String boardName;
    private String fieldsValues;
    private String userProvidedFields;

    public JdbcSudokuBoardDao(final String boardName) throws Throwable {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            Throwable te = new SudokuException("ERROR: JDBC driver not found");
            te.initCause(e);
            throw te;
        }

        try {
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
        } catch (SQLException e) {
            Throwable te = new SudokuException("ERROR: connection problem");
            te.initCause(e);
            throw te;
        }

        String createTable = "CREATE TABLE IF NOT EXISTS SudokuBoards(board_name varchar(40) PRIMARY KEY, fields_values varchar(81), user_provided_fields varchar(81))";
        try {
            statement.execute(createTable);
        } catch (SQLException e) {
            Throwable te = new SudokuException("ERROR: problem during creating the table");
            te.initCause(e);
            throw te;
        }

        this.boardName = boardName;

    }

    @Override
    public SudokuBoard read() throws Throwable {
        List<SudokuField> board = Arrays.asList(new SudokuField[81]);
        String selectQuery = "SELECT fields_values, user_provided_fields FROM SudokuBoards WHERE board_name = \'" + boardName + "\'";

        ResultSet resultSet = statement.executeQuery(selectQuery);
        fieldsValues = resultSet.getString("fields_values");
        userProvidedFields = resultSet.getString("user_provided_fields");
        for (int i = 0; i < 81; i++) {
            board.set(i, new SudokuField(Integer.parseInt(fieldsValues.substring(i, i + 1)), Integer.parseInt(userProvidedFields.substring(i, i + 1)) == 1 ? true : false));
        }
        return new SudokuBoard(board);
    }

    @Override
    public void write(final SudokuBoard obj) throws Throwable {
        fieldsValues = "";
        userProvidedFields = "";
        for (int i = 0; i < 81; i++) {
            fieldsValues += obj.getField(i) == null ? "0" : Integer.toString(obj.getField(i).getFieldValue());
            userProvidedFields += (obj.getField(i) == null || obj.getField(i).getIsUserProvidedField()) ? "1" : "0";
        }
        String insert = "INSERT INTO SudokuBoards VALUES(\'" + boardName + "\', \'" + fieldsValues + "\', \'" + userProvidedFields + "\')";
        try {
            statement.execute(insert);
        } catch (SQLException e) {
            Throwable te = new SudokuException("ERROR: problem during inserting the values into the database");
            te.initCause(e);
            throw te;
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            Throwable te = new SudokuException("ERROR: problem during closing connection");
            te.initCause(e);
            try {
                throw te;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public boolean checkIfExist(final String name) throws Throwable {
        String selectQuery = "SELECT * FROM SudokuBoards WHERE board_name = \'" + name + "\'";
        ResultSet resultSet = statement.executeQuery(selectQuery);
        return resultSet.next();
    }
}
