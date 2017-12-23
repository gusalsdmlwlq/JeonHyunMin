window.onload = function() {
	$("signup").observe("click",signup);
};
function signup(){
	var id = document.getElementById("id").value;
	var pw = document.getElementById("pw").value;
	var name = document.getElementById("name").value;
	new Ajax.Request("signup.php", {
		method: "get",
		parameters: {"id":id,"pw":pw,"name":name},
		onSuccess: success,
		onFailure: fail,
		onException: fail
	});
}
function success(ajax){
	var data = ajax.responseXML.getElementsByTagName("user");
	var id = data[0].getElementsByTagName("id")[0].firstChild.nodeValue;
	var pw = data[0].getElementsByTagName("pw")[0].firstChild.nodeValue;
	var name = data[0].getElementsByTagName("name")[0].firstChild.nodeValue;

	var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "POST");
    form.setAttribute("action", "index.php");
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","id");
    input.setAttribute("value",id);
    form.appendChild(input);
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","pw");
    input.setAttribute("value",pw);
    form.appendChild(input);
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","name");
    input.setAttribute("value",name);
    form.appendChild(input);
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","sign");
    input.setAttribute("value","yes");
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}
function fail(ajax, exception){
	alert("이미 가입된 아이디입니다.");
}