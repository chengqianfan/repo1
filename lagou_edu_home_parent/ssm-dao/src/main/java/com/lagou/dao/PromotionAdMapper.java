package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /*
        分页查询广告信息
     */
    public List<PromotionAd> findAllPromotionAdByPage();

    /*
        添加广告信息
    */
    public void savePromotionAd(PromotionAd promotionAd);

    /*
        修改广告信息
    */
    public void updatePromotionAd(PromotionAd promotionAd);
    /*
        回显广告信息
    */
    public PromotionAd findPromotionAdById(int id);


    /*
        广告动态上下线
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);


}
