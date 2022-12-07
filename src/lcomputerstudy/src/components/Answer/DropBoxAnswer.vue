<template>
    <v-card
    outlined
    >
        <h1>{{qTitle}}</h1>
        <v-select
        :items="answer"
        v-model="dropA"
        label="선택"
        @change="dropAnswer"
        ></v-select>
        </v-card>
</template>

<script>
export default {
    data() {
        return {
            dropA: null,
            answer: [],
        }
    },
    props: ['qTitle', 'itemList', 'qList', 'keyNum'],
    created() {
        for(let index = 0; index < this.itemList.length; index++) {
            this.answer.push(this.itemList[index].value)
        }
    },
    methods: {
        dropAnswer() {
            let drop = {value : this.dropA}
            this.qList[this.keyNum].answerList[0] = drop
            console.log(this.qList)
        }
    }
}
</script>