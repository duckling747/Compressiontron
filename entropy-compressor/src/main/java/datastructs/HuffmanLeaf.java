package datastructs;

public class HuffmanLeaf extends HuffmanTree {

    private final int symbol;

    public HuffmanLeaf(int symbol, int frequency) {
        super(frequency);
        this.symbol = symbol;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    public int getSymbol() {
        return symbol;
    }

}
