<template>
    <div class="container">
      <div class="row">
        <div v-for="post in currentPagePosts" :key="post.id" class="posts">
          <Post :post="post"></Post>
        </div>
      </div>
      <br/>
      <button class="btn btn-primary" @click="showForm = true">New Post</button>

      <form v-if="showForm" @submit.prevent="addPostForm">
        <div class="form-group">
          <label for="title">Title</label>
          <input type="text" class="form-control" id="title" v-model="newPost.title" />
        </div>
        <div class="form-group">
          <label for="body">Author</label>
          <textarea class="form-control" id="author" v-model="newPost.author"></textarea>
        </div>
        <div class="form-group">
          <label for="body">Content</label>
          <textarea class="form-control" id="content" rows="5" v-model="newPost.content"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Add Post</button>
      </form>


      <nav aria-label="Page navigation example">
        <ul class="pagination">
          <li class="page-item" :class="{'disabled': currentPage === 1}">
            <a class="page-link" href="#" @click.prevent="prevPage">Previous</a>
          </li>
          <li class="page-item" :class="{'active': currentPage === page}" v-for="page in pages" :key="page">
            <a class="page-link" href="#" @click.prevent="goToPage(page)">{{ page }}</a>
          </li>
          <li class="page-item" :class="{'disabled': currentPage === pages.length}">
            <a class="page-link" href="#" @click.prevent="nextPage">Next</a>
          </li>
        </ul>
      </nav>
    </div>
  </template>
  
  <script>
  import Post from '@/components/Post.vue'
  import { mapState, mapActions, mapGetters } from "vuex";
  import Cookies from 'js-cookie';
  import jwtDecode from 'jwt-decode';
  export default {
    name: 'Posts',
    components: {
        Post
    },
    data() {
      return {
        showForm: false,
        currentPage: 1,
        postsPerPage: 20,
        searchText: '',
        newPost: {
          id: -1,
          title: '',
          date_published: new Date().toISOString().slice(0, 10),
          author: '',
          content: ''
        }
      }
    },
    computed: {
        ...mapGetters([ 'getPosts' ]),
      currentPagePosts() {
        const start = (this.currentPage - 1) * this.postsPerPage;
        return Object.values(this.getPosts).slice(start, start + this.postsPerPage);
      },
      pages() {
        return Array.from({ length: Math.ceil(Object.values(this.getPosts).length / this.postsPerPage) }, (_, i) => i + 1);
      }
    },
    mounted() {
      this.fetchPosts();
      const decodedToken = jwtDecode(Cookies.get('token'));
      console.log(decodedToken);
      if(decodedToken != null){
        this.newPost.author = decodedToken.username;
      }
    },
    methods: {
      ...mapActions(["fetchPosts"]),
      ...mapActions(["addPost"]),
      goToPage(page) {
        this.currentPage = page
      },
      prevPage() {
        if (this.currentPage > 1) {
          this.currentPage--
        }
      },
      nextPage() {
        if (this.currentPage < this.pages.length) {
          this.currentPage++
        }
      },
      async addPostForm(){
        await this.addPost(this.newPost).then(async () => {
          this.newPost.id = -1,
          this.newPost.title = '';
          this.newPost.date_published = new Date().toISOString().slice(0, 10);
          this.newPost.author = '';
          this.newPost.content = '';
          this.showForm = false;
          await this.fetchPosts();
        });
      }
    }
  }
  </script>
  <style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Century+Gothic&display=swap');
  :root {
        font-family: 'Century Gothic', sans-serif;
    }
    .posts{
      justify-content: center;
      justify-items: center;
      align-items: center;
      align-content: center;
      display: block;
    }
    .navbar {
      border: 3px solid rgb(59, 15, 110) !important;
      padding: 10px !important;
      margin: auto !important;
      background-color: rgba(70, 19, 165, 0.247) !important;
      border: 0 !important;
      font-family: 'Century Gothic', sans-serif !important;
    }
    .navbar-nav{
      display: flex !important;
      justify-content: center !important;
    }
  </style>