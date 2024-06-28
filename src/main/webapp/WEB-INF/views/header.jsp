<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<nav class="container container--70">
    <ul class="nav--actions">
        <sec:authorize access="hasRole('ADMIN')">
            <li class="logged-user">
                Witaj ${loggedUser.username}
                <ul class="dropdown">
                    <li><a href="${pageContext.request.contextPath}/show-institutions">Lista fundacji</a></li>
                    <li><a href="${pageContext.request.contextPath}/show-admins">Lista adminów</a></li>
                    <li><a href="${pageContext.request.contextPath}/show-users">Lista użytkowników</a></li>
                    <li>
                        <form action="${pageContext.request.contextPath}/logout" class="form-logout" method="post">
                            <button class="btn-logout" type="submit">Wyloguj</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="hasRole('USER')">
            <li class="logged-user">
                Witaj ${loggedUser.username}
                <ul class="dropdown">
                    <li><a href="${pageContext.request.contextPath}/edit-my-profile">Edytuj profil</a></li>
                    <li><a href="${pageContext.request.contextPath}/show-donations">Lista darowizn</a></li>
                    <li><a href="${pageContext.request.contextPath}/welcome-page#stats">Moje zbiórki</a></li>
                    <li>
                        <form action="${pageContext.request.contextPath}/logout" method="post">
                            <button class="btn-logout" type="submit">Wyloguj</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </li>
                </ul>
            </li>
        </sec:authorize>
        <div class="logged-out-user">
            <li><a href="${pageContext.request.contextPath}/login" class="btn btn--small btn--without-border">
                Zaloguj</a></li>
            <li><a href="${pageContext.request.contextPath}/register" class="btn btn--small btn--highlighted">
                Załóż konto</a></li>
        </div>
    </ul>

    <ul>
        <li><a href="${pageContext.request.contextPath}/form" class="btn btn--without-border active">Start</a>
        </li>
        <li><a href="${pageContext.request.contextPath}/..#steps" class="btn btn--without-border">O co chodzi?</a>
        </li>
        <li><a href="${pageContext.request.contextPath}/..#aboutUs" class="btn btn--without-border">O nas</a></li>
        <li><a href="${pageContext.request.contextPath}/..#help" class="btn btn--without-border">Fundacje i
            organizacje</a>
        </li>
        <li><a href="${pageContext.request.contextPath}#contact" class="btn btn--without-border">Kontakt</a>
        </li>
    </ul>
</nav>