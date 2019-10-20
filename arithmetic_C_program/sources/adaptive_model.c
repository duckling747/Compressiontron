
#include "adaptive_model.h"

/**
 * Sets up the model so that it has two arrays, char_to_index and index_to_char,
 * for ordering the symbols based on frequency. At the beginning each frequency is 
 * initialized to 1. The cum_freq array is ordered in reverse order so that the highest 
 * cumulative frequency (which is also the total sum frequency) is at index 0. The index 
 * 0 of the freq array is set to 0 for this reason as well, so that with the index 0 the 
 * two arrays match.
 */
void start_model(void)
{
    int i;
    for (i = 0; i < No_of_chars; i++) {
        char_to_index[i] = i + 1;
        index_to_char[i + 1] = i;
    }
    for (i = 0; i <= No_of_symbols; i++) {
        freq[i] = 1;
        cum_freq[i] = No_of_symbols - i;
    }
    freq[0] = 0;
}

/**
 * Update the model / frequency arrays with a new symbol
 */
void update_model(int symbol)
{   
    int i;
    /**
     * If a frequency is over the specified limit, which is a constant defined in the header,
     * then halve all frequencies.
     */
    if (cum_freq[0] == Max_frequency) {
        int cum;
        cum = 0;
        for (i = No_of_symbols; i >= 0; i--) {
            freq[i] = (freq[i] + 1) / 2;
            cum_freq[i] = cum;
            cum += freq[i];
        }
    }
    /**
     * Reorder the symbols if need be to maintain ordering based on frequency, and 
     * update the index_to_char and char_to_index arrays accordingly.
     */
    for (i = symbol; freq[i] == freq[i - 1]; i--);
    if (i < symbol) {
        int ch_i, ch_symbol;
        ch_i = index_to_char[i];
        ch_symbol = index_to_char[symbol];
        index_to_char[i] = ch_symbol;
        index_to_char[symbol] = ch_i;
        char_to_index[ch_i] = symbol;
        char_to_index[ch_symbol] = i;
    }
    freq[i]++;

    while(i > 0) {
        i--;
        cum_freq[i]++;
    }

}
