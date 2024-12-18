import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

public class UsingFile {
    private static final int INT_TYPE = 1;
    private static final int FLOAT_TYPE = 2;

    // Writing in the file
    public static void writeFile(Object num, File file) {
        try (RandomAccessFile writer = new RandomAccessFile(file, "rw")) {
            writer.seek(writer.length());
            if (num instanceof Integer) {
                writer.writeInt((Integer) num);
                writer.writeInt(INT_TYPE); 
            } else if (num instanceof Float) {
                writer.writeFloat((Float) num);
                writer.writeInt(FLOAT_TYPE); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reading the file in reverse order
    public static void readFile(File file) {
        try (RandomAccessFile reader = new RandomAccessFile(file, "r")) {
            long fileLength = reader.length();
            long position = fileLength;
            while (position > 0) {
                reader.seek(position);
                int type = reader.readInt();
                position -= Integer.BYTES;
                if (type == INT_TYPE) {
                    reader.seek(position);
                    int num = reader.readInt();
                    System.out.println(num);
                } else if (type == FLOAT_TYPE) {
                    reader.seek(position);
                    float num = reader.readFloat();
                    System.out.println(new DecimalFormat("#.##").format(num));
                }
                position -= Integer.BYTES;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int n = MyIO.readInt();
        File file = new File("float.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < n; i++) {
            String input = MyIO.readLine();
            if (input.contains(".")) {
                float num = Float.parseFloat(input);
                writeFile(num, file);
            } else {
                int num = Integer.parseInt(input);
                writeFile(num, file);
            }
        }
        readFile(file);
    }
}
