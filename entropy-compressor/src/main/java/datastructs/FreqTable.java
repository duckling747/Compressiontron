package datastructs;

public interface FreqTable {
    
    /**
     * Returns the frequency of the character c.
     * @param c
     * @return Frequency in table for char c
     */
    public int getFreq(int c);
    
    /**
     * Return the sum of all frequencies.
     * @return Sum of frequencies in table
     */
    public int getTotalSumFreq();
    
    /**
     * Increment frequency by one for specified symbol.
     * @param c 
     */
    public void addFreq(int c);
    
    /**
     * Return the symbol limit (maximum integer possible). 
     * @return Sum of different characters in table
     */
    public int getSymbolLimit();
    
    /**
     * Calculates the cumulative frequencies.
     */
    public void calcCumFreq();
    
    /**
     * Returns the cumulative frequency for given character (represented as integer)
     * @param c
     * @return Cumulative frequency
     */
    public int getCumFreq(int c);
    
}