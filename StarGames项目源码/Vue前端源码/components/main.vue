<template>
  <div class="main">
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
        style="width: 100px; height: 50px;left:-270px;top:5px;position:relative"
        :src="url"></el-image>
      <el-menu-item index="1" style="margin-left: 160px" route="/">首页</el-menu-item>
      <el-menu-item index="2" route="/login">登录</el-menu-item>
    </el-menu>
    <div style="color:whitesmoke;font-size:30px;margin-left:360px;margin-top: 20px">
      <span>请先登录，以使用更多功能</span>
    </div>
    <el-card style="width:200px;height:800px;position: absolute;margin-left: 100px;margin-top: 100px;background-color:transparent;border-color: transparent">
      <el-image
        style="width: 180px; height: 90px;left:0px;top:5px;position:relative"
        :src="url"></el-image>
      <span style="font-family: 微软雅黑;color:whitesmoke;line-height: 30px;margin-top: 40px">探索队列</span><br>
      <span style="font-family: 微软雅黑;color:#409EFF;line-height: 30px">
      推荐<br>
      新品<br>
      优惠<br></span>
      <el-divider></el-divider>
      <span style="font-family: 微软雅黑;color:whitesmoke;line-height: 30px;margin-top: 40px">精彩活动</span><br>
      <el-image
        style="width: 150px; height: 100px;left:0px;top:5px;position:relative"
        :src="act1"></el-image>
      <el-image
        style="width: 150px; height: 100px;left:0px;top:5px;position:relative"
        :src="act2"></el-image>
      <el-divider></el-divider>
      <span style="font-family: 微软雅黑;color:whitesmoke;line-height: 30px;margin-top: 40px">关于我们</span><br>
      <span style="font-family: 微软雅黑;color:#409EFF;line-height: 30px">关于……</span>
    </el-card>
    <el-card style="margin-left: 350px;margin-top: 50px;background-color:transparent;border-color: transparent;width:960px">
      <el-menu
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#182d4a"
        text-color="#fff"
        active-text-color="#ffd04b"
        style="font-family: 微软雅黑;border-color: transparent">
        <el-menu-item index="1">游戏商店</el-menu-item>
        <el-menu-item>
          <div style="margin-top: 0px;margin-left: 220px">
            <el-input placeholder="请输入内容" v-model="input5" class="input-with-select" style="width: 500px;margin-left: 0px;margin-top: 0px;">
              <el-select v-model="select" slot="prepend" placeholder="请选择">
                <el-option label="角色扮演" value="1"></el-option>
                <el-option label="冒险" value="2"></el-option>
                <el-option label="休闲" value="3"></el-option>
                <el-option label="射击" value="4"></el-option>
                <el-option label="恐怖" value="5"></el-option>
                <el-option label="益智" value="6"></el-option>
                <el-option label="全部" value="7"></el-option>
              </el-select>
              <el-button slot="append" icon="el-icon-search " @click="gamelist" ></el-button>
            </el-input>
          </div></el-menu-item>
      </el-menu>
      <el-image
        style="width: 300px; height: 100px;left:0px;top:5px"
        :src="xg"></el-image>
      <div style="margin-top: 0px">

        <el-carousel :interval="4000" height="540px" width="960px">
          <el-carousel-item v-for="(item, index) in urls" :key="index">
            <img v-bind:src="item.url" style="width: 100%;height: 100%">
          </el-carousel-item>
        </el-carousel>
      </div>
    </el-card>
    <el-image
      style="width: 300px; height: 100px;left:350px;top:0px"
      :src="rs"></el-image>
    <el-card style="margin-left: 355px;margin-top: 10px;background-color:#182d4a;border-color: transparent;width:960px">

      <el-image
        style="width: 200px; height: 100px;left:0px;top:5px"
        :src="urr3"></el-image>
      <span style="position:absolute;margin-left: 20px;margin-top: 20px;line-height: 40px">
      <span style="color:whitesmoke;font-size:28px;font-family: 微软雅黑">
        同舟共济<br>
      </span>
        <span style="color:lightgray;font-family: 微软雅黑">休闲类</span><span style="position:absolute;font-size:25px;margin-left:500px;margin-top: -30px;color:white;font-family: 微软雅黑">￥65</span>
      </span>
      <el-button @click="$router.push('Commodity/100005')" style="color:#409EFF;font-family: 微软雅黑;margin-top: 4%;font-size:15px;float: right" type="text">查看详情</el-button>
      <el-divider></el-divider>
      <el-image
        style="width: 200px; height: 100px;left:0px;top:5px"
        :src="urrr"></el-image>
      <span style="position:absolute;margin-left: 20px;margin-top: 20px;line-height: 40px">
      <span style="color:whitesmoke;font-size:28px;font-family: 微软雅黑">
        大富翁10<br>
      </span>
        <span style="color:lightgray;font-family: 微软雅黑">休闲类</span>
        <span style="position:absolute;font-size:25px;margin-left:500px;margin-top: -30px;color:white;font-family: 微软雅黑">￥28</span>
      </span>
      <el-button @click="$router.push('Commodity/100013')" style="color:#409EFF;font-family: 微软雅黑;margin-top: 4%;font-size:15px;float: right" type="text">查看详情</el-button>
      <el-divider></el-divider>
      <el-image
        style="width: 200px; height: 100px;left:0px;top:5px"
        :src="urr4"></el-image>
      <span style="position:absolute;margin-left: 20px;margin-top: 20px;line-height: 40px">
      <span style="color:whitesmoke;font-size:28px;font-family: 微软雅黑">
        赛博朋克2077<br>
      </span>
        <span style="color:lightgray;font-family: 微软雅黑">恐怖类</span><span style="position:absolute;font-size:25px;margin-left:500px;margin-top: -30px;color:white;font-family: 微软雅黑">￥298</span>
      </span>
      <el-button @click="$router.push('Commodity/100015')" style="color:#409EFF;font-family: 微软雅黑;margin-top: 4%;font-size:15px;float: right" type="text">查看详情</el-button>
    </el-card>
  </div>
</template>

<script>

export default {
  name: "main",
  data() {
    return {
      select: '',
      input5: '',
      urls: [
        {url: 'https://img1.baidu.com/it/u=2785842686,2377320800&fm=253&fmt=auto&app=120&f=JPEG?w=889&h=500'},
        {url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.bizhizu.com%2F2015%2F0723%2F20150723093414535.jpg&refer=http%3A%2F%2Fimg.bizhizu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670427675&t=5e1c9332ce2775c97f351fd9e0f66533'},
        {url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.3dmgame.com%2Fuploads%2Fimages%2Fnews%2F20200727%2F1595832039_806081.jpg&refer=http%3A%2F%2Fimg.3dmgame.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670427661&t=172a255795a6ef6726923d93d865b206'},
        {url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinakd20220119ac%2F650%2Fw1500h750%2F20220119%2Fb724-7fc72986eb63b24bdd742624306a36e9.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672390858&t=4f923bd5e3c31f17a65b7a00bea35852'},
      ],
      fileList: [{
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }, {
        name: 'food2.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }],
      activeIndex2: '1',
      activeName: 'first',
      url: 'https://s3.bmp.ovh/imgs/2022/11/07/423753b070807ffb.png',
      url2: 'https://s3.bmp.ovh/imgs/2022/10/31/ba36a5e351d6fc0d.jpg',
      urr4: 'https://imgo.youxiniao.com/img2020/12/10/10/2020121012512345.jpg',
      urrr: 'https://pic4.zhimg.com/v2-459fa77ca5b90896bad4f08ea398af9b_1440w.jpg?source=172ae18b',
      xg: 'https://tudingtu.cn/i/2023/01/03/skwtk4.png',
      rs: 'https://tudingtu.cn/i/2023/01/03/skwiw7.png',
      urr3: 'https://media.9game.cn/gamebase/ieu-gdc-pre-process/images/20221103/11/27/3bd1e7aaea115ae6504a16e63286da39.jpg',
      act1: 'https://tudingtu.cn/i/2023/01/03/sy5udf.png',
      act2: 'https://tudingtu.cn/i/2023/01/03/sy5hfg.png'
    }
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

.main{
  background-image: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-09-21%2F5f680a1ab0f99.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670411799&t=5fac79d575355e3fa4d405b9bbe0681f");
  background-size:100%;
  height: 100%;
  width: 100%;
  border-color: transparent;
}
.el-select .el-input {
  width: 100px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.my{
  background-color:#545c64;
}
.text {
  font-size: 14px;
}
/*.item {
  padding: 18px 0;
}*/
/*.box-card {
  margin-top: 20px;
  width: 960px;
}*/
</style>
