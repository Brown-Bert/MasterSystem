<template>
  <body>
    <el-container class="container" v-loading="loading">
      <el-header style="font-size: 40px;font-family: 'Arial, Helvetica, sans-serif';font-weight: bolder;">专业课答疑页面
      </el-header>
      <el-container>
        <!-- <el-aside width="200px"></el-aside> -->
        <el-main>
          <el-form label-position="top" :model="Form" :rules="rules" ref="Form" label-width="120px"
            class="demo-ruleForm">
            <!-- <div style="font-size: 20px; font-family: 'Arial, Helvetica, sans-serif';">请在此处输入您问题</div> -->
            <el-form-item prop="question">
              <el-input v-focus @keydown.native="listen($event)"
                @keyup.enter.native="fSubmitForm" class="el-input-inner" type="textarea" v-focus placeholder="请在此处输入您问题"
                resize="none" :autosize="{minRows:1, maxRows:3}" v-model="Form.question"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button style="width: 100%;" type="primary" @click="fSubmitForm">提交</el-button>
              <!-- <el-button @click="resetForm('ruleForm')">重置</el-button> -->
            </el-form-item>
            <div style="font-size: 20px; font-family: 'Arial, Helvetica, sans-serif';">答案</div>
            <el-form-item prop="answer">
              <el-input placeholder="此处将展示结论" resize="none" readonly class="el-input-inner" :rows="8" type="textarea"
                v-model="answer">
              </el-input>
            </el-form-item>
          </el-form>
        </el-main>
        <!-- <el-aside width="200px"></el-aside> -->
      </el-container>
    </el-container>
    <!-- <body>
      <div class="container">
          <h1>专业课答疑页面</h1>
          <form action="#" ref="Form" id="questionForm">
              <label for="question">请在此处输入您的问题：</label>
              <textarea v-model="Form.question" id="question" name="question"></textarea>
              <button @click="fSubmitForm" type="submit">提交问题</button>
          </form>
          <div id="output">
              <h2>答案：</h2>
              <p id="answer"></p>
          </div>
          <textarea style="height: 100px; width: 500px;" v-model="answer"></textarea>
      </div>
  </body> -->
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
        ProfessionalQuestionPath: '',
        loading: false
      }
    },
    mounted() {
      const pathJson = require('../../static/path')
      const {
        basePath,
        ProfessionalQuestionPath
      } = pathJson
      this.ProfessionalQuestionPath = basePath + ProfessionalQuestionPath
    },
    directives: {
      focus: {
        inserted: function(el) {
          el.children[0].focus();
        }
      }
    },
    methods: {
      // tab(obj){
      //   if (event.keyCode == 9)
      //   {
      //      obj.value = obj.value + "  "; // 跳几格由你自已决定
      //      event.returnValue = false;
      //   }
      // },
      listen(event) {
        if (event.keyCode === 13) {
          // this.send() // 发送文本
          event.preventDefault() // 阻止浏览器默认换行操作
          // return false
        } else if (event.keyCode === 9) {
          console.log('table')
          this.Form.question += "    "; // 跳几格由你自已决定
          event.returnValue = false;
        }
      },
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
        var result = postMapping(this.ProfessionalQuestionPath, json)
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

<style lang="scss" scoped>
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
