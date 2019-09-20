package algo;

import datastructs.HuffmanTree;

public class HuffmanDecoder {

    private HuffmanTree tree;

    public HuffmanDecoder(HuffmanTree tree) {
        this.tree = tree;
    }

    public int decode(int bit) {
        int symbol = -1;
        tree.move(bit);
        if (tree.currentHasNoNeighbors()) {
            symbol = tree.getCurrentSymbol();
            tree.reset();
        }
        return symbol;
    }
}
