import com.back.global.AppContext;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class AppTestRunner {

    public static String run(String input) {
        Scanner sc = TestUtil.genScanner(input + "종료");
        ByteArrayOutputStream outputStream = TestUtil.setOutToByteArray();

        AppContext.init(sc, true);
        new App().run();
        return outputStream.toString();
    }

}
