<!-- <template>
  <body>
    <div class="container">
      <h1>欢迎来到个人规划服务</h1>
      <p>我们的个人规划服务旨在帮助您制定明确的学习和职业发展目标，为您的未来铺设坚实的基石。我们的专业团队将根据您的需求和期望，为您量身定制个性化的规划。</p>
      <h2>我们的服务</h2>
      <ul>
        <li>学习目标制定与规划</li>
        <li>职业发展路径分析</li>
        <li>技能提升建议与资源推荐</li>
        <li>职业生涯规划与管理</li>
        <li>个性化求职建议</li>
      </ul>
      <h2>联系我们</h2>
      <form>
        <label for="name">姓名：</label>
        <input type="text" id="name" name="name" required>
        <label for="email">邮箱：</label>
        <input type="email" id="email" name="email" required>
        <label for="message">留言：</label>
        <textarea id="message" name="message" required></textarea>
        <button type="submit">提交</button>//这里假装是人在给他规划，或者后期确实是人再给他规划，然后发送给他的邮箱
      </form>
    </div>
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

  h1,
  h2 {
    color: #333;
  }

  h1 {
    font-size: 2.5em;
    text-align: center;
    margin-bottom: 40px;
  }

  h2 {
    font-size: 1.8em;
    margin-top: 40px;
    margin-bottom: 20px;
  }

  ul {
    list-style-type: square;
    padding-left: 20px;
  }

  form {
    display: flex;
    flex-direction: column;
    gap: 10px;
  }

  label {
    font-weight: bold;
  }

  input,
  textarea {
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 1em;
    outline: none;
  }

  textarea {
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
</style>
 -->

<template>
  <body>
    <el-container class="container" v-loading="loading">
      <el-header style="color: #A5383A;font-size: 40px;font-family: 'Arial, Helvetica, sans-serif';font-weight: bolder;">
        欢迎来到个人规划服务
      </el-header>
      <p style="text-align: justify;width: 96%;margin-left: 2%;">
        我们的个人规划服务旨在帮助您制定明确的学习和职业发展目标，为您的未来铺设坚实的基石。
        我们的专业团队将根据您的需求和期望，为您量身定制个性化的规划。
      </p>
      <h2 style="color: #A5383A; text-align: left;margin-left: 2%;">我们的服务</h2>
      <ul style="text-align: left;">
        <li>学习目标制定与规划</li>
        <li>职业发展路径分析</li>
        <li>技能提升建议与资源推荐</li>
        <li>职业生涯规划与管理</li>
        <li>个性化求职建议</li>
      </ul>
      <h2 style="color: #A5383A; text-align: left;margin-left: 2%;">联系我们</h2>
      <el-container>
        <!-- <el-aside width="200px"></el-aside> -->
        <el-main>
          <el-form class="demo-ruleForm" :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
            <el-form-item label="姓名:">
              <el-input class="el-input-inner" placeholder="请输入你的姓名" v-model="Form.username"></el-input>
            </el-form-item>
            <el-form-item label="邮箱:">
              <el-input class="el-input-inner" placeholder="请输入你的邮箱" v-model="Form.mailbox"></el-input>
            </el-form-item>
            <el-form-item label="留言:">
              <el-input resize="none" :autosize="{minRows:6, maxRows:10}" class="el-input-inner" type="textarea" placeholder="请输入你的留言" v-model="Form.message"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button style="width: 30%;" type="primary" @click="fSubmitForm">提交</el-button>
            </el-form-item>
          </el-form>
        </el-main>
        <!-- <el-aside width="200px"></el-aside> -->
      </el-container>
    </el-container>
  </body>
  <!-- <body>
  	<div class="container">
  		<h1>欢迎来到考研心理咨询页面</h1>
  		<p>在这里，我们为您提供关于考研心理咨询的帮助，希望能在您追求梦想的路上给予您温暖和支持。请随时与我们联系，我们会竭诚为您提供服务。</p>
  		<h2>联系我们</h2>
  		<form>
  			<label for="name">姓名：</label>
  			<input type="text" id="name" name="name" required>
  			<label for="email">邮箱：</label>
  			<input type="email" id="email" name="email" required>
  			<label for="message">留言：</label>
  			<textarea id="message" name="message" required></textarea>
  			<button type="submit">提交</button>
  		</form>
  		<div class="quotes">
  			<h2>同学留言</h2>
  			<div class="quote">
  				<p>“第二次考上海交通大学计算机研究生，希望自己能够上岸，不负青春。”</p>
  				<p class="quote-author">—陈洪焱</p>
  			</div>
  			<div class="quote">
  				<p>“大多数人一辈子在平凡的路上人满为患，我偏要在荆棘密布的路上，奋力前行。”</p>
  				<p class="quote-author">—北京考生 张三</p>
  			</div>
  			<div class="quote">
  				<p>“希望好好读书，成为父母的骄傲。”</p>
  				<p class="quote-author">— 马克思</p>
  			</div>
  			<div class="quote">
  				<p>“我们的生活始终会充满挑战，勇敢面对挑战，坚信自己能够战胜一切，才能更好地生活。”</p>
  				<p class="quote-author">— 赫连勃拉婷</p>
  			</div>
  		</div>
  	</div>
  </body> -->
</template>

<!-- <script>
</script>

<style scoped>
  body {
    font-family: Arial, sans-serif;
    background-color: #FFEBE1;
    color: #555;
    line-height: 1.6;
  }

  .container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
  }

  h1,
  h2 {
    color: #A5383A;
  }

  h1 {
    font-size: 2.5em;
    text-align: center;
    margin-bottom: 40px;
  }

  h2 {
    font-size: 1.8em;
    margin-top: 40px;
    margin-bottom: 20px;
  }

  form {
    display: flex;
    flex-direction: column;
    gap: 10px;
  }

  label {
    font-weight: bold;
  }

  input,
  textarea {
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 1em;
    outline: none;
  }

  textarea {
    resize: vertical;
    min-height: 150px;
  }

  button {
    width: 150px;
    align-self: center;
    background-color: #A5383A;
    color: #FFF;
    font-size: 1em;
    font-weight: bold;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    padding: 10px;
  }

  button:hover {
    background-color: #85302A;
  }

  .quotes {
    margin-top: 40px;
    font-size: 1.2em;
    font-style: italic;
  }

  .quote {
    margin-bottom: 20px;
  }

  .quote-author {
    font-weight: bold;
  }
</style> -->

<script>
  import {
    postMapping
  } from '../utils/requestUtils'
  export default {
    data() {
      return {
        Form: {
          username: '',
          mailbox: '',
          message: ''
        },
        answer: '',
        GuiderPath: '',
        loading: false
      }
    },
    mounted() {
      const pathJson = require('../../static/path')
      const {
        basePath,
        GuiderPath
      } = pathJson
      this.GuiderPath = basePath + GuiderPath
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
          username: this.Form.username,
          mailbox: this.Form.mailbox,
          message: this.Form.message
        }
        // formMap.set('username', this.ValidateForm.name)
        // formMap.set('password', this.ValidateForm.password)
        var logstate
        console.log('result123')
        var result = postMapping(this.GuiderPath, json)
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
    background-color: #FFEBE1;
    max-width: 1000px;
    height: 90%;
    width: 800px;
    padding: 40px;
    // background-color: #fff;
    border-radius: 10px;
    box-shadow: 10px 10px 15px 2px gray;
    font-family: Arial, Helvetica, sans-serif;
    font-size: 18px;
  }

  .demo-ruleForm /deep/ .el-input-inner {
    font-family: Arial, Helvetica, sans-serif;
    font-size: 18px;
    border: 2px lightblue solid;
    box-shadow: 10px 10px 15px 2px gray;
    border-radius: 10px;
    // margin-top: 10px;
    resize: none;
  }

  >>> label{
    font-size: 18px;
  }
</style>
