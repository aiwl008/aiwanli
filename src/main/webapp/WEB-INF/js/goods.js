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
		var rows = $("#goodsList").bootstrapTable("getSelections");
		if(rows.length<1){
	 		bootbox.alert("请选择要操作的数据");
	 		return;
		}else{
	    	bootbox.confirm("确定要执行删除操作吗 ?", function(result) {  
				if (result) { 
					$.post("delGoods",{goodsid:rows[0].goodsId},function(result){
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
		
		$('.imgbox').remove();
		
		$("#imageShowOne").attr("style","display:none;");
		$("#imageShowTwo").attr("style","display:none;");
		$("#imageShowThree").attr("style","display:none;");
		
		 $("#imageOneDiv").attr("style",";");
		 $("#imageTwoDiv").attr("style","");
		 $("#imageThreeDiv").attr("style","");
		
		$("#editInfo").modal('show');
	});
	
	
	$("select#goodsparentid").change(function(){
	    $("#goodsparentid").val($(this).val());
	});
	// 保存
	$("#save-btn").click(function(){
		fun();
		if($("#save-form").valid()){
			$.post("saveGoods", $("#save-form").serialize(), function(re){
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
	
	function fun(){
	    obj = document.getElementsByName("className");
	    check_val = [];
	    for(k in obj){
	        if(obj[k].checked)
	            check_val.push(obj[k].value);
	    }
	    $("#goodsclass").val(check_val);
	}
	
	$("#btn_edit").click(function() {
		
		var rows = $("#goodsList").bootstrapTable("getSelections");
		if(rows.length<1){
     		bootbox.alert("请选择要操作的数据");
     		return;
	     }else{
	    	 $("#save-form")[0].reset();
	    	 $("#goodsid").val(rows[0].goodsId);
	    	 $("#goodsnameNew").val(rows[0].goodsName);
	    	 $("#goodsid").val(rows[0].goodsId);
	    	 $("#goodsprice").val(rows[0].goodsPrice);
	    	 $("#goodstag").val(rows[0].goodsTag);
	    	 $("#goodsclass").val(rows[0].goodsClass);
	    	 $("#goodsdescribe").val(rows[0].goodsDescribe);
	    	 
	    	 //隐藏Div
    		 $("#imageOneDiv").attr("style","display:none;");
    		 $("#imageTwoDiv").attr("style","display:none;");
    		 $("#imageThreeDiv").attr("style","display:none;");
    		 $("#imageShowOne").attr("style","");
    		 $("#imageShowTwo").attr("style","");
    		 $("#imageShowThree").attr("style","");
    		 
    		 $('.pictureurType').val('');
    		 $('.pictureur').val('');
    		 
    		 
    		 
    		 //图片复制
	    	 $("#imageOne").attr('src',rows[0].imageOne); 
	    	 $("#imageTwo").attr('src',rows[0].imageTwo); 
	    	 $("#imageThree").attr('src',rows[0].imageThree);
	    	 
	    	 $("#editInfo").modal('show');
	    	 
     	}
	});
	
	//图片上传
	$('.imginp').on('change',function(e){
		$(this).attr('disabled', 'disabled');
		var _this=$(this);
		setTimeout(function(){
			_this.attr('disabled', false);
		},2000); 
		var objUrl = getObjectURL(this.files[0]) ;
		var imgType = this.files[0].name.substr(this.files[0].name.lastIndexOf(".") + 1);
		var inpBOx = $(this).parent();
		var imgbox = $('<div class="imgbox  box"><img src="" class="img"/><span class="close_btn">X</span> </div> ');
		if (imgType != "jpg" && imgType != "jpeg" && imgType != "png" && imgType != "bmp") {
            bootbox.alert("请选择正确的图片格式");
            return false;
        }
		if(objUrl){
			 ImgToBase64(this.files[0], 500, function (base64) {
				  inpBOx.find('input[class=pictureur]').val(base64);
	         });
			 imgbox.find('.img').attr("src", objUrl);
			 inpBOx.find('input[class=pictureurType]').val(imgType);
			 
		}   
		imgbox.find('.close_btn').click(function(){
			inpBOx.show();
			$(this).parent().remove();
			inpBOx.find('input[type=hidden]').val('');
		})
		
		$(this).parent().after(imgbox);
		inpBOx.hide();
	});
	$('.imgbox .close_btn').click(function(){
		$(this).parent().prev().show();
		$(this).parent().prev().find('input[type=hidden]').val('');
		$(this).parent().remove();
	});
	
	function getObjectURL(file) {
		var url = null;
		if (window.createObjectURL != undefined) { // basic
			url = window.createObjectURL(file);
		} else if (window.URL != undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file);
		} else if (window.webkitURL != undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file);
		}
		return url;
	}
	
	//html下纯JS实现图片压缩/图片Base64转换
	function ImgToBase64(file, maxLen, callBack) {
	    var img = new Image();

	    var reader = new FileReader();//读取客户端上的文件
	    reader.onload = function () {
	        var url = reader.result;//读取到的文件内容.这个属性只在读取操作完成之后才有效,并且数据的格式取决于读取操作是由哪个方法发起的.所以必须使用reader.onload，
	        img.src = url;//reader读取的文件内容是base64,利用这个url就能实现上传前预览图片
	    };
	    img.onload = function () {
	        //生成比例
	        var width = img.width, height = img.height;
	        //计算缩放比例
	        var rate = 1;
	        if (width >= height) {
	            if (width > maxLen) {
	                rate = maxLen / width;
	            }
	        } else {
	            if (height > maxLen) {
	                rate = maxLen / height;
	            }
	        };
	        img.width = width * rate;
	        img.height = height * rate;
	        //生成canvas
	        var canvas = document.createElement("canvas");
	        var ctx = canvas.getContext("2d");
	        canvas.width = img.width;
	        canvas.height = img.height;
	        ctx.drawImage(img, 0, 0, img.width, img.height);
	        var base64 = canvas.toDataURL('image/jpeg', 0.9);
	        callBack(base64);
	    };
	    reader.readAsDataURL(file);
	}
	var n=0;
	$('.next').click(function(){
		console.log(444)
		n++;
		if(n==3){
			n=1;
			$('.next').css('opacity',0.3);
			return;
		}else{
			
			$('.prev').css('opacity',1);
			$('.next').css('opacity',1);
			$('.branch li').eq(n).fadeIn().siblings().hide();
		}
	});
	$('.prev').click(function(){
		n--;
		if(n==-1){
			n=0;
			$('.prev').css('opacity',0.3);	
			return;
		}else{
			$('.prev').css('opacity',1);
			$('.next').css('opacity',1);
			$('.branch li').eq(n).fadeIn().siblings().hide();
		}
	});
	

});

//行驶证图片
function pic(index){
	var rowData = $('#goodsList').bootstrapTable('getData')[index];
	var arr=[];
	n=0;
	$('.branch').html('');
	$("#pic").modal("show")
	arr.push(rowData.imageOne);
	arr.push(rowData.imageTwo);
	arr.push(rowData.imageThree);
	$.each(arr,function(index,value){
	    if(value){
	    	console.log(value);
			$('.branch').append('<li><img src="'+value+'"></li> ');
	    }
	});
	if($('.branch li').length<=1){
		$('.next').hide();
		$('.prev').hide();
	}else{
		$('.next').show();
		$('.prev').show();
	}
}


function initTable(h) {
	$("#goodsList")
			.bootstrapTable(
					{
						locale : 'zh-CN',
						method : 'post',
						url : "getGoodsList",
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
								field : 'goodsId',
								width : 90,
								title : '分类'
							},{
								field : 'goodsName',
								width : 90,
								title : '商品名称'
							},{
								field : 'goodsPrice',
								width : 90,
								title : '商品价格'
							},{
								field : 'goodsTag',
								width : 90,
								title : '商品标注'
							},{
								field : 'goodsClass',
								width : 90,
								title : '商品分类'
							},{
								field : 'goodsStyleNum',
								width : 90,
								title : '商品款式数量'
							},{
								field : 'goodsImage',
								width : 100,
								title : '商品图片',
								formatter : function(value, row, index) {
									if((row.imageOne==null||row.imageOne=="")&&(row.imageTwo==null||row.imageTwo=="")&&(row.imageThree==null||row.imageThree=="")){
										return;
									}else{
										return '<a style="cursor:pointer;" onclick="pic(' + index + ')">查看</a>';
									}
                                }
							},{
								field : 'goodsDescribe',
								width : 90,
								title : '商品描述'
							},{
								field : 'imageOne',
								width : 90,
								title : '图片一'
							},{
								field : 'imageTwo',
								width : 90,
								title : '图片二'
							},{
								field : 'imageThree',
								width : 90,
								title : '图片三'
							}]
					});
}
	




//重置
function researcth() {
	console.log('a');
	$("#goodsList").bootstrapTable('refresh', {
		url : "getGoodsList"
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
    		goodsname:{
    			required:true
    		},
    		// 校验数字且最多两位小数
    		goodsprice:{
    			reg:regTwoDecimal
    		},
    		goodstag:{
    			required:true
    		},
    		className:{
    			required:true
    		},
    		goodsdescribe:{
    			required:true
    		}
    	},
    	messages:{
    		goodsname:{
    			required:"不能为空"
    		},
    		goodsprice:{
    			reg:regTwoDecimalPrompt
    		},
    		goodstag:{
    			required:"不能为空"
    		},
    		className:{
    			required:"不能为空"
    		},
    		goodsdescribe:{
    			required:"不能为空"
    		}
    	}
	});
}



