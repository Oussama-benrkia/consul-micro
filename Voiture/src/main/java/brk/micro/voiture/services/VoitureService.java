package brk.micro.voiture.services;

import brk.micro.voiture.model.Client;
import brk.micro.voiture.entities.Voiture;
import brk.micro.voiture.repositories.VoitureRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class VoitureService {

    private static final Logger logger = LoggerFactory.getLogger(VoitureService.class);

    private final VoitureRepo voitureRepo;

    private final WebClient.Builder webClient;

    @Value("${app.client.host}")
    private String host;

    @Value("${app.client.port}")
    private String port;

    public String getFullUrl() {
        return "http://" + host + ":" + port + "/Client";
    }
    public VoitureService(VoitureRepo voitureRepo, WebClient.Builder webClient) {
        this.voitureRepo = voitureRepo;
        this.webClient = webClient;
    }
    public List<Voiture> findAll() {
        logger.info("Fetching all voitures from the repository.");
        List<Voiture> voitures = voitureRepo.findAll();
        voitures.forEach(this::setClientForVoiture);
        return voitures;
    }
    public Optional<Voiture> findById(Long id) {
        logger.info("Fetching voiture with ID: {}", id);
        return voitureRepo.findById(id).map(voiture -> {
            setClientForVoiture(voiture);
            return voiture;
        });
    }
    public List<Voiture> findByClientId(Long clientId) {
        logger.info("Fetching voitures for client ID: {}", clientId);
        List<Voiture> voitures = voitureRepo.findAllByClientId(clientId);
        voitures.forEach(this::setClientForVoiture);
        return voitures;
    }
    private void setClientForVoiture(Voiture voiture) {
        String fullUrl = getFullUrl();
        logger.debug("Fetching client data from URL: {}", fullUrl);
        try {
            Client client = webClient.build()
                    .get()
                    .uri(fullUrl + "/api/clients/" + voiture.getClientId())
                    .retrieve()
                    .bodyToMono(Client.class)
                    .block();
            voiture.setClient(client);
            logger.debug("Client data successfully set for voiture with ID: {}", voiture.getId());
        } catch (Exception e) {
            logger.error("Failed to fetch client data for voiture with ID: {}. Error: {}", voiture.getId(), e.getMessage());
        }
    }
}
