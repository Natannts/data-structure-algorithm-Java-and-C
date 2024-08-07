import java.io.File;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

class TP01Q09ArquivoJava {
    public static void main(String[] args) throws Exception {
        try {
            int n = MyIO.readInt();
            RandomAccessFile put = new RandomAccessFile("out.txt", "rw");
            for (int i = 0; i < n; i++) {
                put.seek(i * 8);
                put.writeDouble(MyIO.readDouble());
            }
            put.close();

            RandomAccessFile raf = new RandomAccessFile("out.txt", "r");
            double printa = raf.readDouble();
            for (int i = n-1 ; i >= 0; i--) {
                raf.seek(i * 8);
                printa = raf.readDouble();
                if (printa % 1 == 0) {
                    MyIO.println((int) printa);
                } else
                    MyIO.println(printa);
            }
            raf.close();
        } catch (Exception EstourouMemoria) {
            MyIO.println("ERROR :X");
        }
    }
}