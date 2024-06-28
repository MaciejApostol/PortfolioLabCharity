<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="head.jsp"/>

<body>
<header>
    <jsp:include page="header.jsp"/>
</header>

<section class="login-page">
    <h2>Email został potwierdzony</h2>
    <form:form modelAttribute="user" method="get" novalidate="validate"
               action="${pageContext.request.contextPath}/new-password">
        <form:hidden path="token" value="${user.token}" />
        <button class="btn btn--highlighted" type="submit">Potwierdź</button>
    </form:form>
</section>

<jsp:include page="footer.jsp"/>
</body>
</html>