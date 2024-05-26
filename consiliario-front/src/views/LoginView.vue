<template>
  <div class="login-container">
    <div class="box">
      <h2 class="title is-3">Login</h2>
      <form @submit.prevent="handleLogin">
        <div class="field">
          <label class="label" for="username">Username</label>
          <div class="control">
            <input class="input" type="text" id="username" v-model="username" required />
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
            <button class="button is-primary" type="submit">Login</button>
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
      username: '',
      password: ''
    }
  },
  methods: {
    handleLogin() {
      // Simula autenticação
      localStorage.setItem('auth', 'true')
      this.$router.push('/')
    }
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}

.box {
  width: 100%;
  max-width: 400px;
  padding: 2rem;
}
</style>
