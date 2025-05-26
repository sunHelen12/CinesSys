import java.util.NoSuchElementException;

public interface Listable<T> {

    public void insert(int index, T element) throws Exception;
    public void append(T element) throws Exception;

    public T get(int index) throws NoSuchElementException, IndexOutOfBoundsException;
    public Object[] getAll() throws NoSuchElementException;
    public boolean contains(T element);

    public void update(int index, T element) throws NoSuchElementException, IndexOutOfBoundsException;

    public T remove(int index) throws NoSuchElementException, IndexOutOfBoundsException;
    public void clear();

    public boolean isFull();
    public boolean isEmpty();
    public String print();
}