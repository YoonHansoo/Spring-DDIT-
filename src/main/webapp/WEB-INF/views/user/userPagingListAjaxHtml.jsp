<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

			<c:forEach items="${userList}" var="user">
				<tr class='userTr' data-userid="${user.userId}">
					<td></td>
					<td>${user.userId}</td>
					<td>${user.userNm}</td>
					<td></td>
					<td>${user.reg_dt_fmt}</td>
					<%-- <td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy/MM/dd"/> </td> --%>
				</tr>
			</c:forEach>

			
			==========================seperator===========================
			
						<c:choose>
							<c:when test="${page eq 1}">
								<li class="disabled"><a ria-label="Previous"> <span
										aria-hidden="true">&laquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="javascript:getUserPageListHtml(1);"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="1" end="${lastPage}" var="i">
							<c:set var="active" value="" />
							<%--초기화 과정 값이 계속 남기때문에 --%>
							<c:if test="${i eq page}">
								<c:set var="active" value="active" />
								<%--현재 페이지일 때 활성화를 위해서--%>
							</c:if>
							<li class="${active}"><a href="javascript:getUserPageListHtml(${i})">${i}</a>
								<%-- href="${cp}/user/userPagingList?page=${i}">${i}</a> --%>
							
							</li>
						</c:forEach>

						<c:choose>
							<c:when test="${lastPage eq page}">
								<li class="disabled"><a ria-label="Next"> <span
										aria-hidden="true">&raquo;</span>
								</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="javascript:getUserPageListHtml(${lastPage})"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
							</c:otherwise>
						</c:choose>

						
							