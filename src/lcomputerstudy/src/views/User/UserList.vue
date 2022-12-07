<template>
   <v-container>
        <v-card class="pa-3 ">
          <v-list three-line>
            <template>
              
              <v-list-item
                v-for="(item) in userList"  
                :key="item.username"
                @click="UserDetail(item.username)"
              >
                <v-list-item-content >
                  <v-list-item-subtitle v-html="item.username"></v-list-item-subtitle>
                  <v-list-item-subtitle v-html="item.name"></v-list-item-subtitle>
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
    data() {
      return {
        page: 1
      }
    },
    created() {
      this.$store.dispatch('UserList', this.page)
    },
    computed: {
      ...mapState(['userList', 'pagination']),
      pageLength() {
        return Math.ceil(this.pagination.count/this.pagination.perPage)
    }
    },
    methods: {
      ...mapActions(['UserDetail']),
      updatePage() {
        this.$store.dispatch('UserList', this.page)
      }
    }
  }
</script>