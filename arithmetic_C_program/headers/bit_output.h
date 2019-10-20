/**
 * Bit output writes to the standard output (stdout) and keeps a buffer of bits, until
 * eight of them are ready at a time; I/O needs one byte, which is eight bits.
 */


#ifndef BIT_OUTPUT_H
#define BIT_OUTPUT_H

#include <stdio.h>

void start_outputing_bits(void);

void output_bit(int bit);

void done_outputing_bits(void);

#endif
