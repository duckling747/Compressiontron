package algo;

import datastructs.FreqTable;
import datastructs.HuffmanLeaf;
import datastructs.HuffmanTree;
import java.util.PriorityQueue;

public class General {

    protected static final int CODEVALUEBITS = 16;
    protected static final long TOPVALUE = (1L << CODEVALUEBITS) - 1;
    protected static final long FIRSTQUARTER = (TOPVALUE / 4 + 1);
    protected static final long HALF = (2 * FIRSTQUARTER);
    protected static final long THIRDQUARTER = (3 * FIRSTQUARTER);

    /**
     * Create a Huffman tree containing the Huffman codes using a priority
     * queue.
     *
     * @param frequencyTable
     * @return Huffman tree
     */
    protected final static HuffmanTree genTree(FreqTable frequencyTable) {
        PriorityQueue<HuffmanTree> q = new PriorityQueue<>();
        for (int i = 1; i <= frequencyTable.getSymbolLimit(); i++) {
            q.offer(new HuffmanLeaf(i, frequencyTable.getFreq(i)));
        }
        while (q.size() > 1) {
            HuffmanTree branch = new HuffmanTree(q.poll(), q.poll());
            q.offer(branch);
        }
        return q.poll();
    }

}
