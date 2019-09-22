package datastructs;

public class HuffmanTree implements Comparable<HuffmanTree> {

    private final HuffmanTree leftChild, rightChild;
    private final int frequency;

    /**
     * Construct a Huffman tree. If the tree is constructed using only the
     * frequency, then it must be a leaf node.
     *
     * @param frequency
     */
    protected HuffmanTree(int frequency) {
        this.frequency = frequency;
        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * Construct a Huffman tree. The tree is constructed from two pre-existing
     * Huffman trees.
     *
     * @param left
     * @param right
     */
    public HuffmanTree(HuffmanTree left, HuffmanTree right) {
        this.frequency = left.frequency + right.frequency;
        this.leftChild = left;
        this.rightChild = right;
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
        return this.frequency < o.frequency ? -1
                : this.frequency > o.frequency ? 1 : 0;
    }

}
