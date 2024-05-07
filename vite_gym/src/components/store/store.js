import { createStore } from 'vuex'

const store = createStore({
    state () {
    return {
        username:"",
        userPassword:"",
    }
},
    mutations: {
        setUsername(state, username) {
            state.username = username}
    }
    }
)

export default store
