<template>
  <div>
    <el-dialog :visible.sync="outerVisible" width= 30% :before-close="fOnClose">
      <slide-verify
        ref="slideblock"
        @again="fOnAgain"
        @fulfilled="fOnFulfilled"
        @success="fOnSuccess"
        @fail="fOnFail"
        @refresh="FOnRefresh"
        :slider-text="text"
        :accuracy="accuracy"
        style="width: 74%; margin: 0 auto;"
      ></slide-verify>
<!--      <div>{{ msg }}</div> -->
    </el-dialog>
  </div>
</template>

<script>
export default {
  // name: 'App',
  data () {
    return {
      // msg: '',
      text: '向右滑动->',
      outerVisible: false,
      // 精确度小，可允许的误差范围小；为1时，则表示滑块要与凹槽完全重叠，才能验证成功。默认值为5
      accuracy: 5,
      puzzleImgList: [
        require('../../static/imgs/IMG_0329.png')
      ]
    }
  },
  methods: {
    fOnSuccess () {
      console.log('验证通过')
      this.msg = 'login success'
      // 滑动验证成功之后关闭弹出的窗口
      this.outerVisible = false
      this.$refs.slideblock.reset()
      this.$emit('materials', 'pass')
    },
    fOnFail () {
      console.log('验证不通过')
      this.msg = ''
      this.$emit('materials', 'unpass')
    },
    FOnRefresh () {
      console.log('点击了刷新小图标')
      this.msg = ''
    },
    fOnFulfilled () {
      console.log('刷新成功啦！')
    },
    fOnClose () {
      console.log('关闭弹窗')
      this.outerVisible = false
      this.$refs.slideblock.reset()
    },
    fOnAgain () {
      console.log('检测到非人为操作的哦！')
      this.msg = 'try again'
      // 刷新
      this.$refs.slideblock.reset()
    }
  }
}
</script>

<style lang="css" scoped>
  el-dialog {
    display: flex;
    justify-content: center;
    align-items: center
  }
  /* canvas {
    width: 200px;
    height: 100px;
  } */
</style>
