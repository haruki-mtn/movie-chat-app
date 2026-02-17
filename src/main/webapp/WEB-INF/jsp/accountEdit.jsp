<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>アカウント編集 | Echoes</title>
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
        <main class="main-wrapper">
            <header class="account-header">
                <a href="<c:url value='/app/account' />">
                    <svg class="icon" viewBox="0 0 24 24" aria-hidden="true">
                        <path
                            fill="currentColor"
                            d="M24,13l0-2-21.445.031L6.877,6.707,5.463,5.293.877,9.879a3,3,0,0,0,0,4.242l4.586,4.586,1.414-1.414L2.615,13.031Z"
                        />
                    </svg>
                </a>
                <h1>アカウント編集</h1>
            </header>

            <section class="account-ctrl">
                <a href="" class="item">
                    プロフィール画像を設定・変更
                    <svg class="icon" viewBox="0 0 24 24" aria-hidden="true">
                        <path
                            fill="currentColor"
                            d="M7.412,24,6,22.588l9.881-9.881a1,1,0,0,0,0-1.414L6.017,1.431,7.431.017l9.862,9.862a3,3,0,0,1,0,4.242Z"
                        />
                    </svg>
                </a>
                <a href="" class="item">
                    ユーザー名を変更
                    <svg class="icon" viewBox="0 0 24 24" aria-hidden="true">
                        <path
                            fill="currentColor"
                            d="M7.412,24,6,22.588l9.881-9.881a1,1,0,0,0,0-1.414L6.017,1.431,7.431.017l9.862,9.862a3,3,0,0,1,0,4.242Z"
                        />
                    </svg>
                </a>
                <a href="" class="item">
                    メールアドレスを変更
                    <svg class="icon" viewBox="0 0 24 24" aria-hidden="true">
                        <path
                            fill="currentColor"
                            d="M7.412,24,6,22.588l9.881-9.881a1,1,0,0,0,0-1.414L6.017,1.431,7.431.017l9.862,9.862a3,3,0,0,1,0,4.242Z"
                        />
                    </svg>
                </a>
                <a href="" class="item">
                    パスワードを変更
                    <svg class="icon" viewBox="0 0 24 24" aria-hidden="true">
                        <path
                            fill="currentColor"
                            d="M7.412,24,6,22.588l9.881-9.881a1,1,0,0,0,0-1.414L6.017,1.431,7.431.017l9.862,9.862a3,3,0,0,1,0,4.242Z"
                        />
                    </svg>
                </a>
                <hr />
                <button id="open-modal" class="item">
                    <span class="account-remove">
                        アカウントを削除
                        <br />
                        <span class="sub-text">アカウントを完全に削除します</span>
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

