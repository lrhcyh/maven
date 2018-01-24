/**
 * @author jiangxl
 * @description 图片上传控制公共js
 * @date 2014-10-20
 */

/*$(function(){
	$("input[name='cover']").each(function(){
		
		$(this).click(function(){
			checkUpload(this,$(this).val());
			
		});
	});
	
	var i = 0 ;
	$("input.files").each(function(){
		$("#imgPr"+i).uploadPreview({ Img: "img"+i, Width: 120, Height: 120 });
		$(this).one("change",addImg);
		i++;
	});
	//$("input.files").eq($("input.files").length - 1).one("change",addImg);

	
});*/
/**
 * 选择封面前需选择上传的图片
 * @param obj
 * @param value
 */
function checkUpload(obj,value){
	
	var fileValue = $("input[name='fileImages['"+value+"'].file']").val();
	if($("#img"+value)!=null && $("#img"+value)!=undefined){
		if($("#img"+value).attr("src")!=undefined && $("#img"+value).attr("src")!="" ){
			return ;
		}
		
	}
	if(fileValue==null||fileValue==""){
		//$(obj).attr("checked","false");
		alert("请先选择图片文件进行上传！");
		obj.checked=false;
		return;
	}
	

}

/**
 *	必须选择一个图片作为封面
 * @returns {Boolean}
 */
function checkCover(){
	var pass= false;
	$("input[name='cover']").each(function(){
		if($(this).attr("checked")=="checked"){
			pass= true;
		}
		if(pass){
			return false;
		}
		
	});
	if(!pass){
		alert("请选择图片封面");
	}
	return pass;
	
}


/**
 * 新增图片上传组件
 * 当组件个数>=5个时，不再新增
 */
/*function addImg(){	
	var a = $(".files").length;
	if(a >= 5 )
		return;
	var img="<input class='fl' type='radio' name='cover' value='"+a+"' title='设封面' ><div class ='upload-unit'><span class='add-img'>添加照片</span><img  id='img"+a+"'   src=''   width='120' height='100' style='display: inherit;'/><input type='file' class='files' name=\"fileImages['"+a+"'].file\" id='imgPr"+a+"' /></div>";				
 	var p=$("#pictureList");
 	p.append(img);
	$("#imgPr"+a).one("change",addImg);
 	$("#imgPr"+a).uploadPreview({ Img: "img"+a, Width: 120, Height: 120 });
} */

