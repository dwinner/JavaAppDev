/**
 * Multi cast sniffer
 * This program is useful primarily to verify that you are receiving multi cast data at a particular host
 * Usage: java MulticastSniffer 239.255.255.250 1900
 */
package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 *
 * @author Vinevtsev
 */
public class MulticastSniffer
{

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args)
   {
      InetAddress group = null;
      int port = 0;

      // read the address from the command line
      try
      {
         group = InetAddress.getByName(args[0]);
         port = Integer.parseInt(args[1]);
      }
      catch (ArrayIndexOutOfBoundsException | NumberFormatException | UnknownHostException ex)
      {
         System.err.println("Usage: java networking.MulticastSniffer multicast_address port");
         System.exit(1);
      }

      MulticastSocket mcastSock = null;
      try
      {
         mcastSock = new MulticastSocket(port);
         mcastSock.joinGroup(group);

         byte[] buffer = new byte[BUFFER_SIZE];
         while (true)
         {
            DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
            mcastSock.receive(datagram);
            String rcvString = new String(datagram.getData(), "8859_1");
            System.err.println(rcvString);
         }
      }
      catch (IOException ioEx)
      {
         System.err.println(ioEx);
      }
      finally
      {
         if (mcastSock != null)
         {
            try
            {
               mcastSock.leaveGroup(group);
               mcastSock.close();
            }
            catch (IOException ioEx)
            {
            }
         }
      }
   }

   private static final int BUFFER_SIZE = 8192;

}
