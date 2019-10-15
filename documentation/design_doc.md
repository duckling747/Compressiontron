# Design document

The Compressiontron encodes text files' data in order to compress them to make them consume less space on disk. 
Decoding (i.e. decompression) is also possible. Two algorithms are implemented, the Huffman coding and the arithmetic coding (AC) algorithms. Both can be used for lossless data compression. The programming languages used are Java and C. Both are meant to be used from the command line, see [here](https://github.com/duckling747/Compressiontron/blob/master/documentation/user_guide.md).

## Time complexities, space complexities and design

The Java program is divided package-wise into the ui classes, io classes, classes to do with the algorithms, and classes that contain utility data structures for those algorithms. In the C program, headers are found in their own directory, containing function declarations, constanst and so on, and the sources in their own directory. 

The AC algorithm divides an interval into subintervals so that each subinterval represents a probability for a symbol to be found in the respective context. Each interval becomes the next interval to be divided, one for each symbol encoded. The probabilities will be assigned using a model of probabilities or simply calculated from their frequencies that can be either hard-coded or read from the file. Using the model and the resulting probabilities the original message can be decoded. Because the model is required for decoding, it will be saved to disk as well with the encoded data. In the implementation, a model which updates as the symbols are being read is used. 

The time complexity for AC encoding is linear (that is O(n)), as the algorithm merely reads the input file, counts (or reads directly) the frequencies for the symbols from the file and then outputs the encoded or decoded message bit by bit based on these frequencies in constant time. The decoding is O(n^2) instead, as the symbols have to be found using the probability model, or frequencies, through search. Space complexity for both is n as well.

In the case of the Huffman algorithm, the implementation used here, uses a minimum heap, ordered based on symbol frequencies, with which it constructs a binary tree. This tree is called the Huffman tree and it's size is at most 2 * k - 1 (one extra internal node per each leaf node minus the bottom node with two leaf nodes), where k is the amount of symbols arranged. Time complexity for the construction of the tree is theoretically O(nlog(n)), where n is again the amount of symbols, as the tree has to be ordered on each node insertion and there are n symbols to be ordered. In this implementation however, a minimum heap reorders the nodes for the tree on every insertion, and there will be n*(n-1) insertions to the tree, as every time a new internal node is constructed, it is taken from the tree and then inserted again. Expected time complexity will still be manageable due to the relatively small amount of symbols and the fact that the reorderings will not be entirely tree-deep on every iteration. The most time-consuming part of the implementation is however the traversal of the tree, to get the codes for each individual symbol. For each internal node there are two paths to take; this utilizes a backtracking search to then store the codes to a table for a faster lookup; and so time complexity for this operation is a nasty 2^n, where n is the count of nodes. In practise this doesn't take long at all, but could be mitigated with canonical Huffman coding or other measures not used here, for more demanding encoding needs.

# I/O

The program inputs and outputs text files and encoded binary files, depending on the task. More on program usage [here](https://github.com/duckling747/Compressiontron/blob/master/documentation/user_guide.md). 

The operation of the Java program (Huffman coding) is done through command line switches, and the operation of the C program (arithmetic coding) is done through standard I/O. Please read the user guide for more detailed info. 

# On implementation

## Huffman coding

The Huffman coding is done using a minimum heap for generating the Huffman tree, a frequency table for holding the frequencies of the coded symbols, and a simple table for holding the codes for the symbols, that is constructed using the tree. The tree can be discarded afterwards. I/O for the algorithm is done through two custom classes that construct bytes from bits, one bit at a time, as any I/O must be done one byte at a time.

## Arithmetic coding

Much like the Huffman coding, the arithmetic coding uses a frequency table, this time to compute cumulative frequencies for each symbol to be encoded, and then uses those symbols to construct a bit sequence analogous to an infinite-precicion decimal number, which then becomes the code for the algorithm. The number is generated with integers and bit-shifting, using a dynamic range of bits. The I/O is done through standard I/O of the operating system, as demonstrated in the [user guide](https://github.com/duckling747/Compressiontron/blob/master/documentation/user_guide.md).

# Sources 

* "Introduction to Data Compression", fourth edition, by K. Sayood
* Wikipedia
* "Arithmetic Coding for Data Compression" by Ian H. Witten, Radford M. Neal and John G. Cleary 
* OpenJDK source code for tips and pointers

# Design diagrams

TBA
