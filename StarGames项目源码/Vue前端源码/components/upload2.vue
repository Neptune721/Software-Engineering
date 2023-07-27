<template>
  <div id="app-up">
    <el-card style="margin-left: 50px;margin-top: 50px;background-color:whitesmoke;border-color: transparent;width:420px">

      <span style="font-family: 微软雅黑;font-size:24px">上传商品图片</span>
      <el-upload style="margin-top: 20px"
                 class="upload"
                 drag
                 :name="'iFile'"
                 limit="1"
                 accept=".jpg,.jpeg,.png,.gif"
                 :disabled="isDisabled"
                 :before-upload="beforeAvatarUpload"
                 :http-request="handleUpload"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将要上传的图片拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">
          <p>只能上传jpg，png或gif格式文件，且不超过1GB!</p>
        </div>

      </el-upload>
      <!-- 上传进度条 -->
      <el-progress :percentage="percentage" v-if="percentage<100 && percentage>0"></el-progress>
      <el-progress :percentage="percentage" status="success" v-if="percentage==100"></el-progress>
    </el-card>
  </div>
</template>
<script src="dist/cos-js-sdk-v5.min.js"></script>
<script type="text/javascript">

import COS from "cos-js-sdk-v5";
import api from '../main.js'
export default {
  data() {
    return {
      isDisabled: false,
      percentage: 0,
      originName: '',
      originSize: '',
      uploadFail: false,
      isLoad: 0,
    }
  },
  methods: {
    get(){
      console.log(this.$route.params.id)
    },
    async onsubmit(picName) {
      var picUrl="https://stargames-1314598070.cos.ap-nanjing.myqcloud.com/"+picName
      await axios.post(api+'/api/v1/SellerService/CommodityPic?CommodityID='+this.$route.params.id+'&url='+picUrl)
    },
    handleUpload(e) {
      let _this = this,
        file = e.file
      if (!file) return
      var suffix = file.name.substring(file.name.lastIndexOf("."))
      console.log(suffix)
      var dd = Math.random().toString(36).substr(-10);
      var picName = "pic/" + dd + suffix

      this.onsubmit(picName)
      _this.originName = picName


      console.log(dd + suffix)
      _this.originSize = file.size

      var cos = new COS({
        SecretId: "AKIDkMkupY5TikiU0JVO2QZTAg183oOj2EBB",
        SecretKey: "3uWv51iDUXM8qqrgHi2EHF5jBSccQ1Qg",
      })

      cos.putObject({
        Bucket: "stargames-1314598070",
        Region: "ap-nanjing",
        Key: _this.originName,
        Body: file,
        onProgress: function (progressData) {
          _this.percentage = parseInt(progressData.percent * 100);
          _this.isDisabled = true;
          $('.j-submit').attr('disabled', true);
        }
      }, function (err, data) {
        console.log(err || data);
        if (data && data.Location && data.statusCode == 200) {
          _this.isLoad = 1;
        }
        if (err) {
          layer.msg('上传文件失败', {
            icon: 2
            , time: 1200
            , shade: 0.5
          });
        }
        _this.isDisabled = false;
        _this.uploadFail = true;
        $('.j-submit').attr('disabled', false);
      });
      this.$message('上传成功！');

    },

    beforeAvatarUpload(file) {
      this.uploadFile = file
      this.uploadFileName = file.name
      const isLt2M = file.size / 1024 / 1024 <= 102400
      if (!isLt2M) {
        this.$message.error('上传文件大小不能超过 1GB!')
      }
      return isLt2M
    },
  },
  mounted(){
    this.get()
  }
};
</script>
