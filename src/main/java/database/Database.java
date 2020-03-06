package database;

public interface Database {
  
  public void create(String patientID, String patientName, String content, String doctor, String[] nurses);
  public void read(String patientID);
  public void write(String patientID, String content);
  public void delete(String patientID);

}
