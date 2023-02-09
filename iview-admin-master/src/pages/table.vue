<template>
  <Card>
   <Card>
     <Input>111</Input>
   </Card>

    <Table border :columns="columns" :data="data">
      <template #name="{ row }">
        <strong>{{ row.name }}</strong>
      </template>
      <template #action="{ row, index }">
        <Button type="primary" size="small" style="margin-right: 5px" @click="show(index)">View</Button>
        <Button type="error" size="small" @click="remove(index)">Delete</Button>
      </template>
    </Table>
    <Page :total="100"></Page>
  </Card>
</template>
<script>
    import {getUserList} from "@/api/user";

    export default {
      mounted() {
        getUserList().then(res => {
          this.data = res.data.data
          // console.log(this.data)
        })
      },
      data () {
            return {
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
                    "title": "手机号",
                    "key": "phoneNumber"
                  },
                    {
                        title: 'Action',
                        slot: 'action',
                        width: 150,
                        align: 'center'
                    }
                ],
                data: []
            }
        },
        methods: {


            show (index) {
                this.$Modal.info({
                    title: 'User Info',
                    content: `Name：${this.data[index].name}<br>Age：${this.data[index].age}<br>Address：${this.data[index].address}`
                })
            },
            remove (index) {
                this.data.splice(index, 1);
            }
        }
    }
</script>
