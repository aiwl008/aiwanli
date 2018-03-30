package com.aiwl.pms.entity;

import com.aiwl.common.entity.Query;

public class GoodsDetail extends Query{
    private Integer goodsid;

    private String goodsname;

    private Double goodsprice;

    private String showimage;

    private String goodstag;

    private String goodsdetailsimage1;

    private String goodsdetailsimage2;

    private String goodsdetailsimage3;

    private String goodsclass;

    private Integer goodsstylenum;

    private Integer goodsparentid;

    private String goodsdescribe;

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Double getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(Double goodsprice) {
        this.goodsprice = goodsprice;
    }

    public String getShowimage() {
        return showimage;
    }

    public void setShowimage(String showimage) {
        this.showimage = showimage == null ? null : showimage.trim();
    }

    public String getGoodstag() {
        return goodstag;
    }

    public void setGoodstag(String goodstag) {
        this.goodstag = goodstag == null ? null : goodstag.trim();
    }

    public String getGoodsdetailsimage1() {
        return goodsdetailsimage1;
    }

    public void setGoodsdetailsimage1(String goodsdetailsimage1) {
        this.goodsdetailsimage1 = goodsdetailsimage1 == null ? null : goodsdetailsimage1.trim();
    }

    public String getGoodsdetailsimage2() {
        return goodsdetailsimage2;
    }

    public void setGoodsdetailsimage2(String goodsdetailsimage2) {
        this.goodsdetailsimage2 = goodsdetailsimage2 == null ? null : goodsdetailsimage2.trim();
    }

    public String getGoodsdetailsimage3() {
        return goodsdetailsimage3;
    }

    public void setGoodsdetailsimage3(String goodsdetailsimage3) {
        this.goodsdetailsimage3 = goodsdetailsimage3 == null ? null : goodsdetailsimage3.trim();
    }

    public String getGoodsclass() {
        return goodsclass;
    }

    public void setGoodsclass(String goodsclass) {
        this.goodsclass = goodsclass == null ? null : goodsclass.trim();
    }

    public Integer getGoodsstylenum() {
        return goodsstylenum;
    }

    public void setGoodsstylenum(Integer goodsstylenum) {
        this.goodsstylenum = goodsstylenum;
    }

    public Integer getGoodsparentid() {
        return goodsparentid;
    }

    public void setGoodsparentid(Integer goodsparentid) {
        this.goodsparentid = goodsparentid;
    }

    public String getGoodsdescribe() {
        return goodsdescribe;
    }

    public void setGoodsdescribe(String goodsdescribe) {
        this.goodsdescribe = goodsdescribe == null ? null : goodsdescribe.trim();
    }
}