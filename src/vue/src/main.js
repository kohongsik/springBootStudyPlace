import { createApp } from 'vue'
import App from './App.vue'
import Common from './common/Common.js'
import HelloWorld from './components/HelloWorld.vue'
import axios from 'axios'
import vueRouter from 'vue-router'
axios.defaults.baseURL = 'http://localhost:8080/'
axios.defaults.headers.get['Content-Type'] = 'application/json;charset=utf-8'
axios.defaults.headers.get['Access-Control-Allow-Origin'] = '*'
const app = createApp(App)
app.mixin(Common)
app.component('HelloWorld', HelloWorld)
app.use(vueRouter)
app.mount('#app')
