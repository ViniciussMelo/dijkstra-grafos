package file;

import java.io.*;

public class FileWrite {

    private BufferedWriter file;
    private String path;

    public static void main(String[] arr) {
        try {
            FileWrite file = new FileWrite("./config.txt");

            file.write("Hello");
            file.append("\n World");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileWrite(String path) throws IOException {
        this.path = path;
        openFile(false);
    }

    public void write(String value) throws IOException {
        this.openFile(false);
        this.file.write(value);
        this.file.close();
    }

    public void append(String value) throws IOException {
        this.openFile(true);
        this.file.append(value);
        this.file.close();
    }

    private void openFile(boolean append) throws IOException {
        this.file = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(path, append)
                )
        );
    }

    private void closeFile() throws IOException {
        this.file.close();
        this.file = null;
    }
}
