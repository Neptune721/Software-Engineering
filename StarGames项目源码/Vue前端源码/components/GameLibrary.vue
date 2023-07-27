<template>
  <div class="lib">
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
<el-card style="border-color:transparent;background-color:#182d4a;margin-left:5%;margin-top:5%;width:1100px">
  <div style="color:white;font-family: 微软雅黑;font-size:30px;border-color: transparent">
    <span>我的游戏库</span>
    <el-button @click="$router.push('/')" style="color:white;font-family: 微软雅黑;font-size:24px;float: right; padding: 3px 0" type="text">返回首页</el-button>
  </div>
  <div style="color:white;font-family: 微软雅黑;margin-top: 50px">
    <div v-for="item in games">
      <el-image
        style="width: 300px; height: 150px;position:relative"
        @click="$router.push({ path: '/Commodity/'+item.COMMODITY_ID })"
        :src="item.PICTURE_URL" ></el-image>
      <span style="font-color:white;font-size:30px;margin-left: 50px; margin-top: 20px;position:absolute">{{item.COMMODITY_NAME}}</span>
      <span style="margin-left: 400px; margin-top: 0px;width: 200px;position:absolute">"{{item.DESCRIPTION}}"</span>
      <span style="margin-left: 22px; margin-top: 120px;position:absolute;color:lightyellow"><span style="margin-left: 10px" v-for="ray in item.GENRES">{{ray}}</span></span>
      <el-link type="primary" :href="item.url" style="margin-left:88%">查看商品详情</el-link>
      <el-divider></el-divider>
    </div>
  </div>
</el-card>
    <el-card style="border-color:transparent;background-color:transparent;height:500px">
    </el-card>
  </div>
</template>

<script>
import api from '../main.js'
export default {
  name: "Home",
  data() {
    return {
      input:'',
      activeIndex2: '1',
      activeName: 'first',
      url: 'https://s3.bmp.ovh/imgs/2022/11/07/423753b070807ffb.png',
      games:[{
        PICTURE_URL:'https://s3.bmp.ovh/imgs/2022/11/07/423753b070807ffb.png',
        COMMODITY_NAME:'原神',
        DESCRIPTION:'',
        PUBLISHER_NAME:'',
        COMMODITY_ID:'',
        GENRES:['',''],
        url:''
      }]
    }
  },
  methods:
    {
      async GetInfo() {
        var mm = await axios.get(api+'/api/v1/GameLibService/GameLibraryItems?BuyerID='+document.cookie)
        console.log(mm.data)
        this.games=mm.data
        let element
        for (element of this.games) {
          element.url='/#/Commodity/'+element.COMMODITY_ID
        }

        console.log(this.games.url)
      },
      ToLink1(){
        this.$router.push('/#/Home')
      },
      open() {
        this.$alert('你是个憨批', '测试结果', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${ action }`
            });
          }
        });
      },
      quit() {
        document.cookie = "blank"
        this.$router.push('/Login')
      },
      IsCookie() {
        console.log(document.cookie)
        if(document.cookie=="blank"||document.cookie=="")
          this.$router.push('/Login')
      },
    },
  mounted(){
    this.IsCookie()
    this.GetInfo()
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

.lib{
  background-image: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-09-21%2F5f680a1ab0f99.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670411799&t=5fac79d575355e3fa4d405b9bbe0681f");
  background-size:100% 100%;
  height: 100%;
  width: 100%;
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
