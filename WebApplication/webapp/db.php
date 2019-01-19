<?php
$db= new mysqli('localhost','webapp','webapp','webapp');
mysqli_query($db,"SET NAMES utf8");
mysqli_query($db, "set session character_set_connection=utf8");
mysqli_query($db, "set session character_set_results=utf8");
mysqli_query($db, "set session character_set_client=utf8");
?>