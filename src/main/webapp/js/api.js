const API_KEY = 'd7af4e91d766fb986344048b9c3767da'
/*
取得
================================================ */
export const fetchMovies = async (endpoint) => {
    const res = await fetch(`${endpoint}?api_key=${API_KEY}&language=ja-JP&page=1`)
    if (!res.ok) throw new Error(`Faild to fetch: ${endpoint}`)
    const data = await res.json()
    return data.results
}

export const fetchSearchMoives = async (query) => {
    const res = await fetch(
        `https://api.themoviedb.org/3/search/movie?api_key=${API_KEY}&language=ja-JP&query=${query}`
    )
    if (!res.ok) throw new Error('Faild to fetch: https://api.themoviedb.org/3/search/movie')
    const data = await res.json()
    return data.results
}

export const fetchMovieDetail = async (movieId) => {
    const res = await fetch(`https://api.themoviedb.org/3/movie/${movieId}?api_key=${API_KEY}&language=ja-JP`)
    if (!res.ok) throw new Error(`Faild to fetch: https://api.themoviedb.org/3/movie/${movieId}`)
    const data = await res.json()
    return data
}

export const fetchBookmarkStatus = async (movieId) => {
    const res = await fetch(`${CONTEXT_PATH}/app/bookmark/status?movieId=${movieId}`)
    if (!res.ok) throw new Error(`Faild to fetch: /app/bookmark/status?movieId=${movieId}`)
    const data = await res.json()
    return data.bookmarked
}
