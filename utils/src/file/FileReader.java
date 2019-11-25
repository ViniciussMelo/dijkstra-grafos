package file;

import java.io.*;
import java.util.ArrayList;

public class FileReader {

    private String filename;
    private BufferedReader file;

    private ArrayList<String> lines = new ArrayList<>();

    // Test class
    public static void main(String[] args) {
        try {
            FileReader file = new FileReader("config.txt");

            file.getAllLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileReader(String filename) throws IOException {
        this.filename = filename;
        read();
    }

    private void read() throws IOException {
        InputStream inputStream = new FileInputStream(this.filename);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        String line;

        this.file = new BufferedReader(inputStreamReader);

        while ((line = file.readLine()) != null) {
            lines.add(line);
        }

        file.close();
    }

    public ArrayList<String> getAllLines() throws IOException {
        return lines;
    }

    public String getLine(int line){
        return lines.get(line);
    }
}
