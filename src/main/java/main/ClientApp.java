package main;

import communication.MyClient;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import attribute.Subject;
import attribute.Object;


public class ClientApp {

  MyClient client;

  public ClientApp(MyClient client) {
    this.client = client;
  }

  private void run() {
    Scanner scanner = new Scanner(System.in);
    System.out.print(":>");
    String msg = scanner.nextLine();

    if (msg.toLowerCase().equals("quit")) {
      return;
    }

    String response = client.send(msg);
    System.out.println(response);
    run();
  }

  public static void main(String[] args) {
    ClientApp clientApp = new ClientApp(new MyClient("localhost", 9876));
    clientApp.run();
    List<Subject> subjects = new LinkedList<Subject>();
    List<Object> objects = new LinkedList<Object>();
    try {
      subjects = Subject.load("./src/main/resources/subjects.txt");
      objects = Object.load("./src/main/resources/objects.txt");
    } catch(Exception e) {
      e.printStackTrace();
    }
    System.out.println("---- Subjects ----");
    for (Subject s : subjects) {
      System.out.println(s);
    }
    System.out.println("---- Objects ----");
    for (Object o : objects) {
      System.out.println(o);
    }
  }
}
