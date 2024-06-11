<template>
  <b-modal v-model="show" title="Change Password" hide-footer>
    <b-form @submit.prevent="changePassword">
      <b-form-group label="Email">
        <b-form-input v-model="email" type="email" required></b-form-input>
      </b-form-group>
      <b-form-group label="Old Password">
        <b-form-input v-model="oldPassword" type="password" required></b-form-input>
      </b-form-group>
      <b-form-group label="New Password">
        <b-form-input v-model="newPassword" type="password" required></b-form-input>
      </b-form-group>
      <b-button type="submit" variant="primary">Submit</b-button>
    </b-form>
  </b-modal>
</template>

<script>
import axios from '@/axios';

export default {
  props: {
    show: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      email: '',
      oldPassword: '',
      newPassword: ''
    };
  },
  methods: {
    async changePassword() {
      try {
        const payload = {
          email: this.email,
          oldPassword: this.oldPassword,
          newPassword: this.newPassword
        };
        await axios.post('http://localhost:8080/recoverPassword', payload);
        this.$emit('close');
        alert('Password successfully updated.');
      } catch (error) {
        console.error('Error changing password:', error);
        alert('Failed to change password. Please try again.');
      }
    }
  }
};
</script>
