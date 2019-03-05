package com.mands.bloomreach.Model;

public class ExceptionResponse {
  private String message;
 
  public ExceptionResponse(String message) {
    super();
    this.setMessage(message);
  }
  public void setMessage(String message) {
	  this.message=message;
}
public String getMessage() {
    return message;
  }
}