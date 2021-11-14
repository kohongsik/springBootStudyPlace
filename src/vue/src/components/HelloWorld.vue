<template>
  <div class="hello">
    <input v-model="url" @keyup.enter="getApiCall" /><br>
    <input type="button" value="GET" @click="getApiCall" /><br>
    <input type="button" value="POST" @click="postApiCall" /><br>
    {{ data }}
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data () {
    return {
      url: '',
      data: {}
    }
  },
  created () {
    // this.log('드디어 된건가.. ㅎ')
    // this.log(this.$data.state) // mixins에 변수 참조할떄 .. 
  },
  methods: {
    async getApiCall () {
      const res = await this.axios({
        method: 'get',
        url: this.url
      })
      this.data = res || {}
    },
    async postApiCall () {
      const res = await this.axios({
        method: 'post',
        url: this.url
      })
      this.data = res || {}
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
