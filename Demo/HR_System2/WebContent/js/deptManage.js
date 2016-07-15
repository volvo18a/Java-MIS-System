/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2016-07-05 21:09:17
 * @version $Id$
 */


$(function(){
	/*开始显示部门信息*/
	$('#show-dept').click(function(){
		$('#main-dept-table tr:not(:first)').remove();
		$.ajax({
			type:'post',
			url:'DeptServlet?message=searchAllDept',
			datatype:'json',
			success:function(data){
				showDept(data);
			},
			error:function(status,statusText){
				alert(status+'---'+statusText);
			}
		});
	})

	/*新建部门*/
	$('#main-dept .btn-info').click(function() {
		$('#deptSurName option:not(:first)').remove();
		/*部门下拉条*/
		$.ajax({
			type:'post',
			url:'DppSelect?method=deptidtoname',
			datatype:'json',
			success:function(data){
				var json=eval(data);
				
				for(var i = 0;i < json.length; i ++){
					var depts=$('<option>'+json[i]['dept_name']+'</option>');
					$('#deptSurName').append(depts);
				}
			},
			error:function(status,statusText){
				alert(status+'---'+statusText);
			}
		});
		
		//获取后台部门名称验证重复
		$('#deptName').blur(function() {
			$.ajax({
				type:'post',
				url:'DeptServlet?message=checkRename',
				datatype:'json',
				data:{
					'dept_name':$('#deptName').val()
				},
				success:function(data){
					var name = eval(data);
					if(name[0] == 0){
						alert("部门名称重复！");
					}
				},
				error:function(status,statusText){
					alert(status+'---'+statusText);
				}
			});
		});
		$('#new-dept').dialog({
			
			title: '新建部门',
			width: '700px',
			modal: true, //对话框会遮罩一层灰纱，无法操作。
			buttons: {
				'重置':function(){
					$('#new-dept').find('select,input,textarea').val('');
				},
				'完成': function() {
					$.ajax({
						type:'post',
						url:'DeptServlet?message=newDept',
						datatype:'json',
						data:{
							'dept_name':$('#deptName').val(),
							'dept_type':$('#deptType').val(),
							'dept_tel':$('#deptTel').val(),
							'dept_fax':$('#deptFax').val(),
							'dept_sup':$('#deptSurName').val(),
							'dept_setdate':$('#deptSetDate').val(),
							'dept_description':$('#deptDesc').val()
						},
						success:function(data){
							showDept(data);
							
							$('#new-dept').dialog('close');
							alert('新建成功！');
							return false;
							
						},
						error:function(status,statusText){
							alert(status+'---'+statusText);
						}
					});
				}
			}
		});
	});	

	/*部门修改*/
	$(document).on('click','#main-dept-table tbody tr td a.dept-mod',function() {
		var deptId = $(this).parent().parent().find('td').eq(0);
		//var deptType = $(this).parent().parent().find('td').eq(2);
		//var deptSupName = $(this).parent().parent().find('td').eq(5);
		var deptSupName = $(this).parent().parent().find('td').eq(5);
		$('#mod-deptSupName option:not(:first)').remove();
		/*上级部门部门下拉条*/
		$.ajax({
			type:'post',
			url:'DppSelect?method=deptidtoname',
			datatype:'json',
			success:function(data){
				var json=eval(data);
				
				for(var i = 0;i < json.length; i ++){
					var depts=$('<option value='+ json[i]['dept_name'] +'>'+json[i]['dept_name']+'</option>');
					$('#mod-deptSupName').append(depts);
				}
				$('.Mod-dept-dial #mod-deptSupName').val(deptSupName.text());
			},
			error:function(status,statusText){
				alert(status+'---'+statusText);
			}
		});
		
		var deptName = $(this).parent().parent().find('td').eq(1);
		var deptType = $(this).parent().parent().find('td').eq(2);
		var deptTel = $(this).parent().parent().find('td').eq(3);
		var deptFax = $(this).parent().parent().find('td').eq(4);
		
		var deptSetDate = $(this).parent().parent().find('td').eq(6);
		var deptDesc = $(this).parent().parent().find('td').eq(7);
		
		$('.Mod-dept-dial #mod-deptName').val(deptName.text());
		$('.Mod-dept-dial #mod-deptType').val(deptType.text());
		$('.Mod-dept-dial #mod-deptTel').val(deptTel.text());
		$('.Mod-dept-dial #mod-deptFax').val(deptFax.text());
		 
		//alert($('.Mod-dept-dial #mod-deptSupName option').size());
		$('.Mod-dept-dial #mod-deptSetDate').val(deptSetDate.text());
		$('.Mod-dept-dial #mod-deptDesc').val(deptDesc.text());
		
		$('.Mod-dept-dial').dialog({
			title: '部门修改',
			width: '600px',
			modal: true, //对话框会遮罩一层灰纱，无法操作。
			buttons: {
				'重置':function(){
					$('.Mod-dept-dial').find('select,input,textarea').val('');
				},
				'完成': function() {
					$.ajax({
						url: 'DeptServlet?message=modDept',
						type: 'POST',
						dataType:'json',
						data: {
							'dept_id':deptId.text(),
							'dept_name':$('#mod-deptName').val(),
							'dept_type':$('#mod-deptType').val(),
							'dept_tel':$('#mod-deptTel').val(),
							'dept_fax':$('#mod-deptFax').val(),
							'dept_sup':$('#mod-deptSupName').val(),
							'dept_setdate':$('#mod-deptSetDate').val(),
							'dept_description':$('#mod-deptDesc').val()
						},
						success: function (data) {
							showDept(data);
							alert('修改成功！');
							$('.Mod-dept-dial').dialog('close');
						},
						error:function (xhr, status, statusText) {
							alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
						}
					});
				}
			}
		});
	});
	/*部门删除*/
	$(document).on('click','.dept-del',function() {
		if (confirm("确定删除该条信息吗？")) {
			//var trs = $(this).parent().parent().eq(0).remove();
			var deptId = $(this).parent().parent().find('td').eq(0);
			$.ajax({
				url: 'DeptServlet?message=delDept',
				type: 'POST',
				dataType:'json',
				data: {
					'dept_id':deptId.text()
				},
				success: function (data) {
					//showDept(data);
					$('#main-dept-table tr:not(:first)').remove();
					var dept=eval(data);
					if(dept.flag == 0) {
						alert("该部门下还有员工不能删除");
						for(var i = 0; i < dept.darray1.length; i ++){
							var depts = $('<tr><td>' + dept.darray1[i]['dept_id'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_name'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_typename'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_tel'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_fax'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_supname'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_setdate'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_description'] + '</td>'
										+ '<td ><a class="dept-mod" href="javascript:;">修改</a>'
										+ '/<a class="dept-del" href="javascript:;">删除</a></td></tr>');
							$('#main-dept-table tbody').append(depts);
						}
					} else {
						
						for(var i = 0; i < dept.darray1.length; i ++){
							var depts = $('<tr><td>' + dept.darray1[i]['dept_id'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_name'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_typename'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_tel'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_fax'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_supname'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_setdate'] + '</td>'
										+ '<td>' + dept.darray1[i]['dept_description'] + '</td>'
										+ '<td ><a class="dept-mod" href="javascript:;">修改</a>'
										+ '/<a class="dept-del" href="javascript:;">删除</a></td></tr>');
							$('#main-dept-table tbody').append(depts);
						}
						alert('删除成功！');
					}
				},
				error:function (xhr, status, statusText) {
					alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
				}
			});
		}
	});

	/*点击查询部门员工把之前的数据删掉*/
	$('#show-dept-emp').click(function(){
		$('#dept-emp-table tr:not(:first)').remove();
		$('#emp-deptname option:not(:first)').remove();
		/*部门下拉条*/
		$.ajax({
			type:'post',
			url:'DppSelect?method=deptidtoname',
			datatype:'json',
			success:function(data){
				var json=eval(data);
				
				for(var i = 0;i < json.length; i ++){
					var depts=$('<option>'+json[i]['dept_name']+'</option>');
					$('#dept-emp #emp-deptname').append(depts);
				}
			},
			error:function(status,statusText){
				alert(status+'---'+statusText);
			}
		});
	});

	/*点击查询之后显示部门员工*/
	$('#dept-seacher-emp-btn').click(function(){
		$('#dept-emp-table tr:not(:first)').remove();

		$.ajax({
			type:'post',
			url:'DeptServlet?message=searchDept',
			datatype:'json',
			data:{
					"dept_id":$('#dept-emp #emp-deptid').val(),
					"dept_name":$('#dept-emp #emp-deptname').val(),
					"dept_type":$('#dept-emp #emp-dtype').val()
				},
			success:function(data){
				
				
				var emp = eval(data);

				if(emp.length==0 || emp[0] == null){
					alert('您所查找的数据为空！');
				}else{
					for(var i = 0; i < emp.length; i ++){
						var emps=$('<tr><td>'+ emp[i]['emp_id'] +'</td>'
								+ '<td>'+ emp[i]['emp_name'] +'</td>'
								+ '<td>'+ emp[i]['emp_birth'] +'</td>'
								+ '<td>'+ emp[i]['emp_idcard'] +'</td>'
								+'<td>'+ emp[i]['emp_entrydate'] +'</td>'
								+ '<td>'+ emp[i]['emp_sex'] +'</td><td>'+ emp[i]['emp_deptname'] +'</td>'
								+'<td>'+ emp[i]['emp_postname'] +'</td></tr>');
						$('#dept-emp-table').append(emps);
					}
				}
			},
			error:function(status,statusText){
				alert(status+'---'+statusText);
			}
		});

	});

	function showDept(data){
		$('#main-dept-table tr:not(:first)').remove();

		var dept=eval(data);

		if(dept.length==0){
			alert('您所查找的数据为空！');
		}else{
			for(var i = 0; i < dept.length; i ++){
				var depts = $('<tr><td>' + dept[i]['dept_id'] + '</td>'
							+ '<td>' + dept[i]['dept_name'] + '</td>'
							+ '<td>' + dept[i]['dept_typename'] + '</td>'
							+ '<td>' + dept[i]['dept_tel'] + '</td>'
							+ '<td>' + dept[i]['dept_fax'] + '</td>'
							+ '<td>' + dept[i]['dept_supname'] + '</td>'
							+ '<td>' + dept[i]['dept_setdate'] + '</td>'
							+ '<td>' + dept[i]['dept_description'] + '</td>'
							+ '<td ><a class="dept-mod" href="javascript:;">修改</a>'
							+ '/<a class="dept-del" href="javascript:;">删除</a></td></tr>');
				$('#main-dept-table tbody').append(depts);
			}
			
		}
	}
	
})