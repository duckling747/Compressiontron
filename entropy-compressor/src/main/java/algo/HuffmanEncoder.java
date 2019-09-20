package algo;

import datastructs.FreqTable;
import datastructs.HuffmanTree;

public class HuffmanEncoder {

    private FreqTable freqs;
    private HuffmanTree tree;

    public HuffmanEncoder(FreqTable f) {
        this.freqs = f;
        this.tree = new HuffmanTree(freqs);
    }

    public HuffmanTree encode() {
        for (int i = 1; i < freqs.getSymbolLimit(); i += 2) {
            int leftFreq = freqs.getFreq(i);
            int rightFreq = freqs.getFreq(i + 1);
            if (leftFreq < rightFreq) {
                throw new AssertionError("Frequencies are not in order from highest to lowest");
            }
            tree.addNode(leftFreq, rightFreq, leftFreq + rightFreq);
        }
        return tree;
    }
}
