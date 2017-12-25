function list(){
	var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "GET");
    form.setAttribute("action", "index.php");
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","state");
    input.setAttribute("value","list");
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}
function showlist(){
	new Ajax.Request("content/content.php", {
		method: "get",
		parameters: {"show":"list"},
		onSuccess: success,
		onFailure: fail,
		onException: fail
	});
}
function writetext(){
	var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "GET");
    form.setAttribute("action", "index.php");
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","state");
    input.setAttribute("value","write");
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}
function showwrite(){
	document.getElementById("section").innerHTML = "";
	document.getElementById("section").innerHTML += "<div id='write_text'><fieldset>제목 <input type='text' name='title' id='text_title'><textarea id='text_main'></textarea></fieldset><button id='btn_write_ok' onclick='writeok()'>등록</button></div>";
}
function writeok(){
	var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "GET");
    form.setAttribute("action", "content/write.php");
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","title");
    input.setAttribute("value",document.getElementById("text_title").value);
    form.appendChild(input);
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","text");
    input.setAttribute("value",document.getElementById("text_main").value);
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}
function success(ajax){
	var data = ajax.responseXML.getElementsByTagName("content");
	document.getElementById("section").innerHTML = "";
	document.getElementById("section").innerHTML += '<div id="content"><table id="contents"><tr><th>번호</th><th>제목</th><th>글쓴이</th><th>날짜</th><th>조회</th><th>추천</th><th>댓글수</th></tr></table></div>';
	for(var i=0; i<data.length; i++){
		content = "<tr>"+"<td>"+(data.length-i)+"</td>"+
		"<td><a href='index.php?state=read&content_id="+data[i].getElementsByTagName("id")[0].firstChild.nodeValue+"'>"+data[i].getElementsByTagName("title")[0].firstChild.nodeValue+"</a></td>"+
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
function read(content_id){
	new Ajax.Request("content/content.php", {
		method: "get",
		parameters: {"show":"read","content_id":content_id},
		onSuccess: successread,
		onFailure: failtext,
		onException: failtext
	});
}
function deletetext(){
	var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "GET");
    form.setAttribute("action", "content/delete.php");
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","content_id");
    input.setAttribute("value",document.getElementById("content_id").firstChild.nodeValue);
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}
function modifytext(){
	var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "GET");
    form.setAttribute("action", "index.php");
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","state");
    input.setAttribute("value","modify");
    form.appendChild(input);
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","content_id");
    input.setAttribute("value",document.getElementById("content_id").firstChild.nodeValue);
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}
function showmodify(content_id){
	new Ajax.Request("content/content.php", {
		method: "get",
		parameters: {"show":"read","content_id":content_id},
		onSuccess: loadtext,
		onFailure: failtext,
		onException: failtext
	});
}
function loadtext(ajax){
	var data = ajax.responseXML.getElementsByTagName("content");
	document.getElementById("section").innerHTML = "";
	document.getElementById("section").innerHTML += "<div id='write_text'><fieldset>제목 <input type='text' name='title' id='text_title' value='"+data[0].getElementsByTagName("title")[0].firstChild.nodeValue+"'><textarea id='text_main'>"+data[0].getElementsByTagName("text")[0].firstChild.nodeValue+"</textarea></fieldset><button id='btn_modify_ok' onclick='modifyok()'>수정</button></div>";
}
function modifyok(){
	var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "GET");
    form.setAttribute("action", "content/modify.php");
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","title");
    input.setAttribute("value",document.getElementById("text_title").value);
    form.appendChild(input);
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","text");
    input.setAttribute("value",document.getElementById("text_main").value);
    form.appendChild(input);
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","content_id");
    input.setAttribute("value",document.getElementById("content_id").firstChild.nodeValue);
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}
function successread(ajax){
	var data = ajax.responseXML.getElementsByTagName("content");
	document.getElementById("section").innerHTML = "";
	document.getElementById("section").innerHTML += "<div id='text'><fieldset><table>"+
	"<tr><th colspan='2'>"+data[0].getElementsByTagName("title")[0].firstChild.nodeValue+"</th><th>"+data[0].getElementsByTagName("day")[0].firstChild.nodeValue+" "+data[0].getElementsByTagName("today")[0].firstChild.nodeValue+"</th></tr>"+
	"<tr><td colspan='3'>"+data[0].getElementsByTagName("name")[0].firstChild.nodeValue+"</td></tr>"+
	"<tr><td colspan='3'>"+data[0].getElementsByTagName("text")[0].firstChild.nodeValue+"</td></tr>"+
	"<tr><td>댓글["+data[0].getElementsByTagName("comment")[0].firstChild.nodeValue+"]</td><td>조회수 "+data[0].getElementsByTagName("view")[0].firstChild.nodeValue+"</td><td>추천수 "+data[0].getElementsByTagName("recommend")[0].firstChild.nodeValue+"</td></tr>"+
	"</table><textarea id='comment'></textarea><button id='btn_comment'>댓글 등록</button></fieldset></div>"
	if(data[0].getElementsByTagName("own")[0].firstChild.nodeValue=="yes"){
		document.getElementById("text").innerHTML += "<button id='btn_delete' onclick='deletetext()'>삭제</button><button id='btn_modify' onclick='modifytext()'>수정</button><button id='btn_write' onclick='writetext()'>글쓰기</button>";
	}
	else{
		document.getElementById("text").innerHTML += "<button id='btn_write' onclick='writetext()'>글쓰기</button>";
	}
}
function failtext(ajax, exception){
	showlist();
}