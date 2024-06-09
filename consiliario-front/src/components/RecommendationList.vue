<template>
  <div class="list-container">
    <div class="box">
      <h2 class="title is-3">Recommendation List</h2>
      <b-table striped hover :items="recommendations" :fields="fields" responsive="sm">
        <template #cell(actions)="row">
          <b-button size="sm" variant="primary" @click="viewRecommendation(row.item.id)">View</b-button>
          <b-button size="sm" variant="danger" @click="deleteRecommendation(row.item.id)">Delete</b-button>
        </template>
      </b-table>

      <!-- Modal for viewing recommendation details -->
      <b-modal v-model="showModal" title="Recommendation Details" hide-footer>
        <div v-if="selectedRecommendation">
          <p><strong>Resource URL:</strong> {{ selectedRecommendation.resourceUrl }}</p>
          <p><strong>Receiver:</strong> {{ receiverName }}</p>
          <p><strong>Sender:</strong> {{ senderName }}</p>
          <p><strong>Resource File:</strong> <a :href="selectedRecommendation.resourceFile" target="_blank" v-if="selectedRecommendation.resourceFile">Download</a></p>
        </div>
        <div v-else>
          <p>Loading...</p>
        </div>
      </b-modal>
    </div>
  </div>
</template>

<script>
import axios from '@/axios';

export default {
  data() {
    return {
      fields: [
        { key: 'resourceUrl', label: 'Resource URL', sortable: true },
        { key: 'receiver', label: 'Receiver', sortable: true },
        { key: 'sender', label: 'Sender', sortable: true },
        { key: 'actions', label: 'Actions' }
      ],
      recommendations: [],
      showModal: false,
      selectedRecommendation: null,
      receiverName: '',
      senderName: ''
    };
  },
  created() {
    this.fetchRecommendations();
  },
  methods: {
    async fetchRecommendations() {
      try {
        const response = await axios.get('http://localhost:8080/recommendation');
        this.recommendations = response.data;
      } catch (error) {
        console.error('Error fetching recommendations:', error);
        alert('Failed to fetch recommendations. Please try again.');
      }
    },
    async viewRecommendation(recommendationId) {
      this.showModal = true;
      this.selectedRecommendation = null;
      this.receiverName = 'Loading...';
      this.senderName = 'Loading...';
      try {
        const response = await axios.get(`http://localhost:8080/recommendation?recommendationId=${recommendationId}`);
        this.selectedRecommendation = response.data[0];
        console.log(response.data[0].receiver)
        console.log(response.data[0].sender)
        console.log(response)
        await this.fetchReceiverName(response.data[0].receiver);
        await this.fetchSenderName(response.data[0].sender);
      } catch (error) {
        console.error('Error viewing recommendation:', error);
        alert('Failed to view recommendation. Please try again.');
      }
    },
    async fetchReceiverName(receiverId) {
      try {
        const response = await axios.get(`http://localhost:8080/student?studentId=${receiverId}`);
        this.receiverName = response.data.fullName;
      } catch (error) {
        console.error('Error fetching receiver name:', error);
        this.receiverName = 'Unknown';
      }
    },
    async fetchSenderName(senderId) {
      try {
        const response = await axios.get(`http://localhost:8080/teacher?teacherId=${senderId}`);
        this.senderName = response.data.username;
      } catch (error) {
        console.error('Error fetching sender name:', error);
        this.senderName = 'Unknown';
      }
    },
    async deleteRecommendation(recommendationId) {
      try {
        await axios.delete(`http://localhost:8080/recommendation?recommendationId=${recommendationId}`);
        this.fetchRecommendations(); // Atualizar a lista após deletar
      } catch (error) {
        console.error('Error deleting recommendation:', error);
        alert('Failed to delete recommendation. Please try again.');
      }
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
