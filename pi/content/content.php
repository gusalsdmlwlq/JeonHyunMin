<?php
	$db = new mysqli('localhost','pi','pi','pi');
	$query = "select * from content order by day desc";
	$result = mysqli_query($db,$query);
	header("Content-type: application/xml");
	$xml = new DOMDocument('1.0','UTF-8');
	$contents = $xml->createElement("contents");
	$xml->appendChild($contents);
	while($row = mysqli_fetch_array($result)){
			$tag = $xml->createElement("content");
			$contents->appendChild($tag);
			$title = $xml->createElement("title",$row["title"]);
			$name = $xml->createElement("name",$row["name"]);
			$day = $xml->createElement("day",$row["day"]);
			$view = $xml->createElement("view",$row["view"]);
			$recommend = $xml->createElement("recommend",$row["recommend"]);
			$comment = $xml->createElement("comment",$row["comment"]);
			$tag->appendChild($title);
			$tag->appendChild($name);
			$tag->appendChild($day);
			$tag->appendChild($view);
			$tag->appendChild($recommend);
			$tag->appendChild($comment);	
	}
	print $xml->saveXML();
?>