
#define No_of_chars 256
#define EOF_symbol (No_of_chars + 1)

#define No_of_symbols (No_of_chars + 1)

int char_to_index[No_of_chars];
unsigned char index_to_char[No_of_symbols + 1];

#define Max_frequency 16383

int cum_freq[No_of_symbols + 1];

void start_model();

void update_model(int symbol);
