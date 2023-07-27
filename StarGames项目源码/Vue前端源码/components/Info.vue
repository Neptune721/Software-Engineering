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

    <el-card style="margin-left: 350px;margin-top: 50px;height:800px;background-color:whitesmoke;border-color: transparent;width:960px">
      <div style="color:#2c3e50;font-family: 微软雅黑 Light;font-size:16px;border-color: transparent">
        <span style="font-family: 微软雅黑;font-size:30px">个人信息<br></span>
        <div class="block" style="margin-top: 30px"><el-avatar :size="125" :src="UserInfo.Avatar" ></el-avatar></div>
        <el-link href="http://43.143.159.150:8080/#/upload/" target="_blank" style="margin-left: 15px">点击此处更换头像</el-link>
<!--        <el-link href="http://localhost:8081/#/upload/" target="_blank" style="margin-left: 15px">点击此处更换头像</el-link>-->
        <span></span>
        <div style="margin-top: -100px;margin-left: 170px;font-family: 华文中宋;font-size:18px">
          <div>UID: {{UserInfo.USER_ID}}</div><br>
          <div>昵称:  {{UserInfo.BUYER_NAME}}</div><br>
        </div>

        <el-descriptions class="margin-top" style="margin-top: 35px" :column="1" :size="size" border>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-user"></i>
              昵称
            </template>
            {{UserInfo.BUYER_NAME}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-mobile-phone"></i>
              手机号
            </template>
            {{UserInfo.USER_PHONE}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location-outline"></i>
              邮箱
            </template>
            {{UserInfo.MAIL}}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-tickets"></i>
              账户类型
            </template>
            <el-tag size="small">买家</el-tag>
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-office-building"></i>
              联系地址
            </template>
            {{ UserInfo.Address }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-office-building"></i>
              生日
            </template>
            {{ UserInfo.BIRTHDAY }}
          </el-descriptions-item>
        </el-descriptions>
      </div>
      <el-button type="primary" style="margin-top: 30px" @click="dialogFormVisible = true">修改个人信息</el-button>

      <el-dialog title="修改个人信息" :visible.sync="dialogFormVisible" append-to-body>
        <el-form :model="form">
          <el-form-item label="昵称" :label-width="formLabelWidth">
            <el-input placeholder="请输入修改后的昵称" v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="手机号" :label-width="formLabelWidth">
            <el-input placeholder="请输入要修改的手机号" v-model="form.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" :label-width="formLabelWidth">
            <el-input placeholder="请输入要修改的邮箱" v-model="form.mail" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="地址" :label-width="formLabelWidth">
            <el-input placeholder="请输入要修改的地址" v-model="form.address" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="生日" :label-width="formLabelWidth">
            <template>
              <div class="block">
                <el-date-picker
                  v-model="form.birthday"
                  type="date"
                  placeholder="选择日期">
                </el-date-picker>
              </div>
            </template>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="onsubmit()">确 定</el-button>
        </div>

      </el-dialog>

      <el-button type="primary" style="margin-top: 30px;margin-left: 600px" @click="dialogFormVisible2 = true">修改密码</el-button>
      <el-dialog title="修改密码" :visible.sync="dialogFormVisible2" append-to-body>
        <el-form :model="codeform">
          <el-form-item label="旧密码" :label-width="formLabelWidth">
            <el-input show-password placeholder="请输入修改前的密码" v-model="codeform.password" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="新密码" :label-width="formLabelWidth">
            <el-input show-password placeholder="请输入修改后的密码" v-model="codeform.newpassword" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认新密码" :label-width="formLabelWidth">
            <el-input show-password placeholder="请再次输入修改后的密码" v-model="codeform.newpassword2" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="验证码" :label-width="formLabelWidth">
            <el-input placeholder="请输入发送到邮箱的验证码" v-model="codeform.checkcode" autocomplete="off"> <el-button slot="append" type="success" @click="sendcode">向邮箱发送验证码</el-button></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible2 = false">取 消</el-button>
          <el-button type="primary" @click="changePasswd()">确 定</el-button>
        </div>

      </el-dialog>
    </el-card>

  </div>
</template>

<script>
import api from '../main.js'
export default {
  name: "Info",
  data() {
    return {
      UserInfo: {
        Avatar: '',
        Address:'',
        BIRTHDAY: '',
        USER_PHONE:'',
        USER_ID: '',
        BUYER_NAME: '',
        MAIL: ''
      },
      activeIndex2: '1',
      activeName: 'first',
      url: 'https://s3.bmp.ovh/imgs/2022/11/07/423753b070807ffb.png',
      url2: 'https://s3.bmp.ovh/imgs/2022/10/31/ba36a5e351d6fc0d.jpg',
      avatar: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01b90a6167e0ac11013f22cf1c99ef.png%401280w_1l_2o_100sh.png&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1671434829&t=5747b4a9937b6b81a7a3af60df7b0377',
      dialogFormVisible: false,
      dialogFormVisible2: false,
      form: {
        name: '',
        address: '',
        phone: '',
        birthday: '',
        mail: '',
        date: ''
      },
      codeform: {
        password: '',
        newpassword: '',
        newpassword2: '',
        checkcode: ''
      },
      formLabelWidth: '120px'
    }
  },

  methods: {
    async GetInfo() {
      var infogetter = await axios.get(api + '/api/v1/BuyerInfoService/BuyerInfo?BuyerID='+document.cookie)
      this.UserInfo = infogetter.data
      console.log(this.UserInfo)
    },
    async sendcode(){
      var email = this.UserInfo.MAIL
      await axios.get(api+'/api/v1/test/getCheckCode?email='+email)
    },
    async onsubmit() {
      let user = {
        BuyerID: document.cookie,
        BuyerName: this.form.name,
        PhoneNumber: this.form.phone,
        Mail:this.form.mail,
        Address:this.form.address,
        birthday:this.form.birthday,
      }
      this.dialogFormVisible = false
      let url1 = api+"/api/v1/BuyerInfoService/BuyerInfo"
      axios.put(url1, user)
        .then(function(result){
          console.log(result.data)

        })
      await this.$router.push('/Login')
    },
    async changePasswd(){
      var oldPassword = this.codeform.password
      var userPassword = this.codeform.newpassword
      var userRepeat = this.codeform.newpassword2
      var checkcode = this.codeform.checkcode
      if (userPassword != userRepeat) {
        return this.$message({
          type: 'error',
          message: '两次输入密码不一致！'
        })
      }
      var status=await axios.post(api+"/api/v1/ChangeService/changePasswd?BuyerID="+document.cookie+"&OldPasswd="+oldPassword+"&NewPasswd="+userPassword+"&Code="+checkcode)
      if(status.data == 0)
      {
        await this.$router.push('/Login')
      }
      else if(status.data == -1){
        return this.$message({
          type: 'error',
          message: '旧密码错误！'
        })
      }
      else if(status.data == -2){
        return this.$message({
          type: 'error',
          message: '验证码错误！'
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

<style>


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
  position: fixed;
  width: 100%;
  border-color: transparent;
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
</style>
