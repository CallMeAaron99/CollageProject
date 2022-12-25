<template>
  <div class="container">
      <div class="row mb-3">
          <label class="col-sm-1 col-form-label" style="width: auto; padding-right: 0px;">部门名称：</label>
          <div class="col-sm-3">
              <input type="text" class="form-control" placeholder="名称" v-model="deptName">
          </div>
          <div class="col-sm-3">
              <button type="button" class="btn btn-primary" @click="searchDepartment()">搜索</button>
          </div>
      </div>
      <div class="mb-3">
          <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="addBtn()">新增部门</button>
      </div>
      <!-- 表格 -->
      <div class="table-responsive text-center">
          <table class="table table-hover table-bordered mb-3">
              <thead>
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">名称</th>
                    <th scope="col">操作</th>
                  </tr>
              </thead>
              <tbody>
                  <tr v-for="(item, index) in departments" :key="index">
                      <th scope="row">{{ item.deptId }}</th>
                      <td>{{ item.deptName }}</td>
                      <td>
                          <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="updateBtn(item.deptId)">修改</button>
                          <button type="button" class="btn btn-danger btn-sm" style="margin-left: 1em;" @click="deleteEmployee(item)">删除</button>
                      </td>
                  </tr>
              </tbody>
          </table>
      </div>
      <!-- 分页 -->
      <!-- <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-end">
              <li class="page-item"><a class="page-link" href="#">上一页</a></li>
              <li class="page-item"><a class="page-link" href="#">1</a></li>
              <li class="page-item"><a class="page-link" href="#">2</a></li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
              <li class="page-item"><a class="page-link" href="#">4</a></li>
              <li class="page-item"><a class="page-link" href="#">5</a></li>
              <li class="page-item"><a class="page-link" href="#">下一页</a></li>
          </ul>
      </nav> -->

      <!-- 模态框 -->
      <div class="modal fade" id="exampleModal" tabindex="-1" aria-hidden="true">
          <form>
              <div class="modal-dialog">
                  <div class="modal-content">
                  <div class="modal-header">
                      <h1 class="modal-title fs-5">{{ modalTitle }}</h1>
                      <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                  </div>
                  <div class="modal-body">
                      <div class="mb-3">
                          <label class="form-label">部门名称</label>
                          <input type="text" class="form-control" v-model="department.deptName" required>
                      </div>
                  </div>
                  <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                      <button type="submit" class="btn btn-primary" @click="saveBtn()">保存</button>
                  </div>
                  </div>
              </div>
          </form>
      </div>
  </div>
</template>

<script>
import axios from "axios"
const baseUrl = "http://localhost:8080"

export default {
    data(){
        return {
            deptName: "",
            modalTitle: "",
            departments: [],
            department: {
                deptId: 0,
                deptName: ""
            }
        }
    },
    created(){
        this.getDepartments();
    },
    methods: {
        // 获取部门
        getDepartments(){
            axios.get(new URL("api/departments", baseUrl)).then(res => {
                this.departments = res.data;
            });
        },
        // 搜索
        searchDepartment(){
            let deptName = this.deptName.trim();
            // 如果搜索条件存在
            if(deptName){
                let url = new URL("api/departments", baseUrl);
                url.searchParams.append('deptName', deptName);
                axios.get(url.href).then(res=>{
                    this.departments = res.data; // 获得部门信息
                });
            }else{
                this.getDepartments();
            }
        },
        // 是否有重名部门
        hasDuplicateName(name){
            let url = new URL("api/departments", baseUrl);
            url.searchParams.append('name', name);
            return axios.get(url.href);
        },
        // 添加
        addDepartment(){
            axios.post(new URL("api/departments", baseUrl), this.department).catch(error => {
                alert("部门'"+ this.department.deptName +"'已存在'");
            });
        },
        // 更新
        updateDepartment(){
            axios.put(new URL("api/departments/" + this.department.deptId, baseUrl), this.department).catch(error => {
                alert("部门'"+ this.department.deptName +"'已存在'");
            });
        },
        // 删除
        deleteEmployee(item){        
            if(confirm('确定删除部门"' + item.deptName + '"?')){
                axios.delete(new URL("api/departments/" + item.deptId, baseUrl)).then(res=> { this.getDepartments() }).catch(error => {
                    alert("部门'"+ item.deptName +"'还有关联员工，无法删除'");
                });
            }
        },
        // 新增按钮事件
        addBtn(){
            this.modalTitle = "新增部门";
            this.department = {
                deptId: 0,
                deptName: ""
            }
        },
        // 更新按钮事件
        updateBtn(id){
            this.modalTitle = "修改部门";
            axios.get(new URL("api/departments/" + id, baseUrl)).then(res => {
                this.department = res.data;
            });
        },
        // 保存按钮事件
        saveBtn(){
            // 防止全空格输入
            this.department.deptName = this.department.deptName.trim();
            if(this.department.deptId){
                this.updateDepartment();
            }else{
                this.addDepartment();
            }
        }
    }
}
</script>
