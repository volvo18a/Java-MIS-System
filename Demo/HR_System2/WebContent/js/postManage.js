/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2016-07-05 21:10:50
 * @version $Id$
 */

$(function(){

	function showPost(data){
		$('#main-post-table tr:not(:first)').remove();
		var post = eval(data);

			for (var i = 0; i < post.length; i++) {
				var posts = $('<tr><td>' + post[i]['post_id'] + '</td>'
					+ '<td>' + post[i]['post_name'] + '</td>'
					+ '<td>' + post[i]['post_typename'] + '</td>'
					+ '<td>' + post[i]['post_count'] + '</td>'
					+ '<td><a class="post-mod" href="javascript:;">修改</a>'
					+ '/<a class="post-del" href="javascript:;">删除</a></td></tr>');
				$('#main-post-table tbody').append(posts);
			}
		
	}

	/*开始显示岗位信息*/
	$('#show-post').click(function(){
		$('#main-post-table tr:not(:first)').remove();
		$.ajax({
			type:'post',
			url:'PostServlet?message=searchAllPost',
			datatype:'json',
			success:function(data){
				showPost(data);
			},
			error:function(status,statusText){
				alert(status+'---'+statusText);
			}
		});
	});

	/*新建岗位*/
	$('#main-post .btn-info').click(function() {
		
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
			title: '新建岗位',
			width: '700px',
			modal: true, //对话框会遮罩一层灰纱，无法操作。
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
							showPost(data);
							
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
	});	

	/*岗位修改*/
	$(document).on('click','#main-post-table tbody tr td .post-mod',function() {
		var postId = $(this).parent().parent().find('td').eq(0);
		var postName = $(this).parent().parent().find('td').eq(1);
		var postType = $(this).parent().parent().find('td').eq(2);
		var postCount = $(this).parent().parent().find('td').eq(3);
		
		$('.Mod-post-dial #mod-postName').val(postName.text());
		$('.Mod-post-dial #mod-postType').val(postType.text());
		$('.Mod-post-dial #mod-postCount').val(postCount.text());
		$('.Mod-post-dial').dialog({
			title: '岗位修改',
			width: '600px',
			modal: true, //对话框会遮罩一层灰纱，无法操作。
			buttons: {
				'重置':function(){
					$('.Mod-post-dial').find('select,input,textarea').val('');
				},
				'完成': function() {
						$.ajax({
						url: 'PostServlet?message=modPost',
						type: 'POST',
						dataType:'json',
						data: {
							'post_id':postId.text(),
							'post_name':$('#mod-postName').val(),
							'post_type':$('#mod-postType').val(),
							'post_count':$('#mod-postCount').val()
						},
						success: function (data) {
							showPost(data);
							alert('修改成功！');
							$('.Mod-post-dial').dialog('close');
						},
						error:function (xhr, status, statusText) {
							alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
						}
					});
				}
			}
		});
	});

	/*岗位删除*/
	$(document).on('click','#main-post-table tbody tr td .post-del',function() {
		if (confirm("确定删除该条信息吗？")) {
			var postId = $(this).parent().parent().find('td').eq(0);
			$.ajax({
				url: 'PostServlet?message=delPost',
				type: 'POST',
				dataType:'json',
				data: {
					'post_id':postId.text()
				},
				success: function (data) {
					//showPost(data);
					$('#main-post-table tr:not(:first)').remove();
					var post = eval(data);
					if(post.flag == 0){
						alert("该岗位下还有相应员工无法删除");
						for (var j = 0; j < post.darray1.length; j++) {
							var posts = $('<tr><td>' + post.darray1[j]['post_id'] + '</td>'
								+ '<td>' + post.darray1[j]['post_name'] + '</td>'
								+ '<td>' + post.darray1[j]['post_typename'] + '</td>'
								+ '<td>' + post.darray1[j]['post_count'] + '</td>'
								+ '<td><a class="post-mod" href="javascript:;">修改</a>'
								+ '/<a class="post-del" href="javascript:;">删除</a></td></tr>');
							$('#main-post-table tbody').append(posts);
						}
					} else {
						alert('删除成功！');
						for (var i = 0; i < post.darray1.length; i++) {
							var posts = $('<tr><td>' + post.darray1[i]['post_id'] + '</td>'
								+ '<td>' + post.darray1[i]['post_name'] + '</td>'
								+ '<td>' + post.darray1[i]['post_typename'] + '</td>'
								+ '<td>' + post.darray1[i]['post_count'] + '</td>'
								+ '<td><a class="post-mod" href="javascript:;">修改</a>'
								+ '/<a class="post-del" href="javascript:;">删除</a></td></tr>');
							$('#main-post-table tbody').append(posts);
						}
					}
				},
				error:function (xhr, status, statusText) {
					alert('状态'+xhr.status + '错误信息:' + xhr.statusText);
				}
			});
		}
	});

	/*点击查询岗位员工把之前的数据删掉*/
	$('#show-post-emp').click(function(){
		$('#post-emp-table tr:not(:first)').remove();
		
		/*岗位下拉条*/
		$.ajax({
			type:'post',
			url:'DppSelect?method=postidtoname',
			datatype:'json',
			success:function(data){
				var json=eval(data);
				
				for(var i = 0;i < json.length; i ++){
					var posts=$('<option>'+json[i]['post_name']+'</option>');
					$('#post-emp #emp-postname').append(posts);
				}
			},
			error:function(status,statusText){
				alert(status+'---'+statusText);
			}
		});
	});

	/*点击查询之后显示岗位员工*/
	$('#post-seacher-emp-btn').click(function(){
		$('#post-emp-table tr:not(:first)').remove();

		$.ajax({
			type:'post',
			url:'PostServlet?message=searchPost',
			datatype:'json',
			data:{
					"post_id":$('#post-emp #emp-postid').val(),
					"post_name":$('#post-emp #emp-postname').val(),
					"post_type":$('#post-emp #emp-posttype').val()
				},
			success:function(data){

				var emp=eval(data);

				if(emp.length==0 || emp[0]==null){
					alert('您所查找的数据为空！');
				}else{
					for(var i = 0; i < emp.length; i ++){
						var emps=$('<tr><td>'+ emp[i]['emp_id'] +'</td>'
								+ '<td>'+emp[i]['emp_name']+'</td>'
								+ '<td>'+emp[i]['emp_birth']+'</td>'
								+ '<td>'+emp[i]['emp_idcard']+'</td>'
								+ '<td>'+emp[i]['emp_entrydate']+'</td>'
								+ '<td>'+emp[i]['emp_sex']+'</td>'
								+ '<td>'+emp[i]['emp_deptname']+'</td>'
								+ '<td>'+emp[i]['emp_postname']+'</td></tr>');
						$('#post-emp-table').append(emps);
					}
				}
			},
			error:function(status,statusText){
				alert(status+'---'+statusText);
			}
		});

	});


})