package me.lyuxc.develop.utils;

import java.io.*;

public class ReadOrWriteFile {
    public static void writeToNewFile(String fileName,String connectText) {
        File file = new File(fileName);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            if (!file.createNewFile()) {
                writer.newLine();
            }
            writer.write(connectText);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder sb = new StringBuilder();
        String s;
        try {
            if(file.canRead()){
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while((s = reader.readLine()) != null) {
                    sb.append(s);
                    sb.append(System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
