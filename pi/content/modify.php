<?php session_start(); ?>
<?php 
	$db = new mysqli('localhost','pi','pi','pi');
	$query = "select * from content where id=".$_GET["content_id"];
	$result = mysqli_query($db,$query);
	$row = mysqli_fetch_array($result);
	$views = $row["view"];
	$comments = $row["comment"];
	$recommend = $row["recommend"];
	$query = "delete from content where id=".$_GET["content_id"];
	$result = mysqli_query($db,$query);
	$oldid = $_GET["content_id"];
	$query = "insert into content(title,name,day,today,view,recommend,comment,text) values('".$_GET["title"]."','".$_SESSION["id"]."(".$_SESSION["name"].")',now(),now(),".$views.",".$recommend.",".$comments.",'".$_GET["text"]."')";
	$result = mysqli_query($db,$query);
	$query = "commit";
	$result = mysqli_query($db,$query);
	$query = "select id from content order by day,today desc limit 1";
	$result = mysqli_query($db,$query);
	$row = mysqli_fetch_array($result);
	$newid = $row["id"];
	$query = "update comment set id=".$newid." where id=".$oldid;
	$result = mysqli_query($db,$query);
	$query = "update recommender set id=".$newid." where id=".$oldid;
	$result = mysqli_query($db,$query);
	header("Location:../index.php?state=read&content_id=".$newid);
?>