package com.toec.suncx.phonesafer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

/**
 * Created by admin on 2016/10/26.
 */
public class StreamUtil {

    public static String parserStreamUtil(InputStream is) throws IOException {
        //字符流读取
        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        //写入流
        StringWriter sw=new StringWriter();
        //读写流
        String str=null;
        while((str=br.readLine())!=null){
            sw.write(str);
        }
        //关流
        br.close();
        sw.close();
        return sw.toString();

    }
}
