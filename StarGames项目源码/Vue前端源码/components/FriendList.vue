<template>
  <div class="list">
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
    <el-card style="border-color:transparent;background-color:#182d4a;margin-left:5%;margin-top:2%;width:1250px">
      <div style="color:white;font-family: 微软雅黑;font-size:30px;border-color: transparent">
        <span>好友列表</span>
        <el-input v-model="input" style="width:300px;margin-left: 450px;margin-top: 1%" placeholder="搜索好友" ></el-input>
        <el-button icon="el-icon-search" type="primary"@click="handleFilter"></el-button>
        <el-button @click="$router.push('/')" style="color:white;font-family: 微软雅黑;margin-top: 1%;font-size:20px;float: right" type="text">返回首页</el-button>
      </div>
        <el-table
          :data="friends"
          style="margin-top:2%;width: 100%"
          :default-sort = "{prop: 'NAME', order: 'ascending'}"
          max-height="560">
          <el-table-column
            label="头像"
            width="200">
            <template slot-scope="scope">
                <el-avatar :size="50" :src="scope.row.Avatar"></el-avatar>
            </template>
          </el-table-column>
          <el-table-column
            prop="BUYER_NAME"
            label="昵称"
            sortable
            width="240">
          </el-table-column>
          <el-table-column
            prop="BIRTHDAY"
            label="生日"
            sortable
            width="240">
          </el-table-column>
          <el-table-column
            prop="MAIL"
            label="邮箱"
            width="240">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="160">
            <template slot-scope="scope">
              <el-button
                @click.native.prevent="deleteRow(scope.$index, friends)"
                type="text"
                size="small">
                删除好友
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      <el-button style="margin-top: 30px" type="success">添加好友</el-button>
    </el-card>

  </div>
</template>

<script>
import api from '../main.js'
export default {
  name: "FriendList",
  data() {
    return {
      input:'',
      activeIndex2: '2',
      activeName: 'first',
      url: 'https://s3.bmp.ovh/imgs/2022/11/07/423753b070807ffb.png',
      friends:[{
        Avatar:'',
        BUYER_NAME:'',
        BIRTHDAY:'',
        MAIL:'',
        FRIEND_ID:''
      }]
    }
  },
  methods:
    {
      async GetInfo() {
        let mm = await axios.get(api+'/api/v1/FriendListService/FriendListItems?BuyerID='+document.cookie)
        console.log("friends:",mm.data)
        this.friends=mm.data
      },
      async handleFilter() {
        console.log(this.input);
        console.log(document.cookie);
        let mm = await axios.get(api+'/api/v1/SearchService/SearchByFriendName?BuyerID='+document.cookie+'&name='+this.input)
        console.log("buyers:",mm.data)
        this.friends=mm.data
        this.drawer=true;
      },
      deleteRow(index, rows) {
        this.$confirm('此操作将删除该好友, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          let friendID=this.friends[index].FRIEND_ID
          let res = await axios.get(api+'/api/v1/FriendListService/DeleteFriend?BuyerID='+document.cookie+'&FriendID='+friendID)
          if(res){rows.splice(index, 1);}
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
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
      ToLink1(){
        this.$router.push('/#/Home')
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

.list{
  background-image: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-09-21%2F5f680a1ab0f99.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670411799&t=5fac79d575355e3fa4d405b9bbe0681f");
  background-size:100% 100%;
  height: 100%;
  width: 100%;
  position:absolute;
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

