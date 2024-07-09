<template>
  <div class="create-meeting-container">
    <h2 class="title is-3">Create Meeting</h2>
    <form @submit.prevent="createMeeting">
      <div class="field">
        <label class="label" for="dateTime">Date/Time</label>
        <div class="control">
          <input class="input" type="datetime-local" id="dateTime" v-model="meeting.datetime" required />
        </div>
      </div>
      <div class="field">
        <label class="label" for="student">Student</label>
        <div class="control">
          <div class="select">
            <select id="student" v-model="meeting.student" required>
              <option v-for="student in students" :key="student.id" :value="student.id">{{ student.fullName }}</option>
            </select>
          </div>
        </div>
      </div>
      <div class="field">
        <label class="label" for="location">Location</label>
        <div class="control">
          <input class="input" type="text" id="location" v-model="locationSearch" @input="searchLocations" placeholder="Type city name..." />
          <div class="select">
            <select v-if="locationResults.length" v-model="selectedLocation" @change="updateLocationDescription" required>
              <option v-for="location in locationResults" :key="location.item" :value="location.item">{{ location.itemLabel }}</option>
            </select>
          </div>
          <p v-if="locationDescription">{{ locationDescription }}</p>
        </div>
      </div>
      <div class="field">
        <div class="control">
          <button class="button is-primary" type="submit">Create Meeting</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
import SparqlClient from 'sparql-http-client';

export default {
  data() {
    return {
      meeting: {
        datetime: '',
        teacher: '',
        student: '',
        location: ''
      },
      students: [],
      locationSearch: '',
      locationResults: [],
      selectedLocation: '',
      locationDescription: ''
    };
  },
  async created() {
    this.meeting.teacher = localStorage.getItem('userId');
    await this.fetchStudents();
  },
  methods: {
    async fetchStudents() {
      try {
        const advisorId = localStorage.getItem('userId');
        const response = await axios.get(`http://localhost:8080/student/from-advisor?advisorId=${advisorId}`);
        this.students = response.data;
      } catch (error) {
        console.error('Error fetching students:', error);
      }
    },
    async searchLocations() {
      if (this.locationSearch.length < 3) {
        this.locationResults = [];
        return;
      }

      const query = `
        PREFIX wd: <http://www.wikidata.org/entity/>
        PREFIX wdt: <http://www.wikidata.org/prop/direct/>
        PREFIX wikibase: <http://wikiba.se/ontology#>
        PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
        PREFIX bd: <http://www.bigdata.com/rdf#>
        PREFIX schema: <http://schema.org/>
        
        SELECT ?item ?itemLabel ?itemDescription
        WHERE
        {
          ?item wdt:P31 wd:Q3184121.
          SERVICE wikibase:label { bd:serviceParam wikibase:language "pt". ?item rdfs:label ?itemLabel. }
          SERVICE wikibase:label { bd:serviceParam wikibase:language "pt". ?item schema:description ?itemDescription. }
          FILTER(CONTAINS(LCASE(?itemLabel), "${this.locationSearch.toLowerCase()}"))
        }
      `;

      const client = new SparqlClient({ endpointUrl: 'https://query.wikidata.org/sparql' });
      try {
        const stream = await client.query.select(query);

        const results = [];
        stream.on('data', row => {
          results.push({
            item: row.item.value,
            itemLabel: row.itemLabel.value,
            itemDescription: row.itemDescription.value
          });
        });

        stream.on('end', () => {
          this.locationResults = results;
        });

      } catch (error) {
        console.error('Error fetching locations:', error);
      }
    },
    updateLocationDescription() {
      const selectedLocation = this.locationResults.find(location => location.item === this.selectedLocation);
      this.locationDescription = selectedLocation ? selectedLocation.itemDescription : '';
      if (selectedLocation) {
        const itemId = selectedLocation.item.split('/').pop();
        this.meeting.location = itemId;
      }
    },
    async createMeeting() {
      try {
        this.meeting.datetime = new Date(this.meeting.datetime).toISOString();

        const response = await axios.post('http://localhost:8080/meeting', this.meeting);
        if (response.status === 200) {
          alert('Meeting created successfully.');
        } else {
          alert('Failed to create meeting. Please try again.');
        }
      } catch (error) {
        console.error('Error creating meeting:', error);
        alert('Failed to create meeting. Please try again.');
      }
    }
  }
};
</script>

<style scoped>
.create-meeting-container {
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
