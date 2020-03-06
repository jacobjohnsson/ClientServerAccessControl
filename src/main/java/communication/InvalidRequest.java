package communication;

import attribute.AccessControl;
import attribute.ObjectControl;
import attribute.Subject;

public class InvalidRequest extends Request {
  public InvalidRequest(Subject subject, AccessControl object) {
    super(subject, object, "INVALID");
  }
}

