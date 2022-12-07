<template>
    <v-container>
      <v-card>
        <v-col
        class="d-flex"
        cols="12"
        sm="6"
      >
        <v-select
          :items="items"
          label="검색"
          item-text="name"
          item-value="value"
          v-model="bSearch"          
        ></v-select>
      </v-col>
        <v-text-field
          label="검색내용"
          v-model="bKeyword"
      ></v-text-field>
      <v-btn @click="BoardSearch({
        search: bSearch,
        keyword: bKeyword,
        page: 1
      })">검색</v-btn>  
      </v-card>
      <br/>
        <v-card class="pa-3 ">
          <v-btn router :to="{name:'BoardWrite'}">글쓰기</v-btn>
            <v-list three-line>
              <template >
                <v-list-item
                v-for="(item) in boardlist"
                :key="item.bId"
                @click="BoardDetail(item.bId)"
                >
                  <v-list-item-content >
                    <v-list-item-subtitle v-html="item.bTitle"></v-list-item-subtitle>
                    <v-list-item-subtitle v-html="item.bContent"></v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </template>
            </v-list>
            <v-pagination 
             v-model="page"
             :length="pageLength"
             @input="updatePage"
             prev-icon="mdi-menu-left"
             next-icon="mdi-menu-right"
            ></v-pagination>
        </v-card>
    </v-container>
</template>
<script>
import { mapState, mapActions } from "vuex"
export default {
  data () {
    return {
      page: 1,
      bKeyword: null,
      bSearch: null,
      items: [
        {name: '제목', value:'제목'},
        {name: '제목+내용', value:'제목+내용'},
        {name: '작성자', value:'작성자'}
      ]
    }
  },
  created() {
    this.$store.dispatch('BoardList', this.page)
  },
  computed: {
    ...mapState(["boardlist", 'pagination']),
    pageLength() {
      return Math.ceil(this.pagination.count/this.pagination.perPage)
    }
  },
  methods: {
    ...mapActions(['BoardDetail', 'BoardWrite', 'BoardSearch']),
    updatePage() {
      this.$store.dispatch('BoardList', this.page)
    },
  }
}
</script>