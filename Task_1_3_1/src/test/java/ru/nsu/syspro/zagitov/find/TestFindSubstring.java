package ru.nsu.syspro.zagitov.find;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;


/**
 * Class testing FindSubstring.
 */
public class TestFindSubstring {
    static final FindSubstring clsFind = new FindSubstring();

    private static void createFile(String filename, String data) throws IOException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filename),
                StandardCharsets.UTF_8)) {
            writer.write(data);
        }
    }

    private static void deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }

    private static Reader openReaderFile(String filename) throws IOException {
        return new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8);
    }

    @Test
    public void testNextSymbol0() throws IOException {
        String filename = "testNextSymbol0.txt";
        String data = "123うΩ𝄞456"; // "𝄞" == "\uD834\uDD1E"

        try {
            createFile(filename, data);
            Reader reader = openReaderFile(filename);

            int ch1 = clsFind.nextSymbol(reader);
            assert ch1 == (int) '1';
            int ch2 = clsFind.nextSymbol(reader);
            assert ch2 == (int) '2';
            clsFind.nextSymbol(reader);
            int ch4 = clsFind.nextSymbol(reader);
            assert ch4 == (int) 'う';
            clsFind.nextSymbol(reader);
            int ch6 = clsFind.nextSymbol(reader);
            assert ch6 == 119070; // 119070 == (int) 𝄞
            int ch7 = clsFind.nextSymbol(reader);
            assert ch7 == (int) '4';
        } finally {
            deleteFile(filename);
        }
    }

    @Test
    public void testNextSymbol1() throws IOException {
        String filename = "testNextSymbol1.txt";
        String data = "123うΩ𝄞456";

        try {
            createFile(filename, data);
            Reader reader = openReaderFile(filename);

            int ch1;
            int ch2;
            for (int i = 0; i < 9; i++) {
                ch1 = clsFind.nextSymbol(reader);
                assert ch1 != -1;
            }
            for (int i = 0; i < 10; i++) {
                ch2 = clsFind.nextSymbol(reader);
                assert ch2 == -1;
            }
        } finally {
            deleteFile(filename);
        }
    }
    
    @Test
    public void testFind0() throws IOException {
        String filename = "testFind0.txt";
        String data = "asdzxcczxxcxzczxc";
        String substring = "zxc";

        try {
            createFile(filename, data);
            ArrayList<Long> res = clsFind.find(filename, substring);
            assert Objects.equals(res, List.of(3L, 14L));
        } finally {
            deleteFile(filename);
        }
    }

    @Test
    public void testFind1() throws IOException {
        String filename = "testFind1.txt";
        String data = "aabaabaaaabaabaaab";
        String substring = "aabaa";

        try {
            createFile(filename, data);
            ArrayList<Long> res = clsFind.find(filename, substring);
            assert Objects.equals(res, List.of(0L, 3L, 8L, 11L));
        } finally {
            deleteFile(filename);
        }
    }

    @Test
    public void testFind2() throws IOException {
        String filename = "testFind2.txt";
        String data = "aabaabaaaabaabaaab";
        String substring = "qwe";

        try {
            createFile(filename, data);
            ArrayList<Long> res = clsFind.find(filename, substring);
            assert Objects.equals(res, List.of());
        } finally {
            deleteFile(filename);
        }
    }

    @Test
    public void testFind3() throws IOException {
        String filename = "testFind3.txt";
        String data = "123うΩ𝄞𝄞456";
        String substring = "𝄞";

        try {
            createFile(filename, data);
            ArrayList<Long> res = clsFind.find(filename, substring);
            assert Objects.equals(res, List.of(5L, 6L));
        } finally {
            deleteFile(filename);
        }
    }

    @Test
    public void testBigFile() throws IOException {
        String filename = "testBigFile.txt";
        String substring = "aabaa";

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filename),
                StandardCharsets.UTF_8)) {

            for (int c = 0; c < 3; c++) {  // 3 * 1GB
                for (long i = 0; i < (1L << 30); i++) { // 1GB
                    writer.write(String.valueOf('0'));
                }
                writer.write(substring);
            }
            writer.close();

            ArrayList<Long> res = clsFind.find(filename, substring);
            assert Objects.equals(res, List.of(1L << 30, (2L << 30) + 5, (3L << 30) + 10));
        } finally {
            deleteFile(filename);
        }
    }
}
