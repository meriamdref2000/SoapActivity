using System;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Xml.Linq;

namespace DotNetSoapClient
{
    internal class Program
    {
        private static string url = "http://localhost:9191";

        public static void Main(string[] args)
        {
            GetAllComptesList();
            GetOneCompte(2);
            Convert(126);
        }

        public static void GetAllComptesList()
        {
            string getComptesList =
                @"<?xml version=""1.0"" encoding=""utf-8""?>
                <soapenv:Envelope xmlns:soapenv=""http://schemas.xmlsoap.org/soap/envelope/""
                xmlns:ws=""http://ws/"">
                <soapenv:Header/>
                <soapenv:Body>
                <ws:compteList/>
                </soapenv:Body>
                </soapenv:Envelope>";

            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(url);
            httpWebRequest.ContentType = "text/xml;charset=\"utf-8\"";
            httpWebRequest.Accept = "text/xml";
            httpWebRequest.Method = "POST";

            using (Stream stm = httpWebRequest.GetRequestStream())
            {
                using (StreamWriter streamWriter = new StreamWriter(stm))
                {
                    streamWriter.Write(getComptesList);
                }
            }

            WebResponse response = httpWebRequest.GetResponse();

            using (Stream stream = response.GetResponseStream())
            {
                if (stream != null)
                {
                    StreamReader reader = new StreamReader(stream, Encoding.UTF8);
                    String responseString = reader.ReadToEnd();
                    Console.WriteLine("The List Of All Accounts :\n");
                    Console.WriteLine(responseString);

                    XDocument doc = XDocument.Parse(responseString);
                    var result = from c in doc.Descendants("return")
                        select new Compte()
                        {
                            // parse to int
                            code = int.Parse(c.Element("code").Value),
                            solde = double.Parse(c.Element("solde").Value),
                        };

                    result.ToList().ForEach(Console.WriteLine);
                }

                Console.WriteLine("\n");
            }
        }

        public static void GetOneCompte(int code)
        {
            string body =
                @"<soapenv:Envelope xmlns:soapenv=""http://schemas.xmlsoap.org/soap/envelope/"" xmlns:ws=""http://ws/"">
                <soapenv:Header/>
                <soapenv:Body>
                <ws:getCompte>
                <code>" + code + @"</code>
                </ws:getCompte>
                </soapenv:Body>
                </soapenv:Envelope>";

            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(url);
            httpWebRequest.ContentType = "text/xml;charset=\"utf-8\"";
            httpWebRequest.Accept = "text/xml";
            httpWebRequest.Method = "POST";

            using (Stream stm = httpWebRequest.GetRequestStream())
            {
                using (StreamWriter streamWriter = new StreamWriter(stm))
                {
                    streamWriter.Write(body);
                }
            }

            WebResponse response = httpWebRequest.GetResponse();

            using (Stream stream = response.GetResponseStream())
            {
                if (stream != null)
                {
                    StreamReader reader = new StreamReader(stream, Encoding.UTF8);
                    String responseString = reader.ReadToEnd();
                    Console.WriteLine("The Account That Has The Code :" + code + "\n");
                    Console.WriteLine(responseString);
                    
                    XDocument doc = XDocument.Parse(responseString);
                    var result = from c in doc.Descendants("return")
                        select new Compte()
                        {
                            // parse to int
                            code = int.Parse(c.Element("code").Value),
                            solde = double.Parse(c.Element("solde").Value),
                        };

                    result.ToList().ForEach(Console.WriteLine);
                    
                    
                }

                Console.WriteLine("\n");
            }
        }


        public static void Convert(double montant)
        {
            string body =
                @"<soapenv:Envelope xmlns:soapenv=""http://schemas.xmlsoap.org/soap/envelope/"" xmlns:ws=""http://ws/"">
                <soapenv:Header/>
                <soapenv:Body>
                <ws:Convert>
                <montant>" + montant + @"</montant>
                </ws:Convert>
                </soapenv:Body>
                </soapenv:Envelope>";

            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(url);
            httpWebRequest.ContentType = "text/xml;charset=\"utf-8\"";
            httpWebRequest.Accept = "text/xml";
            httpWebRequest.Method = "POST";

            using (Stream stm = httpWebRequest.GetRequestStream())
            {
                using (StreamWriter streamWriter = new StreamWriter(stm))
                {
                    streamWriter.Write(body);
                }
            }

            WebResponse response = httpWebRequest.GetResponse();

            using (Stream stream = response.GetResponseStream())
            {
                if (stream != null)
                {
                    StreamReader reader = new StreamReader(stream, Encoding.UTF8);
                    String responseString = reader.ReadToEnd();
                    Console.WriteLine("The Response :\n");
                    Console.WriteLine(responseString);

                    XDocument doc = XDocument.Parse(responseString);
                    var result = from c in doc.Descendants("return")
                        select new Compte()
                        {
                            solde = double.Parse(c.Value),
                        };
                    Console.WriteLine(montant + " Euro = " + result.ToList()[0].solde + " MAD");
                }

                Console.WriteLine("\n");
            }
        }
    }
}