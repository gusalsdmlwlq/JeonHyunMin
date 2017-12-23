function list(){
	new Ajax.Request("content/content.php", {
		method: "get",
		onSuccess: success,
		onFailure: fail,
		onException: fail
	});
}
function success(ajax){
	var data = ajax.responseXML.getElementsByTagName("content");
	document.getElementById("section").innerHTML = "";
	document.getElementById("section").innerHTML += '<div id="content"><table id="contents"><tr><th>번호</th><th>제목</th><th>글쓴이</th><th>날짜</th><th>조회</th><th>추천</th><th>댓글수</th></tr></table></div>';
	for(var i=0; i<data.length; i++){
		content = "<tr>"+"<td>"+(data.length-i)+"</td>"+
		"<td>"+data[i].getElementsByTagName("title")[0].firstChild.nodeValue+"</td>"+
		"<td>"+data[i].getElementsByTagName("name")[0].firstChild.nodeValue+"</td>"+
		"<td>"+data[i].getElementsByTagName("day")[0].firstChild.nodeValue+"</td>"+
		"<td>"+data[i].getElementsByTagName("view")[0].firstChild.nodeValue+"</td>"+
		"<td>"+data[i].getElementsByTagName("recommend")[0].firstChild.nodeValue+"</td>"+
		"<td>"+data[i].getElementsByTagName("comment")[0].firstChild.nodeValue+"</td>"+"</tr>";
		document.getElementById("contents").innerHTML += content;
	}
	document.getElementById("section").innerHTML += '<div id="search"><button id="searchbtn">Search</button><label for="searchbtn"><img src="images/search.jpg"></label><input type="text" name="search"></div>';
}
function fail(ajax, exception){
	alert("글 목록이 없습니다.");
}