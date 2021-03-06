package cn.smartcandy.common.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import cn.smartcandy.framework.biz.workdate.WorkDate;
import cn.smartcandy.framework.core.db.mybatis.MyBatisDBAccess;
import cn.smartcandy.framework.core.exception.BusinessException;

/**
  * 项目名称：a-source  
  * 类名称：GenBatchNO  
  * 类描述：产生申请编号
  * 创建人：lgbzc  
  * 创建时间：Apr 16, 2013 5:42:49 PM  
  * 修改人：lgbzc  
  * 修改时间：Apr 16, 2013 5:42:49 PM  
  * 修改备注：
  * Company:Annjema & HLS (C) 2016
  * @version 1.0
 */
public class GenBatchNO {

	public static GenBatchNO getInstance() {
		return new GenBatchNO();
	}

	/**
	 * 方法描述：获取一个新的申请编号
	 *
	 * @param bizCode 业务代码
	 * @param dbAccess 数据库连接实例
	 * @return 16为申请编号（8位当前工作日期+2位业务代码+6位流水号）
	 * @throws DBAcessException
	 */
	public synchronized String getNextBatchNO(BS bizCode, MyBatisDBAccess dbAccess) {
		String batchNO = null;
		String batchNO_seq = dbAccess.getSeqID("BatchNO");
		batchNO = WorkDate.getSystemDate() + bizCode.SEQUENCE + batchNO_seq.substring(4);
		return batchNO;
	}

	/**
	 * 方法描述：验证申请编号位数、业务类型是否有效
	 *
	 * @param batch_no 申请编号
	 * @return 业务类型
	 */
	public BS validateBatchNO(String batch_no) {
		batch_no = StringUtils.trimToEmpty(batch_no);
		if (16 != batch_no.length() && 20 != batch_no.length()) {
			// 抛出异常：申请编号位数不正确
			throw new BusinessException("EC10443");
		}
		// 截取业务代码
		String bizCode = batch_no.substring(8, 10);
		BS cbs_sequence = BS.forCode(NumberUtils.toInt(bizCode));
		if (null == cbs_sequence) {
			// 抛出异常：不存在该类型业务
			throw new BusinessException("EC10444", bizCode);
		}
		return cbs_sequence;
	}

	/**
	 * 方法描述：生成子流水号
	 * @param batchno 申请编号
	 * @param index 子序号
	 * @return 20位流水号
	 */
	public synchronized String getSubWaterNO(String batchno, int index) {
		return batchno + String.valueOf(10001 + index).substring(1);
	}
}
