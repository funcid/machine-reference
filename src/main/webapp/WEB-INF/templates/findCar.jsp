<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<c:choose>
    <c:when test="${not empty car}">
        <b>Машина с ID ${id}:</b>
        <ul>
            <li><b>${car.display} марки ${car.company}</b></li>
        </ul>
    </c:when>
    <c:otherwise>
        <b>Машина с ID ${id} не найдена</b>
    </c:otherwise>
</c:choose>

<p><a href="/">Вернуться</a></p>
</body>
</html>