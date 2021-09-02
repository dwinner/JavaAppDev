// Сервер
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

class Server
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket ss = new ServerSocket(Integer.parseInt(args[0]));
            while (true)
            {
                new HttpConnect(ss.accept());
            }
        }
        catch (ArrayIndexOutOfBoundsException ae)
        {
            System.err.println("Usage: Server port");
            System.exit(0);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
}

class HttpConnect extends Thread
{
    private Socket sock;

    HttpConnect(Socket s)
    {
        sock = s;
        setPriority(NORM_PRIORITY - 1);
        start();
    }

    @Override
    public void run()
    {
        try
        {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String req = br.readLine();
            System.out.println("Request: " + req);
            StringTokenizer st = new StringTokenizer(req);
            if ((st.countTokens() >= 2) && st.nextToken().equals("POST"))
            {
                if ((req = st.nextToken()).endsWith("/") || req.equals(""))
                {
                    req += "index.html";
                }
                try
                {
                    File f = new File(req);
                    BufferedReader bfr = new BufferedReader(new FileReader(f));
                    char[] data = new char[(int) f.length()];
                    pw.println("HTTP/1.1 200 OK\n");
                    pw.write(data);
                    pw.flush();
                }
                catch (FileNotFoundException fe)
                {
                    pw.println("HTTP/1.1 404 Not Found\n");
                }
                catch (IOException ioe)
                {
                    System.out.println(ioe);
                }
            }
            else
            {
                pw.println("HTTP/1.1 400 Bad Request\n");
            }
            sock.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
}