<template>
    <div>
        <div class="container">
            <h2>게시판 목록</h2>
            <table class="board_list">
                <colgroup>
                    <col width="15%">
                    <col width="*">
                    <col width="15%">
                    <col width="20%">
                </colgroup>
                <thead>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>조회수</th>
                    <th>작성일</th>
                </thead>
                <tbody>
                    <template v-if="(list || []).length === 0">
                        <tr>
                            <td colspan="4">조회된 결과가 없습니다.</td>
                        </tr>
                    </template>
                    <template v-else>
                        <tr v-for="data in list" :key="data.idx">
                            <td>{{ data.boardIdx }}</td>
                            <td><a @click="() => detail(data)">{{ data.title }}</a></td>
                            <td>{{ data.hitCnt }}</td>
                            <td>{{ data.createDate }}</td>
                        </tr>
                    </template>
                </tbody>
            </table>
            <input type="button" value="글쓰기" @click="write" />
        </div>
    </div>
</template>
<script>
export default {
    name: 'BoardList',
    data () {
        return {
            list: [],
            searchParam: {}
        }
    },
    mounted () {
        console.log('components change')
        this.getList()
    },
    methods: {
        async getList () {
            console.log('getList')
            const res = await this.axios({
                method: 'get',
                url: '/sample/boards',
                params: {}
            })
            const list = res.data || []
            this.list = list
        },
        detail (data = {}) {
            this.goto('/board/detail', { boardIdx: data.boardIdx })
        },
        write () {
            this.goto('/board/insert')
        }
    }
}
</script>
