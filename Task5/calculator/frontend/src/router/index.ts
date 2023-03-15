import {
  createRouter,
  createWebHistory,
  RouteRecordRaw,
} from "vue-router";
import CalculatorView from "@/views/CalculatorView.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "calculator",
    component: CalculatorView,
  },
  {
    path: "/feedback",
    name: "feedback",
    component: () =>
      import(/* webpackChunkName: "feedback" */ "@/views/FeedbackView.vue"),
  },
  {
    path: "/register",
    name: "register",
    component: () =>
      import(/* webpackChunkName: "feedback" */ "@/views/AuthenticateView.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
