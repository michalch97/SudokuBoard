
public interface Dao<T> {
    T read() throws Throwable;

    void write(T obj) throws Throwable;
}
