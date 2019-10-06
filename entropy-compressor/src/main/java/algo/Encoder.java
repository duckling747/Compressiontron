package algo;

import io.BitsWriter;
import java.io.IOException;

public interface Encoder {

    /**
     * Encode a symbol and write it to a stream.
     *
     * @param symbol
     * @param out
     * @throws IOException
     */
    public void encodeSymbol(int symbol, BitsWriter out) throws IOException;
}
