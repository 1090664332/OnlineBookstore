<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>

    <%-- 静态包含 base标签，js代码，css样式 --%>
    <%@ include file="/community/header.jsp"%>


</head>
<script type="text/javascript" >
    $(function () {
        $("button.addButton").click(function () {
            location.href="${baseHref}cartServlet?action=addItem&id="+$(this).attr("bookId");
        });
    });
</script>

<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">网上书城</span>
    <div>
        <c:if test="${empty sessionScope.username}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${not empty sessionScope.username}">
            <span>欢迎<span class="um_span">${sessionScope.username}</span>光临尚硅谷书城</span>
            <a href="clientServlet?action=logOut">注销</a>
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="pages/manager/manager.jsp">后台管理</a>
        </c:if>
    </div>
</div>

<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="clientServlet" method="get">
                <input type="hidden" name="action" value="screeningOfPrice">
                价格：<input id="min" type="text" name="min" value="${requestScope.min}"> 元 -
                <input id="max" type="text" name="max" value="${requestScope.max}"> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <div style="text-align: center">
            <span>您的购物车中有${sessionScope.cartObj.count}件商品</span>
            <div>
                您刚刚将<span style="color: red">${sessionScope.lastName}</span>加入到了购物车中
            </div>
        </div>
        <c:forEach items="${requestScope.page.items}" begin="0" end="${requestScope.page.items.size()-1}" var="item">
        <div class="b_list">
            <div class="img_div">
                <img class="book_img" alt="" src="${item.img_path}" />
            </div>
            <div class="book_info">
                <div class="book_name">
                    <span class="sp1">书名:</span>
                    <span class="sp2">${item.name}</span>
                </div>
                <div class="book_author">
                    <span class="sp1">作者:</span>
                    <span class="sp2">${item.author}</span>
                </div>
                <div class="book_price">
                    <span class="sp1">价格:</span>
                    <span class="sp2">￥${item.price}</span>
                </div>
                <div class="book_sales">
                    <span class="sp1">销量:</span>
                    <span class="sp2">${item.sales}</span>
                </div>
                <div class="book_amount">
                    <span class="sp1">库存:</span>
                    <span class="sp2">${item.stock}</span>
                </div>
                <div class="book_add">
                    <button class="addButton" bookId="${item.id}">加入购物车</button>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
    <%@ include file="/community/limitPage.jsp"%>

</div>

<%-- 静态包含页脚 --%>
<%@ include file="/community/footer.jsp"%>

</body>
</html>