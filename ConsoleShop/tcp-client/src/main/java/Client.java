import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    private static String endMessageKey = "";
    private static final String STOP = "stop";
    private static Socket socket;

    public static void main(String[] args) {
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            socket = new Socket(ip, port);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                 BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
                requestEndKey(reader);
                String clientQuestion;
                while (true) {
                    String answer = getServerAnswer(reader);
                    System.out.println(answer);
                    clientQuestion = consoleReader.readLine();
                    if (STOP.equals(clientQuestion)) {
                        writer.write(clientQuestion + System.lineSeparator());
                        writer.flush();
                        break;
                    }
                    writer.write(clientQuestion + System.lineSeparator());
                    writer.flush();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getServerAnswer(BufferedReader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        String answer;
        while (true) {
            answer = reader.readLine();
            if (endMessageKey.equals(answer)) {
                break;
            }
            builder.append(answer).append(System.lineSeparator());
        }
        return builder.toString().substring(0, builder.length() - 1);
    }

    private static void requestEndKey(BufferedReader reader) throws IOException {
        endMessageKey = reader.readLine();
    }
}
