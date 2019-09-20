package datastructs;

public class HuffmanTree {

    private static class Node {

        private Node left;
        private Node right;
        private int frequency;
        private int symbol;

        private Node(int symbol, int freq) {
            this.symbol = symbol;
            this.frequency = freq;
        }
    }
    
    private Node current;
    

    public HuffmanTree(FreqTable f) {
        
    }
    
    public void addNode(int leftFreq, int rightFreq, int sumFreq) {
        
    }
    
    public void move(int bit) {
        
    }
    
    public boolean currentHasNoNeighbors() {
        return true;
    }
    
    public int getCurrentSymbol() {
        return 1;
    }
    
    public void reset() {
        
    }
}
