/*
入力時間などを考慮して遅延させる
================================================ */
export const debounce = (fn, delay) => {
    let timer
    return function (...args) {
        clearTimeout(timer)
        timer = setTimeout(() => fn.apply(this, args), delay)
    }
}
/*
URLからidを取得する
================================================ */
// export const getMovieIdFromURL = () => {
//     const params = new URLSearchParams(window.location.search)
//     return params.get('id')
// }
