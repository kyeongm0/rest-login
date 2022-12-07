<template>
    <v-container>
        <v-card outlined>
          <v-card-text>
            <h3>Survey UserList</h3>
          </v-card-text>
          <v-list>
            <v-list-item
            v-for="(item, i) in surveyUserList"
            :key="i"
            >
              <v-list-item-content>
                <v-list-item-title v-text="item.id"></v-list-item-title>
                <v-list-item-title v-text="item.name"></v-list-item-title>
                <v-list-item-title v-text="item.dateTime"></v-list-item-title>
              </v-list-item-content>
              <v-btn @click="AnswerDetail({
                username:item.id,
                sId:item.sId
                })">조회</v-btn>
            </v-list-item>
          </v-list>
        </v-card>
        <v-card outlined>
          <v-card-text>
            <h1>{{this.surveyDetail.sTitle}}</h1><br/>
            <h3>{{this.surveyDetail.sSubTitle}}</h3>
          </v-card-text>
          <v-list>
            <v-list-item             
              v-for="(item, i) in surveyTitle"
              :key="i"
              >
                <v-list-item-content>
                  <v-list-item-title v-text="item.qTitle"></v-list-item-title>
                </v-list-item-content>
                <v-btn 
                v-show="item.qType !==2"
                @click="chart(item)">통계</v-btn>
             </v-list-item>
             <Chart
              v-show="showChart"
              :answerCount="answerCount"              
              ></Chart>
              <v-btn 
              v-show="showButton"
              @click="diplayNone">닫기</v-btn>
          </v-list>
        </v-card>
    </v-container>
</template>


<script>
import { mapActions, mapState } from "vuex"
import Chart from '@/components/Chart.vue'

export default {
    data() {
        return {
            surveyUserList: [],
            surveyTitle: [],
            showChart: 0,
            showButton: 0,
            sId: this.$route.params.sId
        }
    },
    computed: {
        ...mapState(['surveyDetail', 'answerCount'])
    },
    methods: {
        ...mapActions(['AnswerDetail', 'QuestionChart']),
        chart(item) {
          this.showChart = 1
          this.showButton = 1
          this.$store.dispatch('QuestionChart',{qId:item.qId, sId:item.sId})
        },
        diplayNone() {
          this.showChart = 0
          this.showButton = 0
        }
    },
    created() {
      for(let index=0; index<this.surveyDetail.questionList[0].answerList.length; index++) {
        let surveyUserValue = {id: this.surveyDetail.questionList[0].answerList[index].uId, name: this.surveyDetail.questionList[0].answerList[index].aWriter, sId:this.surveyDetail.sId, dateTime: this.surveyDetail.questionList[0].answerList[index].aDateTime}
        this.surveyUserList.push(surveyUserValue)
      }
      for(let index=0; index<this.surveyDetail.questionList.length; index++) {
        let value = {qTitle: this.surveyDetail.questionList[index].qTitle, qId: this.surveyDetail.questionList[index].qId, sId: this.surveyDetail.sId, answerList: this.surveyDetail.questionList[index].answerList, qType: this.surveyDetail.questionList[index].qType}
        this.surveyTitle.push(value)
      }
    },
    components: {
      Chart
    }
}
</script>
