import { createStore } from 'vuex'

const store = createStore({
    state () {
    return {
        username:"",
        userPassword:"",
        LoggedIn: "登陆"
    }
},
    mutations: {
        setUsername(state, username, userPassword) {
            state.username = username
            state.userPassword = userPassword

        },
        setLoggedIn(state, loggedIn) {
            state.LoggedIn = loggedIn
        }
    }
    }
)

export default store
