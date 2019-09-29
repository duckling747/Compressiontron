# Project definition

The aim of this project is to create a simple, as-easy-to-understand-as-possible compression/decompression tool, using alternatively either the Huffman coding or arithmetic coding algorithms. The algorithms are lossless, and so there should be no loss of information during the encoding/decoding process. Programming language used for this project is Java, automated unit testing is done using JUnit, and the project itself is a Maven project. 

## Algorithms and data structures

Huffman coding uses a tree-type structure to generate codes for each symbol encoded. Same tree is used for decoding the symbols. Arithmetic coding uses a cumulative frequency table, which is implemented here as a simple array, with useful utility methods inside the FreqTable class. 

## Input and output

The to-be-compressed file should be larger than the outputted file.

[See here for sources and more elaboration](https://github.com/duckling747/Compressiontron/blob/master/documentation/design_doc.md)
