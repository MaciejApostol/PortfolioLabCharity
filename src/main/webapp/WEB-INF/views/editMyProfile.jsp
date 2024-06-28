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
                Edycja użytkownika:
            </h2>
            <form:form modelAttribute="user" method="post" novalidate="validate" cssClass="form-text">
                <div class="form-section form-section--columns edit-form ">
                    <div class="form-section--column wrapper2">
                        <div class="form-group form-group--inline">
                            <label>
                                <div>Nazwa</div>
                                <form:input path="username" placeholder="Nazwa użytkownika"  />
<%--                                            required="true"--%>
<%--                                            minlength="3"--%>

                                <form:errors path="username" element="div" cssClass="error-div"/>
                                <div class="invalid-feedback">
                                    Podaj poprawną nazwę.
                                </div>
                            </label>
                        </div>
                        <div class="form-group form-group--inline">
                            <label>
                                <div>Email</div>
                                <form:input path="email" placeholder="Email użytkownika" required="true"
                                            minLength="3"/>
                                <form:errors id="${user.id}" element="div" cssClass="error-div"/>
                                <form:errors path="email" element="div" cssClass="error-div"/>
                                <div class="invalid-feedback">
                                    Podaj poprawny adres email.
                                </div>
                            </label>
                        </div>
                        <div class="form-group form-group--inline">
                            <label for="passwordCheck">
                                <div>Edytuj<br> hasło</div>
                                <input type="checkbox" id="passwordCheck" name="passwordCheck"/>
                            </label>
                        </div>
                        <div id="editPassword">
                            <div class="form-group form-group--inline">
                                <label>
                                    <div>Hasło</div>
                                    <form:input path="password" type="password" placeholder="Nowe hasło" minLength="3"/>
                                    <form:errors path="password" element="div" cssClass="error-div"/>
                                    <div class="invalid-feedback">
                                        Podaj poprawne hasło.
                                    </div>
                                </label>
                            </div>
                            <div class="form-group form-group--inline">
                                <label>
                                    Powtórz<br>hasło</br>
                                    <form:input path="passwordConfirmation" type="password" placeholder="Powtórz hasło"
                                                minLength="3" />
                                    <form:errors id="${user.id}" element="div" cssClass="error-div"/>
                                    <div class="invalid-feedback">
                                        Podaj poprawne hasło.
                                    </div>
                                </label>
                            </div>
                        </div>
                        <div class="form-group form-group--buttons">
                            <a href="${pageContext.request.contextPath}/show-users"
                               class="btn btn--highlighted" type="submit">Anuluj</a>
                            <button class="btn btn--highlighted" type="submit">Potwierdź</button>
                        </div>
                    </div>
                </div>
                <form:hidden path="id"/>
                <form:hidden path="deleted"/>
                <form:hidden path="enabled"/>
            </form:form>
        </div>
    </div>
</header>

<jsp:include page="footer.jsp"/>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script>

    $(document).ready($(function () {
        let editPassword = $("div[id='editPassword']")[0];
        $("input[id='passwordCheck").click(function () {
            if (this.checked) {
                editPassword.style.display = "block"
            } else {
                editPassword.style.display = "none"
            }
        });
    }))
</script>
</body>
</html>
