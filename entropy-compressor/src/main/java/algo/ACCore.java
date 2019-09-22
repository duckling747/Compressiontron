package algo;

public class ACCore {

    protected static final int CODEVALUEBITS = 32;
    protected static final long TOPVALUE = (1L << CODEVALUEBITS) - 1;
    protected static final long FIRSTQUARTER = (TOPVALUE / 4 + 1);
    protected static final long HALF = (2 * FIRSTQUARTER);
    protected static final long THIRDQUARTER = (3 * FIRSTQUARTER);

}
