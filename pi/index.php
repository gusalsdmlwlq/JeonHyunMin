<?php session_start(); ?>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Pi</title>
	<link rel="stylesheet" type="text/css" href="pi.css">
	<script type="text/javascript" src="prototype.js"></script>
	<script type="text/javascript" src="pi.js"></script>
	<script type="text/javascript" src="content/content.js"></script>
</head>
<body>
	<header>
	<?php if(isset($_SESSION["id"])){ ?>
			<button id='signout'>Sign out</button>
			<div id='hello'>안녕하세요. <?= $_SESSION["name"]; ?>님</div>
	<?php } else{ ?>
			<button id='signup'>Sign up</button><button id='signin'>Sign in</button>
	<?php } ?>
	<div id="menu">Menu</div>
	<h1>RaspberryPi</h1>
	</header>
	<aside>
		<nav id="nav" class="close">
			<div class="close" id="list" onclick="list()">List</div>
			<div class="close" id="write" onclick="writetext()">Write</div>
		</nav>
	</aside>
	<section id="section">
	</section>
	<div id="state"><?= $_GET["state"]; ?></div>
	<div id="content_id"><?= $_GET["content_id"]; ?></div>
	<div id="search_keyword"><?= $_GET["keyword"]; ?></div>
</body>
</html>