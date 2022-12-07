<template>
    <v-card
    outlined
    >
        DropBox Question
        <div class="dropQuestion">
            <div class="drop_question">
            <v-text-field
            v-model="dropQ"
            label="질문"
            @input="qtitle()"
            ></v-text-field>
            </div>
            <br/>
            <v-text-field
            v-model="dropAnswerList"
            label="항목추가"
            ></v-text-field>
            <v-btn @click="addAnswerList(dropAnswerList)">항목추가</v-btn>

            <div class="drop_answer"
            v-show="1"
            >
                <v-select
                :items="answer"
                v-model="dropA"
                label="선택"
                ></v-select>
                <v-btn 
                @click="deleteOption(dropA)"
                >항목삭제</v-btn>
            </div>
            <br/>
            <v-btn @click="deleteComponent">질문삭제</v-btn>
        </div>
    </v-card>
</template>

<script>
export default {
    data() {
        return {
            dropQ: null,
            dropA: null,
            dropAnswerList: null,
            answer: [],
            selectList: [],
            showValue: 0,
            addOption: null,

        }
    },
    methods: {
        deleteOption(dropA) {
            console.log(dropA)
            while(this.answer.indexOf(dropA) !== -1) {
                this.answer.splice(this.answer.indexOf(dropA), 1)
            }
            this.qList[this.checkNum].itemList.splice(this.qList[this.checkNum].itemList.indexOf(dropA), 1)
            console.log(this.answer)
        },
        addAnswerList(dropAnswerList) {
            console.log(dropAnswerList)
            this.answer.push(dropAnswerList)
            let item= {value: dropAnswerList}
            this.qList[this.checkNum].itemList.push(item)
            this.dropAnswerList= null
        },
        deleteComponent() {
            this.componentList.splice(this.checkNum, 1)
            this.qList.splice(this.qList[this.checkNum], 1)
        },
        qtitle() {
            this.qList[this.checkNum].qTitle= this.dropQ
        }
    },
    props: ['componentList', 'checkNum', 'qList']
}
</script>