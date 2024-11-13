package ru.nsu.syspro.zagitov.find;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


/**
 * A class for searching for all occurrences of a substring in a file.
 */
public class FindSubstring {
    /**
     * Optimize algorithm Knuth–Morris–Pratt.
     *
     * @param filename name file.
     * @param pattern the desired substring.
     * @return ArrayList indexes all occurrences pattern in file.
     * @throws IOException if an I/O error has occurred.
     */
    public ArrayList<Long> find(String filename, String pattern) throws IOException {
        if (filename == null || pattern == null || pattern.isEmpty()) {
            return new ArrayList<>();
        }

        int[] sample = pattern.codePoints().toArray();
        int[] prefix = new int[sample.length];
        int lastPrefix = 0;
        prefix[0] = 0;

        for (int i = 1; i < sample.length; i++) {
            while (lastPrefix > 0 && sample[lastPrefix] != sample[i]) {
                lastPrefix = prefix[lastPrefix - 1];
            }
            if (sample[lastPrefix] == sample[i]) {
                lastPrefix++;
            }
            prefix[i] = lastPrefix;
        }

        lastPrefix = 0;
        ArrayList<Long> result = new ArrayList<>();
        try (Reader reader = new InputStreamReader(new FileInputStream(filename),
                StandardCharsets.UTF_8)) {
            int ch;
            long index = 0;

            while ((ch = nextSymbol(reader)) != -1) {
                index++;

                while (lastPrefix > 0 && sample[lastPrefix] != ch) {
                    lastPrefix = prefix[lastPrefix - 1];
                }

                if (sample[lastPrefix] == ch) {
                    lastPrefix++;
                }
                if (lastPrefix == sample.length) {
                    result.add(index - lastPrefix);
                    lastPrefix = prefix[lastPrefix - 1];
                }
            }
        }
        return result;
    }

    /**
     * Read next symbol(code point) from the reader.
     *
     * @param reader data to read.
     * @return code point next symbol or -1 if end data.
     * @throws IOException if an I/O error has occurred.
     */
    protected int nextSymbol(Reader reader) throws IOException {
        int ch;
        char surrogate = 0;
        while ((ch = reader.read()) != -1) {
            if (surrogate != 0) {
                ch = Character.toCodePoint(surrogate, (char) ch);
            } else if (Character.isHighSurrogate((char) ch)) {
                surrogate = (char) ch;
                continue;
            }
            return ch;
        }
        return -1;
    }
}
