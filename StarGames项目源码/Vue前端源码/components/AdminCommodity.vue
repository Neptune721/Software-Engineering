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
        <el-row :gutter="20">
          <el-col :span="10">
            <el-input v-model="inputID" style="width:200px;margin-left: 110px;margin-top: 10px" placeholder="搜索商品ID" @keyup.enter.native="handleFilter" clearable></el-input>
            <el-button icon="el-icon-search" type="primary" :disabled="this.inputID==''" @click="handleFilter" ></el-button>
          </el-col>
          <el-col :span="26">
            <el-button style="margin-top: 10px" type="primary" plain @click="GetInfo">查看全部</el-button>
          </el-col>
        </el-row>
      </el-menu>
      <el-drawer
        size='35%'
        :visible.sync="drawer"
        :direction="direction"
        :before-close="handleClose">
        <div style="margin-left: 30px">
          <span style="font-family: 微软雅黑;font-size:30px">游戏信息<br></span>
          <div class="block" style="margin-top: 30px"><el-image style="width: 250px; height: 150px;position:relative" :src="commodities[0].PICTURE_URL" ></el-image></div>
          <span></span>
          <div style="margin-top: -100px;margin-left: 280px;font-family: 华文中宋;font-size:18px">
            <div>游戏编号: {{commodities[0].COMMODITY_ID}}</div><br>
            <div>游戏名称:  {{commodities[0].COMMODITY_NAME}}</div><br>
          </div>
        </div>

        <el-descriptions class="description" style="margin-top: 35px" :column="1" size="medium" border>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-tickets"></i>
              游戏编号
            </template>
            {{commodities[0].COMMODITY_ID}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-news"></i>
              游戏名称
            </template>
            {{commodities[0].COMMODITY_NAME}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-price-tag"></i>
              价格
            </template>
            {{ commodities[0].PRICE }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              发布者
            </template>
            {{commodities[0].PUBLISHER_NAME}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-dessert"></i>
              游戏描述
            </template>
            {{commodities[0].DESCRIPTION}}
          </el-descriptions-item>
        </el-descriptions>
      </el-drawer>

      <div style="margin-top: 25px">
        <el-table
          :data="commodities"
          style="margin-top:2%;width: 100%"
          :default-sort = "{prop: 'NAME', order: 'ascending'}"
          max-height="540">
          <el-table-column
            prop="COMMODITY_ID"
            label="游戏编号"
            sortable
            width="160">
          </el-table-column>
          <el-table-column
            prop="COMMODITY_NAME"
            label="游戏名称"
            sortable
            width="160">
          </el-table-column>
          <el-table-column
            prop="PRICE"
            label="价格"
            sortable
            width="140">
          </el-table-column>
          <el-table-column
            prop="PUBLISHER_NAME"
            label="发布者"
            width="160">
          </el-table-column>
          <el-table-column
            prop="DESCRIPTION"
            label="游戏描述"
            width="250">
          </el-table-column>
          <el-table-column
            fixed="right"
            label="操作"
            width="160">
            <template slot-scope="scope">
              <el-button
                @click.native.prevent="withdrawCommodity(scope.$index, commodities)"
                type="text"
                size="small">
                下架游戏
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
import api from '../main.js'
export default {
  name: "AdminCommodity",
  data() {
    return {
      inputID:'',
      activeIndex2: '3',
      activeName: 'first',
      url: 'https://s3.bmp.ovh/imgs/2022/11/07/423753b070807ffb.png',
      drawer: false,
      direction: 'ltr',
      commodities:[{
        COMMODITY_ID:'',
        COMMODITY_NAME:'',
        PRICE:'',
        DESCRIPTION:'',
        PUBLISHER_NAME:'',
        PICTURE_URL:'',
      }]
    }
  },
  methods:
    {
      async GetInfo() {
        let mm = await axios.get(api+'/api/v1/AdminService/CommodityListItems')
        console.log("commodities:",mm.data)
        this.commodities=mm.data
      },
      async handleFilter() {
        console.log(this.inputID);
        let mm = await axios.get(api+'/api/v1/AdminService/QueryCommodity?CommodityID='+this.inputID)
        console.log("commodities:",mm.data)
        this.commodities=mm.data
        this.drawer=true;
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      withdrawCommodity(index, rows) {
        this.$confirm('此操作将下架该游戏, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          this.$message({
            type: 'success',
            message: '下架成功!'
          });
          let comID=this.commodities[index].COMMODITY_ID
          let res = await axios.get(api+'/api/v1/AdminService/WithdrawCommodity?CommodityID='+comID)
          if(res) {rows.splice(index, 1);}
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消下架'
          });
        });
      },
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

.admin{
  background-image: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-09-21%2F5f680a1ab0f99.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1670411799&t=5fac79d575355e3fa4d405b9bbe0681f");
  background-size:100%;
  height: 100%;
  width: 100%;
  border-color: transparent;
}
.description{
  margin-left: 24px;
  width: 90%;
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

