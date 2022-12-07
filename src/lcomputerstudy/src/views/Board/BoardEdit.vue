<template>
    <v-flex xs12 >
        <v-form style="width:800px;margin:0 auto;">
            <v-text-field
                name="writer_title"
                label="제목"
                v-model="board_detail.bTitle"
            ></v-text-field>
          <v-textarea
            name="writer_content"
            rows="6"
            v-model="board_detail.bContent"
            ></v-textarea>
        </v-form>
        <v-btn @click="BoardEditProcess({
            bId:bId,
            bTitle:board_detail.bTitle,
            bContent:board_detail.bContent
            })">등록</v-btn>
        <v-btn @click="back">취소</v-btn>
    </v-flex>
</template>
<script>
import { mapState,mapActions } from 'vuex'
export default {
    data() {
        return {
            bTitle: null,
            bContent: null,
            bId: this.$route.params.bid,
        }
    },
    methods: {
        ...mapActions(['BoardEditProcess']),
        back() {
            this.$store.dispatch('BoardDetail', this.bId)
        }
    },
    computed: {
        ...mapState(['board_detail'])
    }
}
</script>