<template>
  <div class="admin">
    <el-menu
      :router="true"
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#409EFF"
      style="font-family: 微软雅黑;border-color: transparent">
      <el-image
        style="width: 100px; height: 50px;left:-456px;top:5px;position:relative"
        :src="url"></el-image>
      <el-menu-item index="1" style="margin-left: 160px" route="/AdminPage">首页</el-menu-item>
      <el-menu-item index="2" route="/AdminUser">用户管理</el-menu-item>
      <el-menu-item index="3" route="/AdminCommodity">商品管理</el-menu-item>
      <el-menu-item index="4" @click="quit">退出</el-menu-item>
    </el-menu>
    <el-card style="width:400px;position: absolute;margin-left: 70px;margin-top: 8px;background-color:transparent;border-color: transparent">
      <el-calendar></el-calendar>
    </el-card>
    <el-card style="margin-left: 470px;margin-top: 8px;background-color:transparent;border-color: transparent;width:960px">
      <el-menu
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#182d4a"
        text-color="#fff"
        active-text-color="#ffd04b"
        style="font-family: 微软雅黑;border-color: transparent">
        <el-menu-item index="1">近期公告</el-menu-item>
        <el-submenu index="2">
          <template slot="title">个人中心</template>
          <el-menu-item index="2-1">选项1</el-menu-item>
          <el-menu-item index="2-2">选项2</el-menu-item>
          <el-menu-item index="2-3">选项3</el-menu-item>
        </el-submenu>
        <el-menu-item index="3">工作打卡</el-menu-item>
        <el-menu-item index="4">工作日志</el-menu-item>
        <el-input v-model="input" style="width:200px;margin-left: 250px;margin-top: 10px" placeholder="搜索游戏" ></el-input>
        <el-button icon="el-icon-search" type="primary"></el-button>
      </el-menu>
      <div style="margin-top: 25px">

        <el-carousel :interval="4000" height="540px" width="960px">
          <el-carousel-item v-for="(item, index) in urls" :key="index">
            <img v-bind:src="item.url" style="width: 100%;height: 100%">
          </el-carousel-item>
        </el-carousel>

      </div>
    </el-card>
  </div>
</template>

<script>

export default {
  name: "AdminPage",
  data() {
    return {
      input:'',
      activeIndex2: '1',
      activeName: 'first',
      url: 'https://s3.bmp.ovh/imgs/2022/11/07/423753b070807ffb.png',
      urls:[
        {url:'https://img1.baidu.com/it/u=2785842686,2377320800&fm=253&fmt=auto&app=120&f=JPEG?w=889&h=500'},
        {url:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.bizhizu.com%2F2015%2F0723%2F20150723093414535.jpg&refer=http%3A%2F%2Fimg.bizhizu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670427675&t=5e1c9332ce2775c97f351fd9e0f66533'},
        {url:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.3dmgame.com%2Fuploads%2Fimages%2Fnews%2F20200727%2F1595832039_806081.jpg&refer=http%3A%2F%2Fimg.3dmgame.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670427661&t=172a255795a6ef6726923d93d865b206'},
        {url:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F4529a797509fcc7ee828431a819a2260639bf6bd.gif&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670425131&t=7a5805a06798845f3e259779d7a98f6f'},
      ],
    }
  },
  methods:
    {
      quit(){
        document.cookie="blank"
        this.$router.push('/Login')
      },
      GetCookie() {
        console.log(document.cookie)
      },
      DeleteCookie(){
        document.cookie="blank"
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      IsCookie() {
        if(document.cookie=="blank"||document.cookie=="")
          this.$router.push('/Login')
      },
    },
  mounted(){
    this.IsCookie()
  }
}
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.admin{
  background-image: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-09-21%2F5f680a1ab0f99.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670411799&t=5fac79d575355e3fa4d405b9bbe0681f");
  background-size:100%;
  height: 100%;
  width: 100%;
  position: absolute;
  border-color: transparent;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.text {
  font-size: 14px;
}
.item {
  padding: 18px 0;
}
/*.box-card {
  margin-top: 20px;
  width: 960px;
}*/
</style>

