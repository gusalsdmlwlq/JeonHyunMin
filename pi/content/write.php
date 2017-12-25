<?php session_start(); ?>
<?php 
	$db = new mysqli('localhost','pi','pi','pi');
	$query = "insert into content(title,name,day,today,view,recommend,comment,text) values('".$_GET["title"]."','".$_SESSION["id"]."(".$_SESSION["name"].")',now(),now(),0,0,0,'".$_GET["text"]."')";
	$result = mysqli_query($db,$query);
	$query = "commit";
	$result = mysqli_query($db,$query);
	$query = "select id from content order by day,today desc limit 1";
	$result = mysqli_query($db,$query);
	$row = mysqli_fetch_array($result);
	header("Location:../index.php?state=read&content_id=".$row["id"]);
?>