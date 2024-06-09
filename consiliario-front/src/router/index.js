import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'
import StudentRegister from '@/components/StudentRegister.vue'
import StudentList from '@/components/StudentList.vue'
import LoginView from '@/views/LoginView.vue'
import DashboardLayout from '@/components/DashboardLayout.vue'
import ResourceRegister from '@/components/ResourceRegister.vue'
import TeacherRegister from '@/views/TeacherRegisterView.vue';
import RecommendationList from '@/components/RecommendationList.vue';

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    component: LoginView
  },
  {
    path: '/',
    component: DashboardLayout,
    meta: { requiresAuth: true },
    children: [
      { path: '', component: StudentList },
      { path: 'register-student', component: StudentRegister },
      { path: 'list-students', component: StudentList },
      { path: 'register-resource', component: ResourceRegister },
      { path: 'recommendations', component: RecommendationList  }      
    ]
  },
  {
    path: '/register-teacher',
    component: TeacherRegister
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  const isAuthenticated = localStorage.getItem('auth') === 'true'
  if (to.matched.some((record) => record.meta.requiresAuth) && !isAuthenticated) {
    next({ path: '/login' })
  } else {
    next()
  }
})

export default router
