import { createStore } from 'vuex'

const Store = createStore({
    state() {
        return {
            userName: "",
            userPassword: "",
        }
    },

    mutations: {
        clearUserInfo(state) {
            state.userName = "";
        },
        registUserInfo(state, {name, password}) {
            state.userName = name;
            state.password = password;
        },
        GetterExample(state) {
            console.log(state.userName)
        },
        setUsername(state, username) {
            state.username = username;
        }
    },
    actions: {

    },
    getters1: {
        getUsername: state => state.username


    },


})
const moduleA = {
    namespaced: true,
    state: {
        count: 0
    },
    mutations: {
        increment(state) {
            state.count++
        }
}}
Store.registerModule('moduleA', moduleA)
export default Store;