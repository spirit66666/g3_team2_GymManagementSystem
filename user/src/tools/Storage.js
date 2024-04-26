
import Vuex from 'vuex'


//创建VueX对象
const store = new Vuex.Store({
    state:{
        //存放的键值对就是所要管理的状态
        name:''
    },
    mutations: {
        updateUserName (state, account) {
            state.name = account
        }
    }
})

export default store

