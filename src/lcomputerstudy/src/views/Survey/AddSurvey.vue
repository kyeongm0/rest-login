<template>
    <v-container>
        <h1>Survey Page</h1>
        <v-card max-width="360">
            <v-btn
            outlined
            @click="addSelect"
            >객관식</v-btn>
            <v-btn
            outlined
            @click="addEssay"
            >주관식</v-btn>
            <v-btn
            outlined
            @click="addCheck"
            >체크박스</v-btn>
            <v-btn
            outlined
            @click="addDrop"
            >드롭박스</v-btn>
        </v-card>
        <br/>
        <v-card outlined>
            <v-text-field
            v-model="sTitle"
            label="제목"></v-text-field>
            <v-text-field
            v-model="sSubTitle"
            label="설명"></v-text-field>
        </v-card>
        <br/>
        <v-card 
        outlined
        >
            <component
            v-for="(component, i) in components"
            :key="i"
            :is="component.value"
            :componentList="components"
            :qList="qList"
            :checkNum="i" 
            :questionTitle="questionTitle"
            ></component>
        </v-card>
        <br/>
        <v-btn @click="AddSurvey({
            sTitle:sTitle,
            sSubTitle:sSubTitle,
            questionList:qList,
            sWriter:Userinfo.User_Name,
            uId:Userinfo.User_Id
        })">등록</v-btn>
    </v-container>
</template>

<script>

import SelectQuestion from '@/components/Question/SelectQuestion.vue'
import EssayQuestion from '@/components/Question/EssayQuestion.vue'
import CheckBoxQuestion from '@/components/Question/CheckBoxQuestion.vue'
import DropBoxQuestion from '@/components/Question/DropBoxQuestion.vue'

import { mapState,mapActions } from 'vuex'


export default {
    data() {
        return {
            sTitle: null,
            sSubTitle: null,
            componentList: [],
            qList: [],
            questionTitle: null,
            testQ: null
        }
    },
    methods: {
        ...mapActions(['AddSurvey']),
        addSelect() {
            let selectComponent = {value: 'SelectQuestion', type: 1}
            this.componentList.push(selectComponent)
            let questionList = {qTitle: this.questionTitle, itemList: [], qType: 1}
            this.qList.push(questionList)
        },
        addEssay() {
            let essayComponent = {value: 'EssayQuestion', type: 2}
            this.componentList.push(essayComponent)
            let questionList = {qTitle: this.questionTitle, itemList: [], qType: 2}
            this.qList.push(questionList)
        },
        addCheck() {
            let checkComponent = {value: 'CheckBoxQuestion', type: 3}
            this.componentList.push(checkComponent)
            let questionList = {qTitle: this.questionTitle, itemList:[], qType: 3}
            this.qList.push(questionList)
        },
        addDrop() {
            let dropComponent = {value: 'DropBoxQuestion', type: 4}
            this.componentList.push(dropComponent)
            let questionList = {qTitle: this.questionTitle, itemList:[], qType: 4}
            this.qList.push(questionList)
        },
        testfunction() {
            this.questionTitle = 2
        },
    },
    components: {
        SelectQuestion,
        EssayQuestion,
        CheckBoxQuestion,
        DropBoxQuestion
    },
    computed: {
        ...mapState(['Userinfo']),
        components() {
            return this.componentList
        },
    },
}
</script>

