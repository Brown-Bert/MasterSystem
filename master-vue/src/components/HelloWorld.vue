<template>
  <div class="hello">
    <h1>学习辅助页面</h1>
    <div class="options">
      <el-row :gutter="24" type="flex">
        <el-col :span="8">
          <div style="height: 80%;">
            <div style="height: 100%;" class="option" @click="$router.push('/politic')">
              <h2>政治选择题</h2>
              <p>提供政治选择题刷题工具</p>

            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div style="height: 80%;">
            <div style="height: 100%;" class="option" @click="$router.push('/english')">
              <h2>英语作文修改</h2>
              <p>提供英语作文修改服务，帮助你改善语言表达能力。</p>

            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div style="height: 80%;">
            <div style="height: 100%;" class="option" @click="$router.push('/comprehend')">
              <h2>阅读理解</h2>
              <p>提供阅读理解训练，帮助你提升阅读理解能力。</p>
            </div>
          </div>
        </el-col>
        <div style="clear: both;"></div>
      </el-row>
      <el-row :gutter="24" type="flex">
        <el-col :span="8">
          <div style="height: 80%;">
            <div style="height: 100%;" class="option" @click="$router.push('/math')">
              <h2>数学题解答</h2>
              <p>提供数学题解答疑服务，帮助你解决数学题目难点。</p>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div style="height: 80%;">
            <div style="height: 100%;" class="option" @click="$router.push('/professional')">
              <h2>专业课答疑</h2>
              <p>提供各类专业课答疑服务，帮助你更好地掌握专业知识。</p>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div style="height: 80%;">
            <div style="height: 100%;" class="option" @click="$router.push('/counselor')">
              <h2>心理咨询</h2>
              <p>提供心理咨询服务，帮助你解决学习和生活中的问题。</p>
            </div>
          </div>
        </el-col>
        <div style="clear: both;"></div>
      </el-row>
      <el-row :gutter="24" type="flex">
        <el-col :span="8">
          <div style="height: 80%;">
            <div style="height: 100%;" class="option" @click="$router.push('/guider');">
              <h2>个人规划</h2>
              <p>提供个人规划服务，帮助你制定明确的学习和职业发展目标。</p>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div style="height: 80%;">
            <div style="height: 100%;" class="option" @click="$router.push('/dairly');">
              <h2>考研日记</h2>
              <p>记录下你的考研心路历程</p>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div style="height: 80%;">
            <div style="height: 100%;" class="option" @click="$router.push('/mandom');">
              <h2>随机题目</h2>
              <p>提供随机题目服务，帮助你随时测试自己的知识水平。</p>
            </div>
          </div>
        </el-col>
        <div style="clear: both;"></div>
      </el-row>

    </div>
    <!-- <div id="result" style="margin: 20px; text-align: center;"></div>
        <button onclick="submitRequest()" style="display: block; margin: 0 auto;">提交</button> -->
  </div>
</template>

<script>
  import router from '../router'
  export default {
    name: 'HelloWorld',
    data() {
      return {
        msg: 'Welcome to Your Vue.js App'
      }
    },
    methods: {

    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="css" scoped>
  body {
    margin: 0;
  }

  h1 {
    text-align: center;
    font-size: 2.5em;
    color: #444;
    margin-bottom: 20px;
    margin-top: 20px;
  }

  .options {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
    padding: 0 20px;
  }

  .option {
    margin: 10px;
    padding: 20px;
    background-color: #fff;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    cursor: pointer;
    transition: all 0.3s;
    width: calc(50% - 40px);
    transform: translateY(0);
  }

  .option:hover {
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
    transform: translateY(-5px);
  }

  .option h2 {
    margin-top: 0;
    margin-bottom: 10px;
    font-size: 1.5em;
    color: #2E86C1;
  }

  .option p {
    margin-bottom: 0;
    color: #666;
    font-size: 1.1em;
    line-height: 1.6;
  }

  @media (min-width: 768px) {
    .option {
      width: calc(33.333% - 40px);
    }
  }

  @media (min-width: 1024px) {
    .option {
      width: 300px;
    }
  }
</style>
