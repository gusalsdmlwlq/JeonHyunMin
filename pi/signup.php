<?php
	$db = new mysqli('localhost','pi','pi','pi');
	$id = $_GET["id"];
	$pw = $_GET["pw"];
	$name = $_GET["name"];
	$query = "select * from user where id='".$id."'";
	$result = mysqli_query($db,$query);
	$row = mysqli_fetch_array($result);
	if(is_null($row["id"])){
		$query = "insert into user values('".$id."','".$pw."','".$name."')";
		$result = mysqli_query($db,$query);
		$query = "commit";
		$result = mysqli_query($db,$query);
		header("Content-type: application/xml");
		$xml = new DOMDocument('1.0','UTF-8');
		$tag = $xml->createElement("user");
		$xml->appendChild($tag);
		$idtag = $xml->createElement("id",$id);
		$pwtag = $xml->createElement("pw",$pw);
		$nametag = $xml->createElement("name",$name);
		$tag->appendChild($idtag);
		$tag->appendChild($pwtag);
		$tag->appendChild($nametag);
		print $xml->saveXML();
	}
?>