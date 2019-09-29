# Week 4 report

## What was done during the week

Completed all algorithms that are necessary, including any data structures needed, except that some tests are still not passed, and neither encoder still output anything meaningful. So I wrote almost everything from the compression and decompression classes again, re-implemented the frequency table classes practically from scratch a bit differently, and next I'm doing the same for the encoders and decoders, in the hopes of finding any typos or mistakes by chance. Hopefully then the algorithms start to work as intended. Also changed the output design so that the encoders can create two different files. This makes for a lot more clear comparison between original input files and outputted files, and easier testing.

## How has writing the program progressed

As said before, everything except perhaps some UI is now done. Only bugs left to squish and stupid mistakes to correct, if there are any. Most likely there are. 

## What did I learn

I learned that programming can be extremely tedious, as the mistakes, typos and bugs just keep appearing over and over again. Just understanding an idea isn't enough to implement it perfectly. I also learned how a binary tree could be coded as an array instead of just regular old pointers, but didn't have the time to look at preordered Huffman tree generation. Since I'm hesitant to write absolutely everything again and again, except those parts that have to be, I'll just explore the array mechanism with the minimum heap. I learned that practically all Finnish characters could be encoded within a byte, and that in Java, they fit: even the character ß has its integer value within 0-255, so most worries with character encoding should cease.

## Difficulties and what next

Bugs that are hidden are difficult. Next I'll write some of the code again, this time more compartmentalized, shorter, and easier to test. Pressure is starting to rise as I don't know which parts of the code work. Just have to write more tests and design the stuff to be easier to test. 
