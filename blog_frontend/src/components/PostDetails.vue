<template>
    <div>
      <div class="postDetails">     
        <h1>{{ this.getPost.title }}</h1>
        <h3>{{ formatDate(this.getPost.date_published) }}</h3>
        <h3>{{ this.getPost.author }}</h3>
        <p>{{ this.getPost.content }}</p>
        <br/>
        <Comments :postId = "this.getPost.id"/>
      </div>
    </div>
</template>

<script>
  import { mapState, mapActions, mapGetters } from 'vuex';
  import Comments from "../components/Comments.vue"
    export default {
      name: 'PostDetails',
      
      components: {
        Comments
      },
      props: {
      },
      data() {
        return {
        }
      },
      mounted() {
      },
      methods: {
        formatDate(dateString) {
          const date = new Date(dateString);
          return date.toLocaleDateString();
        }
      },
      computed: {
        ...mapGetters([ 'getPost' ])
      }
    }
  
  </script>
  
  <style scoped>
    .postDetails {
      margin-top: 25px;
      border-style: solid;
      border-color: black;
      border-radius: 3px;
      font-size: 1.5em;
      color: rgb(0, 0, 0);
      font-weight: bold;
      width: auto;
      height: 20vh;
      font-family: 'Century Gothic', sans-serif; 
      background-color: rgb(255, 255, 255);
      display: block;
  }

  </style>