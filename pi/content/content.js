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
	if(document.getElementById("hello")!=null){
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
	else{
		alert("로그인하세요.");
	}
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
		"<td>"+data[i].getElementsByTagName("name")[0].firstChild.nodeValue+"</td>";
		if(data[i].getElementsByTagName("day")[0].firstChild.nodeValue==today()){
			content += "<td>"+data[i].getElementsByTagName("today")[0].firstChild.nodeValue+"</td>";
		}
		else{
			content += "<td>"+data[i].getElementsByTagName("day")[0].firstChild.nodeValue+"</td>";
		}
		content += "<td>"+data[i].getElementsByTagName("view")[0].firstChild.nodeValue+"</td>"+
		"<td>"+data[i].getElementsByTagName("recommend")[0].firstChild.nodeValue+"</td>"+
		"<td>"+data[i].getElementsByTagName("comment")[0].firstChild.nodeValue+"</td>"+"</tr>";
		document.getElementById("contents").innerHTML += content;
	}
	document.getElementById("section").innerHTML += "<button id='btn_write' onclick='writetext()'>글쓰기</button>";
	document.getElementById("section").innerHTML += '<div id="search"><button id="searchbtn" onclick="searchtext()">Search</button><label for="searchbtn"><img src="images/search.jpg"></label><input type="text" name="searchtext" id="searchtext"></div>';
}
function fail(ajax, exception){
	alert("글 목록이 없습니다.");
}
function read(){
	new Ajax.Request("content/content.php", {
		method: "get",
		parameters: {"show":"read","content_id":document.getElementById("content_id").firstChild.nodeValue},
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
		parameters: {"show":"modify","content_id":content_id},
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
	document.getElementById("section").innerHTML += "<div id='text'><fieldset><table id='text_table'>"+
	"<tr><th colspan='2'>"+data[0].getElementsByTagName("title")[0].firstChild.nodeValue+"</th><th>"+data[0].getElementsByTagName("day")[0].firstChild.nodeValue+" "+data[0].getElementsByTagName("today")[0].firstChild.nodeValue+"</th></tr>"+
	"<tr><td colspan='3'>"+data[0].getElementsByTagName("name")[0].firstChild.nodeValue+"</td></tr>"+
	"<tr><td colspan='3'>"+data[0].getElementsByTagName("text")[0].firstChild.nodeValue+"</td></tr>"+
	"<tr><td>댓글["+data[0].getElementsByTagName("comment")[0].firstChild.nodeValue+"]</td><td>조회수 "+data[0].getElementsByTagName("view")[0].firstChild.nodeValue+"</td><td>추천수 "+data[0].getElementsByTagName("recommend")[0].firstChild.nodeValue+"</td></tr>"+
	"</table><textarea id='comment'></textarea><button id='btn_comment' onclick='commentok()'>댓글 등록</button></fieldset></div>"
	if(data[0].getElementsByTagName("own")[0].firstChild.nodeValue=="yes"){
		document.getElementById("text").innerHTML += "<button id='btn_delete' onclick='deletetext()'>삭제</button><button id='btn_modify' onclick='modifytext()'>수정</button><button id='btn_write' onclick='writetext()'>글쓰기</button>";
	}
	else{
		document.getElementById("text").innerHTML += "<button id='btn_recommend' onclick='recommendtext()'>추천</button><button id='btn_write' onclick='writetext()'>글쓰기</button>";
	}
	var comments = ajax.responseXML.getElementsByTagName("comments");
	for(var i=0; i<comments.length; i++){
		var nrow=document.getElementById("text_table").insertRow(document.getElementById("text_table").rows.length);
		var cell1 = nrow.insertCell(0);
		var cell2 = nrow.insertCell(1);
		var cell3 = nrow.insertCell(2);
		cell1.innerHTML = comments[i].getElementsByTagName("name")[0].firstChild.nodeValue;
		cell2.innerHTML = comments[i].getElementsByTagName("text")[0].firstChild.nodeValue;
		cell3.innerHTML = comments[i].getElementsByTagName("day")[0].firstChild.nodeValue+" "+comments[i].getElementsByTagName("today")[0].firstChild.nodeValue;
		if(comments[i].getElementsByTagName("own")[0].firstChild.nodeValue=="yes"){
			cell3.innerHTML += "<span id='btn_delete_comment' onclick='deletecomment("+comments[i].getElementsByTagName("commentid")[0].firstChild.nodeValue+")'>삭제</span>";
		}
	}
	if(data[0].getElementsByTagName("recommendtag")[0]!=null){
		if(data[0].getElementsByTagName("recommendtag")[0].firstChild.nodeValue==1){
			alert("1추천 드립니다.");
		}
		else{
			alert("이미 추천한 글입니다.");
		}
	}
}
function failtext(ajax, exception){
	showlist();
}
function today(){
	var date = new Date();
    var year  = date.getFullYear();
    var month = date.getMonth() + 1;
    var day   = date.getDate();
    if (("" + month).length==1){
    	month="0"+month;
    }
    if (("" + day).length==1){
    	day="0"+day;   
    }
    return year+"-"+month+"-"+day;
}
function commentok(){
	if(document.getElementById("hello")!=null){
		new Ajax.Request("content/content.php", {
			method: "get",
			parameters: {"show":"comment","text":document.getElementById("comment").value,"content_id":document.getElementById("content_id").firstChild.nodeValue},
			onSuccess: successread,
			onFailure: failtext,
			onException: failtext
		});
	}
	else{
		alert("로그인하세요.");
	}
}
function deletecomment(commentid){
	new Ajax.Request("content/content.php", {
		method: "get",
		parameters: {"show":"deletecomment","text":document.getElementById("comment").value,"content_id":document.getElementById("content_id").firstChild.nodeValue,"commentid":commentid},
		onSuccess: successread,
		onFailure: failtext,
		onException: failtext
	});
}
function searchtext(){
	var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "GET");
    form.setAttribute("action", "index.php");
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","state");
    input.setAttribute("value","search");
    form.appendChild(input);
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","keyword");
    input.setAttribute("value",document.getElementById("searchtext").value);
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}
function searchkey(keyword){
	new Ajax.Request("content/content.php", {
		method: "get",
		parameters: {"show":"list","search":"yes","keyword":keyword},
		onSuccess: success,
		onFailure: fail,
		onException: fail
	});
}
function recommendtext(){
	if(document.getElementById("hello")!=null){
		new Ajax.Request("content/content.php", {
			method: "get",
			parameters: {"show":"recommend","content_id":document.getElementById("content_id").firstChild.nodeValue},
			onSuccess: successread,
			onFailure: failtext,
			onException: failtext
		});
	}
	else{
		alert("로그인하세요.");
	}
}