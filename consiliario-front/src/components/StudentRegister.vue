<template>
  <div class="register-container">
    <div class="box">
      <h2 class="title is-3">Student Registration</h2>
      <form @submit.prevent="handleRegister">
        <div class="field">
          <label class="label" for="name">Name</label>
          <div class="control">
            <input class="input" type="text" id="name" v-model="name" required />
          </div>
        </div>
        <div class="field">
          <label class="label" for="registration">Registration</label>
          <div class="control">
            <input class="input" type="text" id="registration" v-model="registration" required />
          </div>
        </div>
        <div class="field">
          <label class="label" for="password">Password</label>
          <div class="control">
            <input class="input" type="password" id="password" v-model="password" required />
          </div>
        </div>
        <div class="field">
          <div class="control">
            <button class="button is-primary" type="submit">Register</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: '',
      registration: '',
      password: ''
    }
  },
  methods: {
    handleRegister() {
      // Lógica para registro de estudantes
      console.log('Name:', this.name)
      console.log('Registration:', this.registration)
      console.log('Password:', this.password)
    }
  }
}
</script>

<style scoped>
.register-container {
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 8px;
}
.box {
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
}
</style>
