package datastructs;

public interface MyQueue<T> {

    /**
     * Add an item to the tail of the queue.
     *
     * @param item
     */
    public void push(T item);

    /**
     * Removes and returns the head of this queue.
     *
     * @return
     */
    public T pop();

    /**
     * Returns the size of this queue.
     *
     * @return
     */
    public int size();

}
