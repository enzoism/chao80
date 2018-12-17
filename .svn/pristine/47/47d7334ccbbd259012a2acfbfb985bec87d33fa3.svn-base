package cn.smartcandy.application.a.wxmsg.bean;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

/**
  * 项目名称：zmjema
  * 类名称：OAuthToken
  * 类描述：微信平台用户凭证对象，全局唯一
  * 创建人：lgbzc-surface
  * 创建时间：2017年7月17日 下午7:47:58
  * 修改人：
  * 修改时间：
  * 修改备注：
  * Company:北京闻壹信息有限公司 (C) 2017
  * @version 1.0
  */
public class OAuthToken implements Serializable {

	private static final long serialVersionUID = -1737189777093048783L;
	
	private String access_token;
    private int expires_in = 7200;
    private long exprexpiredTime;
    private long create_time;

    public OAuthToken() {
    }

    public OAuthToken(String accessToken, int expiresIn) {
        this.access_token = accessToken;
        setExpires_in(expiresIn);
    }

    public OAuthToken(String accessToken, int expiresIn, long createTime) {
        this.access_token = accessToken;
        setExpires_in(expiresIn, createTime);
    }


    /**
     * 通过微信公众平台返回JSON对象创建凭证对象
     *
     * <p>
     * 正常情况下，微信会返回下述JSON数据包给公众号：
     * {"access_token":"ACCESS_TOKEN","expires_in":7200}</p>
     *
     * @param jsonObj JSON数据包
     * @throws WeixinException
     */
    public OAuthToken(JSONObject jsonObj) {
        this.access_token = jsonObj.getString("access_token");
        //根据当前时间的毫秒数+获取的秒数计算过期时间
        int expiresIn = jsonObj.getInt("expires_in");
        if (jsonObj.containsKey("create_time")) {
            //获取创建时间
            long createTime = jsonObj.getLong("create_time");
            if (createTime > 0) {
                //设定指定日期
                setExpires_in(expiresIn, createTime);
                return;
            }
        }
        setExpires_in(expiresIn);
    }

    /**
     * 获取用户凭证
     *
     * @return 用户凭证
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     * 设置用户凭证
     *
     * @param access_token 用户凭证
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * 判断用户凭证是否过期
     *
     * @return 过期返回 true,否则返回false
     */
    public boolean isExprexpired() {
        Date now = new Date();
        long nowLong = now.getTime();
        return nowLong >= exprexpiredTime;
    }

    /**
     * 获取 凭证有效时间，单位：秒
     *
     * @return 凭证有效时间，单位：秒
     */
    public int getExpires_in() {
        return expires_in;
    }

    /**
     * 设置 凭证有效时间，单位：秒
     *
     * <p>
     * 为了与微信服务器保存同步，误差设置为提前1分钟，即：将创建时间提早1分钟
     * </p>
     *
     * @param expires_in 凭证有效时间，单位：秒
     */
    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
        //获取当前时间
        Date now = new Date();
        //获取当前时间毫秒数 - 1 分钟，即提前1分钟过期
        create_time = now.getTime() - 60000;
        //设置下次过期时间 = 当前时间 + (凭证有效时间(秒) * 1000)
        this.exprexpiredTime = create_time + (expires_in * 1000);
    }

    /**
     * 设置 凭证有效时间，单位：秒
     *
     * <p>
     * 为了与微信服务器保存同步，误差设置为提前1分钟，即：将创建时间提早1分钟
     * </p>
     *
     * @param expires_in 凭证有效时间，单位：秒
     * @param createTime 凭证创建时间
     */
    public void setExpires_in(int expires_in, long createTime) {
        this.expires_in = expires_in;
        //获取当前时间毫秒数
        create_time = createTime - 60000;
        //设置下次过期时间 = 当前时间 + (凭证有效时间(秒) * 1000)
        this.exprexpiredTime = create_time + (expires_in * 1000);
    }

    /**
     * 获取 此次凭证创建时间 单位：毫秒数
     *
     * @return 创建时间 毫秒数
     */
    public long getCreate_time() {
        return this.create_time + 60000;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OAuthToken [access_token=" + access_token + ", expires_in=" + expires_in + ", exprexpiredTime="
				+ exprexpiredTime + ", create_time=" + create_time + "]";
	}    
}
