package ac.customer.controller;

import ac.customer.model.Client;
import ac.customer.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping(value="/clients")
    public ResponseEntity<?> create(@RequestBody Client client){
        clientService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping(value="/clients")
    public ResponseEntity<List<Client>> read(){
       final List clients= Collections.singletonList(clientService.readAll());
       return clients !=null && !clients.isEmpty()
               ? new ResponseEntity<>(clients,HttpStatus.OK)
               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(value="/clients/{id}")
    public ResponseEntity<? extends Object> read(@PathVariable(name="id") int id) {
        final Optional client = clientService.read(id);
        return client != null
                ? new ResponseEntity<Optional>(client,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping(value="/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name="id") int id, @RequestBody Client client){
        final boolean updated = clientService.update(client, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping(value="/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name="id") int id) {
        final boolean deleted = clientService.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
