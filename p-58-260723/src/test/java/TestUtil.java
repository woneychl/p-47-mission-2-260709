import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TestUtil {
    private static PrintStream ORIGINAL_OUT = System.out;
    private static PrintStream CURRENT_OUT = System.out;

    public static Scanner genScanner(String input){
        return new Scanner(input);
    }
    public static ByteArrayOutputStream setOutToByteArray() {

        ORIGINAL_OUT = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        CURRENT_OUT = printStream;

        return outputStream;

    }

    public static void clearSetOutToByteArray(ByteArrayOutputStream outputStream) {
        System.setOut(ORIGINAL_OUT);
        try {
            outputStream.close();
        } catch (Exception e) {
            System.out.println("aaa");
        }
        CURRENT_OUT.close();
    }
}
