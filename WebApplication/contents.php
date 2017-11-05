<!DOCTYPE html>
<html>
<head>
	<title>contents</title>
	<style type="text/css">
		body
		{
			width: 900px;
			height: 400px;
		}
		img
		{
			width: 850px;
		}
	</style>
</head>
<body>
	<?php
		$filename = $_POST["contents"];
		$filetext = file_get_contents($filename);
		echo $filetext;
	?>
</body>
</html>