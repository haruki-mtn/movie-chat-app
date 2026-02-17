/*
スクロールされたらヘッダーにshadowをつける
================================================ */
const siteHeader = document.querySelector('#site-header')

const handlePageScroll = () => {
    if (scrollY > 0) {
        siteHeader.classList.add('scrolled')
    } else {
        siteHeader.classList.remove('scrolled')
    }
}

window.addEventListener('scroll', handlePageScroll)
/*
確認モーダル
================================================ */
const openModal = document.querySelector('#open-modal')
const modalMask = document.querySelector('#modal-mask')
const modal = document.querySelector('#modal')
const cancelBtn = document.querySelector('#cancel-btn')

const modalShowKeyframes = {
    opacity: [0, 1],
    visibility: 'visible',
}
const modalHideKeyframes = {
    opacity: [1, 0],
    visibility: 'hidden',
}
const modalShowOptions = {
    duration: 800,
    easing: 'ease',
    fill: 'forwards',
}
const modalHideOptions = {
    duration: 100,
    easing: 'ease',
    fill: 'forwards',
}

const handleOpenModalClick = () => {
    modal.animate(modalShowKeyframes, modalShowOptions)
    modalMask.animate(modalShowKeyframes, modalShowOptions)
}

const handleCloseModalClick = () => {
    modal.animate(modalHideKeyframes, modalHideOptions)
    modalMask.animate(modalHideKeyframes, modalHideOptions)
}

if (modal) {
    openModal.addEventListener('click', handleOpenModalClick)
    cancelBtn.addEventListener('click', handleCloseModalClick)
    modalMask.addEventListener('click', (e) => {
        if (e.target === modalMask) {
            handleCloseModalClick()
        }
    })
}
/*
フラッシュメッセージ
================================================ */
const flash = document.querySelector('#flash')

const flashShowKeyframes = {
    opacity: [0, 1],
    visibility: 'visible',
}

const flashHideKeyframes = {
    opacity: [1, 0],
    visibility: 'hidden',
}

const flashShowOptions = {
    duration: 500,
    easing: 'ease',
    fill: 'forwards',
}

const flashHideOptions = {
    delay: 2000,
    duration: 1000,
    easing: 'ease',
    fill: 'forwards',
}

if (flash) {
    flash.animate(flashShowKeyframes, flashShowOptions)
    flash.animate(flashHideKeyframes, flashHideOptions)
}
