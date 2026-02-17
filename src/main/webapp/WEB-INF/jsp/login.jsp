<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>ログイン | Echoes</title>
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
        <jsp:include page="common/flashMsg.jsp" />

        <main class="main-wrapper card-wrapper">
            <div class="card">
                <h1>ログイン</h1>
                <form action="<c:url value='/login' />" method="post" class="login-register-form">
                    <input class="form-input" type="email" name="mail" placeholder="メールアドレス" />
                    <input class="form-input" type="password" name="pass" placeholder="パスワード" />
                    <button class="form-btn" type="submit">ログイン</button>
                </form>
                <p>または</p>
                <div class="google">Googleで続ける</div>
                <p>アカウントをお持ちでないですか？</p>
            </div>
            <a href="<c:url value='/register' />" class="link-btn">新規登録</a>
        </main>
    </body>
</html>
