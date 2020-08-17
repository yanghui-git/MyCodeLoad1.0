package com.yanghui.activemq.queue;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RarTest {

    public static void Rar(String rarFile, String savePath) {
//判断是否rar文件
        if (!rarFile.endsWith(".rar")) {
            System.err.println("打开的文件不是rar文件！");
            return;
        }
        try {
            FileOutputStream fos = null;
//创建一个rar档案文件
            Archive rarArchive = new Archive(new File(rarFile));
//判断是否有加密
            if (rarArchive != null) {
                if (rarArchive.isEncrypted()) {
                    rarArchive.close();//关闭资源
                    return;
                }
                FileHeader fileHeader = rarArchive.nextFileHeader();
                while (fileHeader != null) {
                    if (!fileHeader.isDirectory()) {
//从压缩文件中解压出来的文件名，有可能带目录的文件名
                        String name = fileHeader.getFileNameString().trim();
                        String outPutFileName = savePath + "\\" + name;
                        //分离文件名，为了创建目录
                        File dir = new File(outPutFileName.substring(0, outPutFileName.lastIndexOf("\\")));
                        //创建文件夹
                        if (!dir.exists() || !dir.isDirectory()) {
                            dir.mkdirs();
                        }
                        fos = new FileOutputStream(new File(outPutFileName));
                        //保存解压的文件
                        rarArchive.extractFile(fileHeader, fos);
                        //关闭资源
                        fos.close();
                        fos = null;
                    }
                    fileHeader = rarArchive.nextFileHeader();
                }
            }
            rarArchive.close();//关闭资源
        } catch (RarException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
//        File file =new File("C:\\Users\\yanghui\\Desktop\\各厂家网络设备MIB库整理\\Cisco\\45 MIB\\");
//        System.out.println(file.exists());
//        Rar("C:\\Users\\yanghui\\Desktop\\jieyasuo\\1.rar", "C:\\Users\\yanghui\\Desktop\\jieyasuo\\1");
        System.out.println("123...zip.".replaceAll(".zip",""));
    }

    @Test
    public void yyy(){
        int a=1;
        try {
            a=1/0;
        } catch (Exception e) {
            System.out.println("11");
        }finally {
            System.out.println("22");
        }
        System.out.println("66666"+new File("C:\\Users\\yanghui\\Desktop\\Mib导入\\mib\\zip\\F5").delete());
        deleteFileFolder("C:\\Users\\yanghui\\Desktop\\Mib导入\\mib\\zip\\F5");

    }

    public static void deleteFileFolder(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    deleteFileFolder(f.getAbsolutePath());
                }
            }
        }
        file.delete();
    }
}
