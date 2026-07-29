import com.back.global.AppContext;

public class Main {
    public static void main(String[] args) {
        AppContext.init(true);
        App app = new App();
        app.run();
    }



}