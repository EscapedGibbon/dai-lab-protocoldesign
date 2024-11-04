import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    // Supported operations as constants
    private static final String WELCOME_MESSAGE = "Welcome! Supported operations: ADD, SUB, MUL, DIV, POW, MOD, " + "SQRT, EXP, LN, SIN, COS, TAN, ABS, MAX, MIN, CEIL, FLOOR, ROUND";

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
        if (tokens.length < 2 || tokens.length > 3) {
            return "Error: Invalid format. Use <OPERATION> <OPERAND1> [OPERAND2]";
        }

        String operation = tokens[0].toUpperCase();
        double a;

        try {
            a = Double.parseDouble(tokens[1]);
            if (tokens.length == 2) {
                // Single-operand operations
                return "Result: " + calculate(operation, a);
            } else if (tokens.length == 3) {
                // Two-operand operations
                double b = Double.parseDouble(tokens[2]);
                return "Result: " + calculate(operation, a, b);
            }
        } catch (NumberFormatException e) {
            return "Error: Operands must be numeric.";
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            return "Error: " + e.getMessage();
        }

        return "Error: Invalid request format.";
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
            case "MAX": return Math.max(a, b);
            case "MIN": return Math.min(a, b);
            default: throw new UnsupportedOperationException("Unsupported operation");
        }
    }

    private static double calculate(String operation, double a) {
        switch (operation) {
            case "EXP": return Math.exp(a);
            case "LN":
                if (a <= 0) throw new IllegalArgumentException("Natural logarithm requires positive numbers");
                return Math.log(a);
            case "SQRT":
                if (a < 0) throw new IllegalArgumentException("Cannot calculate square root of a negative number");
                return Math.sqrt(a);
            case "SIN": return Math.sin(a);
            case "COS": return Math.cos(a);
            case "TAN": return Math.tan(a);
            case "CEIL": return Math.ceil(a);
            case "FLOOR": return Math.floor(a);
            case "ABS": return Math.abs(a);
            case "ROUND": return Math.round(a);
            default: throw new UnsupportedOperationException("Unsupported operation");
        }
    }

    private static long factorial(int n) {
        if (n == 0 || n == 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

}
