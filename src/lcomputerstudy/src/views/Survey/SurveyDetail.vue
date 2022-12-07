<template>
    <v-container>
        <v-card outlined>
          <v-card-text>
            <p
            class="text-h4 text--primary"
            >{{surveyDetail.sTitle}}</p>
            <div>{{surveyDetail.sSubTitle}}</div>
          </v-card-text>
        </v-card>
        <v-card outlined>
            <component
            v-for="(item, i) in components"
            :key="i"
            :is="item.value"
            :qTitle="item.qTitle"
            :itemList="item.itemList"
            :qList="qList"
            :keyNum="i"
            ></component>
        </v-card>
        <br/>
        <v-btn @click="SurveyAnswer({
          sId:surveyDetail.sId,
          questionList:qList,
          aWriter:Userinfo.User_Name,
          uId:Userinfo.User_Id
        })">등록</v-btn>
    </v-container>
</template>
  
  <script>
  import { mapActions, mapState } from "vuex"
  
  import SelectAnswer from '@/components/Answer/SelectAnswer.vue'
  import EssayAnswer from '@/components/Answer/EssayAnswer.vue'
  import CheckBoxAnswer from '@/components/Answer/CheckBoxAnswer.vue'
  import DropBoxAnswer from '@/components/Answer/DropBoxAnswer.vue'

  
  export default {
      data() {
        return {
          componentList: [],
          answerList:[],
          aList:[],
          qList:[]
        }
      },
      computed: {
         ...mapState(["surveyDetail", 'Userinfo']),
         components() {
          for (let index = 0; index < this.surveyDetail.questionList.length; index++) {
            if(this.surveyDetail.questionList[index].qType === 1) {
              let component={value:'SelectAnswer', qTitle:this.surveyDetail.questionList[index].qTitle, itemList:this.surveyDetail.questionList[index].itemList}
              this.componentList.push(component)
              let selectAnswer = {qId:this.surveyDetail.questionList[index].qId, answerList:[]}
              this.qList.push(selectAnswer)
            }
            if(this.surveyDetail.questionList[index].qType === 2) {
              let component={value:'EssayAnswer', qTitle:this.surveyDetail.questionList[index].qTitle}
              this.componentList.push(component) 
              let essayAnswer = {qId:this.surveyDetail.questionList[index].qId, answerList:[]}
              this.qList.push(essayAnswer)
            }
            if(this.surveyDetail.questionList[index].qType === 3) {
              let component={value:'CheckBoxAnswer', qTitle:this.surveyDetail.questionList[index].qTitle, itemList:this.surveyDetail.questionList[index].itemList}
              this.componentList.push(component)
              let checkAnswer = {qId:this.surveyDetail.questionList[index].qId, answerList:[]}
              this.qList.push(checkAnswer)
            }
            if(this.surveyDetail.questionList[index].qType === 4) {
              let component={value:'DropBoxAnswer', qTitle:this.surveyDetail.questionList[index].qTitle, itemList:this.surveyDetail.questionList[index].itemList}
              this.componentList.push(component)
              let dropAnswer = {qId:this.surveyDetail.questionList[index].qId, answerList:[]}
              this.qList.push(dropAnswer)
            }
          }
          return this.componentList
         }
         
      },
      methods: {
        ...mapActions(['SurveyAnswer']),
      },
      components: {
        SelectAnswer,
        EssayAnswer,
        CheckBoxAnswer,
        DropBoxAnswer
      }
  }
  </script>
  