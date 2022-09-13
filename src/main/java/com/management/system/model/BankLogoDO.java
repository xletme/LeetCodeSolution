package com.management.system.model;

public class BankLogoDO {
    private Long sysno;

    private String bankclscode;

    private String bankName;

    private String logo;

    private String bankBackgroundImage;

    public Long getSysno() {
        return sysno;
    }

    public void setSysno(Long sysno) {
        this.sysno = sysno;
    }

    public String getBankclscode() {
        return bankclscode;
    }

    public void setBankclscode(String bankclscode) {
        this.bankclscode = bankclscode == null ? null : bankclscode.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getBankBackgroundImage() {
        return bankBackgroundImage;
    }

    public void setBankBackgroundImage(String bankBackgroundImage) {
        this.bankBackgroundImage = bankBackgroundImage == null ? null : bankBackgroundImage.trim();
    }
}