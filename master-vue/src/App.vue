<template>
  <div id="app">
    <!-- <img src="./assets/logo.png"> -->
    <!-- <router-link to="/home">home</router-link>
    <router-link to="/film">film</router-link>
    <router-link to="/card">card</router-link> -->
    <router-view/>
  </div>
</template>

<script>
import Vue from 'vue'
import { setCookie, getCookie, delCookie } from '@/utils/cookies'
export default {
  name: 'App',
  beforeMount () {
    Vue.prototype.$cookieStore = {
      setCookie,
      getCookie,
      delCookie
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
