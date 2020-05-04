
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${pages != null}">
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<c:if test="${currentPage!=1}">
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/admin/list/${param.dest}/${currentPage-1}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>
			<c:forEach var="pageno" items="${pages}">
				<c:choose>
					<c:when test="${currentPage eq pageno}">
						<li class="page-item active"><a class="page-link"
							href="${pageContext.request.contextPath}/admin/list/${param.dest}/${pageno}">${pageno}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/admin/list/${param.dest}/${pageno}">${pageno}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${currentPage < totalpages}">
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/admin/list/${param.dest}/${currentPage+1}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:if>
		</ul>
	</nav>
</c:if>
