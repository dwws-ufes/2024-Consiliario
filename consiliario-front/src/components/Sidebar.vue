<template>
  <div class="sidebar">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark flex-column">
      <a class="navbar-brand" href="#">Dashboard</a>
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav flex-column">
          <li class="nav-item">
            <router-link to="/register-student" class="nav-link">Register Student</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/" class="nav-link">List Students</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/register-resource" class="nav-link">Register Resource</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/recommendations" class="nav-link"
              >Recommendations</router-link
            >
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<style scoped>
.sidebar {
  width: 250px;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  background-color: #343a40;
  color: #fff;
  z-index: 1; /* Para garantir que a sidebar fique sobre o conteúdo */
}
.nav-link {
  color: #fff;
}
.nav-link:hover {
  color: #ddd;
}
</style>
