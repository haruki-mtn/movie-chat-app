<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>404 Not Found</title>
        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css" />
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <main class="main-wrapper not-found-wrapper">
            <h1>404 Not Found</h1>
            <p>お探しのページは存在しません。</p>
            <a href="<c:url value='/' />">トップへ戻る</a>
        </main>
    </body>
</html>

