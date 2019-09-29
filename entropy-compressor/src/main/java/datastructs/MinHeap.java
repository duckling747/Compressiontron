package datastructs;

public class MinHeap implements MyQueue {

    private int size;
    private HuffmanTree[] heap;

    private final int max;

    /**
     * Minimal implementation of a minimum heap. There is no resize feature
     * currently implemented so the total amount of inserts must be known in
     * advance. 
     * @param maximumCapacity 
     */
    public MinHeap(int maximumCapacity) {
        max = maximumCapacity;
        size = 0;
        heap = new HuffmanTree[max + 1];
    }

    private void swap(int a, int b) {
        HuffmanTree temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    /**
     * Add an item to the heap.
     *
     * @param tree
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void push(HuffmanTree tree) {
        if (size == max) {
            throw new IllegalStateException("Heap already full");
        }
        /*
        Find the first empty space, fill from start to end
         */
        int i;
        for (i = 0; i < heap.length && heap[i] != null; i++);

        heap[i] = tree;
        size++;
        for (int parent = (i - 1) / 2; i > 0; i = parent, parent = (i - 1) / 2) {
            /*
            Every item in array has two possible children, as every 
            item has one parent: one for every other number of i
            as in a binary tree
             */
            if (heap[parent].compareTo(heap[i]) > 0) {
                swap(i, parent);
            }
        }
    }

    /**
     * Removes and returns the minimum. Returns the first item in
     * the heap-array, which is the minimum. 
     *
     * @return
     */
    @Override
    public HuffmanTree pop() {
        if (size == 0) {
            return null;
        }
        HuffmanTree tree = heap[0];
        for (int parent = 0, child = 2 * parent + 1;
                heap[parent] != null;
                parent = child) {
            if (heap[child] != null && heap[child + 1] == null) {
                heap[parent] = heap[child];
            } else if (heap[child] == null && heap[child + 1] != null) {
                heap[parent] = heap[child + 1];
                child++;
            } else if (heap[child] != null && heap[child + 1] != null) {
                int compare = heap[child].compareTo(heap[child + 1]);
                if (compare <= 0) {
                    heap[parent] = heap[child];
                } else {
                    heap[parent] = heap[child + 1];
                    child++;
                }
            }
        }
        size--;
        return tree;
    }

    /**
     * Returns the size of this min heap.
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

}
