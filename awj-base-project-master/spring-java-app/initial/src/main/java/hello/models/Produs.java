package hello;

import java.util.List;
import java.util.ArrayList;

public class Produs {
  
  private int id;
  private String name;

  public Produs() {}

  public Produs(int id, String name) {
    this.name = name;
    this.id = id;
  }

  public String getName() {
     return this.name;
  }

  public int getId() {
    return this.id;
  }
  
  public void setName(String name) {
	this.name = name;
  }
}