package algo;

import datastructs.FreqTable;
import datastructs.LookUpTable;
import io.BitsWriter;
import java.io.IOException;

public class HuffmanEncoder extends HuffmanCore {

    private final LookUpTable look;

    /**
     * Construct a Huffman tree if not yet instantiated and create a lookup
     * table for quick access to the resulting Huffman codes.
     *
     * @param frequencyTable
     */
    public HuffmanEncoder(FreqTable frequencyTable) {
        if (super.root == null) {
            super.genTree(frequencyTable);
        }
        look = new LookUpTable(frequencyTable.getSymbolLimit(), root);
    }

    public void encodeSymbol(int symbol, BitsWriter out) throws IOException {
        for (int c : look.getCode(symbol).toCharArray()) {
            out.write(c);
        }
    }
    
    /**
     * Returns the lookup table used by this encoder. Mostly for unit testing.
     * @return lookup table
     */
    public LookUpTable getLookupTable() {
        return this.look;
    }
}
