<template>
    <div class="container">
        <h2>게시판 등록</h2>
        <table class="board_detail">
            <colgroup>
                <col width="100px">
                <col>
            </colgroup>
            <tr>
                <th>제목</th>
                <td><input v-model="obj.title" type="text" id="title" name="title"></td>
            </tr>
            <tr>
                <th>내용</th>
                <td>
                    <textarea v-model="obj.contents" id="contents" name="contents"></textarea>
                </td>
            </tr>
        </table>
        <input type="file" id="files" name="files" multiple="multiple" />
        <input type="button" id="submit" value="저장" class="btn" @click="write">
    </div>
</template>
<script>
export default {
    name: 'BoardInsert',
    data () {
        return {
            obj: {
                title: '',
                contents: ''
            }
        }
    },
    computed: {
        newTf () {
            return !((this.$router.currentRoute.value.query || {}).boardIdx || '')
        }
    },
    mounted () {
        if (!this.newTf) {
            this.getInfo()
        }
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
                    modify: true,
                    boardIdx
                }
            })  
            const data = res.data || {}
            Object.assign(this.obj, data)
        },
        async write() {
            let msg = '생성하시겠습니까?'
            let retMsg = '게시글이 등록되었습니다.'
            let method = 'post'
            let url = '/api/board/insert'
            if (!this.newTf) {
                msg = '수정하시겠습니까?'
                retMsg = '게시글이 수정되었습니다.'
                method = 'put'
                url = '/api/board/update'
            }
            if (!await confirm(msg)) {
                return
            }
            await this.axios({
                method,
                url,
                data: {
                    ...this.obj
                }
            })
            await alert(retMsg)
            if (this.newTf) {
                this.goto('/board')
            } else {
                this.goto('/board/detail', { boardIdx: this.obj.boardIdx })
            }
        }
    }
}
</script>