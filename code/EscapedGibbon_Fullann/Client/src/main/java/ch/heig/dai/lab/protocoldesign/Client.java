package ch.heig.dai.lab.protocoldesign;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Client {
    final String SERVER_ADDRESS = "1.2.3.4";
    final int SERVER_PORT = 1234;

    public static void main(String[] args) {

        // Create a new client and run it
        Client client = new Client();
        client.run();
    }

    private void run() {
      try(Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
      var in = new BufferedReader(
      new InputStreamReader(socket.getInputStream(),
      StandardCharsets.UTF_8));
      var out = new BufferedWriter(
      new OutputStreamWriter(socket.getOutputStream(),
      StandardCharsets.UTF_8))){
       String command = in.readLine();
       out.write(command);
       System.out.println(in.readLine());
    }catch (IOException e) {
      System.out.println("Client: exc.: " + e);
      }
    }
}