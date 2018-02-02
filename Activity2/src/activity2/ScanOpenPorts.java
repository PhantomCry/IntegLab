package activity2;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class scans the ports from an array to check if that port can connect
 * to the hostname.
 * 
 * 
 * 
 * @author Rizalde Jr. A. Velasco
 */
public class ScanOpenPorts {
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        ScanOpenPorts(kbd);
    }
    
    public static void ScanOpenPorts(Scanner kbd) {
        System.out.println("Enter a HostName: ");
        String server_name = kbd.nextLine();
        //20, 22, 23, 25, 53 , 67 ,69 ,80
        int[] ports = {20, 22, 23, 25, 53, 67, 69, 80};
        for (int port : ports) {
            try {
                Socket sock = new Socket(server_name, port);
                System.out.println("Connected to port" + port);
                sock.close();
            } catch (IOException e) {
                System.err.println("Cannot connect to " + server_name);
            }
        }
    }
    
}
