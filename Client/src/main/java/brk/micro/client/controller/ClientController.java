package brk.micro.client.controller;

import brk.micro.client.model.Client;
import brk.micro.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable Long id) throws Exception {
        return clientRepository.findById(id)
                .orElseThrow(() -> new Exception("Client non trouvé"));
    }

    @PostMapping
    public Client save(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client updatedClient) throws Exception {
        return clientRepository.findById(id).map(client -> {
            client.setNom(updatedClient.getNom());
            client.setAge(updatedClient.getAge());
            return clientRepository.save(client);
        }).orElseThrow(() -> new Exception("Client non trouvé"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientRepository.deleteById(id);
    }

    @GetMapping("/nom/{nom}")
    public List<Client> findByNom(@PathVariable String nom) {
        return clientRepository.findByNom(nom);
    }
}
