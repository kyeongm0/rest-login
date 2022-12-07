<template>
    <v-flex xs12>
        <v-form style="width:800px;margin:0 auto;">
            <v-text-field
                name="user_name"
                label="제목"
                v-model="user_detail.name"
            ></v-text-field>
          <v-textarea
            name="user_phone"
            label="전화번호"
            v-model="user_detail.phone"
            ></v-textarea>
        </v-form>
        <v-btn @click="UserEditProcess({
            username:username,
            name:user_detail.name,
            phone:user_detail.phone
            })">등록</v-btn>
        <v-btn @click="back">취소</v-btn>
    </v-flex>
  </template>
  
  <script>
  import { mapActions, mapState } from "vuex"
  
  export default {
      data() {
        return {
          username : this.$route.params.username
        }
      },
      computed: {
         ...mapState(["user_detail"])
      },
      methods: {
        ...mapActions(["UserDelete", "UserEdit", 'UserList', 'UserEditProcess']),
        back() {
          this.$store.dispatch('UserDetail', this.username)
        }
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