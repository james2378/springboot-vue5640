<template>
<div :style='{"width":"100%","padding":"30px 10% 40px","margin":"0px auto","position":"relative","background":"none"}'>
    <el-button :style='{"border":"0","cursor":"pointer","padding":"0 10px","margin":"0 10px 20px 0","color":"#fff","borderRadius":"6px","background":"#000","width":"auto","lineHeight":"36px","fontSize":"14px","height":"36px"}' type="warning" size="mini" @click="backClick" class="el-icon-back">返回</el-button>
    <div class="section-title" :style='{"padding":"30px 0 0","margin":"40px auto","color":"#000","textAlign":"center","background":"url(http://codegen.caihongy.cn/20240303/4a00266e357e47d5856d30e525467334.png) no-repeat center center / 100% 100%","width":"100%","fontSize":"24px","fontWeight":"600","height":"92px"}'>我的订单</div>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="未支付" name="未支付"></el-tab-pane>
      <el-tab-pane label="已支付" name="已支付"></el-tab-pane>
      <el-tab-pane label="已发货" name="已发货"></el-tab-pane>
      <el-tab-pane label="已完成" name="已完成"></el-tab-pane>
      <el-tab-pane label="已退款" name="已退款"></el-tab-pane>
      <el-tab-pane label="已取消" name="已取消"></el-tab-pane>
    </el-tabs>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="订单编号" prop="orderid"></el-table-column>
      <el-table-column label="水果" align="center" width="200px">
        <template slot-scope="scope">
          <div class="shangpin">
            <el-image style="width: 100px; height: 100px" :src="baseUrl + scope.row.picture" fit="fill"></el-image>
            <span style="margin-left: 10px" >{{ scope.row.goodname }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="价格">
        <template slot-scope="scope">
          <span v-if="(scope.row.type == 1 || scope.row.type == 3)&&scope.row.price>0"><span :style='{"fontSize":"12px"}'>￥</span>{{ scope.row.price }}</span>
          <span v-if="scope.row.type == 2&&scope.row.price>0">{{ scope.row.price }}积分</span>
        </template>
      </el-table-column>
      <el-table-column label="数量" prop="buynumber"></el-table-column>
      <el-table-column label="总价">
        <template slot-scope="scope">
          <span v-if="(scope.row.type == 1 || scope.row.type == 3)&&scope.row.total>0"><span :style='{"fontSize":"12px"}'>￥</span>{{ scope.row.total }}</span>
          <span v-if="scope.row.type == 2&&scope.row.total>0">{{ scope.row.total }}积分</span>
        </template>
      </el-table-column>
      <el-table-column label="地址" prop="address"></el-table-column>
      <el-table-column label="电话" prop="tel"></el-table-column>
      <el-table-column label="收货人" prop="consignee"></el-table-column>
      <el-table-column label="下单时间" prop="addtime"></el-table-column>
      <el-table-column label="备注" prop="remark"></el-table-column>
      <el-table-column label="退货审核" prop="sfsh">
		<template slot-scope="scope">
		  <el-tag type="success" v-if="scope.row.sfsh=='是'">已通过</el-tag>
		  <el-tag type="warning" v-if="scope.row.sfsh=='待审核'">待审核</el-tag>
		  <el-tag type="danger" v-if="scope.row.sfsh=='否'">未通过</el-tag>
		</template>
	  </el-table-column>
      <el-table-column label="审核回复" prop="shhf"></el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button v-show="activeName == '未支付'" type="success" :style='{"margin":"2px auto",}' size="mini" @click="pay(scope.row)">支付</el-button>
          <el-button v-show="activeName == '未支付'" type="danger" :style='{"margin":"2px auto",}' size="mini" @click="cancel(scope.row)">取消</el-button>
          <el-button v-show="activeName == '已支付'" type="danger" :style='{"margin":"2px auto",}' size="mini" @click="refund(scope.row)">{{scope.row.total>0?'退款':'退订'}}</el-button>
          <el-button v-if="scope.row.logistics" type="warning" :style='{"margin":"2px auto",}' size="mini" @click="logistics(scope.row)">物流</el-button>
          <el-button v-if="activeName == '已完成'&&scope.row.sfsh==''" type="danger" :style='{"margin":"2px auto",}' size="mini" @click="returnGood(scope.row)">退货</el-button>
          <el-button v-show="activeName == '已完成'" type="primary" :style='{"margin":"2px auto",}' size="mini" @click="toDetail(scope.row)">评价</el-button>
          <el-button v-show="activeName == '已发货'" type="success" :style='{"margin":"2px auto",}' size="mini" @click="confirm(scope.row)">确认收货</el-button>
        </template>
      </el-table-column>
    </el-table>
    
	<el-pagination
	  background
	  id="pagination" class="pagination"
	  :pager-count="7"
	  :page-size="pageSize"
	  :page-sizes="pageSizes"
	  prev-text="上一页"
	  next-text="下一页"
	  :hide-on-single-page="false"
	  :layout='["prev","pager","next"].join()'
	  :total="total"
	  :style='{"padding":"5px 10%","margin":"20px auto","color":"#333","textAlign":"center","width":"100%","clear":"both","lineHeight":"40px","fontWeight":"500","height":"40px","order":"50"}'
	  @current-change="curChange"
	  @prev-click="prevClick"
	  @next-click="nextClick"
	></el-pagination>

    <el-dialog title="物流信息" :visible.sync="logisticsVisible" width="30%">
        <div v-html="logisticsText"></div>
    </el-dialog>
	
</div>
</template>

<script>
  import config from '@/config/config'
  export default {
    data() {
      return {
		layouts: '',
        baseUrl: config.baseUrl,
        activeName: '未支付',
        tableData: [],
        total: 1,
        pageSize: 10,pageSizes: [10,20,30,50],
        totalPage: 1,
        session: {},
        userTableName: localStorage.getItem('UserTableName'),
        logisticsVisible: false,
        logisticsText: ''
      }
    },
    created() {
      this.getSession();
      this.getMyOrderList(1);
    },
    methods: {
      backClick() {
          this.$router.push('/index/center')
      },
      getSession() {
        this.$http.get(this.userTableName+'/session', {emulateJSON: true}).then(res => {
          if (res.data.code == 0) {
            this.session = res.data.data;
          }
        });
      },
      handleClick(tab, event) {
        tab.activeName = event.target.outerText;
        this.getMyOrderList(1);
      },
      toDetail(row) {
          this.$router.push({path: '/index/'+row.tablename+'Detail', query: {id: row.goodid}});
      },
      handleAction(index, row) {
        if (index == '已支付' && this.session.money < row.total) {
          this.$message.error('余额不足，请先充值');
          return;
        }

        row.status = index;
        this.$http.post('orders/update', row).then(res => {
          if (res.data.code == 0) {
            if (index == '已支付') this.session.money -= row.total;
            if (index == '已退款' && row.type == 1) this.session.money += row.total;
            if (index == '已退款' && row.type == 2) this.session.jifen += row.total;
            if (index == '已支付' || index == '已退款') {
              this.session.money = this.session.money.toFixed(2);
              this.$http.post(this.userTableName+'/update', this.session).then(res => {});
            }
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.getMyOrderList(1);
              }
            });
          }
        });
      },
        // 退款
        refund(item) {
            this.$confirm(`${item.total>0?'确定退款吗？退款金额将返回账户中。':'是否退订？'}`, "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
				
                // 获取水果详情信息
                this.$http.get(item.tablename+'/info/'+item.goodid, {}).then(res => {
                    // 水果信息
                    let good = res.data.data;
                    // 用户当前用户信息
                    let table = localStorage.getItem("UserTableName");
                    this.$http.get(table+'/session', {}).then(res => {
                        this.user = res.data.data;
                        if(item.type==1||item.type==3) {
                            // 如果该水果存在积分
                            if (good.jf) {
                                this.user.jf = (Number(this.user.jf) - Number(item.total)).toFixed(2);
                            }
                            this.user.money = (Number(this.user.money) + Number(item.total)).toFixed(2);
                            // 更新用户余额
                            this.$http.post(table+'/update', this.user).then(res => {
                                item.status = '已退款'
								localStorage.setItem('sessionForm',JSON.stringify(this.user))
                                this.$http.post(`orders/update`, item).then(res => {
                                    if(item.tablename == `shangpinxinxi`){
                                        // 加回库存数量
                                        good.alllimittimes = good.alllimittimes + item.buynumber;
                                        this.$http.post(`shangpinxinxi/update`, good).then(res => {
                                        });
                                    }
                                    this.$message({
                                      message: `${item.total>0?'退款成功':'退订成功'}`,
                                      type: 'success',
                                      duration: 1500,
                                      onClose: () => {
                                        this.getMyOrderList(1);
                                      }
                                    });
                                });
                            });
                        } else if(item.type==2) {
                            this.user.jf = Number(this.user.jf) + Number(item.total);
                            // 更新用户余额
                            this.$http.post(table+'/update', this.user).then(res => {
                                item.status = '已退款'
								localStorage.setItem('sessionForm',JSON.stringify(this.user))
                                this.$http.post(`orders/update`, item).then(res => {
                                    if(item.tablename == `shangpinxinxi`){
                                        // 加回库存数量
                                        good.alllimittimes = good.alllimittimes + item.buynumber;
                                        this.$http.post(`shangpinxinxi/update`, good).then(res => {
                                        });
                                    }
                                    this.$message({
                                      message: `${item.total>0?'退款成功':'退订成功'}`,
                                      type: 'success',
                                      duration: 1500,
                                      onClose: () => {
                                        this.getMyOrderList(1);
                                      }
                                    });
                                });
                            });

                        }
                    });
                });
            });
        },
        //物流
        logistics(row) {
            this.logisticsText = row.logistics;
            this.logisticsVisible = true;
        },
        //退货
        returnGood(item) {
            this.$confirm(`确定退货吗？审核通过后退款金额将返回账户中。`, "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
				item.sfsh = '待审核'
				this.$http.post(`orders/update`, item).then(res => {
					this.$message({
						message: '申请成功',
						type: 'success',
						duration: 1500,
						onClose: () => {
							this.getMyOrderList(1);
						}
					});
				})
            });
        },

        // 支付
        pay(item) {
            // 获取水果详情信息
            this.$http.get(item.tablename+'/info/'+item.goodid, {}).then(res => {
                // 水果信息
                let data = res.data.data;
                // 用户当前用户信息
                let table = localStorage.getItem("UserTableName");
                this.$http.get(table+'/session', {}).then(res => {
                    this.user = res.data.data;
                    // 判断余额是否充足
                    if (Number(this.user.money) < Number(item.total)) {
                        this.$message({
                            message: '余额不足，请先充值',
                            type: 'error',
                            duration: 1500
                        });
                        return
                    }
                    // 如果该水果存在积分
                    if (data.jf) {
                        this.user.jf = Number((Number(this.user.jf) + Number(item.total)).toFixed(2));
                    }
                    this.user.money = Number((Number(this.user.money) - Number(item.total)).toFixed(2));
                    // 更新用户余额
                    this.$http.post(table+'/update', this.user).then(res => {
                        item.status = '已支付'
						localStorage.setItem('sessionForm',JSON.stringify(this.user))
                        this.$http.post(`orders/update`, item).then(res => {
                            this.$message({
                              message: '支付成功',
                              type: 'success',
                              duration: 1500,
                              onClose: () => {
                                this.getMyOrderList(1);
                              }
                            });
                        });
                    });
                });
            });
        },
      confirm(item){
          this.$confirm(`是否确定收货？`, "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
          }).then(() => {
              item.status = '已完成'
              this.$http.post(`orders/update`, item).then(res => {
                  this.$message({
                    message: '确认收货成功',
                    type: 'success',
                    duration: 1500,
                    onClose: () => {
                      this.getMyOrderList(1);
                    }
                  });
              });
          });
      },
		cancel(item){
			this.$confirm(`是否取消订单？`, "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
				type: "warning"
			}).then(() => {
				item.status = '已取消'
				this.$http.post(`orders/update`, item).then(res => {
                    // 获取水果详情信息
                    this.$http.get(item.tablename+'/info/'+item.goodid, {}).then(res => {
                        // 水果信息
                        let good = res.data.data;
                        if(item.tablename == `shangpinxinxi`){
                            // 加回库存数量
                            good.alllimittimes = good.alllimittimes + item.buynumber;
                            this.$http.post(`shangpinxinxi/update`, good).then(res => {
                            });
                        }
						this.$message({
							message: '订单取消成功',
							type: 'success',
							duration: 1500,
							onClose: () => {
								this.getMyOrderList(1);
							}
						});
					});
				});
			});
		},
		getMyOrderList(page) {
			this.$http.get('orders/list', {params: {page, limit: this.pageSize,sort:'addtime',order:'desc', userid: localStorage.getItem('frontUserid'), status: this.activeName}}).then(res => {
				if (res.data.code == 0) {
					this.tableData = res.data.data.list;
					this.total = res.data.data.total;
					this.pageSize = res.data.data.pageSize;this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
					this.totalPage = res.data.data.totalPage;
				}
			});
		},
		curChange(page) {
			this.getMyOrderList(page);
		},
		prevClick(page) {
			this.getMyOrderList(page);
		},
		nextClick(page) {
			this.getMyOrderList(page);
		}
	}
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.section {
	  width: 1000px;
	  margin: 0 auto;
	}

	.shangpin {
	  display: flex;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__total {
		margin: 0 10px 0 0;
		color: #666;
		font-weight: 400;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .btn-prev {
		border: none;
		border-radius: 2px;
		padding: 0 6px;
		margin: 0 5px;
		color: #666;
		background: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		min-width: 35px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .btn-next {
		border: none;
		border-radius: 2px;
		padding: 0 6px;
		margin: 0 5px;
		color: #666;
		background: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		min-width: 35px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .btn-prev:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0 6px;
		margin: 0 5px;
		color: #C0C4CC;
		background: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .btn-next:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0 6px;
		margin: 0 5px;
		color: #C0C4CC;
		background: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pager {
		padding: 0;
		margin: 0;
		display: inline-block;
		vertical-align: top;
	}
	
	#pagination.el-pagination /deep/ .el-pager .number {
		cursor: pointer;
		padding: 0 4px;
		margin: 0 5px;
		color: #666;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		border-radius: 2px;
		background: #fff;
		text-align: center;
		min-width: 30px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pager .number:hover {
		cursor: pointer;
		padding: 0 4px;
		margin: 0 5px;
		color: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		border-radius: 2px;
		background: #f7c420;
		text-align: center;
		min-width: 30px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pager .number.active {
		cursor: default;
		padding: 0 4px;
		margin: 0 5px;
		color: #FFF;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		border-radius: 2px;
		background: #f7c420;
		text-align: center;
		min-width: 30px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__sizes {
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__sizes .el-input {
		margin: 0 5px;
		width: 100px;
		position: relative;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 25px 0 8px;
		color: #606266;
		display: inline-block;
		font-size: 13px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
		top: 0;
		position: absolute;
		right: 0;
		height: 100%;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
		cursor: pointer;
		color: #C0C4CC;
		width: 25px;
		font-size: 14px;
		line-height: 28px;
		text-align: center;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__jump {
		margin: 0 0 0 24px;
		color: #606266;
		display: inline-block;
		vertical-align: top;
		font-size: 13px;
		line-height: 28px;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__jump .el-input {
		border-radius: 3px;
		padding: 0 2px;
		margin: 0 2px;
		display: inline-block;
		width: 50px;
		font-size: 14px;
		line-height: 18px;
		position: relative;
		text-align: center;
		height: 28px;
	}
	
	#pagination.el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 3px;
		color: #606266;
		display: inline-block;
		font-size: 14px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
</style>
