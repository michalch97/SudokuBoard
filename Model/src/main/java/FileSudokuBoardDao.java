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
    public SudokuBoard read() {
        SudokuBoard sudokuBoard = null;
        
        try {
            FileInputStream fileInputStream = new FileInputStream(serializationFile);
            objectInputStream = new ObjectInputStream(fileInputStream);

            sudokuBoard = (SudokuBoard) objectInputStream.readObject();
            objectInputStream.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return sudokuBoard;
    }

    @Override
    public void write(final SudokuBoard obj) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(serializationFile);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
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
