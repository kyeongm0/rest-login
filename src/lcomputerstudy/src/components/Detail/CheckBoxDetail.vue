<template>
    <v-card
    outlined
    >
        <h1>{{qTitle}}</h1>
        <v-list
        v-for="(item, i) in answer"
        :key="i">
            <v-checkbox
            disabled
            v-model="checkA"
            :label="item.value"
            :value="item.value"
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
    props: ['qTitle', 'answerList', 'itemList', 'num'],
    created() {
        this.answer = this.itemList
        for(let index=0; index<this.answerList.length; index++) {
            this.checkA.push(this.answerList[index].value)
        }
    },
}
</script>