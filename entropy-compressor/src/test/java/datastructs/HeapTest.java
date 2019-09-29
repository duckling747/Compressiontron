package datastructs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class HeapTest {

    @Test
    public void heapReturnMinimum1() {
        MyQueue q = new MinHeap(20);
        for (int i = 19; i >= 0; i--) {
            q.push(new HuffmanLeaf('a', i));
        }
        for (int i = 0; i < 20; i++) {
            assertThat(q.pop().getFrequency(), is(i));
        }
    }

    @Test
    public void heapReturnMinimum2() {
        MyQueue q = new MinHeap(20);
        q.push(new HuffmanLeaf('a', -1));
        q.push(new HuffmanLeaf('a', -3));
        q.push(new HuffmanLeaf('a', 7));
        q.push(new HuffmanLeaf('a', 90));
        q.push(new HuffmanLeaf('a', 5));
        assertThat(q.pop().getFrequency(), is(-3));
        assertThat(q.pop().getFrequency(), is(-1));
        assertThat(q.pop().getFrequency(), is(5));
        assertThat(q.pop().getFrequency(), is(7));
        assertThat(q.pop().getFrequency(), is(90));
    }

    @Test
    public void heapReturnMinimum3() {
        MyQueue q = new MinHeap(20);
        for (int i = 19; i >= 0; i--) {
            q.push(new HuffmanLeaf('a', i));
        }
        while (q.size() > 0) {
            q.pop();
        }
        assertTrue(true);
    }

    @Test
    public void heapReturnMinimum4() {
        MyQueue q = new MinHeap(20);
        for (int i = 19; i >= 0; i--) {
            q.push(new HuffmanLeaf('a', i));
        }
        while (q.size() > 0) {
            q.pop();
        }
        q.push(new HuffmanLeaf('s', 6));
        while (q.size() > 0) {
            q.pop();
        }
        assertTrue(true);
    }

    @Test
    public void heapSizeCorrect() {
        MyQueue q = new MinHeap(100);
        for (int i = 0; i < 50; i++) {
            q.push(new HuffmanLeaf('b', 123));
        }
        assertThat(q.size(), is(50));
        for (int i = 1; i <= 20; i++) {
            q.pop();
            assertThat(q.size(), is(50 - i));
        }
    }

    @Test(expected = IllegalStateException.class)
    public void heapMaxSizeErrorsOut() {
        MyQueue q = new MinHeap(10);
        for (int i = 0; i < 10; i++) {
            q.push(new HuffmanLeaf('c', 1));
        }
        q.push(new HuffmanLeaf('x', 9));
    }
}
