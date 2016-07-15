/*
 * @Author: wcs
 * @Date:   2016-06-29 10:18:35
 * @Last Modified by:   wcs
 * @Last Modified time: 2016-07-04 15:46:41
 */

$(function() {

	$('#dorplist-dept>a').click(function() {
		if ($('.dorplist-dept-op').css('display') == "none") {
			DorpListParentHide();
		}
		$('.dorplist-dept-op').slideToggle("slow");		
	});
	$('#dorplist-post>a').click(function() {
		if ($('.dorplist-post-op').css('display') == "none") {
			DorpListParentHide();
		}
		$('.dorplist-post-op').slideToggle("slow");
	});
	$('#dorplist-entry>a').click(function() {
		if ($('.dorplist-entry-op').css('display') == "none") {
			DorpListParentHide();
		}
		$('.dorplist-entry-op').slideToggle("slow");
	});
	$('#dorplist-entry .dorplist-entry-op .op-li').click(function() {
		if ($(this).find(".thirdNav2").css('display') == "none") {
			DorpListGroundChildrenHide();
		}
		$(this).find(".thirdNav2").slideToggle("slow");
	});
	$('#dorplist-entry .dorplist-entry-op .op-li').click(function() {
		if ($(this).find(".thirdNav3").css('display') == "none") {
			DorpListGroundChildrenHide();
		}
		$(this).find(".thirdNav3").slideToggle("slow");
	});
	$("#dorplist-entry .dorplist-entry-op .op-li-first").click(function(){
		DorpListChildrenHide();
		$("#main-right .ruzhi-xinxi-jibenxinxi-select").show().siblings().hide();
	})
	$("#dorplist-entry .dorplist-entry-op .op-li-forth").click(function(){
		DorpListChildrenHide();
		$("#main-right .ruzhi-lizhi-select").show().siblings().hide();
	})
	$('#dorplist-report>a').click(function() {
		DorpListParentHide();
		DorpListChildrenHide();
		$('#report').show().siblings().hide();
	});

	$('.dorplist-dept-op li a').eq(0).click(function() {
		DorpListChildrenHide();
		$('#main-dept').toggle();
	});

	$('.dorplist-dept-op li a').eq(1).click(function() {
		DorpListChildrenHide();
		$('#dept-emp').toggle();
	});

	$('.dorplist-post-op li a').eq(0).click(function() {
		DorpListChildrenHide();
		$('#main-post').toggle();
	});
	$('.dorplist-post-op li a').eq(1).click(function() {
		DorpListChildrenHide();
		$('#post-emp').toggle();
	});

	function DorpListParentHide() {
		$('.dorplist-dept-op').slideUp("slow");
		$('.dorplist-post-op').slideUp("slow");
		$('.dorplist-entry-op').slideUp("slow");
	}

	function DorpListChildrenHide() {
		$('#new-dept').hide();
		$('#main-dept').hide();
		$('#dept-emp').hide();

		$('#main-post').hide();
		$('#new-post').hide();
		$('#post-emp').hide();

		$('#main-right .ruzhi-xinxi-jibenxinxi-select').hide();
		$('#main-right .ruzhi-lizhi-select').hide();
		$('#main-right .ruzhi-yidong-shiyongqi-select').hide();
		$('#main-right .ruzhi-yidong-bumen-select').hide();
		$('#main-right .ruzhi-yidong-gangwei-select').hide();

		$('#report').hide();
	}

	function DorpListGroundChildrenHide() {
		$('.dorplist-entry-op .op-li-second .thirdNav2').slideUp();
		$('.dorplist-entry-op .op-li-third .thirdNav3').slideUp();
	}

	$('#main-dept .btn-info').click(function() {
		$('#new-dept').dialog({
			title: '新建部门',
			width: '700px',
			modal: true, //对话框会遮罩一层灰纱，无法操作。
			buttons: {
				'完成': function() {
						// $.ajax({
						// 	url:'report.json',
						// 	type:'POST',
						// 	success:function(){
						// 		alert('完成！');
						$('#new-dept').dialog('close');
					}
					// 	});
					// }
			}
		});
	});	

	$('#main-post .btn-info').click(function() {
		$('#new-post').dialog({
			title: '新建岗位',
			width: '700px',
			modal: true, //对话框会遮罩一层灰纱，无法操作。
			buttons: {
				'完成': function() {
						// $.ajax({
						// 	url:'report.json',
						// 	type:'POST',
						// 	success:function(){
						// 		alert('完成！');
						$('#new-post').dialog('close');
					}
					// 	});
					// }
			}
		});
	});	

	$('#main-dept-table tbody tr td a').click(function() {
		if ($(this).html() == "修改") {
			$('.Mod-dept-dial').dialog({
				title: '部门修改',
				width: '600px',
				modal: true, //对话框会遮罩一层灰纱，无法操作。
				buttons: {
					'完成': function() {
							// $.ajax({
							// 	url:'report.json',
							// 	type:'POST',
							// 	success:function(){
							// 		alert('完成！');
							$('.Mod-dept-dial').dialog('close');
						}
						// 	});
						// }
				}
			});
		} else if ($(this).html() == "删除") {
			if (confirm("确定删除该条信息吗？")) {
				var trs = $(this).parent().parent().eq(0).remove();
			}
		}
	});

	$('#main-post-table tbody tr td a').click(function() {
		if ($(this).html() == "修改") {
			$('.Mod-dept-dial').dialog({
				title: '岗位修改',
				width: '600px',
				modal: true, //对话框会遮罩一层灰纱，无法操作。
				buttons: {
					'完成': function() {
							// $.ajax({
							// 	url:'report.json',
							// 	type:'POST',
							// 	success:function(){
							// 		alert('完成！');
							$('.Mod-dept-dial').dialog('close');
						}
						// 	});
						// }
				}
			});
		} else if ($(this).html() == "删除") {
			if (confirm("确定删除该条信息吗？")) {
				var trs = $(this).parent().parent().eq(0).remove();
			}
		}
	});

	$("#dorplist-entry .thirdNav2 li").click(function(event) {	
		var index = $(this).index();
		switch (index) {
			case 0:
				showYiDongShiYongQiSelect();
				return false;
				break;
			case 1:
				showYiDongBuMenSelect();
				return false;
				break;
			case 2:
				showYiDongGangWeiSelect();
				return false;
				break;
		}

	});

	$('#shiyongqi-tabs').tabs();
	$('#lizhi-manage-tabs').tabs();
	$('#dept-change-tabs').tabs();
	$('#post-change-tabs').tabs();

	function showYiDongShiYongQiSelect() {
		DorpListChildrenHide();
		$("#main-right .ruzhi-yidong-shiyongqi-select").show().siblings().hide();
	}

	function showYiDongBuMenSelect() {
		DorpListChildrenHide();
		$("#main-right .ruzhi-yidong-bumen-select").show().siblings().hide();
	}

	function showYiDongGangWeiSelect() {
		DorpListChildrenHide();
		$("#main-right .ruzhi-yidong-gangwei-select").show().siblings().hide();
	}

	/*********************************************************************/

	/*三级菜单鼠标点击效果结束*/

/*员工试用期管理查询界面-查询试用期员工按钮 开始*/
$(".probation-emp-search-btn").click(function(){
	// 获取试用期员工信息列表
	// 取出json数据并添加到列表中
	// alert("sdf");
	
	$(".probation-emp-search-list tr:not(:first)").remove();
	// 将之前查询结果清空
	// $('.new-emp tr:not(:first)').remove();
	//从数据库中获取新聘员工信息显示在网页中
	$.ajax({
		type : 'POST', //这里可以换成GET
		url : 'json.json',//该文件路径只能在当前路径下，../json.json出错
		dataType:'json',
		
		success : function (data) {
			var emp=eval(data);
			// var emps="";
			// alert(emp[0]["urla"]);
			// $('#report-tabs').tabs({active:0});
			/*for (var i = 0;i<emp.length;i++){
				emps+=$("<tr><td>"+emp[i]["urla"]+"</td><td>"+emp[i]["urlb"]+"</td><td>"+emp[i]["urlc"]+"</td><td>"+emp[i]["urld"]+"</td><td>"+emp[i]["urle"]+"</td><td>"+emp[i]["urlf"]+"</td><td>"+emp[i]["urlg"]+"</td></tr>");
				 // alert(emps);
				  }
				  */
				  for(var i = 0; i < emp.length; i ++){
				  	var emps=$('<tr><td>'+emp[i]['urla']+'</td><td>'+emp[i]['urlb']+'</td>'
				  		  +'<td>'+emp[i]['urlc']+'</td><td>'+emp[i]['urld']+'</td><td>'+emp[i]['urle']+'</td>'
				  	      +'<td>'+emp[i]['urlf']+'</td><td>'+emp[i]['urlg']+'</td><td><input type="button" value="编辑"/></td></tr>');

				  	$('.probation-emp-search-list').append(emps);	
				  	$(".probation-emp-search-list").find('input').attr('class','ruzhi-yidong-shiyongqi-select-edit-btn');
					// alert($("probation-emp-search-list input:first").attr('class'));	
					/*员工试用期管理查询界面-员工信息编辑按钮-跳转效果开始*/
				  }
				  $('.ruzhi-yidong-shiyongqi-select-edit-btn').click(function(event){
				  
				  	var probationId = $(this).parent().parent().find('td:eq(0)').text();
				  	$(".wwwwwww").html(probationId);
				  	// alert(probationId);
				  		$('.ruzhi-yidong-shiyongqi').dialog({
				  			title:'试用期管理',
				  			width:'600px',
				  			modal:true,		//对话框会遮罩一层灰纱，无法操作。
				  			buttons:{
				  				'完成':function(){

				  							$('.ruzhi-yidong-shiyongqi').dialog('close');
				  						}
				  			}
				  		});
				  	});		
		
		},
		error:function (xhr, status, statusText) {
			alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
		}


	});


})

		/*员工试用期管理查询界面-员工信息编辑按钮-跳转效果结束*/
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
		url : 'json.json',//该文件路径只能在当前路径下，../json.json出错
		dataType:'json',
		
		success : function (data) {
			var emp=eval(data);
			// var emps="";
			// alert(emp[0]["urla"]);
			// $('#report-tabs').tabs({active:0});
			/*for (var i = 0;i<emp.length;i++){
				emps+=$("<tr><td>"+emp[i]["urla"]+"</td><td>"+emp[i]["urlb"]+"</td><td>"+emp[i]["urlc"]+"</td><td>"+emp[i]["urld"]+"</td><td>"+emp[i]["urle"]+"</td><td>"+emp[i]["urlf"]+"</td><td>"+emp[i]["urlg"]+"</td></tr>");
				 // alert(emps);
				  }
				  */
				  for(var i = 0; i < emp.length; i ++){
				  	var emps=$('<tr><td>'+emp[i]['urla']+'</td><td>'+emp[i]['urlb']+'</td>'
				  		  +'<td>'+emp[i]['urlc']+'</td><td>'+emp[i]['urld']+'</td><td>'+emp[i]['urle']+'</td>'
				  	      +'<td>'+emp[i]['urlf']+'</td><td>'+emp[i]['urlg']+'</td></tr>');
				  	$('.probation-official-emp-search-list').append(emps);				
				  }
		
		},
		error:function (xhr, status, statusText) {
			alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
		}

	});
})
	/*员工部门调动查询界面-员工信息编辑按钮-跳转效果开始*/
/*	$(".ruzhi-yidong-bumen-select-edit-btn").click(function(event) {
			$("#main-right .ruzhi-yidong-bumen").show().siblings().hide();
		})*/
		$('.ruzhi-yidong-bumen-select-edit-btn').click(function(){
				$('.ruzhi-yidong-bumen').dialog({
					title:'部门调动',
					width:'600px',
					modal:true,		//对话框会遮罩一层灰纱，无法操作。
					buttons:{
						'完成':function(){
							// $.ajax({
							// 	url:'report.json',
							// 	type:'POST',
							// 	success:function(){
							// 		alert('完成！');
									$('.ruzhi-yidong-bumen').dialog('close');
								}
						// 	});
						// }
					}
				});
			});
		/*员工部门调动查询界面-员工信息编辑按钮-跳转效果结束*/

	/*员工岗位调动查询界面-员工信息编辑按钮-跳转效果开始*/
/*	$(".ruzhi-yidong-gangwei-select-edit-btn").click(function(event) {
			
			$("#main-right .ruzhi-yidong-gangwei").show().siblings().hide();
		})*/
		$('.ruzhi-yidong-gangwei-select-edit-btn').click(function(){
				$('.ruzhi-yidong-gangwei').dialog({
					title:'岗位调动',
					width:'600px',
					modal:true,		//对话框会遮罩一层灰纱，无法操作。
					buttons:{
						'完成':function(){
							// $.ajax({
							// 	url:'report.json',
							// 	type:'POST',
							// 	success:function(){
							// 		alert('完成！');
									$('.ruzhi-yidong-gangwei').dialog('close');
							// 	}
							// });
						}
					}
				});
			});
		/*员工岗位调动查询界面-员工信息编辑按钮-跳转效果结束*/

	/*员工离职调动查询界面-员工信息编辑按钮-跳转效果开始*/
	/*$(".ruzhi-lizhi-select-edit-btn").click(function(event) {
			$("#main-right .ruzhi-lizhi").show().siblings().hide();
		})*/
		$('.ruzhi-lizhi-select-edit-btn').click(function(){
				$('.ruzhi-lizhi').dialog({
					title:'离职员工查询',
					width:'600px',
					modal:true,		//对话框会遮罩一层灰纱，无法操作。
					buttons:{
						'完成':function(){
							// $.ajax({
							// 	url:'report.json',
							// 	type:'POST',
							// 	success:function(){
							// 		alert('完成！');
									$('.ruzhi-lizhi').dialog('close');
							// 	}
							// });
						}
					}
				});
			});
		/*员工离职调动查询界面-员工信息编辑按钮-跳转效果结束*/

		/*员工入职管理-基本信息-是否有试用期部分开始*/
		$("input[class='basic-message-probation']").click(function(){
			// alert($("input[class='basic-message-probation']:checked").val());
			if($("input[class='basic-message-probation']:checked").val()=='是'){
				$(".ruzhi-xinxi-jibenxinxi-select .probation-box").show();
			}
			else{
				$(".ruzhi-xinxi-jibenxinxi-select .probation-box").hide();
			}
		})
		
		/*员工入职管理-基本信息-是否有试用期部分结束*/
		/*试用期状态开始*/
		$(".shiyongqi-status").change(function(){
			// alert("sdf");
			// alert($(".shiyongqi-status option:selected").text());
			var status=$(".shiyongqi-status option:selected").text();
			if(status=='延期'){
				// alert("sdf"+status+"sdf");
				$(".ruzhi-yidong-shiyongqi .delay-time-box").show();
			}
			else{
				$(".ruzhi-yidong-shiyongqi .delay-time-box").hide();
			}
		})
		/*试用期状态结束*/
		/*员工入职管理-基本信息-新建部门部分开始*/
		$(".ruzhi-xinxi-jibenxinxi-select .build-dept").click(function(){

					$('#new-dept').dialog({
						title:'新建部门',
						width:'900px',
						modal:true,		//对话框会遮罩一层灰纱，无法操作。
						buttons:{
							'完成':function(){
								// $.ajax({
								// 	url:'report.json',
								// 	type:'POST',
								// 	success:function(){
								// 		alert('完成！');
										$('#new-dept').dialog('close');
									}
							// 	});
							// }
						}

				});
		})
		/*员工入职管理-基本信息-新建部门部分结束*/

		/*员工入职管理-基本信息-新建岗位部分开始*/
		$(".ruzhi-xinxi-jibenxinxi-select .build-post").click(function(){

					$('#new-post').dialog({
						title:'新建岗位',
						width:'900px',
						modal:true,		//对话框会遮罩一层灰纱，无法操作。
						buttons:{
							'完成':function(){
								// $.ajax({
								// 	url:'report.json',
								// 	type:'POST',
								// 	success:function(){
								// 		alert('完成！');
										$('#new-post').dialog('close');
									}
							// 	});
							// }
						}

				});
		})
		/*员工入职管理-基本信息-新建岗位部分结束*/
		/*main-left导航效果开始*/
	$('.whole .main-left .firstNav').accordion({
		header: 'h3',
	});

	$('.whole .main-left .firstNav .secondNav3').accordion({
		header:'h4',
	});


	$('#report-tabs').tabs({
		active: 0,
	});

	//从数据库中获取部门信息显示在网页中
	for (var i = 0; i < 5; i++) {
		var dep = $('<option>' + i + '</option>');
		$('.dep').append(dep);
	}


	//设置人事月报的年，月范围(默认为当前年份，当前月份)
	var today = new Date();

	for (var i = (today.getFullYear() - 50); i < today.getFullYear(); i++) {
		var year = $('<option>' + i + '</option>');
		$('#emp-year').append(year);
	}
	$('#emp-year').append('<option selected="selected">' + today.getFullYear() + '</option>');

	for (var i = 1; i <= 12; i++) {
		if (i == (today.getMonth() + 1)) {
			var month = $('<option selected="selected">' + (today.getMonth() + 1) + '</option>');
		} else {
			var month = $('<option>' + i + '</option>');
		}
		$('#emp-month').append(month);
	}


	//设置结束时间为今天(月份+1，因为月份是从0开始算的)
	
	var month=("0" + (today.getMonth() + 1)).slice(-2);
		var day=("0" + today.getDate()).slice(-2);
		$('.date2').val(today.getFullYear() + '-' + month + '-' + day);
		$('.date1').val(today.getFullYear() + '-' + month + '-' + day);


	$('.date1,.date2').datepicker({
		dateFormat: 'yy-mm-dd',
		dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
		monthNamesShort: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
		showWeek: true,
		weekHeader: '周',
		firstDay: 0,
		showOtherMonths: true,
		selectOtherMonths: true,
		changeMonth: true,
		changeYear: true,
		showButtonPanel: true,
		closeText: '关闭',
		currentText: '今天',
		nextText: '下个月',
		prevText: '上个月',
		showMonthAfterYear: true,
		yearRange: '1950:2020',
		maxDate: 0,
		onClose: function(dateText, inst) {
			var parent = $(this).parent().parent();
			if (parent.find('.date2').length != 0) {
				var startTime = parent.find('.date1').val();
				var start = new Date(startTime.replace("-", "/").replace("-", "/"));
				var endTime = parent.find('.date2').val();
				var end = new Date(endTime.replace("-", "/").replace("-", "/"));
				if (start > end) {
					alert('开始日期不得大于结束日期！');
					$(this).val('');
				}
			}
		}
	});

$('#new-search').button().click(function(){
		//将之前查询结果清空
		$('.new-emp tr:not(:first)').remove();
		//从数据库中获取新聘员工信息显示在网页中
		$.ajax({
			type : 'POST', //这里可以换成GET
			url : 'report.json',
			dataType:'json',
			data:{
				start:$('#new-date1').val(),
				end:$('#new-date2').val(),
				dep:$('#new-dep').val(),
			},
			success : function (data) {
				var emp=eval(data);
				alert("emp");
				$('#report-tabs').tabs({active:0});

				for(var i = 0; i < emp.length; i ++){
					var emps=$('<tr><td>'+emp[i]['urla']+'</td><td>'+emp[i]['urlb']+'</td>'
						  +'<td>'+emp[i]['urlc']+'</td><td>'+emp[i]['urld']+'</td><td>'+emp[i]['urle']+'</td>'
					      +'<td>'+emp[i]['urlf']+'</td><td>'+emp[i]['urlg']+'</td></tr>');
					$('.new-emp').append(emps);				
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
			url : 'report.json',
			dataType:'json',
			data:{
				start:$('#leave-date1').val(),
				end:$('#leave-date2').val(),
				dep:$('#leave-dep').val(),
			},
			success : function (data) {
				var emp=eval(data);
				$('#report-tabs').tabs({active:1});

				for(var i = 0; i < emp.length; i ++){
					var emps=$('<tr><td>'+emp[i]['urla']+'</td><td>'+emp[i]['urlb']+'</td>'
						  +'<td>'+emp[i]['urlc']+'</td><td>'+emp[i]['urld']+'</td><td>'+emp[i]['urle']+'</td>'
					      +'<td>'+emp[i]['urlf']+'</td><td>'+emp[i]['urlg']+'</td></tr>');
					$('.leave-emp').append(emps);				
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
		//从数据库中获取岗位调动员工信息显示在网页中
		$.ajax({
			type : 'POST', //这里可以换成GET
			url : 'report.json',
			dataType:'json',
			data:{
				start:$('#dep-date1').val(),
				end:$('#dep-date2').val(),
			},
			success : function (data) {
				var emp=eval(data);
				$('#report-tabs').tabs({active:2});

				for(var i = 0; i < emp.length; i ++){
					var emps=$('<tr><td>'+emp[i]['urla']+'</td><td>'+emp[i]['urlb']+'</td>'
						  +'<td>'+emp[i]['urlc']+'</td><td>'+emp[i]['urld']+'</td><td>'+emp[i]['urle']+'</td>'
					      +'<td>'+emp[i]['urlf']+'</td><td>'+emp[i]['urlg']+'</td></tr>');
					$('.dep-emp').append(emps);				
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
			url : 'report.json',
			dataType:'json',
			data:{
				start:$('#post-date1').val(),
				end:$('#post-date2').val(),
			},
			success : function (data) {
				var emp=eval(data);
				$('#report-tabs').tabs({active:3});

				for(var i = 0; i < emp.length; i ++){
					var emps=$('<tr><td>'+emp[i]['urla']+'</td><td>'+emp[i]['urle']+'</td><td>'+emp[i]['urlb']+'</td>'
						  +'<td>'+emp[i]['urlc']+'</td><td>'+emp[i]['urld']+'</td><td>'+emp[i]['urle']+'</td>'
					      +'<td>'+emp[i]['urlf']+'</td><td>'+emp[i]['urlg']+'</td></tr>');
					$('.post-emp').append(emps);				
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
			url : 'report.json',
			dataType:'json',
			data:{
				year:$('#emp-year').val(),
				month:$('#emp-month').val()
			},
			success : function (data) {
				var emp=eval(data);
				$('#report-tabs').tabs({active:4});

				for(var i = 0; i < emp.length; i ++){
					var emps=$('<tr><td>'+emp[i]['urla']+'</td><td>'+emp[i]['urle']+'</td><td>'+emp[i]['urlb']+'</td>'
						  +'<td>'+emp[i]['urlc']+'</td><td>'+emp[i]['urld']+'</td><td>'+emp[i]['urle']+'</td>'
						  +'<td>'+emp[i]['urlc']+'</td><td>'+emp[i]['urld']+'</td><td>'+emp[i]['urle']+'</td>'
					      +'<td>'+emp[i]['urlf']+'</td><td>'+emp[i]['urlg']+'</td></tr>');
					$('.emp-report').append(emps);				
				}
			},
			error:function (xhr, errorText, errorStatus) {
				alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
			}
		});
	});



});