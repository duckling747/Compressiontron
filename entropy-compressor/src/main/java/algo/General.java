package algo;

import datastructs.FreqTable;
import datastructs.HuffmanLeaf;
import datastructs.HuffmanTree;
import java.util.PriorityQueue;

public final class General {

    public static final int CODEVALUEBITS = 16;
    public static final long TOPVALUE = (1L << CODEVALUEBITS) - 1;
    public static final long FIRSTQUARTER = (TOPVALUE / 4 + 1);
    public static final long HALF = (2 * FIRSTQUARTER);
    public static final long THIRDQUARTER = (3 * FIRSTQUARTER);
    /**
     * Global maximum amount of symbols supported. Most datastructs are inteded
     * to work with characters as ints, so this effectively translates to the
     * character range 0-SYMBOLLIMIT (inclusive).
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
        PriorityQueue<HuffmanTree> q = new PriorityQueue<>();
        for (int i = 0; i <= frequencyTable.getSymbolLimit(); i++) {
            if (frequencyTable.getFreq(i) == 0) {
                continue;
            }
            q.offer(new HuffmanLeaf(i, frequencyTable.getFreq(i)));
        }
        while (q.size() > 1) {
            HuffmanTree branch = new HuffmanTree(q.poll(), q.poll());
            q.offer(branch);
        }
        return q.poll();
    }

}
