<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:choose>
    <c:when test="${modalLabel == 'ログアウト'}">
        <c:set var="formAction" value="/app/account/logout" />
    </c:when>
    <c:when test="${modalLabel == '削除'}">
        <c:set var="formAction" value="/app/account/edit/delete" />
    </c:when>
</c:choose>

<form action="<c:url value='${formAction}' />" method="post">
    <div id="modal-mask" class="modal-mask">
        <div id="modal" class="modal">
            <p class="modal-title">${modalMsg}</p>
            <div class="modal-actions">
                <button type="button" id="cancel-btn" class="cancel-btn">キャンセル</button>
                <div class="partition"></div>
                <button type="submit" id="confirm-btn" class="confirm-btn">
                    ${modalLabel}
                </button>
            </div>
        </div>
    </div>
</form>

