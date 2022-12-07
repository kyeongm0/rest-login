<template>
    <v-flex xs12>
        <v-textarea
        label="댓글입력"
        v-model="cContent"
        rows="1"
        ></v-textarea>
        <v-btn @click="CommentReply({
            cContent:cContent,
            cWriter:Userinfo.User_Name,
            cId:cId,
            bId:bId,
            cGroup:cGroup,
            cOrder:cOrder,
            cDepth:cDepth
            })">등록</v-btn>
    </v-flex>
</template>

<script>
import { mapState,mapActions } from 'vuex'

export default {
    props:['cId','cGroup','cOrder','cDepth','bId'],
    data() {
        return {
            cContent: null
        }
    },
    methods: {
        ...mapActions(['CommentReply'])
    },
    computed: {
        ...mapState(["Userinfo"]),
    }
}
</script>