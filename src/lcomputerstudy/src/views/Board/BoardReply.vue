<template>
    <v-flex xs12 >
        <v-form style="width:800px;margin:0 auto;">
            <v-text-field
                value="제목을 입력해주세요"
                name="writer_title"
                label="제목"
                v-model="bTitle"
            ></v-text-field>
          <v-textarea
            name="writer_content"
            rows="6"
            value="내용을 입력해주세요"
            v-model="bContent"
            ></v-textarea>
        </v-form>
        <v-btn @click="BoardReplyProcess({
            bTitle:bTitle,
            bContent:bContent,
            bWriter:Userinfo.User_Name,
            bDateTime:new Date(),
            bId: board_detail.bId,
            bGroup: board_detail.bGroup,
            bOrder: board_detail.bOrder,
            bDepth: board_detail.bDepth})">등록</v-btn>
    </v-flex>
</template>
<script>
import { mapState,mapActions } from 'vuex'
export default {
    data() {
        return {
            bTitle: null,
            bContent: null,
            bWriter: null,
            bDateTime: null,
            bId: null,
            bGroup: null,
            bOrder: null,
            bDepth: null
        }
    },
    methods: {
        ...mapActions(['boardWrite', 'BoardReplyProcess'])
    },
    computed: {
        ...mapState(["Userinfo", 'board_detail'])
    }
}
</script>