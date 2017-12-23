var id;
window.onload = function() {
	if($("signin")!=null){
		$("signin").observe("click",signin);
		$("signup").observe("click",signup);
	}
	if($("signout")!=null){
		$("signout").observe("click",signout);
	}
	$("menu").observe("click",menu);
	if($("hello")!=null){
		id=$("hello").firstChild.nodeValue.split("(")[0].substring(7);
	}
};
function signin(){
	var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "POST");
    form.setAttribute("action", "sign/signin.html");
    document.body.appendChild(form);
    form.submit();
}
function signup(){
	var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "POST");
    form.setAttribute("action", "sign/signup.html");
    document.body.appendChild(form);
    form.submit();
}
function signout(){
	var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "POST");
    form.setAttribute("action", "index.php");
    var input = document.createElement("input");
    input.setAttribute("type","hidden");
    input.setAttribute("name","sign");
    input.setAttribute("value","no");
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
    alert("로그아웃 되었습니다.");
}
function menu(){
	var nav = $("nav");
	var div = $$("nav div");
	if(nav.hasClassName("close")){
		nav.removeClassName("close");
		nav.addClassName("open");
		for(var i=0; i<div.length; i++){
			div[i].removeClassName("close");
			div[i].addClassName("open");
		}
	}
	else{
		nav.removeClassName("open");
		nav.addClassName("close");
		for(var i=0; i<div.length; i++){
			div[i].removeClassName("open");
			div[i].addClassName("close");
		}
	}
}