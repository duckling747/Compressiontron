package algo;

import io.BitsReader;
import java.io.IOException;

public interface Decoder {

    public int decodeSymbol(BitsReader in) throws IOException;
}
