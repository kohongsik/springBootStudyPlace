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
        
    },
    methods: {
        async write() {
            if (!await confirm('생성 하시겠습니까 ?')) {
                return
            }
            await this.axios({
                method: 'post',
                url: '/api/board/insert',
                data: {
                    ...this.obj
                }
            })
            await alert('게시글이 등록되었습니다.')
            this.goto('/board')
        }
    }
}
</script>