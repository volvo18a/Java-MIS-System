/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2016-07-05 21:11:11
 * @version $Id$
 */

$(function(){
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

	//设置结束时间为今天(月份+1，因为月份是从0开始算的)
	var today = new Date();
	var month=("0" + (today.getMonth() + 1)).slice(-2);
		var day=("0" + today.getDate()).slice(-2);
		$('.date3').val(today.getFullYear() + '-' + month + '-' + day);
	
	$('.date1,.date2,.date3').datepicker({
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

	$('.date4,.date5,.date6').datepicker({
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
		onClose: function(dateText, inst) {
			var parent = $(this).parent().parent();
			if (parent.find('.date6').length != 0) {
				var startTime = parent.find('.date5').val();
				var start = new Date(startTime.replace("-", "/").replace("-", "/"));
				var endTime = parent.find('.date6').val();
				var end = new Date(endTime.replace("-", "/").replace("-", "/"));
				if (start > end) {
					alert('开始日期不得大于结束日期！');
					$(this).val('');
				}
			}
		}
	});
	
	
	
	
	
	
	
	
	
	
})