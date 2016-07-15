/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2016-07-05 21:11:25
 * @version $Id$
 */

$(function(){
	$('#shiyongqi-tabs').tabs();
	$('#lizhi-manage-tabs').tabs();
	$('#dept-change-tabs').tabs();
	$('#post-change-tabs').tabs();

	/*部门下拉条*/
	function deptselect(){
		$('.ruzhi-xinxi-jibenxinxi-select .dept_name_select option:not(:first)').remove();
		$('.ruzhi-yidong-shiyongqi-select #shiyongqi-tabs1 .dept_name_select option:not(:first)').remove();
		$('.ruzhi-yidong-shiyongqi-select #shiyongqi-tabs2 .dept_name_select option:not(:first)').remove();
		$('.ruzhi-yidong-bumen-select .dept_name_select option:not(:first)').remove();
		$('.ruzhi-lizhi-select #lizhi-manage-tabs1 .dept_name_select option:not(:first)').remove();
		$('.ruzhi-lizhi-select #lizhi-manage-tabs2 .dept_name_select option:not(:first)').remove();
		
		$.ajax({
			type:'post',
			url:'DppSelect?method=deptidtoname',
			datatype:'json',
			success:function(data){
				var json=eval(data);
				
				for(var i = 0;i < json.length; i ++){
					var depts=$('<option>'+json[i]['dept_name']+'</option>');
					$('.dept_name_select').append(depts);
				}
			},
			error:function(status,statusText){
				alert(status+'---'+statusText);
			}
		});
	}
	
	
	/*岗位下拉条*/
	function postselect(){
		$('.ruzhi-xinxi-jibenxinxi-select .post_name_select option:not(:first)').remove();
		$('.ruzhi-yidong-shiyongqi-select #shiyongqi-tabs1 .post_name_select option:not(:first)').remove();
		$('.ruzhi-yidong-shiyongqi-select #shiyongqi-tabs2 .post_name_select option:not(:first)').remove();
		$('.ruzhi-yidong-gangwei-select .post_name_select option:not(:first)').remove();
		$('.ruzhi-lizhi-select #lizhi-manage-tabs1 .post_name_select option:not(:first)').remove();
		$('.ruzhi-lizhi-select #lizhi-manage-tabs2 .post_name_select option:not(:first)').remove();
		
		$.ajax({
			type:'post',
			url:'DppSelect?method=postidtoname',
			datatype:'json',
			success:function(data){
				var json=eval(data);
				
				for(var i = 0;i < json.length; i ++){
					var posts=$('<option>'+json[i]['post_name']+'</option>');
					$('.post_name_select').append(posts);
				}
			},
			error:function(status,statusText){
				alert(status+'---'+statusText);
			}
		});
	}
	
	$('.op-li-first,.op-li-second,.op-li-forth').click(function(){
		deptselect();
		postselect();
	})
	
	
	
		/*三级菜单鼠标点击效果结束*/
	
	
	/*员工入职管理查询界面-员工基本信息表单 开始*/
	$(".ruzhi-msg-submit-btn").click(function(){
		 $.ajax({
             type : 'POST', //这里可以换成GET
             url : 'DppSelect?method=insertemp',//该文件路径只能在当前路径下，../json.json出错
             dataType:'json',
             /*===============================================*/
             data:{'emp_name':$(".basic-msg-emp-name").val(),'emp_sex':$(".basic-msg-emp-sex").val(),'emp_birth':$(".basic-msg-emp-birth").val(),'emp_idcard':$(".basic-msg-emp-IDcard").val(),'dept_name':$(".basic-msg-emp-dept").val(),'post_name':$(".basic-msg-emp-post").val(),'emp_entrydate':$(".basic-msg-emp-entry-date").val(),'emp_paritdate':$(".basic-msg-emp-work-date").val(),'emp_form':$(".basic-msg-emp-work-type").val(),'emp_dgree':$(".basic-msg-emp-dgree").val(),'emp_source':$(".basic-msg-emp-source").val(),'emp_ifprobation':$(".basic-msg-emp-if-haspropation:checked").val(),'begintime':$(".basic-msg-emp-propation-start").val(),'endtime':$(".basic-msg-emp-propation-end").val()},

                 success : function (data) {
                    	alert("成功！");
                 },
                 error:function (xhr, status, statusText) {
                     alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
                 }
         })
	})
	/*员工入职管理查询界面-员工基本信息表单 结束*/
	
	
	/*员工试用期管理查询界面-查询试用期员工按钮 开始*/
	$(".probation-emp-search-btn").click(function(){
		// 获取试用期员工信息列表
		// 取出json数据并添加到列表中
		// alert("sdf");
		
		$(".probation-emp-search-list tr:not(:first)").remove();
		// 将之前查询结果清空
		 $('.new-emp tr:not(:first)').remove();
		//从数据库中获取新聘员工信息显示在网页中
		$.ajax({
			type : 'POST', //这里可以换成GET
			url :'EmployeeServlet?method=seachproba',
			dataType:'json',
			 data:{'emp_id':$(".probation-emp-tabs1-emp-id").val(),'emp_name':$(".probation-emp-tabs1-emp-name").val(),'proba_begintime':$(".probation-emp-tabs1-start-date").val(),'dept_name':$(".probation-emp-tabs1-dept-name").val(),'post_name':$(".probation-emp-tabs1-post-name").val(),'proba_endtime':$(".probation-emp-tabs1-end-date").val(),'state_name':$(".probation-emp-tabs1-status").val()},

			success : function (data) {
				var emp=eval(data);
				
				if(emp.length==0 || emp[0] == null){
					alert('您所查询的数据为空！');
				}else{
					
				  for(var i = 0; i < emp.length; i ++){
				  	var emps=$('<tr><td>'+emp[i]['emp_id']+'</td><td>'+emp[i]['emp_name']+'</td>'
				  		  +'<td>'+emp[i]['dept_name']+'</td><td>'+emp[i]['post_name']+'</td><td>'+emp[i]['state_name']+'</td>'
				  	      +'<td>'+emp[i]['proba_begindate']+'</td><td>'+emp[i]['proba_enddate']+'</td><td><input type="button" value="编辑"/></td></tr>');

				  	$('.probation-emp-search-list').append(emps);	
				  	$(".probation-emp-search-list").find('input').attr('class','ruzhi-yidong-shiyongqi-select-edit-btn');
					}
				
				   /*员工试用期管理查询界面-员工信息编辑按钮-跳转效果开始*/
                  $('.ruzhi-yidong-shiyongqi-select-edit-btn').click(function(event){
                  
                     var probationId = $(this).parent().parent().find('td:eq(0)').text();
                     $(".probation-edit-emp-id").val(probationId);
                     // alert(probationId);
                         $('.ruzhi-yidong-shiyongqi').dialog({
                             title:'试用期管理',
                             width:'600px',
                             modal:true,     //对话框会遮罩一层灰纱，无法操作。
                             buttons:{
                                 '完成':function(){
                                             $.ajax({
                                                 type : 'POST', //这里可以换成GET
                                                 url : 'DppSelect?method=modProbation',//该文件路径只能在当前路径下，../json.json出错
                                                 dataType:'json',
                                                 /*===============================================*/
                                                 //  data:{'emp_id':$(".probation-edit-emp-id").val(),'comment':$(".probation-edit-emp-pingyu").val(),'statename':$(".probation-edit-emp-result").val(),'dealdate':$(".probation-edit-emp-date").val(),'dealydate':$(".probation-edit-emp-yanqi").val()},
                                                   data:{'emp_id':$(".probation-emp-tabs1-emp-id").val(),'emp_name':$(".probation-emp-tabs1-emp-name").val(),'proba_begintime':$(".probation-emp-tabs1-start-date").val(),'dept_name':$(".probation-emp-tabs1-dept-name").val(),'post_name':$(".probation-emp-tabs1-post-name").val(),'proba_endtime':$(".probation-emp-tabs1-end-date").val(),'state_name':$(".probation-emp-tabs1-status").val(),'emp_id1':$(".probation-edit-emp-id").val(),'comment':$(".probation-edit-emp-pingyu").val(),'statename':$(".probation-edit-emp-result").val(),'dealdate':$(".probation-edit-emp-date").val(),'dealydate':$(".probation-edit-emp-yanqi").val()},

                                                 

                                                     success : function (data) {
                                                    	 //if(data[0]>0){
                                                    	//	 alert("编辑成功！");
                                                    	 var emp=eval(data);
                                                    	 $(".probation-emp-search-list tr:not(:first)").remove();
                                                    	
                                                    		 for(var i = 0; i < emp.length; i ++){
                                                    			
                                             				  	var emps=$('<tr><td>'+emp[i]['emp_id']+'</td><td>'+emp[i]['emp_name']+'</td>'
                                             				  		  +'<td>'+emp[i]['dept_name']+'</td><td>'+emp[i]['post_name']+'</td><td>'+emp[i]['state_name']+'</td>'
                                             				  	      +'<td>'+emp[i]['proba_begindate']+'</td><td>'+emp[i]['proba_enddate']+'</td><td><input type="button" value="编辑"/></td></tr>');

                                             				  	$('.probation-emp-search-list').append(emps);	
                                             				  	$(".probation-emp-search-list").find('input').attr('class','ruzhi-yidong-shiyongqi-select-edit-btn');
                                             					}	
                                                    		 
                                                    	// }
                                                    	// else{
                                                    	//	 alert("编辑失败")
                                                    	// }
                                            
                                                     },
                                                     error:function (xhr, status, statusText) {
                                                         alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
                                                     }
                                             });
                                           
                                             $('.ruzhi-yidong-shiyongqi').dialog('close');
                                         }
                             }
                         });
                     });     
                /*员工试用期管理查询界面-员工信息编辑按钮-跳转效果结束*/
				}
			},
			error:function (xhr, status, statusText) {
				alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
			}
		});
	})

	
	/*员工试用期管理查询界面-查询已转正员工按钮 开始*/
	$(".probation-official-emp-search-btn").click(function(){
		
		// 获取试用期员工信息列表
		// 取出json数据并添加到列表中
		// alert("sdf");
		
		$(".probation-official-emp-search-list tr:not(:first)").remove();
		// 将之前查询结果清空
		// $('.new-emp tr:not(:first)').remove();
		//从数据库中获取新聘员工信息显示在网页中
		$.ajax({
			type : 'POST', //这里可以换成GET
			url : 'EmployeeServlet?method=seachregular', 
			dataType:'json',
			 data:{'emp_id':$(".probation-emp-tabs2-emp-id").val(),'emp_name':$(".probation-emp-tabs2-emp-name").val(),'begintime':$(".probation-emp-tabs2-start-date").val(),'dept_name':$(".probation-emp-tabs2-dept-name").val(),'post_name':$(".probation-emp-tabs2-post-name").val(),'endtime':$(".probation-emp-tabs2-end-date").val()},

			success : function (data) {
				var emp=eval(data);
				if(emp.length==0 || emp[0] == null){
					alert('您所查询的数据为空')
				}else{
					  for(var i = 0; i < emp.length; i ++){
					  	var emps=$('<tr><td>'+emp[i]['emp_id']+'</td><td>'+emp[i]['emp_name']+'</td>'
					  		  +'<td>'+emp[i]['post_name']+'</td><td>'+emp[i]['dept_name']+'</td><td>'+emp[i]['proba_comment']+'</td>'
					  	      +'<td>'+emp[i]['proba_dealdate']+'</td></tr>');
					  	$('.probation-official-emp-search-list').append(emps);				
					  }
				}
			},
			error:function (xhr, status, statusText) {
				alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
			}

		});
	})

	/*员工部门调动查询界面-部门调动员工信息-查询效果开始*/
	$(".dept-change-emp-search-btn").click(function(){
		// 获取试用期员工信息列表
		// 取出json数据并添加到列表中
		// alert("sdf");
		
		$(".dept-change-emp-search-list tr:not(:first)").remove();
		// 将之前查询结果清空
		// $('.new-emp tr:not(:first)').remove();
		//从数据库中获取新聘员工信息显示在网页中
		$.ajax({
			type : 'POST', //这里可以换成GET
			url : 'EmployeeServlet?method=seaemplist',//该文件路径只能在当前路径下，../json.json出错
			dataType:'json',
			 data:{'emp_id':$(".dept-change-tabs1-emp-id").val(),'emp_name':$(".dept-change-tabs1-emp-name").val(),'dept_id':$(".dept-change-tabs1-dept-id").val(),'dept_name':$(".dept-change-tabs1-dept-name").val()},

			success : function (data) {
				var emp=eval(data);
				if(emp.length==0 || emp[0] == null){
					alert('您所查询的数据为空')
				}else{
				  for(var i = 0; i < emp.length; i ++){
				  	var emps=$('<tr><td>'+emp[i]['emp_id']+'</td><td>'+emp[i]['emp_name']+'</td>'
				  		  +'<td>'+emp[i]['dept_name']+'</td><td>'+emp[i]['emp_birth']+'</td><td>'+emp[i]['emp_entrydate']+'</td><td><input type="button" value="编辑"/></td></tr>');
				  	$('.dept-change-emp-search-list').append(emps);	
				  	$(".dept-change-emp-search-list").find('input').attr('class','ruzhi-yidong-bumen-select-edit-btn');
					}

					/*员工部门管理查询界面-员工信息编辑按钮-跳转效果开始*/
					  $('.ruzhi-yidong-bumen-select-edit-btn').click(function(event){
					  
					  	var probationId = $(this).parent().parent().find('td:eq(0)').text();
					  	$(".dept-edit-emp-id").val(probationId);
					  	// alert(probationId);
					  		$('.ruzhi-yidong-bumen').dialog({
					  			title:'部门调动管理',
					  			width:'600px',
					  			modal:true,		//对话框会遮罩一层灰纱，无法操作。
					  			buttons:{
					  				'完成':function(){
//					  							修改
					  					
					  					$(".dept-change-emp-search-list tr:not(:first)").remove();
					  					 $.ajax({
                                             type : 'POST', //这里可以换成GET
                                             url : 'DppSelect?method=deptTurn',//该文件路径只能在当前路径下，../json.json出错
                                             dataType:'json',
                                             /*===============================================*/
                                             data:{'emp_id':$(".dept-edit-emp-id").val(),'aftername':$(".dept-edit-emp-changed-dept").val(),'typename':$(".dept-edit-emp-change-type").val(),'reson':$(".dept-edit-emp-change-reason").val(),'date':$(".dept-edit-emp-change-date").val()},
                                                success : function (data) {
                                                	
                                                	 alert('编辑完成！');
                                                	
                                                 },
                                                 error:function (xhr, status, statusText) {
                                                     alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
                                                 }
                                         })
                                         
					  							$('.ruzhi-yidong-bumen').dialog('close');
					  						}
					  			}
					  		});
					  	});		
				/*员工部门管理查询界面-员工信息编辑按钮-跳转效果开始*/
				}
			},
			error:function (xhr, status, statusText) {
				alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
			}
		});
	})
	/*员工部门管理查询界面-调转部门员工信息--查询效果结束*/

	/*员工部门调动查询界面-部门已调动员工信息-查询效果开始*/
	$(".dept-haschanged-emp-search-btn").click(function(){
		
		$(".dept-haschanged-emp-search-list tr:not(:first)").remove();
		// 将之前查询结果清空
		// $('.new-emp tr:not(:first)').remove();
		//从数据库中获取新聘员工信息显示在网页中
		
		$.ajax({
			type : 'POST', //这里可以换成GET
			url : 'EmployeeServlet?method=getdturnMessage', 
			dataType:'json',
			data:{'emp_id':$(".dept-change-tabs2-emp-id").val(),'emp_name':$(".dept-change-tabs2-emp-name").val(),'dturn_name':$(".dept-change-tabs2-dept-change-style").val(),'begintime':$(".dept-change-tabs2-start-time").val(),'endtime':$(".dept-change-tabs2-end-time").val()},
			//data:{'emp_id':1,'emp_name':'','dturn_name':'','begintime':'','endtime':''},

			success : function (data) {
				var emp=eval(data);
				if(emp.length==0 || emp[0] == null){
					alert('您所查询的数据为空')
				}else{
				  for(var i = 0; i < emp.length; i ++){
				  	var emps=$('<tr><td>'+emp[i]['dturn_empid']+'</td><td>'+emp[i]['emp_name']+'</td><td>'+emp[i]['dturn_beforename']+'</td><td>'+emp[i]['dturn_aftername']+'</td>'
				  		  +'<td>'+emp[i]['dturn_typeName']+'</td><td>'+emp[i]['dturn_data']+'</td>'
				  	      +'<td>'+emp[i]['dturn_reason']+'</td></tr>');

				  	$('.dept-haschanged-emp-search-list').append(emps);	
				  }
				}
			},
			error:function (xhr, status, statusText) {
				alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
			}
		});
	})
	/*员工部门调动查询界面-部门已调动员工信息-查询效果结束*/

	/*员工岗位调动查询界面-员工信息查询按钮开始*/

	$(".post-change-emp-search-btn").click(function(){
		
		$(".post-change-emp-search-list tr:not(:first)").remove();
		// 将之前查询结果清空
		// $('.new-emp tr:not(:first)').remove();
		//从数据库中获取新聘员工信息显示在网页中
		$.ajax({
			type : 'POST', //这里可以换成GET
			url : 'EmployeeServlet?method=seaemplist',//该文件路径只能在当前路径下，../json.json出错
			dataType:'json',
			 data:{'emp_id':$(".post-change-tabs1-emp-id").val(),'emp_name':$(".post-change-tabs1-emp-name").val(),'post_name':$(".post-change-tabs1-post-name").val(),'post_id':$(".post-change-tabs1-post-id").val()},

			success : function (data) {
				var emp=eval(data);
				if(emp.length==0 || emp[0] == null){
					alert('您所查询的数据为空')
				}else{
				  for(var i = 0; i < emp.length; i ++){
				  	var emps=$('<tr><td>'+emp[i]['emp_id']+'</td><td>'+emp[i]['emp_name']+'</td>'
				  		  +'<td>'+emp[i]['post_name']+'</td><td>'+emp[i]['emp_birth']+'</td><td>'+emp[i]['emp_entrydate']+'</td><td><input type="button" value="编辑"/></td></tr>');

				  	$('.post-change-emp-search-list').append(emps);	
				  	$(".post-change-emp-search-list").find('input').attr('class','ruzhi-yidong-gangwei-select-edit-btn');
					}
					/*员工岗位管理查询界面-员工信息编辑按钮-跳转效果开始*/
					 $('.ruzhi-yidong-gangwei-select-edit-btn').click(function(){
					 	var probationId = $(this).parent().parent().find('td:eq(0)').text();
					 	$(".post-edit-emp-id").val(probationId);
					 			$('.ruzhi-yidong-gangwei').dialog({
					 				title:'岗位调动',
					 				width:'600px',
					 				modal:true,		//对话框会遮罩一层灰纱，无法操作。
					 				buttons:{
					 					'完成':function(){
					 						 $.ajax({
	                                             type : 'POST', //这里可以换成GET
	                                             url : 'DppSelect?method=postTrun',//该文件路径只能在当前路径下，../json.json出错
	                                             dataType:'json',
	                                             /*===============================================*/
	                                             data:{'emp_id':$(".post-edit-emp-id").val(),'paftername':$(".post-edit-changed-post").val(),'ptypeName':$(".post-edit-change-type").val(),'remark':$(".post-edit-remark").val(),'pdate':$(".post-edit-change-date").val()},

	                                                 success : function (data) {
	                                                	 alert('编辑完成！');
	                                                 },
	                                                 error:function (xhr, status, statusText) {
	                                                     alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
	                                                 }
	                                         })
	                                          
					 								$('.ruzhi-yidong-gangwei').dialog('close');
					 					
					 					}
					 				}
					 			});
					 		});
					/*员工岗位管理查询界面-员工信息编辑按钮-跳转效果结束*/
				}
			},
			error:function (xhr, status, statusText) {
				alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
			}
		});
	})

	/*员工异动管理-查询已调动岗位员工按钮 开始*/
	$(".post-haschanged-emp-search-btn").click(function(){
	
		$(".post-haschanged-emp-search-list tr:not(:first)").remove();
		// 将之前查询结果清空
		//从数据库中获取新聘员工信息显示在网页中
		$.ajax({
			type : 'POST', //这里可以换成GET
			url : 'EmployeeServlet?method=getpturnMessage',//该文件路径只能在当前路径下，../json.json出错
			dataType:'json',
			 data:{'emp_id':$(".post-change-tabs2-emp-id").val(),'emp_name':$(".post-change-tabs2-emp-name").val(),'pturn_name':$(".post-change-tabs2-post-change-style").val(),'begintime':$(".post-change-tabs2-start-time").val(),'endtime':$(".post-change-tabs2-end-time").val()},

			success : function (data) {
				var emp=eval(data);
				if(emp.length==0 || emp[0] == null){
					alert('您所查询的数据为空')
				}else{
					  for(var i = 0; i < emp.length; i++){
					 	var emps=$('<tr><td>'+emp[i]['pturn_empid']+'</td><td>'+emp[i]['emp_name']+'</td><td>'+emp[i]['pturn_beforename']+'</td><td>'+emp[i]['pturn_aftername']+'</td>'
					  		  +'<td>'+emp[i]['pturn_typeName']+'</td><td>'+emp[i]['pturn_date']+'</td><td>'+emp[i]['pturn_reason']+'</td></tr>');
					
//						var a=$("<tr><td>1</td><td>2</td><td>3</td></tr>")
							  	//$('.post-haschanged-emp-search-list').append(emps);
						  $('.post-haschanged-emp-search-list').append(emps);
					  }
				}
			},
			error:function (xhr, status, statusText) {
				alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
			}

		});
	})


	/*离职员工信息查询开始*/

		$(".dimmision-emp-search-btn").click(function(){
	
		$(".dimmision-emp-search-list tr:not(:first)").remove();
		// 将之前查询结果清空
	
		$.ajax({
			type : 'POST', //这里可以换成GET
			url : 'EmployeeServlet?method=seaemplist',//该文件路径只能在当前路径下，../json.json出错
			dataType:'json',
			 data:{'emp_id':$(".leave-manage-tabs1-emp-id").val(),'emp_name':$(".leave-manage-tabs1-emp-name").val(),'dept_name':$(".leave-manage-tabs1-dept-name").val(),'post_name':$(".leave-manage-tabs1-post-name").val()},

			success : function (data) {
				var emp=eval(data);
				if(emp.length==0 || emp[0] == null){
					alert('您所查询的数据为空')
				}else{
				  for(var i = 0; i < emp.length; i ++){
				  	var emps=$('<tr><td>'+emp[i]['emp_id']+'</td><td>'+emp[i]['emp_name']+'</td>'
				  		  +'<td>'+emp[i]['dept_name']+'</td><td>'+emp[i]['post_name']+'</td><td>'+emp[i]['emp_entrydate']+'</td>'
				  	      +'<td>'+emp[i]['emp_idcard']+'</td><td><input type="button" value="编辑"/></td></tr>');

				  	$('.dimmision-emp-search-list').append(emps);	
				  	$(".dimmision-emp-search-list").find('input').attr('class','dimmision-select-edit-btn');
					}
					/*员工离职管理查询界面-员工信息编辑按钮-跳转效果开始*/
					 $('.dimmision-select-edit-btn').click(function(){
					 	var probationId = $(this).parent().parent().find('td:eq(0)').text();
//				alert("probationId");
					
					 	$(".leave-edit-emp-id").val(probationId);
					 			$('.emp-manage-dimmision').dialog({
					 				title:'离职管理',
					 				width:'600px',
					 				modal:true,		//对话框会遮罩一层灰纱，无法操作。
					 				buttons:{
					 					'完成':function(){
					 						  $.ajax({
	                                                 type : 'POST', //这里可以换成GET
	                                                 url : 'DppSelect?method=dischargeDeal',//该文件路径只能在当前路径下，../json.json出错
	                                                 dataType:'json',
	                                                 /*===============================================*/
	                                                 data:{'emp_id':$(".leave-edit-emp-id").val(),'dischgdate':$(".leave-edit-leave-date").val(),'type':$(".leave-edit-leave-type").val(),'dinec':$(".leave-edit-leave-quxiang").val(),'ifenter':$(".leave-edit-ifaddto-base:checked").val()},
	                                                     success : function (data) {
	                                                        //var dataNum = eval(data);
	                                                       // if(dataNum[0]['number']=="1"){
	                                                        	alert("成功！");
	                                                       //}
	                                                       // else{
	                                                       // 	alert("error");
	                                                       // }
	                                                    	
	                                                     },
	                                                     error:function (xhr, status, statusText) {
	                                                         alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
	                                                     }
	                                             })
					 						 alert('编辑完成！');
					 								$('.emp-manage-dimmision').dialog('close');
					 					
					 					}
					 				}
					 			});
					 		});
					/*员工离职管理查询界面-员工信息编辑按钮-跳转效果结束*/
				}
			},
			error:function (xhr, status, statusText) {
				alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
			}
		});
		})

	/*员工异动管理-查询已离职员工按钮 开始*/
	$(".hasdimmision-emp-search-btn").click(function(){
	
		$(".hasdimmision-emp-search-list tr:not(:first)").remove();
		// 将之前查询结果清空
		//从数据库中获取新聘员工信息显示在网页中
		$.ajax({
			type : 'POST', //这里可以换成GET
			url : 'EmployeeServlet?method=getdischarge',//该文件路径只能在当前路径下，../json.json出错
			dataType:'json',
			data:{'emp_id':$(".leave-manage-tabs2-emp-id").val(),'emp_name':$(".leave-manage-tabs2-emp-name").val(),'distype':$(".leave-manage-tabs2-leave-style").val(),'dept_name':$(".leave-manage-tabs2-dept-name").val(),'post_name':$(".leave-manage-tabs2-post-name").val(),'begintime':$(".leave-manage-tabs2-start-time").val(),'endtime':$(".leave-manage-tabs2-end-time").val()},

			success : function (data) {
				var emp=eval(data);
				if(emp.length==0 || emp[0] == null){
					alert('您所查询的数据为空')
				}else{
					  for(var i = 0; i < emp.length; i ++){
					  	var emps=$('<tr><td>'+emp[i]['dischg_empid']+'</td><td>'+emp[i]['emp_name']+'</td>'
					  		  +'<td>'+emp[i]['post_name']+'</td><td>'+emp[i]['dept_name']+'</td><td>'+emp[i]['dischg_type']+'</td>'
					  	      +'<td>'+emp[i]['dischg_date']+'</td></tr>');
					  	$('.hasdimmision-emp-search-list').append(emps);				
					  }
				}
			},
			error:function (xhr, status, statusText) {
				alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
			}

		});
	})



	/*员工入职管理-基本信息-是否有试用期部分开始*/
			$(".basic-message-probation").click(function(){
			
				
				if($(".basic-message-probation:checked").val()=='1'){
					$(".ruzhi-xinxi-jibenxinxi-select .probation-box").show();
				}
				else{
					$(".ruzhi-xinxi-jibenxinxi-select .probation-box").hide();
				}
			})
			
	/*员工入职管理-基本信息-是否有试用期部分结束*/
	/*试用期状态开始*/
			$(".probation-edit-emp-result").change(function(){
				
				// alert($(".shiyongqi-status option:selected").text());
				var status=$(".probation-edit-emp-result option:selected").text();
//				 alert(status);
				if(status=='延期'){
					// alert("sdf"+status+"sdf");
					$(".delay-time-box").show();
				}
				else{
					$(".delay-time-box").hide();
				}
			})
	/*试用期状态结束*/
	/*员工入职管理-基本信息-新建部门部分开始*/
			$(".ruzhi-xinxi-jibenxinxi-select .build-dept").click(function(){
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
				/*上级部门部门下拉条*/
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
						$('#new-dept').dialog({
							title:'新建部门',
							width:'900px',
							modal:true,		//对话框会遮罩一层灰纱，无法操作。
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
											'dept_sup':$('#deptSurNum').val(),
											'dept_setdate':$('#deptSetDate').val(),
											'dept_description':$('#deptDesc').val()
										},
										success:function(){
											deptselect();
											
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
			})
	/*员工入职管理-基本信息-新建部门部分结束*/

	/*员工入职管理-基本信息-新建岗位部分开始*/
			$(".ruzhi-xinxi-jibenxinxi-select .build-post").click(function(){
				//失去焦点时岗位名称验证重复
				$('#postName').blur(function() {
					$.ajax({
						type:'post',
						url:'PostServlet?message=checkPost',
						datatype:'json',
						data:{
							'post_name':$('#postName').val()
						},
						success:function(data){
							var post = eval(data);
							if(post[0] == '0'){
								alert("岗位名称重复！");
							}
						},
						error:function(status,statusText){
							alert(status+'---'+statusText);
						}
					});
				});
						$('#new-post').dialog({
							title:'新建岗位',
							width:'900px',
							modal:true,		//对话框会遮罩一层灰纱，无法操作。
							buttons: {
								'重置':function(){
									$('#new-post').find('select,input,textarea').val('');
								},
								'完成': function() {
									$.ajax({
										type:'post',
										url:'PostServlet?message=newPost',
										datatype:'json',
										data:{
											'post_name':$('#postName').val(),
											'post_type':$('#postType').val(),
											'post_count':$('#postStaffing').val()
										},
										success:function(data){
											postselect();
											
											$('#new-post').dialog('close');
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
			})
	/*员工入职管理-基本信息-新建岗位部分结束*/
})