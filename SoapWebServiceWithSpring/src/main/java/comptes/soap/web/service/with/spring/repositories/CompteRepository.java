package comptes.soap.web.service.with.spring.repositories;

import comptes.jax.ws.web.service.Compte;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class CompteRepository {

    private static final List<Compte> comptes = new ArrayList<>();

    public List<Compte> getAllComptes() {
        return comptes;
    }
    public double convert(double montant) {
        return montant * 10.54;
    }
    public Compte getCompte(Long code) {
        return new Compte(code, Math.random() * 9888, new Date());
    }

}
