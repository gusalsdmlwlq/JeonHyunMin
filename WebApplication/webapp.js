var preside="";
function menu(sidename){
	var side = document.getElementById(sidename);
	if(preside!="") document.getElementById(preside).className = "close";
	preside = sidename;
	if(side.className=="open") side.className = "close";
	else side.className = "open";
}
function callcontents(contentsname){
	alert("callcontents");
	parent.contents(contentsname);
}