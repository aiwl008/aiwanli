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
	<script type="text/javascript" src="../js/public/public.js"></script>
	<script type="text/javascript" src="../js/public/bootbox.js"></script>
	<script type="text/javascript" src="../js/public/formValidatorRegex.js"></script>
	<script type="text/javascript" src="../js/goods.js"></script>
	<style>
	li{
		list-style:none;
	}
	#editInfo .modal-dialog{
		width: 980px;
	}
	.bigbox{
	display:block;
	overflow: hidden;
	height:160px;
}
.box{
	position:relative;
    width: 233px;
    height: 160px;
	border:1px solid #ccc;
	text-align:center;	
	float:left;
	background: #fff;
    border-radius: 4px;
    margin-right: 10px;
}
.bigbox label{
	float:left;
}
.imgbox img{
	width:100%;
	height:100%;
}
.imgbox span{
	position: absolute;
    left: 0px;
    top: -2px;
    color: #fff;
    cursor: pointer;
    background: rgba(0,0,0,0.5);
    width: 100%;
    font-size: 24px;
    display:none;
}
.imgbox:hover  span {
	 display:block;
}
.bigbox .inpbox input{
	position:absolute;
	width: 233px;
    height: 160px;
	opacity: 0;
	left:0;
}
.inpbox span{
	font-size:56px;
	line-height:160px;
}
/* 行驶证图片轮播 */
#pic .modal-dialog{
	background:#fff;
}
.branch_bar{  
    width:700px;  
    height:500px; 
   margin:90px auto;
    overflow:hidden;  
    position:relative;  
    }  
.branch_bar .branch{  
    width:700px;
     height:500px;
    position:relative;
    }  
.branch_bar .branch li{  
    position:absolute; 
    display:none;
    width:524px;
    height:500px;
    background:#fff;
    } 
.branch_bar .branch li:first-child{
	display:block;
}
 .branch_bar .branch li img{
 		position: absolute; 
 		left: 50%; 
 		top: 50%; 
 		transform: translate(-50%,-50%);
 		-ms-transform: translate(-50%,-50%);
 }    
.prev{  
    position:absolute; 
    top:210px;  
    left:30px;  
    cursor:pointer;  
    } 
.prev  img{
	width:80px;
}
.next img{
	width:80px;
} 
.next{  
    position:absolute;  
    top:210px;  
    right:30px;  
    cursor:pointer;  
    } 
	</style>
<body>
	<h2>商品列表</h2>
	<div class="content">
      	<form onsubmit="return false" id="search-form">
      		
	      		<div class="searchCriteria inline-block">
	               <div class="searchInput">
	                   <label>商品名称</label>
	                   <input type="text" name="goodsname" id="goodsname" maxlength="7">
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
   			<table id="goodsList"></table>
	   	</div>
    </div>
    <!--模态框  -->
	<div class="modal fade" id="editInfo" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-mwapper ">
			<form id="save-form">
				<input id="goodsid" name="goodsid" hidden="true">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">编辑商品</h4>
					</div>

					<div class="modal-body">
							<div class="labelContent">
								<label for="reportNum" class="reqItem">商品名称：</label> 
								<input type="text" name="goodsname" id="goodsnameNew" maxlength="10">
								<br>
								<label for="reportNum" class="reqItem">商品价格：</label> 
								<input type="text" name="goodsprice" id="goodsprice" maxlength="5">
								<br>
								<label for="reportNum" class="reqItem">商品标签：</label> 
								<input type="text" name="goodstag" id="goodstag" maxlength="5">
								<br>
								<label for="reportNum" class="reqItem">商品款式：</label> 
								<input type="text" name="goodsstylenum" id="goodsstylenum" maxlength="5">有商品款式添加后会在父商品名列表显示
								<br>
								<div class="searchInput  selectContent radiorop-down" >
								
<!-- 								   <input  name="goodsparentid"  id="goodsparentid" /> -->
				                   <label class="Class">父商品名:</label>
				                   <select id="goodsparentid" name="goodsparentid">
				                   		<option value="0">全部</option>
										<c:forEach items="${parentGoods}" var="item">
											<option value="${item.goodsId}">${item.goodsName}</option>
										</c:forEach>
									</select>
				               </div>
				                
								<br>
								<div class="searchInput  selectContent radiorop-down" >
				                    <label class="Class">商品分类:</label>
				                    <c:forEach items="${childrenClass}" var="item">
										<input type="checkbox" name="className" value="${item.classId}" />${item.className}    
									</c:forEach>
									<input type="hidden" name="goodsclass"  id="goodsclass" />
				               </div>
								
								<br>
								<label for="reportNum" class="reqItem">商品描述：</label> 
								<input type="text" name="goodsdescribe" id="goodsdescribe" maxlength="50">
							</div>
							<div class="labelContent bigbox">
								<label for="reportNum" class="reqItem">图片上传：</label> 
								<div class="inpbox box" id="imageOneDiv">
								    <input type="hidden" class="pictureurType" value=""  name="goodsdetailsimage1Type"/>
							 		<input type="hidden" class="pictureur" value=""  name="goodsdetailsimage1"/>
							        <input type="file" value="" class="imginp" id=""   accept="image/jpg,image/JPG,image/gif,image/png" multiple/> 
							        <span>+</span>
								</div>
								<div class="imgbox  box" id="imageShowOne">
									<img src="" id="imageOne" class="img"/>
									<span class="close_btn">
										X
									</span> 
								</div>
								<div class="inpbox box" id="imageTwoDiv">
								    <input type="hidden" class="pictureurType" value=""  name="goodsdetailsimage2Type"/>
							 		<input type="hidden" class="pictureur" value=""  name="goodsdetailsimage2"/>
							        <input type="file" value="" class="imginp" id=""   accept="image/jpg,image/JPG,image/gif,image/png" multiple/> 
							        <span>+</span>
								</div>
								<div class="imgbox  box" id="imageShowTwo">
									<img src="" id="imageTwo" class="img"/>
									<span class="close_btn">
										X
									</span> 
								</div>
								<div class="inpbox box" id="imageThreeDiv">
								    <input type="hidden" class="pictureurType" value=""  name="goodsdetailsimage3Type"/>
							 		<input type="hidden" class="pictureur" value=""  name="goodsdetailsimage3"/>
							        <input type="file" value="" class="imginp" id=""   accept="image/jpg,image/JPG,image/gif,image/png" multiple/> 
							        <span>+</span>
								</div>
								<div class="imgbox  box" id="imageShowThree">
									<img src="" id="imageThree" class="img"/>
									<span class="close_btn">
										X
									</span> 
								</div>
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
	<!--图片模态框  -->
	<div class="modal fade" id="pic" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-wapper map-wrapper">
			<div class="modal-content">
			<button type="button" class="close location-close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				<div class="branch_bar">  
				    <ul class="branch">  
				       
				    </ul>  
				    
				</div> 
				  <span  class="prev">《</span>  
				  <span  class="next">》</span>  		   
			</div>
		</div>
	</div>
</body>
</html>
