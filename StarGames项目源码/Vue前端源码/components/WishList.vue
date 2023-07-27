<template>
  <div class="wishlist">
    <el-menu
      :router="true"
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
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
        <span class="span_style">我的心愿单</span>
        <el-button @click="$router.push('/')" style="color:black;font-family: 微软雅黑;font-size:24px;float: right; padding: 3px 0" type="text">返回首页</el-button>
      </div>
      <div style="color:white;font-family: 微软雅黑;margin-top: 50px">
        <template>
          <el-table
            :data="tableData"
            border
            style="width: 100%;
            height :100%"
            >
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
              width="250">
            </el-table-column>
            <el-table-column
              prop="GENRES"
              label="游戏分类"
              width="250">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="success" @click="handleAdd(scope.$index, scope.row)">
                  添加到购物车<i class="el-icon-delete2 el-icon--right"></i>
                </el-button>
                <el-button type="danger" @click="handleDelete(scope.$index, scope.row)">
                  删除<i class="el-icon-delete2 el-icon--right"></i>
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </div>
    </el-card>
  </div>
</template>

<script>
import api from '../main.js'
export default {
  name: "WishList",
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
      }],
      tableData: [],
      multipleSelection: []
    }
  },
  methods:
    {
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleDelete(index, row){
        var CommodityID=this.tableData[index].COMMODITY_ID
        let that=this
        this.$confirm('此操作将从愿望单中移除该商品, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.get(api+'/api/v1/WishListService/WishListRemove?BuyerID='+document.cookie+'&CommodityID=' + CommodityID)
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
      },
      handleAdd(index, row){
        var CommodityID=this.tableData[index].COMMODITY_ID
        let that=this
        axios.get(api+'/api/v1/ShoppingCartService/AddShoppingCart?CommodityID=' + CommodityID+"&UserID="+document.cookie)
          .then(function (response) {
            if(response.data.STATUS!==1)
            {
              alert(response.data.REASON)
            }
            that.GetInfo()
          })
          .catch(function (error) {
            this.$message({
              type: 'error',
              message: '操作失败'
            })
          })
      },
      async GetInfo() {
        var mm = await axios.get(api+'/api/v1/WishListService/WishListItems?BuyerID='+document.cookie)
        console.log(mm.data)
        this.games=mm.data
        let element
        for (element of this.games) {
          element.url='/#/Commodity/'+element.COMMODITY_ID
        }
        this.tableData=mm.data
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
      remove(param){
        console.log(param)
        let that=this
        this.$confirm('此操作将从心愿单中移除该商品, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.post(api+'/api/v1/WishListService/WishListRemove?BuyerID='+document.cookie+'&CommodityID='+param)
            .then(function (response){
              console.log(response)
              that.GetInfo()
            })
            .catch(function (error){
              this.$message({
                type:'error',
                message:'操作失败'
              })
            })
        })
      },
      IsCookie() {
        console.log(document.cookie)
        if(document.cookie=="blank"||document.cookie=="")
          this.$router.push('/Login')
      },
      quit() {
        document.cookie = "blank"
        this.$router.push('/Login')
      },
      shop(param){
        let that=this
        axios.post(api+'/api/v1/ShoppingCartService/CheckCart?BuyerID='+document.cookie+'&CommodityID='+param)
          .then(function (response){
            console.log(response)
            if(response.data==true)
              that.$router.push('/ShoppingCart')
            else{
              that.$confirm('该商品未添加至购物车，是否添加？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                axios.post(api+'/api/v1/ShoppingCartService/Wish2Cart?BuyerID='+document.cookie+'&CommodityID='+param)
                  .then(function (response){
                    console.log(response)
                    if(response.data==true)
                      that.$router.push('/ShoppingCart')
                  })
              })
            }
          })
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

.wishlist{
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
