//验证用户输入是否是数字

function checkInput(){
	var objs = document.getElementsByTagName("input");
	var reg = new RegExp("^[1-9]\d*");
	for(var i=0;i<objs.length;i++){
		if(objs[i].getAttribute("type") == "text"){
			if(objs[i].value==""){
				window.alert("输入不能为空");
				return false;
			}
			if(!reg.test(objs[i].value)){
				window.alert("输入不合法");
				return false;
			}
		}
	}

}
