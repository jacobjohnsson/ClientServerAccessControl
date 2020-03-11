package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileDatabase implements Database {
  private static final String ERRORMESSAGE = "COULD NOT FIND DATABASE";

  private String path;

  public FileDatabase(String path) {
    this.path = path;
  }

  @Override
  public String create(String patientID, String patientName, String content, String doctor, String[] nurses) {
    return "";
  }

  @Override
  public String read(String patientID) {
    Scanner scanner;
    try {
      scanner = getScanner(patientID);
    } catch(Exception e) {
      e.printStackTrace();
      return ERRORMESSAGE;
    }
    StringBuilder sb = new StringBuilder();
    while (scanner.hasNext()) {
      sb.append(scanner.next());
    }
    return sb.toString();
  }

  @Override
  public String write(String patientID, String content) {
    File file = null;
    try {
      file = getFile(patientID);
      FileWriter writer = new FileWriter(file);
      writer.write(content);
    } catch (IOException e) {
      e.printStackTrace();
      return ERRORMESSAGE;
    }
    return "Content successfully written.";
  }

  @Override
  public String delete(String patientID) {
    return "";
  }

  private File getFile(String patientID) throws FileNotFoundException {
    return new File(path + "/" + patientID + ".txt");
  }

  private Scanner getScanner(String patientID) throws FileNotFoundException {
    return new Scanner(getFile(patientID));
  }

}
