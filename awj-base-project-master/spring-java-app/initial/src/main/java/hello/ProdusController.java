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
public class ProdusController {
  private List<Produs> produse = new ArrayList<Produs>();

  ProdusController() {
    Produs p1 = new Produs(1, "laptop");
    Produs p2 = new Produs(2, "tastatura");
    Produs p3 = new Produs(3, "mouse");

    produse.add(p1);
    produse.add(p2);
    produse.add(p3);
  }

  @RequestMapping(value="/produs", method = RequestMethod.GET)
  public List<Produs> index() {
    return this.produse;
  }

  @RequestMapping(value="/produs/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id) {
    for(Produs p : this.produse) {
      if(p.getId() == id) {
        return new ResponseEntity<Produs>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value="/produs/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Produs p : this.produse) {
      if(p.getId() == id) {
        this.produse.remove(p);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
  
  @RequestMapping(value="/produs", method = RequestMethod.POST)
  public ResponseEntity create(@RequestParam(value="name", defaultValue="produs nou") String name) {
	Produs newProdus = new Produs(this.produse.size() + 1,String.format(name));
	produse.add(newProdus);
	String numeProdus = newProdus.getName();
	for(Produs p : this.produse) {	
		if(p.getName().equals(numeProdus)) {
            return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.OK);
        }
	}
	return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }  
  
  @RequestMapping(value="/produs/{id}", method = RequestMethod.PUT)
  public ResponseEntity update(@PathVariable("id") int id , @RequestParam(value="name", defaultValue="Updated Name") String newName) {
    for(Produs p : this.produse) {
      if(p.getId() == id) {
        p.setName(newName);
		return new ResponseEntity<Produs>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  } 
}
