<template>
    <v-flex xs12>
        <v-text-field
        label="댓글수정"
        v-model="comment.cContent"
        ></v-text-field>
        <v-btn @click="CommentEdit({
            cId:comment.cId,
            bId:comment.bId,
            cContent:comment.cContent
        })"
        >등록</v-btn>
    </v-flex>
</template>

<script>
import { mapState,mapActions } from 'vuex'

export default {    
    props:['cContent', 'bId', 'cId'],
    data() {
        return {
            comment: {}      
        }
    },
    created() {
        this.comment.cContent = this.cContent
        this.comment.cId = this.cId
        this.comment.bId = this.bId
    },
    methods: {
        ...mapActions(['CommentEdit']),
        changeComment() {
            console.log(this.comment)
        }
    },
    computed: {
        ...mapState(["Userinfo"]),
    }
}
</script>