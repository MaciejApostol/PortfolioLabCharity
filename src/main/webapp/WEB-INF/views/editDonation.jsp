<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">
<jsp:include page="head.jsp"/>
<style>
    .flex {
        width: 80%;
        margin: 0 auto;
        padding: 2rem;
        background-color: green;
        display: flex;
        flex-direction: column;
        flex-wrap: wrap;
        justify-content: space-evenly;
    }
</style>

<body>
<header class="header--main-page">
    <jsp:include page="header.jsp"/>
    <div class="slogan container container--85">
        <div class="slogan--item">
            <form:form modelAttribute="donation" method="post" novalidate="validate" cssClass="form-text">
                <div class="form-section form-section--columns edit-form">
                    <h4>Szczegóły darowizny</h4>
                        <%--                        <div class="flex"></div>--%>
                    <div class="form-group form-group--inline">
                        <label>
                            <c:forEach items="${categories}" var="category">
                                <div class="form-group form-group--checkbox">
                                    <label>
                                        <form:checkbox path="categories" value="${category}" id="${category.name}"/>
                                        <span class="checkbox"></span>
                                        <span class="description">${category.name}</span>
                                    </label>
                                </div>
                            </c:forEach>
                            <div class="form-group form-group--checkbox">
                                <form:errors path="categories" element="div" cssClass="error-div"/>
                                <div class="invalid-feedback">
                                    Wybierz przynajmniej jedną kategorię.
                                </div>
                            </div>
                        </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label>
                            <c:forEach items="${institutions}" var="institution">
                                <div class="form-group form-group--checkbox edit-donation--checkbox">
                                    <label>
                                        <form:radiobutton path="institution" value="${institution}"
                                                          id="${institution.name}"/>
                                        <span class="checkbox radio"></span>
                                        <div class="description">
                                            <div class="title">${institution.name}</div>
                                        </div>
                                    </label>
                                </div>
                            </c:forEach>
                            <form:errors path="institution" element="div" cssClass="error-div"/>
                            <div class="form-group form-group--checkbox">
                                <div class="invalid-feedback">
                                    Wybierz przynajmniej jedną instytucję.
                                </div>
                            </div>
                        </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label>
                            Liczba 60l worków:
                            <form:input type="number" path="quantity" value="${donation.quantity}" step="1" min="1"
                                        required="true"/>
                            <form:errors path="quantity" element="div" cssClass="error-div"/>
                            <div class="invalid-feedback">
                                Podaj co najmniej 1 worek.
                            </div>
                        </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <h4>Adres odbioru</h4>
                        <label>
                            Ulica <form:input path="street" required="true" minlength="3"
                                              value="${donation.street}"/>
                            <form:errors path="street" element="div" cssClass="error-div"/>
                            <div class="invalid-feedback">
                                Podaj poprawną nazwę ulicy.
                            </div>
                        </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label>
                            Miasto <form:input path="city" required="true" minlength="3" value="${donation.city}"/>
                            <form:errors path="city" element="div" cssClass="error-div"/>
                            <div class="invalid-feedback">
                                Podaj poprawną nazwę miasta.
                            </div>
                        </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label>
                            Kod pocztowy <form:input path="zipCode" required="true" minlength="6"
                                                     value="${donation.zipCode}"/>
                            <form:errors path="zipCode" element="div" cssClass="error-div"/>
                            <div class="invalid-feedback">
                                Podaj poprawny kod pocztowy.
                            </div>
                        </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label>
                            Numer telefonu <input name="phone" value="123456789"/>
                            <div class="invalid-feedback">
                                Podaj poprawny numer telefonu.
                            </div>
                        </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <h4>Termin odbioru</h4>
                        <label>
                            Data <form:input type="date" path="pickUpDate" required="true"
                                             value="${donation.pickUpDate}"/>
                            <form:errors path="pickUpDate" element="div" cssClass="error-div"/>
                            <div class="invalid-feedback">
                                Podaj datę.
                            </div>
                        </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label>
                            Godzina <form:input type="time" path="pickUpTime" required="true"
                                                value="${donation.pickUpTime}"/>
                            <form:errors path="pickUpTime" element="div" cssClass="error-div"/>
                            <div class="invalid-feedback">
                                Podaj godzinę.
                            </div>
                        </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label>
                            Uwagi dla kuriera <form:textarea path="pickUpComment"
                                                             value="${donation.pickUpComment}"/>
                            <form:errors path="pickUpComment" element="div" cssClass="error-div"/>
                        </label>
                    </div>

                    <div class="form-group form-group--buttons">
                        <a href="${pageContext.request.contextPath}/show-donations" class="btn btn--highlighted"
                           type="submit">Anuluj</a>
                        <button class="btn btn--highlighted" type="submit">Potwierdź</button>
                    </div>
                </div>

            </form:form>
        </div>
    </div>
</header>

<jsp:include page="footer.jsp"/>
</body>
</html>
