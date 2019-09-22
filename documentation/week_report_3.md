# Week 3 report

## What was done during the week

Had a busy week, so I got to work only some time friday. Huffman code has progressed, and that compression algorithm is finished. I also wrote some tests for it so that the Huffman tree should be generated in a correct manner. I also did some research on different ways to get Huffman codes out of some source message, as well as different optimizations for the algorithm. I had in my mind to implement some of those, but then realized that any possible comparison between the arithmetic and Huffman coding would be skewed if one was far more optimal than the other. Therefore, both simply store the frequencies first in a resulting file, and the coded message after that. 

## How has writing the program progressed

Huffman compression is done, a few mandatory tests for it are written, and the decompression should be relatively simple to write. Also some minor work has been done keeping an eye on future implementations of data structures, like the minimum heap, used in generating the Huffman tree. 

## What did I learn

I learned about canonical Huffman codes, some different ways of coding symbols using a Huffman tree, and that, crucially, when writing data in Java with a DataOutputStream, the write-method writes what is essentially an unsigned byte, and the writeByte-method writes what is essentially a signed byte. So there's no confusion left with that hopefully.

## Difficulties and what next

The end of the project is already in sight. There are essentially three things that have to be done so that the project will work as expected. First, the arithmetic coding/decoding should not output garble. Second, the Huffman decoding must be implemented and tested. Third, the minimum heap must be implemented. The rest is just writing the UI and some documentation for the project, including whatever JavaDoc might be missing, the user guide, and so on. Also beautifying the arithmetic coding algorithm might be a consideration, as it takes relatively many lines of code as it now stands. The arithmetic code is somewhat of a puzzle, as I followed the example put forth in the article quite closely, but then once I take a pen and some paper to that problem, things will most likely become more clear.
