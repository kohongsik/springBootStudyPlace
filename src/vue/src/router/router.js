import { createWebHashHistory, createRouter } from "vue-router"

const routes = [
    {
        path: '/',
        name: 'home',
        component: () => import('../components/HelloWorld.vue')
    },
    {
        path: '/sample',
        name: 'sample',
        component: () => import('../components/Sample.vue')
        
    },
    {
        path: '/board',
        name: 'board',
        component: () => import('../components/commonComponent/CommonFrameHeader.vue'),
        children: [
            {
                path: '',
                redirect: '/board/list'
            },
            {
                path: 'list',
                name: 'boardList',
                component: () => import('../components/board/BoardList.vue')
            },
            {
                path: 'detail',
                name: 'boardDetail',
                component: () => import('../components/board/BoardDetail.vue')
            },
            {
                path: 'insert',
                name: 'boardInsert',
                component: () => import('../components/board/BoardInsert.vue')
            }
        ]
    }
]
export default createRouter({
    history: createWebHashHistory(),
    routes
})