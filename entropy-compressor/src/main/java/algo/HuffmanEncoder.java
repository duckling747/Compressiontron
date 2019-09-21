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
        return tree;
    }
}
