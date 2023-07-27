<template>
  <div id="app">
        <router-view v-if="isRouterAlive" />
  </div>

</template>

<script>
export default {
  name: 'App',
  provide() { // 父组件中返回要传给下级的数据
    return {
      reload: this.reload
    }
  },
  data() {
    return {
      isRouterAlive: true
    }
  },
  methods: {
    reload() {
      this.isRouterAlive = false
      this.$nextTick(function() {
        this.isRouterAlive = true
      })
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #2c3e50;
  margin-top: -10px;
  margin-left: 0px;
}
.my{
  background-color:lightskyblue;
}
.title{
  text-align:left;
  color:whitesmoke;
  font-family: '微软雅黑';
  font-size: 28px;
}
.title2{
  text-align:left;
  color:whitesmoke;
  font-family: 'Times New Roman';
  font-size: 15px;
}
</style>
