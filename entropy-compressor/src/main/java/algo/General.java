package algo;

import datastructs.FreqTable;
import datastructs.HuffmanLeaf;
import datastructs.HuffmanTree;
import datastructs.MinHeap;
import datastructs.MyQueue;

public final class General {

    /**
     * Global maximum amount of symbols supported. Most datastructs are inteded
     * to work with characters as ints, so this effectively translates to the
     * character range 0-{@code SYMBOLLIMIT} (inclusive).
     */
    public static final int SYMBOLLIMIT = 255;

    /**
     * Create a Huffman tree containing the Huffman codes using a priority
     * queue.
     *
     * @param frequencyTable
     * @return Huffman tree
     */
    public final static HuffmanTree genTree(FreqTable frequencyTable) {
        MyQueue q = new MinHeap(SYMBOLLIMIT);
        for (int i = 0; i <= frequencyTable.getSymbolLimit(); i++) {
            if (frequencyTable.getFreq(i) == 0) {
                continue;
            }
            q.push(new HuffmanLeaf(i, frequencyTable.getFreq(i)));
        }
        while (q.size() > 1) {
            HuffmanTree branch = new HuffmanTree(q.pop(), q.pop());
            q.push(branch);
        }
        return q.pop();
    }
}
