<template>
    <v-card
    outlined
    >
        <h1>{{qTitle}}</h1>
        <v-text-field
        v-model="essayA"
        label="답변"
        @input="essayAnswer"
        ></v-text-field>
        </v-card>
</template>

<script>
export default {
    data() {
        return {
            essayA: null,
            essayContent: null
        }
    },
    props: ['qTitle', 'itemList', 'qList', 'keyNum'],
    methods: {
        essayAnswer() {
            let essay = {value : this.essayA}
            this.qList[this.keyNum].answerList[0] = essay
            console.log(this.qList)            
        }
    }
}
</script>