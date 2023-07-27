<template>
  <div class="SellerBackground">
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
        style="width: 100px; height: 50px;left:-300px;top:5px;position:relative"
        :src="url"></el-image>
      <el-menu-item index="1" style="margin-left: 160px" route="/">卖家中心</el-menu-item>
      <el-menu-item index="2" @click="quit">退出</el-menu-item>
    </el-menu>

  <el-card style="margin-top: 10%;margin-left: 10%;width:80%">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="查看卖家信息" name="first">
        <div style="color:#2c3e50;font-family: 微软雅黑 Light;font-size:16px;border-color: transparent">
          <span style="font-family: 微软雅黑;font-size:30px">个人信息<br></span>
          <div class="block" style="margin-top: 30px"><el-avatar :size="125" :src="url2"></el-avatar></div>
          <span></span>
          <div style="margin-top: -100px;margin-left: 170px;font-family: 华文中宋;font-size:18px">
            <div>UID: {{PublisherInfo.PUBLISHER_ID}}</div><br>
            <div>昵称:  {{PublisherInfo.PUBLISHER_NAME}}</div><br>
          </div>
          </div>
        <el-descriptions class="margin-top" style="margin-top: 35px" :column="1" :size="size" border>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              昵称
            </template>
            {{PublisherInfo.PUBLISHER_NAME}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-office-building"></i>
              描述
            </template>
            {{PublisherInfo.DESCRIPTION }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-office-building"></i>
              卖家主页
            </template>
            {{PublisherInfo.HOMEPAGE_URL }}
          </el-descriptions-item>
        </el-descriptions>
      </el-tab-pane>
      <el-tab-pane label="修改卖家信息" name="second">
        <span style="font-family: 微软雅黑;font-size:30px">修改信息<br></span>
        <el-form :model="form" style="margin-top:30px">
          <el-form-item label="昵称" :label-width="formLabelWidth">
            <el-input placeholder="请输入修改后的昵称" v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="描述" :label-width="formLabelWidth">
            <el-input placeholder="请输入要修改的描述" v-model="form.description" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="主页" :label-width="formLabelWidth">
            <el-input placeholder="请输入要修改的主页网址" v-model="form.homepageurl" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <el-button style="margin-left: 40%" type="primary" @click="onsubmit()">确 定</el-button>
      </el-tab-pane>
      <el-tab-pane label="发布新游戏" name="third">
        <span style="font-family: 微软雅黑;font-size:30px">发布游戏<br></span>
          <el-steps :active="active" finish-status="success" style="margin-top:30px;margin-left:10%;width:70%">
            <el-step title="步骤 1：填写商品信息"></el-step>
            <el-step title="步骤 2：上传游戏图片"></el-step>
          </el-steps>
        <el-form :model="form2" style="margin-top:30px">
          <el-form-item label="名称" :label-width="formLabelWidth">
            <el-input placeholder="请输入游戏名称" v-model="form2.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="描述" :label-width="formLabelWidth">
            <el-input placeholder="请输入游戏描述" v-model="form2.description" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="链接" :label-width="formLabelWidth">
            <el-input placeholder="请输入游戏下载地址" v-model="form2.url" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="价格" :label-width="formLabelWidth">
            <el-input placeholder="请输入游戏售价" v-model="form2.price" autocomplete="off"></el-input>
          </el-form-item>

        </el-form>

        <div>
          <span style="margin-left:10px;font-size: 14px">分类</span>
        <el-select  style="margin-left:10px" v-model="value" placeholder="请选择分类">
          <el-option
            v-for="item in options"
            :key="item.GENRE_ID"
            :label="item.GENRE_NAME"
            :value="item.GENRE_ID">
          </el-option>
        </el-select>
        </div>
        <el-button style="margin-left: 45%;margin-top: 20px" type="primary" @click="onsubmit2()">下一步</el-button>
      </el-tab-pane>
      <el-tab-pane label="查看已发布游戏" name="fourth">
        <span style="font-family: 微软雅黑;font-size:30px">查看已发布游戏<br></span>
        <div style="margin-top: 30px"></div>
        <div v-for="item in games">
          <el-image
            style="width: 300px; height: 150px;position:relative"
            :src="item.PICTURE_URL" ></el-image>
          <span style="font-color:black;font-size:30px;margin-left: 50px; margin-top: 20px;position:absolute">{{item.COMMODITY_NAME}}</span>
          <span style="color:gray;margin-left: 50px; margin-top: 100px;position:absolute">发行商:{{item.PUBLISHER_NAME}}</span>
          <span style="margin-left: 560px; margin-top: 40px;position:absolute">"{{item.DESCRIPTION}}"</span>
          <span style="margin-left: 280px; margin-top: 98px;position:absolute;color:blue"><span style="margin-left: 10px" v-for="ray in item.GENRES">{{ray}}</span></span>
          <el-divider></el-divider>
        </div>
      </el-tab-pane>
    </el-tabs>
  </el-card>
    <el-card style="border-color:transparent;background-color:transparent;margin-top:40%;width:1100px"></el-card>
  </div>
</template>

<script>
import api from '../main.js'
export default {
  name: "SellerBackground",
  data() {
    return {
      games:[{
        PICTURE_URL:'https://s3.bmp.ovh/imgs/2022/11/07/423753b070807ffb.png',
        COMMODITY_NAME:'原神',
        DESCRIPTION:'',
        PUBLISHER_NAME:'',
        COMMODITY_ID:'',
        GENRES:['',''],
        url:''
      }],
      active:0,
      form: {
        name: '',
        description: '',
        homepageurl: ''
      },
      form2: {
        name: '',
        description: '',
        price:'',
        url: ''
      },
      formLabelWidth: '50px',
      PublisherInfo:{
        PUBLISHER_ID:'100000000007',
        PUBLISHER_NAME:'果团团',
        START_TIME:'',
        DESCRIPTION:'一家致力于提供水果的游戏公司',
        HOMEPAGE_URL:'1.tongji.edu.cn'
      },
      activeName: 'first',
      activeIndex2: '1',
      url: 'https://s3.bmp.ovh/imgs/2022/11/07/423753b070807ffb.png',
      url2:'https://s3.bmp.ovh/imgs/2022/10/31/ba36a5e351d6fc0d.jpg',
      urr4:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.19yxw.com%2Fwy%2Fupdate%2F20160421%2F2016042137555.jpg&refer=http%3A%2F%2Fimg.19yxw.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670430104&t=9e57a156284f7e55db35a7bc9e8982d7',
      urrr:'https://img2.baidu.com/it/u=2971083199,3587494588&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500',
      urr3:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.3dmgame.com%2Fuploads%2Fimages%2Fnews%2F20200727%2F1595832039_806081.jpg&refer=http%3A%2F%2Fimg.3dmgame.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670427661&t=172a255795a6ef6726923d93d865b206',
      options: [{
        GENRE_ID: '选项1',
        GENRE_NAME: '黄金糕'
      }, {
        GENRE_ID: '选项2',
        GENRE_NAME: '双皮奶'
      }, {
        GENRE_ID: '选项3',
        GENRE_NAME: '蚵仔煎'
      }],
      value:''
    };
  },
  methods: {
    async GetInfo() {
      var infogetter = await axios.get(api+'/api/v1/SellerService/SellerInfo?SellerID=10006')
      this.PublisherInfo = infogetter.data
      console.log(this.PublisherInfo)

      var mm = await axios.get(api+'/api/v1/SellerService/PublishedGames?PublisherID=10006')
      console.log(mm.data)
      this.games = mm.data
      let element
      for (element of this.games) {
        element.url = '/#/Commodity/' + element.COMMODITY_ID
      }

      var optiongetter = await axios.get(api+'/api/v1/SellerService/Genres')
      this.options = optiongetter.data;
      console.log(optiongetter.data)
    },
    handleClick(tab, event) {
      console.log(tab, event);

    },
    quit() {
      document.cookie = "blank"
      this.$router.push('/Login')
    },
    async onsubmit(){
      console.log(this.form.name,this.form.homepageurl,this.form.description);
      this.$axios.put(api+"/api/v1/SellerService/SellerInfo?SellerID="+document.cookie+"&Name="+this.form.name+"&Description="+this.form.description+"&HomePageUrl="+this.form.homepageurl);
      await this.$router.push('/Login')
    },
    async onsubmit2() {
      console.log(this.form2.name, this.form2.description, this.form2.url, this.form2.price, this.value)
      var dddd = await axios.post(api+"/api/v1/SellerService/NewGame?PublisherID=" + document.cookie + "&Name=" + this.form2.name + "&Description=" + this.form2.description + "&Url=" + this.form2.url + "&price=" + this.form2.price + "&genreID=" + this.value);
      console.log(dddd);
      this.$router.push("/upload2/"+dddd.data)
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
};
</script>

<style scoped>
.SellerBackground{
  background-image: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-09-21%2F5f680a1ab0f99.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670411799&t=5fac79d575355e3fa4d405b9bbe0681f");
  background-size:100%;
  height: 100%;
  width: 100%;
  border-color: transparent;
}
</style>
