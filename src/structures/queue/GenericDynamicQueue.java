package structures.queue;
import structures.list.Node;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Código referente a uma fila genérica que cresce de forma dinâmica.
 *
 * @author Vinícius Nunes de Andrade e Maria Eduarda Campos
 * @since 29-05-2025
 * @version 2
 */
public class GenericDynamicQueue<T> implements Queueable<T>, Iterable<T> {
    private Node<T> headPointer;
    private Node<T> tailPointer;
    private int amount;
    private int size;

    public GenericDynamicQueue(){
        this(10);
    }

    public GenericDynamicQueue(int size) {
        this.amount = 0;
        this.size = size;
        tailPointer = null;
        headPointer = null;
    }
    /**
     * Método que enfileira um dado na fila.
     * @param element elemento a ser enfileirado.
     *
     * @throws Exception se a fila estiver cheia.
     */
    @Override
    public void enqueue(T element) throws Exception{
        if (isFull())
            throw new Exception("Queue is Full!");
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            tailPointer = newNode;
            headPointer = newNode;
        }
        newNode.setPrevious(tailPointer);
        tailPointer.setNext(newNode);
        tailPointer = newNode;
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
        if (isEmpty())
            throw new NoSuchElementException("Queue is Empty!");
        return headPointer.getElement();
    }

    /**
     * Método que atualiza o dado presente no início da fila.
     * @param element dado que substituirá o valor atual no início da fila.
     *
     * @throws NoSuchElementException se a fila estiver vazia.
     */
    @Override
    public void updateFirst(T element) throws NoSuchElementException{
        if (isEmpty())
            throw new NoSuchElementException("Queue is Empty!");
        headPointer.setElement(element);
    }

    /**
     * Método que atualiza o dado presente no fim da fila.
     * @param element dado que substituirá o valor atual no final da fila.
     *
     * @throws NoSuchElementException se a fila estiver vazia.
     */
    @Override
    public void updateLast(T element) throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException("Queue is Empty!");
        tailPointer.setElement(element);
    }

    /**
     * Método que desenfileira o dado no início da fila.
     * @return dado desenfileirado.
     *
     * @throws NoSuchElementException se a fila estiver vazia.
     */
    @Override
    public T dequeue() throws NoSuchElementException{
        if (isEmpty())
            throw new NoSuchElementException("Queue is Empty!");
        T inicialElement = headPointer.getElement();
        headPointer = headPointer.getNext();
        amount--;
        if (!isEmpty())
            headPointer.setPrevious(null);
        else
            tailPointer = null;

        return inicialElement;
    }

    /**
     * Método que imprime os dados da fila.
     * @return String contendo os dados da fila.
     */
    @Override
    public String print() {
        String aux = "[";
        Node<T> auxPointer = headPointer;
        for (int i = 0; i < amount; i++) {
            aux += auxPointer.getElement();
            if (i != amount - 1)
                aux += ", ";
            auxPointer = auxPointer.getNext();
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
        return amount == size;
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

    /**
     * Retorna o tamanho atual da lista.
     * @return o tamanho da lista.
     */
    public int size() {
        return amount;
    }

    /**
     * Retorna um iterador que percorre sequencialmente os elementos da estrutura.
     * @return um objeto interator
     * @throws NoSuchElementException se next() for chamado e não houver mais
     *                                elementos
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = headPointer;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T value = current.getElement();
                current = current.getNext();
                return value;
            }
        };
    }
}