package io;

import java.io.File;

public final class FileUtils {

    private static final String[] SUFFIX = {"B", "KiB", "MiB", "GiB", "TiB"};

    public static String getFileSize(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            throw new IllegalArgumentException("File no exist! ");
        }
        long size = f.length();
        int index = (int) (Math.log(size) / Math.log(1024));
        return String.format("%.1f %s", size / Math.pow(1024, index), SUFFIX[index]);
    }

    public static String getRatio(String original, String compression) {
        File orig = new File(original);
        File compres = new File(compression);
        if (!orig.exists() || !compres.exists()) {
            throw new IllegalArgumentException("Some file not exist! ");
        }
        long spaceOrig = orig.length();
        long spaceCompr = compres.length();
        return String.format("%.1f%%", 100.0 * spaceCompr / spaceOrig);
    }
}
