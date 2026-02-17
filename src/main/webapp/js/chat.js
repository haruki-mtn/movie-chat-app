const questionToggleBtn = document.querySelector('#question-toggle-btn')
const isQuestionHiddenInput = document.querySelector('#is-question-hidden-input')
const chatInput = document.querySelector('#chat-input')

let isQuestion = false

const handleQuestionToggleBtnClick = () => {
    isQuestion = !isQuestion
    isQuestionHiddenInput.value = isQuestion
    chatInput.classList.toggle('question-mode', isQuestion)
    questionToggleBtn.classList.toggle('active', isQuestion)
}

if (questionToggleBtn) {
    questionToggleBtn.addEventListener('click', handleQuestionToggleBtnClick)
}
