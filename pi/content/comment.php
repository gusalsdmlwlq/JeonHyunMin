<?php session_start(); ?>
<?php 
	$db = new mysqli('localhost','pi','pi','pi');
	$query = "insert into comment(id,name,day,today,text) values(".$_GET["content_id"].",'".$_SESSION["id"]."(".$_SESSION["name"].")',now(),now(),'".$_GET["text"]."')";
	$result = mysqli_query($db,$query);
	$query = "commit";
	$result = mysqli_query($db,$query);
?>