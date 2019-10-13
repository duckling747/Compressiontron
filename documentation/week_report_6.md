# Week 6 report

## What was done during the week

I read and rewrote the code for the arithmetic coding algorithm several times again trying to find possible bugs and/or mistakes, but didn't find anything to fix the fact that it produces only empty Strings on output. I tried different data types, unsigned math, signed math, making the model update on-the-fly, and so on, but still it only outputs an empty String according to the tests every single time (or results in a division by zero error if the precision of the types should be too low). Huffman coding however functions perfectly as planned with the same I/O classes and methods. Then I got really frustrated and wrote the arithmetic thing in C, and it works! So. After wasting dozens of hours on the Java program and basically instantly making a working C version of the same thing, I will very probably just leave the Java version in a nonfunctional state, unless somehow something magical happens. And I'll demo only the Huffman algorithm, as if that was the only one ever made. I can also demo the C program I have made, if necessary. Just say the word. 

## How has writing the program progressed

Not really. The program is now finished, unless something or someone fixes Java itself. 

## What did I learn

Languages count, as they make the algorithms either easy or awkward to implement. 

## Difficulties and what next

It wouldn't make sense to compare a C program with a Java program, so I'll most likely do a series of compressions with the Huffman algorithm only next week, as that's the one that works. Then put that stuff with the last of the docs and whatnot. Comments? Advice? 
