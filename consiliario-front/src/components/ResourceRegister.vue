<template>
  <div class="register-container">
    <div class="box">
      <h2 class="title is-3">Register Resource</h2>
      <form @submit.prevent="handleRegister" enctype="multipart/form-data">
        <div class="field">
          <label class="label" for="resourceFile">Resource File</label>
          <div class="control">
            <input class="input" type="file" id="resourceFile" @change="handleFileUpload" required />
          </div>
        </div>
        <div class="field">
          <label class="label" for="resourceUrl">Resource URL</label>
          <div class="control">
            <input class="input" type="text" id="resourceUrl" v-model="resourceUrl" required />
          </div>
        </div>
        <div class="field">
          <label class="label" for="receiver">Receiver</label>
          <div class="control">
            <input class="input" type="text" id="receiver" v-model="receiver" required />
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
      resourceFile: null,
      resourceUrl: '',
      receiver: '',
    };
  },
  methods: {
    handleFileUpload(event) {
      this.resourceFile = event.target.files[0];
    },
    async handleRegister() {
      try {
        const sender = localStorage.getItem('userId');
        const formData = new FormData();
        formData.append('resourceFile', this.resourceFile);
        formData.append('resourceUrl', this.resourceUrl);
        formData.append('receiver', this.receiver);
        formData.append('sender', sender);
        
        const response = await axios.post('http://localhost:8080/recommendation', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
        if (response.status === 200) {
          alert('Resource registered successfully.');
          this.resourceFile = null;
          this.resourceUrl = '';
          this.receiver = '';
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
