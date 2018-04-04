<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
  	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" />
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap-table/1.11.0/bootstrap-table.min.css" />
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
	<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
	<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="http://cdn.bootcss.com/jquery-validate/1.14.0/jquery.validate.min.js"></script>
	<script type="text/javascript" src="../js/public.js"></script>
	<script type="text/javascript" src="../js/bootbox.js"></script>
	<script type="text/javascript" src="../js/goodsClass.js"></script>
	<style>
		li{
			list-style:none;
		}
		.selectText {
	     border: 1px solid #ddd;
	     outline: none;
	     width: 140px;
	     height: 30px;
	     line-height: 30px;
	     display: inline-block;
	     padding: 0 10px;
	     cursor: pointer;
	     position: relative;
	 	 background: url(../img/select_icon.png) no-repeat;
	     background-position: right 8px;
	 }
	 	.searchCriteria  .searchInput{
	    display: inline-block;
	    margin-right: 6px;
	    margin-bottom: 12px;
	    position: relative;
	}
		.searchCriteria  .searchInput{
	    display: inline-block;
	    margin-right: 6px;
	    margin-bottom: 12px;
	    position: relative;
	}
	.searchCriteria  .searchInput>label{
	    display: block;
	    margin: 0;
	    font-size: 14px;
	    font-weight:normal;
	}
	.searchCriteria  .searchInput>input{
	    width: 160px;
	    line-height: 26px;
	    background-color: #fff;
	     height: 26px;
	}
	.searchCriteria .searchInput .Wdate{
	    border: 1px solid #ddd;
	    height: 30px;
	    width: 120px;
	} 
	.searchCriteria .inputNone{
	    display: none;
	} 
	.searchbtn{
	    margin-bottom: 10px;
	}
	

.selectContent input{
	    padding-left: 0;
}
 .venus-menu {
        width: 23%;
		list-style: none;
		position: absolute;
		border: 1px solid #ddd;
		line-height: 30px;
		background: #fff;
		z-index: 100;
		max-height: 200px;
		overflow: auto;
		margin-left: 32px;
 }
 .venus-menu li {
     display: inline-block;
     width: 100%;
      padding-left: 10px;
     color: #707070;
     white-space: nowrap;
 }
 .venus-menu li:hover {
     background-color: #eae6e6;
     line-height: 30px;
     cursor: pointer;
 }
 .venus-menu li input[type="checkbox"] {
     width: 16px;
     height: 16px;
     margin-left: 10px;
 }
 .venus-menu li label {
     background-color: transparent;
     font-size: 14px;
     font-weight: normal;
     color: #6B5B5B;
     min-width: 0;
     cursor: pointer;
     vertical-align: middle;

 }
 .selectText {
     border: 1px solid #ddd;
     outline: none;
     width: 140px;
     height: 30px;
     line-height: 30px;
     display: inline-block;
     padding: 0 10px;
     cursor: pointer;
     position: relative;
  background: url(../img/select_icon.png) no-repeat;
     background-position: right 8px;
 }
	</style>
<body>
	<h2>商品分类列表</h2>
   	 <div class="content">
      	<form onsubmit="return false" id="search-form">
      		
	      		<div class="searchCriteria inline-block">
	               <div class="searchInput">
	                   <label>分类名称</label>
	                   <input type="text" name="classname" id="classname" maxlength="7">
	               </div>
	           </div>
     	</form>
     	<div class="searchbtn inline-block">
					<button class="btn btn-query" type="button" id="btn_search">
						<i class="iconfont icon-sousuo"></i>查询
					</button>
					<button class="btn btn-query" type="button" id="btn_delete">
						<i class="iconfont icon-sousuo"></i>删除
					</button>
					<button class="btn btn-query" type="button" id="btn_add">
						<i class="iconfont icon-sousuo"></i>添加
					</button>
					<button class="btn btn-query" type="button" id="btn_edit">
						<i class="iconfont icon-sousuo"></i>编辑
					</button>
				</div>
          <div class="table-wapper">
   			<table id="goodsClassList"></table>
	   	</div>
    </div>
    	<!--模态框  -->
		<div class="modal fade" id="editInfo" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-mwapper ">
				<form id="save-form">
					<input id="classid" name="classid" hidden="true">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">编辑分类</h4>
						</div>

						<div class="modal-body">
								<div class="labelContent">
									<label for="reportNum" class="reqItem">分类：</label> <input
										type="text" name="classname" id="classnameNew" maxlength="5">
								</div>
						</div>
						<div class="searchInput  selectContent radiorop-down" >
		                   <label class="Class">父类</label>
		                   <input type="text" class="selectText" name="Class" value="全部"  readonly/>
		                    <input type="hidden" name="parentid"  id="parentid" value="" class="selectTextId" readonly="readonly" />
			                <ul class="venus-menu" style="">
			                    <c:forEach items="${parentClass}" var="item">
									<li><label for="${item.classId}">${item.className}</label></li>
								</c:forEach>
			                </ul>
		               </div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" id="save-btn">确定</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="">关闭</button>
						</div>
					</div>
				</form>
			</div>
		</div>
</body>
<script>
/* 下拉复选框 */
$("div.selectContent").each(function(i, e) {
	var $e = $(e);
	new SelectBox($e);
});
/* 下拉列表 */
function SelectBox($cell) {
	var $list = $cell.find("ul.venus-menu");
	$list.hide();
	var $items = $list.children("li");
	var $input = $cell.find("input.selectText");
	var isSelectAll = false;
	/* 下拉列表 */
	$input.on("click", function() {
		$cell.siblings().find("ul.venus-menu").hide();;
		$list.show();
	});

	$list.on("click", function(e) {
		e.stopPropagation();
		var tagName = e.target.tagName.toLowerCase();
		if (tagName == "ul")
			return;
		var l = $items.length;
		var p = $(e.target);
		var flag = true;
		switch (tagName) {
			case "li":
				p = p.children("input");
				break;
			case "input":
				flag = !flag;
				break;
			default:
				p = p.siblings("input");
				break;
		}
		if (p.hasClass("selectAll")) {
			if (!isSelectAll) {
				var val = p.next("label").text();
				console.log(true);
				$items.find("input").prop("checked", true);
				$input.val(val);
				//$list.hide();
			} else {
				$items.find("input").prop("checked", false);
				console.log(false);
				$input.val('');
			}
			isSelectAll = !isSelectAll;
		} else {
			var str = "";
			if (p.length) {
				flag && p.prop("checked", !p.prop("checked"));
				if (!p.prop("checked")) {
					$items.children("input.selectAll").prop("checked", false);
					isSelectAll = false;
				}
				for (var i = 0; i < l; i++) {
					if ($items.eq(i).children("input").prop("checked")) {
						str += $items.eq(i).children("label").text() + ",";
					}
				}
				str && (str = str.substring(0, str.length - 1));
			} else {
				str = $(e.target).text();
				$list.hide();
			}
			$input.val(str);
		}
	});
	return this;
}
/*单选下拉赋id值*/
$(".radiorop-down li").click(function(e) {
		var val = $(this).find("label").text();
	var valId = $(this).find("label").attr("for");
	$(this).parents(".selectContent").find(".selectText").val(val);
	$(this).parents(".selectContent").find(".selectTextId").val(valId);
	$(".selectContent .venus-menu").hide();
	e.stopPropagation();
	});
</script>
</html>

