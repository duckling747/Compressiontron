package algo;

import datastructs.FreqTable;
import datastructs.HuffmanTree;
import datastructs.LookupTable;
import io.BitsWriter;
import java.io.IOException;

public class HuffmanEncoder extends General implements Encoder {

    private LookupTable table;

    /**
     * Construct a Huffman tree if not yet constructed and use it to get the
     * Huffman codes for the table. Construct a Huffman tree to get the codes
     * for the individual symbols, then use it to create a lookup table, and,
     * finally, the tree can be discarded.
     *
     * @param frequencyTable
     */
    public HuffmanEncoder(FreqTable frequencyTable) {
        HuffmanTree root = genTree(frequencyTable);
        table = new LookupTable(root, frequencyTable.getSymbolLimit() + 1);
        root = null;
    }

    /**
     * Encode a given symbol and write it to stream one bit at a time.
     *
     * @param symbol
     * @param out
     * @throws IOException
     */
    @Override
    public void encodeSymbol(int symbol, BitsWriter out) throws IOException {
        String code = table.getCode(symbol);
        for (char c : code.toCharArray()) {
            out.writeBit(c - 48);// 0 char is 48 in integer and 1 char is 49
        }
    }

    public LookupTable getLookupTable() {
        return table;
    }
}
