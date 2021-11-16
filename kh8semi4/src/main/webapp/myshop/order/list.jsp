<%@page import="semi.beans.BuyDto"%>
<%@page import="java.util.List"%>
<%@page import="semi.beans.BuyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/template/header.jsp"></jsp:include>

    <%-- 입력 --%>
<%
	String id = (String)session.getAttribute("loginId");
%>

<%-- 처리 --%>
<%
	BuyDao buyDao = new BuyDao();
	List<BuyDto> buyList = buyDao.BuyProductMember(id);
%>

<%-- 출력 --%>
<table border="1" width="500">
			<thead>		
					<tr>
							<th>주문일자</th>
							<th>이미지</th>
							<th>상품정보</th>
							<th>수량</th>
							<th>상품구매금액</th>
							<th>주문처리상태</th>
							<th>취소/교환/반품</th>
					</tr>
			</thead>
			<tbody>
					<%for(BuyDto buyDto : buyList) {%>
							<tr>
									<td align="center"><%=buyDto.getBuyDate() %></td>
									<td align="center">없어용</td>
									<td align="center"><%=buyDto.getProductNo() %></td>
									<td align="center">없음</td>
									<td align="center">없음</td>
									<td align="center"><%=buyDto.getStatus() %></td>
									<td align="center">없음</td>
							</tr>
			</tbody>
			<%} %>
</table>


<jsp:include page="/template/footer.jsp"></jsp:include>