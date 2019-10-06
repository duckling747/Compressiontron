package datastructs;

public class MinHeap implements MyQueue {

    private int size;
    private HuffmanTree[] heap;

    private final int max;

    /**
     * Minimal implementation of a minimum heap. There is no resize feature
     * currently implemented so the total amount of inserts must be known in
     * advance.
     *
     * @param maximumCapacity
     */
    public MinHeap(int maximumCapacity) {
        max = maximumCapacity;
        size = 0;
        heap = new HuffmanTree[max];
    }

    private void swap(int a, int b) {
        HuffmanTree temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    /**
     * Add an item to the heap. Maintains heap order on every insert by
     * iteratively swapping items.
     *
     * @param tree
     */
    @Override
    public void push(HuffmanTree tree) {
        if (size == max) {
            throw new IllegalStateException("Heap already full");
        }
        int i = size++;
        heap[i] = tree;

        for (int parent = parent(i);
                i > 0 && heap[parent].compareTo(heap[i]) > 0;
                i = parent, parent = parent(i)) {
            swap(i, parent);
        }
    }

    /**
     * Removes and returns the minimum. Returns the first item in the
     * heap-array, which is the minimum.
     *
     * @return minimum
     */
    @Override
    public HuffmanTree pop() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            return heap[--size];
        }
        HuffmanTree ret = heap[0];
        heap[0] = heap[--size];
        orderHeap(0);
        return ret;
    }

    /**
     * Keep the order so that the smallest is in index 0. Recursively push index
     * i down the tree using swapping, as long as it is larger than the
     * children.
     */
    private void orderHeap(int i) {
        int minimum = i;
        int left = left(i), right = right(i);
        if (left < size && heap[left].compareTo(heap[minimum]) < 0) {
            minimum = left;
        }
        if (right < size && heap[right].compareTo(heap[minimum]) < 0) {
            minimum = right;
        }
        if (minimum != i) {
            swap(i, minimum);
            orderHeap(minimum);
        }
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
