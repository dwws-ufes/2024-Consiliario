<template>
  <div class="register-container">
    <div class="box">
      <h2 class="title is-3">Register Teacher</h2>
      <form @submit.prevent="handleRegister">
        <div class="field">
          <label class="label" for="email">Email</label>
          <div class="control">
            <input class="input" type="email" id="email" v-model="email" required />
          </div>
        </div>
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
            <button class="button is-primary" type="submit">Register</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from '@/axios';

export default {
  data() {
    return {
      email: '',
      username: '',
      password: '',
    };
  },
  methods: {
    async handleRegister() {
      try {
        const payload = {
          email: this.email,
          username: this.username,
          password: this.password,
        };
        const response = await axios.post('http://localhost:8080/register', payload);
        if (response.status === 200) {
          this.$router.push('/login');
        } else {
          alert('Registration failed. Please try again.');
        }
      } catch (error) {
        console.error('Error during registration:', error);
        alert('Registration failed. Please try again.');
      }
    }
  }
};
</script>

<style scoped>
.register-container {
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.box {
  width: 100%;
  max-width: 400px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>
