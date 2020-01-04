<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<c:choose>
    <c:when test="${not empty cars}">
        <b>Машина марки ${company}:</b>
        <ul>
            <c:forEach items="${cars}" var="car">
                <li><b>${car.display} марки ${car.company}</b></li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <b>Машина марки ${company} не найдены</b>
    </c:otherwise>
</c:choose>

<p><a href="/">Вернуться</a></p>
</html>