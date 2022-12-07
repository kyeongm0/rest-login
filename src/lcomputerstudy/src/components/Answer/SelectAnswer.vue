<template>
    <v-card
    outlined
    >
        <h1>{{qTitle}}</h1>
        <v-radio-group v-model="selectA">
            <v-list 
            v-for="(item, i) in answer"
            :key="i">
                <v-radio
                :label="item.value"
                :value="item.value"
                @click="selectAnswer"
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
    props: ['qTitle', 'itemList', 'qList', 'keyNum'],
    created() {
        this.answer = this.itemList
    },
    methods: {
        selectAnswer() {
            let select = {value : this.selectA}
            this.qList[this.keyNum].answerList[0] = select
        }
    }
}
</script>