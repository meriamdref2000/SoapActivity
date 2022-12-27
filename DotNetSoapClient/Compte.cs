namespace DotNetSoapClient
{
    public class Compte
    {
        public int code;
        public double solde;
        
        public override string ToString()
        {
            return "Code: " + code + " Solde: " + solde;
        }
    }
}