package ysoserial;

import org.jsoup.Connection;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class Base64Payload {
    @Test
    public void outPayload() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\00Code_JAVA\\ysoserial\\1.txt");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        int len;
        byte[] buffer = new byte[1024];
        while ((len = fis.read(buffer)) != -1) {
            bout.write(buffer, 0, len);
        }

        byte[] data = bout.toByteArray();

        System.out.println(Base64.getEncoder().encodeToString(data));
    }
}
