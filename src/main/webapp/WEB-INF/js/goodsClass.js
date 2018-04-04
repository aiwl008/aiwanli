$(function() {
	
	var clientH=document.documentElement.clientHeight;
	var tableHeight=460;
	if(clientH>900){
		tableHeight=690;
	}
	initTable(tableHeight);
	initForm();
	$("#btn_search").click(function() {
		var x = $("#qlcTabContent").find(".page-number");
		x.addClass("active").siblings().removeClass("active");
		researcth();
	});
	
	//删除
	$("#btn_delete").click(function(){
		var rows = $("#goodsClassList").bootstrapTable("getSelections");
		if(rows.length<1){
	 		bootbox.alert("请选择要操作的数据");
	 		return;
		}else{
	    	bootbox.confirm("确定要执行删除操作吗 ?", function(result) {  
				if (result) { 
					$.post("delGoodsClass",{classid:rows[0].classId},function(result){
						if(result=='success'){
							researcth();
							
						}else{
							bootbox.alert(result.message);
						}
					});
				}
			});
	 	}
	});
	
	$("#btn_add").click(function() {
		$("#save-form")[0].reset();
		 $("#editInfo").modal('show');
	});
	
	// 保存
	$("#save-btn").click(function(){
		if($("#save-form").valid()){
			$.post("saveGoodsClass", $("#save-form").serialize(), function(re){
				if(re == 'success'){
					researcth();
					$("#editInfo").modal('hide');
					bootbox.alert("添加成功");
    			}else{
    				bootbox.alert("添加失败");
    			}
			});
		}
	});
	
	
	
	
	
	$("#btn_edit").click(function() {
		
		var rows = $("#goodsClassList").bootstrapTable("getSelections");
		if(rows.length<1){
     		bootbox.alert("请选择要操作的数据");
     		return;
	     }else{
	    	 $("#save-form")[0].reset();
	    	 $("#classnameNew").val(rows[0].className);
	    	 $("#classid").val(rows[0].classId);
	    	 
	    	 
	    	 $("#editInfo").modal('show');
     	}
	});
});




function initTable(h) {
	$("#goodsClassList")
			.bootstrapTable(
					{
						locale : 'zh-CN',
						method : 'post',
						url : "getGoodsClassList",
						striped : true,
						contentType : "application/x-www-form-urlencoded",
						queryParamsType : "limit",
						height : h,
						queryParams : function(params) {
							var data = bootstrapTableParams("#search-form",
									params);
							var x = $(".page-number.active");
							var page = x.find("a").html();
							if (!isNaN(page)) {
								page = parseInt(page);
								if (this.pageNumber != page) {
									this.pageNumber = page;
								}
								for (var int = 0; int < data.length; int++) {
									if (data[int].name == 'offset') {
										data[int].value = (page - 1)
												* parseInt($(".page-size")
														.html());
									}
								}
							}
							return data;
						},
						pagination : true,
						clickToSelect : true,
						singleSelect : true,
						pageSize : 15,
						pageList : [ 15, 25, 50, 100, 200 ],
						sidePagination : "server",
						columns : [
							{
								checkbox : true
							
							},{
								field : 'classId',
								width : 90,
								title : '分类'
							},{
								field : 'className',
								width : 90,
								title : '分类'
							},{
								field : 'parentId',
								width : 90,
								title : '父类'
							},{
								field : 'parentName',
								width : 90,
								title : '父类'
							}]
					});
}
	




//重置
function researcth() {
	console.log('a');
	$("#goodsClassList").bootstrapTable('refresh', {
		url : "getGoodsClassList"
	});
}



//校验
function initForm(){
	$("#save-form").validate({
		unhighlight: function (element, errorClass, validClass) { // 验证通过
        	$(element).tooltip('destroy').removeClass(errorClass);
     	},
   		errorPlacement: function (error, element) {
        $(element).attr('title', $(error).text()).tooltip('show'); 
    	},
    	rules:{
    		className:{
    			required:true
    		}
    	},
    	messages:{
    		
    		className:{
    			required:"不能为空"
    		}
    	}
	});
}



