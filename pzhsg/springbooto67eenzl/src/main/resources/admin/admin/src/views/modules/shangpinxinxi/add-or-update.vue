<template>
	<div class="addEdit-block" :style='{"padding":"30px","fontSize":"14px","color":"#666","background":"none"}'>
		<el-form
			:style='{"border":"1px solid #bababa","padding":"40px 30px 20px","borderRadius":"8px","alignItems":"flex-start","flexWrap":"wrap","background":"rgba(255,255,255,1)","display":"flex","fontSize":"inherit"}'
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="150px"
		>
			<template >
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="水果名称" prop="shangpinmingcheng">
					<el-input v-model="ruleForm.shangpinmingcheng" placeholder="水果名称" clearable  :readonly="ro.shangpinmingcheng"></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' v-else class="input" label="水果名称" prop="shangpinmingcheng">
					<el-input v-model="ruleForm.shangpinmingcheng" placeholder="水果名称" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' class="select" v-if="type!='info'"  label="水果类型" prop="shangpinleixing">
					<el-select :disabled="ro.shangpinleixing" v-model="ruleForm.shangpinleixing" placeholder="请选择水果类型" >
						<el-option
							v-for="(item,index) in shangpinleixingOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' v-else class="input" label="水果类型" prop="shangpinleixing">
					<el-input v-model="ruleForm.shangpinleixing"
						placeholder="水果类型" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="品牌" prop="pinpai">
					<el-input v-model="ruleForm.pinpai" placeholder="品牌" clearable  :readonly="ro.pinpai"></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' v-else class="input" label="品牌" prop="pinpai">
					<el-input v-model="ruleForm.pinpai" placeholder="品牌" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="规格" prop="guige">
					<el-input v-model="ruleForm.guige" placeholder="规格" clearable  :readonly="ro.guige"></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' v-else class="input" label="规格" prop="guige">
					<el-input v-model="ruleForm.guige" placeholder="规格" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="生产厂家" prop="shengchanchangjia">
					<el-input v-model="ruleForm.shengchanchangjia" placeholder="生产厂家" clearable  :readonly="ro.shengchanchangjia"></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' v-else class="input" label="生产厂家" prop="shengchanchangjia">
					<el-input v-model="ruleForm.shengchanchangjia" placeholder="生产厂家" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' class="date" v-if="type!='info'" label="生产日期" prop="shengchanriqi">
					<el-date-picker
						format="yyyy 年 MM 月 dd 日"
						value-format="yyyy-MM-dd"
						v-model="ruleForm.shengchanriqi" 
						type="date"
						:readonly="ro.shengchanriqi"
						placeholder="生产日期"
					></el-date-picker> 
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' class="input" v-else-if="ruleForm.shengchanriqi" label="生产日期" prop="shengchanriqi">
					<el-input v-model="ruleForm.shengchanriqi" placeholder="生产日期" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' class="upload" v-if="type!='info' && !ro.shangpintupian" label="水果图片" prop="shangpintupian">
					<file-upload
						tip="点击上传水果图片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.shangpintupian?ruleForm.shangpintupian:''"
						@change="shangpintupianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' class="upload" v-else-if="ruleForm.shangpintupian" label="水果图片" prop="shangpintupian">
					<img v-if="ruleForm.shangpintupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.shangpintupian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.shangpintupian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="店铺名称" prop="dianpumingcheng">
					<el-input v-model="ruleForm.dianpumingcheng" placeholder="店铺名称" clearable  :readonly="ro.dianpumingcheng"></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' v-else class="input" label="店铺名称" prop="dianpumingcheng">
					<el-input v-model="ruleForm.dianpumingcheng" placeholder="店铺名称" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="单限" prop="onelimittimes">
					<el-input v-model.number="ruleForm.onelimittimes" placeholder="单限" clearable  :readonly="ro.onelimittimes"></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' v-else class="input" label="单限" prop="onelimittimes">
					<el-input v-model="ruleForm.onelimittimes" placeholder="单限" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="库存" prop="alllimittimes">
					<el-input v-model.number="ruleForm.alllimittimes" placeholder="库存" clearable  :readonly="ro.alllimittimes"></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' v-else class="input" label="库存" prop="alllimittimes">
					<el-input v-model="ruleForm.alllimittimes" placeholder="库存" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="价格" prop="price">
					<el-input-number v-model="ruleForm.price" placeholder="价格" :readonly="ro.price"></el-input-number>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' v-else class="input" label="价格" prop="price">
					<el-input v-model="ruleForm.price" placeholder="价格" readonly></el-input>
				</el-form-item>
			</template>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' v-if="type!='info'"  label="水果介绍" prop="shangpinjieshao">
					<editor 
						style="min-width: 200px; max-width: 600px;"
						v-model="ruleForm.shangpinjieshao" 
						class="editor" 
						action="file/upload">
					</editor>
				</el-form-item>
				<el-form-item :style='{"border":"1px solid #eee","padding":"20px 0","margin":"0 0 2px 0","color":"inherit","borderRadius":"0px","width":"100%","fontSize":"inherit"}' v-else-if="ruleForm.shangpinjieshao" label="水果介绍" prop="shangpinjieshao">
                    <span :style='{"fontSize":"14px","lineHeight":"40px","color":"inherit","fontWeight":"500","display":"inline-block"}' v-html="ruleForm.shangpinjieshao"></span>
                </el-form-item>
			<el-form-item :style='{"padding":"0","margin":"30px 0","alignItems":"center","textAlign":"center","display":"flex","width":"100%","perspective":"320px","-webkitPerspective":"320px","fontSize":"48px","justifyContent":"flex-start"}' class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-tijiao16" :style='{"margin":"0 2px","fontSize":"18px","color":"inherit","display":"none"}'></span>
					保存
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-quxiao09" :style='{"margin":"0 2px","fontSize":"18px","color":"inherit","display":"none"}'></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-fanhui01" :style='{"margin":"0 2px","fontSize":"18px","color":"inherit","display":"none"}'></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

  </div>
</template>
<script>
// 数字，邮件，手机，url，身份证校验
import { isNumber,isIntNumer,isEmail,isPhone, isMobile,isURL,checkIdCard } from "@/utils/validate";
export default {
	data() {
		let self = this
		var validateIdCard = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!checkIdCard(value)) {
				callback(new Error("请输入正确的身份证号码"));
			} else {
				callback();
			}
		};
		var validateUrl = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isURL(value)) {
				callback(new Error("请输入正确的URL地址"));
			} else {
				callback();
			}
		};
		var validateMobile = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isMobile(value)) {
				callback(new Error("请输入正确的手机号码"));
			} else {
				callback();
			}
		};
		var validatePhone = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isPhone(value)) {
				callback(new Error("请输入正确的电话号码"));
			} else {
				callback();
			}
		};
		var validateEmail = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isEmail(value)) {
				callback(new Error("请输入正确的邮箱地址"));
			} else {
				callback();
			}
		};
		var validateNumber = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isNumber(value)) {
				callback(new Error("请输入数字"));
			} else {
				callback();
			}
		};
		var validateIntNumber = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isIntNumer(value)) {
				callback(new Error("请输入整数"));
			} else {
				callback();
			}
		};
		return {
			id: '',
			type: '',
			
			
			ro:{
				shangpinmingcheng : false,
				shangpinleixing : false,
				pinpai : false,
				guige : false,
				shengchanchangjia : false,
				shengchanriqi : false,
				shangpinjieshao : false,
				shangpintupian : false,
				dianpumingcheng : false,
				onelimittimes : false,
				alllimittimes : false,
				thumbsupnum : false,
				crazilynum : false,
				clicktime : false,
				clicknum : false,
				discussnum : false,
				price : false,
				storeupnum : false,
			},
			
			
			ruleForm: {
				shangpinmingcheng: '',
				shangpinleixing: '',
				pinpai: '',
				guige: '',
				shengchanchangjia: '',
				shengchanriqi: '',
				shangpinjieshao: '',
				shangpintupian: '',
				dianpumingcheng: '',
				onelimittimes: '-1',
				alllimittimes: '-1',
				clicktime: '',
				price: '',
			},
		
			shangpinleixingOptions: [],

			
			rules: {
				shangpinmingcheng: [
				],
				shangpinleixing: [
				],
				pinpai: [
				],
				guige: [
				],
				shengchanchangjia: [
				],
				shengchanriqi: [
				],
				shangpinjieshao: [
				],
				shangpintupian: [
				],
				dianpumingcheng: [
				],
				onelimittimes: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				alllimittimes: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				thumbsupnum: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				crazilynum: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				clicktime: [
				],
				clicknum: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				discussnum: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				price: [
					{ required: true, message: '价格不能为空', trigger: 'blur' },
					{ validator: validateNumber, trigger: 'blur' },
				],
				storeupnum: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
			}
		};
	},
	props: ["parent"],
	computed: {



	},
    components: {
    },
	created() {
		this.ruleForm.shengchanriqi = this.getCurDate()
	},
	methods: {
		
		// 下载
		download(file){
			window.open(`${file}`)
		},
		// 初始化
		init(id,type) {
			if (id) {
				this.id = id;
				this.type = type;
			}
			if(this.type=='info'||this.type=='else'){
				this.info(id);
			}else if(this.type=='logistics'){
				this.logistics=false;
				this.info(id);
			}else if(this.type=='cross'){
				var obj = this.$storage.getObj('crossObj');
				for (var o in obj){
						if(o=='shangpinmingcheng'){
							this.ruleForm.shangpinmingcheng = obj[o];
							this.ro.shangpinmingcheng = true;
							continue;
						}
						if(o=='shangpinleixing'){
							this.ruleForm.shangpinleixing = obj[o];
							this.ro.shangpinleixing = true;
							continue;
						}
						if(o=='pinpai'){
							this.ruleForm.pinpai = obj[o];
							this.ro.pinpai = true;
							continue;
						}
						if(o=='guige'){
							this.ruleForm.guige = obj[o];
							this.ro.guige = true;
							continue;
						}
						if(o=='shengchanchangjia'){
							this.ruleForm.shengchanchangjia = obj[o];
							this.ro.shengchanchangjia = true;
							continue;
						}
						if(o=='shengchanriqi'){
							this.ruleForm.shengchanriqi = obj[o];
							this.ro.shengchanriqi = true;
							continue;
						}
						if(o=='shangpinjieshao'){
							this.ruleForm.shangpinjieshao = obj[o];
							this.ro.shangpinjieshao = true;
							continue;
						}
						if(o=='shangpintupian'){
							this.ruleForm.shangpintupian = obj[o];
							this.ro.shangpintupian = true;
							continue;
						}
						if(o=='dianpumingcheng'){
							this.ruleForm.dianpumingcheng = obj[o];
							this.ro.dianpumingcheng = true;
							continue;
						}
						if(o=='onelimittimes'){
							this.ruleForm.onelimittimes = obj[o];
							this.ro.onelimittimes = true;
							continue;
						}
						if(o=='alllimittimes'){
							this.ruleForm.alllimittimes = obj[o];
							this.ro.alllimittimes = true;
							continue;
						}
						if(o=='thumbsupnum'){
							this.ruleForm.thumbsupnum = obj[o];
							this.ro.thumbsupnum = true;
							continue;
						}
						if(o=='crazilynum'){
							this.ruleForm.crazilynum = obj[o];
							this.ro.crazilynum = true;
							continue;
						}
						if(o=='clicktime'){
							this.ruleForm.clicktime = obj[o];
							this.ro.clicktime = true;
							continue;
						}
						if(o=='clicknum'){
							this.ruleForm.clicknum = obj[o];
							this.ro.clicknum = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='price'){
							this.ruleForm.price = obj[o];
							this.ro.price = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
							continue;
						}
				}
				










				this.ruleForm.onelimittimes = Number('-1'); 
				this.ruleForm.alllimittimes = Number('-1'); 






			}
			
			// 获取用户信息
			this.$http({
				url: `${this.$storage.get('sessionTable')}/session`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					
					var json = data.data;
					if(((json.dianpumingcheng!=''&&json.dianpumingcheng) || json.dianpumingcheng==0) && this.$storage.get("role")!="管理员"){
						this.ruleForm.dianpumingcheng = json.dianpumingcheng
						this.ro.dianpumingcheng = true;
					}
				} else {
					this.$message.error(data.msg);
				}
			});
			
            this.$http({
				url: `option/shangpinleixing/shangpinleixing`,
				method: "get"
            }).then(({ data }) => {
				if (data && data.code === 0) {
					this.shangpinleixingOptions = data.data;
				} else {
					this.$message.error(data.msg);
				}
            });
			
		},
    // 多级联动参数

    info(id) {
      this.$http({
        url: `shangpinxinxi/info/${id}`,
        method: "get"
      }).then(({ data }) => {
        if (data && data.code === 0) {
        this.ruleForm = data.data;
        //解决前台上传图片后台不显示的问题
        let reg=new RegExp('../../../upload','g')//g代表全部
        this.ruleForm.shangpinjieshao = this.ruleForm.shangpinjieshao.replace(reg,'../../../springbooto67eenzl/upload');
        } else {
          this.$message.error(data.msg);
        }
      });
    },


    // 提交
    onSubmit() {








	if(this.ruleForm.shangpintupian!=null) {
		this.ruleForm.shangpintupian = this.ruleForm.shangpintupian.replace(new RegExp(this.$base.url,"g"),"");
	}











	  if(this.ruleForm.price<0){
		this.$message.error("价格不能输入负数");
		return
	  }
	  if(this.ruleForm.alllimittimes<0){
		this.$message.error("库存不能输入负数");
		return
	  }
	  if(this.ruleForm.onelimittimes<0){
		this.$message.error("单次购买不能输入负数");
		return
	  }
var objcross = this.$storage.getObj('crossObj');
      //更新跨表属性
       var crossuserid;
       var crossrefid;
       var crossoptnum;
       if(this.type=='cross'){
                var statusColumnName = this.$storage.get('statusColumnName');
                var statusColumnValue = this.$storage.get('statusColumnValue');
                if(statusColumnName!='') {
                        var obj = this.$storage.getObj('crossObj');
                       if(statusColumnName && !statusColumnName.startsWith("[")) {
                               for (var o in obj){
                                 if(o==statusColumnName){
                                   obj[o] = statusColumnValue;
                                 }
                               }
                               var table = this.$storage.get('crossTable');
                             this.$http({
                                 url: `${table}/update`,
                                 method: "post",
                                 data: obj
                               }).then(({ data }) => {});
                       } else {
                               crossuserid=this.$storage.get('userid');
                               crossrefid=obj['id'];
                               crossoptnum=this.$storage.get('statusColumnName');
                               crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
                        }
                }
        }
		this.$refs["ruleForm"].validate(valid => {
			if (valid) {
				if(crossrefid && crossuserid) {
					this.ruleForm.crossuserid = crossuserid;
					this.ruleForm.crossrefid = crossrefid;
					let params = { 
						page: 1, 
						limit: 10, 
						crossuserid:this.ruleForm.crossuserid,
						crossrefid:this.ruleForm.crossrefid,
					} 
				this.$http({ 
					url: "shangpinxinxi/page", 
					method: "get", 
					params: params 
				}).then(({ 
					data 
				}) => { 
					if (data && data.code === 0) { 
						if(data.data.total>=crossoptnum) {
							this.$message.error(this.$storage.get('tips'));
							return false;
						} else {
							this.$http({
								url: `shangpinxinxi/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.shangpinxinxiCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
								} else {
									this.$message.error(data.msg);
								}
							});

						}
					} else { 
				} 
			});
		} else {
			this.$http({
				url: `shangpinxinxi/${!this.ruleForm.id ? "save" : "update"}`,
				method: "post",
			   data: this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "操作成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.parent.showFlag = true;
							this.parent.addOrUpdateFlag = false;
							this.parent.shangpinxinxiCrossAddOrUpdateFlag = false;
							this.parent.search();
							this.parent.contentStyleChange();
						}
					});
				} else {
					this.$message.error(data.msg);
			   }
			});
		 }
         }
       });
    },
    // 获取uuid
    getUUID () {
      return new Date().getTime();
    },
    // 返回
    back() {
      this.parent.showFlag = true;
      this.parent.addOrUpdateFlag = false;
      this.parent.shangpinxinxiCrossAddOrUpdateFlag = false;
      this.parent.contentStyleChange();
    },
    shangpintupianUploadChange(fileUrls) {
	    this.ruleForm.shangpintupian = fileUrls;
    },
  }
};
</script>
<style lang="scss" scoped>
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
	  	  padding: 0 10px 0 0;
	  	  color: #333;
	  	  font-weight: 500;
	  	  display: inline-block;
	  	  width: 150px;
	  	  font-size: inherit;
	  	  line-height: 40px;
	  	  text-align: right;
	  	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
	  margin-left: 150px;
	}
	
	.add-update-preview .el-input /deep/ .el-input__inner {
	  	  padding: 0 12px;
	  	  color: inherit;
	  	  font-size: 14px;
	  	  border-color: #ddd;
	  	  border-radius: 0px;
	  	  box-shadow: 0 0 0px rgba(64, 158, 255, .5);
	  	  outline: none;
	  	  background: none;
	  	  width: auto;
	  	  border-width: 0 0 1px;
	  	  border-style: solid;
	  	  min-width: 350px;
	  	  height: 39px;
	  	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
	  	  padding: 0 12px;
	  	  color: inherit;
	  	  font-size: 14px;
	  	  border-color: #ddd;
	  	  border-radius: 0px;
	  	  box-shadow: 0 0 0px rgba(64, 158, 255, .5);
	  	  outline: none;
	  	  background: none;
	  	  width: auto;
	  	  border-width: 0 0 1px;
	  	  border-style: solid;
	  	  min-width: 350px;
	  	  height: 39px;
	  	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	
	.add-update-preview .el-select /deep/ .el-input__inner {
	  	  border-radius: 0px;
	  	  padding: 0 10px;
	  	  box-shadow: 0 0 0px rgba(64, 158, 255, .5);
	  	  outline: none;
	  	  color: inherit;
	  	  background: none;
	  	  width: auto;
	  	  font-size: 14px;
	  	  border-color: #ddd;
	  	  border-width: 0 0 1px;
	  	  border-style: solid;
	  	  height: 39px;
	  	}
	
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
	  	  border-radius: 0px;
	  	  padding: 0 10px 0 30px;
	  	  box-shadow: 0 0 0px rgba(64, 158, 255, .5);
	  	  outline: none;
	  	  color: inherit;
	  	  background: none;
	  	  width: auto;
	  	  font-size: 14px;
	  	  border-color: #ddd;
	  	  border-width: 0 0 1px;
	  	  border-style: solid;
	  	  height: 39px;
	  	}
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
	  	  cursor: pointer;
	  	  color: #bbb;
	  	  object-fit: cover;
	  	  font-size: 24px;
	  	  border-color: #ddd;
	  	  line-height: 60px;
	  	  border-radius: 0px;
	  	  background: none;
	  	  width: 140px;
	  	  border-width: 0 0 1px;
	  	  border-style: solid;
	  	  text-align: center;
	  	  height: 60px;
	  	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
	  	  cursor: pointer;
	  	  color: #bbb;
	  	  object-fit: cover;
	  	  font-size: 24px;
	  	  border-color: #ddd;
	  	  line-height: 60px;
	  	  border-radius: 0px;
	  	  background: none;
	  	  width: 140px;
	  	  border-width: 0 0 1px;
	  	  border-style: solid;
	  	  text-align: center;
	  	  height: 60px;
	  	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
	  	  cursor: pointer;
	  	  color: #bbb;
	  	  object-fit: cover;
	  	  font-size: 24px;
	  	  border-color: #ddd;
	  	  line-height: 60px;
	  	  border-radius: 0px;
	  	  background: none;
	  	  width: 140px;
	  	  border-width: 0 0 1px;
	  	  border-style: solid;
	  	  text-align: center;
	  	  height: 60px;
	  	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
	  	  padding: 12px;
	  	  color: inherit;
	  	  font-size: 14px;
	  	  border-color: #ddd;
	  	  border-radius: 0px;
	  	  box-shadow: 0 0 0px rgba(64, 158, 255, .5);
	  	  outline: none;
	  	  background: none;
	  	  width: auto;
	  	  border-width: 0 0 1px;
	  	  border-style: solid;
	  	  min-width: 50%;
	  	  height: auto;
	  	}
	
	.add-update-preview .btn .btn1 {
				border: 1px solid #a768f3;
				cursor: pointer;
				padding: 0 20px;
				margin: 0px 4px;
				color: #a768f3;
				display: inline-block;
				font-size: 14px;
				line-height: 24px;
				border-radius: 4px;
				outline: none;
				background: none;
				width: auto;
				height: 36px;
			}
	
	.add-update-preview .btn .btn1:hover {
				transform: scale(0.9) translate3d(0px, -10px, 0px);
				opacity: 0.8;
			}
	
	.add-update-preview .btn .btn2 {
				border: 0px solid rgba(126, 96, 16, .2);
				cursor: pointer;
				border-radius: 4px;
				padding: 0 20px;
				margin: 0px 4px;
				outline: none;
				color: #fff;
				background: #34bfa3;
				width: auto;
				font-size: 14px;
				line-height: 24px;
				height: 36px;
			}
	
	.add-update-preview .btn .btn2:hover {
				transform: scale(0.9) translate3d(0px, -10px, 0px);
				opacity: 0.8;
			}
	
	.add-update-preview .btn .btn3 {
				border: 1px solid #0260ad;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 20px;
				margin: 0px 4px;
				outline: none;
				color: #0260ad;
				background: none;
				width: auto;
				font-size: 14px;
				line-height: 24px;
				height: 36px;
			}
	
	.add-update-preview .btn .btn3:hover {
				transform: scale(0.9) translate3d(0px, -10px, 0px);
				opacity: 0.8;
			}
	
	.add-update-preview .btn .btn4 {
				border: 0px solid rgba(126, 96, 16, .2);
				cursor: pointer;
				border-radius: 4px;
				padding: 0 20px;
				margin: 0px 4px;
				outline: none;
				color: #fff;
				background: #ff518a;
				width: auto;
				font-size: 14px;
				line-height: 24px;
				height: 36px;
			}
	
	.add-update-preview .btn .btn4:hover {
				transform: scale(0.9) translate3d(0px, -10px, 0px);
				opacity: 0.8;
			}
	
	.add-update-preview .btn .btn5 {
				border: 1px solid #6c757d;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 20px;
				margin: 4px;
				outline: none;
				color: #6c757d;
				background: none;
				width: auto;
				font-size: 14px;
				line-height: 24px;
				height: 36px;
			}
	
	.add-update-preview .btn .btn5:hover {
				transform: scale(0.9) translate3d(0px, -10px, 0px);
				opacity: 0.8;
			}
</style>
