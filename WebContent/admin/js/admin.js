function check(){
	if(document.myform.username.value==''){
		alert('请输入用户名'); 
		console.log("用户名为空")
		return false; 
	}
	if(document.myform.password.value==''){
		alert('请输入密码'); 
		return false; 
	}if(document.myform.realname.value==''){
		alert('请输入姓名'); 
		return false; 
	}if(document.myform.contact.value==''){
		alert('请输入联系方式'); 
		return false; }
	}