<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>ホーム | Echoes</title>
        <link rel="icon" type="image/png" href="<c:url value='/images/favicon-96x96.png' />" sizes="96x96" />
        <link rel="icon" type="image/svg+xml" href="<c:url value='/images/favicon.svg' />" />
        <link rel="shortcut icon" href="<c:url value='/images/favicon.ico' />" />
        <link rel="apple-touch-icon" sizes="180x180" href="<c:url value='/images/apple-touch-icon.png' />" />
        <meta name="apple-mobile-web-app-title" content="Echoes" />
        <link rel="manifest" href="<c:url value='/images/site.webmanifest' />" />
        <link rel="stylesheet" href="<c:url value='/css/ress.min.css' />" />
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
        <script type="module" src="<c:url value='/js/home.js' />"></script>
        <script type="module" src="<c:url value='/js/common.js' />"></script>
    </head>
    <body>
        <jsp:include page="common/flashMsg.jsp" />

		<jsp:include page="common/header.jsp" />

        <div class="app-container">
            <section class="search-section">
                <h2>映画を探す</h2>
                <input id="search-box" type="text" placeholder="映画を検索できます" />
                <div class="slides" id="search"></div>
                <hr />
            </section>

            <section class="slide-section">
                <h2>上映中の映画</h2>
                <div class="slides" id="now-playing"></div>
                <hr />
            </section>

            <section class="slide-section">
                <h2>人気の映画</h2>
                <div class="slides" id="popular"></div>
                <hr />
            </section>

            <section class="slide-section">
                <h2>評価の高い映画</h2>
                <div class="slides" id="top-rated"></div>
                <hr />
            </section>

            <section class="slide-section">
                <h2>上映予定の映画</h2>
                <div class="slides" id="upcoming"></div>
                <hr />
            </section>

            <footer class="site-footer">
                <p><small>&copy; 2026 Haruki Yamada. Created at HAL Tokyo.</small></p>
            </footer>
        </div>

        <jsp:include page="common/bottomNav.jsp" />

        <jsp:include page="common/movieSheet.jsp" />
        <script>
            const CONTEXT_PATH = '${pageContext.request.contextPath}';
        </script>
        <script type="module" src="<c:url value='/js/movieSheet.js' />"></script>
    </body>
</html>

