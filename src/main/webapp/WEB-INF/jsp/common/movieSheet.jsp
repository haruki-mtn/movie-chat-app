<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div id="movie-sheet" class="movie-sheet close">
    <div class="main-wrapper">
        <div class="drag-handle"></div>
        <div class="operation-icons">
            <button type="button" id="sheet-close-btn" class="sheet-close-btn">
                <svg class="icon" viewBox="0 0 24 24" aria-hidden="true">
                    <path
                        fill="currentColor"
                        d="M24,13l0-2-21.445.031L6.877,6.707,5.463,5.293.877,9.879a3,3,0,0,0,0,4.242l4.586,4.586,1.414-1.414L2.615,13.031Z"
                    />
                </svg>
            </button>
            <button type="button" id="bookmark-toggle" class="bookmark-btn">
                <svg id="bookmark-icon" class="icon" viewBox="0 0 24 24" aria-hidden="true">
                    <path
                        fill="currentColor"
                        d="M20.137,24a2.8,2.8,0,0,1-1.987-.835L12,17.051,5.85,23.169a2.8,2.8,0,0,1-3.095.609A2.8,2.8,0,0,1,1,21.154V5A5,5,0,0,1,6,0H18a5,5,0,0,1,5,5V21.154a2.8,2.8,0,0,1-1.751,2.624A2.867,2.867,0,0,1,20.137,24ZM6,2A3,3,0,0,0,3,5V21.154a.843.843,0,0,0,1.437.6h0L11.3,14.933a1,1,0,0,1,1.41,0l6.855,6.819a.843.843,0,0,0,1.437-.6V5a3,3,0,0,0-3-3Z"
                    />
                </svg>
                <svg id="bookmarked-icon" class="icon hidden" viewBox="0 0 24 24" aria-hidden="true">
                    <path
                        fill="currentColor"
                        d="M2.849,23.55a2.954,2.954,0,0,0,3.266-.644L12,17.053l5.885,5.853a2.956,2.956,0,0,0,2.1.881,3.05,3.05,0,0,0,1.17-.237A2.953,2.953,0,0,0,23,20.779V5a5.006,5.006,0,0,0-5-5H6A5.006,5.006,0,0,0,1,5V20.779A2.953,2.953,0,0,0,2.849,23.55Z"
                    />
                </svg>
            </button>
        </div>
        <div id="sheet-content" class="sheet-content"></div>
        <form action="<c:url value='/app/chat' />" method="post">
            <input type="hidden" name="action" value="postChatRoom">
            <input type="hidden" name="movieId" value="" id="movie-id-hidden-input">
            <input type="hidden" name="movieTitle" value="" id="movie-title-hidden-input">
            <button type="submit" class="chat-btn">チャットを開く</button>
        </form>
    </div>
</div>
