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
                            <td><a @click="getList">{{ data.title }}</a></td>
                            <td>{{ data.hitCnt }}</td>
                            <td>{{ data.createDate }}</td>
                        </tr>
                    </template>
                </tbody>
            </table>
            <!-- <a href="/board/openBoardWrite.do" class="btn">글 쓰기</a> -->
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
        }
    }
}
</script>
