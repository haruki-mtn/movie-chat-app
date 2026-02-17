<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<nav class="bottom-nav">
    <a href="<c:url value='/app/home' />" class="${activeNav == 'home' ? 'active' : ''}">
        <svg class="icon" viewBox="0 0 24 24" aria-hidden="true">
            <path
                fill="currentColor"
                d="M23.121,9.069,15.536,1.483a5.008,5.008,0,0,0-7.072,0L.879,9.069A2.978,2.978,0,0,0,0,11.19v9.817a3,3,0,0,0,3,3H21a3,3,0,0,0,3-3V11.19A2.978,2.978,0,0,0,23.121,9.069ZM15,22.007H9V18.073a3,3,0,0,1,6,0Zm7-1a1,1,0,0,1-1,1H17V18.073a5,5,0,0,0-10,0v3.934H3a1,1,0,0,1-1-1V11.19a1.008,1.008,0,0,1,.293-.707L9.878,2.9a3.008,3.008,0,0,1,4.244,0l7.585,7.586A1.008,1.008,0,0,1,22,11.19Z"
            />
        </svg>
        <span class="label">ホーム</span>
    </a>
    <a href="<c:url value='/app/chat' />" class="${activeNav == 'chat' ? 'active' : ''}">
        <svg class="icon" viewBox="0 0 24 24" aria-hidden="true">
            <path
                fill="currentColor"
                d="M24,16v8H16a8,8,0,0,1-6.92-4,10.968,10.968,0,0,0,2.242-.248A5.988,5.988,0,0,0,16,22h6V16a5.988,5.988,0,0,0-2.252-4.678A10.968,10.968,0,0,0,20,9.08,8,8,0,0,1,24,16ZM18,9A9,9,0,0,0,0,9v9H9A9.01,9.01,0,0,0,18,9ZM2,9a7,7,0,1,1,7,7H2Z"
            />
        </svg>
        <span class="label">チャット</span>
    </a>
    <a href="<c:url value='/app/bookmark' />" class="${activeNav == 'bookmark' ? 'active' : ''}">
        <svg class="icon" viewBox="0 0 24 24" aria-hidden="true">
            <path
                fill="currentColor"
                d="M20.137,24a2.8,2.8,0,0,1-1.987-.835L12,17.051,5.85,23.169a2.8,2.8,0,0,1-3.095.609A2.8,2.8,0,0,1,1,21.154V5A5,5,0,0,1,6,0H18a5,5,0,0,1,5,5V21.154a2.8,2.8,0,0,1-1.751,2.624A2.867,2.867,0,0,1,20.137,24ZM6,2A3,3,0,0,0,3,5V21.154a.843.843,0,0,0,1.437.6h0L11.3,14.933a1,1,0,0,1,1.41,0l6.855,6.819a.843.843,0,0,0,1.437-.6V5a3,3,0,0,0-3-3Z"
            />
        </svg>
        <span class="label">ブックマーク</span>
    </a>
    <a href="<c:url value='/app/account' />" class="${activeNav == 'account' ? 'active' : ''}">
        <svg class="icon" viewBox="0 0 24 24" aria-hidden="true">
            <path
                fill="currentColor"
                d="M12,12A6,6,0,1,0,6,6,6.006,6.006,0,0,0,12,12ZM12,2A4,4,0,1,1,8,6,4,4,0,0,1,12,2Z"
            />
            <path
                fill="currentColor"
                d="M12,14a9.01,9.01,0,0,0-9,9,1,1,0,0,0,2,0,7,7,0,0,1,14,0,1,1,0,0,0,2,0A9.01,9.01,0,0,0,12,14Z"
            />
        </svg>
        <span class="label">アカウント</span>
    </a>
</nav>
