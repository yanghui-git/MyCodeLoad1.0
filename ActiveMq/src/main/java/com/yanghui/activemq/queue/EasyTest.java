package com.yanghui.activemq.queue;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EasyTest {

    @Test
    public void testOne() {

        int a = 0;
        a = 1 + 1;
        System.out.println(a);

        System.out.println(" ".hashCode());


    }

    /**
     * 打包成tar
     *
     * @param sourcesFile
     * @param endName
     * @param url
     */
    public static void packTar(List<String> sourcesFile, String endName, String url) {
        List<File> sources = new ArrayList<>();
        sourcesFile.stream().forEach(file -> sources.add(new File(url, file)));
        File target = new File(url, endName);
        FileOutputStream out = null;
        TarArchiveOutputStream os = null;
        InputStream inputStream = null;
        try {
            out = new FileOutputStream(target);
            os = new TarArchiveOutputStream(out);
            for (File file : sources) {
                os.putArchiveEntry(new TarArchiveEntry(file, file.getName()));
                inputStream = new FileInputStream(file);
                IOUtils.copy(inputStream, os);
                os.closeArchiveEntry();
            }
        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                //e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
               // e.printStackTrace();
            }
            //删除源文件
            for (String delete:sourcesFile){
                new File(url,delete).delete();
            }
        }
    }

    @Test
    public void test() {
        List<String> name = new ArrayList<>();
        name.add("MibAllMessage.yml");
       // packTar(name, "22.tar", "D:\\桌面\\Mib\\mibs_f5");
        tarFileFunction("MibAllMessage.tar","D:\\桌面\\Mib\\mibs_f5");
    }



    /**
     * .tar 解压
     */
    public static void tarFileFunction(String oldFileName, String newFileName) {
        if (!oldFileName.endsWith(".tar")) {
            return;
        }
        File file = new File(newFileName, oldFileName);
        if (!file.exists()) {
            return;
        }
        TarArchiveInputStream tais = null;
        try {
            tais = new TarArchiveInputStream(new FileInputStream(file));
            TarArchiveEntry tae = null;
            while ((tae = tais.getNextTarEntry()) != null) {
                String dir = newFileName + File.separator + tae.getName();//tar档中文件
                File dirFile = new File(dir);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dirFile));
                int count;
                byte data[] = new byte[1024];
                while ((count = tais.read(data, 0, 1024)) != -1) {
                    bos.write(data, 0, count);
                }
                bos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (tais != null) {
                    tais.close();
                }
                //删除原文件
                boolean delete = file.delete();
                System.out.println("删除原始文件" + delete);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 解压成 tar.gz

     */
    public static void toTarGz(String path) throws IOException{
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
        GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(new BufferedOutputStream(new FileOutputStream(path + ".gz")));
        byte[] buffer = new byte[1024];
        int read = -1;
        while((read = bis.read(buffer)) != -1){
            gcos.write(buffer, 0, read);
        }
        gcos.close();
        bis.close();
        //删除tar
        new File(path).delete();
    }

    @Test
    public void tes(){
//        try {
//            toTarGz("D:\\桌面\\Mib\\test1.tar");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
      //  tarGzFileFunction("D:\\桌面\\Mib\\test1.tar.gz","D:\\桌面\\Mib\\");

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
        System.out.println(dateFormat.format(date));

    }




    /**
     * .gz文件解压
     */
    public static void tarGzFileFunction(String oldFileName, String newFileName) {
        if (!oldFileName.endsWith(".gz")) {
            return;
        }
        File sourceFile = new File(oldFileName);
        if (!sourceFile.exists()) {
            return;
        }
        // **tar.gz-->tar
        TarArchiveInputStream fin = null;
        try {
            fin = new TarArchiveInputStream(new GzipCompressorInputStream(new FileInputStream(sourceFile)));
            File extraceFolder = new File(newFileName);
            TarArchiveEntry entry;
            // 将 tar 文件解压到 extractPath 目录下
            while ((entry = fin.getNextTarEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }
                File curfile = new File(extraceFolder, entry.getName());
                File parent = curfile.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                // 将文件写出到解压的目录
                IOUtils.copy(fin, new FileOutputStream(curfile));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
                boolean delete = sourceFile.delete();
                System.out.println("删除解压缩原始文件" + delete);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
