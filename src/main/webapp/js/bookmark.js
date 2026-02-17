import { fetchMovieDetail } from './api.js'
import { renderBookmarks } from './render.js'

const bookmarksForm = document.querySelector('#bookmarks-form')
const bookmarksToggle = document.querySelector('#bookmarks-toggle')
const bookmarksIcon = document.querySelector('#bookmarks-icon')
const complete = document.querySelector('#complete')

const bookmarkLists = document.querySelector('#bookmark-lists')

let isEdit = false;
/*
編集ボタン（ブックマークページのヘッダー）
================================================ */
const handleBookmarksToggleClick = () => {
    if (!complete || !bookmarksIcon || !bookmarkLists) return
    if (!isEdit) {
        complete.classList.remove('hidden')
        bookmarksIcon.classList.add('hidden')

        bookmarkLists.classList.add('editing')
        isEdit = true
    } else {
        bookmarksForm.submit()
    }
}
/*
ブックマーク作品のAPI取得
================================================ */
const loadBookmarks = async () => {
    if (!bookmarkLists) return
    try {
        const movies = await Promise.all(BOOKMARK_MOVIE_IDS.map((movieId) => fetchMovieDetail(movieId)))
        bookmarkLists.textContent = ''
        renderBookmarks(movies, bookmarkLists)
    } catch (err) {
        console.error('Movie fetch error: ', err)
    }
}

loadBookmarks()

bookmarksToggle.addEventListener('click', handleBookmarksToggleClick)
