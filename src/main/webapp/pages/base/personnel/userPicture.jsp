<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<style>
.niubility {
	color: #444;
	background: #fafafa;
	background-repeat: repeat-x;
	border: 1px solid #bbb;
	background: -webkit-linear-gradient(top, #ffffff 0, #eeeeee 100%);
	background: -moz-linear-gradient(top, #ffffff 0, #eeeeee 100%);
	background: -o-linear-gradient(top, #ffffff 0, #eeeeee 100%);
	background: linear-gradient(to bottom, #ffffff 0, #eeeeee 100%);
	background-repeat: repeat-x;
 filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#ffffff, endColorstr=#eeeeee, GradientType=0);
	-moz-border-radius: 5px 5px 5px 5px;
	-webkit-border-radius: 5px 5px 5px 5px;
	border-radius: 5px 5px 5px 5px;
}

.add_user {
	width: 840px;
	margin: 0 auto;
}

</style>

<input type="hidden" id="empPicCode" value="<%=request.getParameter("empCode") %>"/> 
 <tr>
    <td >
    <img align="center" id="coverPic"  width="120" height="120" onclick="openFile()" style="display: inherit; margin:auto 0" src="http://pic.hanxinbank.com/4B8B0138F8F050B4352AA86C1799060A.png" />
		<p style="display:none;">
			<input type="file" style="display:none;height: 25px" name="file" id="file" onchange="ajaxFileUpload()" /><br> 
			<input id="cover" name="picture"  type="hidden">
		</p>
	</td>
  </tr>

<script type="text/javascript">
function openFile(){
	$("#file").click();
}


$(function(){
	 var empCode=$('#empPicCode').val();
		var url = "personnel/findUserDetail.form?empCode="+empCode;
		$.ajax({
			url:url,
			type:"post",
			//data:data,
			dataType:"json",
			success:function(jsonData){
				//var t = "["+jsonData.data.toString()+"]";
				var t = jsonData;
				if(t.imgUrl!=null&&t.imgUrl!=''){
					$("#coverPic").attr("src", "http://pic.hanxinbank.com/"+t.imgUrl);
					$("#cover").val(t.imgUrl);
				}
			}
		})
		
	})	


function ajaxFileUpload() {
	var empCode=$('#empPicCode').val();
	//获取欲上传的文件路径  
	var file = $("#file").val();
	var rs = checkPictureType(file);
	if (rs < 0) {
		$.messager.alert('提示','您选择的上传文件不是有效的图片文件！');
		return;
	}
	$.ajaxFileUpload({
		url : 'personnel/uploadImage.form?empCode='+empCode,
		secureuri : false,
		fileElementId : 'file', //文件选择框的id属性
		dataType : 'json', //服务器返回的格式，可以是json
		success : function(data) { //相当于java中try语句块的用法
	
			$("#coverPic").attr("src", "http://pic.hanxinbank.com/"+data);
			$("#cover").val(data);
		}
	});

}
</script>
</body>
</html>