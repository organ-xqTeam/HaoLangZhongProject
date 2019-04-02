 let Global=(function(){
  //var api="http://localhost:8980/js/f/sys/"
  //var projectApi="http://localhost:8980/js/"      
  var api="http://120.92.10.2:81/hlz/f/sys/"
  var projectApi="http://120.92.10.2:81/hlz/"
	var picUrl="http://120.92.10.2/hlz/f/sys/fileInfo/showPic/"   
  //得到get 参数	   
   var getParameter=function(name){
	   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
	   
       var r = window.location.search.substr(1).match(reg); 
       if (r != null) return unescape(r[2]); 
       return null; 
   }
   //得到cokkie
   var getCookie=function(name){
	       //可以搜索RegExp和match进行学习
	       var arr,reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	       if (arr = document.cookie.match(reg)) {
	           return unescape(arr[2]);
	       } else {
	           return null;
	       }
	   }

  //格式化时间
  var formatTimestamp=  function  (shijianchuo){
	  var add0=function(m){return m<10?'0'+m:m }
	//shijianchuo是整数，否则要parseInt转换
	  var time = new Date(parseInt(shijianchuo));
	  var y = time.getFullYear();
	  var m = time.getMonth()+1;
	  var d = time.getDate();
	  var h = time.getHours();
	  var mm = time.getMinutes();
	  var s = time.getSeconds();
	  return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
  }

   var ajaxRequest=function(url,postdata,method="post",contentType="application/json"){
	   return new Promise(function (resolve, reject) {
		   $.ajax({
		   	   type: method,
		       url:url, 
		       contentType: contentType,
		       data:postdata, 
		       success: function (res) {
		    	   resolve(res);
		           	
		        },
		        error: function (e) {
		            console.log(e)
		            reject(e);
		        }
		     })
	   })
				  
   }

   return {
     api,
     projectApi,
     getParameter,
     ajaxRequest,
     formatTimestamp,
     getCookie,
     picUrl
   }
 })()
