<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>ブックマーク | Echoes</title>
        <link rel="icon" type="image/png" href="<c:url value='/images/favicon-96x96.png' />" sizes="96x96" />
        <link rel="icon" type="image/svg+xml" href="<c:url value='/images/favicon.svg' />" />
        <link rel="shortcut icon" href="<c:url value='/images/favicon.ico' />" />
        <link rel="apple-touch-icon" sizes="180x180" href="<c:url value='/images/apple-touch-icon.png' />" />
        <meta name="apple-mobile-web-app-title" content="Echoes" />
        <link rel="manifest" href="<c:url value='/images/site.webmanifest' />" />
        <link rel="stylesheet" href="<c:url value='/css/ress.min.css' />" />
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
        <script type="module" src="<c:url value='/js/common.js' />"></script>
    </head>
    <body>
        <form action="<c:url value='/app/bookmark' />" method="post" id="bookmarks-form">
            <input type="hidden" name="action" value="multipleRemove">
            <jsp:include page="common/header.jsp" />

            <script>
                const BOOKMARK_MOVIE_IDS = [
                    <c:forEach var="id" items="${movieIdList}" varStatus="st">
                        ${id}<c:if test="${!st.last}">,</c:if>
                    </c:forEach>
                ];
                const CONTEXT_PATH = '${pageContext.request.contextPath}';
            </script>

            <main class="main-wrapper">
                <c:choose>
                    <c:when test="${not empty movieIdList}">
                        <section class="bookmark-section">
                            <div class="lists" id="bookmark-lists"></div>
                        </section>
                    </c:when>
                    <c:otherwise>
                        <p class="hint-text">ブックマークをすると作品が表示されます</p>
                    </c:otherwise>
                </c:choose>
            </main>
        </form>

        <jsp:include page="common/bottomNav.jsp" />

        <jsp:include page="common/movieSheet.jsp" />

        <script type="module" src="<c:url value='/js/bookmark.js' />"></script>
        <script type="module" src="<c:url value='/js/movieSheet.js' />"></script>
    </body>
</html>
