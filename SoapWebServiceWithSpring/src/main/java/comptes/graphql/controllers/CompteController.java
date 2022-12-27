package comptes.graphql.controllers;

import comptes.jax.ws.web.service.Compte;
import comptes.soap.web.service.with.spring.repositories.CompteRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@AllArgsConstructor
public class CompteController {
    private CompteRepository compteRepository;

    @QueryMapping
    public List<Compte> getAllComptes() {
        return compteRepository.getAllComptes();
    }

    @QueryMapping
    public Compte getCompte(@Argument Integer code) {
        return compteRepository.getCompte(code.longValue());
    }

    @MutationMapping
    public Double convert(@Argument double montant) {
        return compteRepository.convert(montant);
    }
}
