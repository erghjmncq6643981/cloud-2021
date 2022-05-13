package com.chandler.instance.client.example;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * MappedByteBuffer读取文件
 *
 * @author 钱丁君-chandler 2021/12/16 5:52 下午
 * @since 1.8
 */
public class MappedByteBufferTest {
    public static void main(String[] args) {
        File file = new File("/Users/chandler/Downloads/随记.txt");
        long len = file.length();
        byte[] ds = new byte[(int) len];
        try {
            MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "r")
                    .getChannel()
                    .map(FileChannel.MapMode.READ_ONLY, 0, len);
            for (int offset = 0; offset < len; offset++) {
                byte b = mappedByteBuffer.get();
                ds[offset] = b;
            }
            Scanner scan = new Scanner(new ByteArrayInputStream(ds));
            while (scan.hasNext()) {
                System.out.println(scan.next() + " ");
            }
        } catch (IOException e) {}

    }
}
