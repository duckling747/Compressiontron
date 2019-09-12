package IO;

import java.io.DataOutputStream;
import java.io.IOException;

public final class BitsWriter implements AutoCloseable {

    private int current;
    private int bitsReady;
    private DataOutputStream os;

    public BitsWriter(DataOutputStream os) {
        this.current = 0;
        this.bitsReady = 0;
        this.os = os;
    }

    /**
     * If a byte is not ready, stores a bit into a bit buffer, else writes the ready
     * byte into the output stream.
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
     * Writes an integer to file as four bytes.
     *
     * @param i
     * @throws IOException
     */
    public void writeInt(int i) throws IOException {
        os.writeInt(i);
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
            write(0);
        }
        os.flush();
        os.close();
    }
}
