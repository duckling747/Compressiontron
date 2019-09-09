package IO;

import java.io.IOException;
import java.io.OutputStream;

public final class BitsWriter implements AutoCloseable {

    private int current;
    private int bitsReady;

    private final OutputStream os;

    public BitsWriter(OutputStream os) {
        this.current = 0;
        this.bitsReady = 0;
        this.os = os;
    }

    /**
     * Stores a bit into a byte (that's represented as an int) until all 8 bits
     * of it are ready. Then writes it to file.
     *
     * @param bit
     * @throws IOException
     */
    public void write(int bit) throws IOException {
        current = (current << 1) | bit;
        if (++bitsReady == 8) {
            os.write(bit);
            current = 0;
            bitsReady = 0;
        }
    }

    /**
     * Write any bits left not yet written, flush the underlying stream and
     * then close it.
     *
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        while (bitsReady > 0) {
            write(0);
        }
        os.flush();
        os.close();
    }
}
