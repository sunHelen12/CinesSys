package structures.queue;
import java.util.NoSuchElementException;

/**
 * Código referente a uma fila genérica estática.
 *
 * @author Vinícius Nunes de Andrade
 * @since 25-05-2025
 * @version 1
 */
public class CircularStaticQueue<T> implements Queueable<T> {
    private T[] data;
    private int inicialPointer;
    private int finalPointer;
    private int amount;

    public CircularStaticQueue (){
        this(10);
    }

    public CircularStaticQueue(int size) {

        this.data = (T[]) new Object[size];
        this.inicialPointer = 0;
        this.finalPointer = -1;
        this.amount = 0;
    }
    /**
     * Método que enfileira um dado na fila.
     * @param element elemento a ser enfileirado.
     *
     * @throws Exception se a fila estiver cheia.
     */
    @Override
    public void enqueue(T element) throws Exception{
        if(isFull())
            throw new Exception("Queue is full!");
        finalPointer = (finalPointer +1)%data.length;
        data[finalPointer] = element;
        amount++;
    }

    /**
     * Método que mostra o dado presente no início da fila.
     * @return dado no ínicio da fila.
     *
     * @throws NoSuchElementException se a fila estiver vazia.
     */
    @Override
    public T front() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException("Queue is empty!");
        return data[inicialPointer];
    }

    /**
     * Método que atualiza o dado presente no início da fila.
     * @param element dado que substituirá o valor atual no início da fila.
     *
     * @throws NoSuchElementException se a fila estiver vazia.
     */
    @Override
    public void updateFirst(T element) throws NoSuchElementException{
        if(isEmpty())
            throw new NoSuchElementException("Queue is empty!");
        data[inicialPointer] = element;
    }

    /**
     * Método que atualiza o dado presente no fim da fila.
     * @param element dado que substituirá o valor atual no final da fila.
     *
     * @throws NoSuchElementException se a fila estiver vazia.
     */
    @Override
    public void updateLast(T element) throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException("Queue is empty!");
        data[finalPointer] = element;
    }

    /**
     * Método que desenfileira o dado no início da fila.
     * @return dado desenfileirado.
     *
     * @throws NoSuchElementException se a fila estiver vazia.
     */
    @Override
    public T dequeue() throws NoSuchElementException{
        if(isEmpty())
            throw new NoSuchElementException("Queue is full!");
        T aux = data[inicialPointer];
        inicialPointer = (inicialPointer+1) % data.length;
        amount--;
        return aux;
    }

    /**
     * Método que imprime os dados da fila.
     * @return String contendo os dados da fila.
     */
    @Override
    public String print() {
        String aux = "[";
        for(int i = inicialPointer;i < amount + inicialPointer;i++){
            if (i == (amount+inicialPointer)-1){
                aux += data[i%data.length]; //% - Volta para o inicio, se alcançar o fim
            } else {
                aux += data[i] + ", ";
            }
        }
        return aux + "]";
    }

    /**
     * Verifica se a fila está cheia ou não.
     * @return true, se a fila estiver cheia,
     * false, se não.
     */
    @Override
    public boolean isFull() {
        return amount == data.length;
    }

    /**
     * Verifica se a fila está vazia ou não.
     * @return true, se a fila estiver vazia,
     * false, se não.
     */
    @Override
    public boolean isEmpty() {
        return amount == 0;
    }
}