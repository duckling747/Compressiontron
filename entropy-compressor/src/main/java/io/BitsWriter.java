package io;

import java.io.DataOutputStream;
import java.io.IOException;

public final class BitsWriter implements AutoCloseable {

    private int buffer;
    private int bitsReady;
    private DataOutputStream os;

    public BitsWriter(DataOutputStream os) {
        this.buffer = 0;
        this.bitsReady = 0;
        this.os = os;
    }

    /**
     * If a byte is not ready, stores a bit into a bit buffer, else writes the
     * ready byte into the output stream.
     *
     * @param bit
     * @throws IOException
     */
    public void writeBit(int bit) throws IOException {
        if (bit != 0 && bit != 1) {
            throw new IllegalArgumentException("Bit must be either 0 or 1");
        }
        buffer = (buffer << 1) | bit;
        if (++bitsReady == 8) {
            os.write(buffer);
            buffer = 0;
            bitsReady = 0;
        }
    }

    /**
     * Writes a byte to file.
     *
     * @param value
     * @throws IOException
     */
    public void writeByte(int value) throws IOException {
        for (int j = 7; j >= 0; j--) {
            writeBit((value >>> j) & 1);
        }
    }

    /**
     * Write any bits left not yet written, flush the underlying stream and then
     * close it.
     *
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        while (bitsReady > 0) {
            writeBit(0);
        }
        os.close();
    }

    public int getBuffer() {
        return buffer;
    }

    public int getBitsReady() {
        return bitsReady;
    }

}
