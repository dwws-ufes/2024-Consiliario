<template>
  <div>
    <Sidebar />
    <div class="main-content">
      <Navbar />
      <div class="content">
        <h1>Welcome to the Dashboard</h1>
        <!-- Conteúdo adicional do dashboard -->
      </div>
    </div>
  </div>
</template>

<script>
import Sidebar from '@/components/Sidebar.vue'
import Navbar from '@/components/Navbar.vue'

export default {
  name: 'Home',
  components: {
    Sidebar,
    Navbar
  }
}
</script>

<style scoped>
.main-content {
  margin-left: 250px; /* Espaço para a barra lateral */
  padding: 20px;
}
</style>
