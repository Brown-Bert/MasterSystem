import axios from 'axios'
import { Notification, MessageBox, Message } from 'element-ui'
// import store from '../vuex/index'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  // baseURL: process.env.VUE_APP_BASE_API,\
  // baseURL: process.env.API,
  baseURL: 'http://127.0.0.1:9999/order',
  // 超时
  timeout: 100000
})
// request拦截器
service.interceptors.request.use(
  config => {
    config.headers['Authorization'] = 'Bearer ' + '' // 让每个请求携带自定义token 请根据实际情况自行修改
    return config
  },
  error => {
    console.log(error)
    Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(res => {
  const code = res.status
  if (code === 401) {
    MessageBox.confirm(
      '登录状态已过期，您可以继续留在该页面，或者重新登录',
      '系统提示',
      {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
      }
    ).then(() => {
      this.$store.dispatch('LogOut').then(() => {
        location.reload() // 为了重新实例化vue-router对象 避免bug
      })
    })
  } else if (code !== 200) {
    Notification.error({
      title: res.data.msg
    })
    return Promise.reject(new Error('error'))
  } else {
    return res.data
  }
},
error => {
  console.log('err' + error)
  Message({
    message: error.message,
    type: 'error',
    duration: 5 * 1000
  })
  return Promise.reject(error)
})

export default service
