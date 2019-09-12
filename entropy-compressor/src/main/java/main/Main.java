package main;

import algo.ACCompress;

public class Main {

    public static void main(String[] args) {
        ACCompress compress = new ACCompress("Lorem_ipsum.txt");
        compress.compress();
    }

}
