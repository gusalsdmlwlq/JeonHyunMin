
<?php
	include '../db.php';
    session_start();
    $val=$_GET['contents_name'];
	$sql = "SELECT * from sight where sight_name= '$val'";
	$ret= mysqli_query($db,$sql);
	$res = mysqli_fetch_array($ret);
	$contentR=explode("\n",$res[2]);
	//echo "<br>";
	//print_r($contentR);
	$info=explode("\n",$res[8]);
	$bottom=explode("\n",$res[9]);
    $extra=explode("\n",$res[10]);
	//echo "<br>";
	//print_r($info);
	//echo "<br>";
	//print_r($bottom);
    
    ?>
    <!DOCTYPE html>
<html>
<head>
    <title><?= $val?></title>
    <meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="contentsamplecss.css">
</head>
<body>
	<section>
		<h2> <?= $res[0]; ?> </h2>
		<img class="fullimg" src=<?= $res[3] ?>>
		<div class="paragraph">
			<div class="left_paragraph">
				<p> <?= $res[1]; ?> </p>				
				<!-- veriable1 ? -->
			</div>
			<div class="right_paragraph">
				<p>  <?= $contentR[0]; ?> </p>
				<!-- veriable2 ? -->
				<blockquote>
					<q> <?=$contentR[1]; ?></q> 
					<br />
					<em>  <?= $contentR[2]; ?> </em>
				</blockquote>
				<!-- 일단 있어서 들고오긴 했는데 blockquote가 필요한지는 의문 -->
				<!-- 여기 text는 justfied 적용 예정-->
			</div>
		</div>
		<div class="paragraph">
			<div class="left_paragraph">
				<img src=<?= $res[4] ?>>
				<img src=<?= $res[5] ?>>
			</div>
			<div class="right_paragraph">
				<img src=<?= $res[6] ?>>
				<img src=<?= $res[7] ?>>
			</div>
		</div>
		<div class="paragraph">
			<h3> <?= $info[0]; ?></h3>
			<ul>
				<li>
					<dl>
						<dt> <?= $info[1]; ?></dt>
						<dt> <?= $info[2]?></dt>
					</dl>
				</li>
				<li>
					<dl>
					<?php for($i=0;$i<6;$i++){
	                    ?>
						<dt> <?= $bottom[$i]; ?></dt>
						<?php
	                }?>
					</dl>
				</li>
				<li>
					<dl>
					<?php for($i=0;$i<count($extra);$i++){
	                    ?>
						<dt> <?= $extra[$i]; ?></dt>
						<?php
	                }?>
					</dl>
				</li>
			</ul>
		</div>
		
	</section>
	
</body>
</html>