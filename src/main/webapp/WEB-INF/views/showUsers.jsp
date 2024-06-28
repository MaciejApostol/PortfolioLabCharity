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
                <c:choose>
                <c:when test="${empty users}">
                    <h1 class="no-decoration">
                        Brak użytkowników
                    </h1>
                </c:when>
                <c:otherwise>
                <h2>
                    Lista użytkowników
                </h2>
                <ol>
                    <div class="wrapper">

                        <c:forEach items="${users}" var="user">
                            <li>
                                <div class="content">
                                    <div>
                                            ${user.username}<br>
                                            ${user.email}<br>
                                    </div>
                                    <div class="form-group form-group--buttons sidebar">
                                        <c:choose>
                                            <c:when test="${user.enabled.ordinal()==1}">
                                                <a href="${pageContext.request.contextPath}/edit-user?id=${user.id}"
                                                   class="btn btn--highlighted" type="submit">Edytuj</a>
                                                <a href="${pageContext.request.contextPath}/delete-user?id=${user.id}"
                                                   class="btn btn--highlighted" type="submit">Usuń</a>
                                                <a href="${pageContext.request.contextPath}/block-user?id=${user.id}"
                                                   class="btn btn--highlighted" type="submit">Zablokuj</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${pageContext.request.contextPath}/unblock-user?id=${user.id}"
                                                   class="btn btn--highlighted" type="submit">Odblokuj</a>
                                                <a href="${pageContext.request.contextPath}/delete-user?id=${user.id}"
                                                   class="btn btn--highlighted" type="submit">Usuń</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                        </c:otherwise>
                        </c:choose>
                    </div>
                </ol>

            </div>
        </div>
    </div>
</header>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
        integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
<jsp:include page="footer.jsp"/>
</body>
</html>
