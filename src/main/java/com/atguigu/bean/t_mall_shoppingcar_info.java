package com.atguigu.bean;

public class t_mall_shoppingcar_info {
    private Integer id;

    private String skuMch;

    private Integer skuJg;

    private String yhXm;

    private String yhNch;

    private String yhDz;

    private String shpMch;

    private String shpTp;

    private Integer skuKc;

    private String skuShx;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkuMch() {
        return skuMch;
    }

    public void setSkuMch(String skuMch) {
        this.skuMch = skuMch == null ? null : skuMch.trim();
    }

    public Integer getSkuJg() {
        return skuJg;
    }

    public void setSkuJg(Integer skuJg) {
        this.skuJg = skuJg;
    }

    public String getYhXm() {
        return yhXm;
    }

    public void setYhXm(String yhXm) {
        this.yhXm = yhXm == null ? null : yhXm.trim();
    }

    public String getYhNch() {
        return yhNch;
    }

    public void setYhNch(String yhNch) {
        this.yhNch = yhNch == null ? null : yhNch.trim();
    }

    public String getYhDz() {
        return yhDz;
    }

    public void setYhDz(String yhDz) {
        this.yhDz = yhDz == null ? null : yhDz.trim();
    }

    public String getShpMch() {
        return shpMch;
    }

    public void setShpMch(String shpMch) {
        this.shpMch = shpMch == null ? null : shpMch.trim();
    }

    public String getShpTp() {
        return shpTp;
    }

    public void setShpTp(String shpTp) {
        this.shpTp = shpTp == null ? null : shpTp.trim();
    }

    public Integer getSkuKc() {
        return skuKc;
    }

    public void setSkuKc(Integer skuKc) {
        this.skuKc = skuKc;
    }

    public String getSkuShx() {
        return skuShx;
    }

    public void setSkuShx(String skuShx) {
        this.skuShx = skuShx == null ? null : skuShx.trim();
    }
}