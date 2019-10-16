#include "encode.h"
#include "arithmetic_encode.h"
#include "bit_output.h"


int main(void)
{
    start_model();
    start_outputing_bits();
    start_encoding();
    for(;;) {
        int ch;
        int symbol;
        ch = getc(stdin);
        if (ch == EOF) {
            break;
        }
        symbol = char_to_index[ch];
        encode_symbol(symbol, cum_freq);
        update_model(symbol);
    }
    encode_symbol(EOF_symbol, cum_freq);
    done_encoding();
    done_outputing_bits();
    exit(0);
}
