<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>アカウント | Echoes</title>
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
        <jsp:include page="common/header.jsp" />

        <main class="main-wrapper">
            <section class="account-info">
                <div class="profile">
                    <img src="" class="profile-image" alt="プロフィール画像" />
                    <p>${loginUser.name}</p>
                </div>
                <div class="activity">
                    <p class="bookmark-count">
                        <span>ブックマーク数</span>
                        <br />
                        ${bookmarkCount}
                    </p>
                    <p class="chat-count">
                        <span>総投稿数</span>
                        <br />
                        ${messagesCount}
                    </p>
                </div>
                <hr />
            </section>
            <section class="account-ctrl">
                <a href="<c:url value='/app/account/edit' />" class="item">
                    アカウント
                    <svg class="icon" viewBox="0 0 24 24" aria-hidden="true">
                        <path
                            fill="currentColor"
                            d="M7.412,24,6,22.588l9.881-9.881a1,1,0,0,0,0-1.414L6.017,1.431,7.431.017l9.862,9.862a3,3,0,0,1,0,4.242Z"
                        />
                    </svg>
                </a>
                <hr />
                <button id="open-modal" class="item">
                    <span>
                        ログアウト
                        <br />
                        <span class="sub-text">現在のユーザーからログアウトします</span>
                    </span>
                    <svg class="icon" viewBox="0 0 24 24" aria-hidden="true">
                        <path
                            fill="currentColor"
                            d="M7.412,24,6,22.588l9.881-9.881a1,1,0,0,0,0-1.414L6.017,1.431,7.431.017l9.862,9.862a3,3,0,0,1,0,4.242Z"
                        />
                    </svg>
                </button>
            </section>
        </main>

        <jsp:include page="common/confirmModal.jsp" />

        <jsp:include page="common/bottomNav.jsp" />
    </body>
</html>

