package brk.micro.voiture;

import brk.micro.voiture.entities.Voiture;
import brk.micro.voiture.model.Client;
import brk.micro.voiture.repositories.VoitureRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VoitureApplication {
    @Value("${app.client.host}")
    private String host;

    @Value("${app.client.port}")
    private String port;

    public String getFullUrl() {
        return "http://" + host + ":" + port + "/Client";
    }
    public static void main(String[] args) {
        SpringApplication.run(VoitureApplication.class, args);
    }
    @Bean
    public CommandLineRunner initializeData(VoitureRepo voitureRepo, WebClient.Builder webClientBuilder) {
        return args -> {
            try {
                Client client = webClientBuilder.build().get().uri(getFullUrl() + "/api/clients/" + 1)
                        .retrieve().bodyToMono(Client.class).block();
                if (client != null) {
                    voitureRepo.save(new Voiture("123ABC", "Toyota", "Corolla", "2020", client));
                    voitureRepo.save(new Voiture("456DEF", "Ford", "Focus", "2021", client));
                    voitureRepo.save(new Voiture("789GHI", "BMW", "X5", "2022", client));
                    System.out.println("Données initiales chargées avec succès");
                }
            } catch (Exception e) {
                System.err.println("Erreur lors de l'initialisation des données: " + e.getMessage());
            }
        };
    }
}
