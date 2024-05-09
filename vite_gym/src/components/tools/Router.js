import { createRouter, createWebHistory } from 'vue-router'

import AppHome from "../../components/login/AppHome.vue"
const Router = createRouter({
    history:createWebHistory(),
    routes:[
        {
            path:'/',
            redirect:'/AppHome/first_page'
        },
        {
            path:'/home',
            component: () => import('../../components/admin/home.vue'),
            name:"home",
            children:[
                {
                    path:'addfacility',
                    component: () => import('../../components/admin/addfacility.vue'),
                    name:"addfacility"


        },

                {
                    path:'facility',
                    component: () => import('../../components/admin/facility.vue'),
                    name:"facility"


                },
                {
                    path:'manageuser',
                    component: () => import('../../components/admin/manageuser.vue'),
                    name:"manageuser"
                }
            ]
        },
        {
            path:'/AppHome'
        ,component: AppHome,
            name:"AppHome",

            children:[
                {
                    path:'first_page',


                    component: () => import('../../components/differ_page/first_page.vue'),
                    name:"first_page"
                },

                {
                    path:'second_page',


                    component: () => import('../../components/differ_page/second_page.vue'),
                    name:"second_page"
                },

            ]


        },

        {
            path:'/AppBody',
            component: () => import('../../components/login/AppBody.vue'),
            name:"AppBody"
        },

        {
                 path:'/adminlogin',
            component: () => import('../../components/login/admin-login.vue'),
                 name:"adminlogin"
        },

        {
            path:'/login',
            component: () => import('../../components/login/Login.vue'),
            name:"login"
        },
        {
            path:'/Appregister',

            component: () => import('../../components/login/Appregister.vue'),
            name:"Appregister",

        },

    ]
})


export default Router;