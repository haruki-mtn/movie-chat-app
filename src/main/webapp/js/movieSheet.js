import { fetchBookmarkStatus, fetchMovieDetail } from './api.js'
import { renderMovieDetail } from './render.js'

const movieSheet = document.querySelector('#movie-sheet')
const sheetContent = document.querySelector('#sheet-content')
const sheetCloseBtn = document.querySelector('#sheet-close-btn')
const movieIdHiddenInput = document.querySelector('#movie-id-hidden-input')
const movieTitleHiddenInput = document.querySelector('#movie-title-hidden-input')

const bookmarkToggle = document.querySelector('#bookmark-toggle')
const bookmarkIcon = document.querySelector('#bookmark-icon')
const bookmarkedIcon = document.querySelector('#bookmarked-icon')

const sheetOpenKeyframes = {
    transform: ['translateY(100%)', 'translateY(0)'],
}

const sheetCloseKeyFrames = {
    transform: ['translateY(0)', 'translateY(100%)'],
}

const sheetOptions = {
    duration: 800,
    easing: 'ease',
    fill: 'forwards',
}

let isBookmarked = false;
/*
ボトムシート開閉
================================================ */
const handleMovieCardClick = async (e) => {
    // .movie-cardが親要素になければ早期return
    const movieCard = e.target.closest('.movie-card')
    if (!movieCard) return

    // 編集モード中ならば早期return
    const bookmarkLists = document.querySelector('#bookmark-lists')
    if (bookmarkLists?.classList.contains('editing')) return

    // hiddenフィールドに映画のIDとタイトルをセット
    movieIdHiddenInput.value = movieCard.dataset.movieId
    movieTitleHiddenInput.value = movieCard.dataset.movieTitle

    document.body.classList.add('no-scroll')
    movieSheet.classList.remove('close')
    movieSheet.animate(sheetOpenKeyframes, sheetOptions)
    try {
        // 映画詳細
        const movieId = movieCard.dataset.movieId
        const movieDetail = await fetchMovieDetail(movieId)
        sheetContent.textContent = ''
        renderMovieDetail(movieDetail, sheetContent)
        // ブックマーク状態
        isBookmarked = await fetchBookmarkStatus(movieId)
        updateBookmarkIcon()
    } catch (err) {
        console.error('Movie detail fetch error: ', err)
    }
}

const handleSheetCloseBtnClick = () => {
    document.body.classList.remove('no-scroll')
    movieSheet.classList.add('close')
    movieSheet.animate(sheetCloseKeyFrames, sheetOptions)
}

const updateBookmarkIcon = () => {
    if (isBookmarked) {
        bookmarkedIcon.classList.remove('hidden')
        bookmarkIcon.classList.add('hidden')
    } else {
        bookmarkedIcon.classList.add('hidden')
        bookmarkIcon.classList.remove('hidden')
    }
}

const handleBookmarkToggleClick = async () => {
    const bookmarkLists = document.querySelector('#bookmark-lists')
    const movieId = movieIdHiddenInput.value
    const movieTitle = movieTitleHiddenInput.value
    const action = isBookmarked ? 'remove' : 'add'

    try {
        await fetch(`${CONTEXT_PATH}/app/bookmark`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                action,
                movieId,
                movieTitle,
            }),
        })

        if (action === 'remove' && bookmarkLists) {
            const card = document.querySelector(`.movie-card[data-movie-id="${movieId}"]`)
            card?.classList.add('hidden')
        }

        if (action === 'add' && bookmarkLists) {
            const card = document.querySelector(`.movie-card[data-movie-id="${movieId}"]`)
            card?.classList.remove('hidden')
        }

        isBookmarked = !isBookmarked
        updateBookmarkIcon()

    } catch (err) {
        console.error('Bookmark failed:', err)
    }
}

document.addEventListener('click', handleMovieCardClick)
sheetCloseBtn.addEventListener('click', handleSheetCloseBtnClick)
bookmarkToggle.addEventListener('click', handleBookmarkToggleClick)
