import org.junit.Test;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;

public class GenericDynamicListTest {

    @Test
    public void testDifferentTypesList() throws Exception {
        Listable<Integer> listaInt = new GenericDynamicList<>(2);
        listaInt.append(1);
        listaInt.append(2);
        assertEquals(Integer.valueOf(1), listaInt.get(0));

        Listable<Double> listaDouble = new GenericDynamicList<>(2);
        listaDouble.append(1.5);
        listaDouble.append(2.5);
        assertEquals(Double.valueOf(1.5), listaDouble.get(0));
    }

    @Test
    public void testDefaultConstructor() throws Exception {
        Listable<String> lista = new GenericDynamicList<>();
        for (int i = 0; i < 1000; i++) {
            lista.append("Elemento" + i);
        }
        assertTrue(lista.isFull());
        try {
            lista.append("Extra");
            fail("Deveria ter lançado exceção de lista cheia!");
        } catch (Exception e) {
            assertEquals("List is full!", e.getMessage());
        }
    }

    @Test
    public void testAppendAndGet() throws Exception {
        GenericDynamicList<String> list = new GenericDynamicList<>();
        list.append("A");
        list.append("B");
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
    }

    @Test
    public void testGetFromEmptyListThrowsException() {
        GenericDynamicList<String> list = new GenericDynamicList<>();
        try {
            list.get(0);
            fail("Esperava NoSuchElementException");
        } catch (NoSuchElementException e) {
            // esperado
        }
    }

    @Test
    public void testGetInvalidIndexThrowsException() throws Exception {
        GenericDynamicList<String> list = new GenericDynamicList<>();
        list.append("X");
        try {
            list.get(2);
            fail("Esperava IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // esperado
        }
    }

    @Test
    public void testInsert() throws Exception {
        GenericDynamicList<String> list = new GenericDynamicList<>();
        list.append("A");
        list.append("C");
        list.insert(1, "B");
        assertEquals("B", list.get(1));
    }

    @Test
    public void testUpdate() throws Exception {
        GenericDynamicList<String> list = new GenericDynamicList<>();
        list.append("X");
        list.update(0, "Y");
        assertEquals("Y", list.get(0));
    }

    @Test
    public void testUpdateInEmptyList() {
        GenericDynamicList<String> list = new GenericDynamicList<>();

        try {
            list.update(0, "NovoValor");
            fail("Era esperado lançar NoSuchElementException ao tentar atualizar uma lista vazia.");
        } catch (NoSuchElementException e) {
            // esperado
        } catch (Exception e) {
            fail("Lançou uma exceção diferente da esperada: " + e.getClass().getSimpleName());
        }
    }

    @Test
    public void testInsertInvalidIndexThrowsException() {
        GenericDynamicList<String> list = new GenericDynamicList<>();
        try {
            list.insert(5, "Invalid");
            fail("Esperava IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // esperado
        } catch (Exception e) {
            fail("Esperava IndexOutOfBoundsException, mas veio outra: " + e);
        }
    }

    @Test
    public void testRemove() throws Exception {
        GenericDynamicList<String> list = new GenericDynamicList<>();
        list.append("A");
        list.append("B");
        list.append("C");

        String removed = list.remove(1);
        assertEquals("B", removed);
        assertEquals("C", list.get(1));
        assertEquals(2, list.getAll().length);
    }

    @Test
    public void testContains() throws Exception {
        GenericDynamicList<String> list = new GenericDynamicList<>();
        list.append("Alpha");
        list.append("Beta");

        assertTrue(list.contains("Alpha"));
        assertFalse(list.contains("Gamma"));
    }

    @Test
    public void testClear() throws Exception {
        GenericDynamicList<String> list = new GenericDynamicList<>();
        list.append("One");
        list.append("Two");
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testPrint() throws Exception {
        GenericDynamicList<String> list = new GenericDynamicList<>();
        list.append("A");
        list.append("B");
        list.append("C");
        assertEquals("[A, B, C]", list.print());
    }

    @Test
    public void testEmptyPrint() throws Exception {
        GenericDynamicList<String> list = new GenericDynamicList<>();
        assertEquals("[]", list.print());
    }


    @Test
    public void testIsFullThrowsException() throws Exception {
        GenericDynamicList<String> list = new GenericDynamicList<>(2);
        list.append("1");
        list.append("2");
        assertTrue(list.isFull());

        try {
            list.append("3");
            fail("Esperava Exception por lista cheia");
        } catch (Exception e) {
            // esperado
        }
    }
}
