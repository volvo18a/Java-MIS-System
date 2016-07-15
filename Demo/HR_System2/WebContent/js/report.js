/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2016-07-05 21:11:59
 * @version $Id$
 */

$(function(){


	//设置人事月报的年，月范围(默认为当前年份，当前月份)
	var today = new Date();
	var month=("0" + (today.getMonth() + 1)).slice(-2);
	var day=("0" + today.getDate()).slice(-2);

	for (var i = (today.getFullYear() - 50); i < today.getFullYear(); i++) {
		var year = $('<option>' + i + '</option>');
		$('#emp-year').append(year);
	}
	$('#emp-year').append('<option selected="selected">' + today.getFullYear() + '</option>');

	for (var i = 1; i <= 12; i++) {
		if (i == (today.getMonth() + 1)) {
			if(i<10){
				var month = $('<option selected="selected">' + ('0'+(today.getMonth() + 1)) + '</option>');
			}else{
				var month = $('<option selected="selected">' + (today.getMonth() + 1) + '</option>');
			}
		} else {
			if(i<10){
				var month = $('<option>' + ('0'+i) + '</option>');
			}else{
				var month = $('<option>' + i + '</option>');
			}
		}
		$('#emp-month').append(month);
	}
	$('.report-reset').button();
	$('.import').button();

	/*部门下拉列表*/
	
	$('#dorplist-report a').click(function() {
		$('#new-dep option:not(:first)').remove();
		/*部门下拉条*/
		$.ajax({
			type:'post',
			url:'DppSelect?method=deptidtoname',
			datatype:'json',
			success:function(data){
				var json=eval(data);
				
				for(var i = 0;i < json.length; i ++){
					var depts=$('<option>'+json[i]['dept_name']+'</option>');
					$('#new-dep').append(depts);
				}
			},
			error:function(status,statusText){
				alert(status+'---'+statusText);
			}
		});
	});
	
	$('#report-tabs1 a').click(function() {
		$('#new-dep option:not(:first)').remove();
		/*部门下拉条*/
		$.ajax({
			type:'post',
			url:'DppSelect?method=deptidtoname',
			datatype:'json',
			success:function(data){
				var json=eval(data);
				
				for(var i = 0;i < json.length; i ++){
					var depts=$('<option>'+json[i]['dept_name']+'</option>');
					$('#new-dep').append(depts);
				}
			},
			error:function(status,statusText){
				alert(status+'---'+statusText);
			}
		});
	});
	
	$('#report-tabs2 a').click(function() {
		$('#leave-dep option:not(:first)').remove();
		/*部门下拉条*/
		$.ajax({
			type:'post',
			url:'DppSelect?method=deptidtoname',
			datatype:'json',
			success:function(data){
				var json=eval(data);
				
				for(var i = 0;i < json.length; i ++){
					var depts=$('<option>'+json[i]['dept_name']+'</option>');
					$('#leave-dep').append(depts);
				}
			},
			error:function(status,statusText){
				alert(status+'---'+statusText);
			}
		});
	});
	
	$('#new-search').button().click(function(){
			//将之前查询结果清空
			$('.new-emp tr:not(:first)').remove();
			//从数据库中获取新聘员工信息显示在网页中
			$.ajax({
				type : 'POST', //这里可以换成GET
				url : 'ReportServlet?method=NewEmp',
				dataType:'json',
				data: {"newemp_date1":$('#new-date1').val(), "newemp_date2":$('#new-date2').val(),"dep":$('#new-dep').val()},
				success : function (data) {
					var emp=eval(data);
					if(emp.length==0 || emp[0] == null){
						alert('您所查找的数据为空！');
					}else{
						for(var i = 0; i < emp.length; i ++){
							var emps=$('<tr><td>'+emp[i]['emp_id']+'</td><td>'+emp[i]['emp_deptname']+'</td>'
								  +'<td>'+emp[i]['emp_postname']+'</td><td>'+emp[i]['emp_name']+'</td><td>'+emp[i]['emp_sex']+'</td>'
							      +'<td>'+emp[i]['emp_entrydate']+'</td><td>'+emp[i]['emp_dgree']+'</td></tr>');
							$('.new-emp').append(emps);				
						}
					}
				},
				error:function (xhr, status, statusText) {
					alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
				}

			});
		});

		$('#leave-search').button().click(function(){
			//将之前查询结果清空
			$('.leave-emp tr:not(:first)').remove();
			//从数据库中获取离职员工信息显示在网页中
			$.ajax({
				type : 'POST', //这里可以换成GET
				url : 'ReportServlet?method=LeaveEmp',
				dataType:'json',
				data:{"LeaveEmp_date1":$('#leave-date1').val(), "LeaveEmp_date2":$('#leave-date2').val(),"dep":$('#leave-dep').val()},
				success : function (data) {
					var emp=eval(data);
					if(emp.length==0 || emp[0] == null){
						alert('您所查找的数据为空！');
					}else{
						for(var i = 0; i < emp.length; i ++){
							var emps=$('<tr><td>'+emp[i]['dischg_id']+'</td><td>'+emp[i]['dept_name']+'</td>'
								  +'<td>'+emp[i]['post_name']+'</td><td>'+emp[i]['emp_name']+'</td><td>'+emp[i]['emp_sex']+'</td>'
							      +'<td>'+emp[i]['dischg_date']+'</td><td>'+emp[i]['dischg_type']+'</td></tr>');
							$('.leave-emp').append(emps);				
						}
					}
				},
				error:function (xhr, errorText, errorStatus) {
					alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
				}
			});
		});

		$('#dep-search').button().click(function(){
			//将之前查询结果清空
			$('.dep-emp tr:not(:first)').remove();
			//从数据库中获取部门调动员工信息显示在网页中
			$.ajax({
				type : 'POST', //这里可以换成GET
				url : 'ReportServlet?method=DeptTurn',
				dataType:'json',
				data:{"deptturn_date1":$('#dep-date1').val(), "deptturn_date2":$('#dep-date2').val()},
				success : function (data) {
					var emp=eval(data);
					if(emp.length==0 || emp[0] == null){
						alert('您所查找的数据为空！');
					}else{
						for(var i = 0; i < emp.length; i ++){
							var emps=$('<tr><td>'+emp[i]['dturn_id']+'</td><td>'+emp[i]['dturn_beforename']+'</td>'
								  +'<td>'+emp[i]['dturn_aftername']+'</td><td>'+emp[i]['emp_name']+'</td><td>'+emp[i]['emp_sex']+'</td>'
							      +'<td>'+emp[i]['dturn_data']+'</td><td>'+emp[i]['dturn_reason']+'</td></tr>');
							$('.dep-emp').append(emps);				
						}
					}
				},
				error:function (xhr, errorText, errorStatus) {
					alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
				}
			});
		});

		$('#post-search').button().click(function(){
			//将之前查询结果清空
			$('.post-emp tr:not(:first)').remove();
			//从数据库中获取岗位调动员工信息显示在网页中
			$.ajax({
				type : 'POST', //这里可以换成GET
				url : 'ReportServlet?method=PostTurn',
				dataType:'json',
				data:{"postturn_date1":$('#post-date1').val(), "postturn_date2":$('#post-date2').val()},
				success : function (data) {
					var emp=eval(data);
					if(emp.length==0 || emp[0] == null){
						alert('您所查找的数据为空！');
					}else{
						for(var i = 0; i < emp.length; i ++){
							var emps=$('<tr><td>'+emp[i]['pturn_id']+'</td><td>'+emp[i]['pturn_deptname']+'</td><td>'+emp[i]['pturn_beforename']+'</td>'
								  +'<td>'+emp[i]['pturn_aftername']+'</td><td>'+emp[i]['emp_name']+'</td><td>'+emp[i]['emp_sex']+'</td>'
							      +'<td>'+emp[i]['pturn_date']+'</td><td>'+emp[i]['pturn_reason']+'</td></tr>');
							$('.post-emp').append(emps);				
						}
					}
				},
				error:function (xhr, errorText, errorStatus) {
					alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
				}
			});
		});

		$('#emp-search').button().click(function(){
			//将之前查询结果清空
			$('.emp-report tr:not(:first,.second)').remove();
			
			//从数据库中获取岗位调动员工信息显示在网页中
			$.ajax({
				type : 'POST', //这里可以换成GET
				url :'ReportServlet?method=EmpReport',
				dataType:'json',
				data:{"emp_year":$('#emp-year').val(), "emp_month":$('#emp-month').val()},
				success : function (data) {
					var emp=eval(data);
					if(emp.length==0 || emp[0] == null){
						alert('您所查找的数据为空！');
					}else{
						for(var i = 0; i < emp.length; i ++){
							var emps=$('<tr><td>'+emp[i]['dept_name']+'</td><td>'+emp[i]['emp_beforenum']+'</td><td>'+emp[i]['emp_afternum']+'</td>'
								  +'<td>'+emp[i]['emp_newnum']+'</td><td>'+emp[i]['emp_dischargenum']+'</td><td>'+emp[i]['turnin_num']+'</td>'
								  +'<td>'+emp[i]['turnout_num']+'</td><td>'+emp[i]['graduate_num']+'</td><td>'+emp[i]['college_num']+'</td>'
							      +'<td>'+emp[i]['junior_num']+'</td><td>'+emp[i]['senior_num']+'</td></tr>');
							$('.emp-report').append(emps);				
						}
					}
				},
				error:function (xhr, errorText, errorStatus) {
					alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
				}
			});
		});


})