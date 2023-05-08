import Vue from 'vue'
import Vuex from 'vuex'
import Cookies from 'js-cookie'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    posts: {},
    post: {},
    comments: {}
  },
  getters: {
    getPosts: state => state.posts,
    getPost: state => state.post,
    getComments: state => state.comments
  },
  mutations: {
    setPosts(state, posts){
      state.posts = posts
    },
    setPost(state, post){
      state.post = post
    },
    setComments(state, comments){
      state.comments = comments
    }
  },
  actions: {
    fetchPost({ commit }, id) {
      const token = Cookies.get('token') || 'none'
      return fetch(`http://95.180.97.206:8081/api/blog_post/get/${id}`, {
        method: "GET",
        headers: {
            'authorization': `Bearer ${token}`
        }})
        .then(res => res.json())
        .then(post => {
          commit('setPost', post);
        });
    },
    fetchPosts({ commit }) {
      const token = Cookies.get('token') || 'none'
      return fetch('http://95.180.97.206:8081/api/blog_post/getAll', {
        method: "GET",
        headers: {
            'authorization': `Bearer ${token}`
        }})
        .then(res => res.json())
        .then(posts => {
          commit('setPosts', posts);
        });
    },
    fetchComments({ commit }, blogPostId) {
      const token = Cookies.get('token') || 'none'
      return fetch(`http://95.180.97.206:8081/api/blog_post_comment/getAll/${blogPostId}`, {
        method: "GET",
        headers: {
            'authorization': `Bearer ${token}`
        }})
        .then(res => res.json())
        .then(comments => {
          commit('setComments', comments);
        });
    },
    addPost( {commit}, post ){
      const token = Cookies.get('token') || 'none'
      return fetch('http://95.180.97.206:8081/api/blog_post/add', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'authorization': `Bearer ${token}`
        },
        body: JSON.stringify(post)
        })
        .then(res => res.json());
    },
    addComment( {commit}, blogPostComment ){
      const token = Cookies.get('token') || 'none'
      const id = blogPostComment.id;
      return fetch(`http://95.180.97.206:8081/api/blog_post_comment/add/${id}`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'authorization': `Bearer ${token}`
        },
        body: JSON.stringify(blogPostComment)
        })
        .then(res => res.json());
    }
  },
  modules: {
  }
})
