# Testing document

This document explains what was tested and how in the compression program. The program is for the algorithms and data structures project course at University of Helsinki. 

## What was tested and how

The tests for the Java program (Huffman) are done automatically using JUnit. The test classes are found [here](https://github.com/duckling747/Compressiontron/tree/master/entropy-compressor/src/test/java). The tests encompass the core functionality for the data structures and the functioning of the compression algorithms, and test for whether the structures work as expected for the purposes of this program, for example, that the minimum heap orders it's items correctly, that the cumulative frequencies are computed correctly in the frequency table, and so on. The most important test regarding the program's function is the test for the equality of the original input file, and the resulting output file from first compressing the input and then decompressing it again. Once this test passes, the program is effectively complete. 

As the program is run, it will also output some statistics on how fast the algorithm worked. Comparisons can obviously be done with different input files manually as well as with custom scripts using the output. 

The C program has been tested manually by comparing the original input file and the resulting output file from the compression-decompression cycle.

The Java program can be compiled by traversing to the project folder and executing: 
```
mvn package
```
The resulting jar is run using:
```
java -jar <-enc/-dec> <input file>
```
The jar can be found in the target folder.

## How to do the tests yourself

Execute the command
```
mvn test
```
from the command line in the project folder to run the automated tests for the Java program. 

As for the C program (arithmetic) only manual testing has been done via comparing the compressed file and the file that the decompressor outputs using that compressed data as input. This however could be automated rather easily using standard UNIX tools such as diff, for example by running the following command: 

```
diff <original_input> <decompressed_output>
```
If the output of the diff is an empty string, then the files match.

## Benchmarking


