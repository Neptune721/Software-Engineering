<template>
  <div class="login">
    <el-card class="box-card">
      <span style="font-size: 20px;font-weight: 1000">统一身份认证</span>
      <el-form ref="form" style="margin-top: 30px" :model="form" label-width="40px" :label-position="labelPosition" >
        <el-form-item label="账号">
          <el-input placeholder="请输入账号" v-model="form.id"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input placeholder="请输入密码" v-model="form.passwd" @keyup.enter.native="onSubmit" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="margin-left:5%" @click="onSubmit">登录</el-button>
          <el-link href="/#/Register" type="primary" style="margin-left:15%"  target="_blank">没有账号？点此处注册</el-link>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import api from '../main.js'
export default {
  data () {
    return {
      labelPosition: 'left',
      form: {
        id: '',
        passwd: ''
      }
    }
  },
  methods: {
    async onSubmit() {
      const userAccount = this.form.id
      const userPassword = this.form.passwd
      if (!userAccount) {
        return this.$message({
          type: 'error',
          message: '账号不能为空！'
        })
      }
      if (!userPassword) {
        return this.$message({
          type: 'error',
          message: '密码不能为空！'
        })
      }
      console.log('用户输入的账号为：', userAccount)
      console.log('用户输入的密码为：', userPassword)

      var status = await axios.post(api+'/api/v1/LoginService/Login?UserID=' + this.form.id + '&Password=' + this.form.passwd)
      console.log(status)
      if(status.data == -1) {
        return this.$message({
          type: 'error',
          message: '用户名或密码错误！'
        })
      }
      else if(status.data == 0){
        this.$router.push('/')
        document.cookie = this.form.id
      }
      else if(status.data == 1){
        this.$router.push('/SellerBackground')
        document.cookie = this.form.id
      }
      else if(status.data == 2){
        this.$router.push('/AdminUser')
        document.cookie = this.form.id
      }
    }
  }
}
</script>
<style>
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}
/*.el-card__body{
  background:RGBA(255,255,255,255);
}*/
.box-card {
  width: 480px;
  margin-top: 18%;
  margin-left: 33%;
}
.login{
  background-image: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F3%2F541f9bb537d8b.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1671518450&t=59b28eb73ed2d5b0b78cac35b06a2fc9");
  background-size:100% 100%;
  height: 100%;
  position: absolute;
  width: 100%
}
</style>
