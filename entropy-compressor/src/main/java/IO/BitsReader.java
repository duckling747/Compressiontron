package IO;

import java.io.DataInputStream;
import java.io.IOException;

public final class BitsReader implements AutoCloseable {

    private int current;
    private int bitsReady;

    private final DataInputStream in;

    public BitsReader(DataInputStream in) {
        this.current = 0;
        this.bitsReady = 0;
        this.in = in;
    }

    public int read() throws IOException {

    }

    @Override
    public void close() throws Exception {
    }

}
