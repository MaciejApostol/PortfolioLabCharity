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
            <div class="form-text">
                <h3>Szczegóły darowizny</h3>
                ${donation.institution.name}<br>
                Przekazywane rzeczy:
                <ul>
                    <c:forEach items="${donation.categories}" var="category">
                        <li>
                                ${category.name}
                        </li>
                    </c:forEach>
                </ul>
                Status:
                <c:choose>
                    <c:when test="${donation.status=='TAKEN'}">
                        odebrane
                    </c:when>
                    <c:otherwise>
                        nieodebrane
                    </c:otherwise>
                </c:choose>
                <br>
                Data utworzenia wpisu: ${donation.orderDate}
                <h3>Adres odbioru</h3>
                Ulica ${donation.street}<br>
                Miasto ${donation.city}<br>
                Kod pocztowy ${donation.zipCode}<br>
                Numer telefonu 123456789<br>

                <h3>Termin odbioru</h3>
                Data ${donation.pickUpDate}<br>
                Godzina ${donation.pickUpTime}<br>
                Uwagi dla kuriera ${donation.pickUpComment}<br>

                <div class="form-group form-group--buttons">
                    <a href="${pageContext.request.contextPath}/edit-donation?id=${donation.id}"
                       class="btn btn--highlighted" type="submit">Edytuj</a>
                    <a href="${pageContext.request.contextPath}/delete-donation?id=${donation.id}"
                       class="btn btn--highlighted" type="submit">Usuń</a>
                </div>
            </div>
        </div>
</header>

<jsp:include page="footer.jsp"/>
</body>
</html>
