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
import hello.models.*;

@RestController
public class PersoanaController {
  private List<Persoana> persoane = new ArrayList<Persoana>();

  PersoanaController() {
    Adresa a1 = new Adresa("Bucuresti", "Fraului", 18);
    Adresa a2 = new Adresa("Brasov", "Fantanica", 32);
    Adresa a3 = new Adresa ("Bucuresti", "Mihai Bravu", 38);
    Persoana p1 = new Persoana(1, "John", a1);
    Persoana p2 = new Persoana(2, "Paul", a2);
    Persoana p3 = new Persoana(3, "Paul", a3);

    persoane.add(p1);
    persoane.add(p2);
    persoane.add(p3);
  }

  @RequestMapping(value="/persoana", method = RequestMethod.GET)
  public List<Persoana> index() {
    return this.persoane;
  }

  @RequestMapping(value="/persoana/{id}", method = RequestMethod.GET)
  public ResponseEntity show(@PathVariable("id") int id) {
    for(Persoana p : this.persoane) {
      if(p.getId() == id) {
        return new ResponseEntity<Persoana>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
  
  //cauta persoane dintr-un oras dat ca parametru
  @RequestMapping(value="/persoana/oras", method = RequestMethod.GET)
  public List<Persoana> show(@RequestParam(value="oras", defaultValue="Oras") String oras) {
   List<Persoana> pers = new ArrayList<Persoana>();
    for(Persoana p : this.persoane) {
      if(p.getAddress().getOras().equals(oras)) {
       pers.add(p);
      }
    }
    return pers;
  }
  
  @RequestMapping(value="/persoana/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Persoana p : this.persoane) {
      if(p.getId() == id) {
        this.persoane.remove(p);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
  
  @RequestMapping(value="/persoana", method = RequestMethod.POST)
  public ResponseEntity create(@RequestParam(value="name", defaultValue="Elena") String name,
		               @RequestParam(value="oras", defaultValue="Oras") String oras,
  			       @RequestParam(value="stada", defaultValue="Strada") String strada,
  			       @RequestParam(value="numar", defaultValue="0") int numar) {
  
  Adresa adr = new Adresa(String.format(oras), String.format(strada), numar);
  Persoana newPerson = new Persoana(this.persoane.size() + 1,String.format(name), adr);
  persoane.add(newPerson);
  String numePersoana = newPerson.getName();
  for(Persoana p : this.persoane) {	
  	if(p.getName().equals(numePersoana)) {
        	return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.OK);
        }
   }
	return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);	
}  
 
  @RequestMapping(value="/persoana/{id}", method = RequestMethod.PUT)
  public ResponseEntity update(@PathVariable("id") int id , @RequestParam(value="name", defaultValue="Updated Name") String newName,
		 	       @RequestParam(value="oras", defaultValue="Oras") String oras,
			       @RequestParam(value="stada", defaultValue="Strada") String strada,
			       @RequestParam(value="numar", defaultValue="0") int numar) {
  Adresa adr = new Adresa(String.format(oras), String.format(strada), numar);
  for(Persoana p : this.persoane) {
      if(p.getId() == id) {
        p.setName(newName);
        p.setAddress(adr);
	return new ResponseEntity<Persoana>(p, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  } 
}
