package datastructs;

public class LookupTable {

    private String[] codes;

    public LookupTable(HuffmanTree tree, int size) {
        codes = new String[size];
        getCodes(tree, new StringBuilder());
    }

    /**
     * Get codes from the tree and initialize the table using recursion.
     */
    private void getCodes(HuffmanTree current, StringBuilder b) {
        if (current.isLeaf()) {
            codes[((HuffmanLeaf) current).getSymbol()] = b.toString();
            return;
        }
        getCodes(current.getLeftChild(), b.append('0'));
        b.deleteCharAt(b.length() - 1);
        getCodes(current.getRightChild(), b.append('1'));
        b.deleteCharAt(b.length() - 1);
    }

    /**
     * Return the Huffman code for the given symbol.
     *
     * @param symbol
     * @return
     */
    public String getCode(int symbol) {
        return codes[symbol];
    }

}
