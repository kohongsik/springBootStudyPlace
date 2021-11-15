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
        component: () => import('../components/board/BoardList.vue'),
        children: [
            // {
            //     path: '/',
            //     component: () => import('../components/board/BoardList.vue')
            // },
            {
                path: 'list',
                name: 'boardList',
                component: () => import('../components/board/BoardList.vue')
            }
        ]
    }
]
export default createRouter({
    history: createWebHashHistory(),
    routes
})