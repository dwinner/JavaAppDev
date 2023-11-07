/**
 * MulticastSender multicast_address port ttl
 * Sending packets to group
 * Example: java MulticastSender all-systems.mcast.net 4000
 */
package networking;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author Vinevtsev
 */
public class MulticastSender
{

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args)
   {
      InetAddress inetAddr = null;
      int port = 0;
      byte ttlValue = (byte) 1;

      // read the address from the command line
      try
      {
         inetAddr = InetAddress.getByName(args[0]);
         port = Integer.parseInt(args[1]);

         if (args.length > 2)
         {
            ttlValue = (byte) Integer.parseInt(args[2]);
         }
      }
      catch (NumberFormatException | IndexOutOfBoundsException | UnknownHostException ex)
      {
         System.err.println(ex);
         System.err.println("Usage: java networking.MulticastSender multicast_address port ttl");
         System.exit(1);
      }

      byte[] data = "Here's some multicast data\r\n".getBytes();
      DatagramPacket datagram = new DatagramPacket(data, data.length, inetAddr, port);

      try (MulticastSocket mcastSock = new MulticastSocket())
      {
         mcastSock.setTimeToLive(ttlValue);
         mcastSock.joinGroup(inetAddr);
         for (int i = 0; i < 10; i++)
         {
            mcastSock.send(datagram);
         }

         mcastSock.leaveGroup(inetAddr);
      }
      catch (SocketException sockEx)
      {
         System.err.println(sockEx);
      }
      catch (IOException ioEx)
      {
         System.err.println(ioEx);
      }
   }

}
