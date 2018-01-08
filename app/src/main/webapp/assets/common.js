layui.use(['layedit', 'jquery','laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'], function(){
	var $ = layui.jquery;  			//引用自身的JQuery
	var laydate = layui.laydate 	//日期
  	,laypage = layui.laypage 		//分页
  	,layer = layui.layer 			//弹层
  	,table = layui.table 			//表格
  	,carousel = layui.carousel 		//轮播
 	,upload = layui.upload 			//上传
 	,layedit = layui.layedit 		//文本编辑器
 	,element = layui.element; 		//元素操作
 	
//将日期直接嵌套在指定容器中 
	laydate.render({ 
		elem: '#time'
		,type: 'date'				
	});
	
	var type,file,value,tr,files ;
	upload.render({
		  elem: '#import'
		  ,url: baseUrl + '/upload'
		  ,method: 'post' 
		  ,auto: false //选择文件后不自动上传
		  ,multiple: true //多文件
		  ,accept:'file'
		  ,exts: 'jpg|gif|jpeg|png'
		  ,bindAction: '#importList' //指向一个按钮触发上传
		,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
		    layer.load(); //上传loading
			this.data={'year':value,'fileType':type,'stage':file}//动态选择参数
		  }
		  ,choose: function(obj){
		    //将每次选择的文件追加到文件队列
		    var files = obj.pushFile();
		    type = $("#typeName1").find("option:selected").val();
			  file = $("#typeName2").find("option:selected").val();
			  value = $("#birthdateId").val();
			 if(type!=null && file!=null){
			 console.log("type===="+type+" file="+file+" date="+value)
				 
			 }
		    //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
		      obj.preview(function(index, file, result){
		      console.log(index); //得到文件索引
		      console.log(file.name); //得到文件对象    name 文件名
		      //这里还可以做一些 append 文件列表 DOM 的操作
		       tr = $('#demo2').append('<tr id="'+index+'"><td>文件名 ：</td><td>'+ file.name +'</td><td><button  class="delete'+index+'">删除</button></td></tr>')
		      //obj.upload(index, file); //对上传失败的单个文件重新上传，一般在某个事件中使用
			     tr.find('.delete'+index).on('click', function(){
			    	 console.log("dain==="+'.delete'+index)
					delete files[index]; //删除对应的文件
					$('#'+index).remove();
					
				 }); //删除列表中对应的文件，一般在某个事件中使用
		    });
		  }
		  ,allDone: function(obj){ //当文件全部被提交后，才触发
			    console.log("===="+obj.total); //得到总文件数
			    console.log("=="+obj.successful); //请求成功的文件数
			    tr.remove();
			    layer.closeAll();
		 }
		 ,done: function(res, index, upload){
			    if(res.code == 200){
			    	layer.msg('导入成功!', {icon: 6});
		    		queryDynamic();
			    }else{
		    		layer.msg(res.msg, {icon: 2});
		    		queryDynamic();
		    	}
			    
			   
		}
		
		
	}); 
	
	
});
	