<template>
  <body>
    <el-container class="container" v-loading="loading">
      <el-header style="font-size: 40px;font-family: 'Arial, Helvetica, sans-serif';font-weight: bolder;">数学问题求解
      </el-header>
      <el-container>
        <!-- <el-aside width="200px"></el-aside> -->
        <el-main>
          <el-form label-position="top" :model="Form" :rules="rules" ref="Form" label-width="120px"
            class="demo-ruleForm">
            <!-- <div style="font-size: 20px; font-family: 'Arial, Helvetica, sans-serif';">请在此处输入您问题</div> -->
            <el-form-item prop="question">
              <el-input v-focus
                class="el-input-inner" type="textarea" v-focus placeholder="请在此处输入您的问题" resize="none"
                :autosize="{minRows:6, maxRows:10}" v-model="Form.question"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button style="width: 100%;" type="primary" @click="fSubmitForm">提交</el-button>
              <!-- <el-button @click="resetForm('ruleForm')">重置</el-button> -->
            </el-form-item>
            <!-- <div style="font-size: 20px; font-family: 'Arial, Helvetica, sans-serif';">答案</div> -->
            <el-form-item prop="answer">
              <el-input placeholder="此处将展示求解过程" resize="none" readonly class="el-input-inner" :rows="8" type="textarea"
                v-model="answer">
              </el-input>
            </el-form-item>
          </el-form>
        </el-main>
        <!-- <el-aside width="200px"></el-aside> -->
      </el-container>
    </el-container>
  </body>
  <!-- <body> -->
    <!-- <h1>问题求解</h1> -->

    <!-- <form id="problemSolvingForm"> -->
        <!-- <label for="problemInput">请输入问题：</label> -->
        <!-- <textarea id="problemInput" rows="5"></textarea> -->

        <!-- <input type="button" value="提交" onclick="outputSolvingProcess()"> -->
    <!-- </form> -->

    <!-- <h2>求解过程：</h2> -->
    <!-- <div id="output"> -->
        <!-- 这里将输出求解过程 -->
    <!-- </div> -->
  <!-- </body> -->
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
        MathQuestionPath: '',
        loading: false
      }
    },
    mounted() {
      const pathJson = require('../../static/path')
      const {
        basePath,
        MathQuestionPath
      } = pathJson
      this.MathQuestionPath = basePath + MathQuestionPath
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
        var result = postMapping(this.MathQuestionPath, json)
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
