<template>
  <div class="register-container">
    <div class="box">
      <h2 class="title is-3">Resource Registration</h2>
      <form @submit.prevent="handleRegister">
        <div class="field">
          <label class="label" for="url">URL</label>
          <div class="control">
            <input class="input" type="text" id="url" v-model="url" required />
          </div>
        </div>
        <div class="field">
          <label class="label" for="creationDate">Creation Date</label>
          <div class="control">
            <input class="input" type="date" id="creationDate" v-model="creationDate" required />
          </div>
        </div>
        <div class="field">
          <label class="label" for="type">Type</label>
          <div class="control">
            <div class="select">
              <select id="type" v-model="type" required>
                <option value="File">File</option>
                <option value="Url">Url</option>
              </select>
            </div>
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
      url: '',
      creationDate: '',
      type: 'File'
    }
  },
  methods: {
    handleRegister() {
      // Lógica para registro de recursos
      console.log('URL:', this.url)
      console.log('Creation Date:', this.creationDate)
      console.log('Type:', this.type)
    }
  }
}
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
  max-width: 800px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>
