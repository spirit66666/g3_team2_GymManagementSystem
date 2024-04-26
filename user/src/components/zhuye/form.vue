<template>
  <div>
    <el-form ref="form" :model="form" label-width="100px">
        <el-form-item label="用户名">
            <el-input v-model="form.username"></el-input>


        </el-form-item>
        <el-form-item label="密码">
            <el-input type="password" v-model="form.password"></el-input>


        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm">提交</el-button>
        </el-form-item>
    </el-form>
  </div>
</template>


<script>
export default {
  name: "Form", // 组件名称
  data() {
    return {
      form: {
        username: "",
        password: "",


      },

    };
  },
  methods: {
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          console.log("submitForm", this.form);
          // 表单验证通过，可以提交表单数据
        } else {
          console.log("error submitForm");
          return false;
        }
      });
    },
  },
};
</script>