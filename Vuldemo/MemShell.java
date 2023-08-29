package com.Crucis.Vuldemo;

import org.junit.Test;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.Base64;

public class MemShell {
    public static  void main(String[] args) throws IOException {
        //FileInputStream fin = new FileInputStream("D:\\00Code_JAVA\\LeetCode\\practice\\src\\main\\java\\com\\Crucis\\Vuldemo\\FilterMemShell.java");
        String filename = "D:\\00Code_JAVA\\LeetCode\\practice\\src\\main\\java\\com\\Crucis\\Vuldemo\\FilterMemShell.txt";
        byte[] MemShell = toByteArray3(filename);
        System.out.println(Base64.getEncoder().encode(MemShell));

        //byte[] MemShell =
    }

    public static byte[] toByteArray3(String filename) throws IOException {


        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }
}
