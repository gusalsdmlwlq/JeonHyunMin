<?php session_start(); ?>
<?php
	if($_GET["show"]=="list"){
		$db = new mysqli('localhost','pi','pi','pi');
		if($_GET["search"]=="yes"){
			$query = "select id,title,name,day,today,view,recommend,comment from content where title like '%".$_GET["keyword"]."%' or text like '%".$_GET["keyword"]."%' order by id desc";
			$result = mysqli_query($db,$query);
		}
		else{
			$query = "select id,title,name,day,today,view,recommend,comment from content order by id desc";
			$result = mysqli_query($db,$query);
		}
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
		$isrecommend=0;
		$db = new mysqli('localhost','pi','pi','pi');
		$query = "select * from content where id=".$_GET["content_id"];
		$result = mysqli_query($db,$query);
		header("Content-type: application/xml");
		$xml = new DOMDocument('1.0','UTF-8');
		$contents = $xml->createElement("contents");
		$xml->appendChild($contents);
		while($row = mysqli_fetch_array($result)){
				if($_GET["show"]=="read"){
					$views = $row["view"]+1;
					$query = "update content set view=".$views." where id=".$_GET["content_id"];
					$result2 = mysqli_query($db,$query);
				}
				else{
					$views = $row["view"];
				}
				if($_GET["show"]=="comment"){
					$comments = $row["comment"]+1;
					$query = "update content set comment=".$comments." where id=".$_GET["content_id"];
					$result3 = mysqli_query($db,$query);
					$query = "insert into comment(id,name,day,today,text) values(".$_GET["content_id"].",'".$_SESSION["id"]."(".$_SESSION["name"].")',now(),now(),'".$_GET["text"]."')";
					$result3 = mysqli_query($db,$query);
					$query = "commit";
					$result3 = mysqli_query($db,$query);
				}
				else if($_GET["show"]=="deletecomment"){
					$comments = $row["comment"]-1;
					$query = "update content set comment=".$comments." where id=".$_GET["content_id"];
					$result3 = mysqli_query($db,$query);
					$query = "delete from comment where commentid=".$_GET["commentid"];
					$result3 = mysqli_query($db,$query);
					$query = "commit";
					$result3 = mysqli_query($db,$query);
				}
				else{
					$comments = $row["comment"];
				}
				if($_GET["show"]=="recommend"){
					$query = "select * from recommender where id='".$_SESSION["id"]."' and contentid=".$_GET["content_id"];
					$result4 = mysqli_query($db,$query);
					$row4 = mysqli_fetch_array($result4);
					if($row4["id"]==null){
						$query = "insert into recommender values('".$_SESSION["id"]."',".$_GET["content_id"].")";
						$result4 = mysqli_query($db,$query);
						$recommends = $row["recommend"]+1;
						$query = "update content set recommend=".$recommends." where id=".$_GET["content_id"];
						$result4 = mysqli_query($db,$query);
						$isrecommend = 1;
					}
					else{
						$recommends = $row["recommend"];
					}
				}
				else{
					$recommends = $row["recommend"];
				}
				$tag = $xml->createElement("content");
				$contents->appendChild($tag);
				$id = $xml->createElement("id",$row["id"]);
				$title = $xml->createElement("title",$row["title"]);
				$name = $xml->createElement("name",$row["name"]);
				$day = $xml->createElement("day",$row["day"]);
				$today = $xml->createElement("today",$row["today"]);
				$view = $xml->createElement("view",$views);
				$recommend = $xml->createElement("recommend",$recommends);
				$comment = $xml->createElement("comment",$comments);
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
				if($_GET["show"]=="recommend"){
					$recommendtag = $xml->createElement("recommendtag",$isrecommend);
					$tag->appendChild($recommendtag);
				}
		}
		$query = "select * from comment where id=".$_GET["content_id"]." order by commentid";
		$result = mysqli_query($db,$query);
		while($row = mysqli_fetch_array($result)){
			$tag = $xml->createElement("comments");
			$contents->appendChild($tag);
			$commentid = $xml->createElement("commentid",$row["commentid"]);
			$name = $xml->createElement("name",$row["name"]);
			$text = $xml->createElement("text",$row["text"]);
			$day = $xml->createElement("day",$row["day"]);
			$today = $xml->createElement("today",$row["today"]);
			$tag->appendChild($commentid);
			$tag->appendChild($name);
			$tag->appendChild($text);
			$tag->appendChild($day);
			$tag->appendChild($today);
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