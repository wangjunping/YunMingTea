package com.imcore.yunmingtea.ui;

public class Commodity {
	public long id;
   public long data;
   public long categoryId;
   public String ProductName;
   public String altName;
   public long  status;
   public String shortName;
   public String shortDesc;
   public String longDesc;
   public String updatedTime;
   public String createdTime;
   public String imageUrl;
   public long price;
   public long saleTotal;
   public long favotieTotal;
   
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public long getData() {
	return data;
}
public void setData(long data) {
	this.data = data;
}
public long getCategoryId() {
	return categoryId;
}
public void setCategoryId(long categoryId) {
	this.categoryId = categoryId;
}
public String getProductName() {
	return ProductName;
}
public void setProductName(String productName) {
	ProductName = productName;
}
public String getAltName() {
	return altName;
}
public void setAltName(String altName) {
	this.altName = altName;
}
public long getStatus() {
	return status;
}
public void setStatus(long status) {
	this.status = status;
}
public String getShortName() {
	return shortName;
}
public void setShortName(String shortName) {
	this.shortName = shortName;
}
public String getShortDesc() {
	return shortDesc;
}
public void setShortDesc(String shortDesc) {
	this.shortDesc = shortDesc;
}
public String getLongDesc() {
	return longDesc;
}
public void setLongDesc(String longDesc) {
	this.longDesc = longDesc;
}
public String getUpdatedTime() {
	return updatedTime;
}
public void setUpdatedTime(String updatedTime) {
	this.updatedTime = updatedTime;
}
public String getCreatedTime() {
	return createdTime;
}
public void setCreatedTime(String createdTime) {
	this.createdTime = createdTime;
}
public String getImageUrl() {
	return imageUrl;
}
public void setImageUrl(String imageUrl) {
	imageUrl = imageUrl;
}
public long getPrice() {
	return price;
}
public void setPrice(long price) {
	this.price = price;
}
public long getSaleTotal() {
	return saleTotal;
}
public void setSaleTotal(long saleTotal) {
	this.saleTotal = saleTotal;
}
public long getFavotieTotal() {
	return favotieTotal;
}
public void setFavotieTotal(long favotieTotal) {
	this.favotieTotal = favotieTotal;
}


    
}
