<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>チャット | Echoes</title>
        <link rel="icon" type="image/png" href="<c:url value='/images/favicon-96x96.png' />" sizes="96x96" />
        <link rel="icon" type="image/svg+xml" href="<c:url value='/images/favicon.svg' />" />
        <link rel="shortcut icon" href="<c:url value='/images/favicon.ico' />" />
        <link rel="apple-touch-icon" sizes="180x180" href="<c:url value='/images/apple-touch-icon.png' />" />
        <meta name="apple-mobile-web-app-title" content="Echoes" />
        <link rel="manifest" href="<c:url value='/images/site.webmanifest' />" />
        <link rel="stylesheet" href="<c:url value='/css/ress.min.css' />" />
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
        <script type="module" src="<c:url value='/js/common.js' />"></script>
        <script type="module" src="<c:url value='/js/chat.js' />"></script>
    </head>
    <body>
        <jsp:include page="common/flashMsg.jsp" />

        <jsp:include page="common/header.jsp" />

        <main class="main-wrapper">
            <c:choose>
                <c:when test="${not empty chatRoom}">
                    <h2 class="chat-heading">${movie.title}</h2>
                    <div class="chat-wrapper">
                        <c:forEach var="chatMessage" items="${chatMessages}">
                            <div class="chat-message ${chatMessage.isQuestion ? 'question' : ''}">
                                <span class="user">${chatMessage.userName}</span>
                                <p>${chatMessage.content}</p>
                                <span class="time">${chatMessage.formattedCreatedAt}</span>
                            </div>
                        </c:forEach>
                    </div>
                    <form action="<c:url value='/app/chat' />" method="post" id="chat-form">
                        <input type="hidden" name="action" value="postChatContent">
                        <input type="hidden" name="isQuestion" value="false" id="is-question-hidden-input">
                        <div class="chat-input-wrapper">
                            <button type="button" id="question-toggle-btn" class="question-btn">疑問</button>
                            <input type="text" name="chatContent" placeholder="感想や疑問を投稿できます" class="chat-input" id="chat-input">
                            <button type="submit" class="chat-submit-btn">投稿</button>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <p class="hint-text">「チャットを開く」を押すとチャットができます</p>
                </c:otherwise>
            </c:choose>
        </main>

        <jsp:include page="common/bottomNav.jsp" />
    </body>
</html>

