<?php session_start() ?>
<?php
	if($_POST["state"]=="sign"){
		if($_POST["sign"]=="yes"){
			$_SESSION["id"]=$_POST["id"];
			$_SESSION["pw"]=$_POST["pw"];
			$_SESSION["name"]=$_POST["name"];
		}
		else{
			session_destroy();
		}
	}
	header("Location:../index.php");
?>