package comptes.soap.web.service.with.spring.endpoints;

import comptes.jax.ws.web.service.*;
import comptes.soap.web.service.with.spring.repositories.CompteRepository;
import comptes.jax.ws.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;

@Endpoint
public class CompteEndpoint {

    private static final String NAMESPACE_URI = "http://service.web.ws.jax.com/";

    private CompteRepository countryRepository;

    @Autowired
    public CompteEndpoint(CompteRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCompte")
    @ResponsePayload
    public JAXBElement<GetCompteResponse> getCompte(@RequestPayload JAXBElement<GetCompte> request) {
        GetCompteResponse response = new GetCompteResponse();
        response.setReturn(countryRepository.getCompte(request.getValue().getCode()));
        return new ObjectFactory().createGetCompteResponse(response);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "Convert")
    @ResponsePayload
    public JAXBElement<ConvertResponse> convert(@RequestPayload JAXBElement<Convert> request) {
        ConvertResponse response = new ConvertResponse();
        response.setReturn(countryRepository.convert(request.getValue().getMontant()));
        return new ObjectFactory().createConvertResponse(response);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "compteList")
    @ResponsePayload
    public JAXBElement<CompteListResponse> getCompteList(@RequestPayload JAXBElement<CompteList> request) {
        CompteListResponse response = new CompteListResponse();
        response.getReturn().addAll(countryRepository.getAllComptes());
        return new ObjectFactory().createCompteListResponse(response);
    }
}
