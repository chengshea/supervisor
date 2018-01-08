layui.use('table', function(){
  var table = layui.table;
  
  table.render({
	  elem: '#test' //指定原始表格元素选择器（推荐id选择器）
	  ,height: 315 //容器高度
	  ,url: baseUrl+'/manager/userList' //数据接口
	  //,page: true //开启分页
	  ,response: {
			  statusName: 'code'  	//数据状态的字段名称，默认：code
			  ,statusCode: 200 		//成功的状态码，默认：0
			  ,msgName: 'msg'  		//状态信息的字段名称，默认：msg
			  ,countName: 'total' 	//数据总数的字段名称，默认：count
			  ,dataName: 'data' 	//数据列表的字段名称，默认：data
		  } 	
	  ,cols: [[
		  //{checkbox: true},
		  
		    {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
		    ,{field: 'address', title: '签名', width: 170}
		    ,{field: 'birthday', title: '城市', width:80} 
		    ,{field: 'openId', title: '积分', width: 80, sort: true}
		      ,{field: 'unoinId', title: '积分', width: 80, sort: true}
		      ,{field: 'sex', title: '性别', width:80, sort: true}
		      ,{field: 'username', title: '用户名', width:80}
		    
		     
		    
		    ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'} 
		    
		    
	  ]], //设置表头
	  //limits: [10,20,30,40,50,60,70,80,90]
	  //,…… //更多参数参考右侧目录：基本参数选项
	});
  
  //监听单元格编辑
 /*  table.on('edit(test3)', function(obj){
    var value = obj.value //得到修改后的值
    ,data = obj.data //得到所在行所有键值
    ,field = obj.field; //得到字段
    layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
  }); */
  
//监听工具条
  table.on('tool(demo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
  	var data = obj.data 		//获得当前行数据
  	,layEvent = obj.event; 		//获得 lay-event 对应的值		
  	if(layEvent === 'status'){
  		if(data.ifOpen==1){//改变状态
  			data.ifOpen=0;
  		}else{
  			data.ifOpen=1;
  		}
  		 $.ajax({
      	        url:baseUrl + '/update',
      	        data:{'status':data.ifOpen,'id':data.id},
      	        dataType:'json',//服务器返回json格式数据
      	        type:'POST',//HTTP请求类型
      	        timeout:10000,//超时时间设置为10秒；
      	        success:function(data){
      	        	console.log(data)
      	            if(data.code==200){
      	            	layer.msg(data.msg, {icon: 6}); 
      	            	 queryDynamic();
      	            }else{  		    	               
      	                layer.msg(data.msg, {icon: 6}); 
      	                
      	            }
      	        }
      	    });    			
  		layer.closeAll();
  	}
  });
  
  
  
  
});



