import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by s422 on 03/04/2018.
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 36000);
            System.out.println("Connected");
            Scanner kbd = new Scanner(System.in);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String simonSays;

            while ((simonSays = in.readLine()) != null) {
                System.out.println(simonSays);
                System.out.println("Answer: ");
                String input = kbd.nextLine();
                if (input.equalsIgnoreCase(simonSays)) {
                    System.out.println("Wrong answer, Please try again: ");
                    input = kbd.nextLine();
                }
                out.println(input);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
