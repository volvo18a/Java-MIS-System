<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.5/dist/css/bootstrap.css">
	<link rel="stylesheet" type="text/css"
		href="bootstrap-3.3.5/dist/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css"
			href="bootstrap-3.3.5/dist/css/bootstrap-theme.min.css">
			<link rel="stylesheet" href="smoothness/jquery.ui.css"
				type="text/css" />
			<link rel="stylesheet" type="text/css" href="css/layout.css">
				<script type="text/javascript" src="js/jquery-1.12.1.js"></script>
				<script type="text/javascript" src="js/jquery-ui.js"></script>
				<script type="text/javascript">
					
				</script>
</head>

<body>
	<header class="page_header">
	<div id="logo">
		<img src="images/logo.png">
	</div>
	<div id="sys_head">
		<span>人事管理系统</span>
	</div>
	<div id="welcome">
		<span>欢迎<em>"<%=request.getAttribute("name") %>"</em>登陆</span>
	</div>
	<div id="exit">
		<a href="login.jsp">退出</a>
	</div>
	</header>
	<div id="main">
		<div id="nav">
			<ul class="dorplist-left">
				<li id="dorplist-dept"><a href="javascript:;">部门管理</a>
					<ul class="dorplist-dept-op">
						<li id="show-dept"><a href="javascript:;">显示部门信息</a></li>
						<li id="show-dept-emp"><a href="javascript:;">查询部门下员工</a></li>
					</ul></li>
				<li id="dorplist-post"><a href="javascript:;">岗位管理</a>
					<ul class="dorplist-post-op">
						<li id="show-post"><a href="javascript:;">显示岗位信息</a></li>
						<li id="show-post-emp"><a href="javascript:;">查询岗位下员工</a></li>
					</ul></li>
				<li id="dorplist-entry"><a href="javascript:;">员工管理</a>
					<ul class="dorplist-entry-op">
						<li class="op-li op-li-first"><a href="javascript:;">员工入职管理</a>
						</li>
						<li class="op-li op-li-second"><a href="javascript:;">员工异动管理</a>
							<ul class="thirdNav thirdNav2">
								<li><a href="javascript:;">试用期</a></li>
								<li><a href="javascript:;">部门调动</a></li>
								<li><a href="javascript:;">岗位调动</a></li>
							</ul></li>
						<!-- 
                        <li class="op-li op-li-third"><a href="javascript:;">员工信息管理</a>
                            <ul class="thirdNav thirdNav3">
                                <li><a href="javascript:;">职业</a></li>
                                <li><a href="javascript:;">外语</a></li>
                                <li><a href="javascript:;">家庭成员</a></li>
                            </ul>
                        </li> -->
						<li class="op-li op-li-forth"><a href="javascript:;">员工离职管理</a></li>
					</ul></li>
				<li id="dorplist-report"><a href="javascript:;">报表管理</a></li>
			</ul>
		</div>
		<div id="main-right">
			<div id="main-dept">
				<h2>部门信息</h2>
				<div>
					<input class="btn btn-info" type="button" value="新建部门">
				</div>
				<table id="main-dept-table"
					class="table  table-bordered table-hover">
					<thead>
						<tr>
							<td>部门编号</td>
							<td>部门名称</td>
							<td>部门类型</td>
							<td>部门电话</td>
							<td>部门传真</td>
							<td>上级部门名称</td>
							<td>成立日期</td>
							<td>描述</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div id="new-dept">
				<h2>新建部门</h2>
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="deptName" class="col-sm-2 control-label">部门名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="deptName"
								placeholder="部门名称">
						</div>
					</div>
					<div class="form-group">
						<label for="deptType" class="col-sm-2 control-label">部门类型</label>
						<div class="col-sm-10">
							<select id="deptType" class="form-control">
								<option></option>
								<option value="公司">公司</option>
								<option value="部门">部门</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="deptTel" class="col-sm-2 control-label">部门电话</label>
						<div class="col-sm-10">
							<input type="tel" class="form-control" id="deptTel"
								placeholder="部门电话">
						</div>
					</div>
					<div class="form-group">
						<label for="deptFax" class="col-sm-2 control-label">部门传真</label>
						<div class="col-sm-10">
							<input type="tel" class="form-control" id="deptFax"
								placeholder="部门传真">
						</div>
					</div>
					<div class="form-group">
						<label for="deptSurName" class="col-sm-2 control-label">上级部门名称</label>
						<div class="col-sm-10">
							<select class="form-control" id="deptSurName">
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="deptSetDate" class="col-sm-2 control-label">成立日期</label>
						<div class="col-sm-10">
							<input type="text" class="date1 form-control" id="deptSetDate"
								placeholder="成立日期">
						</div>
					</div>
					<div class="form-group">
						<label for="deptDesc" class="col-sm-2 control-label">描述</label>
						<div class="col-sm-10">
							<textarea id="deptDesc" class="form-control" rows="3"
								placeholder="描述"></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="Mod-dept-dial">
				<form>
					<table class="table table-hover">
						<thead>
							<tr>
								<td>名称</td>
								<td>描述</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>部门名称</td>
								<td><input type="text" id="mod-deptName"
									class="form-control"></td>
							</tr>
							<tr>
								<td>部门类型</td>
								<td><select id="mod-deptType" class="form-control">
										<option></option>
										<option value="公司">公司</option>
										<option value="部门">部门</option>
								</select></td>
							</tr>
							<tr>
								<td>部门电话</td>
								<td><input type="tel" id="mod-deptTel" class="form-control" />
								</td>
							</tr>
							<tr>
								<td>部门传真</td>
								<td><input type="text" id="mod-deptFax"
									class="form-control" /></td>
							</tr>
							<tr>
								<td>上级部门名称</td>
								<td><select id="mod-deptSupName" class="form-control">
										<option></option>
								</select></td>
							</tr>
							<tr>
								<td>成立日期</td>
								<td><input type="text" id="mod-deptSetDate"
									readonly="readonly" class="form-control date1" /></td>
							</tr>
							<tr>
								<td>描述</td>
								<td><textarea class="form-control" id="mod-deptDesc"
										rows="3" placeholder="描述"></textarea></td>
							</tr>
							<tr><td><input type="text" id="aaa" ></td></tr>
						</tbody>
					</table>
				</form>
			</div>
			<div id="dept-emp" class="table-responsive">
				<h2>部门员工</h2>
				<form class="form-inline" role="form">
					<label>选择查询条件</label>
					<div class="form-group">
						<label class="sr-only" for="emp-deptid">部门编号</label> <input
							type="text" class="form-control" id="emp-deptid"
							placeholder="部门编号"> <label class="" for="emp-deptname">部门名称</label>
							<select class="form-control" id="emp-deptname">
								<option value=""></option>
						</select> <label class="" for="emp-dtype">部门类型</label> <select
							class="form-control" id="emp-dtype">
								<option value=""></option>
								<option value="公司">公司</option>
								<option value="部门">部门</option>
						</select>
					</div>
					<input type="button" class="btn btn-info" id="dept-seacher-emp-btn"
						value="搜索">
				</form>
				<table id="dept-emp-table" class="table  table-bordered table-hover">
					<thead>
						<tr>
							<td>员工编号</td>
							<td>员工姓名</td>
							<td>出生日期</td>
							<td>身份证</td>
							<td>入职日期</td>
							<td>性别</td>
							<td>部门名称</td>
							<td>岗位名称</td>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div id="new-post">
				<h2>新建岗位</h2>
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="postName" class="col-sm-2 control-label">岗位名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="postName"
								placeholder="岗位名称">
						</div>
					</div>
					<div class="form-group">
						<label for="postType" class="col-sm-2 control-label">岗位类型</label>
						<div class="col-sm-10">
							<select id="postType" class="form-control">
								<option></option>
								<option value="管理">管理</option>
								<option value="技术">技术</option>
								<option value="营销">营销</option>
								<option value="市场">市场</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="postStaffing" class="col-sm-2 control-label">岗位编制</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="postStaffing"
								placeholder="岗位编制">
						</div>
					</div>
				</form>
			</div>
			<div id="main-post" class="table-responsive">
				<h2>岗位信息</h2>
				<div>
					<input class="btn btn-info" type="button" value="新建岗位">
				</div>
				<table id="main-post-table"
					class="table  table-bordered table-hover">
					<thead>
						<tr>
							<td>岗位编号</td>
							<td>岗位名称</td>
							<td>岗位类型</td>
							<td>岗位编制</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
			<div class="Mod-post-dial">
				<form>
					<table class="table table-hover">
						<thead>
							<tr>
								<td>名称</td>
								<td>描述</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>岗位名称</td>
								<td><input type="text" id="mod-postName"
									class="form-control"></td>
							</tr>
							<tr>
								<td>岗位类型</td>
								<td><select id="mod-postType" class="form-control">
										<option></option>
										<option value="管理">管理</option>
										<option value="市场">市场</option>
										<option value="营销">营销</option>
										<option value="技术">技术</option>
								</select></td>
							</tr>
							<tr>
								<td>岗位编制</td>
								<td><input type="tel" id="mod-postCount"
									class="form-control" /></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div id="post-emp" class="table-responsive">
				<h2>岗位员工</h2>
				<form class="form-inline" role="form">
					<label>选择查询条件</label>
					<div class="form-group">
						<label class="sr-only" for="emp-postid">岗位编号</label> <input
							type="text" class="form-control" id="emp-postid"
							placeholder="岗位编号"> <label class="" for="emp-postname">岗位名称</label>
							<select class="form-control" id="emp-postname">
								<option></option>
						</select> <label class="" for="emp-posttype">岗位类型</label> <select
							class="form-control" id="emp-posttype">
								<option></option>
								<option value="管理">管理</option>
								<option value="市场">市场</option>
								<option value="营销">营销</option>
								<option value="技术">技术</option>
						</select>
					</div>
					<input type="button" class="btn btn-info" id="post-seacher-emp-btn"
						value="搜索">
				</form>
				<table id="post-emp-table" class="table  table-bordered table-hover">
					<thead>
						<tr>
							<td>员工编号</td>
							<td>员工姓名</td>
							<td>出生日期</td>
							<td>身份证</td>
							<td>入职日期</td>
							<td>性别</td>
							<td>部门名称</td>
							<td>岗位名称</td>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div id="report" class="report">
				<h2>报表管理</h2>
				<div id="report-tabs">
					<ul id="report-leader">
						<li id="report-tabs1"><a href="#tabs1">新聘员工报表</a></li>
						<li id="report-tabs2"><a href="#tabs2">离职员工报表</a></li>
						<li><a href="#tabs3">部门调动员工报表</a></li>
						<li><a href="#tabs4">岗位调动员工报表</a></li>
						<li id="report-5"><a href="#tabs5">人事月报</a></li>
					</ul>
					<div id="tabs1">
						<form action="ReportExcel?method=NewEmp" method="post">
							<div id="report-term">
								<span> <label for="date1">开始时间：</label> <input
									type="text" name="newemp_date1" readonly="readonly"
									class="text date1" id="new-date1" />
								</span> <span> <label for="date2">结束时间：</label> <input
									type="text" name="newemp_date2" readonly="readonly"
									class="text date2 date3" id="new-date2" />
								</span> <span> <label for="new-dep">部门：</label> <select
									name="dep" id="new-dep" class="dep dept_name_select">
										<option selected="selected">全部</option>
								</select>
								</span> <input type="button" value="查询" id="new-search" />
								<input
									type="submit" value="导出报表" class="import" id="report-new-excel" />
							</div>
						</form>
						<table class="new-emp emp">
							<tr>
								<td>序号</td>
								<td>部门名称</td>
								<td>岗位名称</td>
								<td>姓名</td>
								<td>性别</td>
								<td>入职日期</td>
								<td>学历</td>
							</tr>
						</table>

					</div>
					<div id="tabs2">
						<form id="formtabs2" name="2"
							action="ReportExcel?method=Discharge" method="post">
							<div id="report-term">
								<span> <label for="date1">开始时间：</label> <input
									type="text" name="disBeginDate" readonly="readonly"
									class="text date1" id="leave-date1" />
								</span> <span> <label for="date2">结束时间：</label> <input
									type="text" name="disEndDate" readonly="readonly"
									class="text date2 date3" id="leave-date2" />
								</span> <span> <label for="leave-dep">部门：</label> <select
									name="deptName" id="leave-dep" class="dep dept_name_select">
										<option selected="selected">全部</option>
								</select>
								</span> <input type="button" value="查询" id="leave-search" /> <input
									type="submit" value="导出报表" class="import" id="report-new-excel" />
							</div>
						</form>
						<table class="leave-emp emp">
							<tr>
								<td>序号</td>
								<td>部门名称</td>
								<td>岗位名称</td>
								<td>姓名</td>
								<td>性别</td>
								<td>离职日期</td>
								<td>离职原因</td>
							</tr>
						</table>

					</div>
					<div id="tabs3">
						<form action="ReportExcel?method=deptTurn" method="post">
							<div id="report-term" class="change-term">
								<span> <label for="date1">开始时间：</label> <input
									type="text" name="dturnBeginDate" readonly="readonly"
									class="text date1" id="dep-date1" />
								</span> <span> <label for="date2">结束时间：</label> <input
									type="text" name="dturnEndDate" readonly="readonly"
									class="text date2 date3" id="dep-date2" />
								</span> <input type="button" value="查询" id="dep-search" /> <input
									type="submit" value="导出报表" class="import" id="report-new-excel" />
							</div>
						</form>
						<table class="dep-emp emp">
							<tr>
								<td>序号</td>
								<td>原部门名称</td>
								<td>新部门名称</td>
								<td>姓名</td>
								<td>性别</td>
								<td>调动日期</td>
								<td>调动原因</td>
							</tr>
						</table>
					</div>
					<div id="tabs4">
						<form action="ReportExcel?method=postTurn" method="post">
							<div id="report-term" class="change-term">
								<span> <label for="date1">开始时间：</label> <input
									type="text" name="pturnBeginDate" readonly="readonly"
									class="text date1" id="post-date1" />
								</span> <span> <label for="date2">结束时间：</label> <input
									type="text" name="pturnEndDate" readonly="readonly"
									class="text date2 date3" id="post-date2" />
								</span> <input type="button" value="查询" id="post-search" /> <input
									type="submit" value="导出报表" class="import" id="report-new-excel" />
							</div>
						</form>
						<table class="post-emp emp">
							<tr>
								<td>序号</td>
								<td>部门名称</td>
								<td>原岗位名称</td>
								<td>新岗位名称</td>
								<td>姓名</td>
								<td>性别</td>
								<td>调动日期</td>
								<td>调动原因</td>
							</tr>
						</table>
					</div>
					<div id="tabs5">
						<form action="ReportExcel?method=empReport" method="post">
							<div id="report-term" class="change-term">
								<span> <label for="emp-year">年份：</label> <select
									name="empBeginDate" id="emp-year"></select>
								</span> <span> <label for="emp-month">月份：</label> <select
									name="empEndDate" id="emp-month"></select>
								</span> <input type="button" value="查询" id="emp-search" /> <input
									type="submit" value="导出报表" class="import" id="report-new-excel" />
							</div>
						</form>
						<table class="emp-report emp">
							<tr>
								<td rowspan="2">部门名称</td>
								<td colspan="6">人数统计</td>
								<td colspan="4">学历统计</td>
							</tr>
							<tr class="second">
								<td>月初人数</td>
								<td>月末人数</td>
								<td>本月新入职</td>
								<td>本月离职</td>
								<td>本月调入</td>
								<td>本月调出</td>
								<td>研究生</td>
								<td>本科</td>
								<td>大专</td>
								<td>高中及高中以下</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="ruzhi-xinxi-jibenxinxi-select">
				<h3 class="ruzhi-h3">员工基本信息</h3>
				<form class="form-horizontal" role="form">

					<div class="form-group">
						<label for="Name" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control basic-msg-emp-name"
								id="Text1" placeholder="姓名">
						</div>
					</div>
					<div class="form-group">
						<label for="Sex" class="col-sm-2 control-label">性别</label>

						<div class="col-sm-10">
							<select class="form-control basic-msg-emp-sex">
								<option name="sex" value="男">男</option>
								<option name="sex" value="女">女</option>
							</select>
						</div>

					</div>
					<div class="form-group">
						<label for="Date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-10">
							<input type="text" class="date1 form-control basic-msg-emp-birth"
								id="" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="ID" class="col-sm-2 control-label">身份证号</label>
						<div class="col-sm-10">
							<input type="text" class="form-control basic-msg-emp-IDcard"
								id="" placeholder="身份证号">
						</div>
					</div>
					<div class="form-group">
						<label for="dep" class="col-sm-2 control-label">部门<input
							type="button" value="新建" class="build-dept" /></label>
						<div class="col-sm-10">

							<select class="form-control dept_name_select basic-msg-emp-dept">
								<option></option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="GW" class="col-sm-2 control-label">岗位<input
							type="button" value="新建" class="build-post" /></label>
						<div class="col-sm-10">

							<select class="form-control post_name_select basic-msg-emp-post">
								<option></option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="ruzhidate" class="col-sm-2 control-label">入职日期</label>
						<div class="col-sm-10">
							<input type="text"
								class="date1 form-control basic-msg-emp-entry-date" id="Text3"
								readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="joindate" class="col-sm-2 control-label">参加工作日期</label>
						<div class="col-sm-10">
							<input type="text"
								class="date1 form-control basic-msg-emp-work-date" id="Text5"
								readonly="readonly">
						</div>
					</div>
					<div class="form-group">
						<label for="xingshi" class="col-sm-2 control-label">用工形式</label>
						<div class="col-sm-10">
							<select class="form-control basic-msg-emp-work-type">
								<option name="type" value="正式">正式</option>
								<option name="type" value="临时">临时</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">学历</label>
						<div class="col-sm-10">
							<select class="form-control basic-msg-emp-dgree">
								<option name="type" value=""></option>
								<option name="type" value="高中及以下">高中及以下</option>
								<option name="type" value="大专">大专</option>
								<option name="type" value="本科">本科</option>
								<option name="type" value="研究生">研究生</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="laiyuan" class="col-sm-2 control-label">人员来源</label>
						<div class="col-sm-10">
							<select class="form-control basic-msg-emp-source">
								<option name="source" value="校园招聘">校园招聘</option>
								<option name="source" value="社会招聘">社会招聘</option>
								<option name="source" value="其他">其他</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="shifou" class="col-sm-2 control-label">是否有试用期</label>
						<div class="col-sm-8 if-probation-box">
							是 <input type="radio" value="1" name="probation"
								class="basic-msg-emp-if-haspropation basic-message-probation" />
							否 <input type="radio" value="0" name="probation"
								class="basic-msg-emp-if-haspropation basic-message-probation" />
						</div>
					</div>
					<div class="form-group probation-box">

						<label for="shifou" class="col-sm-3 control-label">试用期开始时间</label>
						<div class="col-sm-3">
							<input type="text" name="" readonly="readonly"
								class="basic-msg-emp-propation-start date4 form-control" id="" />
						</div>
						<label for="shifou" class="col-sm-3 control-label">试用期截止时间</label>
						<div class="col-sm-3">
							<input type="text" name="" readonly="readonly"
								class="basic-msg-emp-propation-end text date5 form-control"
								id="" />
						</div>
					</div>
					<div class="emp-message-btn">
						<input type="button" class="btn btn-primary ruzhi-msg-submit-btn"
							value="提交" /> <input type="reset" class="btn btn-primary"
							value="重置" />
					</div>
			</div>
			<div class="ruzhi-xinxi-qitaxinxi-select">
				<form>
					<h3 class="ruzhi-h3">员工其他信息</h3>
					<h3>职业生涯信息</h3>
					<table class="table table-hover">
						<thead>
						</thead>
						<tbody>
							<tr>
								<td>起始年月</td>
								<td><input type="text" name="date1" readonly="readonly"
									class="text date1" /></td>
								<td>截至年月</td>
								<td><input type="text" name="date2" readonly="readonly"
									class="text date2" /></td>
							</tr>
							<!--     <tr>
                            
                        </tr> -->
							<!-- <tr>
                                                              <td>离职去向</td>
                                                              <td><input type="text" value="日期" /></td>
                                                            </tr>
                                                            <tr>
                                                              <td>是否进入人才库</td>
                                                              <td><input type="radio" name="ren-cai-ku" value="是" /></td>
                                                              <td><input type="radio" name="ren-cai-ku" value="否" /></td>
                                                            </tr> -->
						</tbody>
					</table>
					<h3>外语能力</h3>
					<table class="table table-hover">
						<thead>
						</thead>
						<tbody>
							<tr>
								<td>外语语种</td>
								<td><select>
										<option name="make-sure" value="">英</option>
										<option name="make-sure" value="">法</option>
										<option name="make-sure" value="">日</option>
										<option name="make-sure" value="">德</option>
								</select></td>
								<td>外国语中熟练程度</td>
								<td><select>
										<option name="make-sure" value="">0</option>
										<option name="make-sure" value="">1</option>
										<option name="make-sure" value="">2</option>
										<option name="make-sure" value="">3</option>
								</select></td>
							</tr>
							<!--    <tr>
                             <td>外国语中熟练程度</td>
                             <td>
                                 <select>
                                     <option name="make-sure" value="">0</option>
                                     <option name="make-sure" value="">1</option>
                                     <option name="make-sure" value="">2</option>
                                     <option name="make-sure" value="">3</option>
                                 </select>
                             </td>
                         </tr> -->
						</tbody>
					</table>
					<h3>家庭成员与社会关系信息</h3>
					<table class="table table-hover">
						<thead>
						</thead>
						<tbody>
							<tr>
								<td>与本人关系</td>
								<td><input type="text" /></td>
								<td>姓名</td>
								<td><input type="text" /></td>
							</tr>
						</tbody>
					</table>
					<input type="submit" value="提交" /> <input type="reset" value="重置" />
				</form>
			</div>
			<div class="ruzhi-yidong-shiyongqi-select">
				<h3 class="ruzhi-h3">试用期员工管理</h3>
				<div id="shiyongqi-tabs">
					<ul>
						<li><a href="#shiyongqi-tabs1">查询试用期员工</a></li>
						<li><a href="#shiyongqi-tabs2">查询已转正员工信息</a></li>
					</ul>
					<div id="shiyongqi-tabs1">

						<form class="form-horizontal" id="shiyongqi-tabs1-form" role="">

							<div class="form-group">
								<label for="yuangongbianhao" class="col-sm-3 control-label">员工编号</label>
								<div class="col-sm-3">
									<input type="text"
										class="form-control probation-emp-tabs1-emp-id" id=""
										placeholder="员工编号" />
								</div>
								<label for="yuangongxingming" class="col-sm-3 control-label">员工姓名</label>
								<div class="col-sm-3">
									<input type="text"
										class="form-control probation-emp-tabs1-emp-name" id=""
										placeholder="员工姓名" />
								</div>

								
							</div>
							<div class="form-group">
								<label for="kaishidate" class="col-sm-3 control-label">试用期开始</label>
								<div class="col-sm-3">
									<input type="text"
										class="date4 form-control probation-emp-tabs1-start-date"
										id="" readonly="readonly" />
								</div>
								<label for="jieshudate" class="col-sm-3 control-label">试用期结束</label>
								<div class="col-sm-3">
									<input type="text"
										class="date5 form-control probation-emp-tabs1-end-date" id=""
										readonly="readonly" />
								</div>
								

							</div>
							
								<div class="form-group">
									<label for="bumenmingcheng" class="col-sm-3 control-label">部门名称</label>
								<div class="col-sm-3">

									<select
										class="form-control dept_name_select probation-emp-tabs1-dept-name"
										id="shiyongqi-tab1-dept-select">
										<option></option>
									</select>

								</div>
								<label for="bumenbianhao" class="col-sm-3 control-label">岗位名称</label>
								<div class="col-sm-3">

									<select
										class="form-control post_name_select probation-emp-tabs1-post-name"
										id="shiyongqi-tab1-post-select">
										<option></option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">试用期状态</label>
								<div class="col-sm-3">
									<select class="form-control probation-emp-tabs1-status">
										<option></option>
										<option>正常</option>
										<option>延期</option>
										
									</select>
								</div>
								<div class="col-sm-3"></div>

								<div class="col-sm-3">
									<input type="button"
										class="btn btn-primary probation-emp-search-btn" value="查询" />
									<input class="btn btn-primary" type="reset" value="重置" name="" />
								</div>
							</div>



						</form>


						<table class="table table-hover probation-emp-search-list">

							<tr>
								<td>员工编号</td>
								<td>员工姓名</td>
								<td>部门名称</td>
								<td>岗位名称</td>
								<td>试用期状态</td>
								<td>试用期开始时间</td>
								<td>试用期结束时间</td>
								<td>操作</td>
							</tr>

						</table>

					</div>
					<div id="shiyongqi-tabs2">
						<!-- 已转正员工信息查询：查询条件：开始日期，结束日期，部门编号，部门名称，员工编号，员工姓名 -->
						<form class="form-horizontal" role="">

							<div class="form-group">
								<label for="bumenbianhao" class="col-sm-2 control-label">岗位名称</label>
								<div class="col-sm-4">

									<select
										class="form-control post_name_select probation-emp-tabs2-post-name"
										id="">
										<option></option>
									</select>
								</div>
								<label for="bumenmingcheng" class="col-sm-2 control-label">部门名称</label>
								<div class="col-sm-4">

									<select
										class="form-control dept_name_select probation-emp-tabs2-dept-name"
										id="">
										<option></option>
									</select>

								</div>
							</div>
							<div class="form-group">
								<label for="yuangongbianhao" class="col-sm-2 control-label">员工编号</label>
								<div class="col-sm-4">
									<input type="text"
										class="form-control probation-emp-tabs2-emp-id" id=""
										placeholder="员工编号">
								</div>
								<label for="yuangongxingming" class="col-sm-2 control-label">员工姓名</label>
								<div class="col-sm-4">
									<input type="text"
										class="form-control probation-emp-tabs2-emp-name" id=""
										placeholder="员工姓名">
								</div>

							</div>
							<div class="form-group">
								<label for="kaishidate" class="col-sm-2 control-label">开始日期</label>
								<div class="col-sm-4">
									<input type="text"
										class="date1 form-control probation-emp-tabs2-start-date"
										id="" readonly="readonly">
								</div>
								<label for="jieshudate" class="col-sm-2 control-label">结束日期</label>
								<div class="col-sm-4">
									<input type="text"
										class="date1 form-control probation-emp-tabs2-end-date" id=""
										readonly="readonly">
								</div>
							</div>
							<div class="form-group">

								<div class="col-sm-8"></div>

								<div class="col-sm-4">
									<input type="button"
										class="btn btn-primary probation-official-emp-search-btn"
										value="查询" /> <input class="btn btn-primary" type="reset"
										value="重置" name="" />
								</div>
							</div>


						</form>
						<table
							class="table table-hover probation-official-emp-search-list">
							<tr>
								<td>员工编号</td>
								<td>员工姓名</td>
								<td>岗位名称</td>
								<td>部门名称</td>
								<td>评语</td>
								<td>处理日期</td>

							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="ruzhi-yidong-shiyongqi">

				<div class="wwwwwww"></div>
				<table class="table table-hover">
					<thead>
						<tr>
							<td>名称</td>
							<td>描述</td>
						</tr>
					</thead>
					<!--  <tbody>
                        	<tr>
                        		<td>员工编号</td>
                        		<td><input name="emp_id" id="id111" readonly="readonly"/></td>
                        	</tr>
                            <tr>
                                <td>试用期部门考核评语</td>
                                <td>
                                    <textarea cols="20" cols="30" name="comment"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td>考核结果</td>
                                <td>
                                    <select class="shiyongqi-status" name="statename">
                                        <option name="make-sure" value="转正">转正</option>
                                        <option name="make-sure" value="延期">延期</option>
                                        <option name="make-sure" value="不予录用">不予录用</option>
                                    </select>
                                </td>
                            </tr>
                            <tr class="delay-time-box">
                                <td>延期至</td>
                                <td>
                                    <input type="text" name="dealydate" readonly="readonly" class="text date1 probation-edit-emp-yanqi" />
                                </td>
                            </tr>
                            <tr>
                                <td>处理日期</td>
                                <td>
                                    <input type="text" name="dealdate" readonly="readonly" class="text date1" id="new-date002" />
                                </td>
                            </tr>
                        </tbody> -->
					<!-- ********测试用的代码测试用的代码测试用的代码测试用的代码************修改的地方************************************** -->
					<tbody>
						<tr>
							<td>id</td>
							<td><input type="text" class="probation-edit-emp-id"
								readonly="readonly" /></td>
						</tr>
						<tr>
							<td>试用期部门考核评语</td>
							<td><textarea cols="20" cols="30"
									class="probation-edit-emp-pingyu"></textarea></td>
						</tr>
						<tr>
							<td>考核结果</td>
							<td>
								<!-- <select class="shiyongqi-status probation-edit-emp-result"> -->
								<select class="probation-edit-emp-result">
									<option name="make-sure" value="转正">转正</option>
									<option name="make-sure" value="延期">延期</option>
									<option name="make-sure" value="不予录用">不予录用</option>
							</select>
							</td>
						</tr>
						<tr class="delay-time-box">
							<td>延期至</td>
							<td><input type="text"
								name="" readonly="readonly" class="text date4 probation-edit-emp-yanqi" /></td>
						</tr>
						<tr>

							<td>处理日期</td>
							<td><input type="text" name="" readonly="readonly"
								class="text date3 probation-edit-emp-date" id="" /></td>
						</tr>
					</tbody>
					<!-- ********测试用的代码测试用的代码测试用的代码测试用的代码************修改的地方************************************** -->

				</table>

			</div>
			<div class="ruzhi-yidong-bumen-select">
				<!-- 1、员工列表查询：查询条件为部门编号，部门名称，员工编号，员工姓名。2、已调动部门员工信息查询：查询条件为指定开始日期，结束日期，员工编号，员工姓名，调动方式。 -->
				<h3 class="ruzhi-h3">部门调动员工管理</h3>
				<div id="dept-change-tabs">
					<ul>
						<li><a href="#dept-change-tabs1">查询调动部门员工</a></li>
						<li><a href="#dept-change-tabs2">查询已调动部门员工信息</a></li>
					</ul>
					<div id="dept-change-tabs1">

						<form class="form-horizontal">

							<div class="form-group">
								<label for="yuangongbianhao" class="col-sm-2 control-label">员工编号</label>
								<div class="col-sm-4">
									<input type="text"
										class="form-control dept-change-tabs1-emp-id" id=""
										placeholder="员工编号">
								</div>
								<label for="yuangongxingming" class="col-sm-2 control-label">员工姓名</label>
								<div class="col-sm-4">
									<input type="text"
										class="form-control dept-change-tabs1-emp-name" id=""
										placeholder="员工姓名">
								</div>
							</div>
							<div class="form-group">
								<label for="bumenbianhao" class="col-sm-2 control-label">部门编号</label>
								<div class="col-sm-4">
									<input type="text" name="" value=""
										class="form-control dept-change-tabs1-dept-id" />

								</div>
								<label for="bumenmingcheng" class="col-sm-2 control-label">部门名称</label>
								<div class="col-sm-4">

									<select
										class="form-control dept_name_select dept-change-tabs1-dept-name">
										<option></option>

									</select>

								</div>
							</div>

							<div class="form-group">

								<div class="col-sm-8"></div>

								<div class="col-sm-4">

									<input class="btn btn-primary dept-change-emp-search-btn"
										type="button" value="查询" name="" /> <input
										class="btn btn-primary" type="reset" value="重置" name="" />
								</div>
							</div>


						</form>
						<table class="table table-hover dept-change-emp-search-list">
							<thead>
								<tr>
									<td>员工编号</td>
									<td>员工姓名</td>
									<td>部门名称</td>
									<td>出生日期</td>
									<td>入职日期</td>
									<td>操作</td>
								</tr>
							</thead>

						</table>
					</div>
					<div id="dept-change-tabs2">
						<form class="form-horizontal">
							<!--  部门已调转员工查询-->

							<div class="form-group">
								<label for="yuangongbianhao" class="col-sm-3 control-label">员工编号</label>
								<div class="col-sm-3">
									<input type="text"
										class="form-control dept-change-tabs2-emp-id" id=""
										placeholder="员工编号" />
								</div>
								<label for="yuangongxingming" class="col-sm-3 control-label">员工姓名</label>
								<div class="col-sm-3">
									<input type="text"
										class="form-control dept-change-tabs2-emp-name" id=""
										placeholder="员工姓名" />
								</div>
								

							</div>
							<div class="form-group">

								<label for="kaishidate" class="col-sm-3 control-label">开始日期</label>
								<div class="col-sm-3">
									<input type="text"
										class="date1 form-control dept-change-tabs2-start-time" id=""
										readonly="readonly">
								</div>
								<label for="jieshudate" class="col-sm-2 control-label">结束日期</label>
								<div class="col-sm-3">
									<input type="text"
										class="date2 form-control dept-change-tabs2-end-time" id=""
										readonly="readonly" />
								</div>

								
							</div>
							<div class="form-group">

								<label for="yuangongxingming"
									class="col-sm-3 control-label">调动方式</label>
								<div class="col-sm-3">

									<select class="dept-change-tabs2-dept-change-style form-control">
										<option></option>
										<option value="主动调动">主动调动</option>
										<option value="被动调动">被动调动</option>
										<option value="数据错误">数据错误</option>
									</select>
								</div>
								<div class="col-sm-3"></div>
								<div class="col-sm-3">
									<input type="button"
										class="btn btn-primary dept-haschanged-emp-search-btn"
										value="查询" /> 
									<input class="btn btn-primary" type="reset"
										value="重置" name="" />
								</div>
							</div>

						</form>
						<table class="table table-hover dept-haschanged-emp-search-list">
							<tr>
								<td>员工编号</td>
								<td>员工姓名</td>
								<td>调转前部门名</td>
								<td>调转后部门名</td>
								<td>调动方式</td>
								<td>处理日期</td>
								<td>调转原因</td>

							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="ruzhi-yidong-bumen">
				<form>

					<table class="table table-hover">
						<thead>
							<tr>
								<td>名称</td>
								<td>描述</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>员工编号</td>
								<td><input class="dept-edit-emp-id" /></td>
							</tr>
							<tr>
								<td>调转后的部门</td>
								<td>
									<!-- <textarea cols="20" cols="30" class="dept-edit-emp-changed-dept"></textarea> -->
									<select class="dept_name_select dept-edit-emp-changed-dept">
										<option name="" value=""></option>

								</select>
								</td>
							</tr>
							<tr>
								<td>调转类型</td>
								<td><select class="dept-edit-emp-change-type">
										<option name="make-sure" value="主动调动">主动调动</option>
										<option name="make-sure" value="被动调动">被动调动</option>
										<option name="make-sure" value="数据错误">数据错误</option>
								</select></td>
							</tr>
							<tr>
								<td>调转原因</td>
								<td><input type="text" value=""
									class="dept-edit-emp-change-reason" /></td>
							</tr>
							<tr>
								<td>调动日期</td>
								<td><input type="text" value=""
									class="date3 dept-edit-emp-change-date" /></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div class="ruzhi-yidong-gangwei-select">
				<!-- 1、查询员工列表：查询条件：部门编号，部门名称，员工名称。2、已调动岗位员工信息查询：查询条件为开始日期，结束日期，，员工编号，员工姓名，调动方式。 -->
				<h3 class="ruzhi-h3">岗位调动员工管理</h3>
				<div id="post-change-tabs">
					<ul>
						<li><a href="#post-change-tabs1">查询岗位调动员工</a></li>
						<li><a href="#post-change-tabs2">查询已调动岗位员工信息</a></li>
					</ul>
					<div id="post-change-tabs1">
						<form class="form-horizontal">



							<div class="form-group">
								<label for="yuangongxingming" class="col-sm-3 control-label">员工编号</label>
								<div class="col-sm-3">
									<input type="text"
										class="form-control post-change-tabs1-emp-id" id=""
										placeholder="员工姓名">
								</div>
								<label for="yuangongxingming" class="col-sm-3 control-label">员工姓名</label>
								<div class="col-sm-3">
									<input type="text"
										class="form-control post-change-tabs1-emp-name" id=""
										placeholder="员工姓名">
								</div>
							</div>
							<div class="form-group">
								<label for="bumenbianhao" class="col-sm-3 control-label">岗位编号</label>
								<div class="col-sm-3">
									<input type="text" name="" value=""
										class="form-control post-change-tabs1-post-id" />
								</div>

								<label for="bumenmingcheng" class="col-sm-3 control-label">岗位名称</label>
								<div class="col-sm-3">

									<select
										class="form-control post_name_select post-change-tabs1-post-name">
										<option></option>

									</select>

								</div>
							</div>
							<div class="form-group">


								<div class="col-sm-8"></div>
								<div class="col-sm-4">
									<input class="btn btn-primary post-change-emp-search-btn"
										type="button" value="查询" name="" /> <input
										class="btn btn-primary" type="reset" value="重置" name="" />
								</div>
							</div>


						</form>


						<table class="table table-hover post-change-emp-search-list">
							<thead>
								<tr>
									<td>员工编号</td>
									<td>员工姓名</td>
									<td>岗位名称</td>
									<td>出生日期</td>
									<td>入职日期</td>
									<td>操作</td>
								</tr>
							</thead>
						
						</table>
					</div>
					<div id="post-change-tabs2">
						<form class="form-horizontal">


							<div class="form-group">
								<label for="yuangongbianhao" class="col-sm-3 control-label">员工编号</label>
								<div class="col-sm-3">
									<input type="text"
										class="form-control post-change-tabs2-emp-id" id=""
										placeholder="员工编号" />
								</div>
								<label for="yuangongxingming" class="col-sm-3 control-label">员工姓名</label>
								<div class="col-sm-3">
									<input type="text"
										class="form-control post-change-tabs2-emp-name" id=""
										placeholder="员工姓名" />
								</div>
							
							</div>
							<div class="form-group">
								<label for="kaishidate" class="col-sm-3 control-label">开始日期</label>
								<div class="col-sm-3">
									<input type="text"
										class="date1 form-control date1 post-change-tabs2-start-time"
										id="" readonly="readonly" />
								</div>
								<label for="jieshudate" class="col-sm-3 control-label">结束日期</label>
								<div class="col-sm-3">
									<input type="text"
										class="date1 form-control date2 post-change-tabs2-end-time"
										id="" readonly="readonly">
								</div>


								
							</div>
							<div class="form-group">
								
								<label for="diaodongfangshi" class="col-sm-3 control-label">调动方式</label>
								<div class="col-sm-3">
								<select class="form-control post-change-tabs2-post-change-style">
									<option></option>
									<option value="升职">升职</option>
									<option value="降职">降职</option>
									<option value="数据录入错误">数据录入错误</option>
									
								</select>
									
								</div>
								<div class="col-sm-3"></div>
								<div class="col-sm-3">
									<input type="button"
										class="btn btn-primary post-haschanged-emp-search-btn"
										value="查询" /> <input class="btn btn-primary" type="reset"
										value="重置" name="" />

								</div>
							</div>
						</form>
						<table class="table table-hover post-haschanged-emp-search-list">
							<tr>
								<td>员工编号</td>
								<td>员工姓名</td>
								<td>调转前岗位名</td>
								<td>调转后岗位名</td>
								<td>调动方式</td>
								<td>处理日期</td>
								<td>调转原因</td>

							</tr>
	
						</table>
					</div>
				</div>
			</div>
			<div class="ruzhi-yidong-gangwei">

				<form>

					<table class="table table-hover">

						<thead>
							<tr>
								<td>名称</td>
								<td>描述</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>员工编号</td>
								<td><input class="post-edit-emp-id" value="员工编号" /></td>
							</tr>
							<tr>
								<td>调转后的岗位</td>
								<td><select class="post_name_select post-edit-changed-post">
										<option></option>
								</select></td>
							</tr>
							<tr>
								<td>调转类型</td>
								<td><select class="post-edit-change-type">
										<option name="make-sure" value="升职">升职</option>
										<option name="make-sure" value="降职">降职</option>
										<option name="make-sure" value="数据录入错误">数据录入错误</option>
								</select></td>
							</tr>
							<tr>
								<td>调转原因</td>
								<td><input class="post-edit-remark" type="text" value="" />
								</td>
							</tr>
							<tr>
								<td>调动日期</td>
								<td><input readonly="readonly"
									class="date3 post-edit-change-date" type="text" value="" /></td>
							</tr>
						</tbody>
					</table>
				</form>
				<!--   <label>已调转岗位人员查询</label>
                <select>
                    <option>开始日期</option>
                    <option>结束日期</option>
                    <option>员工编号</option>
                    <option>员工姓名</option>
                    <option>调动方式</option>
                </select> -->
			</div>
			<div class="ruzhi-lizhi-select">
				<!-- 1、员工列表查询：查询条件为部门编号，部门名称，员工编号，员工姓名2、已离职员工信息查询：查询条件包括：员工编号，姓名，部门名称，岗位名称，离职类型，查询开始日期，结束日期 -->
				<h3 class="ruzhi-h3">离职员工管理</h3>
				<div id="lizhi-manage-tabs">
					<ul>
						<li><a href="#lizhi-manage-tabs1">离职员工管理</a></li>
						<li><a href="#lizhi-manage-tabs2">查询已离职员工信息</a></li>
					</ul>
					<div id="lizhi-manage-tabs1">
						<form class="form-horizontal">


							<div class="form-group">
								<label for="yuangongbianhao" class="col-sm-2 control-label">员工编号</label>
								<div class="col-sm-4">
									<input type="text"
										class="form-control leave-manage-tabs1-emp-id" id=""
										placeholder="员工编号" />
								</div>
								<label for="yuangongxingming" class="col-sm-2 control-label">员工姓名</label>
								<div class="col-sm-4">
									<input type="text"
										class="form-control leave-manage-tabs1-emp-name" id=""
										placeholder="员工姓名" />
								</div>
							</div>
							<div class="form-group">
								<label for="bumenbianhao" class="col-sm-2 control-label">部门名称</label>
								<div class="col-sm-4">

									<select class="form-control dept_name_select leave-manage-tabs1-dept-name">
										<option></option>

									</select>

								</div>
								<label for="bumenmingcheng" class="col-sm-2 control-label">岗位名称</label>
								<div class="col-sm-4">

									<select
										class="form-control post_name_select leave-manage-tabs1-post-name">
										<option></option>

									</select>
								</div>
							</div>


							<div class="form-group">

								<div class="col-sm-8"></div>

								<div class="col-sm-4">
									<input class="btn btn-primary dimmision-emp-search-btn"
										type="button" value="查询" name="" /> <input
										class="btn btn-primary" type="reset" value="重置" name="" />


								</div>

							</div>

						</form>
						<table class="table table-hover dimmision-emp-search-list">
							<thead>
								<tr>
									<td>员工编号</td>
									<td>员工姓名</td>
									<td>部门名称</td>
									<td>岗位名称</td>
									<td>入职时间</td>
									<td>身份证号</td>
									<td>操作</td>
								</tr>
							</thead>
						
						</table>
					</div>
					<div id="lizhi-manage-tabs2">
						<!--    已离职员工信息查询：查询条件包括：员工编号，姓名，部门名称，岗位名称，离职类型，查询开始日期，结束日期 -->
						<form class="form-horizontal">



							<div class="form-group">
								<label for="yuangongbianhao" class="col-sm-3 control-label">员工编号</label>
								<div class="col-sm-3">
									<input type="text"
										class="form-control leave-manage-tabs2-emp-id" id=""
										placeholder="员工编号" />
								</div>
								<label for="yuangongxingming" class="col-sm-3 control-label">员工姓名</label>
								<div class="col-sm-3">
									<input type="text"
										class="form-control leave-manage-tabs2-emp-name" id=""
										placeholder="员工姓名" />
								</div>

							</div>
							<div class="form-group">
								
								<label for="" class="col-sm-3 control-label">开始日期</label>
								<div class="col-sm-3">
									<input type="text"
										class="date1 form-control leave-manage-tabs2-start-time" id=""
										readonly="readonly" />
								</div>
								<label for="" class="col-sm-3 control-label">结束日期</label>
								<div class="col-sm-3">
									<input type="text"
										class="date2 form-control leave-manage-tabs2-end-time" id=""
										readonly="readonly" />
								</div>


							</div>
							<div class="form-group">
								
								<label for="" class="col-sm-3 control-label">部门名称</label>
								<div class="col-sm-3">
									<select
										class="form-control dept_name_select leave-manage-tabs2-dept-name"
										id="">
										<option></option>
									</select>
								</div>
								<label for="" class="col-sm-3 control-label">岗位名称</label>

								<div class="col-sm-3">
								<select class="post_name_select leave-manage-tabs2-post-name">
									<option></option>
									
								</select>
									
								</div>
							</div>
							<div class="form-group">

								<label for="lizhileixing" class="col-sm-3 control-label">离职类型</label>
								<div class="col-sm-3">
									<select class="leave-manage-tabs2-leave-style form-control">
										<option></option>
										<option>辞退</option>
										<option>退休</option>
										<option>开除</option>
										<option>主动辞职</option>
										<option>试用期不通过</option>
									</select>
								</div>
								<div class="col-sm-3"></div>
								<div class="col-sm-3">
									<input type="button"
										class="btn btn-primary hasdimmision-emp-search-btn" value="查询" />
									<input class="btn btn-primary" type="reset" value="重置" name="" />
								</div>
							</div>




						</form>
						<table class="table table-hover hasdimmision-emp-search-list">
							<tr>
								<td>员工编号</td>
								<td>员工姓名</td>
								<td>岗位名称</td>
								<td>部门名称</td>
								<td>离职类型</td>
								<td>离职时间</td>

							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="emp-manage-dimmision">
				<form>
					<table class="table table-hover">
						<thead>
							<tr>
								<td>名称</td>
								<td>描述</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>员工编号</td>
								<td><input class="leave-edit-emp-id" value="" /></td>
							</tr>
							<tr>
								<td>离职日期</td>
								<td><input class="leave-edit-leave-date date3" value="date" />
								</td>
							</tr>
							<tr>
								<td>离职类型</td>
								<td><select class="leave-edit-leave-type">
										<option name="make-sure" value="主动辞职">主动辞职</option>
										<option name="make-sure" value="辞退">辞退</option>
										<option name="make-sure" value="退休">退休</option>
										<option name="make-sure" value="开除">开除</option>
										<option name="make-sure" value="试用期未通过">试用期未通过</option>
								</select></td>
							</tr>
							<tr>
								<td>离职去向</td>
								<td><input class="leave-edit-leave-quxiang" type="text"
									value="" /></td>
							</tr>
							<tr>
								<td>是否进入人才库</td>
								<td>是<input class="leave-edit-ifaddto-base" type="radio"
									name="ren-cai-ku" value="1" />
								</td>
								<td>否 <input class="leave-edit-ifaddto-base" type="radio"
									name="ren-cai-ku" value="0" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="js/deptManage.js"></script>
	<script type="text/javascript" src="js/postManage.js"></script>
	<script type="text/javascript" src="js/empManage.js"></script>
	<script type="text/javascript" src="js/report.js"></script>
	<script type="text/javascript" src="js/general.js"></script>

</body>

</html>