/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity1;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Activity1 {
    public static void main(String[] args) throws UnknownHostException {
        Scanner kbd = new Scanner(System.in);
        
        int loop = 1;
        System.out.println("Search for hostnames? [Y/N]");
        String choice = kbd.nextLine();
        while("Y".equals(choice)){
            
            
            System.out.print("Host "+ loop + "– Type IP address/Hostname: ");
            String host = kbd.nextLine();
            InetAddress[] ipAddress = InetAddress.getAllByName(host);
            System.out.println("Number of Hosts/IPs:" + ipAddress.length);
            System.out.printf("%s","IpAddress\n");
            for (int i = 0; i < ipAddress.length; i++){
                String ipAdd = ipAddress[i].getHostAddress();
                System.out.printf("%s", ipAdd + "\n");
            }
            loop++;
            System.out.println("Search for another?: [Y/N]");
            choice = kbd.nextLine();
        }
    }
}
