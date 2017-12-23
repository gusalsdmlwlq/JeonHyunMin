<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Pi</title>
	<link rel="stylesheet" type="text/css" href="pi.css">
	<script type="text/javascript" src="prototype.js"></script>
	<script type="text/javascript" src="pi.js"></script>
</head>
<body>
	<header>
	<?php
		if($_POST["sign"]=="yes"){
			print("<button id='signout'>Sign out</button>");
			$user = "<div>안녕하세요. ".$_POST["id"]."(".$_POST["name"].")님</div>";
			print($user);
		}
		else{
			print("<button id='signup'>Sign up</button><button id='signin'>Sign in</button>");
		}
	?>
	<div id="menu">Menu</div>
	<h1>RaspberryPi</h1>
	</header>
	<aside>
		<nav id="nav" class="close">
			<div class="close">List</div>
			<div class="close">Write</div>
		</nav>
	</aside>
	<section>
		<div id=search>
			<input type="text" name="search">
			<button id="searchbtn">Search</button>
			<label for="searchbtn"><img src="images/search.jpg"></label>
		</div>
	</section>
</body>
</html>