<template>
  <div class="login-container">
    <div class="box">
      <h2 class="title is-3">Login</h2>
      <form @submit.prevent="handleLogin">
        <div class="field">
          <label class="label" for="email">Email</label>
          <div class="control">
            <input class="input" type="email" id="email" v-model="email" required />
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
      <button class="button is-link mt-3" @click="goToRegisterTeacher">Register as Teacher</button>
      <button class="button is-warning mt-3" @click="showChangePasswordModal = true">Change Password</button>
    </div>
    <PasswordChangeModal :show="showChangePasswordModal" @close="showChangePasswordModal = false" />
  </div>
</template>

<script>
import axios from 'axios';
import jwtDecode from 'jwt-decode';
import PasswordChangeModal from '@/components/PasswordChangeModal.vue';

export default {
  components: {
    PasswordChangeModal
  },
  data() {
    return {
      email: '',
      password: '',
      showChangePasswordModal: false
    };
  },
  methods: {
    async handleLogin() {
      try {
        const payload = {
          email: this.email,
          password: this.password
        };
        const response = await axios.post('http://localhost:8080/login', payload);
        if (response.status === 200) {
          const token = response.data.token;
          const decodedToken = jwtDecode(token);
          localStorage.setItem('auth', 'true');
          localStorage.setItem('token', token);
          localStorage.setItem('userId', decodedToken.id);
          axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
          this.$router.push('/');
        } else {
          alert('Login failed. Please try again.');
        }
      } catch (error) {
        console.error('Error during login:', error);
        alert('Login failed. Please try again.');
      }
    },
    goToRegisterTeacher() {
      this.$router.push('/register-teacher');
    }
  }
};
</script>

<style scoped>
.login-container {
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
}
.box {
  width: 100%;
  max-width: 400px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
.mt-3 {
  margin-top: 1rem;
}
</style>
