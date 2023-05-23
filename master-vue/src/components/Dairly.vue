<!-- <template>
  <body>
    <div class="container">
      <h1>考研日记</h1>
      <form id="diaryForm">
        <label for="diaryEntry">今天的日记：</label>
        <textarea id="diaryEntry" name="diaryEntry"></textarea>
        <button type="submit">保存</button>
      </form>
      <div class="entries" id="entries">
      </div>
    </div>
    <script>
      const diaryForm = document.getElementById("diaryForm");
      const entriesElement = document.getElementById("entries");

      function formatDate(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');

        return `${year}-${month}-${day}`;
      }

      function loadEntries() {
        const entries = JSON.parse(localStorage.getItem("diaryEntries")) || [];

        entriesElement.innerHTML = "";
        entries.forEach((entry) => {
          const entryElement = document.createElement("div");
          entryElement.classList.add("entry");

          const entryDateElement = document.createElement("div");
          entryDateElement.classList.add("entry-date");
          entryDateElement.textContent = formatDate(new Date(entry.date));
          entryElement.appendChild(entryDateElement);

          const entryContentElement = document.createElement("div");
          entryContentElement.textContent = entry.content;
          entryElement.appendChild(entryContentElement);

          entriesElement.appendChild(entryElement);
        });
      }

      diaryForm.addEventListener("submit", (event) => {
        event.preventDefault();

        const diaryEntry = document.getElementById("diaryEntry").value;
        if (!diaryEntry.trim()) return;

        const entries = JSON.parse(localStorage.getItem("diaryEntries")) || [];
        entries.push({
          date: new Date().toISOString(),
          content: diaryEntry
        });
        localStorage.setItem("diaryEntries", JSON.stringify(entries));

        document.getElementById("diaryEntry").value = "";
        loadEntries();
      });

      loadEntries();
    </script>
  </body>
</template>

<script>
</script>

<style scoped>
  body {
    font-family: Arial, sans-serif;
    background-color: #F0F0F0;
    color: #333;
    line-height: 1.6;
  }

  .container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
  }

  h1 {
    font-size: 2.5em;
    text-align: center;
    margin-bottom: 40px;
  }

  form {
    display: flex;
    flex-direction: column;
    gap: 10px;
  }

  label {
    font-weight: bold;
  }

  textarea {
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 1em;
    outline: none;
    resize: vertical;
    min-height: 150px;
  }

  button {
    width: 150px;
    align-self: center;
    background-color: #333;
    color: #FFF;
    font-size: 1em;
    font-weight: bold;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    padding: 10px;
  }

  button:hover {
    background-color: #444;
  }

  .entries {
    margin-top: 40px;
  }

  .entry {
    background-color: #FFF;
    border-radius: 5px;
    padding: 20px;
    margin-bottom: 20px;
  }

  .entry-date {
    font-size: 0.8em;
    font-weight: bold;
    color: #666;
    margin-bottom: 10px;
  }
</style>
 -->
<template>
  <body>
    <el-container class="container" v-loading="loading">
      <el-header style="font-size: 40px;font-family: 'Arial, Helvetica, sans-serif';font-weight: bolder;">考研日记
      </el-header>
      <h2 style="text-align: left;margin-left: 2%;">今天的日记</h2>
      <el-container style="margin-top: -30px;">
        <!-- <el-aside width="200px"></el-aside> -->
        <el-main>
          <el-form label-position="top" :model="Form" :rules="rules" ref="Form" label-width="120px"
            class="demo-ruleForm">
            <!-- <div style="font-size: 20px; font-family: 'Arial, Helvetica, sans-serif';">请在此处输入您问题</div> -->
            <el-form-item prop="question">
              <el-input v-focus
                class="el-input-inner" type="textarea" v-focus placeholder="请在此处输入您的日记" resize="none"
                :autosize="{minRows:10, maxRows:14}" v-model="Form.question"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button style="width: 30%;" type="primary" @click="fSubmitForm">提交</el-button>
              <!-- <el-button @click="resetForm('ruleForm')">重置</el-button> -->
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
        DairyPath: '',
        loading: false
      }
    },
    mounted() {
      const pathJson = require('../../static/path')
      const {
        basePath,
        DairyPath
      } = pathJson
      this.DairyPath = basePath + DairyPath
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
        var result = postMapping(this.DairyPath, json)
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
