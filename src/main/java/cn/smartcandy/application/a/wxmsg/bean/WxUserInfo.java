package cn.smartcandy.application.a.wxmsg.bean;

import java.util.List;

/**
 * 项目名称：zmjema
 * 类名称：WxUserInfo
 * 类描述：微信用户类
 * 创建人：zhoushuyi
 * 创建时间：2016-12-12 上午12:56
 * 修改人：
 * 修改时间：
 * 修改备注：
 * Company:zmjema & HLS (C) 2016
 * @version 1.0
 */
public class WxUserInfo {
	// 用户标识
    private String openId;
    // 用户昵称
    private String nickname;
    // 性别（1是男性，2是女性，0是未知）
    private int sex;
    // 国家
    private String country;
    // 省份
    private String province;
    // 城市
    private String city;
    // 用户头像链接
    private String headImgUrl;
    // 用户特权信息
    private List<String> privilegeList;
   
    private String unionid;

    public String getOpenId() {
        return openId;
    }

    public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public List<String> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<String> privilegeList) {
        this.privilegeList = privilegeList;
    }

	@Override
	public String toString() {
		return "WxUserInfo [openId=" + openId + ", nickname=" + nickname + ", sex=" + sex + ", country=" + country
				+ ", province=" + province + ", city=" + city + ", headImgUrl=" + headImgUrl + ", privilegeList="
				+ privilegeList + ", unionid=" + unionid + "]";
	}
    
    
}
