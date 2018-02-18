package com.atguigu.bean;

public class t_mall_user_account {
    private Integer id;

    private String yhMch;//名称

    private String yhNch;//昵称

    private String yhMm;//密码

    private String yhXm;//姓名

    private String yhShjh;

    private String yhYx;

    private String yhTx;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYhMch() {
        return yhMch;
    }

    public void setYhMch(String yhMch) {
        this.yhMch = yhMch == null ? null : yhMch.trim();
    }

    public String getYhNch() {
        return yhNch;
    }

    public void setYhNch(String yhNch) {
        this.yhNch = yhNch == null ? null : yhNch.trim();
    }

    public String getYhMm() {
        return yhMm;
    }

    public void setYhMm(String yhMm) {
        this.yhMm = yhMm == null ? null : yhMm.trim();
    }

    public String getYhXm() {
        return yhXm;
    }

    public void setYhXm(String yhXm) {
        this.yhXm = yhXm == null ? null : yhXm.trim();
    }

    public String getYhShjh() {
        return yhShjh;
    }

    public void setYhShjh(String yhShjh) {
        this.yhShjh = yhShjh == null ? null : yhShjh.trim();
    }

    public String getYhYx() {
        return yhYx;
    }

    public void setYhYx(String yhYx) {
        this.yhYx = yhYx == null ? null : yhYx.trim();
    }

    public String getYhTx() {
        return yhTx;
    }

    public void setYhTx(String yhTx) {
        this.yhTx = yhTx == null ? null : yhTx.trim();
    }

	@Override
	public String toString() {
		return "t_mall_user_account [id=" + id + ", yhMch=" + yhMch + ", yhNch=" + yhNch + ", yhMm=" + yhMm + ", yhXm="
				+ yhXm + ", yhShjh=" + yhShjh + ", yhYx=" + yhYx + ", yhTx=" + yhTx + "]";
	}
    
}