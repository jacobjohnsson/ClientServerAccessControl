package communication;

import attribute.AccessControl;
import attribute.ObjectControl;
import attribute.Subject;
import database.Database;

public class WriteRequest extends Request {
  
  private String content;
  
  public WriteRequest(Subject subject, AccessControl object, String description, String content) {
    super(subject, object, description);
    this.content = content;
  }
  
  @Override
  public String execute(Database db) {
    if (object.hasAccess(subject, ObjectControl.AccessRight.READ)) {
      return "Write to database: " + content;
    } else {
      return "No Access";
    }
  }
}
