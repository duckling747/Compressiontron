# Week 5 report

## What was done during the week

Wrote more tests, and fixed a ton of bugs and weird mistakes. Got Huffman algorithms to work, assuming there are no errors in the JUnit tests that measure this. Also wrote a simple UI using command line switches, and a small utility class for getting the file sizes for some kind of internal benchmarking. 

## How has writing the program progressed

Bug squashing is slow. The UI happened quite quickly. MinHeap had a small typo, and now that's fixed. 

## What did I learn

I learned that Java's OutputStream is actually an interface, and that I can't wrap FileOutputStream with both a DataOutputStream and a BufferedOutputStream. How that kind of wrapping is supposed to be done is rather convoluted in my mind, but I believe I understood now how these Buffered-type streams are supposed to work. 

## Difficulties and what next

Bit manipulation is kind of hard. Just need to keep working at it. Next there is only the Arithmetic coding algorithm which needs to work, unless some new bugs pop up. 
