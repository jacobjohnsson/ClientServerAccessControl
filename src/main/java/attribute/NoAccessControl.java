package attribute;

public class NoAccessControl extends AccessControl {
  
  @Override
  public String toString() {
    return "No Access!";
  }
  
  
  
  @Override
  public boolean hasAccess(Subject subject, ObjectControl.AccessRight right) {
    return false;
  }
  
  @Override
  public String getId() {
    return "No Access control getId().";
  }
}
