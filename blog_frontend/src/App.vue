<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-light bg-light">
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/">Home</router-link>
          </li>
          <li v-if="!token" class="nav-item">
            <router-link class="nav-link" to="/register">Register</router-link>
          </li>

          <li class="nav-item">
            <router-link class="nav-link" to="/login">Login</router-link>
          </li>

          <li class="nav-item">
            <a class="nav-link" href="#" @click.prevent="logoutButton">Logout</a>
          </li>

        </ul>
      </div>
    </nav>
    <body>
      <router-view @loginSuccess="updateToken" />
    </body>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from 'vuex';
import Cookies from 'js-cookie'
import jwtDecode from 'jwt-decode';
import router from './router';
export default {
   data() {
      return {
         token: Cookies.get('token')
      }
   },
   
    methods:{
      logoutButton(){
        Cookies.remove('token');
        this.token = Cookies.get('token');
        router.push({name: 'login'});
      },
      updateToken(token) {
        this.token = token;
      }
    },
    watch: {
      token(newToken) {
        this.token = newToken;
      }
    }
}
</script>

<style>

@import url('https://fonts.googleapis.com/css2?family=Century+Gothic&display=swap');
:root {
      font-family: 'Century Gothic', sans-serif;
  }
#app {
  justify-content: center;
  justify-items: center;
  align-items: center;
  align-content: center;
  font-family: 'Century Gothic', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #ffffff;
  margin-top: 10px !important;
  /*background-image: url('@/assets/background.jpg');
  background-size: cover;*/

}

.router-view {
  margin: 0 auto;
}

#nav {
  padding: 10px !important;
}

#nav a{
  font-weight: bold;
  padding: 10px !important;
  color: #2c3e50;
}

#nav a.router-link-exact-active{
  color: #42b983;
}
body{
  justify-content: center;
  justify-items: center;
  align-items: center;
  align-content: center;
  background-color: #ffffff !important;
  width: 100% !important;
  height: 100% !important;
  min-height: 100%;
  background-repeat: repeat;
  background-size: auto;
}
</style>
