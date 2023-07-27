<template>
  <div class="login">
    <el-card class="box-card">
      <span style="font-size: 20px;font-weight: 1000">新用户注册</span>
      <el-form ref="form" style="margin-top: 30px" :model="form" label-width="100px" :label-position="labelPosition" >
        <el-form-item label="您的手机号">
          <el-input placeholder="请输入手机号" type="number" v-model="form.id"></el-input>
        </el-form-item>
        <el-form-item label="您的密码">
          <el-input placeholder="请输入密码" v-model="form.passwd" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input placeholder="请再次输入密码" v-model="form.passwd_pw2" show-password></el-input>
        </el-form-item>
        <el-form-item label="身份类型">
        <el-radio v-model="radio" label="0">我是买家</el-radio>
        <el-radio v-model="radio" label="1">我是卖家</el-radio>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="margin-left:24%" @click="onSubmit">注册</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import api from '../main.js'
export default {
  name: "Register",
  data () {
    return {
      labelPosition: 'left',
      form: {
        id: '',
        passwd: '',
        passwd_pw2: ''
      },
      radio:'0'
    }
  },
  methods: {
    async onSubmit() {
      const userPhone = this.form.id
      const userPassword = this.form.passwd
      const userRepeat = this.form.passwd_pw2
      const userType = this.radio
      console.log(userPhone, userPassword, userRepeat,userType)
      if (!userPhone) {
        return this.$message({
          type: 'error',
          message: '手机号不能为空！'
        })
      }
      if (!userPassword) {
        return this.$message({
          type: 'error',
          message: '密码不能为空！'
        })
      }
      if (userPassword != userRepeat) {
        return this.$message({
          type: 'error',
          message: '两次输入密码不一致！'
        })
      }
      var status = await axios.post(api+'/api/v1/LoginService/Register?PhoneNumber='+userPhone+'&Password='+userPassword+'&UserType='+userType)
      if (status.data == false){
        return this.$message({
          type: 'error',
          message: '该手机号已经被注册，请重试！'
        })
      }
      else if (status.data == true){
        this.$router.push('/Login')
        return this.$message({
          type: 'success',
          message: '注册成功！'
        })

      }

    }
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}
.el-card__body{
  background:RGBA(255,255,255,255);
}
.box-card {
  width: 480px;
  margin-top: 18%;
  margin-left: 33%;
}
.login{
  background-image: url("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F3%2F541f9bb537d8b.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1671518450&t=59b28eb73ed2d5b0b78cac35b06a2fc9");
  background-size:100%;
  height: 100%;
  position: fixed;
  width: 100%
}
</style>
