package database;

public interface Database {
  
  public String create(String patientID, String patientName, String content, String doctor, String[] nurses);
  public String read(String patientID);
  public String write(String patientID, String content);
  public String delete(String patientID);

}
