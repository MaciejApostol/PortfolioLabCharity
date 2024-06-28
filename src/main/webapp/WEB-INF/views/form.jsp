<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="head.jsp"/>

<body>
<header class="header--form-page">
    <jsp:include page="header.jsp"/>
    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>
        <form:form modelAttribute="donation" method="post" novalidate="validate" id="donationForm">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>

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
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60l worków:
                        <form:input type="number" path="quantity" step="1" min="1" required="true"/>
                        <form:errors path="quantity" element="div" cssClass="error-div"/>
                        <div class="invalid-feedback">
                            Podaj co najmniej 1 worek.
                        </div>
                    </label>
                    <div class="invalid-feedback">
                        Podaj co najmniej 1 worek.
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 4 -->
            <div data-step="3">
                <h3>Wybierz organizacje, której chcesz pomóc:</h3>

                <c:forEach items="${institutions}" var="institution">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <form:radiobutton path="institution" value="${institution}"
                                              id="${institution.name}"/>
                            <span class="checkbox radio"></span>
                            <div class="description">
                                <div class="title">${institution.name}</div>
                                <div class="subtitle">${institution.description}</div>
                            </div>
                        </label>
                    </div>
                </c:forEach>
                <form:errors path="institution" element="div" cssClass="error-div"/>
                <div class="form-group form-group--checkbox">
                    <div class="invalid-feedback">
                        Wybierz przynajmniej jedną fundację.
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="4">

                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label>
                                Ulica <form:input path="street" required="true" minlength="3" value="Długa 8/9A m.11"/>
                                <form:errors path="street" element="div" cssClass="error-div"/>
                                <div class="invalid-feedback">
                                    Podaj poprawną nazwę ulicy.
                                </div>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Miasto <form:input path="city" required="true" minlength="3" value="Poznań"/>
                                <form:errors path="city" element="div" cssClass="error-div"/>
                                <div class="invalid-feedback">
                                    Podaj poprawną nazwę miasta.
                                </div>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Kod pocztowy <form:input path="zipCode" required="true" minlength="6" value="00-001"/>
                                <form:errors path="zipCode" element="div" cssClass="error-div"/>
                                <div class="invalid-feedback">
                                    Podaj poprawny kod pocztowy.
                                </div>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Numer telefonu <input type="phone" name="phone" value="123456789"/>

                                <div class="invalid-feedback">
                                    Podaj poprawny numer telefonu.
                                </div>
                            </label>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label>
                                Data <form:input type="date" path="pickUpDate" required="true" value="10/23/2023"/>
                                <form:errors path="pickUpDate" element="div" cssClass="error-div"/>
                                <div class="invalid-feedback">
                                    Podaj datę.
                                </div>
                            </label>

                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Godzina <form:input type="time" path="pickUpTime" required="true" value="12:00"/>
                                <form:errors path="pickUpTime" element="div" cssClass="error-div"/>
                                <div class="invalid-feedback">
                                    Podaj godzinę.
                                </div>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Uwagi dla kuriera <form:textarea path="pickUpComment"/>
                                <form:errors path="pickUpComment" element="div" cssClass="error-div"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 6 -->
            <div data-step="5">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Przekazywane rzeczy:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text" id="summary1"
                                >4 worki ubrań w dobrym stanie dla dzieci</span
                                >
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text" id="summary2"
                                >Dla fundacji "Mam marzenie" w Warszawie</span
                                >
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul>
                                <li id="summary3">Prosta 51</li>
                                <li id="summary4">Warszawa</li>
                                <li id="summary5">99-098</li>
                                <li id="summary6">123 456 789</li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul>
                                <li id="summary7">13/12/2018</li>
                                <li id="summary8">15:40</li>
                                <li id="summary9">Brak uwag</li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="submit" class="btn">Potwierdzam</button>
                </div>
            </div>
            <form:hidden path="user"/>
        </form:form>
    </div>
</section>

<jsp:include page="footer.jsp"/>
</body>
</html>