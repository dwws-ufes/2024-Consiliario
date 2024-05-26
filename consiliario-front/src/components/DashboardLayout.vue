<template>
  <div style="width: 100%">
    <Sidebar />
    <div class="main-content">
      <Navbar />
      <div class="content">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import Sidebar from '@/components/Sidebar.vue'
import Navbar from '@/components/Navbar.vue'

export default {
  components: {
    Sidebar,
    Navbar
  }
}
</script>

<style scoped>
.main-content {
  margin-left: 250px; /* Espaço para a barra lateral */
  display: flex;
  flex-direction: column;
  height: 100vh;
  margin-top: 56px; /* Altura da navbar */
}

.content {
  flex: 1;
  padding: 20px;
  background-color: #f5f5f5;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
