/**
 * Arithmetic encoding does the encoding of the symbols one by one based on 
 * their cumulative frequencies.
 */


#ifndef ARITHMETIC_ENCODE_H
#define ARITHMETIC_ENCODE_H

#include "arithmetic_coding.h"

void start_encoding(void);

void encode_symbol(int symbol, int cum_freq[]);

void done_encoding(void);

#endif
