package com.xuzimian.globaldemo.common.utils.mycustom;

import org.junit.Test;

import java.io.*;

public class FileEncodeConvert {

    @Test
    public void test() throws IOException {
        convert(new File("C:\\Users\\AW\\Desktop\\proxy"),"gb2312","utf-8");
    }


    public static void convert(File file, String sourceCharset, String targetCharset) throws IOException {
        // 如果是文件则进行编码转换，写入覆盖原文件
        if (file.isFile()) {
            // 只处理.java结尾的代码文件
            if (file.getPath().indexOf(".java") == -1) {
                return;
            }
            InputStreamReader isr = new InputStreamReader(new FileInputStream(
                    file), sourceCharset);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                // 注意写入换行符
                sb.append(line + "\n");
            }
            br.close();
            isr.close();

            File targetFile = new File(file.getPath());
            OutputStreamWriter osw = new OutputStreamWriter(
                    new FileOutputStream(targetFile), targetCharset);
            BufferedWriter bw = new BufferedWriter(osw);
            // 以字符串的形式一次性写入
            bw.write(sb.toString());
            bw.close();
            osw.close();

            System.out.println("Deal:" + file.getPath());

        } else {
            for (File subFile : file.listFiles()) {
                convert(subFile, sourceCharset, targetCharset);
            }
        }
    }


}
