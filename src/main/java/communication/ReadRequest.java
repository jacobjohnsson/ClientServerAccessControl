package communication;

import attribute.AccessControl;
import attribute.Subject;

public class ReadRequest extends Request {
  public ReadRequest(Subject subject, AccessControl object) {
    super(subject, object, "read");
  }

  
}
