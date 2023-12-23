package me.lyuxc.develop.utils;

import java.io.*;

public class ReadOrWriteFile {
    public static void writeToNewFile(String fileName,String text,boolean newLine) {
        writeToNewFile(new File(fileName),text,newLine);
    }

    public static void writeToNewFile(String fileName,String[] text,boolean newLine) {
        writeToNewFile(new File(fileName),text,newLine);
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
        } catch (IOException e) {
            e.printStackTrace();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile(String fileName,boolean isForce) {
        return readFromFile(new File(fileName),isForce);
    }

    public static String readFromFile(File file,boolean isForce) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            if(file.canRead() || isForce){
                return readContext(reader).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return sb;
    }
}
