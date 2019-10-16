/*
 * The adaptive model is basically implemented as two auto-updating
 * frequency tables that are used to calculate the probability range for
 * each encoded symbol.
*/


#ifndef ADAPTIVE_MODEL_H
#define ADAPTIVE_MODEL_H

#define No_of_chars 256                     /* Maximum amount of symbols supported */
#define EOF_symbol (No_of_chars + 1)

#define No_of_symbols (No_of_chars + 1)

int char_to_index[No_of_chars];
unsigned char index_to_char[No_of_symbols + 1];

#define Max_frequency 16383                 /* Maximum frequency count for any frequency */

int cum_freq[No_of_symbols + 1];

int freq[No_of_symbols + 1];

void start_model(void);                     /* Initializes the model / frequency tables */

void update_model(int symbol);              /* Function for updating the model on the fly */

#endif
