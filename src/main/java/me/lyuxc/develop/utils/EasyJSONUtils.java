package me.lyuxc.develop.utils;

public class EasyJSONUtils {
    public static String[][] readJSON(String fileName) {
        try {
            String[] s = ReadOrWriteFile.readFromFile(fileName,true).replace("{","").replace("}","").replace(System.lineSeparator(),"").split(",");
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            String[][] result = new String[2][];
            for(String s1 : s) {
                s1 = s1.replace("\"","").replace(" ","");
                String[] s2 = s1.split(":");
                sb1.append(s2[0]);
                sb1.append(System.lineSeparator());
                sb2.append(s2[1]);
                sb2.append(System.lineSeparator());
            }
            result[0] = sb1.toString().split(System.lineSeparator());
            result[1] = sb2.toString().split(System.lineSeparator());
            return result;
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return null;
    }
}
