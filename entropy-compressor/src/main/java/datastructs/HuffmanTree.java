package datastructs;

public class HuffmanTree implements Comparable<HuffmanTree> {

    private final HuffmanTree leftChild, rightChild;
    private final int frequency;

    protected StringBuilder prefix = new StringBuilder();

    public HuffmanTree(int frequency) {
        this.frequency = frequency;
        this.leftChild = null;
        this.rightChild = null;
    }

    public HuffmanTree(HuffmanTree left, HuffmanTree right) {
        this.frequency = left.frequency + right.frequency;
        this.leftChild = left;
        this.rightChild = right;
        this.leftChild.prefix.append(this.prefix)
                .append('0');
        this.rightChild.prefix.append(this.prefix)
                .append('1');
    }

    public boolean isLeaf() {
        return false;
    }

    public HuffmanTree getLeftChild() {
        return leftChild;
    }

    public HuffmanTree getRightChild() {
        return rightChild;
    }

    @Override
    public int compareTo(HuffmanTree o) {
        return this.frequency < o.frequency ? -1 : this.frequency > o.frequency ? 1 : 0;
    }

}
