import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import SignUp from '../views/SignUp.vue'
import BoardList from '../views/Board/BoardList.vue'
import User from '../views/User/User.vue'
import UserList from '../views/User/UserList.vue'
import BoardDetail from '../views/Board/BoardDetail.vue'
import BoardEdit from '../views/Board/BoardEdit.vue'
import BoardReply from '../views/Board/BoardReply.vue'
import UserDetail from '../views/User/UserDetail.vue'
import UserEdit from '../views/User/UserEdit.vue'
import BoardWrite from '../views/Board/BoardWrite.vue'
import admin from '../views/admin.vue'
import BoardSearchList from '../views/Board/BoardSearchList.vue'
import Survey from '../views/Survey/Survey.vue'
import AddSurvey from '../views/Survey/AddSurvey.vue'
import SurveyDetail from '../views/Survey/SurveyDetail.vue'
import SurveyReport from '../views/Survey/SurveyReport.vue'
import SurveyAnswer from '../views/Survey/SurveyAnswer.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/boardlist',
    name: 'BoardList',
    component: BoardList
  },
  {
    path: '/user',
    name: 'User',
    component: User
  },
  {
    path: '/boardDetail/:bId',
    name: 'BoardDetail',
    component: BoardDetail
  },
  {
    path: '/boardWrite',
    name: 'BoardWrite',
    component: BoardWrite
  },
  {
    path: '/boardEdit/:bid',
    name: 'BoardEdit',
    component: BoardEdit
  },
  {
    path: '/admin',
    name: 'admin',
    component:admin
  },
  {
    path: '/userList',
    name: 'UserList',
    component: UserList
  },
  {
    path: '/userDetail/:username',
    name: 'UserDetail',
    component: UserDetail
  },
  {
    path: '/userEdit/:username',
    name: 'UserEdit',
    component: UserEdit
  },
  {
    path: '/boardReply',
    name: 'BoardReply',
    component: BoardReply
  },
  {
    path: '/boardSearchList',
    name: 'BoardSearchList',
    component: BoardSearchList
  },
  {
    path: '/survey',
    name: 'Survey',
    component: Survey
  },
  {
    path: '/addSurvey',
    name: 'AddSurvey',
    component: AddSurvey
  },
  {
    path: '/surveyDetail/:sId',
    name: 'SurveyDetail',
    component: SurveyDetail
  },
  {
    path: '/surveyReport/:sId',
    name: 'SurveyReport',
    component: SurveyReport
  },
  {
    path: '/surveyAnswer/:uId',
    name: 'SurveyAnswer',
    component: SurveyAnswer
  },

  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
