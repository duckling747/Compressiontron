package datastructs;

import java.util.ArrayDeque;
import java.util.Queue;

public class LookUpTable {

    private String[] arr;

    public LookUpTable(int size, HuffmanTree root) {
        arr = new String[size];
        constructBreadthWide(root);
    }

    private void constructBreadthWide(HuffmanTree root) {
        Queue<HuffmanTree> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            HuffmanTree current = q.poll();
            if (current.isLeaf()) {
                arr[((HuffmanLeaf) current).getSymbol()] = ((HuffmanLeaf) current).getCode();
            } else {
                q.offer(current.getLeftChild());
                q.offer(current.getRightChild());
            }
        }
    }

    /**
     * Returns the binary Huffman code for the given symbol.
     *
     * @param c
     * @return Huffman code binary in String representation.
     */
    public String getCode(int c) {
        if (c < 1 || c > arr.length - 1) {
            throw new AssertionError("Character not in range");
        }
        return arr[c];
    }

}
