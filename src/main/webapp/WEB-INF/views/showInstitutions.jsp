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
    <div class="slogan container container--90">
        <div class="slogan--item">
            <div class="form-text">
                <ol>
                    <div class="wrapper">
                        <h2>
                            Lista fundacji:
                        </h2>
                        <c:forEach items="${institutions}" var="institution">
                            <li>
                                <div class="content">
                                    <div>
                                            ${institution.name}<br>
                                            ${institution.description}
                                    </div>
                                    <div class="form-group form-group--buttons sidebar">
                                        <a href="${pageContext.request.contextPath}/edit-institution?id=${institution.id}"
                                           class="btn btn--highlighted" type="submit">Edytuj</a>

                                        <a href="${pageContext.request.contextPath}/delete-institution?id=${institution.id}"
                                           class="btn btn--highlighted" type="submit">Usuń</a>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </div>
                </ol>
                <div class="add-institution">
                    <a href="${pageContext.request.contextPath}/edit-institution"
                       class="btn btn--highlighted" type="submit">Dodaj</a>
                </div>
            </div>
        </div>
    </div>
</header>

<jsp:include page="footer.jsp"/>
</body>
</html>
