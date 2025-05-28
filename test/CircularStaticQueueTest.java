import org.junit.Test;
import structures.queue.CircularStaticQueue;

import java.util.NoSuchElementException;
import static org.junit.Assert.*;

public class CircularStaticQueueTest {
    @Test
    public void testEnqueueAndFront() throws Exception {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        queue.enqueue("A");
        assertEquals("A", queue.front());
        queue.enqueue("B");
        assertEquals("A", queue.front());
    }

    @Test
    public void testEnqueueAndDequeue() throws Exception {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        queue.enqueue("A");
        queue.enqueue("B");
        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
    }

    @Test
    public void testIsFull() throws Exception {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        assertFalse(queue.isFull());
        queue.enqueue("A");
        assertFalse(queue.isFull());
        queue.enqueue("B");
        assertFalse(queue.isFull());
        queue.enqueue("C");
        assertTrue(queue.isFull());
    }

    @Test
    public void testIsEmpty() throws Exception {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        assertTrue(queue.isEmpty());
        queue.enqueue("A");
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeueThrowsExceptionWhenEmpty() {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        try {
            queue.dequeue(); // Deve lançar exceção
            fail("Deveria ter lançado NoSuchElementException ao desenfileirar de uma fila vazia");
        } catch (NoSuchElementException e) {
            // esperado
        } catch (Exception e) {
            fail("Esperava NoSuchElementException, mas foi lançado(a) " + e.getClass().getSimpleName());
        }
    }

    @Test
    public void testEnqueueThrowsExceptionWhenFull() throws Exception {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        try {
            queue.enqueue("D"); // Deve lançar exceção
            fail("Deveria ter lançado IllegalStateException ao enfileirar em uma fila cheia");
        } catch (IllegalStateException e) {
            // esperado
        } catch (Exception e) {
            fail("Esperava IllegalStateException, mas foi lançado(a) " + e.getClass().getSimpleName());
        }
    }

    @Test
    public void testUpdateFirst() throws Exception {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.updateFirst("X");
        assertEquals("X", queue.front());
        assertEquals("X", queue.dequeue());
        assertEquals("B", queue.front());
    }

    @Test
    public void testUpdateLast() throws Exception {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.updateLast("Y");
        assertEquals("A", queue.dequeue()); // remove A
        assertEquals("Y", queue.front());
        assertEquals("Y", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPrint() throws Exception {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        assertEquals("[]", queue.print());
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertEquals("[A, B, C]", queue.print());
    }

    @Test
    public void testFrontThrowsExceptionWhenEmpty() {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        assertTrue(queue.isEmpty());
        try {
            queue.front();
            fail("Deveria ter lançado NoSuchElementException ao chamar front em uma fila vazia");
        } catch (NoSuchElementException e) {
            // esperado
        } catch (Exception e) {
            fail("Esperava NoSuchElementException, mas foi lançado(a) " + e.getClass().getSimpleName());
        }
    }

    @Test
    public void testCircularBehaviorEnqueueDequeue() throws Exception {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertTrue(queue.isFull());

        assertEquals("A", queue.dequeue());
        assertFalse(queue.isFull());
        queue.enqueue("D");
        assertTrue(queue.isFull());
        assertEquals("B", queue.front());

        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());
        assertEquals("D", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPrintAfterWrapAround() throws Exception {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertEquals("A", queue.dequeue());
        queue.enqueue("D");
        assertEquals("[B, C, D]", queue.print());
    }

    @Test
    public void testUpdateFirstOnEmptyQueue() {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        try {
            queue.updateFirst("X");
            fail("Deveria ter lançado NoSuchElementException ao atualizar o primeiro elemento de uma fila vazia");
        } catch (NoSuchElementException e) {
            // esperado
        } catch (Exception e) {
            fail("Esperava NoSuchElementException, mas foi lançado(a) " + e.getClass().getSimpleName());
        }
    }

    @Test
    public void testUpdateLastOnEmptyQueue() {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        try {
            queue.updateLast("Y");
            fail("Deveria ter lançado NoSuchElementException ao atualizar o último elemento de uma fila vazia");
        } catch (NoSuchElementException e) {
            // esperado
        } catch (Exception e) {
            fail("Esperava NoSuchElementException, mas foi lançado(a) " + e.getClass().getSimpleName());
        }
    }

    @Test
    public void testUpdateFirstWhenOneElement() throws Exception {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        queue.enqueue("A");
        queue.updateFirst("X");
        assertEquals("X", queue.front());
        assertEquals("X", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testUpdateLastWhenOneElement() throws Exception {
        CircularStaticQueue<String> queue = new CircularStaticQueue<>(3);
        queue.enqueue("A");
        queue.updateLast("Y");
        assertEquals("Y", queue.front());
        assertEquals("Y", queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}