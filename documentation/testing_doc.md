# Testing document

This document explains what was tested and how in the compression program. The program is for the algorithms and data structures project course at University of Helsinki. 

## What was tested and how

The project tests are done automatically using JUnit. The test classes are found [here](https://github.com/duckling747/Compressiontron/tree/master/entropy-compressor/src/test/java). The tests encompass the core functionality for the data structures and the functioning of the compression algorithms. The tests test for whether the structures work as expected for the purposes of this program, for example, that the minimum heap orders it's items correctly, that the cumulative frequencies are computed correctly in the frequency table, and so on. The most important test regarding the program's function is the test for the equality of the original input file, and the resulting output file from first compressing the input and then decompressing it again. Once this test passes, the program is effectively complete. 

As the program is run, it will also input some statistics on how fast the compression algorithms worked. Comparisons can be done with different input files manually as well as with custom scripts using the output. 

Manual testing can also be done for the project using varied input files for compression and decompression. Using the command line, first compile and package the program using the command
´´´
mvn package
´´´
from the project folder and then run it using in the target folder
´´´
java -jar <coding> <encode/decode> <input file>
´´´
If the tests do not pass, the compilation is terminated. In that case the program is not functional and any testing (and hopefully bug squashing) can be done by manually coding the program yourself.

## How to do the tests yourself

Execute the command
´´´
mvn test
´´´
from the command line in the project folder to run the automated tests. 

## Graphics

TBA
