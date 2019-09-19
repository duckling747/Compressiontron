package datastructs;

public class HuffmanTree {

    private static class Node {

        private Node left;
        private Node right;
        private long frequency;

        private Node(long f) {
            this.frequency = f;
        }
    }

    public HuffmanTree(FreqTable f) {
        
    }
}
