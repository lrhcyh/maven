<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<body>
	<script type="text/javascript" src="js/inning/privilege.js"></script>

	<script type="text/javascript">
		var toolbar = [ {
			iconCls : 'icon-add',
			text : '添加',
			handler : function() { 
				saveAuthority();
			}
		}, '-', {
			iconCls : 'icon-edit',
			text : '修改',
			handler : function() {
				editAuthority();
			}
		}, '-', {
			iconCls : 'icon-remove',
			text : '删除',
			handler : function() { 
				removeAuthority();
			}
		} , '-', {
			iconCls : 'icon-add',
			text : '显示菜单',
			handler : function() {
				displayAuthority();
			}
		}, '-', {
			iconCls : 'icon-remove',
			text : '隐藏菜单',
			handler :function() {
				hideAuthority()
			}
		}];
	</script>
	<div id="cc" class="easyui-layout" data-options="fit:true,border:false" >
		<div data-options="region:'center',border:false,title:'菜单管理'" style="width: 100%;padding-bottom:10px">
			<table id="privilege_tg" class="easyui-treegrid" style="width: inherit; "
				data-options="
                iconCls: 'icon-ok',
                rownumbers: true,
                animate: true,
                lines:true,
                fit:true,
                url: 'privilege/treegrid.form',
                method: 'get',
                idField: 'id',
                treeField: 'name',
                pagination : false,
                striped:true,
                toolbar:toolbar,
                onLoadSuccess: function(row){
                    
					$(this).treegrid('enableDnd', row?row.id:null);
				},
				onBeforeDrop: function(targetRow, sourceRow, point){
					var sourceId = sourceRow.id;
					var targetId = targetRow.id;
					var sourceNum = sourceRow.sortnum;
					var targetNum = targetRow.sortnum;
					if(sourceRow._parentId!=targetRow._parentId || point=='append' ){
						alert('不能脱离父级');
						return false;
					} else {
						$.ajax({
							url:'privilege/dragNode.form?sourceId='+sourceId+'&targetId='+targetId+'&sourceNum='+sourceNum+'&targetNum='+targetNum,
							dataType:'text',
							success:function(j) {
								$('#privilege_tg').treegrid('reload');
							}
						})
					
					}
				}
            ">
				<thead>
					<tr>
						<th data-options="field:'id',width:80,align:'center'">编号</th>
						<th data-options="field:'name',width:230,align:'left'">名称</th>
						<th data-options="field:'showText',width:180,align:'center'">是否显示</th>
						<th data-options="field:'URL',width:280,align:'center'">URL</th>
						<th data-options="field:'create_time',width:180,align:'center'">创建时间</th>
						
					</tr>
				</thead>
			</table>
		</div>
	</div>

	


</body>
