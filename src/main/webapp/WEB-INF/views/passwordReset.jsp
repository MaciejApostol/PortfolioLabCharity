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
    <h2>Przypomnij hasło</h2>
    <form:form modelAttribute="user" method="post" novalidate="validate">
    <div class="form-group">
            <form:input path="email" placeholder="Email" cssClass="form-control" required="true" minLength="3"/>
            <form:errors path="email" element="div" cssClass="error-div"/>
        <div class="invalid-feedback">
            Podany adres email nie został znaleziony.
        </div>
        <div class="form-group form-group--buttons">
            <button class="btn btn--highlighted" type="submit">Potwierdź</button>
        </div>
        </form:form>

        <c:if test="${showEmailMessage==true}">
        <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title" id="exampleModalLabel">Link do zmiany hasła</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Na podany adres email został wysłany link potrzebny do zmainy hasła.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn--inherit" data-bs-dismiss="modal">OK</button>
                    </div>
                </div>
            </div>
        </div>
        </c:if>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
        integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
<jsp:include page="footer.jsp"/>
</body>
</html>