<template>
 <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-header">Login</div>
          <div class="card-body">
            <div>
                <form @submit.prevent="register" class="needs-validation" novalidate>
                        <div class="form-group">
                        <label>Username</label>
                        <input type="text" v-model="username" class="form-control" required />
                    </div>
                        <div class="form-group">
                        <label>Password</label>
                        <input type="password" v-model="pass" class="form-control" required />
                    </div>
                    <br/>
                        <button type="submit" class="btn btn-primary">Register</button>
                </form>
            </div>
          </div>
            <div class="card-footer text-center">
                Already have an account? <router-link to="/login">Login</router-link>
             </div>
        </div>
      </div>
    </div>
  </div>
  </template>
  
  <script>
  
  export default {
    data() {
      return {
        username: '',
        pass: ''
      }
    },
    methods: {
      register() {

        const registerData = {
          id: 0,
          username: this.username,
          pass: this.pass,
        }


        fetch('http://95.180.97.206:8081/api/service_user/register', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(registerData)
        })
          .then(res => res.json())
          .then(res => {
            if (res.error) {
              console.error(res.error)
            } 
            else {
              console.log('User registered!')
            }})
          .catch(err => console.error(err))
        }
    }
}
</script>

<style>
.needs-validation {
  margin-bottom: 1rem;
}
</style>
         
  