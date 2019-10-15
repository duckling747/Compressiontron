# Project definition

The aim of this project is to create two simple, as-easy-to-understand-as-possible compression/decompression tools, using the Huffman coding and arithmetic coding algorithms, respectively. The algorithms are lossless, and so there should be no loss of information during the encoding/decoding process. The other algorithm is implemented in C and the other in Java. Automated unit testing is done using JUnit for the Java program, and it uses Maven for automated build assistance. 

## Algorithms and data structures

Huffman coding uses a tree-type structure to generate codes for each symbol encoded. Same tree is used for decoding the symbols. Arithmetic coding uses a cumulative frequency table, which is implemented here as a simple array, with useful utility functions as declared in the model header. 

## Input and output

The to-be-compressed file should be larger than the outputted file.

[See here for sources and more elaboration](https://github.com/duckling747/Compressiontron/blob/master/documentation/design_doc.md)
