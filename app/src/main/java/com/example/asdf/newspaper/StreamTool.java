package com.example.asdf.newspaper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by asdf on 2018/1/5.
 */

public class StreamTool {
    public static String decodeStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        int len=0;
        byte[] buf=new byte[10240];
        while ((len=inputStream.read(buf))>0){
            baos.write(buf,0,len);
        }
        String data= baos.toString();
        return data;
    }
}
