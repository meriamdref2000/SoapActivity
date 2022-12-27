import proxy.BanqueSerice;
import proxy.BanqueWS;
import proxy.Compte;

public class Client {

    public static void main(String[] args) {
        BanqueSerice stub = new BanqueWS().getBanqueSericePort();
        System.out.println(stub.convert(100));
        Compte compte = stub.getCompte(1);
        System.out.println(compte.getCode());
        System.out.println(compte.getSolde());

    }
}

