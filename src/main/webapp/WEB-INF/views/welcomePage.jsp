<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<jsp:include page="head.jsp"/>

<body>
<header class="header--main-page">
    <jsp:include page="header.jsp"/>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <sec:authorize access="hasRole('ADMIN')">
                <h1>
                    Jesteś zalogowany jako admin<br/>
                    Nazwa admina: ${user.username}<br/>
                    Adres admina: ${user.email}
                </h1>
            </sec:authorize>
            <sec:authorize access="hasRole('USER')">
                <h1>
                    Nazwa użytkownika: ${user.username}<br/>
                    Adres email użytkownika: ${user.email}
                </h1>
            </sec:authorize>
        </div>
    </div>

</header>

<section class="stats" id="stats">
    <div class="container container--85">
        <div class="stats--item">
            <em>${sumBagsQuantity}</em>
            <h3>Oddanych worków</h3>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius est beatae, quod accusamus illum
                tempora!</p>
        </div>

        <div class="stats--item">
            <em>${countDonations}</em>
            <h3>Przekazanych darów</h3>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Laboriosam magnam, sint nihil cupiditate quas
                quam.</p>
        </div>

    </div>
</section>


<jsp:include page="footer.jsp"/>
</body>
</html>
