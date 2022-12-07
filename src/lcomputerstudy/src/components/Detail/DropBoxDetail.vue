<template>
    <v-card
    outlined
    >
        <h1>{{qTitle}}</h1>
        <v-select
        disabled
        :items="answer"
        v-model="dropA"
        label="선택"
        ></v-select>
        </v-card>
</template>

<script>
export default {
    data() {
        return {
            dropA: '1',
            answer: [],
        }
    },
    props: ['qTitle', 'answerList', 'itemList', 'num'],
    created() {
        for(let index = 0; index < this.itemList.length; index++) {
            this.answer.push(this.itemList[index].value)
        }
        this.dropA = this.answerList[0].value
    },
}
</script>