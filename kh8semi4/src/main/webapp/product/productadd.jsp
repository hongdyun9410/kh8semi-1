<%@page import="semi.beans.ColorDto"%>
<%@page import="semi.beans.ColorDao"%>
<%@page import="semi.beans.SizeDto"%>
<%@page import="semi.beans.SizeDao"%>
<%@page import="semi.beans.BigTypeDto"%>
<%@page import="semi.beans.SmallTypeDto"%>
<%@page import="java.util.List"%>
<%@page import="semi.beans.SmallTypeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String root = request.getContextPath();
String isType=request.getParameter("bigtypeno");
String isNo = request.getParameter("no");

SmallTypeDao smallTypeDao = new SmallTypeDao();
List<SmallTypeDto> list;

if(isType == null){
  int bigTypeNo=smallTypeDao.searchBigTypeNo(Integer.parseInt(request.getParameter("no")));
  list=smallTypeDao.searchSmallType(bigTypeNo);
}else{
	int bigTypeNo=Integer.parseInt(isType);
	list=smallTypeDao.searchSmallType(bigTypeNo);
	isNo = ""+list.get(0).getNo();
}
BigTypeDto bigtypeDto = new BigTypeDto();

SizeDao sizeDao = new SizeDao();
List<SizeDto> sizeList = sizeDao.list();
ColorDao colorDao = new ColorDao();
List<ColorDto> colorList = colorDao.list();
%>

<jsp:include page="/template/header.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		$(".size-btn").click(function (e) {
        	e.preventDefault();
        	var template = $("#size-content-template").html();
        	$(".size-contents").append(template);
    	});
		
		$(".color-btn").click(function (e) {
        	e.preventDefault();
        	var template = $("#color-content-template").html();
        	$(".color-contents").append(template);
    	});
	  
	  	$("#list").on("click", ".btnDel", function() { //list안의 btnDel을 선택
	    	$(this).parent().parent().remove(); //this(btnDel)의 부모(td)의 부모(tr)를 삭제
	  	});  

	})
	//vscode 59번 survey 참고해서 사이즈 색상 고르는거 만들어!!
</script>



<form action="./productadd.kj" method=post enctype="multipart/form-data"> 
    <template id="size-content-template">
        <div class="row">
        	<select name="size">
        	<%for(SizeDto sizeDto : sizeList) {%>
           		<option value="<%=sizeDto.getNo()%>"><%=sizeDto.getSizeName()%></option>
        	<%}%>
        	</select>
        </div>
    </template>
    <template id="color-content-template">
    	<div class="row">
			<select name="color">
        	<%for(ColorDto colorDto : colorList) {%>
           		<option value="<%=colorDto.getNo()%>"><%=colorDto.getColorName()%></option>
        	<%}%>
        	</select>
        </div>
    </template>


	<div>
	<h1>상품등록 페이지</h1>
	</div>
	<div  class="row">	 
	
	 <select name=smallTypeNo class="form-input form-inline">
	 <%for(SmallTypeDto smallTypeDto : list){ %>
		<%if(smallTypeDto.getNo()==Integer.parseInt(isNo)){ %>	 
	 	<option selected value=<%=smallTypeDto.getNo()%>><%=smallTypeDto.getName()%></option>
	 	<%}else{ %>
	 		<option value=<%=smallTypeDto.getNo()%>><%=smallTypeDto.getName()%></option>
	    <%} %>
	 <%} %>
	 </select>

	</div>
	<div class="row">
	 상품명 <input type="text"  name="name" required class=form-input>
	</div>

	<div  class="row">
	 가격 <input type="number" name="price" class=form-input>
	</div>
	<div class="row">
		<div class="flex-container">
			<div class="flex-gro center">
   				<button style="width:30%" class="size-btn form-btn">사이즈 추가</button>
   				<div class="size-contents"></div>
			</div>
			<div class="flex-gro center">
				<button style="width:30%" class="color-btn form-btn">색상 추가</button>
				<div class="color-contents"></div>
			</div>
		</div>
	</div>
	<div  class="row">
	상품설명
	<textarea rows="40" cols="100" name="description" class=form-input></textarea>
	</div>
	
	<div class="row">
	상품이미지
	<input type="file" name="attach" accept="image/*" class="form-input">
	</div>
		
			

	<div class="row">
	<input type="submit" value="상품등록">
	</div>
</form>




<jsp:include page="/template/footer.jsp"></jsp:include>