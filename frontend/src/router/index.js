import Vue from 'vue'
import VueRouter from 'vue-router'
import Employee from '../views/Employee.vue'
import Department from '../views/Department.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'employee',
    component: Employee
  },
  {
    path: '/department',
    name: 'department',
    component: Department
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
