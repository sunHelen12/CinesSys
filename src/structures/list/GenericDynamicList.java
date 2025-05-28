package structures.list;

import java.util.NoSuchElementException;
/**
 * Código referente a lista genérica que cresce
 * de forma dinâmica.
 *
 * @author Vinícius Nunes de Andrade
 * @since 25-05-2025
 * @version 1
 */
public class GenericDynamicList<T> implements Listable<T> {
    private Node<T> inicialPointer;
    private Node<T> finalPointer;
    private int amount;
    private int max;

    public GenericDynamicList(){
        this(1000);
    }

    public GenericDynamicList(int max){
        inicialPointer = null;
        finalPointer = null;
        this.max = max;
        this.amount = 0;
    }
    /**
     * Insere um dado no índice inserido pelo usuário.
     * @param index lugar da lista para inserir o dado.
     * @param element a ser inserido.
     */
    @Override
    public void insert(int index, T element) throws Exception{
        if(isFull())
            throw new Exception("List is full!");
        if(index < 0 || index > amount)
            throw new IndexOutOfBoundsException("Invalid index!");
        Node<T> newNode = new Node<>();
        newNode.setElement(element);
        Node<T> previousPointer = null;
        Node<T> nextPointer = inicialPointer;

        for (int i = 0; i < index; i++) {
            previousPointer = nextPointer;
            nextPointer = nextPointer.getNext();
        }
        if (previousPointer != null) {
            previousPointer.setNext(newNode);
        } else {
            inicialPointer = newNode;
        }

        if (nextPointer != null) {
            nextPointer.setPrevious(newNode);
        } else {
            finalPointer = newNode;
        }

        newNode.setPrevious(previousPointer);
        newNode.setNext(nextPointer);
        amount++;
    }

    /**
     * Anexa um dado ao fim da lista.
     * @param element dado a ser anexado.
     *
     * @throws Exception se a lista estiver cheia.
     */
    @Override
    public void append(T element) throws Exception{
        if(isFull())
            throw new Exception("List is full!");
        Node<T> newNode = new Node<>(element);
        newNode.setPrevious(finalPointer);
        if (!isEmpty())
            finalPointer.setNext(newNode);
        else
            inicialPointer = newNode;
        newNode.setPrevious(finalPointer);
        finalPointer = newNode;
        amount++;
    }

    /**
     * Pega o dado presente no índice indicado.
     * @param index índice a ser procurado.
     * @return dado presente no índice.
     *
     * @throws NoSuchElementException se a lista estiver cheia.
     * @throws IndexOutOfBoundsException se o índice for inválido.
     */
    @Override
    public T get(int index) throws NoSuchElementException, IndexOutOfBoundsException {
        if(isEmpty())
            throw new NoSuchElementException("Lista vazia");
        if(index <0 || index >= amount)
            throw new IndexOutOfBoundsException("Índice inválido!");
        Node<T> newNode = inicialPointer;
        for (int i = 0; i < index; i++) {
            newNode =  newNode.getNext();
        }
        return newNode.getElement();
    }

    /**
     * Seleciona todos os dados presentes na lista.
     * @return array contendo os dados da lista.
     *
     * @throws NoSuchElementException se a fila estiver vazia.
     */
    @Override
    public Object[] getAll() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException("List is empty!");
        Node<T> newNode = inicialPointer;
        Object[] aux =new Object[amount];
        for (int i = 0; i < amount; i++) {
            aux[i] = newNode.getElement();
            newNode = newNode.getNext();
        }
        return aux;
    }

    /**
     * Verifica se um dado está presente ou não na lista.
     * @param dado a ser verificado na lista.
     * @return true, se o dado estiver presente,
     * false, se não estiver.
     */
    @Override
    public boolean contains(T dado) {
        boolean aux = false;
        Node<T> ponteiroAux = inicialPointer;
        for (int i = 0; i < amount; i++) {
            if (ponteiroAux.getElement() == dado) {
                aux = true;
                break;
            }
            ponteiroAux = ponteiroAux.getNext();
        }
        return aux;
    }

    /**
     * Atualiza o dado presente no índice indicado.
     * @param index índice que contêm o dado a ser atualizado.
     * @param element dado que substituirá o valor presente no índice indicado.
     *
     * @throws NoSuchElementException se a lista estiver vazia.
     * @throws IndexOutOfBoundsException se o índice for inválido.
     */
    @Override
    public void update(int index, T element) throws NoSuchElementException, IndexOutOfBoundsException{
        if(isEmpty())
            throw new NoSuchElementException("Lista vazia");
        if(index <0 || index >= amount)
            throw new IndexOutOfBoundsException("Índice inválido!");

        Node<T> ponteiroAux = inicialPointer;
        for (int i = 0; i < index; i++) {
            ponteiroAux = ponteiroAux.getNext();
        }
        ponteiroAux.setElement(element);
    }

    /**
     * Removerá o dado presente no índice indicado.
     * @param index índice que indica a posição do elemento a ser removido.
     * @return dado removido.
     *
     * @throws NoSuchElementException se a lista estiver vazia.
     * @throws IndexOutOfBoundsException se o índice for inválido.
     */
    @Override
    public T remove(int index) throws NoSuchElementException, IndexOutOfBoundsException{
        if(isEmpty())
            throw new NoSuchElementException("Lista vazia!");
        if(index < 0 || index >= amount)
            throw new IndexOutOfBoundsException("Índice inválido!");
        Node<T> auxPointer = inicialPointer;

        for (int i = 0; i < index; i++) {
            auxPointer = auxPointer.getNext();
        }
        Node<T> pointerPrevious = auxPointer.getPrevious();
        Node<T> ponteiroProximo = auxPointer.getNext();

        if (pointerPrevious != null) {
            pointerPrevious.setNext(ponteiroProximo);
        } else {
            inicialPointer = inicialPointer.getNext();
        }
        if (ponteiroProximo != null) {
            ponteiroProximo.setPrevious(pointerPrevious);
        } else {
            finalPointer = finalPointer.getPrevious();
        }
        amount--;
        return auxPointer.getElement();
    }

    /**
     * Limpa os dados da lista.
     */
    @Override
    public void clear() {
        inicialPointer = null;
        finalPointer = null;
        amount = 0;
    }

    /**
     * Verifica se lista está cheia ou não.
     * @return true se a fila estiver cheia.
     */
    @Override
    public boolean isFull() {
        return amount == max;
    }

    /**
     * Verifica se lista está vaiza ou não.
     * @return true se a fila estiver vazia.
     */
    @Override
    public boolean isEmpty() {
        return amount == 0;
    }

    /**
     * Retorna os dados presentes na lista.
     * @return String contendo os dados da lista.
     */
    @Override
    public String print() {
        String aux = "[";
        Node<T> auxPointer = inicialPointer;
        for (int i = 0; i < amount; i++) {
            aux += auxPointer.getElement();
            if (i != amount - 1)
                aux += ", ";

            auxPointer = auxPointer.getNext();
        }
        return aux + "]";
    }

    /**
     * Retorna o tamanho atual da lista.
     * @return o tamanho da lista.
     */
    @Override
    public int size() {
        return amount;
    }
}
