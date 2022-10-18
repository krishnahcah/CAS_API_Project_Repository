package utils;

import java.io.*;

public class CommonMethods {

    public static String FilePath = "src/main/java/ade/resources/";

    public static void setDataInFile(String filename, Object data) {
        PrintWriter pw = null;
        try {
            String path = System.getProperty("user.dir").replace("src", "") + "/";
            String fileName = path + FilePath + filename;
            System.out.println("write  to  fileName = " + fileName);
            File file = new File(fileName);
            pw = new PrintWriter(file);
            pw.write(data.toString().trim());
            pw.flush();
            pw.close();
        } catch (Exception e) {
            if (pw != null) {
                pw.close();
            }
            System.out.println("Exception Occured while writing " + filename + " file." + e.getMessage());
        }
    }

    public String getDataFromFile(String filename) {

        String fileName = null;
        String filevalue = null;
        try {
            fileName = FilePath + filename;
            filevalue = ReadFileInputTest(fileName).trim();
        } catch (Exception e) {
            System.out.println("Exception Occured while reading variable file " + fileName);
        }
        return filevalue;
    }

    public static String ReadFileInputTest(String FileName) throws IOException {
        String testInput = "";
        try (BufferedReader br = new BufferedReader(new FileReader(FileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            testInput = sb.toString();

        } catch (Exception e) {
            System.out.println("Some error occured While reading test Input File " + e);
        }
        return testInput;
    }
}
