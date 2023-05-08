<template>
    <div>
      <div class="comment">     
        <h1>{{ comment.author }}</h1>
        <p>{{ comment.content }}</p>       
      </div>
    </div>
</template>

<script>
  import { mapState, mapActions, mapGetters } from 'vuex';
    export default {
      name: 'Comment',
      
      props: {
        comment: Object
      },
      data() {
        return {
        }
      },
      mounted() {

      },
      methods: {

      }
    }
  
  </script>
  
  <style scoped>
    .comment {
      margin-top: 25px;
      border-style: solid;
      border-color: black;
      border-radius: 3px;
      font-size: 1.5em;
      color: rgb(0, 0, 0);
      font-weight: bold;
      width: 100%;
      height: 20vh;
      font-family: 'Century Gothic', sans-serif; 
      background-color: rgb(255, 255, 255);
      display: block;
  }

  </style>