<template>
    <v-flex xs12>        
        <table>
          <tr>
            <td colspan="2" style="font-weight:700;"> ID : {{user_detail.username}}</td>
          </tr>
          <tr>
            <td colspan="2" style="border:none;">이름 : {{user_detail.name}}</td>
          </tr>
          <tr>
            <td colspan="2" style="border:none;">전화번호 : {{user_detail.phone}}</td>
          </tr>
          <tr>
            <td colspan="2" style="border:none;">가입일자 : {{user_detail.uDateTime}}</td>
          </tr>
          <tr>
            <td colspan="2" style="border:none;">등급 : {{user_detail.uGrade}}</td>
          </tr>
          <tr>
            <td colspan="2" style="border:none;text-align:right;border-top:3px double #ededed">
              <v-btn router :to="{name:'UserList'}">목록</v-btn>
              <v-btn 
              v-show='user_detail.username===Userinfo.User_Id'
              @click="UserEdit({username:username})">수정</v-btn>
              <v-btn 
              v-show='Userinfo.User_auth.length === 2'
              @click="UserLevelChange({
                username:username,
                userauth:userauthList})">등급</v-btn>
              <v-btn 
              v-show=" user_detail.username===Userinfo.User_Id || Userinfo.User_auth.length === 2 "
              @click="UserDelete({username:username})">삭제</v-btn>
            </td>
          </tr>
        </table>
    </v-flex>
  </template>
  
  <script>
import { mapActions, mapState } from "vuex"
  
  export default {
      data() {
        return {
          username : this.$route.params.username,
          userauth : null,
        }
      },
      computed: {
         ...mapState(["user_detail", 'Userinfo']),
         userauthList() {
          this.userauth = this.user_detail.authorities
          return this.userauth
         }
      },
      methods: {
        ...mapActions(["UserDelete", "UserEdit", 'UserList', 'UserLevelChange']),
      },
      created() {
        this.username = this.$route.params.username
        this.$store.dispatch('UserDetail', this.username)
      }
  }
  </script>
  <style scoped>
  table {
    width:600px;
    margin:0 auto;
    border-collapse:collapse;
    text-align: center;
  }
  table tr td {
    border-top:1px solid #ededed;
    border-bottom:1px solid #ededed;
    padding:10px;
  }
  </style>