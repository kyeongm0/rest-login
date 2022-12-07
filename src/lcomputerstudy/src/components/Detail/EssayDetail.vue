<template>
    <v-card
    outlined
    >
        <h1>{{qTitle}}</h1>
        <v-text-field
        disabled
        v-model="essayA"
        label="답변"
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
    props: ['qTitle', 'answerList', 'itemList', 'num'],
    created() {
        this.essayA = this.answerList[0].value
    },
}
</script>