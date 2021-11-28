<template>
  <div class="container">
    <h2>게시글 상세 화면</h2>
    <table class="board_detail">
      <colgroup>
        <col width="15%"/>
        <col width="35%"/>
        <col width="15%"/>
        <col width="35%"/>
      </colgroup>
      <caption>게시글 상세내용</caption>
      <tbody>
        <tr>
            <th scope="row">글 번호</th>
            <td>{{ obj.boardIdx }}</td>
            <th scope="row">조회수</th>
            <td>{{ obj.hitCnt }}</td>
        </tr>
        <tr>
            <th scope="row">작성자</th>
            <td>{{ obj.createUserId }}</td>
            <th scope="row">작성일</th>
            <td>{{ obj.createDate }}</td>
        </tr>
        <tr>
            <th scope="row">제목</th>
            <td colspan="3">{{ obj.title }}</td>
        </tr>
        <tr>
            <td colspan="4" class="view_text">
                <textarea v-model="obj.contents" title="내용" id="contents" name="contents" readonly ></textarea>
            </td>
        </tr>
      </tbody>
    </table>
      <input type="hidden" id="boardIdx" name="boardIdx" th:value="${board.boardIdx }">
    <div class="file_list">
        <a
            th:href="@{/board/downloadBoardFile.do(idx=${list.idx}, boardIdx=${list.boardIdx})}"
            th:each="list : ${board.boardFileVOList}"
            th:text="|${list.originalFileName}(${list.fileSize} Kb)|"
        >
        </a>
    </div>
    <input type="button" value="목록으로" @click="goList" />
    <input type="button" value="수정하기" @click="goModify" />
    <input type="button" value="삭제하기" @click="deleteIt" />
  </div>
</template>

<script>
export default {
    name: 'BoardDetail',
    data () {
      return {
        obj: {
          boardIdx: 0,
          hitCnt: 0,
          createUserId: '',
          createDate: '',
          title: '',
          contents: ''
        }
      }
    },
    mounted () {
      console.log('route', this.$router.currentRoute)
      this.getInfo()
    },
    methods: {
      async getInfo () {

        const boardIdx = (this.$router.currentRoute.value.query || {}).boardIdx || ''

        if (!boardIdx) {
          await alert('삭제된 데이터 입니다.')
          this.goto('/board')
        }
        const res = await this.axios({
            method: 'get',
            url: '/api/board/detail',
            params: {
                modify: false,
                boardIdx
            }
        })
        const data = res.data || {}
        Object.assign(this.obj, data)
      },
      goList () {
        this.goto('/board')
      },
      goModify () {
        this.goto('/board/insert', { boardIdx: this.obj.boardIdx })
      },
      async deleteIt () {
        if (! await confirm('삭제하시곘습니까 ?')) {
          return
        }
        await this.axios({
          method: 'delete',
          url: `/api/board/${this.obj.boardIdx}`,
          data: {
            ...this.obj
          }
        })
        await alert('삭제되었습니다.')
        this.goList()
      }
    }
}
</script>