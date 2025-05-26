public class Node<T> {
    private Node<T> previous;
    private T element;
    private Node<T> next;

    public Node() {
        this(null);
    }

    public Node(T element) {
        this.previous = null;
        this.element = element;
        this.next = null;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }


}
