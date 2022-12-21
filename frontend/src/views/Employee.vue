<template>
    <div class="container">
        <div class="row mb-3">
            <label class="col-sm-1 col-form-label" style="width: auto; padding-right: 0px;">姓名：</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" placeholder="姓名" v-model="name">
            </div>
            <label class="col-sm-1 col-form-label" style="width: auto; padding-right: 0px;">部门：</label>
            <div class="col-sm-3">
                <select class="form-select" v-model="deptId">
                    <option selected value="0">...</option>
                    <option v-for="(item, index) in departments" :key="index" :value="item.deptId">{{ item.deptName }}</option>
                </select>
            </div>
            <div class="col-sm-3">
                <button type="button" class="btn btn-primary" @click="searchEmployee()">搜索</button>
            </div>
        </div>
        <div class="mb-3">
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="addBtn()">新增员工</button>
        </div>
        <!-- 表格 -->
        <div class="table-responsive text-center">
            <table class="table table-hover table-bordered mb-3">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">姓名</th>
                        <th scope="col">性别</th>
                        <th scope="col">出生日期</th>
                        <th scope="col">部门</th>
                        <th scope="col">薪水</th>
                        <th scope="col">雇佣日期</th>
                        <th scope="col">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(item, index) in employees" :key="index">
                        <th scope="row">{{ item.empId }}</th>
                        <td>{{ item.name }}</td>
                        <td>{{ formatGender(item.gender) }}</td>
                        <td>{{ formatDate(item.birthDate) }}</td>
                        <td>{{ item.deptName }}</td>
                        <td>{{ item.salary }}</td>
                        <td>{{ formatDate(item.hireDate) }}</td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#exampleModal" @click="updateBtn(item.empId)">修改</button>
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
                            <label class="form-label">姓名</label>
                            <input type="text" class="form-control" v-model="employee.name" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">性别</label>
                            <select class="form-select" v-model="employee.gender" required>
                                <option selected value="0">男</option>
                                <option value="1">女</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">出生日期</label>
                            <input type="date" class="form-control" v-model="employee.birthDate" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">部门</label>
                            <select class="form-select" v-model="employee.deptId" required>
                                <option v-for="(item, index) in departments" :key="index" :value="item.deptId">{{ item.deptName }}</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">薪水</label>
                            <input type="number" step="any" class="form-control" v-model="employee.salary" required>
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
            modalTitle: "",
            employees: [],
            departments: [],
            employee: {
                empId: 0,
                name: "",
                gender: 0,
                deptId: 0,
                birthDate: "",
                salary: 0,
                hireDate: ""
            },
            name: "",
            deptId: 0
        }
    },
    created(){
        this.getEmployees();
        this.getDepartments();
    },
    methods: {
        // 获取员工
        getEmployees(){
            axios.get(new URL("api/employees", baseUrl)).then(res => {
                this.employees = res.data;
            });
        },
        // 获取部门
        getDepartments(){
            axios.get(new URL("api/departments", baseUrl)).then(res => {
                this.departments = res.data;
            });
        },
        // 搜索
        searchEmployee(){
            let name = this.name.trim();
            let deptId = parseInt(this.deptId);
            // 如果搜索条件存在
            if(name || deptId){
                let url = new URL("api/employees", baseUrl);
                if(name){ // 姓名搜索
                    url.searchParams.append('name', name);
                }
                if(deptId){ // 部门ID搜索
                    url.searchParams.append('deptId', deptId);
                }
                axios.get(url.href).then(res=>{
                    this.employees = res.data; // 获得员工信息
                });
            }else{
                this.getEmployees();
            }
        },
        // 添加
        addEmployee(){
            this.employee.hireDate = new Date();
            axios.post(new URL("api/employees", baseUrl), this.employee);
        },
        // 更新
        updateEmployee(){
            axios.put(new URL("api/employees/" + this.employee.empId, baseUrl), this.employee);
        },
        // 删除
        deleteEmployee(item){
            if(confirm('确定删除员工"' + item.name + '"?')){
                axios.delete(new URL("api/employees/" + item.empId, baseUrl)).then(res=> { this.getEmployees() });
            }
        },
        // 新增按钮事件
        addBtn(){
            this.modalTitle = "新增员工";
            this.employee = {
                empId: 0,
                name: "",
                gender: 0,
                deptId: 0,
                birthDate: "",
                salary: 0,
                hireDate: ""
            }
        },
        // 更新按钮事件
        updateBtn(id){
            this.modalTitle = "修改员工";
            axios.get(new URL("api/employees/" + id, baseUrl)).then(res => {
                res.data.birthDate = this.formatDate(res.data.birthDate); // type=date 绑定需要 YYYY-MM-DD 格式
                this.employee = res.data;
            });
        },
        // 保存按钮事件
        saveBtn(){
            // 防止全空格输入
            this.employee.name = this.employee.name.trim();
            if(this.employee.empId){
                this.updateEmployee();
            }else{
                this.addEmployee();
            }
        },
        // 格式化性别
        formatGender(gender){
            if(gender){
                return "女";
            }else{
                return "男";
            }
        },
        // 格式化日期
        formatDate(date){
            return date.slice(0, 10);
        }
    }
}
</script>
