import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    File serializationFile;
    
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    
    public FileSudokuBoardDao(String fileName) throws IOException {
        serializationFile = new File(fileName);
        serializationFile.createNewFile();
    }
    
    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoard = null;
        
        try {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (objectInputStream == null) {
                FileInputStream fileInputStream = new FileInputStream(serializationFile);
                objectInputStream = new ObjectInputStream(fileInputStream);
            }

            sudokuBoard = (SudokuBoard)objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard obj) {
        try {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (objectOutputStream == null) {
                FileOutputStream fileOutputStream = new FileOutputStream(serializationFile);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            }
            
            objectOutputStream.writeObject(obj);
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
