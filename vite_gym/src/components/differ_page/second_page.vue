<template>
  <div>

    <input type="text" v-model="inputText"> </input>
<el-button type="primary" @click="gps_callback">Get GP</el-button>
    <div>
      <p>{{ output }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'SecondPage',
  data() {
    return {
      // data
      output: '',
      inputText: ''
    }
  },
  methods: {
    // methods
    gps_callback() {
      console.log("11");
      axios.post('https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=AIzaSyAx7AeiyZ-o0wEm2EKFT24_AJpsRO_hLSM',
          {"contents":[{"parts":[{"text":this.inputText}]}]}).then(response => {
        console.log(response.data.candidates[0].content.parts[0].text);
        this.output = response.data.candidates[0].content.parts[0].text;
      })
    }
  }
}
</script>

