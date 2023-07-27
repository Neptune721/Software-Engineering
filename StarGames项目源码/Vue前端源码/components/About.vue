<template>
<div class="About">
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
      style="width: 100px; height: 50px;left:-400px;top:5px;position:relative"
      :src="url"></el-image>
    <el-menu-item index="1" style="margin-left: 160px" route="/">首页</el-menu-item>
    <el-menu-item index="2" route="/FriendList">好友</el-menu-item>
    <el-menu-item index="3" route="/About">关于</el-menu-item>
    <el-menu-item index="4" @click="quit">退出</el-menu-item>
  </el-menu>
  <el-card style="margin-left: 350px;margin-top: 50px;background-color:transparent;border-color: transparent;width:960px">
    <div style="font-size: 30px;font-family: 微软雅黑;color: white;font-weight: bolder">关于StarGames</div>
    <div style="margin-top:20px;font-size: 20px;font-family: 华文楷体;color: whitesmoke">近年来，游戏产业不断发展，许多精美的游戏上线，改变了人们的娱乐方式，随之而来的，对游戏零售平台的需求也不断上升。StarGames 是由我们团队开发的一个游戏信息管理系统，依据一定的隐私条例来供用户在 StarGames 平台上进行游戏的发布与购买，以此方便用户对自己的游戏进行管理。当今, 随着游戏数量的不断增长，用户需要处理大量的游戏数据信息，从而快捷地管理自己所拥有的游戏并进行各种操作，用户同时也需要一个集中的平台来了解当前游戏如评价、价格和销量等各种信息，与好友进行交流；管理员也需要一个可以快速管理买家与商品的途径。
      <br></br>本项目名为StarGames，中文名“星游游戏商品零售平台”，是基于Vue和SpringBoot框架开发的Web端应用。项目能为游戏玩家提供优秀的游戏购物体验，同时为发行商提供一个优质的游戏零售平台。另外，本项目具有界面美观、使用简单、功能全面等特点，同时以“星空”为主题，为用户提供了一种沉浸式的游戏购物体验。</div>
    <div style="font-size: 25px;font-family: 华文楷体;color: white;font-weight: bolder"><br></br>2023，StarGames欢迎您！</div>
  </el-card>
</div>
</template>

<script>
export default {
  name: "About",
  data() {
    return {
      select: '',
      input5:'',
      activeIndex2: '3',
      url: 'https://s3.bmp.ovh/imgs/2022/11/07/423753b070807ffb.png',
    }
  },

}
</script>

<style scoped>
.About{
  background-image: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-09-21%2F5f680a1ab0f99.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670411799&t=5fac79d575355e3fa4d405b9bbe0681f");
  background-size:100% 100%;
  height: 100%;
  width: 100%;
  position:absolute;
  border-color: transparent;
}
</style>
