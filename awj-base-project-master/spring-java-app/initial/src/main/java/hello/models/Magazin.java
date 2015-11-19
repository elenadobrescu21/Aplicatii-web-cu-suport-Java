package hello;

import java.util.List;
import java.util.ArrayList;

public class Magazin{
  
  private int id;
  private String name;
  private String address;

  public Magazin() {}

  public Magazin(int id, String name, String address) {
      this.name = name;
      this.id = id;
      this.address = address;
  }

  public String getName() {
    return this.name;
  }

  public int getId() {
    return this.id;
  }
  
  public String getAddress() {
     return this.address;
  }
  
  public void setName(String name) {
	this.name = name;
  }
  
  public void setAddress(String address) {
	this.address = address;
  }
}
