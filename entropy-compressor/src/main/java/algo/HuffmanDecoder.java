package algo;

import datastructs.FreqTable;
import datastructs.HuffmanLeaf;
import datastructs.HuffmanTree;
import io.BitsReader;
import java.io.IOException;

public class HuffmanDecoder extends General implements Decoder {

    private final HuffmanTree root;
    private HuffmanTree current;

    public HuffmanDecoder(FreqTable f) {
        root = genTree(f);
    }

    private int decode(int bit) {
        if (bit == 0) {
            current = current.getLeftChild();
        } else if (bit == 1) {
            current = current.getRightChild();
        }
        if (current.isLeaf()) {
            return ((HuffmanLeaf) current).getSymbol();
        } else {
            return -1;
        }
    }

    @Override
    public int decodeSymbol(BitsReader in) throws IOException {
        int bit;
        int symbol;
        current = root;
        while ((bit = in.read()) != -1) {
            if ((symbol = decode(bit)) != -1) {
                return symbol;
            }
        }
        return -1;
    }
}
