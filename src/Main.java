import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server is running at http://127.0.0.1:3000");

        ServerSocket serverSocket = new ServerSocket(3000);
        while (true) {
            // Wait for the new connection and block the further execution.
            Socket socket = serverSocket.accept();

            // Great, browser is connected to our web server
            // Print writer is used to write buffered string text. It will not write to the socket until it's buffer is filled.
            // Writing to socket at once is faster than writing in very small chunks.
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            // Http Header starts
            // Http version and status code for the browser to handle response
            printWriter.write("HTTP/1.1 200 OK\r\n");
            // We are saying browser, this is a html file
            printWriter.write("Content-Type: text/html\r\n");
            // Http header ends with \r\n
            printWriter.write("\r\n");

            // We now write actual response
            printWriter.write("<h1>Hello World</h1>");
            // If buffered is filled, probably response is already sent. If it's waiting for the buffer to filled,
            // we need to flush it to make sure all the data is sent to the browser.
            printWriter.flush();

            // Closing socket is the simplest way to say to the browser that all data has been sent.
            socket.close();
        }
    }
}