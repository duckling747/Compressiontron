package datastructs;

public interface MyQueue {

    /**
     * Add an item to the tail of the queue.
     *
     * @param item
     */
    public void push(HuffmanTree item);

    /**
     * Removes and returns the head of this queue.
     *
     * @return item
     */
    public HuffmanTree pop();

    /**
     * Returns the size of this queue.
     *
     * @return size
     */
    public int size();

}
