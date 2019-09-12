package IO;

import java.io.DataInputStream;
import java.io.IOException;

public final class BitsReader implements AutoCloseable {

    private DataInputStream in;
    private int nextByte;
    private int bitsLeft;

    public BitsReader(DataInputStream in) {
        this.in = in;
        this.bitsLeft = 0;
        this.nextByte = 0;
    }

    /**
     * Reads a bit from the stream. Uses a byte buffer to store each byte from
     * the stream and returns the next bit from the byte on call. Returns -1 if 
     * end of stream is reached. 
     *
     * @return
     * @throws IOException
     */
    public int read() throws IOException {
        if (bitsLeft == 0) {
            nextByte = in.read();
            if (nextByte == -1) {
                return -1;
            }
            bitsLeft = 8;
        }
        int bit = nextByte & 1;
        nextByte >>= 1;
        bitsLeft--;
        return bit;
    }

    /**
     * Reads an int from the stream. Reads an integer as four bytes from the 
     * stream and returns it.
     * @return
     * @throws IOException 
     */
    public int readInt() throws IOException {
        return in.readInt();
    }

    /**
     * Closes the underlying stream.
     * @throws Exception 
     */
    @Override
    public void close() throws Exception {
        in.close();
    }

}
