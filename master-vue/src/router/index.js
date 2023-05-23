import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'

import Login from '@/components/MyLogin'
import Wellcome from '@/components/Wellcome'
import HelloWorld from '@/components/HelloWorld'
import Politic from '@/components/Politic'
import English from '@/components/English'
import Comprehend from '@/components/Comprehend'
import Math from '@/components/Math'
import Professional from '@/components/Professional'
import Counselor from '@/components/Counselor'
import Guider from '@/components/Guider'
import Dairly from '@/components/Dairly'
import Mandom from '@/components/Mandom'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/helloworld',
      name: 'HelloWorld',
      component: HelloWorld,
      // children:[
      //   {
      //     path: '/politic',
      //     name: 'politic',
      //     component: Politic
      //   },
      // ]
    },
    // {
    //   path: '/myLogin',
    //   name: 'login',
    //   component: Login
    // },
    {
      path: '/wellcome',
      name: 'wellcome',
      component: Wellcome
    },
    {
      path: '/politic',
      name: 'politic',
      component: Politic
    },
    {
      path: '/english',
      name: 'English',
      component: English
    },
    {
      path: '/comprehend',
      name: 'Comprehend',
      component: Comprehend
    },
    {
      path: '/math',
      name: 'Math',
      component: Math
    },
    {
      path: '/professional',
      name: 'Professional',
      component: Professional
    },
    {
      path: '/counselor',
      name: 'Counselor',
      component: Counselor
    },
    {
      path: '/guider',
      name: 'Guider',
      component: Guider
    },
    {
      path: '/dairly',
      name: 'Dairly',
      component: Dairly
    },
    {
      path: '/mandom',
      name: 'Mandom',
      component: Mandom
    },
    {
      path: '/',
      redirect: '/helloworld'
    }
  ]
})
