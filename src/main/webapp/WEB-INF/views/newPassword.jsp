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
    <h2>Ustaw nowe hasło</h2>

    <form:form modelAttribute="user" method="post" novalidate="validate">
        <div class="form-group">
            <form:input path="password" type="password" placeholder="Nowe hasło" required="true" minLength="3"/>
            <form:errors path="password" element="div" cssClass="error-div"/>
            <div class="invalid-feedback">
                Podaj poprawne hasło.
            </div>
        </div>
        <div class="form-group">
            <form:input path="passwordConfirmation" type="password" placeholder="Powtórz hasło" required="true"
                        minLength="3"/>
            <form:errors id="${user.id}" element="div" cssClass="error-div"/>
            <div class="invalid-feedback">
                Podaj poprawne hasło.
            </div>
        </div>
        <button class="btn btn--highlighted" type="submit">Potwierdź</button>
    </form:form>

</section>

<jsp:include page="footer.jsp"/>
</body>
</html>