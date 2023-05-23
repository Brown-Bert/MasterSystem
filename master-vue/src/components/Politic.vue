<template>
  <div class="politic">
    <h3 id="question-number">第1题</h3>
    <p id="question">这里是题目内容</p>
    <div>
        <button id="prev" onclick="previousQuestion()" disabled>前一题</button>
        <button id="next" onclick="nextQuestion()">后一题</button>
    </div>
    <button id="submit" onclick="submitAnswers()" style="display: none;">交卷</button>
  </div>
</template>

<script>
  // let currentQuestion = 0;
  // const totalQuestions = 30;

  // function updateQuestionNumber() {
  //     document.getElementById('question-number').innerText = `第${currentQuestion + 1}题`;
  //     if (currentQuestion === 0) {
  //         document.getElementById('prev').disabled = true;
  //     } else {
  //         document.getElementById('prev').disabled = false;
  //     }
  //     if (currentQuestion === totalQuestions - 1) {
  //         document.getElementById('next').style.display = 'none';
  //         document.getElementById('submit').style.display = 'block';
  //     } else {
  //         document.getElementById('next').style.display = 'inline-block';
  //         document.getElementById('submit').style.display = 'none';
  //     }
  // }

  // function previousQuestion() {
  //     if (currentQuestion > 0) {
  //         currentQuestion--;
  //         updateQuestionNumber();
  //     }
  // }

  // function nextQuestion() {
  //     if (currentQuestion < totalQuestions - 1) {
  //         currentQuestion++;
  //         updateQuestionNumber();
  //     }
  // }

  // function submitAnswers() {
  //     // 在此处处理答案提交和评分逻辑
  // }
</script>

<style lang="css" scoped>
  .politic {
      font-family: Arial, sans-serif;
      display: flex;
      flex-direction: column;
      align-items: center;
  }

  #submit {
      margin: 10px;
      display: inline-block;
  }
</style>
