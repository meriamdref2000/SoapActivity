package comptes.rest.controllers;

import comptes.jax.ws.web.service.Compte;
import comptes.soap.web.service.with.spring.repositories.CompteRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api")
public class CompteRestController {
    private CompteRepository compteRepository;

    @GetMapping("comptes")
    public List<Compte> getAllComptes() {
        return compteRepository.getAllComptes();
    }

    @PostMapping("/comptes/convert")
    public Map<String, Object> convert(@RequestBody Map<String, Double> montant) {
        double montantConverted = montant.get("montant");
        return Map.of("valueAfterConversion", compteRepository.convert(montantConverted));
    }

    @GetMapping("/comptes/{code}")
    public Compte getCompte(@PathVariable Long code) {
        return compteRepository.getCompte(code);
    }
}
