package structures.queue;

import org.junit.Test;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;

/**
 * Classe de testes unitários para a classe GenericDynamicQueue.
 *
 * @author Vinícius Nunes de Andrade
 * @version 2.0
 * @since 20-05-2025
 */
public class GenericDynamicQueueTest {

    /**
     * Testa se o método "enqueue" adiciona elementos corretamente e se
     * o método "front" sempre retorna o primeiro elemento da fila.
     *
     * @throws Exception se ocorrer um erro ao enfileirar.
     */
    @Test
    public void testEnqueueAndFront() throws Exception {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        queue.enqueue("A");
        assertEquals("A", queue.front());
        queue.enqueue("B");
        assertEquals("A", queue.front());
    }

    /**
     * Testa a funcionalidade de enfileirar e desenfileirar, verificando
     * se os elementos são removidos na ordem correta (FIFO).
     *
     * @throws Exception se ocorrer um erro ao enfileirar.
     */
    @Test
    public void testEnqueueAndDequeue() throws Exception {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        queue.enqueue("A");
        queue.enqueue("B");
        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
    }

    /**
     * Testa se o método "isFull" retorna o estado correto da fila
     * conforme elementos são adicionados.
     *
     * @throws Exception se ocorrer um erro ao enfileirar.
     */
    @Test
    public void testIsFull() throws Exception {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        assertFalse(queue.isFull());
        queue.enqueue("A");
        assertFalse(queue.isFull());
        queue.enqueue("B");
        assertFalse(queue.isFull());
        queue.enqueue("C");
        assertTrue(queue.isFull());
    }

    /**
     * Testa se o método "isEmpty" retorna o estado correto da fila
     * ao adicionar e remover elementos.
     *
     * @throws Exception se ocorrer um erro ao enfileirar.
     */
    @Test
    public void testIsEmpty() throws Exception {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        assertTrue(queue.isEmpty());
        queue.enqueue("A");
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    /**
     * Testa se o método "dequeue" lança uma exceção ao ser chamado
     * em uma fila vazia.
     */
    @Test
    public void testDequeueThrowsExceptionWhenEmpty() {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        try {
            queue.dequeue(); // Deve lançar exceção
            fail("Deveria ter lançado NoSuchElementException ao desenfileirar de uma fila vazia");
        } catch (NoSuchElementException e) {
            // esperado
        } catch (Exception e) {
            fail("Esperava NoSuchElementException, mas foi lançado(a) " + e.getClass().getSimpleName());
        }
    }

    /**
     * Testa se o método "enqueue" lança uma exceção ao ser chamado
     * em uma fila cheia.
     *
     * @throws Exception é esperada ao tentar enfileirar em uma fila cheia.
     */
    @Test
    public void testEnqueueThrowsExceptionWhenFull() throws Exception {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        try {
            queue.enqueue("D"); // Deve lançar exceção
            fail("Deveria ter lançado IllegalStateException ao enfileirar em uma fila cheia");
        } catch (Exception e) {
            //esperado
        }
    }

    /**
     * Testa a atualização do primeiro elemento da fila com "updateFirst".
     *
     * @throws Exception se ocorrer um erro ao enfileirar.
     */
    @Test
    public void testUpdateFirst() throws Exception {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.updateFirst("X");
        assertEquals("X", queue.front());
        assertEquals("X", queue.dequeue());
        assertEquals("B", queue.front());
    }

    /**
     * Testa a atualização do último elemento da fila com "updateLast".
     *
     * @throws Exception se ocorrer um erro ao enfileirar.
     */
    @Test
    public void testUpdateLast() throws Exception {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.updateLast("Y");
        assertEquals("A", queue.dequeue()); // remove A
        assertEquals("Y", queue.front());
        assertEquals("Y", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    /**
     * Testa a representação em String de uma fila com e sem elementos.
     *
     * @throws Exception se ocorrer um erro ao enfileirar.
     */
    @Test
    public void testPrint() throws Exception {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        assertEquals("[]", queue.print());
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertEquals("[A, B, C]", queue.print());
    }

    /**
     * Testa se o método "front" lança uma exceção ao ser chamado
     * em uma fila vazia.
     */
    @Test
    public void testFrontThrowsExceptionWhenEmpty() {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
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

    /**
     * Testa se a representação em String da fila está correta
     * após operações que envolvem o comportamento circular.
     *
     * @throws Exception se ocorrer um erro ao enfileirar.
     */
    @Test
    public void testPrintAfterWrapAround() throws Exception {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertEquals("A", queue.dequeue());
        queue.enqueue("D");
        assertEquals("[B, C, D]", queue.print());
    }

    /**
     * Testa se o método "updateFirst" lança uma exceção ao ser
     * chamado em uma fila vazia.
     */
    @Test
    public void testUpdateFirstOnEmptyQueue() {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        try {
            queue.updateFirst("X");
            fail("Deveria ter lançado NoSuchElementException ao atualizar o primeiro elemento de uma fila vazia");
        } catch (NoSuchElementException e) {
            // esperado
        } catch (Exception e) {
            fail("Esperava NoSuchElementException, mas foi lançado(a) " + e.getClass().getSimpleName());
        }
    }

    /**
     * Testa se o método "updateLast" lança uma exceção ao ser
     * chamado em uma fila vazia.
     */
    @Test
    public void testUpdateLastOnEmptyQueue() {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        try {
            queue.updateLast("Y");
            fail("Deveria ter lançado NoSuchElementException ao atualizar o último elemento de uma fila vazia");
        } catch (NoSuchElementException e) {
            // esperado
        } catch (Exception e) {
            fail("Esperava NoSuchElementException, mas foi lançado(a) " + e.getClass().getSimpleName());
        }
    }

    /**
     * Testa a atualização do primeiro elemento quando a fila contém apenas um item.
     *
     * @throws Exception se ocorrer um erro ao enfileirar.
     */
    @Test
    public void testUpdateFirstWhenOneElement() throws Exception {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        queue.enqueue("A");
        queue.updateFirst("X");
        assertEquals("X", queue.front());
        assertEquals("X", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    /**
     * Testa a atualização do último elemento quando a fila contém apenas um item.
     *
     * @throws Exception se ocorrer um erro ao enfileirar.
     */
    @Test
    public void testUpdateLastWhenOneElement() throws Exception {
        GenericDynamicQueue<String> queue = new GenericDynamicQueue<>(3);
        queue.enqueue("A");
        queue.updateLast("Y");
        assertEquals("Y", queue.front());
        assertEquals("Y", queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}