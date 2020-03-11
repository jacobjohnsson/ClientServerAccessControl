package attribute;

public class ObjectControl extends AccessControl {
  private String id;
  private String name;
  private String[] subjects;
  private String[] divisions;
  public enum AccessRight {NONE, READ, WRITE, CREATE, DELETE}

  public ObjectControl(String id, String name, String[] subjects, String[] divisions) {
    super();
    this.id = id;
    this.name = name;
    this.subjects = subjects;
    this.divisions = divisions;
  }
  
  public boolean hasAccess(Subject subject, AccessRight right) {
    switch (right) {
      case NONE:
        return false;
      case READ:
        return subjectsContains(subject.getId());
      case WRITE:
        return subjectsContains(subject.getId()) && subject.isDoctor();
      case DELETE:
        return subject.isGov();
    }
    return false;
  }
  
  public String getId() {
    return id;
  }
  
  private boolean subjectsContains(String subjectID) {
    for (String s : subjects) {
      if (s.equals(subjectID)) {
        return true;
      }
    }
    return false;
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
