import { createApp } from 'vue'
import App from './App.vue'
import Router from './router/router.js'
import common from './common/common.js'
import axios from 'axios'
import '../../main/resources/static/css/style.css'
axios.defaults.baseURL = 'http://localhost:8080/'
axios.defaults.headers.get['Content-Type'] = 'application/json;charset=utf-8'
axios.defaults.headers.get['Access-Control-Allow-Origin'] = '*'
const app = createApp(App)
app.use(Router)
app.mixin(common)
app.mount('#app')
