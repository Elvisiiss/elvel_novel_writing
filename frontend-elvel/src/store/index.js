// src/store/index.js
import { createStore } from 'vuex'

export default createStore({
    state: {
        user: null,
        isAuthenticated: false
    },
    mutations: {
        SET_USER(state, user) {
            state.user = user
            state.isAuthenticated = !!user
        },
        CLEAR_USER(state) {
            state.user = null
            state.isAuthenticated = false
        },
        UPDATE_USER(state, userData) {
            if (state.user) {
                state.user = { ...state.user, ...userData }
            }
        }
    },
    actions: {
        login({ commit }, user) {
            commit('SET_USER', user)
            localStorage.setItem('user_token', user.token)
            localStorage.setItem('user_data', JSON.stringify(user))
        },
        logout({ commit }) {
            commit('CLEAR_USER')
            localStorage.removeItem('user_token')
            localStorage.removeItem('user_data')
        },
        updateProfile({ commit }, userData) {
            commit('UPDATE_USER', userData)
            localStorage.setItem('user_data', JSON.stringify(userData))
        },
        initializeAuth({ commit }) {
            const token = localStorage.getItem('user_token')
            const userData = localStorage.getItem('user_data')

            if (token && userData) {
                commit('SET_USER', JSON.parse(userData))
            }
        }
    },
    getters: {
        currentUser: state => state.user,
        isAuthenticated: state => state.isAuthenticated
    }
})
