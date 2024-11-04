import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    // Supported operations as constants
    private static final String WELCOME_MESSAGE = "Welcome! Supported operations: ADD, SUB, MUL, DIV, POW, MOD";
    private static final int SERVER_PORT = 1234;
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server is listening on port " + SERVER_PORT);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    // Send welcome message to client
                    out.println(WELCOME_MESSAGE);

                    String clientMessage;
                    while ((clientMessage = in.readLine()) != null) {
                        // Check for termination command
                        if (clientMessage.trim().equalsIgnoreCase("EXIT")) {
                            out.println("Goodbye!");
                            break;
                        }

                        // Process client message
                        String response = processRequest(clientMessage);
                        out.println(response);
                    }
                } catch (IOException e) {
                    System.out.println("Client disconnected or error occurred: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    private static String processRequest(String request) {
        String[] tokens = request.trim().split("\\s+");

        // Check if the format is valid
        if (tokens.length != 3) {
            return "Error: Invalid format. Use <OPERATION> <OPERAND1> <OPERAND2>";
        }

        String operation = tokens[0].toUpperCase();
        double a, b;

        try {
            a = Double.parseDouble(tokens[1]);
            b = Double.parseDouble(tokens[2]);
        } catch (NumberFormatException e) {
            return "Error: Operands must be numeric.";
        }

        try {
            double result = calculate(operation, a, b);
            return "Result: " + result;
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            return "Error: " + e.getMessage();
        }
    }

    private static double calculate(String operation, double a, double b) {
        switch (operation) {
            case "ADD": return a + b;
            case "SUB": return a - b;
            case "MUL": return a * b;
            case "DIV":
                if (b == 0) throw new IllegalArgumentException("Division by zero");
                return a / b;
            case "POW": return Math.pow(a, b);
            case "MOD": return a % b;
            default: throw new UnsupportedOperationException("Unsupported operation");
        }
    }
}
