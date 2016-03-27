// Клиент.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class Client
{
    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            System.err.println("Usage: Client host port file");
            System.exit(0);
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String file = args[2];
        try
        {
            try (Socket sock = new Socket(host, port))
            {
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()), true);
                pw.println("POST " + file + " HTTP/1.1\n");
                BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                String line = null;
                line = br.readLine();
                StringTokenizer st = new StringTokenizer(line);
                String code = null;
                if ((st.countTokens() >= 2) && st.nextToken().equals("POST"))
                {
                    if ((code = st.nextToken()) != "200")
                    {
                        System.err.println("File not found, code = " + code);
                        System.exit(0);
                    }
                }
                while ((line = br.readLine()) != null)
                {
                    System.out.println(line);
                }
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}