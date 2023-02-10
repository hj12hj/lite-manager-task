<template>
  <div>
    <Card style="margin-bottom: 15px">
      <Form label-position="left" :label-width="60" v-model="searchForm">
        <Row :gutter="16">
          <Col span="6">
            <FormItem label="用户名：" prop="userName">
              <Input v-model="searchForm.userName" placeholder="请输入用户名"></Input>
            </FormItem>
          </Col>
          <Col span="8" offect="1">
            <Button type="primary" @click="search">查询</Button>
          </Col>

        </Row>
      </Form>


    </Card>

    <Card>
      <Row style="margin-bottom: 15px">
        <Col span="12">
          <h2>用户列表</h2>
        </Col>
        <Col span="12" style="text-align: right">
          <Button type="primary" @click="addUser">添加用户</Button>
        </Col>
      </Row>
      <Table border :columns="columns" :data="data">
        <template #name="{ row }">
          <strong>{{ row.name }}</strong>
        </template>
        <template #action="{ row, index }">
          <Button type="primary" size="small" style="margin-right: 5px" @click="update(index)">修改</Button>
          <Button type="error" size="small" @click="remove(index)">删除</Button>
        </template>
      </Table>
      <div class="page" style="margin-top: 15px">
        <Page @on-prev="prev" :total="total" size="small" @on-change="pageChange" @on-page-size-change="pageSizeChange" show-elevator
              show-sizer/>
      </div>
    </Card>

    <!--    修改-->
    <Modal
      v-model="updateVisable"
      closable
      title="修改用户">

      <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">

        <FormItem label="用户名" prop="userName">
          <Input v-model="formValidate.userName" placeholder="请输入用户名"></Input>
        </FormItem>

        <FormItem label="微信" prop="wxName">
          <Input v-model="formValidate.wxName" placeholder="请输入微信"></Input>
        </FormItem>

        <FormItem label="手机号" prop="phoneNumber">
          <Input v-model="formValidate.phoneNumber" placeholder="请输入手机号"></Input>
        </FormItem>


        <div class="page">
          <Button type="primary" @click="handleSubmit('formValidate')">提交</Button>
          <Button @click="handleReset('formValidate')" style="margin-left: 8px">返回</Button>
        </div>


      </Form>

      <div slot="footer"></div>
    </Modal>
    <!--增加-->
    <Modal
      v-model="addVisable" closable title="增加用户">
      <Form ref="addformValidate" :model="addformValidate" :rules="addruleValidate" :label-width="80">

        <FormItem label="用户名" prop="userName">
          <Input v-model="addformValidate.userName" placeholder="请输入用户名"></Input>
        </FormItem>

        <FormItem label="微信" prop="wxName">
          <Input v-model="addformValidate.wxName" placeholder="请输入微信"></Input>
        </FormItem>

        <FormItem label="手机号" prop="phoneNumber">
          <Input v-model="addformValidate.phoneNumber" placeholder="请输入手机号"></Input>
        </FormItem>

        <div class="page">
          <Button type="primary" @click="handleAddSubmit('addformValidate')">提交</Button>
          <Button @click="handleAddReset('addformValidate')" style="margin-left: 8px">返回</Button>
        </div>

      </Form>

      <div slot="footer"></div>
    </Modal>


  </div>

</template>
<script>
import {deleteByid, getUserList, updateByid,addUser} from "@/api/user";

export default {
  mounted() {
    getUserList({page: this.page, pageSize: this.pageSize}).then(res => {
      this.data = res.data.data.records
      this.total = res.data.data.total
    })
  },
  data() {
    return {
      addformValidate: {
        id: '',
        userName: '',
        wxName: '',
        phoneNumber: ''
      },
      addruleValidate: {
        userName: [
          {required: true, message: '用户名不能为空！！！', trigger: 'blur'}
        ],
        wxName: [
          {required: true, message: '微信名不能为空', trigger: 'blur'}
        ],
        phoneNumber: [
          {required: true, message: '手机号不能为空', trigger: 'blur'}
        ],
      },


      formValidate: {
        id: '',
        userName: '',
        wxName: '',
        phoneNumber: ''
      },
      ruleValidate: {
        userName: [
          {required: true, message: '用户名不能为空！！！', trigger: 'blur'}
        ],
        wxName: [
          {required: true, message: '微信名不能为空', trigger: 'blur'}
        ],
        phoneNumber: [
          {required: true, message: '手机号不能为空', trigger: 'blur'}
        ],
      },
      page: 1,
      pageSize: 10,
      total: 0,
      updateVisable: false,
      addVisable: false,
      searchForm: {
        userName: ''
      },
      columns: [
        {
          title: '用户名',
          key: 'userName'
        },
        {
          title: '微信',
          key: 'wxName'
        },
        {
          title: "手机号",
          key: "phoneNumber"
        },
        {
          title: '操作',
          slot: 'action',
          width: 150,
          align: 'center'
        }
      ],
      data: []
    }
  },
  methods: {
    prev(page) {
      console.log(page)
    },

    handleAddSubmit(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          addUser(this.addformValidate).then(res => {
            this.$Message.success('增加成功')
            this.addVisable = false
            getUserList({page: this.page, pageSize: this.pageSize}).then(res => {
              this.data = res.data.data.records
              this.total = res.data.data.total
            })
          }).catch(err => {
            this.$Message.error('增加失败')
          })
        } else {
          this.$Message.error('Fail!');

        }
      })
    },
    handleAddReset(name) {
      this.addVisable = false
      this.$refs[name].resetFields();
    },

    addUser() {
      this.addVisable = true
      this.$refs.addformValidate.resetFields();
    },
    handleSubmit(name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          updateByid(this.formValidate).then(res => {
            this.$Message.success('修改成功')
            this.updateVisable = false
            getUserList({page: this.page, pageSize: this.pageSize}).then(res => {
              this.data = res.data.data.records
              this.total = res.data.data.total
            })
          })
          handleReset(name)
        } else {
          this.$Message.error('Fail!');
          handleReset(name)
        }
      })
    },
    handleReset(name) {
      this.updateVisable = false
      this.$refs[name].resetFields();
    },

    pageSizeChange(pageSize) {
      this.pageSize = pageSize
      getUserList({...this.searchForm, page: this.page, pageSize: this.pageSize}).then(res => {
        this.data = res.data.data.records
        this.total = res.data.data.total
      })
    },

    pageChange(page) {
      this.page = page
      getUserList({...this.searchForm, page: this.page, pageSize: this.pageSize}).then(res => {
        this.data = res.data.data.records
        this.total = res.data.data.total
      })
    },
    search() {
      getUserList({...this.searchForm, page: this.page, pageSize: this.pageSize}).then(res => {
        this.data = res.data.data.records
        this.total = res.data.data.total
      })
    },
    update(index) {
      this.updateVisable = true
      this.formValidate.id = this.data[index].id
      this.formValidate.userName = this.data[index].userName
      this.formValidate.wxName = this.data[index].wxName
      this.formValidate.phoneNumber = this.data[index].phoneNumber

    },
    remove(index) {
      deleteByid(this.data[index].id).then(res => {
        this.$Message.success('删除成功')
        this.page = 1
        getUserList({...this.searchForm, page: this.page, pageSize: this.pageSize}).then(res => {
          this.data = res.data.data.records
          this.total = res.data.data.total
        })
      })
    }
  }
}
</script>

<style lang="less" scoped>
.page {
  display: flex;
  justify-content: flex-end;
}


</style>
