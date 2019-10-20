/**
 * A simple set of generally used constants that set up the code range.
 * The precision for the binary arithmetic will be >= Code_value_bits + how many
 * bits will be required for the frequency of each symbol.
 */

#ifndef ARITHMETIC_CODING_H
#define ARITHMETIC_CODING_H

#include<stdlib.h>

#define Code_value_bits 16
typedef long code_value;

#define Top_value   (((long) 1 << Code_value_bits) - 1)
#define First_qtr   (Top_value / 4 + 1)
#define Half        (2 * First_qtr)
#define Third_qtr   (3 * First_qtr)

#endif
