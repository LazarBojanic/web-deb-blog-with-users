<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-header">Login</div>
          <div class="card-body">
            <form @submit.prevent="submitForm">
              <div class="form-group">
                <label>Username:</label>
                <input class="form-control" type="text" v-model="username" />
              </div>
              <div class="form-group">
                <label>Password:</label>
                <input class="form-control" type="password" v-model="pass" />
              </div>
              <br/>
              <button class="btn btn-primary" type="submit">Login</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Cookies from 'js-cookie'
export default {
  data() {
    return {
      username: "",
      pass: ""
    };
  },
  methods: {
    async submitForm() {

      const loginData = {
          id: 0,
          username: this.username,
          pass: this.pass,
      }

      const res = await fetch("http://95.180.97.206:8081/api/service_user/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(loginData),
      });
      const data = await res.json();
      if (data.token) {
        console.log(data.token);
        Cookies.set('token', data.token);
        this.$emit('loginSuccess', data.token);
        this.$router.push({ name: 'home' });
      }
    },
  },
};
</script>