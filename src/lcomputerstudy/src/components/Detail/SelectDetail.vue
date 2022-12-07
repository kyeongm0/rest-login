<template>
    <v-card
    outlined
    >
        <h1>{{qTitle}}</h1>
        <v-radio-group 
        disabled
        v-model="selectA">
            <v-list 
            v-for="(item, i) in answer"
            :key="i">
                <v-radio
                :label="item.value"
                :value="item.value"
                ></v-radio>
            </v-list>
        </v-radio-group>
    </v-card>
</template>

<script>
export default {
    data() {
        return {
            selectQ: null,
            selectA: null,
            answer: [],
        }
    },
    props: ['qTitle', 'answerList', 'itemList', 'num'],
    created() {
        this.answer = this.itemList
        this.selectA = this.answerList[0].value
    },
    methods: {
        selectAnswer() {
            let select = {value : this.selectA}
            this.qList[this.keyNum].answerList[0] = select
        }
    }
}
</script>