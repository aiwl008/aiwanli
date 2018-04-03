<%@ page contentType="text/html; charset=utf-8"%>
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
</html>

