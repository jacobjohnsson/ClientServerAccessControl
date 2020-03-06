package main;

import communication.MyClient;
import java.util.Scanner;

public class ClientApp {

  MyClient client;

  public ClientApp(MyClient client) {
    this.client = client;
  }

  private void communicate() {
    Scanner scanner = new Scanner(System.in);
    System.out.print(":>");
    String msg = scanner.nextLine();

    if (msg.toLowerCase().equals("quit")) {
      return;
    }

    String response = client.send(msg);
    System.out.println(response);
    communicate();
  }

  public static void main(String[] args) {
    ClientApp clientApp = new ClientApp(new MyClient("localhost", 9876));
    clientApp.communicate();
  }
}
