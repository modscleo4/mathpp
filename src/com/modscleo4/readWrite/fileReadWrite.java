/**
 * Created by Modscleo4.
 * Feel free to use this class, or modify it.
*/
package com.modscleo4.readWrite;

import com.modscleo4.mathpp.settings.GlobalSettings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class fileReadWrite {
    // This is for read the content of a file.
    public static String readLine(int line) throws IOException {
        String textFile = "text";
        textFile += GlobalSettings.lang;
        textFile += ".txt";
        return Files.readAllLines(Paths.get(textFile)).get(line);
    }
    public static String fileRead(String filename) throws IOException {
        String content = null;
        File file = new File(filename); //for ex foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader !=null) {
                reader.close();
            }
        }
        return content;
    }

    // This overload is for when you don't need to create a new line.
    public static int fileWrite(String filename, String content) {
        try {
            File statText = new File(filename);
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            Writer w = new BufferedWriter(osw);

            w.write(content);
            w.close();
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }
    // This overload is for when you need to create a new line.
    public static int fileWrite(String filename, String content, String separator) {
        try {
            File statText = new File(filename);
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);
            BufferedWriter w = new BufferedWriter(osw);

            String[] parts = content.split(separator);
            for (String part: parts) {
                w.write(part);
                w.newLine();
            }
            w.close();
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }
}
