<template>
    <v-container>
      <v-card class="pa-3 ">
        <v-btn  router :to="{name:'AddSurvey'}">설문지</v-btn>
          <v-list three-line>
            <template>
              <v-list-item
              v-for="(item) in surveyList"
              :key="item.sId"
              >
                <v-list-item-content>
                  <v-list-item-subtitle v-html="item.sTitle"></v-list-item-subtitle>
                  <v-list-item-subtitle v-html="item.sSubTitle"></v-list-item-subtitle>
                  <v-list-item-subtitle v-html="item.sWriter"></v-list-item-subtitle>
                  <v-list-item-subtitle v-html="item.sDateTime"></v-list-item-subtitle>
                </v-list-item-content> 
                  <v-btn 
                  v-show="Userinfo.User_Id !== item.uId"
                  @click="SurveyDetail(item.sId)">설문</v-btn>
                  <v-btn 
                  v-show="Userinfo.User_auth.length === 2 || Userinfo.User_Id === item.uId"
                  @click="SurveyDelete({sId: item.sId, pageNum: page})">삭제</v-btn>
                  <v-btn 
                  @click="Analysis(item.sId)"
                  v-show="Userinfo.User_auth.length === 2"
                  >통계</v-btn>
              </v-list-item>
            </template>
          </v-list>
          <v-pagination 
            v-model="page"
            :length="pageLength"
            @input="updatePage"
            prev-icon="mdi-menu-left"
            next-icon="mdi-menu-right"
          ></v-pagination>
      </v-card>
    </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex"
export default {
  data () {
    return {
      page: 1,
      bKeyword: null,
      bSearch: null,
      items: [
        {name: '제목', value:'제목'},
        {name: '제목+내용', value:'제목+내용'},
        {name: '작성자', value:'작성자'}
      ]
    }
  },
  created() {
    this.$store.dispatch('SurveyList', this.page)
  },
  computed: {
    ...mapState(["surveyList", 'pagination', 'Userinfo']),
    pageLength() {
      return Math.ceil(this.pagination.count/this.pagination.perPage)
    }
  },
  methods: {
    ...mapActions(['SurveyDetail', 'AddSurvey', 'SurveyDelete', 'Analysis']),
    updatePage() {
      this.$store.dispatch('SurveyList', this.page)
    },
  }
}
</script>