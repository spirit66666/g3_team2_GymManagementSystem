<template>
    <div>
        <el-container class="content-row">
            <div class="input-tip">
                名称:
            </div>
            <div class="input-field">
                <el-input v-model="queryParams.name"></el-input>
            </div>
        </el-container>
        <el-container class="content-row">
            <div class="input-tip">
                简介:
            </div>
            <div class="input-field">
                <el-input type="textarea" :rows="3" v-model="queryParams.desc"></el-input>
            </div>
        </el-container>
        <el-container class="content-row">
            <div class="input-tip">
                封面:
            </div>
                <el-upload
                :auto-upload="false"
                :limit="1"
                list-type="picture-card">
                    <i class="el-icon-plus"></i>
                </el-upload>
        </el-container>
        <el-container class="content-row">
            <div class="input-tip">
                列表图片:
            </div>
            <el-upload
            :auto-upload="false"
            :limit="5"
            list-type="picture-card">
                <i class="el-icon-plus"></i>
            </el-upload>
        </el-container>
        <el-container class="content-row">
            <div class="input-tip">
                日期:
            </div>
            <div class="input-field">
                <el-date-picker
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                v-model="queryParams.timeRange">
                </el-date-picker>
            </div>
        </el-container>
        <el-container class="content-row">



        </el-container>
        <el-container class="content-row">
            <el-button type="success" plain @click="submit">提交</el-button>
            <div style="margin-left:40px"></div>
            <el-button type="warning" plain @click="cancel">取消</el-button>
        </el-container>
    </div>
</template>

<script>
export default {
    data() {
        return {
            queryParams:{
                name:"",
                desc:"",
                timeRange:"",
                category:0
            }
        }
    },
    methods: {
        cancel() {
            this.$router.go(-1)
        },
        submit() {
            this.$message({
                type:'success',
                message:'设置商品基本属性：' + JSON.stringify(this.queryParams)
            })
        },
        addCategory() {
            this.$router.push({name:'GoodCategory'})
        }
    }
}
</script>