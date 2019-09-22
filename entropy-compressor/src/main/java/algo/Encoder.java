package algo;

import io.BitsWriter;
import java.io.IOException;

public interface Encoder {

    public void encodeSymbol(int symbol, BitsWriter out) throws IOException;
}
