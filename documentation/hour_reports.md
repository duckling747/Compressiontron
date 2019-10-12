# Hour reports

Date     | time | synopsis |
---------|------|----------|
2019/09/05 | 4h | Project creation. Research on different compression algorithms. Decision on what to implement. |
2019/09/07 | 5h | Reading about mathematical preliminaries for lossless compression and about Huffman coding |
2019/09/09 | 5h | Started working on the arithmetic coding algorithm based on available literature |
2019/09/12 | 9h | Hours of confusion over Java's I/O mechanisms. Finalized the arithmetic encoder and started on the decoder |
2019/09/14 | 6h | Writing tests, working out bugs |
2019/09/15 | 6h | A couple of hours of vain research on how to get different maven plugins working on Java 11, plus writing some fixes, tests and documentation for the week |
2019/09/19 | 1h | Setting up classes and things for Huffman |
2019/09/20 | 2h | Create more classes, work on Huffman coding |
2019/09/21 | 7h | Work on Huffman some more, figure out at least one way how to make it work without getting confused by optimizations |
2019/09/22 | 8h | Work on Huffman, refractor code |
2019/09/26 | 10h | Complete the code and now have two finished nonfunctional compression algorithms, write tests, work on problems, reflect on life |
2019/09/27 | 8h | Hours of staring at the code, wondering why it doesn't work. Marginal changes here and there, more tests. |
2019/09/28 | 12h | Spent most of the day staring at the code, reading and rewriting everything several times, trying to find what's wrong with it. No luck. |
2019/09/29 | 11h | More writing and rewriting, minimal implementation of a minimum heap. Doesn't pass all tests. Write weekly reports and docs. |
2019/10/03 | 2h | Read code, found typo in min heap |
2019/10/04 | 2h | Experimented with different ways to calculate the range in arithmetic coding. Output still not quite right. |
2019/10/05 | 10h | Thought I made AC and Huffman work, but no. Experimented with different ways of writing the binaries to disk in an attempt to catch errors. |
2019/10/06 | 5h | Found errors and corrected them. Now the Huffman stuff works for sure. AC still needs work. Somewhere there is an infinite loop that needs fixing. |
2019/10/10 | 4h | Looked for errors and found one potential bug relating to decoder, but it should not have anything to do with longer than CODEVALUEBITS length files. So still don't know why output for decoder is empty string. |
2019/10/11 | 6h | Wrote and rewrote everything several times, wrote tests for arithmetic coding with Strings. Intense late night testing. Still no work! |
2019/10/12 | 3h | Looked at the code for the arithmetic encoding. Removed useless AC tests for Strings. |
