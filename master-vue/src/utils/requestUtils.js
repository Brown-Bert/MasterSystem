// this.$http.defaults.timeout = 3000
// this.$http.defaults.baseURL = process.env.API_HOST
import Axios from '@/axios/axios'
export async function getMapping (url, query) {
  var result
  // console.log("url", url)
  await Axios({
    method: 'get',
    url: url + '?' + query
  }).then(res => {
    result = res
  }).catch(err => {
    console.log('err', err)
  })
  return result
}

// 传入两个参数url即地址，mapData是一个map类型的数据
export async function postMapping (url, json) {
  var result
  await Axios({
    method: 'post',
    // baseURL: 'http://127.0.0.1:8888',
    // var ca = 'http://127.0.0.1:8888' + url,
    url,
    // data: {
    //   'data': json
    //   // 'timeStamp': formatDate(new Date().getTime())
    // }
    data: json
  }).then(res => {
    result = res
  }).catch(err => {
    console.log(err)
  })
  return result
}

// 时间戳函数
export function formatDate (data) {
  var now = new Date(data)
  var year = now.getFullYear()
  var month = now.getMonth()
  var date = now.getDate()
  var hour = now.getHours()
  var minute = now.getMinutes()
  var second = now.getSeconds()

  return (year + '-' + month + '-' + date + ' ' + hour + ':' + minute + ':' + second)
}
