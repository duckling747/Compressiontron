#ifndef ARITHMETIC_DECODE_H
#define ARITHMETIC_DECODE_H

#include "arithmetic_coding.h"

void start_decoding(void);

int decode_symbol(int cum_freq[]);

#endif
