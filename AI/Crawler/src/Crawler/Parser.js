const puppeteer = require('puppeteer');
var url = process.argv[2];

function printarray(array){
	for(var i=0; i<array.length; i++){
		if(typeof(array[i]) == "string") console.log(array[i].replace(/\n/gi, " "));
		else console.log(array[i])
	}
}

function show(array){
	for(var i=0; i<array.length; i++){
		if(typeof(array[i]) == "string"){
			printarray(array);
			return;
		}
		else show(array[i]);
	}
}

async function parse(url){
	const browser = await puppeteer.launch({
	    args: ["--no-sandbox", "--disable-web-security", `--user-data-dir=data`]
	});
	const page = await browser.newPage();
	await page.goto(url);
	//await page.goto("http://shopping.interpark.com/product/productInfo.do?prdNo=6296973788&smid1=ssendeal&smid2=pd&smid3=16");
	//await page.goto("https://blog.naver.com/yo__ol/221443714772");
	//await page.goto("https://news.naver.com/main/hotissue/read.nhn?mid=hot&sid1=100&cid=1079165&iid=2983118&oid=001&aid=0010587294&ptype=052");
	await page.waitFor(2000);
	const nodes = await page.evaluate((url) => {
		var bodywidth = document.body.scrollWidth;
		var bodyheight = document.body.scrollHeight;
		function isad(link){
			if(link == "#") return false;
			let link_machine = link.split('.')[0].split('/')[2];
			let link_domain = link.split('.')[1];
			let url_machine = url.split('.')[0].split('/')[2];
			let url_domain = url.split('.')[1];
			if(link_domain != url_domain || link_machine != url_machine) return true;
		}
		function recur(root, indent, ad){
			let contents = root.childNodes;
			let result = new Array();
			let attributes = new Array(); //(type, content, x, y, w, h, fontsize, bg_color, indent) : node의 attribute
			let childindent = indent;
			const a_tag_count = 5;
			for(var i=0; i<contents.length; i++){
				attributes = new Array();
				let node = contents[i];
				if(["SCRIPT", "#comment", "STYLE", "NOSCRIPT"].includes(node.nodeName)) continue;
				if(node.nodeValue != null){
					if(node.nodeValue.trim().length != 0 && node.nodeName == "#text"){ //text node 체크
						attributes.push("text");
						attributes.push(node.nodeValue.trim());
						if(node.parentNode.innerHTML != node.nodeValue){ //태그가 없는 text node 체크
							let spantext = document.createTextNode(node.nodeValue);
							let insertspan = document.createElement('span');
							insertspan.appendChild(spantext);
							node.parentNode.insertBefore(insertspan, node);
							node.parentNode.removeChild(node);
							node = spantext;
							childindent += 1;
						}
						node = node.parentNode;
						attributes.push((node.offsetLeft + node.offsetWidth / 2) / bodywidth);
						attributes.push((node.offsetTop + node.offsetHeight / 2) / bodyheight);
						attributes.push(node.offsetWidth);
						attributes.push(node.offsetHeight);
						let size = window.getComputedStyle(node,null).getPropertyValue('font-size');
						attributes.push(size.split('p')[0]*1);
						let bgcolor = window.getComputedStyle(node,null).getPropertyValue('background-color');
						attributes.push(bgcolor);
						attributes.push(childindent);
					}
				}
				else{
					let position = window.getComputedStyle(node,null).getPropertyValue('position');
					let z_index = window.getComputedStyle(node,null).getPropertyValue('z-index');
					if(node.nodeName == "IFRAME"){
						node = node.contentWindow.document.body;
						if(node == null) continue;
					}
					else if(node.nodeName == "IMG"){ //img node 체크
						if(ad == false){
							attributes.push("img");
							attributes.push(node.src);
							attributes.push((node.offsetLeft + node.offsetWidth / 2) / bodywidth);
							attributes.push((node.offsetTop + node.offsetHeight / 2) / bodyheight);
							attributes.push(node.offsetWidth);
							attributes.push(node.offsetHeight);
							attributes.push(0);
							attributes.push("rgba(0, 0, 0, 0)");
							attributes.push(childindent);
						}
					}
					if(node.childNodes != null && position != "fixed" && (z_index == "auto" || z_index < 10000)){
						let is_ad = false;
						if(node.nodeName == "A"){
							let href = node.getAttribute("href");
							if(href == null || isad(href) == true) is_ad = true;
						}
						result.push(recur(node,indent+1,is_ad));
					}
				}
				result.push(attributes);
			}
			return result;
		}
		body = document.querySelector("body");
		return recur(body,0);
	},url);
	await browser.close();
	show(nodes);
}
parse(url);