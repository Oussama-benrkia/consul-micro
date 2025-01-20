package brk.micro.client.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private Float age;

    public Client(Long id, String nom, Float age) {
        this.id = id;
        this.nom = nom;
        this.age = age;
    }

    public Client() {
    }

    public static ClientBuilder builder() {
        return new ClientBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public Float getAge() {
        return this.age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAge(Float age) {
        this.age = age;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Client)) return false;
        final Client other = (Client) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$nom = this.getNom();
        final Object other$nom = other.getNom();
        if (this$nom == null ? other$nom != null : !this$nom.equals(other$nom)) return false;
        final Object this$age = this.getAge();
        final Object other$age = other.getAge();
        if (this$age == null ? other$age != null : !this$age.equals(other$age)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Client;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nom = this.getNom();
        result = result * PRIME + ($nom == null ? 43 : $nom.hashCode());
        final Object $age = this.getAge();
        result = result * PRIME + ($age == null ? 43 : $age.hashCode());
        return result;
    }

    public String toString() {
        return "Client(id=" + this.getId() + ", nom=" + this.getNom() + ", age=" + this.getAge() + ")";
    }

    public static class ClientBuilder {
        private Long id;
        private String nom;
        private Float age;

        ClientBuilder() {
        }

        public ClientBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ClientBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public ClientBuilder age(Float age) {
            this.age = age;
            return this;
        }

        public Client build() {
            return new Client(this.id, this.nom, this.age);
        }

        public String toString() {
            return "Client.ClientBuilder(id=" + this.id + ", nom=" + this.nom + ", age=" + this.age + ")";
        }
    }
}
