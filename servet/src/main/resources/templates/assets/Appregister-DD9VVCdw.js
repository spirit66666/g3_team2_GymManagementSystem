import{_ as u,E as p,h as a,o as c,y as h,e,a6 as l,a7 as n,b as g,w as _,aa as f,d as w,p as v,n as U}from"./index-W_Z73pLO.js";/* empty css                */const b={data(){return{registerUsername:"",registerPassword:"",registerphone:"",registeremail:"",receiveMsg:!1}},computed:{name:{get(){return this.fields[0].model},set(s){this.fields[0].model=s}},email:{get(){return this.fields[1].model},set(s){this.fields[1].model=s}},password:{get(){return this.fields[2].model},set(s){this.fields[2].model=s}}},methods:{registerUser(){this.$http.post("/postuser",{userName:this.registerUsername,passWord:this.registerPassword,mobilePhone:this.registerphone,email:this.registeremail}).then(s=>{console.log(s),p({message:a("p",{style:"line-height: 1; font-size: 14px"},[a("span",null,"用户"),a("i",{style:"color: teal"},this.registerUsername,"已经注册成功")])}),setTimeout(()=>{this.$router.push("/login")},400)}).catch(s=>{console.log(s),p({message:"注册失败，请检查输入信息",type:"error"})})}}},i=s=>(v("data-v-16676ab7"),s=s(),U(),s),y={class:"container",id:"Application"},x={class:"container"},V=i(()=>e("h1",{class:"title"},"创建你的账号",-1)),k={class:"inputContainer"},P={class:"field"},C=i(()=>e("label",{for:"username"},"用户名",-1)),E={class:"field"},I=i(()=>e("label",{for:"username"},"密码",-1)),N={class:"field"},B=i(()=>e("label",{for:"username"},"手机号",-1)),M={class:"field"},S=i(()=>e("label",{for:"username"},"邮箱",-1));function T(s,t,A,z,o,d){const m=f;return c(),h("div",y,[e("div",x,[V,e("div",k,[e("div",P,[C,l(e("input",{class:"input",type:"text",placeholder:"请输入用户名",id:"username","onUpdate:modelValue":t[0]||(t[0]=r=>o.registerUsername=r)},null,512),[[n,o.registerUsername]])]),e("div",E,[I,l(e("input",{class:"input",type:"password",placeholder:"请输入密码",id:"password","onUpdate:modelValue":t[1]||(t[1]=r=>o.registerPassword=r)},null,512),[[n,o.registerPassword]])]),e("div",N,[B,l(e("input",{class:"input",type:"password",placeholder:"请输入手机",id:"password","onUpdate:modelValue":t[2]||(t[2]=r=>o.registerphone=r)},null,512),[[n,o.registerphone]])]),e("div",M,[S,l(e("input",{class:"input",type:"password",placeholder:"请输入邮箱",id:"password","onUpdate:modelValue":t[3]||(t[3]=r=>o.registeremail=r)},null,512),[[n,o.registeremail]])]),g(m,{type:"primary",onClick:t[4]||(t[4]=r=>s.$router.push("/admin-register"))},{default:_(()=>[w("管理员注册")]),_:1})]),e("button",{onClick:t[5]||(t[5]=(...r)=>d.registerUser&&d.registerUser(...r)),class:"btn"},"创建账号")])])}const W=u(b,[["render",T],["__scopeId","data-v-16676ab7"]]);export{W as default};
