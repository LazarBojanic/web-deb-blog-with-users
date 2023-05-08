<template>
    <div class="container">
      <div class="row">
        <div v-for="comment in getComments" :key="comment.id" class="comments">
          <Comment :comment="comment"></Comment>
        </div>
      </div>
      <button class="btn btn-primary" @click="showForm = true">New Comment</button>

      <form v-if="showForm" @submit.prevent="addCommentForm">
        <div class="form-group">
          <label for="body">Author</label>
          <textarea class="form-control" id="author" v-model="newComment.author"></textarea>
        </div>
        <div class="form-group">
          <label for="body">Content</label>
          <textarea class="form-control" id="content" rows="5" v-model="newComment.content"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Add Comment</button>
      </form>
    </div>
  </template>
  
  <script>
  import Comment from '@/components/Comment.vue'
  import { mapState, mapActions, mapGetters } from "vuex";
  import Cookies from 'js-cookie';
  import jwtDecode from 'jwt-decode';
  export default {
    name: 'Comments',
    components: {
      Comment
    },
    props: {
        postId: Number,
    },
    data() {
      return {
        showForm: false,
        newComment: {
          id: -1,
          blog_post_id: -1,
          author: '',
          content: ''
        }
      }
    },
    computed: {
        ...mapGetters([ 'getComments' ]),
        ...mapGetters([ 'getPost' ])
    },
    mounted() {
      this.fetchComments(this.getPost.id);
      const decodedToken = jwtDecode(Cookies.get('token'));
      console.log(decodedToken);
      if(decodedToken != null){
        this.newComment.author = decodedToken.username; 
      }
    },
    methods: {
      ...mapActions(["fetchComments"]),
      ...mapActions(["addComment"]),

      addCommentForm(){
        this.newComment.id = this.getPost.id;
        this.addComment(this.newComment).then(async () => {
          this.newComment.id = -1,
          this.newComment.blog_post_id = this.getPost.id;
          this.newComment.author = '';
          this.newComment.content = '';
          this.showForm = false;
          await this.fetchComments(this.getPost.id);
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
    .comments{
      display: block;
    }
  </style>