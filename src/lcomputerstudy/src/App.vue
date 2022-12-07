<template>
  <div id="app">
    <v-app id="inspire">
      <v-navigation-drawer
        v-model="drawer"
        app
      >
        <v-list dense>
          <v-list-item-group
            v-model="group">
              <v-list-item router :to="{name:'Login'}">
                <v-list-item-action>
                  <v-icon>mdi-home</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>Login</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              <v-list-item router :to="{name:'UserList'}">
                <v-list-item-action>
                  <v-icon>mdi-account</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>회원</v-list-item-title>
                </v-list-item-content>
              </v-list-item> 
              <v-list-item router :to="{name:'BoardList'}">
                <v-list-item-action>
                  <v-icon>mdi-card-text-outline</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>게시판</v-list-item-title>
                </v-list-item-content>
              </v-list-item> 
              <v-list-item router :to="{name:'admin'}">
                <v-list-item-action>
                  <v-icon>mdi-pencil</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>관리자</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              <v-list-item router :to="{name:'Survey'}">
                <v-list-item-action>
                  <v-icon>mdi-message-text</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title>설문조사</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-navigation-drawer>

      <v-app-bar
        app
        color="primary"
        dark
      >
        <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
        <v-toolbar-title>LcomputerStudy</v-toolbar-title>
        <v-spacer/>
        <v-spacer/><v-spacer/><v-spacer/><v-spacer/><v-spacer/><v-spacer/><v-spacer/>
        <v-toolbar-title>{{Userinfo.User_Id}}</v-toolbar-title>
        <v-spacer/>
        <v-btn
          color="cyan"
          router :to="{name:'User'}">홈</v-btn>
        <v-btn
          router :to="{name:'Login'}">로그인</v-btn>
        <v-btn
          color="purple"
          @click="logout()">로그아웃</v-btn>
      </v-app-bar>

      <v-main>
        <v-container
          class="fill-height"
          fluid
        >
          <router-view/>
        </v-container>
      </v-main>
      <v-footer
        color="primary"
        app
      >
        <span class="white--text">&copy; {{ new Date().getFullYear() }}</span>
      </v-footer>
    </v-app>
  </div>
</template>

<script>
import {mapMutations, mapState} from "vuex"

  export default {
    name: 'App',
    data: () => ({
      drawer: false,
      group: null
    }),
    computed: {
      ...mapState(["Userinfo"])
    },
    methods: {
      ...mapMutations(["logout"])
    },
    created() {
      if(this.Userinfo.User_token === null && localStorage.getItem("token") !== null) {
        this.$store.commit("INSERT_TOKEN"),
        this.$store.dispatch('UnpackToken')
        console.log("tttttt"+localStorage.getItem("token"))
      } else if (this.Userinfo.User_token === null && localStorage.getItem("token") === null) {
        console.log("dasjfkldasjklsda")
      }
      // if(this.Userinfo.User_token === null && localStorage.getItem("token") !== null) {
      //   console.log("토큰 이 notnull이면" + localStorage.getItem("token"))
      //   console.log("토큰 이 notnull이면22" + this.Userinfo.User_token)
      // }else if(this.Userinfo.User_token !== null && localStorage.getItem("token") === null) {
      //   console.log("토큰 이 null이면" + localStorage.getItem("token"))
      // }
    },
    watch: {
      group() {
        this.drawer= false
      }
    }
  }
</script>