<template>
  <div class="shopping-cart">
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
    <el-card style="border-color:transparent;background-color:white;margin-left:5%;margin-top:5%;width:1100px">
      <div style="color:white;font-family: 微软雅黑;font-size:30px;border-color: transparent">
        <span class="span_style">我的购物车</span>
        <el-button @click="$router.push('/')" style="color:black;font-family: 微软雅黑;font-size:24px;float: right; padding: 3px 0" type="text">返回首页</el-button>
      </div>
      <div style="color:white;font-family: 微软雅黑;margin-top: 50px">
        <template>
          <el-table
            :data="tableData"
            border
            style="width: 100%;
            height :100%"
            @selection-change="handleSelectionChange">
            <el-table-column
              type="selection"
              width="50">
            </el-table-column>
            <el-table-column
              prop="PICTURE_URL"
              label="游戏图片"
              width="250">
              <template slot-scope="scope">
                <div>
                  <img :src="scope.row.PICTURE_URL" style="height: 100px;width: 250px">
                </div>
              </template>
            </el-table-column>
            <el-table-column
              prop="COMMODITY_NAME"
              label="游戏名称"
              width="180">
            </el-table-column>
            <el-table-column
              prop="PRICE"
              label="游戏价格"
              width="180">
            </el-table-column>
            <el-table-column
              prop="GENRES"
              label="游戏分类"
              width="180">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="danger" @click="handleDelete(scope.$index, scope.row)">
                  删除<i class="el-icon-delete2 el-icon--right"></i>
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </template>
        <el-input placeholder="请输入优惠券码" v-model="coupon" class="input-with-select" style="width: 500px;margin-left: 0px;margin-top: 0px;"></el-input>
        <el-button type="text" icon="el-icon-goods" style=" float:right;margin-top: 10px" @click="open" >点击结算购物车</el-button>
        </div>

    </el-card>
  </div>
</template>

<script>
import async from "async";
import api from '../main.js'
export default {
  name: "ShoppingCart",
  data() {
    return {
      input: '',
      activeIndex2: '1',
      activeName: 'first',
      url: 'https://s3.bmp.ovh/imgs/2022/11/07/423753b070807ffb.png',
      games: [{
        PICTURE_URL: 'https://s3.bmp.ovh/imgs/2022/11/07/423753b070807ffb.png',
        COMMODITY_NAME: '原神',
        PRICE: 648,
        DESCRIPTION: '',
        PUBLISHER_NAME: '',
        COMMODITY_ID: '',
        GENRES: ['', ''],
        url: '',
        Checked:false
      }],
      tableData: [],
      multipleSelection: [],
      coupon:''
    }
  },
  methods:
    {
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      async BuyItem() {
        var sum = 0
        var status = 1
        for (let item of this.$data.multipleSelection) {
          sum = sum + item.PRICE
        }

        console.log(this.coupon)
        if(this.coupon==='TJSSE2022')
          sum=sum*0.88

        for (let item of this.$data.multipleSelection) {
          var CommodityID = item.COMMODITY_ID
          await axios.get(api+'/api/v1/GameLibService/AddToGameLibrary?BuyerID='+document.cookie+'&CommodityID=' + CommodityID)
            .then(function (response) {
              if (response.data.STATUS !== 1) {
                status = 0
              }
            })
            .catch(function (error) {
              this.$message({
                type: 'error',
                message: '操作失败'
              })
            })
        }
        console.log(status)
        if (status === 1) {
          window.open(api+'/api/alipay/page?subject=购买游戏&total=' + sum)
        }
      },
      async GetInfo() { //cookie
        var mm = await axios.get(api+'/api/v1/ShoppingCartService/ShoppingCartItems?BuyerID='+document.cookie)
        this.games=mm.data
        let element
        for (element of this.games) {
          element.url='/#/Commodity/'+element.COMMODITY_ID
        }
        this.tableData=mm.data
      },
      ToLink1(){
        this.$router.push('/#/Home')
      },
      open() {
        var Status=true
        var str='您将购买以下游戏: '
        var sum=0
        for(let item of this.$data.multipleSelection){
          str=str+item.COMMODITY_NAME+"  "
          sum=sum+item.PRICE
        }
        console.log(this.coupon)
        if(this.coupon==='TJSSE2022')
          sum=sum*0.88
        console.log(Status)
        for(let item of this.$data.multipleSelection){
          var mm = axios.get(api+'/api/v1/CommonService/CanPurchase?CommodityID='+item.COMMODITY_ID+'&UserID='+document.cookie)
            .then(function (response) {
              console.log(response)
              if (response.data===true) {
                Status=false
              }
            })
            .catch(function (error) {
              this.$message({
                type: 'error',
                message: '操作失败'
              })
            })
        }
        str=str+"  ,您需要付款的总金额为:"+sum
        this.$confirm(str, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(Status===false) {
            this.$message({
              type: 'success',
              message: "对不起，您已经拥有想要付款当中游戏，购买失败",
            });
          }
          else
          {
            this.$message({
              type: 'success',
              message: "感谢您的购买",
              callback: this.BuyItem()
            });
          }
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '您已取消结算'
          });
        });
      },
     handleDelete(index, row){
        var CommodityID=this.tableData[index].COMMODITY_ID
        let that=this
        this.$confirm('此操作将从购物车中移除该商品, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
              axios.get(api+'/api/v1/ShoppingCartService/ShoppingCartRemove?BuyerID='+document.cookie+'&CommodityID=' + CommodityID)
                .then(function (response) {
                  alert("删除成功")
                  that.GetInfo()
                })
                .catch(function (error) {
                  this.$message({
                    type: 'error',
                    message: '操作失败'
                  })
                })
        })
      }
    },
  mounted(){
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

.shopping-cart{
  background-image: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-09-21%2F5f680a1ab0f99.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670411799&t=5fac79d575355e3fa4d405b9bbe0681f");
  background-size:100% 100%;
  height: 100%;
  width: 100%;
  position:absolute;
border-color: transparent;
}
.span_style{
  font-family: 微软雅黑,serif;
  color: black;
}
</style>
