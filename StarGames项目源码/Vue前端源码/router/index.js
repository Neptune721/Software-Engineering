import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import Info from "@/components/Info"
import GameLibrary from "@/components/GameLibrary";
import Commodity from "@/components/Commodity";
import AdminCommodity from "@/components/AdminCommodity";
import AdminPage from "@/components/AdminPage";
import AdminUser from "@/components/AdminUser";
import Register from "@/components/Register";
import FriendList from "@/components/FriendList";
import ShoppingCart from "@/components/ShoppingCart";
import WishList from "@/components/WishList";
import SearchGame from "@/components/SearchGame";
import SellerBackground from "@/components/SellerBackground";
import upload2 from "@/components/upload2";
import upload from "@/components/upload";
import About from "@/components/About";
import main from "@/components/main";
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/Login',
      name: 'Login',
      component: Login
    },
    {
      path:'/',
      name:'Home',
      component: Home
    },
    {
      path:'/Info',
      name:'Info',
      component: Info
    },
    {
      path:'/GameLibrary',
      name:'GameLibrary',
      component: GameLibrary
    },
    {
      path:'/Commodity/:id',
      name:'Commodity',
      component: Commodity
    },
    {
      path:'/Register',
      name:'Register',
      component: Register
    },
    {
      path:'/AdminCommodity',
      name:'AdminCommodity',
      component: AdminCommodity
    },
    {
      path:'/AdminPage',
      name:'AdminPage',
      component: AdminPage
    },
    {
      path:'/AdminUser',
      name:'AdminUser',
      component: AdminUser
    },
    {
      path:'/FriendList',
      name:'FriendList',
      component: FriendList
    },
    {
      path:'/ShoppingCart',
      name:'ShoppingCart',
      component: ShoppingCart
    },
    {
      path:'/WishList',
      name:'WishList',
      component: WishList
    },
    {
      path:'/SearchGame',
      name:'SearchGame',
      component: SearchGame
    },
    {
      path:'/SellerBackground',
      name:'SellerBackground',
      component: SellerBackground
    },
    {
      path:'/upload2/:id',
      name:'upload2',
      component: upload2
    },
    {
      path:'/upload',
      name:'upload',
      component: upload
    },
    {
      path:'/About',
      name:'About',
      component: About
    },
    {
      path:'/main',
      name:'main',
      component: main
    },
  ]
})
