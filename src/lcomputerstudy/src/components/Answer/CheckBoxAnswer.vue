<template>
    <v-card
    outlined
    >
        <h1>{{qTitle}}</h1>
        <v-list
        v-for="(item, i) in answer"
        :key="i">
            <v-checkbox
            v-model="checkA"
            :label="item.value"
            :value="item.value"
            @click="checkAnswer"
            ></v-checkbox>
        </v-list>
    </v-card>
</template>

<script>
export default {
    data() {
        return {
            checkA: [],
            answer: [],
        }
    },
    props: ['qTitle', 'itemList', 'qList', 'keyNum'],
    created() {
        this.answer = this.itemList
    },
    methods: {
        checkAnswer() {
            if(this.qList[this.keyNum].answerList.length > this.checkA.length) {
                this.qList[this.keyNum].answerList.splice(this.qList[this.keyNum].answerList.length-1 , 1)
            }
            else {
                let check = {value : this.checkA[this.checkA.length-1]}
                this.qList[this.keyNum].answerList.push(check)
                console.log(this.qList)
            }
        },
    }
}
</script>