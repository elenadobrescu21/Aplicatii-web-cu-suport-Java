package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.ArrayList;

@RestController
public class MagazinController {
  private List<Magazin> magazine = new ArrayList<Magazin>();

	MagazinController() {
    Magazin m1 = new  Magazin(1, "Altex", "Mihai Bravu 2");
    Magazin m2 = new  Magazin(2, "Cora" , "Pantelimon");
    Magazin m3 = new  Magazin(3, "Afi", "Iuliu Maniu");

    magazine.add(m1);
    magazine.add(m2);
    magazine.add(m3);
  }

  @RequestMapping(value="/magazin", method = RequestMethod.GET)
  public List<Magazin> index() {
    return this.magazine;
  }

  @RequestMapping(value="/magazin/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id) {
    for(Magazin m : this.magazine) {
      if(m.getId() == id) {
        return new ResponseEntity<Magazin>(m, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/magazin/{id}", method = RequestMethod.DELETE)
  public void remove(@PathVariable("id") int id) {
    for(Magazin m : this.magazine) {
      if(m.getId() == id) {
        this.magazine.remove(m);
        //return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
   // return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
  
  @RequestMapping(value="/magazin", method = RequestMethod.POST)
  public ResponseEntity create(@RequestParam(value="name", defaultValue="magazin") String name, @RequestParam(value="address", defaultValue="adresa") String address) {
	Magazin newMagazin = new Magazin(this.magazine.size() + 1,String.format(name), String.format(address));
	magazine.add(newMagazin);
	String numeMagazin = newMagazin.getName();
	for(Magazin m : this.magazine) {	
		if(m.getName().equals(numeMagazin)) {
            return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.OK);
        }
	}
	return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }  
  
  @RequestMapping(value="/magazin/{id}", method = RequestMethod.PUT)
  public ResponseEntity update(@PathVariable("id") int id , @RequestParam(value="name", defaultValue="Updated Name") String newName, @RequestParam(value="address", defaultValue="adresa") String address) {
    for(Magazin m : this.magazine) {
      if(m.getId() == id) {
        m.setName(newName);
		m.setAddress(address);
		return new ResponseEntity<Magazin>(m, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  } 
}