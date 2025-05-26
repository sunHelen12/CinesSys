import java.util.NoSuchElementException;

public interface Queueable<T> {
    public void enqueue(T element) throws Exception;

    public T front() throws NoSuchElementException;

    public void updateFirst(T element) throws NoSuchElementException;

    public void updateLast(T element) throws NoSuchElementException;

    public T dequeue() throws NoSuchElementException;

    public String print();                  

    public boolean isFull();               

    public boolean isEmpty();             
}