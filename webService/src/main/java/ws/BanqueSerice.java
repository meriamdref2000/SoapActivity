package ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.Date;
import java.util.List;


//POJO : Plain Old Java Object (simple classe java)
@WebService(serviceName = "BanqueWS")
public class BanqueSerice {
    @WebMethod(operationName = "Convert")
    public double conversion(@WebParam(name = "montant") double mt) {
        return mt * 10.54;
    }

    @WebMethod
    public Compte getCompte(@WebParam(name = "code") int code) {
        return new Compte(code, Math.random() * 96000, new Date());
    }

    @WebMethod
    public List<Compte> compteList() {
        return List.of(
                new Compte(1, Math.random() * 96000, new Date()),
                new Compte(2, Math.random() * 96000, new Date()),
                new Compte(3, Math.random() * 96000, new Date()),
                new Compte(4, Math.random() * 96000, new Date()),
                new Compte(5, Math.random() * 96000, new Date()),
                new Compte(6, Math.random() * 96000, new Date())
        );
    }
}
