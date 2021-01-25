
import com.epam.preprod.pavlov.initialization.HttpServerContext;

public class HttpServerRunner {
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        HttpServerContext context = new HttpServerContext(port);
        context.start();
    }
}
