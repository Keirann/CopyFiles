package CopyFiles;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
/*
 The time taken to complete File Stream Copy is 449598110
 The time taken to complete File Channel copy is 5770707
 The time taken to complete  Using Apache FileUtils 103516349
 The time taken to complete using Files.Copy is 114105521
 Created by kiranbr on 1/4/17.
 */

public class CopyFilesImpl {
    public static void main(String args[]) throws IOException, InterruptedException {
        //Copy Using FileStream
        long begin = System.nanoTime();
        File source = new File("/home/kiranbr/gitRepo/CopyFiles/source/set1/Astronomia.mp3");
        File dest = new File("/home/kiranbr/gitRepo/CopyFiles/dest/set1/Astronomia.mp3");
        copyFileUsingFileStream(source, dest);
        long end = System.nanoTime();
        System.out.println("The time taken to complete File Stream Copy is " + (end - begin));

        //Copy using channels
        begin = System.nanoTime();
        source = new File("/home/kiranbr/gitRepo/CopyFiles/source/set2/Astronomia.mp3");
        dest = new File("/home/kiranbr/gitRepo/CopyFiles/dest/set2/Astronomia.mp3");
        copyFileUsingChannels(source, dest);
        end = System.nanoTime();
        System.out.println("The time taken to complete File Channel copy is " + (end - begin));

        //Copy using Apache FileUtils
        begin = System.nanoTime();
        source = new File("/home/kiranbr/gitRepo/CopyFiles/source/set3/Astronomia.mp3");
        dest = new File("/home/kiranbr/gitRepo/CopyFiles/dest/set3/Astronomia.mp3");
        copyFileUsingFileUtils(source, dest);
        end = System.nanoTime();
        System.out.println("The time taken to complete  Using Apache FileUtils " + (end - begin));

        //Copy using Files.CopyFile
        begin = System.nanoTime();
        source = new File("/home/kiranbr/gitRepo/CopyFiles/source/set4/Astronomia.mp3");
        dest = new File("/home/kiranbr/gitRepo/CopyFiles/dest/set4/Astronomia.mp3");
        copyFileUsingFilesCopyFIle(source, dest);
        end = System.nanoTime();
        System.out.println("The time taken to complete using Files.Copy is " + (end - begin));
    }

    private static void copyFileUsingFileUtils(File source, File dest) throws IOException {
        FileUtils.copyFile(source,dest);
    }

    private static void copyFileUsingChannels(File source, File dest) throws IOException {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            FileChannel inputChannel = new FileInputStream(source).getChannel();
            FileChannel outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel,0,1024);
        } finally {
            inputStream = null;
            outputStream = null;
        }
    }

    private static void copyFileUsingFilesCopyFIle(File source, File dest) throws IOException {
        Files.copy(source.toPath(),dest.toPath());
    }

    private static void copyFileUsingFileStream(File source, File dest) throws FileNotFoundException, IOException {

        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(source);
            outputStream = new FileOutputStream(dest);
            byte buf[] = new byte[1024];
            int readchar = 0;
            while ((readchar = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, readchar);
            }
        } finally {
            inputStream = null;
            outputStream = null;
        }

    }

}


