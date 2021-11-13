import { createApp } from 'vue'
import App from './App.vue'
import Common from './common/Common.js'
import HelloWorld from './components/HelloWorld.vue'
const app = createApp(App)
app.mixin(Common)
app.component('HelloWorld', HelloWorld)
app.mount('#app')
