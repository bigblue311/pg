// ( 1 ) String.contains()
String.prototype.contains = function(text){
		if(text == '') return true;
		else if(text == null) return false;
		else return this.indexOf(text) !== -1;
}

String.prototype.isNumber = function(){
	if(typeof(this)=='undefined'){ 
		return false;
	}
	var r = /^[0-9]*[1-9][0-9]*$/
	return r.test(this);
}

// ( 2 ) String.count()
String.prototype.count = function(text){
		if(this.contains(text))
			return this.split(text).length-1;
		else
			return 0;
}

// ( 3 ) String.capitalize()
String.prototype.capitalize = function(){
	    if(this == '') return this;
	    else return this.charAt(0).toUpperCase() + this.slice(1).toLowerCase();
}

// ( 4 ) String.trim()
String.prototype.trim = function(){
     	return this.replace(/^\s+|\s+$/g, '');
}

// ( 5 ) String.leftTrim()
String.prototype.leftTrim =function(){
	return this.replace(/^\s+/,'');
}

// ( 6 ) String.rightTrim()
String.prototype.rightTrim=function(){
	return this.replace(/\s+$/,'');
}

// ( 7 ) String.clear()
String.prototype.clear = function(){
     	return this.replace(/^\s+|\s+$/g, '').replace(/\s+/g, ' ');
}

// ( 8 ) String.startsWith()
String.prototype.startsWith = function(start) {
	if(start == '') return true;
	else if(start == null || start.length > this.length) return false;
    else return this.substring(0,start.length) == start;
}

// ( 9 ) String.endsWith()
String.prototype.endsWith = function(end) {
	if(end == '') return true;
	else if(end == null || end.length > this.length) return false;
    else return this.indexOf(end, this.length - end.length) !== -1;
}

// ( 10 ) String.insert()
String.prototype.insert = function(text,at) {
	if(at == null || at > this.length)
		at = this.length;
	else if(at < 0)
		at = 0;
	
	return this.substring(0,at)+text+this.substring(at);
}

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

Number.prototype.toDate = function(){
	var day = new Date();
	day.setTime(this);
	return day;
}