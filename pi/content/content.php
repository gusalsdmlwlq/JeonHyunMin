<?php session_start(); ?>
<?php
	if($_GET["show"]=="list"){
		$db = new mysqli('localhost','pi','pi','pi');
		$query = "select id,title,name,day,today,view,recommend,comment from content order by day,today desc";
		$result = mysqli_query($db,$query);
		header("Content-type: application/xml");
		$xml = new DOMDocument('1.0','UTF-8');
		$contents = $xml->createElement("contents");
		$xml->appendChild($contents);
		while($row = mysqli_fetch_array($result)){
				$tag = $xml->createElement("content");
				$contents->appendChild($tag);
				$id = $xml->createElement("id",$row["id"]);
				$title = $xml->createElement("title",$row["title"]);
				$name = $xml->createElement("name",$row["name"]);
				$day = $xml->createElement("day",$row["day"]);
				$today = $xml->createElement("today",$row["today"]);
				$view = $xml->createElement("view",$row["view"]);
				$recommend = $xml->createElement("recommend",$row["recommend"]);
				$comment = $xml->createElement("comment",$row["comment"]);
				$tag->appendChild($id);
				$tag->appendChild($title);
				$tag->appendChild($name);
				$tag->appendChild($day);
				$tag->appendChild($today);
				$tag->appendChild($view);
				$tag->appendChild($recommend);
				$tag->appendChild($comment);	
		}
		print $xml->saveXML();
	}
	else{
		$db = new mysqli('localhost','pi','pi','pi');
		$query = "select * from content where id=".$_GET["content_id"];
		$result = mysqli_query($db,$query);
		header("Content-type: application/xml");
		$xml = new DOMDocument('1.0','UTF-8');
		$contents = $xml->createElement("contents");
		$xml->appendChild($contents);
		while($row = mysqli_fetch_array($result)){
				$tag = $xml->createElement("content");
				$contents->appendChild($tag);
				$id = $xml->createElement("id",$row["id"]);
				$title = $xml->createElement("title",$row["title"]);
				$name = $xml->createElement("name",$row["name"]);
				$day = $xml->createElement("day",$row["day"]);
				$today = $xml->createElement("today",$row["today"]);
				$view = $xml->createElement("view",$row["view"]);
				$recommend = $xml->createElement("recommend",$row["recommend"]);
				$comment = $xml->createElement("comment",$row["comment"]);
				$text = $xml->createElement("text",$row["text"]);
				$tag->appendChild($id);
				$tag->appendChild($title);
				$tag->appendChild($name);
				$tag->appendChild($day);
				$tag->appendChild($today);
				$tag->appendChild($view);
				$tag->appendChild($recommend);
				$tag->appendChild($comment);
				$tag->appendChild($text);
				if(explode("(",$row["name"])[0]==$_SESSION["id"]){
					$own = $xml->createElement("own","yes");
				}
				else{
					$own = $xml->createElement("own","no");
				}
				$tag->appendChild($own);
		}
		print $xml->saveXML();
	}
?>