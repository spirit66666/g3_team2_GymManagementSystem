import { createRouter, createWebHistory } from 'vue-router'

import adminlogin from '../components/login/admin-login.vue'
import AppHome from "../components/login/AppHome.vue"
import Login from '../components/login/Login.vue'
import AppBody from "../components/login/AppBody.vue"
import Appregister from "../components/login/Appregister.vue"
import Home from '../components/home/Home.vue'
import Order from '../components/order/Order.vue'
import Goods from '../components/goods/Goods.vue'
import AddGood from '../components/goods/AddGood.vue'
import reservations from '../components/zhuye/reservations.vue'

import zhuye from '../components/zhuye/zhuye.vue'
import form from "@/components/zhuye/form.vue";

const Router = createRouter({
    history:createWebHistory(),
    routes:[

        {
                 path:'/adminlogin',
                 component:adminlogin,
                 name:"adminlogin"
        },
        {
            path:'/'
            ,redirect:'/AppHome/zhuye/0'
        },
        {
            path:'/login',
            component:Login,
            name:"login"
        },
        {
            path:'/AppHome',
            name:"AppHome",
            component:AppHome,
            children:[
                {
                path:'order/:type',
           component:Order,
                name:"Order"
          },
                {
                    path:'zhuye/:type',
                    component:zhuye,
                    name:"zhuye"
                },
                {
                    path:'reservations/:type',
                    component:reservations,
                    name:"reservation"
                },
                {
                    path:'form',

                    component:form,
                    name:"form",
                }


            ]
        },
        {
            path:'/AppBody',
            name:"AppBody",
            component:AppBody,},

        {
            path:'/home',
            component:Home,
            name:"home",
            children:[
                {
                    path:'order/:type',
                    component:Order,
                    name:"Order"
                },
                {
                    path:'goods/:type',
                    component:Goods,
                    name:"Goods"
                },
                {
                    path:'addGood/:type',
                    component:AddGood,
                    name:"AddGood"
                },

            ],

            redirect:'/home/order/0'
        },
        {
            path:'/Appregister',
            component:Appregister,
            name:"Appregister",

        },

    ]
})


export default Router;