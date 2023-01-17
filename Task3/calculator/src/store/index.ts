import { createStore } from 'vuex'

export default createStore({
  state: {
    feedback: {
      name: '',
      email: '',
    }
  },
  getters: {
  },
  mutations: {
    SET_FEEDBACK_INFO(state, payload) {
      state.feedback.name = payload.name
      state.feedback.email = payload.email
    }
  },
  actions: {
    saveFeedbackInfo({ commit }, payload) {
      commit('SET_FEEDBACK_INFO', payload)
    }
  },
  modules: {
  }
})
