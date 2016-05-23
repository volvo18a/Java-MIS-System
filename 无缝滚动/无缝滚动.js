/*
offsetLeft/offsetTop 获取物体左边距和上边距
*/
window.onload = function() {
		var oDiv = document.getElementById('div1');
		var oUl = oDiv.getElementsByTagName('ul')[0];
		var aLi = oUl.getElementsByTagName('li');
		var timer = null;
		var speed = 2;
		
		oUl.innerHTML = oUl.innerHTML+oUl.innerHTML;
		oUl.style.width = aLi[0].offsetWidth*aLi.length+'px';
		function move() {
			if(oUl.offsetLeft < -oUl.offsetWidth/2){
				oUl.style.left = '0';
			}
			
			if(oUl.offsetLeft > 0){
				oUl.style.left = -oUl.offsetWidth/2 + 'px';
			}
			
			
				oUl.style.left = oUl.offsetLeft + speed + 'px';
		}
		timer = setInterval(move, 30);

		oDiv.onmouseover = function(){
			clearInterval(timer);
		}
		oDiv.onmouseout = function(){
			timer = setInterval(move, 30);
		}

		document.getElementsByTagName('a')[0].onclick = function(){
			speed = -2;
		};

		document.getElementsByTagName('a')[1].onclick = function(){
			speed = 2;
		};
}