<template>
  <body>
    <div class="container">
      <h1>随机题目服务</h1>
      <div class="buttons">
        <button id="politicsBtn">政治</button>
        <button id="englishBtn">英语</button>
        <button id="mathBtn">数学</button>
      </div>
      <div class="question" id="question">
        <!-- 随机题目将显示在这里 -->
      </div>
    </div>
    <!-- <script>
      const politicsQuestions = [
        "政治问题1",
        "政治问题2",
        "政治问题3",
      ];

      const englishQuestions = [
        "英语问题1",
        "英语问题2",
        "英语问题3",
      ];

      const mathQuestions = [
        "数学问题1",
        "数学问题2",
        "数学问题3",
      ];

      function getRandomQuestion(category) {
        const questions = category === "politics" ? politicsQuestions :
          category === "english" ? englishQuestions :
          mathQuestions;

        return questions[Math.floor(Math.random() * questions.length)];
      }

      function showRandomQuestion(category) {
        const questionElement = document.getElementById("question");
        questionElement.textContent = getRandomQuestion(category);
        questionElement.style.display = "block";
      }

      document.getElementById("politicsBtn").addEventListener("click", () => {
        showRandomQuestion("politics");
      });

      document.getElementById("englishBtn").addEventListener("click", () => {
        showRandomQuestion("english");
      });

      document.getElementById("mathBtn").addEventListener("click", () => {
        showRandomQuestion("math");
      });
    </script> -->
  </body>
</template>

<script>
</script>

<style>
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

  .buttons {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-bottom: 40px;
  }

  button {
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

  .question {
    background-color: #FFF;
    border-radius: 5px;
    padding: 20px;
    font-size: 1.2em;
    display: none;
  }
</style>
