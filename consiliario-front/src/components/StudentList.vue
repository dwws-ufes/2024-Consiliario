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
import axios from '@/axios';

export default {
  data() {
    return {
      fields: [
        { key: 'fullName', label: 'Full Name', sortable: true },
        { key: 'email', label: 'Email', sortable: true },
        { key: 'advisor', label: 'Advisor', sortable: true },
        { key: 'actions', label: 'Actions' }
      ],
      students: []
    };
  },
  created() {
    this.fetchStudents();
  },
  methods: {
    async fetchStudents() {
      try {
        const advisorId = localStorage.getItem('userId');
        const response = await axios.get(`http://localhost:8080/student/from-advisor?advisorId=${advisorId}`);
        this.students = response.data;
      } catch (error) {
        console.error('Error fetching students:', error);
        alert('Failed to fetch students. Please try again.');
      }
    },
    editStudent(student) {
      // Lógica para editar o estudante
      console.log('Edit student:', student);
    },
    deleteStudent(student) {
      // Lógica para deletar o estudante
      console.log('Delete student:', student);
    }
  }
};
</script>

<style scoped>
.list-container {
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
