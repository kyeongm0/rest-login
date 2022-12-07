import Vue from 'vue'
import Vuex from 'vuex'
import Route from '../router/index'
import axios from 'axios'
import router from '../router/index'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    Userinfo:{User_Id:null,User_Name:null,User_auth:[],User_token:null},
    boardlist:[],
    board_detail:[],
    userList:[],
    user_detail:[],
    pagination:{page:null, count:null, perPage:null},
    commentList:[],
    searchmemory:{bSearch:null, bKeyword:null, page:null},
    surveyList:[],
    surveyDetail:[],
    answerDetail:[],
    answerCount:[],
    files:[],
    uploadUpdate:{bId: null, lId:[]},
  },
  mutations: {
    NewUsers: (state,payload) => {
      state.userList.push(payload)
      Route.push("/login")
    },
    SET_USER(state, data) {
      state.Userinfo.User_Id = data.username
      state.Userinfo.User_Name = data.name
      state.Userinfo.User_auth = data.roles
      state.Userinfo.User_token = data.token
      this.dispatch('UnpackToken')
      Route.push("/user")
   },
   SET_USER_LIST(state,data) {
    state.userList = data
    state.pagination = data[0].pagination
   },
   SET_USER_DETAIL(state,data) {
    state.user_detail=data
    Route.push("/userDetail/"+data.username).catch(()=>{})
   },
   SET_USER_EDIT(state,data) {
    var index = state.boardlist.findIndex(i => i.username == data)
    state.boardlist.splice(index, 1)
    Route.push("/userEdit/"+data.username)
   },
   SET_BOARD_LIST(state,data) {
     state.boardlist = data
     state.pagination = data[0].pagination
   },
   SET_BOARD_WRITE(state, data) {
    state.uploadUpdate.bId = data.bId
   },
   SET_BOARD_UPLOAD(state, data) {
    for(let i=0; i<data.length; i++) {
      state.uploadUpdate.lId.push(data[i].lId)
    }
    let uploadData = {bId: null, upList: null}
    uploadData.bId = state.uploadUpdate.bId
    uploadData.upList = state.uploadUpdate.lId
    this.dispatch('UploadUpdate', uploadData )
   },
   update_files(state, data) {
     state.files = data
   },
   SET_BOARD_DETAIL(state,data) {
     state.board_detail=data
     Route.push("/boardDetail/"+data.bId).catch(()=>{})
   },
   SET_BOARD_EDIT(state,data) {
    var index = state.boardlist.findIndex(i => i.bId == data)
    state.boardlist.splice(index, 1)
    Route.push("/boardEdit/"+data.bId)
   },
   SET_BOARD_EDIT_PROCESS(state,data) {
    Route.push("/boardlist")
   },
   SET_BOARD_DELETE(state,data) {
     var index = state.boardlist.findIndex(i => i.bId == data)
     state.boardlist.splice(index, 1)
     Route.push("/boardlist")
   },
   SET_COMMENT_LIST(state,data) {
    state.commentList = data
    state.pagination = data[0].pagination
   },
   SET_BOARD_SEARCH_LIST(state,data) {
    state.boardlist = data
    state.pagination = data[0].pagination
    state.searchmemory.bSearch = data[0].search.bSearchOrigin
    state.searchmemory.bKeyword = data[0].search.bKeyword
    state.searchmemory.page = data[0].pagination.page
    Route.push("/boardSearchList").catch(()=>{})
   },
   SET_SURVEY_LIST(state,data) {
    state.surveyList = data
    state.pagination = data[0].pagination
   },
   SET_SURVEY_DETAIL(state,data) {
    state.surveyDetail=data
    console.log(state.surveyDetail)
    Route.push("/surveyDetail/"+data.sId)
   },
   SET_SURVEY_DELETE(state,data) {
    var index = state.surveyList.findIndex(i => i.sId == data)
    state.boardlist.splice(index, 1)
    Route.push("/survey").catch(()=>{})
   },
   SET_SURVEY_USER_LIST(state,data) {
    state.surveyDetail = data
    Route.push("/surveyReport/"+data.sId)
   },
   SET_ANSWER_DETAIL(state,data) {
    state.answerDetail = data
    Route.push("/surveyAnswer/"+data.questionList[0].answerList[0].uId)
   },
   SET_ANSWER_COUNT(state, data) {
    state.answerCount = data
   },
   READ_USER_LIST(state,data) {
    state.userList = data
   },
   INSERT_TOKEN(state) {
     state.Userinfo.User_token = localStorage.getItem("token")
   },
   SET_USER_REFRESH(state,data) {
    console.log(data.roles)
    state.Userinfo.User_Id = data.username
    state.Userinfo.User_Name = data.name
    state.Userinfo.User_auth = data.authorities
    state.Userinfo.User_token = data.token
   },
   logout(state) {
    state.Userinfo.User_Id = null
    state.Userinfo.User_Name = null
    state.Userinfo.User_auth = null
    state.Userinfo.User_token = null
    localStorage.removeItem("token")
    console.log(state.Userinfo)
    console.log("로그아웃됐니?"+localStorage.getItem("token"))
    Route.push("/login")
   }
  },
  actions: {
    LoginProcess({ commit }, payload) {
      console.log(payload)
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/auth/signin', payload)
              .then(Response => {
                  console.log(Response.data)
                  if (Response.data.username != null) {
                      axios.defaults.headers.common['Authorization'] = `Bearer ${Response.data.token}`
                      localStorage.setItem("token",Response.data.token)
                      commit('SET_USER', Response.data)
                  }
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
      })
   },
   SignUp({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
        axios.post('http://localhost:9000/api/auth/signup', payload)
            .then(Response => {
                console.log(Response.data)
                if(Response.data === "success") {
                  Route.push("/signin")
                }
            })
            .catch(Error => {
                console.log('error')
                reject(Error)
                alert("아이디가 중복")
                Route.push("/signup")
            })
    })
   },
   UserList({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
        axios.get('http://localhost:9000/api/test/user/'+payload)
            .then(Response => {
                console.log(Response.data)
                commit('SET_USER_LIST', Response.data)
            })
            .catch(Error => {
                console.log('error')
                reject(Error)
            })
    })
   },
   UserDetail({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.get('http://localhost:9000/api/test/userDetail', {
        params: {
          username: payload
        }
      })
          .then(Response => {
              console.log(Response.data)
              commit('SET_USER_DETAIL', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
   },
   UserEdit({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.get('http://localhost:9000/api/test/userEdit/'+payload.username)
          .then(Response => {
              console.log(Response.data)
              commit('SET_USER_EDIT', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
   },
   UserEditProcess({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:9000/api/test/userEditProcess', payload)
          .then(Response => {
              console.log(Response.data)
              Route.push('/userDetail/payload.username')
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
   },
  UserDelete({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.delete('http://localhost:9000/api/test/userDelete/'+payload.username)
          .then(Response => {
              console.log(Response.data)
              Route.push('/userList')
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
   })
  },
  UserLevelChange({dispatch},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:9000/api/test/userLevelChange', payload)
          .then(Response => {
              console.log(Response.data)
              dispatch( 'UserDetail', payload.username).catch(()=>{})
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
   },
  BoardList({commit},payload) {
  console.log(payload)
  return new Promise((resolve, reject) => {
    axios.get('http://localhost:9000/api/test/board/'+payload)
        .then(Response => {
            console.log(Response.data)
            commit('SET_BOARD_LIST', Response.data)
        })
        .catch(Error => {
            console.log('error')
            reject(Error)
        })
    })
   },
   BoardDetail({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.get('http://localhost:9000/api/test/boardDetail', {
        params: {
          bId: payload
        }
      })
          .then(Response => {
              console.log(Response.data)
              commit('SET_BOARD_DETAIL', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
   },
   BoardEdit({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.get('http://localhost:9000/api/test/boardEdit/'+payload.bId)
          .then(Response => {
              console.log(Response.data)
              commit('SET_BOARD_EDIT', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
   },
   BoardEditProcess({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:9000/api/test/boardEditProcess', payload)
          .then(Response => {
              console.log(Response.data)
              Route.push('/boardDetail/payload.bId')
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
   },
  BoardDelete({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.delete('http://localhost:9000/api/test/boardDelete/'+payload.bId)
          .then(Response => {
              console.log(Response.data)
              commit('SET_BOARD_DELETE', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
   })
  },
  BoardWrite({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:9000/api/test/boardWrite', payload)
          .then(Response => {
              console.log(Response.data)
              commit('SET_BOARD_WRITE', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
  },
  Upload({commit},payload) {    
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:9000/api/test/upload', payload)
          .then(Response => {
              console.log(Response.data)
              commit('SET_BOARD_UPLOAD', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
  },
  UploadUpdate({commit},payload) {    
    console.log('here')
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:9000/api/test/uploadUpdate', payload)
          .then(Response => {
              console.log(Response.data)
              Route.push('/boardlist')
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
  },
  BoardReplyProcess({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:9000/api/test/boardReplyProcess', payload)
          .then(Response => {
              console.log(Response.data)
              if(Response.data === "success") {
                Route.push('/boardlist')
              }
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
  })
  },
  CommentList({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.get('http://localhost:9000/api/test/comment/'+payload.checkId+'/'+payload.pageNum)
          .then(Response => {
              console.log(Response.data)
              commit('SET_COMMENT_LIST', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
   },
   CommentWrite({dispatch},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:9000/api/test/commentWrite', payload)
          .then(Response => {
              console.log(Response.data)
              dispatch( 'CommentList', {pageNum: Response.data.pagination.page, checkId: Response.data.bId} )
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
        })
  },
  CommentReply({dispatch},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:9000/api/test/commentReply', payload)
          .then(Response => {
              console.log(Response.data)
              dispatch( 'CommentList', {pageNum: Response.data.pagination.page, checkId: Response.data.bId} )
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
        })
  },
  CommentEdit({dispatch},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:9000/api/test/commentEdit', payload)
          .then(Response => {
              console.log(Response.data)
              dispatch( 'CommentList', {pageNum: Response.data.pagination.page, checkId: Response.data.bId} )
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
  },
  CommentDelete({dispatch},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.delete('http://localhost:9000/api/test/commentDelete/'+payload.cId)
          .then(Response => {
              console.log(Response.data)
              dispatch( 'CommentList', {pageNum: payload.page, checkId: payload.bId} )
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
   })
  },
  BoardSearch({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.get('http://localhost:9000/api/test/boardSearch/'+payload.search+'/'+payload.keyword+'/'+payload.page)
          .then(Response => {
              console.log(Response.data)
              commit('SET_BOARD_SEARCH_LIST', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
   },
  AddSurvey({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:9000/api/test/addSurvey', payload)
          .then(Response => {
              console.log(Response.data)
              Route.push('/survey')
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
        })
  },
  SurveyList({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.get('http://localhost:9000/api/test/survey/'+payload)
          .then(Response => {
              console.log(Response.data)
              commit('SET_SURVEY_LIST', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
  },
  SurveyDetail({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.get('http://localhost:9000/api/test/surveyDetail', {
        params: {
          sId: payload
        }
      })
          .then(Response => {
              console.log(Response.data)
              commit('SET_SURVEY_DETAIL', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
  },
  SurveyDelete({dispatch},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.delete('http://localhost:9000/api/test/surveyDelete/'+payload.sId)
          .then(Response => {
              console.log(Response.data)
              dispatch( 'SurveyList', payload.pageNum )
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
   })
  },
  SurveyAnswer({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.post('http://localhost:9000/api/test/surveyAnswer', payload)
          .then(Response => {
              console.log(Response.data)
              if(Response.data === "success") {
                Route.push('/survey')
              }
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
  },
  Analysis({commit}, payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.get('http://localhost:9000/api/test/surveyReport', {
        params: {
          sId: payload
        }
      })
          .then(Response => {
              console.log(Response.data)
              commit('SET_SURVEY_USER_LIST', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
  },
  AnswerDetail({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.get('http://localhost:9000/api/test/answerDetail', {
        params: {
          username: payload.username,
          sId: payload.sId
        }
      })
          .then(Response => {
              console.log(Response.data)
              commit('SET_ANSWER_DETAIL', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
  },
  QuestionChart({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
      axios.get('http://localhost:9000/api/test/questionChart', {
        params: {
          qId: payload.qId,
          sId: payload.sId
        }
      })
          .then(Response => {
              commit('SET_ANSWER_COUNT', Response.data)
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
          })
    })
  },
  admin({commit}) {
    let token = localStorage.getItem("token")
    console.log(token)
    return new Promise((resolve, reject) => {
      axios.get('http://localhost:9000/api/admin/adminPage')
          .then(Response => {
            console.log(Response.data)
             commit('READ_USER_LIST',Response.data)
          })
          .catch(Error => {
            // console.log(Error)
              console.log('admin_error')
              Route.push("/")
          })
  })
  },
  UnpackToken({commit}) {
    return new Promise((resolve, reject) => {
      axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`
      axios.get('http://localhost:9000/api/auth/unpackToken')
          .then(Response => {
            console.log(Response.data)
            commit('SET_USER_REFRESH',Response.data)
          })
          .catch(Error => {
            console.log(Error)
              console.log('unpackToken_error')
          })
  })
  },
  
}
})