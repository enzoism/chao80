package cn.smartcandy.common.common;

import java.util.HashMap;
import java.util.Map;

/**
  * 项目名称：a-source  
  * 类名称：BS  
  * 类描述：业务代码
  * 创建人：ShiLei  
  * 创建时间：2014-9-22 上午1:15:33  
  * 修改人：ShiLei  
  * 修改时间：2014-9-22 上午1:15:33  
  * 修改备注：
  * Company:Annjema & HLS (C) 2016
  * @version 1.0
 */
public enum BS {

	/**收银管理*/
	BS_A_SALESMNG(10), 
	BS_A_SALESMNG_PRODUCT(101), 
	BS_A_SALESMNG_SERVICE(102), 
	BS_A_SALESMNG_PACKAGE(103), 
	
	/**  冲红	 */
	BS_A_SALESMNG_REFUND(120), 
	/**借款管理*/
	BS_A_LOANSMNG(11), 
	/**日结账管理*/
	BS_A_BALANCESMNG(12), 
	/**系统月结*/
	BS_A_MONTHLY_SETTLEMENT(13), 
	
	/**第三方支付*/
	BS_C_PAY(14),

	/**顾客管理*/
	BS_A_CUSTOMERSMNG(20), 
	BS_A_CUSTOMERADD(21), 
	BS_A_CUSTOMERMODIFY(22), 
	BS_A_CUSTOMERDEL(23), 
	BS_A_CUSTOMERPWDMODIFY(24), 
	BS_A_CUSTOMERPWDRESET(25), 
	/** 客户绑定微信*/
	BS_A_CUSTOMERBINDWX(25),
	
	/**产品/服务/套餐管理*/
	BS_A_PRODUCTSMNG(40), 
	
	/**库存管理*/
	BS_A_STOCKSMNG(30), 
	/**归还*/
	BS_A_STOCKSRETURN(32),
	
	/**领用*/
	BS_A_STOCKSRECEIVED(33),	
	/**出库 */
	BS_A_STOCKSOUTBOUND(34),
	
	/**不动产管理*/
	BS_AMS_IMMOVABLEMNG(180),
	/**不动产登记*/
	BS_AMS_IMMOVABLE_REGISTER(181),
	/**不动产使用*/
	BS_AMS_IMMOVABLE_USE(182),
	/**不动产使用*/
	BS_AMS_IMMOVABLE_RETURN(183),

	/**卡信息管理*/
	BS_A_CARDSMNG(50), 
	/**新增卡种*/
	BS_A_CARDSADD(51), 
	/**修改卡种*/
	BS_A_CARDSMODIFY(52), 
	/**删除卡种*/
	BS_A_CARDSDEL(53), 
	/**制卡*/
	BS_A_CARDSMANUFACTURE(54), 
	/**发卡*/
	BS_A_CARDDISTUIBUTE(55), 
	/**改卡*/
	BS_A_CARDCHANGE(56), 
	/**发卡*/
	BS_A_CHANGEVALUE(57),
	/**收银售卡*/
	BS_A_CARDSALE(58), 
	/**卡片绑定*/
	BS_A_CARDBIND(59), 
	/**发卡绑定*/
	BS_A_DISTRIBUTEDCARDBIND(591), 
	
	
	
	/**新增券种*/
	BS_A_COUPONTYPEADD(110), 	
	/**新增批次*/
	BS_A_COUPONBATCHADD(111), 
	/**修改券种*/
	BS_A_COUPONTYPEMODIFY(112), 
	/**指定批次制作优惠券*/
	BS_A_COUPONBATCHMAKE(113), 
	/**删除券种*/
	BS_A_COUPONTYPEDELETE(114), 
	/**指定批次补制优惠券*/
	BS_A_COUPONADDCOUPONNUM(115),
	/**指定券种发放优惠券*/
	BS_A_COUPONRELEASECOUPON(116),
	/**指定券种删除优惠券*/
	BS_A_COUPONDELETECOUPON(117),
	/**指定批次修改优惠券*/
	BS_A_COUPONMODIFYCOUPON(118),
	/**优惠券领取*/
	BS_A_COUPONRECEIVE(119),
	
	
	/**优惠促销管理*/
	BS_A_PROMOTIOSNMNG(60), 
	/**增加优惠促销*/
	BS_A_PROMOTIOSNADD(61), 
	/**修改优惠促销*/
	BS_A_PROMOTIOSNMODIFY(62), 
	/**删除优惠促销*/
	BS_A_PROMOTIOSNDEL(63), 

	/**员工管理*/
	BS_A_EMPLOYEESMNG(70), 
	/**新增员工信息*/
	BS_A_EMPLOYEESADD(71), 
	/**修改员工信息*/
	BS_A_EMPLOYEESMODIFY(72), 
	/**删除员工信息*/
	BS_A_EMPLOYEESDEL(73), 
	/**员工绑定微信	 */
	BS_A_EMPLOYEESBINDWX(74), 
	
	/**客户预约*/
	BS_BOOKING(80), 
	
	/**地址簿管理*/
	BS_ADDRBOOK(81), 
	
	/**系统管理*/
	BS_SYS(90), 
	/**用户登录*/
	BS_C_LOGIN(91),
	
	/**房间管理*/
	BS_C_ROOMMNG(100),
	
	/**核销卡*/
	BS_A_VARIFICODE(120),
	/**核销积分兑换的服务*/
	BS_A_VARIFISERVFORPOINT(121);
	
	public final int SEQUENCE;

	BS(int sequence) {
		this.SEQUENCE = sequence;
	}

	private static Map<Integer, BS> codeLookup = new HashMap<Integer, BS>();

	static {
		for (BS type : BS.values()) {
			codeLookup.put(type.SEQUENCE, type);
		}
	}

	public static BS forCode(int sequence) {
		return codeLookup.get(sequence);
	}

	@Override
	public String toString() {
		return String.valueOf(this.SEQUENCE);
	}
}
