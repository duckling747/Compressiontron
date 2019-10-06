package algo;

import io.BitsReader;
import java.io.IOException;

public interface Decoder {

    /**
     * Decode a symbol as it is read from a stream.
     *
     * @param in
     * @return
     * @throws IOException
     */
    public int decodeSymbol(BitsReader in) throws IOException;
}
