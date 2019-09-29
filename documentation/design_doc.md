# Design document

The Compressiontron encodes text files' data in order to compress them to make them consume less space on disk. 
Decoding (i.e. decompression) is also possible. Two algorithms are implemented, the Huffman coding and the arithmetic coding (AC) algorithms. Both can be used for lossless data compression. The programming language used is Java. 

## Time complexities, space complexities and design

The algorithms are implemented each in their own respective class and the possible data structures they utilize in their own respective classes, one for each decoding and one for each encoding algorithm. The algorithms, the data structures (utility structures), and whatever disk I/O or text mangling will all also be found in one package for each group. 

The AC algorithm divides an interval into subintervals so that each subinterval represents a probability for a symbol to be found in the respective context. Each interval becomes the next interval to be divided, one for each symbol encoded. The probabilities will be assigned using a model of probabilities or simply calculated from their frequencies that can be either hard-coded or read from the file. Using the model and the resulting probabilities the original message can be decoded. Because the model is required for decoding, it will be saved to disk as well with the encoded data, albeit in a different file. 

The time complexity for AC encoding is linear (that is O(n)), as the algorithm merely reads the input file, counts (or reads directly) the frequencies for the symbols from the file and then outputs the encoded or decoded message bit by bit based on these frequencies in constant time. The decoding is O(nlog(n)) instead, as the symbols have to be found using the probability model, or frequencies, through search. Space complexity for both is n as well.

In the case of the Huffman algorithm, the implementation used here, the most common one, uses a minimum heap, ordered based on symbol frequencies, with which it constructs a binary tree. This tree is called the Huffman tree and it's size is at most 2 * k - 1 (one extra internal node per each leaf node minus the bottom node with two leaf nodes), where k is the amount of symbols arranged. Time complexity for the construction of the tree is theoretically O(nlog(n)), where n is again the amount of symbols, as the tree has to be ordered on each node insertion and there are n symbols to be ordered. In this implementation however, a minimum heap reorders the nodes for the tree on every insertion, and there will be n*(n-1) insertions to the tree, as every time a new internal node is constructed, it is taken from the tree and then inserted again. Expected time complexity will still be manageable due to the relatively small amount of symbols and the fact that the reorderings will not be entirely tree-deep on every iteration. The most time-consuming part of the implementation is however the traversal of the tree, to get the codes for each individual symbol. For each internal node there are two paths to take; this utilizes a backtracking search to then store the codes to a table for a faster lookup; and so time complexity for this operation is a nasty 2^n, where n is the count of nodes. In practise this doesn't take long at all, but could be mitigated with canonical Huffman coding or other measures not used here, for more demanding encoding needs.

# I/O

The program inputs and outputs text files and encoded binary files, depending on the task. More on program usage [here](https://github.com/duckling747/Compressiontron/blob/master/documentation/user_guide.md). 

As the emphasis of the course (for which this program is created for) is on the data structures and algorithms, any possible UI is done using command line switches. Please read the user guide for more detailed info. 

# On implementation

## Huffman coding

The Huffman coding is done using a minimum heap for generating the Huffman tree, a frequency table for holding the frequencies of the coded symbols, and a simple table for holding the codes for the symbols, that is constructed using the tree. The tree can be discarded afterwards. I/O for the algorithm is done through two custom classes that construct bytes from bits, one bit at a time, as any I/O must be done one byte at a time.

## Arithmetic coding

Much like the Huffman coding, the arithmetic coding uses a frequency table, this time to compute cumulative frequencies for each symbol to be encoded, and then uses those symbols to effectively construct an infinite-precicion decimal number, which then becomes the code for the algorithm. The number is generated with integers and bit-shifting, using a dynamic range of bits. The same I/O classes are used for this as in the Huffman case. 

## UI and everything else

The UI (if I have the time to complete it) will be a simple command line switch parser, that selects the operation to be performed for the designated input file. Output will be on the working folder, where the program was executed.

# Sources 

* "Introduction to Data Compression", fourth edition, by K. Sayood
* Wikipedia
* "Arithmetic Coding for Data Compression" by Ian H. Witten, Radford M. Neal and John G. Cleary 
* OpenJDK source code for tips and pointers

# Design diagrams

TBA
