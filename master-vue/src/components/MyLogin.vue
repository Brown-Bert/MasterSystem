<template>
  <div>
    <div style="font-size: 50px;font-family: 'microsoft sans serif';margin-top: -50px; font-family: '宋体';font-weight: bolder;">点餐系统</div>

    <div :style="'background-image: url('+url+');height: 670px;background-repeat: no-repeat; background-size: 100% 100%;'">
      <el-row type="flex" justify="center">
        <el-col :span="8">
          <div class="grid-content"></div>

        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :span="6">
          <el-card shadow="always" style="border-radius: 10%;">
            <h1 style="text-align: center;">L O G I N</h1>
            <!-- <el-divider></el-divider> -->
            <el-form :model="ValidateForm" ref="ValidateForm" label-width="100px" class="demo-ruleForm">
              <!-- 用户名 -->
              <el-form-item
                label="用户名"
                prop="name"
                :rules="[
                  { required: true, message: '用户名不能为空'}
                ]"
              >
                <el-input type="text" v-focus placeholder="请输入用户名" v-model="ValidateForm.name" autocomplete="off"></el-input>
              </el-form-item>
              <!-- 密码 -->
              <el-form-item
                label="密码"
                prop="password"
                :rules="[
                  { required: true, message: '密码不能为空'}
                ]"
              >
                <el-input @keyup.enter.native="fSubmitForm" placeholder="请输入密码" v-model="ValidateForm.password" show-password></el-input>
              </el-form-item>
              <!-- 图形验证码 -->
              <!-- <el-form-item prop="sidentify">
                <el-col :span="14">
                <el-input placeholder="sidentify" v-model="ValidateForm.password" @keyup.enter.native="submitForm('ValidateForm')">
                </el-input>
                </el-col>
                <el-col :span="8">
                    <my-canvas></my-canvas>
                </el-col>
              </el-form-item> -->
              <!-- 滑动验证码 -->
              <el-form-item>
                <!-- <button @click="btn">验证</button>
                <page-slide ref="slide"></page-slide> -->
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="fSubmitForm">提交</el-button>
                <el-button @click="fResetForm('ValidateForm')">重置</el-button>
              </el-form-item>
            </el-form>
            <!-- <button @click="btn">验证</button> -->
            <page-slide @materials='fGetpass' ref="slide"></page-slide>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import router from '../router'
import Slide from './Slide'
import { postMapping } from '../utils/requestUtils'

export default {
  data () {
    return {
      ValidateForm: {
        name: '',
        password: ''
      },
      loginPath: '',
      url: ''
    }
  },
  mounted () {
    const pathJson = require('../../static/path')
    const { basePath, loginPath } = pathJson
    this.loginPath = basePath + loginPath
  },
  methods: {
    fSubmitForm () {
      this.$refs['ValidateForm'].validate((valid) => {
        if (valid) {
          this.$refs.slide.outerVisible = true
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    fResetForm (formName) {
      this.$refs[formName].resetFields()
    },
    async fGetpass (data) {
      if (data === 'pass') {
        // var formMap = new Map()
        var json = {username: this.ValidateForm.name, password: this.ValidateForm.password}
        // formMap.set('username', this.ValidateForm.name)
        // formMap.set('password', this.ValidateForm.password)
        var logstate
        var result = postMapping(this.loginPath, json)
        // console.log('result', result.then())
        await result.then(res => {
          logstate = res
        })
        if (logstate.flag === 'true') {
          this.$cookieStore.setCookie('username', this.ValidateForm.name)
          this.$cookieStore.setCookie('token', logstate.token)
          this.$store.commit('fSetToken', logstate.token)
          this.$store.commit('fSetUserName', this.ValidateForm.name)
          router.push('wellcome')
        } else {
          this.fOpen()
        }
      } else {
        console.log('error submit!!')
        return false
      }
    },
    fOpen () {
      this.$message({
        showClose: true,
        message: '账号密码输入错误',
        type: 'error'
      })
    }
  },
  components: {
    'page-slide': Slide
  },
  directives: {
    focus: {
      inserted: function (el) {
        el.querySelector('input').focus()
      }
    }
  }
}
</script>

<style lang="css" scoped="scoped">
  .content{
    margin: 0 auto;
  }
  .grid-content{
    border-radius: 4px;
    min-height: 80px;
  }
  .el-row{
    margin-bottom: 20px;
  }
</style>
