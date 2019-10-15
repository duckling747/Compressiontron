# User guide

Here are simple instructions two run the two programs in the compression algorithms project. Note that there are implementations with two different languages, which you will find in different directories. For Huffman coding, there is a Java implementation here, and for arithmetic coding, a C implementation here. 

## How to run the Java program

First generate the jar-file, by executing
```
mvn package
```
Then, in the folder with the resulting jar, run it with 
```
java -jar <-com|-decom> <input file> <output file>
```
Example 1 (compression using the Huffman algorithm):
```
java -jar entropy-compressor-1.0.jar -com pride_and_prejudice.txt outputted_bits
```
This will compress the text file "pride_and_prejudice.txt" into a data file named "outputted_bits" with some arbitrary file extension, and generate an auxiliary file, containing the frequencies, size exactly 256 B, named the same except with a different suffix.

Example 2 (decompression using the Huffman algorithm):
```
java -jar entropy-compressor-1.0.jar -decom outputted_bits.huff decompressed_pride
```
This will decompress the given data file "outputted_bits.huff" into a text file named "decompressed_pride.txt". Remember to no write the file endings when giving the arguments, as these will be appended automatically. 

# How to run the C program

