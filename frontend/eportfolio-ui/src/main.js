import { mount } from 'svelte'
import './app.css'
import App from './App.svelte'
import './styles/base.css'
import './styles/components.css'
import './styles/components.css'


const app = mount(App, {
  target: document.getElementById('app'),
})

export default app
