import jakarta.xml.ws.Endpoint;
import ws.BanqueSerice;


public class ServerJWS {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9191/", new BanqueSerice());
        System.out.println("Web service déployé sur http://0.0.0.0:9191/");
    }
}
