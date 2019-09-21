package datastructs;

public class HuffmanTree implements Comparable<HuffmanTree> {

    @Override
    public int compareTo(HuffmanTree o) {
        return this.frequency < o.frequency ? -1 : this.frequency > o.frequency ? 1 : 0;
    }

    private HuffmanTree leftChild, rightChild;
    private final int frequency;

    public HuffmanTree(int frequency) {
        this.frequency = frequency;
    }

    public HuffmanTree(HuffmanTree left, HuffmanTree right) {
        this.frequency = left.frequency + right.frequency;
    }

    public boolean hasNoChildren() {
        return this.leftChild == null && this.rightChild == null;
    }

    public boolean isLeaf() {
        return false;
    }

}
