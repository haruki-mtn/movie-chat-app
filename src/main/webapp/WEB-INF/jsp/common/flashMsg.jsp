<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${not empty flashMsg}">
    <div id="flash" class="flash ${flashType}">
        ${flashMsg}
    </div>
</c:if>
