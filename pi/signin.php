<?php
	$db = new mysqli('localhost','pi','pi','pi');
	$id = $_GET["id"];
	$pw = $_GET["pw"];
	$query = "select * from user where id='".$id."' and password='".$pw."'";
	$result = mysqli_query($db,$query);
	$row = mysqli_fetch_array($result);
	if(!is_null($row["id"])){
		header("Content-type: application/xml");
		$xml = new DOMDocument('1.0','UTF-8');
		$tag = $xml->createElement("user");
		$xml->appendChild($tag);
		$idtag = $xml->createElement("id",$id);
		$pwtag = $xml->createElement("pw",$pw);
		$nametag = $xml->createElement("name",$row["name"]);
		$tag->appendChild($idtag);
		$tag->appendChild($pwtag);
		$tag->appendChild($nametag);
		print $xml->saveXML();
	}
?>