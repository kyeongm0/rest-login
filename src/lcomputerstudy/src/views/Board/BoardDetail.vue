<template>
  <v-flex xs12>
      <table>
        <tr>
          <td colspan="2" style="font-weight:700;"> {{board_detail.bTitle}}</td>
        </tr>
        <tr style="font-size:0.9rem;">
          <td> 작성자 :  {{board_detail.bWriter}}</td>
          <td style="text-align:right">{{board_detail.bDateTime}}</td>
        </tr>
        <tr>
          <td colspan="2" style="border:none;">{{board_detail.bContent}}</td>
        </tr>
        <tr v-show="board_detail.uploadList[0].upload != null">
          <v-row>
            <v-col 
            cols="10"
            v-for="(item, i) in fileLoad"
            :key="i"
            >
              <v-card width="300">
                <v-img
                :src="item.upload"
                class="mx-auto"
                width="300"
                height="200"
                ></v-img>
                <v-card-title>
                  {{item.fileName}}
                </v-card-title>
              </v-card>
            </v-col>
          </v-row>
        </tr>
        <tr>
          <td colspan="2" style="border:none;text-align:right;border-top:3px double #ededed">
            <v-btn router :to="{name:'BoardList'}">목록</v-btn>
            <v-btn 
            v-show='Userinfo.User_auth.length === 1'
            router :to="{name:'BoardReply'}">답글</v-btn>
            <v-btn 
            v-show="board_detail.uId===Userinfo.User_Id"
            @click="BoardEdit({bId:bId})">수정</v-btn>
            <v-btn 
            v-show='board_detail.uId===Userinfo.User_Id || Userinfo.User_auth.length === 2'
            @click="BoardDelete({bId:bId})">삭제</v-btn>
          </td>
        </tr>
      </table>
      <v-flex xs12 >
        <v-form style="width:800px;margin:0 auto;">
          <v-textarea
            name="writer_content"
            rows="1"
            value="내용을 입력해주세요"
            v-model="cContent"
            ></v-textarea>
        </v-form>
        <v-btn @click="createComment(cContent)">등록</v-btn>
      </v-flex>
      <v-list>
        <template 
        v-for="item in commentList"
        >        
          <v-list-item>
            <v-list-item-content >
              <v-list-item-title v-html="item.cContent"></v-list-item-title>
              <v-list-item-subtitle v-html="item.uId"></v-list-item-subtitle>
              <ReplyComment 
              v-show="item.cShowReply"
              :cId="item.cId"
              :bId="item.bId"
              :cGroup="item.cGroup"
              :cOrder="item.cOrder"
              :cDepth="item.cDepth"
              ></ReplyComment>
              <EditComment 
              v-show="item.cShowEdit"
              :cId="item.cId"
              :bId="item.bId"
              :cContent="item.cContent"
              :showEdit="item.cShowEdit"
              ></EditComment>
            </v-list-item-content>
            <v-btn v-show="!item.cShowReply && Userinfo.User_auth.length === 1 && Userinfo.User_Id !== item.uId"
            @click="changeReply(item)">대댓글</v-btn>
            <v-btn v-show="item.cShowReply"
            @click="displaynonebutton(item)">취소</v-btn>
            <v-btn v-show="!item.cShowEdit && Userinfo.User_auth.length === 1 && Userinfo.User_Id === item.uId"
            @click="changeEdit(item)">수정</v-btn>
            <v-btn v-show="item.cShowEdit"
            @click="displaynonebutton(item)">취소</v-btn>
            <v-btn 
            v-show="Userinfo.User_Id === item.uId || Userinfo.User_auth.length ===2"
            @click='CommentDelete({
            cId:item.cId,
            bId:item.bId,
            page:pagination.page})'>삭제</v-btn>
          </v-list-item>
        </template>
      </v-list>
      <v-pagination 
        v-model="page"
        :length="pageLength"
        @input="updatePage"
        prev-icon="mdi-menu-left"
        next-icon="mdi-menu-right"
        v-show="commentList.length !== 0"
      ></v-pagination>
  </v-flex>
</template>

<script>
import { mapActions, mapState } from "vuex"

import ReplyComment from '@/components/BoardEdit/ReplyComment.vue'
import EditComment from '@/components/BoardEdit/EditComment.vue'

export default {
    data() {
      return {
        bId : this.$route.params.bId,
        page: 1,
        cContent: null,
        uPage: null,
        files: [],
      }
    },
    created() {
      this.$store.dispatch("BoardDetail", this.bId)
      this.$store.dispatch("CommentList", {pageNum: this.page, checkId: this.$route.params.bId})
      
    },
    computed: {
       ...mapState(["board_detail", "commentList", "pagination", "Userinfo"]),
       pageLength() {
        if(this.commentList.length !==0) {
          return Math.ceil(this.pagination.count/this.pagination.perPage)
        }
       },
       fileLoad() {
        this.files= []
        for(let i=0; i<this.board_detail.uploadList.length; i++) {
          let upload = {upload: null, fileName: null}
          upload.upload = 'http://localhost:8080/img/' + this.board_detail.uploadList[i].upload
          upload.fileName = this.board_detail.uploadList[i].fileName
          this.files.push(upload)
        }
        console.log(this.files)
        return this.files
       }
    },
    methods: {
      ...mapActions(["BoardDelete", "BoardEdit", 'BoardList', 'CommentWrite', 'CommentDelete']),
      updatePage() {
        if(this.commentList.length !==0) {
          this.$store.dispatch('CommentList', {pageNum: this.page, checkId: this.$route.params.bId})
        }
      },
      changeReply(item) {
        item.cShowReply=1
       },
      changeEdit(item) {
        item.cShowEdit=1
      },
      displaynonebutton(item) {
        item.cShowReply=0,
        item.cShowEdit=0
      },
      createComment(cContent) {
        this.$store.dispatch('CommentWrite', {
          cContent: cContent,
          cWriter: this.Userinfo.User_Name,
          bId: this.$route.params.bId,
          uPage: this.page,
          uId: this.Userinfo.User_Id
        })
        this.cContent = null
      }
    },
    components: {
      ReplyComment,
      EditComment
    }
}
</script>
<style scoped>
table {
  width:600px;
  margin:0 auto;
  border-collapse:collapse;
  text-align: center;
}
table tr td {
  border-top:1px solid #ededed;
  border-bottom:1px solid #ededed;
  padding:10px;
}
</style>