<template>
  <div class="list-container">
    <div class="box">
      <h2 class="title is-3">Student List</h2>
      <b-table striped hover :items="students" :fields="fields" responsive="sm">
        <template #cell(actions)="row">
          <b-button size="sm" variant="primary" @click="editStudent(row.item)">Edit</b-button>
          <b-button size="sm" variant="danger" @click="deleteStudent(row.item)">Delete</b-button>
        </template>
      </b-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fields: [
        { key: 'name', label: 'Name', sortable: true },
        { key: 'registration', label: 'Registration', sortable: true },
        { key: 'actions', label: 'Actions' }
      ],
      students: [
        { id: 1, name: 'John Doe', registration: '12345' },
        { id: 2, name: 'Jane Smith', registration: '67890' }
        // Adicione mais estudantes conforme necessário
      ]
    }
  },
  methods: {
    editStudent(student) {
      // Lógica para editar o estudante
      console.log('Edit student:', student)
    },
    deleteStudent(student) {
      // Lógica para deletar o estudante
      console.log('Delete student:', student)
    }
  }
}
</script>

<style scoped>
.list-container {
  width: 100%;
  max-width: 800px;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
.box {
  width: 100%;
}
</style>
