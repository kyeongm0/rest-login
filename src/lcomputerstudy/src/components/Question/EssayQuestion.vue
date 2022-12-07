<template>
    <v-card
    outlined
    >
        Essay Question
        <div class="essayQuestion">
            <div class="essay_question">
            <v-text-field
            v-model="essayQ"
            label="질문"
            @input="qtitle()"
            ></v-text-field>
            </div>
            <div class="essay_answer">
                <v-text-field
                v-model="essayA"
                label="답변"
                ></v-text-field>
            </div>
            <v-btn @click="deleteComponent">질문삭제</v-btn>
            <v-btn @click="addEssay">등록</v-btn>
        </div>
    </v-card>
</template>

<script>
export default {
    data() {
        return {
            essayQ: null,
            essayA: null,
        }
    },
    methods: {
        deleteComponent() {
            this.componentList.splice(this.checkNum, 1)
            this.qList.splice(this.qList[this.checkNum], 1)
        },
        addEssay() {
            let qlist= {qTitle:this.questionTitle, itemList:null}
            this.qList.push(qlist)
            console.log(this.qList)
        },
        qtitle() {
            this.qList[this.checkNum].qTitle= this.essayQ
        }
    },
    props: ['componentList', 'checkNum', 'qList'],
}
</script>
