package com.abcft.business.demo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.abcft.common.base.BaseEntity;

/**
 * 
 * @author inning
 * @Date 2015-7-22 下午5:13:40
 *
 */
@Entity
@Table(name = "UserInfo", schema = "MISC", uniqueConstraints = { @UniqueConstraint(columnNames = { "UserId" }) })
public class UserInfo extends BaseEntity implements Serializable {

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Long getSerialVersionUID() {
		return null;
	}
	
	

	private String cause;

	private String price;

	private String userId;

	private String userName;
	
	private String fullName;
	
	private String phoneNumber;

	private String salt;

	private String fuiouUserName;

	private Date registerDate;

	private Date lastLoginDate;

	private String CanUseAmnout;

	private String id; // 投资id

	private String email;

	private Integer batchIndex;// 10为集团用户
	
	private String reference; // 推荐人userId

	private Date updateTime; // 集团用户加入名单时间

	private Integer status; // 集团用户审核状态

	

	private BigDecimal additional; // 加息利率

	@Column(name = "additional")
	public BigDecimal getAdditional() {
		return additional;
	}

	public void setAdditional(BigDecimal additional) {
		this.additional = additional;
	}

	@Column(name = "reference")
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "batchIndex")
	public Integer getBatchIndex() {
		return batchIndex;
	}

	public void setBatchIndex(Integer batchIndex) {
		this.batchIndex = batchIndex;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCanUseAmnout(String canUseAmnout) {
		CanUseAmnout = canUseAmnout;
	}

	@Column(name = "registerDate")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Column(name = "lastLoginDate")
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Column(name = "fuiouUserName")
	public String getFuiouUserName() {
		return fuiouUserName;
	}

	public void setFuiouUserName(String fuiouUserName) {
		this.fuiouUserName = fuiouUserName;
	}

	@Column(name = "salt")
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "Password")
	private String passWord;

	@Column(name = "BankCardNo1")
	private String bankCardNo1;

	@Column(name = "RealNameCount")
	private Integer realNameCount;

	@Column(name = "idCardCount")
	private Integer idCardCount;

	@Column(name = "FuiouValid")
	private Integer fuiouValid;

	private String idNumber;

	

	private String birthday; // 生日

	private Integer userLevel; // 用户等级

	private String levelName;

	private Integer sex; // 性别

	private BigDecimal Integeregral; // 用户积分

	@Column(name = "Integeregral")
	public BigDecimal getIntegeregral() {
		return Integeregral;
	}

	public void setIntegeregral(BigDecimal Integeregral) {
		this.Integeregral = Integeregral;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "levelName")
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	@Column(name = "userLevel")
	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	@Column(name = "birthday")
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "UserStatus")
	private String userStatus;

	private Double freezeAmount;

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	private Double canUseAmnout;

	@Column(name = "BankCode1")
	private String bankCode1; // 开户银行类型

	@Column(name = "BankName1")
	private String bankName1; // 开户银行类型名字

	@Column(name = "DealPws")
	private String dealPws;

	@Column(name = "busi_tp_desc")
	private String busi_tp_desc;

	@Column(name = "Iss_ins_cd")
	private String iss_ins_cd;

	@Column(name = "BankCode")
	private String bankCode;

	@Column(name = "Bank_nm")
	private String bank_nm;

	@Column(name = "Category")
	private Integer category;

	@Column(name = "BankProvinceId1")
	private String bankProvinceId1; // 开户银行区域省

	@Column(name = "BankCityId1")
	// 开户银行区域市
	private String bankCityId1;

	@Column(name = "BankAddress1")
	private String bankAddress1;

	@Column(name = "txn_rsp_cd")
	private String txn_rsp_cd;

	@Column(name = "busi_tp")
	private String busi_tp;

	@Column(name = "txn_amt")
	private String txn_amt;

	@Column(name = "UserType")
	private Integer userType;

	/** default constructor */
	public UserInfo() {
	}

	public UserInfo(String userId, Double canUseAmnout, Double freezeAmount, String fullName) {
		this.userId = userId;
		this.canUseAmnout = canUseAmnout;
		this.freezeAmount = freezeAmount;
		this.fullName = fullName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getTxn_amt() {
		return txn_amt;
	}

	public void setTxn_amt(String txn_amt) {
		this.txn_amt = txn_amt;
	}

	public String getBusi_tp() {
		return busi_tp;
	}

	public void setBusi_tp(String busi_tp) {
		this.busi_tp = busi_tp;
	}

	public String getTxn_rsp_cd() {
		return txn_rsp_cd;
	}

	public void setTxn_rsp_cd(String txn_rsp_cd) {
		this.txn_rsp_cd = txn_rsp_cd;
	}

	public Integer getCategory() {
		return category;
	}

	public String getBankProvinceId1() {
		return bankProvinceId1;
	}

	public void setBankProvinceId1(String bankProvinceId1) {
		this.bankProvinceId1 = bankProvinceId1;
	}

	public String getBankCityId1() {
		return bankCityId1;
	}

	public void setBankCityId1(String bankCityId1) {
		this.bankCityId1 = bankCityId1;
	}

	public String getBankAddress1() {
		return bankAddress1;
	}

	public void setBankAddress1(String bankAddress1) {
		this.bankAddress1 = bankAddress1;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	@Column(name = "PhoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBank_nm() {
		return bank_nm;
	}

	public void setBank_nm(String bank_nm) {
		this.bank_nm = bank_nm;
	}

	public String getIss_ins_cd() {
		return iss_ins_cd;
	}

	public void setIss_ins_cd(String iss_ins_cd) {
		this.iss_ins_cd = iss_ins_cd;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBusi_tp_desc() {
		return busi_tp_desc;
	}

	public void setBusi_tp_desc(String busi_tp_desc) {
		this.busi_tp_desc = busi_tp_desc;
	}

	@Column(name = "IDNumber")
	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@Column(name = "FullName")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDealPws() {
		return dealPws;
	}

	public void setDealPws(String dealPws) {
		this.dealPws = dealPws;
	}

	@Column(name = "userStatus")
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Column(name = "FreezeAmount")
	public Double getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(Double freezeAmount) {
		this.freezeAmount = freezeAmount;
	}

	@Column(name = "CanUseAmnout")
	public Double getCanUseAmnout() {
		return canUseAmnout;
	}

	public void setCanUseAmnout(Double canUseAmnout) {
		this.canUseAmnout = canUseAmnout;
	}

	@Column(name = "bankCode1")
	public String getBankCode1() {
		return bankCode1;
	}

	public void setBankCode1(String bankCode1) {
		this.bankCode1 = bankCode1;
	}

	@Column(name = "bankName1")
	public String getBankName1() {
		return bankName1;
	}

	public void setBankName1(String bankName1) {
		this.bankName1 = bankName1;
	}

	public Integer getIdCardCount() {
		return idCardCount;
	}

	public void setIdCardCount(Integer idCardCount) {
		this.idCardCount = idCardCount;
	}

	@Column(name = "fuiouValid")
	public Integer getFuiouValid() {
		return fuiouValid;
	}

	public void setFuiouValid(Integer fuiouValid) {
		this.fuiouValid = fuiouValid;
	}

	@Column(name = "bankCardNo1")
	public String getBankCardNo1() {
		return bankCardNo1;
	}

	public void setBankCardNo1(String bankCardNo1) {
		this.bankCardNo1 = bankCardNo1;
	}

	public Integer getRealNameCount() {
		return realNameCount;
	}

	public void setRealNameCount(Integer realNameCount) {
		this.realNameCount = realNameCount;
	}

	@Id
	@Column(name = "UserId")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "UserName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
