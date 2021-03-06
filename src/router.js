import Vue from 'vue';
import Router from 'vue-router';

import Home from './page/Home';

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
      {
        path: "/",
        alias: "/home",
        name: "home",
        component: Home
      } 
    ]
  });