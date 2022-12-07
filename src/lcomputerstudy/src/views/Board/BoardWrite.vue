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
        <v-btn @click="upload">첨부파일</v-btn>
        <br/>
        <v-card v-show="uploadShow">
            <v-list>
                <v-list-item>
                    <template>
                        <v-list-item-action style="width: 150px">
                        <p>이미지 첨부</p>
                        </v-list-item-action>
                        <v-list-item-content>
                            <v-col cols="12" md="5">
                            <v-file-input
                            v-model="file"
                            accept="image/png, image/jpeg, image/bmp"
                            prepend-icon="mdi-camera"
                            label="image"
                            @change="uploadImage"
                            ></v-file-input>
                            </v-col>
                            <div>
                                upload :
                                <v-btn
                                v-for="(item, index) in this.files"
                                :key="index"
                                @click="deleteImage(item)"
                                depressed
                                small
                                >{{ item.name }} X</v-btn
                                >
                            </div>
                        </v-list-item-content>
                        <v-btn @click="cancel">취소</v-btn>
                    </template>
                </v-list-item>
        </v-list>
    </v-card>
    <br/>
    <v-btn @click=writeBoard>등록</v-btn>
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
            file: null,
            uploadShow: 0,
        }
    },
    methods: {
        ...mapActions(['BoardWrite', 'Upload']),
        upload() {
            this.uploadShow = 1
        },
        uploadImage() {
            if (!!this.file) {
                this.files.push(this.file)                
                this.file = null
            }
        },
        deleteImage(item) {
            let num = this.files.indexOf(item)
            this.files.splice(num, 1)
        },
        cancel() {
            this.uploadShow = 0
            this.files = []
        },
        writeBoard() {
            let formData =  new FormData();
            for(let i=0; i<this.files.length; i++) {
                formData.append('file', this.files[i])
            }
            this.$store.dispatch('Upload', formData)
            
            this.$store.dispatch('BoardWrite', {
                bTitle:this.bTitle,
                bContent:this.bContent,
                bWriter:this.Userinfo.User_Name,
                uId:this.Userinfo.User_Id,
                bDateTime:new Date()
            })
            
        }
    },
    computed: {
        ...mapState(["Userinfo", 'uploadUpdate']),
        files: {
            get() {
                return this.$store.state.files
            },
            set(value) {
                this.$store.commit('update_files', value)
            }
        }
    }
}
</script>