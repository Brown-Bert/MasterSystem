<template>
  <body>
    <el-container class="container" v-loading="loading">
      <el-header style="font-size: 40px;font-family: 'Arial, Helvetica, sans-serif';font-weight: bolder;">英语作文修改
      </el-header>
      <el-container>
        <!-- <el-aside width="200px"></el-aside> -->
        <el-main>
          <el-form label-position="top" :model="Form" :rules="rules" ref="Form" label-width="120px"
            class="demo-ruleForm">
            <!-- <div style="font-size: 20px; font-family: 'Arial, Helvetica, sans-serif';">请在此处输入您问题</div> -->
            <el-form-item prop="question">
              <el-input v-focus
                class="el-input-inner" type="textarea" v-focus placeholder="请在此处输入您的作文" resize="none"
                :autosize="{minRows:6, maxRows:10}" v-model="Form.question"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button style="width: 100%;" type="primary" @click="fSubmitForm">提交</el-button>
              <!-- <el-button @click="resetForm('ruleForm')">重置</el-button> -->
            </el-form-item>
            <div style="font-size: 20px; font-family: 'Arial, Helvetica, sans-serif';">修改的作文</div>
            <el-form-item prop="answer">
              <el-input placeholder="此处将展示修改之后的作文" resize="none" readonly class="el-input-inner" :rows="6" type="textarea"
                v-model="answer">
              </el-input>
            </el-form-item>
          </el-form>
        </el-main>
        <!-- <el-aside width="200px"></el-aside> -->
      </el-container>
    </el-container>
    <!-- <div class="container">
        <form id="essay-form">
            <label for="essay">输入您的文章：</label>
            <textarea id="essay" required></textarea>
            <button type="submit">提交</button>
        </form>
        <div class="dialogue">
          <label for="essay">修改结果：</label>
          <textarea id="essay" required></textarea>
        </div>
    </div> -->
  </body>
</template>

<script>
  import {
    postMapping
  } from '../utils/requestUtils'
  export default {
    data() {
      return {
        Form: {
          question: ''
        },
        answer: '',
        EnglishQuestionPath: '',
        loading: false
      }
    },
    mounted() {
      const pathJson = require('../../static/path')
      const {
        basePath,
        EnglishQuestionPath
      } = pathJson
      this.EnglishQuestionPath = basePath + EnglishQuestionPath
    },
    directives: {
      focus: {
        inserted: function(el) {
          el.children[0].focus();
        }
      }
    },
    methods: {
      fSubmitForm() {
        // var id = Encrypt(this.ValidateForm.idcard)
        console.log('发送')
        this.loading = true
        var json = {
          message: this.Form.question
        }
        // formMap.set('username', this.ValidateForm.name)
        // formMap.set('password', this.ValidateForm.password)
        var logstate
        console.log('result123')
        var result = postMapping(this.EnglishQuestionPath, json)
        console.log('result', result.then())
        result.then(res => {
          // logstate = res
          console.log('res', res)
          this.answer = res.data
          this.loading = false
        })
      },
    },
  }
</script>

<style scoped>
  body {
      font-family: Arial, sans-serif;
      font-size: 18px;
      background-color: #f3f3f3;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      // height: 90vh;
  }

  .container {
    max-width: 1000px;
    height: 90%;
    width: 800px;
    padding: 40px;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 10px 10px 15px 2px gray;
  }

  .demo-ruleForm /deep/ .el-input-inner {
    font-family: Arial, Helvetica, sans-serif;
    font-size: 18px;
    border: 2px lightblue solid;
    box-shadow: 10px 10px 15px 2px gray;
    border-radius: 10px;
    margin-top: 10px;
    resize: none;
  }
</style>
