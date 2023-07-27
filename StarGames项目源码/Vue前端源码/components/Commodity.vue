<template>
  <div class="info">
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
    <el-card style="margin-left: 5%;margin-top: 5%;background-color:whitesmoke;border-color: transparent;width:960px">
      <div style="color:#2c3e50;font-family: 微软雅黑 Light;font-size:16px;border-color: transparent">
        <span style="font-family: 微软雅黑;font-size:30px">商品信息<br></span>
        <div class="demo-image__placeholder">
          <div class="block" style="margin-top: 20px">
            <el-image :src="CommodityInfo.PICTURE_URL">
            </el-image>
          </div>
        </div>
        <span></span>
        <div style="margin-top: 30px;margin-left: 0px;font-family: 华文中宋;font-size:18px">
          <div>商品ID: {{CommodityInfo.COMMODITY_ID}}</div><br>
          <div>商品名称 :  {{CommodityInfo.COMMODITY_NAME}}</div><br>
        </div>

        <el-descriptions class="margin-top" style="margin-top: 35px" :column="1" :size="size" border>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-goods"></i>
              商品名称
            </template>
            {{CommodityInfo.COMMODITY_NAME}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-s-goods"></i>
              商品ID
            </template>
            {{CommodityInfo.COMMODITY_ID}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-more"></i>
              商品详情
            </template>
            {{CommodityInfo.DESCRIPTION}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-shopping-cart-2"></i>
              商品价格
            </template>
            {{ CommodityInfo.PRICE}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user-solid"></i>
              发布时间
            </template>
            {{ CommodityInfo.PUBLISH_TIME }}
          </el-descriptions-item>
        </el-descriptions>
        <el-button type="primary" style="margin-top: 30px;margin-left: 50px" @click="addtowishlist">添加至愿望单</el-button>
        <el-button type="primary" style="margin-top: 30px ;margin-left: 400px" @click="addtoshoppingcart">添加至购物车</el-button>
      </div>
    </el-card>
    <el-card style="border-color:transparent;background-color:white;margin-left:5%;margin-top:5%;width:1100px">
      <div style="color:#182d4a;font-family: 微软雅黑;font-size:30px;border-color: transparent">
        <span>精选评论</span>
        <el-button style="margin-left:70%"type="success">发布评论</el-button>
        <el-divider></el-divider>
      </div>
      <div style="color:white;font-family: 微软雅黑;margin-top: 20px">
        <div v-for="item in evaluations">

          <el-descriptions
            class="margin-top"
              :column="1"
            border
          >
            <el-descriptions-item>
              <template slot="label">
                <i class="el-icon-user-solid"></i>
               买家
              </template>
              {{item.BUYER_NAME}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <div class="cell-item">
                  <i class="el-icon-time"></i>
                  评论时间
                </div>
              </template>
              {{item.EVA_TIME}}
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <div class="cell-item">
                  <i class="el-icon-star-on"></i>
                  评分
                </div>
              </template>
              <div class="block">
                <el-rate
                  v-model="value2"
                  :colors="colors">
                </el-rate>
              </div>
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                <div class="cell-item">
                  <i class="el-icon-chat-square"></i>
                  评论
                </div>
              </template>
              {{item.EVALUATION}}
            </el-descriptions-item>
          </el-descriptions>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import api from '../main.js'
export default {
  name: "Commodity",
  data() {
    return {
      value2: 5,
      CommodityInfo: {
        PUBLISH_TIME: '',
        COMMODITY_NAME:'',
        DESCRIPTION:'',
        PRICE: '',
        COMMODITY_ID: '',
        PICTURE_URL: ''
      },
      evaluations:[{
        COMMODITY_ID:'',
        BUYER_ID:'',
        EVALUATION:'',
        EVA_TIME:''
      }],
      activeIndex2: '1',
      activeName: 'first',
      url: 'https://s3.bmp.ovh/imgs/2022/11/07/423753b070807ffb.png',
      url2: 'https://s3.bmp.ovh/imgs/2022/10/31/ba36a5e351d6fc0d.jpg',
      avatar: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi-1.rar8.net%2F2022%2F9%2F28%2F993458cd-823f-4c9d-bf72-c4a3e267e343.jpg&refer=http%3A%2F%2Fi-1.rar8.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670515052&t=63fb559f31f502bcdd1e134b96111426',
      dialogFormVisible: false,
      formLabelWidth: '120px'
    }
  },

  methods: {
    async GetInfo() {
      var cid=this.$route.params.id
      var infogetter = await axios.get( api + '/api/v1/CommodityInfoService/CommodityInfo?CommodityID='+cid)
      this.CommodityInfo = infogetter.data
      var mm = await axios.get(api+'/api/v1/CommodityInfoService/CommodityEvaluation?CommodityID='+cid)
      console.log(mm.data)
      this.evaluations=mm.data

      console.log(this.CommodityInfo)
    },
    async addtowishlist(){
      const commodityid = this.CommodityInfo.COMMODITY_ID
      var status = await axios.get(api+'/api/v1/WishListService/AddWishList?CommodityID='+commodityid+'&UserID='+document.cookie)
      console.log(status.data)
      if (status.data.STATUS == -1){
        return this.$message({
          type: 'error',
          message: '愿望单中已包含该游戏！'
        })
      }
      else if (status.data.STATUS == 1){
        return this.$message({
          type: 'success',
          message: '添加成功！'
        })
      }
    },
    async addtoshoppingcart(){
      const commodityid = this.CommodityInfo.COMMODITY_ID
      var status = await axios.get(api+'/api/v1/ShoppingCartService/AddShoppingCart?CommodityID='+commodityid+'&UserID='+document.cookie)
      console.log(status.data)
      if (status.data.STATUS == -1){
        return this.$message({
          type: 'error',
          message: '购物车中已包含该游戏！'
        })
      }
      else if (status.data.STATUS == 1){
        return this.$message({
          type: 'success',
          message: '添加成功！'
        })
      }
    },
    quit() {
      document.cookie = "blank"
      this.$router.push('/Login')
    },
    GetCookie() {
      console.log(document.cookie)
    },
    DeleteCookie() {
      document.cookie = "blank"
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    IsCookie() {
      if (document.cookie == "blank" || document.cookie == "")
        this.$router.push('/Login')
    }
  },
  mounted(){
    this.IsCookie()
    this.GetInfo()
  }
}
</script>

<style scoped>

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}
.info{
  background-image: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-09-21%2F5f680a1ab0f99.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670411799&t=5fac79d575355e3fa4d405b9bbe0681f");
  background-size:100%;
  height: 100%;
  width: 100%;
  position: static;
  border-color: transparent;
}
.text-wrapper {
  word-break: break-all;
  word-wrap: break-word
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
.item {
  padding: 18px 0;
}
/*.box-card {
  margin-top: 20px;
  width: 960px;
}*/
.el-descriptions {
  margin-top: 20px;
}
.cell-item {
  display: flex;
  align-items: center;
}
.margin-top {
  margin-top: 20px;
}
</style>
