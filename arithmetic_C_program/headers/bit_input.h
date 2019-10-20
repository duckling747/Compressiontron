/**
 * Bit input reads the standard input (stdin) for a sequence of bits and it will be called in
 * the arithmetic decode file.
 */


#ifndef BIT_INPUT_H
#define BIT_INPUT_H

#include <stdio.h>
#include "arithmetic_coding.h"

void start_inputing_bits(void);

int input_bit(void);

#endif
