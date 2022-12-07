<template>
    <v-card
    outlined
    >
        CheckBox Question
        <div class="checkQuestion">
            <div class="check_question">
            <v-text-field
            v-model="checkQ"
            label="질문"
            @input="qtitle()"
            ></v-text-field>
            </div>
            <v-text-field
            v-model="checkAnswerList"
            label="항목추가"
            ></v-text-field>
            <v-btn @click="addAnswerList(checkAnswerList)">항목추가</v-btn>
            <div class="check_answer">
                <v-list
                v-for="(item, i) in answer"
                :key="i">
                    <v-checkbox
                    v-model="checkA"
                    :label="item"
                    :value="item"
                    ></v-checkbox>
                    <v-btn @click="deleteOption(item)">항목삭제</v-btn>
                </v-list>
                <br/>
                <v-btn @click="deleteComponent">질문삭제</v-btn>
            </div>
        </div>
    </v-card>
</template>

<script>
export default {
    data() {
        return {
            checkQ: null,
            checkA: [],
            checkAnswerList: null,
            answer: [],
            showValue: 0,
            addOption: null,

        }
    },
    methods: {
        addAnswerList(checkAnswerList) {
            console.log(checkAnswerList)
            this.answer.push(checkAnswerList)
            let item= {value: checkAnswerList}
            this.qList[this.checkNum].itemList.push(item)
            this.checkAnswerList= null
        },
        deleteOption(item) {
            console.log(item)
            while(this.answer.indexOf(item) !== -1) {
                this.answer.splice(this.answer.indexOf(item), 1)
            }
            this.qList[this.checkNum].itemList.splice(this.qList[this.checkNum].itemList.indexOf(item), 1)
            console.log(this.answer)
        },
        deleteComponent() {
            this.componentList.splice(this.checkNum, 1)
            this.qList.splice(this.qList[this.checkNum], 1)
        },
        qtitle() {
            this.qList[this.checkNum].qTitle= this.checkQ
        }
    },
    props: ['componentList', 'checkNum', 'qList']
}
</script>