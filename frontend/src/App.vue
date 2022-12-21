<template>
  <div id="app">
    <!-- 导航栏 -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
      <div class="container">
        <label class="navbar-brand">员工管理系统</label>
        <div class="collapse navbar-collapse">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item" @click="activate(1)">
              <router-link to='/' class="nav-link" :class="{ active : active_index == 1 }">员工管理</router-link>
            </li>
            <li class="nav-item" @click="activate(2)">
              <router-link to='/department' class="nav-link" :class="{ active : active_index == 2 }">部门管理</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  data(){
    return {
      active_index: 1
    }
  },
  methods: {
    activate(i){
      this.active_index = i;
    }
  }
}
</script>