import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    File serializationFile;

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public FileSudokuBoardDao(final String fileName) throws IOException {
        this(new File(fileName));
    }

    public FileSudokuBoardDao(final File file) throws IOException {
        serializationFile = file;
        serializationFile.createNewFile();
    }

    @Override
    public SudokuBoard read() throws Throwable {
        SudokuBoard sudokuBoard = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(serializationFile);
            objectInputStream = new ObjectInputStream(fileInputStream);

            sudokuBoard = (SudokuBoard) objectInputStream.readObject();
            objectInputStream.close();
        } catch (ClassNotFoundException e) {
            Throwable te = new SudokuException("Deserialization error: class not found");
            te.initCause(e);
            throw te;
        } catch (IOException e) {
            Throwable te = new SudokuException("Deserialization error: input/output error");
            te.initCause(e);
            throw te;
        }

        return sudokuBoard;
    }

    @Override
    public void write(final SudokuBoard obj) throws Throwable {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(serializationFile);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
        } catch (IOException e) {
            Throwable te = new SudokuException("Serialization error: input/output error");
            te.initCause(e);
            throw te;
        }
    }

    @Override
    public void close() throws Exception {
        if (objectOutputStream != null) {
            objectOutputStream.close();
        }

        if (objectInputStream != null) {
            objectInputStream.close();
        }
    }
}
