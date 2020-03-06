package attribute;

public class ObjectControl extends AccessControl {
  private String id;
  private String name;
  private String[] subjects;
  private String[] divisions;

  public ObjectControl(String id, String name, String[] subjects, String[] divisions) {
    super();
    this.id = id;
    this.name = name;
    this.subjects = subjects;
    this.divisions = divisions;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID: " + id);
    sb.append("\nName: " + name);
    sb.append("\nSubjects: ");
    for (String sub : subjects) {
      sb.append(", " + sub);
    }
    sb.append("\nDivisions: ");
    for (String div : divisions) {
      sb.append(", " + div);
    }
    return sb.toString();
  }
}
