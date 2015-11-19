package hello;

import java.util.List;
import java.util.ArrayList;

public class Persoana {
  private String name;
  private int id;
  private Adresa address;

  public Persoana() {}

  public Persoana(int id, String name, Adresa a) {
      this.name = name;
      this.id = id;
      this.address = a;
  }
  
  public Adresa getAddress() {
	return address;
  }

  public void setAddress(Adresa address) {
	this.address = address;
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