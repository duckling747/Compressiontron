package algo;

import datastructs.FreqTable;
import datastructs.HuffmanLeaf;
import datastructs.HuffmanTree;
import java.util.PriorityQueue;

public class HuffmanCore {

    protected HuffmanTree root;

    /**
     * Create a Huffman tree containing the Huffman codes using a priority
     * queue.
     *
     * @param frequencyTable
     */
    protected final void genTree(FreqTable frequencyTable) {
        PriorityQueue<HuffmanTree> q = new PriorityQueue<>();
        for (int i = -1; i <= frequencyTable.getSymbolLimit(); i++) {
            q.offer(new HuffmanLeaf(i, frequencyTable.getFreq(i)));
        }
        while (q.size() > 1) {
            HuffmanTree branch = new HuffmanTree(q.poll(), q.poll());
            q.offer(branch);
        }
        root = q.poll();
    }
}
