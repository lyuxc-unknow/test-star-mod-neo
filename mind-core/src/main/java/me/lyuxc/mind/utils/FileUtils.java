package me.lyuxc.mind.utils;

import me.lyuxc.mind.Variables;

import java.io.*;

public class FileUtils {
    public static final String configFolder = Variables.workDir + "/mind";

    /**
     * 创建文件
     */
    public static void createFiles() {
        File file = new File(configFolder);
        if(file.mkdir()) {
            Variables.LOGGER.info("folder is be created");
        }
    }

    /**
     * 写入到新文件
     * @param fileName 文件名字
     * @param text 写入的文本
     * @param newLine 是否从新一行开始
     */
    public static void writeToNewFile(String fileName,String text,boolean newLine){
        writeToNewFile(new File(configFolder , fileName),text,newLine);
    }

    /**
     * 写入字符串数组到文件
     * @param fileName 文件名
     * @param text 字符串数组
     * @param newLine 是否都在新一行开始写入
     */
    @SuppressWarnings("unused")
    public static void writeToNewFile(String fileName,String[] text,boolean newLine) {
        writeToNewFile(new File(configFolder , fileName),text,newLine);
    }

    /**
     * 写入字符串输入到文件
     * @param file 文件类型变量
     * @param text 字符串数组
     * @param newLine 是否新的一行
     */
    @SuppressWarnings("unused")
    public static void writeToNewFile(File file, String[] text, boolean newLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for(String text1: text) {
                if (newLine) {
                    writer.newLine();
                }
                writer.write(text1);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    /**
     * 写入字符串输入到文件
     * @param file 文件类型变量
     * @param text 字符串
     * @param newLine 是否新的一行
     */
    public static void writeToNewFile(File file,String text,boolean newLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            if (newLine) {
                writer.newLine();
            }
            writer.write(text);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    /**
     * 从文件读入内容
     * @param fileName 文件名
     * @param isForce 是否强制读取
     * @return 读取到的内容
     * @throws FileNotFoundException 文件未找到异常
     */
    public static String readFromFile(String fileName,boolean isForce) throws FileNotFoundException {
        return readFromFile(new File(configFolder , fileName),isForce);
    }

    /**
     * 从文件读入内容
     * @param file 文件类型变量
     * @param isForce 是否强制读取
     * @return 读取到的内容
     * @throws FileNotFoundException 文件未找到异常
     */
    public static String readFromFile(File file,boolean isForce) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        if(file.canRead() || isForce){
            return readContext(reader).toString();
        }
        return "";
    }

    /**
     * 读取变量
     * @param reader 从缓存区读取变量
     * @return 读取到的数据
     */
    private static StringBuilder readContext(BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        String s;
        try {
            while((s = reader.readLine()) != null) {
                if(!s.startsWith("//") && !s.startsWith("#")) {
                    sb.append(s);
                    sb.append(System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return sb;
    }
}
