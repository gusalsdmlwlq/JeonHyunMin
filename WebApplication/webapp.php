<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="webapp.css">
	<meta charset="utf-8">
	<title>한국 명소</title>
</head>
<body>
	<script type="text/javascript">
		var preside="";
		function menu(sidename){
			var side = document.getElementById(sidename);
			if(preside!="") document.getElementById(preside).className = "close";
			preside = sidename;
			if(side.className=="open") side.className = "close";
			else side.className = "open";
		}
	</script>
	<header>
		header
	</header>
	<aside>
		<nav>
			<div onclick="menu('side1')">
				한국 명소
			</div>
			<div onclick="menu('side2')">
				한국 음식
			</div>
			<div>
				한우
			</div>
			<div>
				정규
			</div>
		</nav>
		<iframe src="side1.php" id="side1"></iframe>
		<iframe src="side2.php" id="side2"></iframe>
	</aside>
	<section>
		<iframe src="contents.php" name="main" scrolling="no" id="main" onload="resize()"></iframe>
		<script type="text/javascript">
			function contents(contentsname){
				var form = document.createElement("form");
				form.setAttribute("charset", "UTF-8");
				form.setAttribute("method", "POST");
				form.setAttribute("action", "contents.php");
				form.setAttribute("target", "main");
				form.setAttribute("id","submit");
				document.body.appendChild(form);
				var input = document.createElement("input");
				input.setAttribute("type","hidden");
				input.setAttribute("name","contents");
				input.setAttribute("value",contentsname);
				form.appendChild(input);
				form.submit();
			}
			function resize(){
				document.getElementById('main').style.height = "0";
				document.getElementById('main').style.height = document.getElementById("main").contentWindow.document.body.scrollHeight + "px";
				window.scrollTo(0,0);
			}
		</script>
	</section>
	<footer>
		footer
	</footer>
</body>
</html>