<template>
  <div class="resource-container">
    <div class="box">
      <h2 class="title is-3">Associate Student with Resource</h2>
      <form @submit.prevent="handleAssociate">
        <div class="field">
          <label class="label" for="student">Student</label>
          <div class="control">
            <div class="select">
              <select id="student" v-model="selectedStudent" required>
                <option v-for="student in students" :key="student.id" :value="student.id">
                  {{ student.name }}
                </option>
              </select>
            </div>
          </div>
        </div>
        <div class="field">
          <label class="label" for="resource">Resource</label>
          <div class="control">
            <div class="select">
              <select id="resource" v-model="selectedResource" required>
                <option v-for="resource in resources" :key="resource.id" :value="resource.id">
                  {{ resource.url }} ({{ resource.type }})
                </option>
              </select>
            </div>
          </div>
        </div>
        <div class="field">
          <div class="control">
            <button class="button is-primary" type="submit">Associate</button>
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
      selectedStudent: '',
      selectedResource: '',
      students: [
        { id: 1, name: 'John Doe' },
        { id: 2, name: 'Jane Smith' }
        // Adicione mais estudantes conforme necessário
      ],
      resources: [
        { id: 1, url: 'http://example.com/file1', type: 'File' },
        { id: 2, url: 'http://example.com/url1', type: 'Url' }
        // Adicione mais recursos conforme necessário
      ]
    }
  },
  methods: {
    handleAssociate() {
      // Lógica para associar estudante com recurso
      console.log('Student ID:', this.selectedStudent)
      console.log('Resource ID:', this.selectedResource)
    }
  }
}
</script>

<style scoped>
.resource-container {
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
