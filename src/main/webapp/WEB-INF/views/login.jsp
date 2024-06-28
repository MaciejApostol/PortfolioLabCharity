<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setBundle basename="messages_pl"/>
<fmt:message key="message.password" var="noPass"/>
<fmt:message key="message.username" var="noUser"/>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="head.jsp"/>

<body>
<header>
    <jsp:include page="header.jsp"/>
</header>

<section class="login-page">
    <h2>Zaloguj się</h2>
    <form:form modelAttribute="user" method="post" novalidate="validate">
        <div class="form-group">
            <input name="email" placeholder="Email" class="form-control" required="required" minLength="3"
                   value="johnuser@gmail.com"/>
<%--                                   value="${user.email}"/>--%>
            <div class="invalid-feedback">
                Podaj poprawny adres email.
            </div>
            <c:if test="${param.error == true}">
                <div class="error-div">
                    Nieprawidłowy adres email lub hasło.
                </div>
            </c:if>

            <c:if test="${enabled=='UNABLED'}">
                <div class="error-div">
                    Twoje konto zostało zablokowane.
                </div>
            </c:if>
        </div>
        <div class="form-group">
            <input name="password" type="password" placeholder="Hasło" required="required" minLength="3"
                   value="H@slo123"/>

            <div class="invalid-feedback">
                Podaj poprawne hasło.
            </div>
            <a href="${pageContext.request.contextPath}/password-reset"
               class="btn btn--small btn--without-border password-reset">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
            <a href="${pageContext.request.contextPath}/register" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Zaloguj się</button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>

</section>

<jsp:include page="footer.jsp"/>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script>
    $(function () {
        $("input[name='email']").on("input", function () {
            $("div.error-div").hide();
        })
    })
</script>
</body>
</html>