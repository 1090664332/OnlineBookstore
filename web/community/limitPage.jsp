<%--
  Created by IntelliJ IDEA.
  User: 郭浩宇
  Date: 2020/12/20
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">

    <c:if test="${requestScope.page.pageNum>1}">
        <a href="manager/bookServlet?action=${requestScope.pageType}">首页</a>
        <a href="manager/bookServlet?action=${requestScope.pageType}&pageNum=${requestScope.page.pageNum-1}">上一页</a>
    </c:if>
    <%--			总页面不足5页，或者当前页面是1、2、3--%>
    <c:choose>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="num">
                <c:if test="${requestScope.page.pageNum == num}">
                    【${num}】
                </c:if>
                <c:if test="${requestScope.page.pageNum != num}">
                    <a href="manager/bookServlet?action=${requestScope.pageType}&pageNum=${num}">${num}</a>
                </c:if>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${requestScope.page.pageNum<3}">
                    <c:forEach begin="1" end="5" var="num">
                        <c:if test="${requestScope.page.pageNum == num}">
                            【${num}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNum != num}">
                            <a href="manager/bookServlet?action=${requestScope.pageType}&pageNum=${num}">${num}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:when test="${requestScope.page.pageNum+3>requestScope.page.pageTotal}">
                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="num">
                        <c:if test="${requestScope.page.pageNum==num}">
                            【${num}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNum!=num}">
                            <a href="manager/bookServlet?action=${requestScope.pageType}&pageNum=${num}">${num}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNum-2}" end="${requestScope.page.pageNum+2}" var="num">
                        <c:if test="${requestScope.page.pageNum==num}">
                            【${num}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNum!=num}">
                            <a href="manager/bookServlet?action=${requestScope.pageType}&pageNum=${num}">${num}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <c:if test="${requestScope.page.pageNum<requestScope.page.pageTotal}">
        <a href="manager/bookServlet?action=${requestScope.pageType}&pageNum=${requestScope.page.pageNum+1}">下一页</a>
        <a href="manager/bookServlet?action=${requestScope.pageType}&pageNum=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageCount}条记录 到第<input value="${requestScope.page.pageNum}" name="pn" id="pn_input"/>页
    <input type="button" value="确定">
</div>
