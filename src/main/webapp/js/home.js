import { fetchMovies, fetchSearchMoives } from './api.js'
import { renderMovies } from './render.js'
import { debounce } from './utils.js'

const searchBox = document.querySelector('#search-box')
const search = document.querySelector('#search')

const nowPlaying = document.querySelector('#now-playing')
const popular = document.querySelector('#popular')
const topRated = document.querySelector('#top-rated')
const upcoming = document.querySelector('#upcoming')

const categories = [
    {
        element: nowPlaying,
        endpoint: 'https://api.themoviedb.org/3/movie/now_playing',
    },
    {
        element: popular,
        endpoint: 'https://api.themoviedb.org/3/movie/popular',
    },
    {
        element: topRated,
        endpoint: 'https://api.themoviedb.org/3/movie/top_rated',
    },
    {
        element: upcoming,
        endpoint: 'https://api.themoviedb.org/3/movie/upcoming',
    },
]
/*
トップページAPI取得
================================================ */
const init = async () => {
    try {
        // 並列でfetchMoviesを実行
        const moviesArray = await Promise.all(categories.map((category) => fetchMovies(category.endpoint)))
        // 順番にrenderMoviesを実行
        moviesArray.forEach((movies, i) => {
            renderMovies(movies, categories[i].element)
        })
    } catch (err) {
        console.error('Movie fetch error: ', err)
    }
}

init()
/*
検索
================================================ */
const handleSearchBoxInput = async (e) => {
    try {
        const query = e.target.value.trim()
        if (query.length === 0) {
            search.textContent = ''
            return
        }
        const movies = await fetchSearchMoives(query)
        if (movies.length === 0) {
            search.textContent = `"${query}"に該当する映画は見当たりませんでした`
        } else {
            search.textContent = ''
            renderMovies(movies, search)
        }
    } catch (err) {
        console.error('Search Error', err)
    }
}

searchBox.addEventListener('input', debounce(handleSearchBoxInput, 500))
