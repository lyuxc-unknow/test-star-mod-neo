package me.lyuxc.develop.utils;

import me.lyuxc.develop.Variables;

import java.io.*;

public class FileUtils {
    public static final String configFolder = Variables.workDir + "/mind/";
    public static void writeToNewFile(String fileName,String text,boolean newLine){
        writeToNewFile(new File(configFolder + fileName),text,newLine);
    }

    public static void writeToNewFile(String fileName,String[] text,boolean newLine) {
        writeToNewFile(new File(configFolder + fileName),text,newLine);
    }

    public static void writeToNewFile(File file, String[] text, boolean newLine) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(String text1: text) {
                if (newLine) {
                    writer.newLine();
                }
                writer.write(text1);
                writer.close();
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public static void writeToNewFile(File file,String text,boolean newLine) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            if (newLine) {
                writer.newLine();
            }
            writer.write(text);
            writer.close();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public static String readFromFile(String fileName,boolean isForce) throws FileNotFoundException {
        return readFromFile(new File(configFolder + fileName),isForce);
    }

    public static String readFromFile(File file,boolean isForce) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        if(file.canRead() || isForce){
            return readContext(reader).toString();
        }
        return "null";
    }

    private static StringBuilder readContext(BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        String s;
        try {
            while((s = reader.readLine()) != null) {
                sb.append(s);
                sb.append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return sb;
    }
}
