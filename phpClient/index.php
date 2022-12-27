<?php
$soapUrl = "http://localhost:9191";
#$soapUrl = "http://localhost:8080/ws";

get_all_comptes($soapUrl);
echo "<br><br>";
get_one_compte($soapUrl, 1);
echo "<br><br>";
convert($soapUrl, 126);

function get_all_comptes($soapUrl) {
    $xml_post_string = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws/"><soapenv:Header/><soapenv:Body><ws:compteList/></soapenv:Body></soapenv:Envelope>';

    $headers = array("Content-Type: text/xml");
    $url = $soapUrl;
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $xml_post_string);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    $response = curl_exec($ch);
    curl_close($ch);
    $xml = simplexml_load_string($response);

  $dom = new DOMDocument();
  $dom->loadXML($xml->asXML());
  $items = $dom->getElementsByTagName('solde');
  $codes = $dom->getElementsByTagName('code');
  if ($items->length > 0) {

    // loop and show a HTML table of soles of evey nodevalue of $items
    echo '<table border="1">';
    echo '<tr><td>Code</td><td>solde</td></tr>';
    $i = 0;
    foreach ($items as $item) {
      echo '<tr><td>' . $codes[$i]->nodeValue . '</td><td>' . $item->nodeValue . '</td></tr>';
        $i++;
    }
    echo '</table>';

  }


}

function get_one_compte($soapUrl, $code) {
    $xml_post_string = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws/"><soapenv:Header/><soapenv:Body><ws:getCompte><code>'.$code.'</code></ws:getCompte></soapenv:Body></soapenv:Envelope>';
    $headers = array(
        "Content-Type: text/xml"
    );
    $url = $soapUrl;
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $xml_post_string);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    $response = curl_exec($ch);
    curl_close($ch);
    $xml = simplexml_load_string($response);
    echo $xml->asXML();
}

function convert($soapUrl, $montant) {
    $xml_post_string = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws/"><soapenv:Header/><soapenv:Body><ws:Convert><montant>'.$montant.'</montant></ws:Convert></soapenv:Body></soapenv:Envelope>';
    $headers = array(
        "Content-Type: text/xml"
    );
    $url = $soapUrl;
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $xml_post_string);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    $response = curl_exec($ch);
    curl_close($ch);
    $xml = simplexml_load_string($response);
    echo $xml->asXML();
}
?>
