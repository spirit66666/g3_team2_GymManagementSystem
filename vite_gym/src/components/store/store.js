import { createStore } from 'vuex'
import VuexPersistence from 'vuex-persist'
const vuexLocal = new VuexPersistence({
    storage: window.localStorage
})
const store = createStore({
    state () {
    return {
        userID: 1,
        adminID: 1,
        username:"",
        userPassword:"",
        LoggedIn: "登陆",
        adminname: "",

        warningColor:  true,
        authInfo: JSON.parse(sessionStorage.getItem("COMPANY_AUTH_INFO")) || {}
    }
},
    mutations: {
        setAdminID (state, adminID) {
            state.adminID = adminID
        },
        setUserID (state, userID) {
            state.userID = userID
        },
        setUsername(state, username, userPassword) {
            state.username = username
            state.userPassword = userPassword


            sessionStorage.setItem("COMPANY_AUTH_INFO", JSON.stringify(username))
        },
        setLoggedIn(state, loggedIn) {
            state.LoggedIn = loggedIn
        },
        setAdminname(state, adminname) {
            state.adminname = adminname
        }
    },

    getters : {
        authInfo: state => state.authInfo,
    },
    plugins: [vuexLocal.plugin]
    }
)

export default store
