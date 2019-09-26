package io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class BitsReader implements AutoCloseable {

    private InputStream in;
    private int buffer;
    private int bitsLeft;

    public BitsReader(DataInputStream in) {
        this.in = in;
        this.bitsLeft = 0;
        this.buffer = 0;
    }

    /**
     * Reads a bit from the stream. Uses a byte buffer to store each byte from
     * the stream and returns the next bit from the byte on call. Returns -1 if
     * end of stream is reached.
     *
     * @return
     * @throws IOException
     */
    public int readBit() throws IOException {
        if (bitsLeft == 0) {
            buffer = in.read();
            if (buffer == -1) {
                return -1;
            }
            bitsLeft = 8;
        }
        bitsLeft--;
        return (buffer >>> bitsLeft) & 1;
    }

    /**
     * Reads a byte from the stream.
     *
     * @return the byte as an integer
     * @throws IOException
     */
    public int readByte() throws IOException {
        int value = 0;
        for (int i = 7; i >= 0; i--) {
            value = value | (readBit() << i);
        }
        return value;
    }

    /**
     * Closes the underlying stream.
     *
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        in.close();
    }

    public int getBuffer() {
        return buffer;
    }

    public int getBitsLeft() {
        return bitsLeft;
    }

}
