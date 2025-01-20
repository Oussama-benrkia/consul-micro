package brk.micro.voiture.entities;

import brk.micro.voiture.model.Client;
import jakarta.persistence.*;

@Entity
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricule;
    private String marque;
    private String modele;  // Renommé de 'model' à 'modele'
    private String annee;   // Nouvel attribut
    private Long clientId;  // Pour stocker l'ID du client

    @Transient
    private Client client;

    // Constructeur vide
    public Voiture() {
    }

    // Constructeur avec paramètres pour création nouvelle voiture
    public Voiture(String matricule, String marque, String modele, String annee, Client client) {
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.client = client;
        this.clientId = client != null ? client.getId() : null;
    }

    // Constructeur complet avec ID
    public Voiture(Long id, String matricule, String marque, String modele, String annee, Long clientId, Client client) {
        this.id = id;
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.clientId = clientId;
        this.client = client;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
        this.clientId = client != null ? client.getId() : null;
    }
}