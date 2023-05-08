import Vue from 'vue'
import VueRouter from 'vue-router'

import App from '../App.vue'
import Posts from '../components/Posts.vue'
import PostDetails from '../components/PostDetails.vue'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'

Vue.use(VueRouter)


const routes = [
  {
    path: '/',
    name: 'home',
    component: Posts
  },
  {
    path: '/postDetails',
    name: 'postDetails',
    component: PostDetails
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
