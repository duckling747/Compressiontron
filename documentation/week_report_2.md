# Week 2 report

## What was done during the week

I decided to start with the arithmetic coding instead of the Huffman one, mostly because it seemed more challenging to implement, and therefore would probably require more time. The classes having to do with the arithmetic coding algorithm are now completed, and most of the testing for the arithmetic coding algorithm is also done. In addition one datastructure, which most likely can be shared between the arithmetic and Huffman algorithms, is also almost completed, maybe missing one method at the most. 

## How has writing the program progressed

Writing has been challenging and slow even with the good examples in the article source I mentioned in the original design document, as Java has its own peculiarities with I/O and bit manipulation. Unsigned variables do not really exist, which is also a challenge. 

## What did I learn

I realized during coding that the Huffman algorithm wouldn't necessarily need a priority queue for generating the tree, and so could use an offline sorting algorithm for an array just as well. I learned that the time complexity for the arithmetic decoding would most likely be nlog(n) in my implementation. I also learned how in Java one doesn't simply shift bits. Things work far better with 
```java
return (buffer >>> bitsLeft) & 1;
```
than with
```java
int bit = buffer & 1;
buffer <<= 1;
return bit;
```
The other time complexities are becoming more clear as well. The alphabet size determines most of what happens with the compression.

## Difficulties and what next

The arithmetic coding algorithm has hit a snag: I'm not sure how to fix the code so that it would produce the same output text as the input. I'll need to look at it some more, but due to time constraints, will probably start implementing the Huffman algorithm next regardless. Most difficult thing this week was probably figuring out how in Java you can store an int as an unsigned byte so that when the stored byte is read again as an integer, it will have zeroes everywhere else except the eight least significant bits. Other stuff with bits also kept me scratching my head. Mostly the program is written trusting that the default character encoding will just work, but there's the fear that this approach will hit some nasty bugs in the future, and so that might deserve some attention as well. 
