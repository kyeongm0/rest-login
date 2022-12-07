<template>
    <v-card
    outlined
    >
        Select Question
        <div class="selctQuestion">
            <div class="select_question">
            <v-text-field
            v-model="selectQ"
            label="질문"
            @input="qtitle()"
            ></v-text-field>
            </div>
            <v-text-field
            v-model="selectAnswerList"
            label="항목추가"
            ></v-text-field>
            <v-btn @click="addAnswerList(selectAnswerList)">항목추가</v-btn>

            <div class="select_answer">
                <v-radio-group v-model="selectA">
                    <v-list 
                    v-for="(item, i) in answer"
                    :key="i">
                        <v-radio
                        :label="item"
                        ></v-radio>
                        <v-btn 
                        width="30"
                        @click="deleteOption(item)"
                        >항목삭제</v-btn>
                    </v-list>
                </v-radio-group>
                <v-btn @click="deleteComponent">질문삭제</v-btn>
            </div>
        </div>
    </v-card>
</template>

<script>
import { mapState,mapActions } from 'vuex'

export default {
    data() {
        return {
            selectQ: null,
            selectA: null,
            selectAnswerList: null,
            answer: [],
            showValue: 0,
            addOption: null,
        }
    },
    methods: {
        ...mapActions([]),
        addAnswerList(selectAnswerList) {
            this.answer.push(selectAnswerList)
            let item= {value: selectAnswerList}
            this.qList[this.checkNum].itemList.push(item)
            this.selectAnswerList= null
        },
        deleteOption(item) {
            console.log(item)
            while(this.answer.indexOf(item) !== -1) {
                this.answer.splice(this.answer.indexOf(item), 1)
            }
            this.qList[this.checkNum].itemList.splice(this.qList[this.checkNum].itemList.indexOf(item), 1)
            console.log(this.qList[this.checkNum].itemList)
        },        
        deleteComponent() {
            this.componentList.splice(this.checkNum, 1)
            this.qList.splice(this.qList[this.checkNum], 1)
        },
        qtitle() {
            this.qList[this.checkNum].qTitle= this.selectQ
        }
    },
    props: ['componentList', 'checkNum', 'qList', 'questionTitle'],
}
</script>