<template>
    <v-container>
        <v-card outlined>
          <v-card-text>
            <p
            class="text-h4 text--primary"
            >{{answerDetail.sTitle}}</p>
            <div>{{answerDetail.sSubTitle}}</div>
          </v-card-text>
        </v-card>
        <v-card outlined>
            <component
            v-for="(item, i) in components"
            :key="i"
            :is="item.value"
            :qTitle="item.qTitle"
            :answerList="item.answerList"
            :itemList="item.itemList"
            :num="i"
            ></component>
        </v-card>
        <br/>
        <v-btn>뒤로</v-btn>
    </v-container>
</template>
  
  <script>
  import { mapActions, mapState } from "vuex"
  
  import SelectDetail from '@/components/Detail/SelectDetail.vue'
  import EssayDetail from '@/components/Detail/EssayDetail.vue'
  import CheckBoxDetail from '@/components/Detail/CheckBoxDetail.vue'
  import DropBoxDetail from '@/components/Detail/DropBoxDetail.vue'

  
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
         ...mapState(["answerDetail", 'Userinfo']),
         components() {
          for (let index = 0; index < this.answerDetail.questionList.length; index++) {
            if(this.answerDetail.questionList[index].qType === 1) {
              let component={value:'SelectDetail', qTitle:this.answerDetail.questionList[index].qTitle, answerList:this.answerDetail.questionList[index].answerList, itemList:this.answerDetail.questionList[index].itemList}
              this.componentList.push(component)
            }
            if(this.answerDetail.questionList[index].qType === 2) {
              let component={value:'EssayDetail', qTitle:this.answerDetail.questionList[index].qTitle, answerList:this.answerDetail.questionList[index].answerList, itemList:this.answerDetail.questionList[index].itemList}
              this.componentList.push(component) 
            }
            if(this.answerDetail.questionList[index].qType === 3) {
              let component={value:'CheckBoxDetail', qTitle:this.answerDetail.questionList[index].qTitle, answerList:this.answerDetail.questionList[index].answerList, itemList:this.answerDetail.questionList[index].itemList}
              this.componentList.push(component)
            }
            if(this.answerDetail.questionList[index].qType === 4) {
              let component={value:'DropBoxDetail', qTitle:this.answerDetail.questionList[index].qTitle, answerList:this.answerDetail.questionList[index].answerList, itemList:this.answerDetail.questionList[index].itemList}
              this.componentList.push(component)
            }
          }
          return this.componentList
         }
         
      },
      methods: {
        ...mapActions([]),
      },
      components: {
        SelectDetail,
        EssayDetail,
        CheckBoxDetail,
        DropBoxDetail
      }
  }
  </script>
