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
                Edytuj fundację:
            </h2>
            <form:form modelAttribute="institution" method="post" novalidate="validate" cssClass="form-text">
                <div class="form-section form-section--columns edit-form ">
                    <div class="form-section--column">
                        <div class="form-group form-group--inline">
                            <label>
                                Nazwa <form:input path="name" placeholder="Nazwa fundacji" required="true"
                                                  minlength="3" value="${institution.name}"/>
                                <form:errors path="name" element="div" cssClass="error-div"/>
                                <div class="invalid-feedback">
                                    Podaj poprawną nazwę.
                                </div>
                            </label>
                        </div>
                        <div class="form-group form-group--inline">
                            <label>
                                Opis <form:textarea path="description" placeholder="Opis fundacji" required="true"
                                                    minlength="3" value="${institution.description}"/>
                                <form:errors path="name" element="div" cssClass="error-div"/>
                                <div class="invalid-feedback">
                                    Podaj poprawny opis.
                                </div>
                            </label>
                        </div>
                        <div class="form-group form-group--buttons">
                            <a href="${pageContext.request.contextPath}/show-institutions"
                               class="btn btn--highlighted" type="submit">Anuluj</a>
                            <button class="btn btn--highlighted" type="submit">Potwierdź</button>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</header>

<jsp:include page="footer.jsp"/>
</body>
</html>
