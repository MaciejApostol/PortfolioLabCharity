<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">
<jsp:include page="head.jsp"/>

<body>
<header class="header--main-page">
    <jsp:include page="header.jsp"/>
    <div class="slogan container container--85">
        <div class="slogan--item">
            <h2>
                Lista fundacji:
            </h2>
            <div class="form-text">
                <ol>
                    <c:forEach items="${donations}" var="donation">
                        <li>
                                ${donation.institution.name}<br>
                            <ul>
                                <c:forEach items="${donation.categories}" var="category">
                                    <li>${category.name}</li>
                                </c:forEach>
                            </ul>
                            <div class="form-group form-group--buttons">
                                <a href="${pageContext.request.contextPath}/donation-details?id=${donation.id}"
                                   class="btn btn--highlighted" type="submit">Szczegóły</a>
                            </div>
                        </li>
                    </c:forEach>
                    <div class="add-institution">
                        <a href="${pageContext.request.contextPath}/edit-donation"
                           class="btn btn--highlighted" type="submit">Dodaj</a>
                    </div>
                </ol>
            </div>
        </div>
    </div>
</header>

<jsp:include page="footer.jsp"/>
</body>
</html>
