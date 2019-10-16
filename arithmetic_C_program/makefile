#
# This is a makefile for the arithmetic coding program for the datastructures and algorithms course.
# Typing 'make' will create the executables for both encoding and decoding.
#
#

CC = gcc
CFLAGS = -O2 -Wall
INCLUDES = -I./headers

vpath %.h headers
vpath %.c sources

.PHONY: default

default: encoder decoder

encoder: encode.o bit_output.o
	$(CC) $(CFLAGS) $(INCLUDES) -o encoder encode.o arithmetic_encode.o adaptive_model.o bit_output.o

adaptive_model.o: adaptive_model.c adaptive_model.h
	$(CC) $(CFLAGS) $(INCLUDES) -c $<

bit_output.o: bit_output.c bit_output.h
	$(CC) $(CFLAGS) $(INCLUDES) -c $<

arithmetic_encode.o: arithmetic_encode.c arithmetic_encode.h arithmetic_coding.h bit_output.h
	$(CC) $(CFLAGS) $(INCLUDES) -c $<

encode.o: encode.c adaptive_model.o arithmetic_encode.o encode.h arithmetic_encode.h bit_output.h
	$(CC) $(CFLAGS) $(INCLUDES) -c $<

decoder: decode.o bit_input.o
	$(CC) $(CFLAGS) $(INCLUDES) -o decoder decode.o arithmetic_decode.o adaptive_model.o bit_input.o

arithmetic_decode.o: arithmetic_decode.c arithmetic_decode.h arithmetic_coding.h bit_input.h
	$(CC) $(CFLAGS) $(INCLUDES) -c $<

decode.o: decode.c adaptive_model.o arithmetic_decode.o decode.h arithmetic_decode.h bit_input.h
	$(CC) $(CFLAGS) $(INCLUDES) -c $<

bit_input.o: bit_input.c bit_input.h
	$(CC) $(CFLAGS) $(INCLUDES) -c $<

clean:
	$(RM) *.o *~ encoder decoder

