import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    count: 0,
    username: '',
    token: ''
  },
  mutations: {
    // 在存入cookie中的同时把用户名存到全局状态管理器中
    fSetUserName (state, name) {
      state.username = name
    },
    fSetToken (state, tokenData) {
      state.token = tokenData
    }
  },
  actions: {
    addAsync (context, step) {
      setTimeout(() => {
        context.commit('addN', step)
      }, 1000)
    }
  },
  getters: {
    showNum (state) {
      return '当前最新的数量[' + state.count + ']'
    }
  }
})
