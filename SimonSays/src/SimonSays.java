import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by s422 on 03/04/2018.
 */
public class SimonSays {
    static ArrayList<Integer> responseID = new ArrayList<>();
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(36000);
            Scanner kbd = new Scanner(System.in);
            int[] tally = {0, 0, 0};
            ArrayList<PrintWriter> outList = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                Socket client = server.accept();
                PrintWriter out = new PrintWriter(client.getOutputStream());
                outList.add(out);
                Player p = new Player(client, i);
                p.start();
                System.out.println("Player " + i + " Connected");
            }

            while (true) {
                System.out.println("Input: ");
                String simonInput = kbd.nextLine();

                for (PrintWriter out : outList) {
                    out.println("Simon says: " + simonInput);
                    out.flush();
                }
                responseID.clear();

                while (responseID.size() != 1) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int clientID = responseID.get(0);
                tally[clientID]++;
                System.out.println("Score: " + tally[clientID]);

                if (tally[clientID] == 3) {
                    for (PrintWriter out : outList) {
                        out.println("Player " + clientID + " won!");
                        break;
                    }
                } else {
                    for (PrintWriter out : outList) {
                        out.println("Player " + clientID + " was first");
                    }
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class Player extends Thread {
        Socket socket;
        int ID;

        public Player(Socket socket, int ID) {
            this.socket = socket;
            this.ID = ID;
        }
        public void run(){
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while (true) {
                    System.out.println("Player " + ID + ": " + input.readLine());
                    responseID.add(ID);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
