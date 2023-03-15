<template>
  <nav>
    <router-link :to="{ name: 'calculator' }">Calculator</router-link> |
    <router-link :to="{ name: 'feedback' }">Feedback</router-link> | 
    <router-link :to="{ name: 'register' }">Register</router-link>
  </nav>
  <router-view />
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
