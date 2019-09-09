# Design document

The Compressiontron encodes text files' data in order to compress them to make them consume less space on disk. 
Decoding (i.e. decompression) is also possible. At the very least two algorithms will be implemented, the Huffman coding and the arithmetic coding (AC) algorithms. Both can be used for lossless data compression. The programming language used will be Java. 

## Time complexities and design

The algorithms will be imlemented each in their own respective class and the possible data structures they utilize in their own respective classes. The algorithms, the data structures (utility structures), and whatever disk I/O or text mangling will all also be found in one package for each group. 

The AC algorithm divides an interval into subintervals so that each subinterval represents a probability for a symbol to be found in the respective context. Each interval becomes the next interval to be divided, one for each symbol encoded. The probabilities will be assigned using a model of probabilities that can be either fixed or auto-updating. Using the model and the resulting probabilities the original message can be decoded. Because the model is required for decoding, it will most likely be saved to disk as well with the encoded data. 

The time complexity for AC (both encode and decode) should be linear (that is O(n)), as the algorithm merely reads the input file, counts (or reads directly) the frequencies for the symbols from the file and then outputs the encoded or decoded message bit by bit based on these frequencies. 

In the case of the Huffman algorithm, it uses a min heap that's ordered based on symbol frequencies, with which it constructs a binary tree. This tree is called the Huffman tree and it's size will depend on the number of symbols in the encoded message. Time complexity of the algorithm should be O(nlog(n)). 

This document will be revisioned once the algorithms are implemented. 

## Data structures and Space complexities

Huffman coding utilizes a tree structure and a priority queue, and both the Huffman coding and AC algorithms utilize a frequency table of some sort. 

More on space complexities as the algorithms are implemented. 

# I/O

The program takes as input either text files or encoded text files and outputs one or more of the following: an encoded text file, a decoded text file, benchmarking results. These depend on the input and used command line switches. More on program usage [here](https://github.com/duckling747/Compressiontron/blob/master/documentation/user_guide.md). 

As the emphasis of the course (for which this program is created for) is on the data structures and algorithms, no specially crafted UI will be implemented. Please read the user guide for more detailed info. 

# Sources 

Inspiration is drawn especially from the book "Introduction to Data Compression", fourth edition, by K. Sayood, and from Wikipedia. Also the paper "Arithmetic Coding for Data Compression" by Ian H. Witten, Radford M. Neal and John G. Cleary is clearly very helpful for the purposes of this project. 

# Design diagrams

TBA
