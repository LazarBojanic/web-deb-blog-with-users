<template>
    <div>
      <div class="post" @click="navigateToPostDetailsPage" @mousedown="clicked = true" @mouseup="clicked = false" :class="{'clicked': clicked }">     
        <h1>{{ post.title }}</h1>
        <br/>
        <p>{{ post.content }}</p>       
      </div>
    </div>
</template>

<script>
  import { mapState, mapActions, mapGetters } from 'vuex';
    export default {
      name: 'Post',
      
      props: {
        post: Object
      },
      data() {
        return {
          clicked: false
        }
      },
      mounted() {

      },
      methods: {
        ...mapActions(['fetchPost']),
        navigateToPostDetailsPage() {
          this.fetchPost(this.post.id).then(() => {
            this.$router.push({ name: 'postDetails' });
          });
        }
      }
    }
  
  </script>
  
  <style scoped>
    .post {
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


  .post.clicked {
    transform: scale(0.95); /* Scale down the card slightly */
    border-color: #007bff; /* Change border color */
  }

  </style>