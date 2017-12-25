<?php session_start(); ?>
<?php 
	$db = new mysqli('localhost','pi','pi','pi');
	$query = "delete from content where id=".$_GET["content_id"];
	$result = mysqli_query($db,$query);
	$query = "delete from comment where id=".$_GET["content_id"];
	$result = mysqli_query($db,$query);
	$query = "delete from recommender where id=".$_GET["content_id"];
	$result = mysqli_query($db,$query);
	$query = "commit";
	$result = mysqli_query($db,$query);
	header("Location:../index.php?state=list");
?>