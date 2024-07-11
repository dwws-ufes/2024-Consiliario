<template>
  <div class="meeting-list-container">
    <h2 class="title is-3">Meeting List</h2>
    <table class="table is-striped is-fullwidth">
      <thead>
        <tr>
          <th>Date/Time</th>
          <th>Student</th>
          <th>Location</th>
          <th>Description</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="meeting in meetings" :key="meeting.id">
          <td>{{ formatDate(meeting.datetime) }}</td>
          <td>{{ getStudentName(meeting.student) }}</td>
          <td>{{ meeting.label }}</td>
          <td>{{ meeting.description }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      meetings: [],
      students: {}
    };
  },
  async created() {
    await this.fetchMeetings();
    await this.fetchStudents();
  },
  methods: {
    async fetchMeetings() {
      try {
        const teacherId = localStorage.getItem('userId');
        const response = await axios.get(`http://localhost:8080/meeting/from-teacher?teacherId=${teacherId}`);
        this.meetings = response.data;
      } catch (error) {
        console.error('Error fetching meetings:', error);
      }
    },
    async fetchStudents() {
      try {
        const advisorId = localStorage.getItem('userId');
        const response = await axios.get(`http://localhost:8080/student/from-advisor?advisorId=${advisorId}`);
        response.data.forEach(student => {
          this.students[student.id] = student.fullName;
        });
      } catch (error) {
        console.error('Error fetching students:', error);
      }
    },
    getStudentName(studentId) {
      return this.students[studentId] || 'Unknown';
    },
    formatDate(dateTime) {
      const date = new Date(dateTime);
      return date.toLocaleString();
    }
  }
};
</script>

<style scoped>
.meeting-list-container {
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 8px;
}
.table {
  margin-top: 20px;
}
</style>
