<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <button class="nav-link btn btn-link text-white" @click="logout">Logout</button>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
  methods: {
    logout() {
      localStorage.removeItem('auth')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.navbar {
  background-color: #6aadf1 !important;
  width: 100%;
  position: fixed;
  top: 0;
  left: 250px; /* Espaço para a barra lateral */
  z-index: 1; /* Para garantir que a navbar fique sobre o conteúdo */
}
.ml-auto {
  margin-left: auto;
}
</style>
