package com.yanghui.activemq.queue;

import org.junit.Test;

import java.io.*;

public class FileCopyTest {

    /**
     * 文件复制
     * @param oldPath
     * @param newPath
     */
    public static void copy(String oldPath, String newPath) {
        long one = System.currentTimeMillis();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            File two = new File(newPath);
            if (!two.getParentFile().exists()) {
                two.getParentFile().mkdirs();
            }
            inputStream = new FileInputStream(new File(oldPath));
            outputStream = new FileOutputStream(two);

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("testone" + Long.toString(System.currentTimeMillis() - one));
    }

    @Test
    public void testOne() {
       // copy("C:\\Users\\yanghui\\Desktop\\Mib导入\\1.txt", "C:\\Users\\yanghui\\Desktop\\Mib导入\\testOne.txt");
       // copyBuffer("C:\\Users\\yanghui\\Desktop\\Mib导入\\1.txt", "C:\\Users\\yanghui\\Desktop\\Mib导入\\testTwo.txt");

        fileCopy("C:\\Users\\yanghui\\Desktop\\Mib导入\\1.txt", "C:\\Users\\yanghui\\Desktop\\Mib导入\\testOne.txt");
    }

    /**
     * 文件复制带缓冲
     * @param oldFile
     * @param newFile
     */
    public static void copyBuffer(String oldFile, String newFile) {
        long one = System.currentTimeMillis();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            File file = new File(newFile);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            inputStream = new FileInputStream(new File(oldFile));
            outputStream = new FileOutputStream(file);

            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);

            byte[] bytess = new byte[1024];
            int len = 0;
            while ((len = bufferedInputStream.read(bytess)) > 0) {
                bufferedOutputStream.write(bytess, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - one);
    }


    public static void fileCopy(String startPath,String endPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(startPath);
            fos = new FileOutputStream(endPath);
            byte[] b = new byte[10];
            int len = -1;
            while ((len = fis.read(b)) != -1) {
                fos.write(b, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null && fos != null) {
                    fis.close();
                    fos.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
