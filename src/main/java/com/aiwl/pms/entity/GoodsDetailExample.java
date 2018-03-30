package com.aiwl.pms.entity;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andGoodsidIsNull() {
            addCriterion("goodsId is null");
            return (Criteria) this;
        }

        public Criteria andGoodsidIsNotNull() {
            addCriterion("goodsId is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsidEqualTo(Integer value) {
            addCriterion("goodsId =", value, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidNotEqualTo(Integer value) {
            addCriterion("goodsId <>", value, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidGreaterThan(Integer value) {
            addCriterion("goodsId >", value, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidGreaterThanOrEqualTo(Integer value) {
            addCriterion("goodsId >=", value, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidLessThan(Integer value) {
            addCriterion("goodsId <", value, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidLessThanOrEqualTo(Integer value) {
            addCriterion("goodsId <=", value, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidIn(List<Integer> values) {
            addCriterion("goodsId in", values, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidNotIn(List<Integer> values) {
            addCriterion("goodsId not in", values, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidBetween(Integer value1, Integer value2) {
            addCriterion("goodsId between", value1, value2, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsidNotBetween(Integer value1, Integer value2) {
            addCriterion("goodsId not between", value1, value2, "goodsid");
            return (Criteria) this;
        }

        public Criteria andGoodsnameIsNull() {
            addCriterion("goodsName is null");
            return (Criteria) this;
        }

        public Criteria andGoodsnameIsNotNull() {
            addCriterion("goodsName is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsnameEqualTo(String value) {
            addCriterion("goodsName =", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotEqualTo(String value) {
            addCriterion("goodsName <>", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameGreaterThan(String value) {
            addCriterion("goodsName >", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameGreaterThanOrEqualTo(String value) {
            addCriterion("goodsName >=", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameLessThan(String value) {
            addCriterion("goodsName <", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameLessThanOrEqualTo(String value) {
            addCriterion("goodsName <=", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameLike(String value) {
            addCriterion("goodsName like", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotLike(String value) {
            addCriterion("goodsName not like", value, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameIn(List<String> values) {
            addCriterion("goodsName in", values, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotIn(List<String> values) {
            addCriterion("goodsName not in", values, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameBetween(String value1, String value2) {
            addCriterion("goodsName between", value1, value2, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodsnameNotBetween(String value1, String value2) {
            addCriterion("goodsName not between", value1, value2, "goodsname");
            return (Criteria) this;
        }

        public Criteria andGoodspriceIsNull() {
            addCriterion("goodsPrice is null");
            return (Criteria) this;
        }

        public Criteria andGoodspriceIsNotNull() {
            addCriterion("goodsPrice is not null");
            return (Criteria) this;
        }

        public Criteria andGoodspriceEqualTo(Double value) {
            addCriterion("goodsPrice =", value, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceNotEqualTo(Double value) {
            addCriterion("goodsPrice <>", value, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceGreaterThan(Double value) {
            addCriterion("goodsPrice >", value, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceGreaterThanOrEqualTo(Double value) {
            addCriterion("goodsPrice >=", value, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceLessThan(Double value) {
            addCriterion("goodsPrice <", value, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceLessThanOrEqualTo(Double value) {
            addCriterion("goodsPrice <=", value, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceIn(List<Double> values) {
            addCriterion("goodsPrice in", values, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceNotIn(List<Double> values) {
            addCriterion("goodsPrice not in", values, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceBetween(Double value1, Double value2) {
            addCriterion("goodsPrice between", value1, value2, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andGoodspriceNotBetween(Double value1, Double value2) {
            addCriterion("goodsPrice not between", value1, value2, "goodsprice");
            return (Criteria) this;
        }

        public Criteria andShowimageIsNull() {
            addCriterion("showImage is null");
            return (Criteria) this;
        }

        public Criteria andShowimageIsNotNull() {
            addCriterion("showImage is not null");
            return (Criteria) this;
        }

        public Criteria andShowimageEqualTo(String value) {
            addCriterion("showImage =", value, "showimage");
            return (Criteria) this;
        }

        public Criteria andShowimageNotEqualTo(String value) {
            addCriterion("showImage <>", value, "showimage");
            return (Criteria) this;
        }

        public Criteria andShowimageGreaterThan(String value) {
            addCriterion("showImage >", value, "showimage");
            return (Criteria) this;
        }

        public Criteria andShowimageGreaterThanOrEqualTo(String value) {
            addCriterion("showImage >=", value, "showimage");
            return (Criteria) this;
        }

        public Criteria andShowimageLessThan(String value) {
            addCriterion("showImage <", value, "showimage");
            return (Criteria) this;
        }

        public Criteria andShowimageLessThanOrEqualTo(String value) {
            addCriterion("showImage <=", value, "showimage");
            return (Criteria) this;
        }

        public Criteria andShowimageLike(String value) {
            addCriterion("showImage like", value, "showimage");
            return (Criteria) this;
        }

        public Criteria andShowimageNotLike(String value) {
            addCriterion("showImage not like", value, "showimage");
            return (Criteria) this;
        }

        public Criteria andShowimageIn(List<String> values) {
            addCriterion("showImage in", values, "showimage");
            return (Criteria) this;
        }

        public Criteria andShowimageNotIn(List<String> values) {
            addCriterion("showImage not in", values, "showimage");
            return (Criteria) this;
        }

        public Criteria andShowimageBetween(String value1, String value2) {
            addCriterion("showImage between", value1, value2, "showimage");
            return (Criteria) this;
        }

        public Criteria andShowimageNotBetween(String value1, String value2) {
            addCriterion("showImage not between", value1, value2, "showimage");
            return (Criteria) this;
        }

        public Criteria andGoodstagIsNull() {
            addCriterion("goodsTag is null");
            return (Criteria) this;
        }

        public Criteria andGoodstagIsNotNull() {
            addCriterion("goodsTag is not null");
            return (Criteria) this;
        }

        public Criteria andGoodstagEqualTo(String value) {
            addCriterion("goodsTag =", value, "goodstag");
            return (Criteria) this;
        }

        public Criteria andGoodstagNotEqualTo(String value) {
            addCriterion("goodsTag <>", value, "goodstag");
            return (Criteria) this;
        }

        public Criteria andGoodstagGreaterThan(String value) {
            addCriterion("goodsTag >", value, "goodstag");
            return (Criteria) this;
        }

        public Criteria andGoodstagGreaterThanOrEqualTo(String value) {
            addCriterion("goodsTag >=", value, "goodstag");
            return (Criteria) this;
        }

        public Criteria andGoodstagLessThan(String value) {
            addCriterion("goodsTag <", value, "goodstag");
            return (Criteria) this;
        }

        public Criteria andGoodstagLessThanOrEqualTo(String value) {
            addCriterion("goodsTag <=", value, "goodstag");
            return (Criteria) this;
        }

        public Criteria andGoodstagLike(String value) {
            addCriterion("goodsTag like", value, "goodstag");
            return (Criteria) this;
        }

        public Criteria andGoodstagNotLike(String value) {
            addCriterion("goodsTag not like", value, "goodstag");
            return (Criteria) this;
        }

        public Criteria andGoodstagIn(List<String> values) {
            addCriterion("goodsTag in", values, "goodstag");
            return (Criteria) this;
        }

        public Criteria andGoodstagNotIn(List<String> values) {
            addCriterion("goodsTag not in", values, "goodstag");
            return (Criteria) this;
        }

        public Criteria andGoodstagBetween(String value1, String value2) {
            addCriterion("goodsTag between", value1, value2, "goodstag");
            return (Criteria) this;
        }

        public Criteria andGoodstagNotBetween(String value1, String value2) {
            addCriterion("goodsTag not between", value1, value2, "goodstag");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1IsNull() {
            addCriterion("goodsDetailsImage1 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1IsNotNull() {
            addCriterion("goodsDetailsImage1 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1EqualTo(String value) {
            addCriterion("goodsDetailsImage1 =", value, "goodsdetailsimage1");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1NotEqualTo(String value) {
            addCriterion("goodsDetailsImage1 <>", value, "goodsdetailsimage1");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1GreaterThan(String value) {
            addCriterion("goodsDetailsImage1 >", value, "goodsdetailsimage1");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1GreaterThanOrEqualTo(String value) {
            addCriterion("goodsDetailsImage1 >=", value, "goodsdetailsimage1");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1LessThan(String value) {
            addCriterion("goodsDetailsImage1 <", value, "goodsdetailsimage1");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1LessThanOrEqualTo(String value) {
            addCriterion("goodsDetailsImage1 <=", value, "goodsdetailsimage1");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1Like(String value) {
            addCriterion("goodsDetailsImage1 like", value, "goodsdetailsimage1");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1NotLike(String value) {
            addCriterion("goodsDetailsImage1 not like", value, "goodsdetailsimage1");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1In(List<String> values) {
            addCriterion("goodsDetailsImage1 in", values, "goodsdetailsimage1");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1NotIn(List<String> values) {
            addCriterion("goodsDetailsImage1 not in", values, "goodsdetailsimage1");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1Between(String value1, String value2) {
            addCriterion("goodsDetailsImage1 between", value1, value2, "goodsdetailsimage1");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage1NotBetween(String value1, String value2) {
            addCriterion("goodsDetailsImage1 not between", value1, value2, "goodsdetailsimage1");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2IsNull() {
            addCriterion("goodsDetailsImage2 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2IsNotNull() {
            addCriterion("goodsDetailsImage2 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2EqualTo(String value) {
            addCriterion("goodsDetailsImage2 =", value, "goodsdetailsimage2");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2NotEqualTo(String value) {
            addCriterion("goodsDetailsImage2 <>", value, "goodsdetailsimage2");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2GreaterThan(String value) {
            addCriterion("goodsDetailsImage2 >", value, "goodsdetailsimage2");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2GreaterThanOrEqualTo(String value) {
            addCriterion("goodsDetailsImage2 >=", value, "goodsdetailsimage2");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2LessThan(String value) {
            addCriterion("goodsDetailsImage2 <", value, "goodsdetailsimage2");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2LessThanOrEqualTo(String value) {
            addCriterion("goodsDetailsImage2 <=", value, "goodsdetailsimage2");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2Like(String value) {
            addCriterion("goodsDetailsImage2 like", value, "goodsdetailsimage2");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2NotLike(String value) {
            addCriterion("goodsDetailsImage2 not like", value, "goodsdetailsimage2");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2In(List<String> values) {
            addCriterion("goodsDetailsImage2 in", values, "goodsdetailsimage2");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2NotIn(List<String> values) {
            addCriterion("goodsDetailsImage2 not in", values, "goodsdetailsimage2");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2Between(String value1, String value2) {
            addCriterion("goodsDetailsImage2 between", value1, value2, "goodsdetailsimage2");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage2NotBetween(String value1, String value2) {
            addCriterion("goodsDetailsImage2 not between", value1, value2, "goodsdetailsimage2");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3IsNull() {
            addCriterion("goodsDetailsImage3 is null");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3IsNotNull() {
            addCriterion("goodsDetailsImage3 is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3EqualTo(String value) {
            addCriterion("goodsDetailsImage3 =", value, "goodsdetailsimage3");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3NotEqualTo(String value) {
            addCriterion("goodsDetailsImage3 <>", value, "goodsdetailsimage3");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3GreaterThan(String value) {
            addCriterion("goodsDetailsImage3 >", value, "goodsdetailsimage3");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3GreaterThanOrEqualTo(String value) {
            addCriterion("goodsDetailsImage3 >=", value, "goodsdetailsimage3");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3LessThan(String value) {
            addCriterion("goodsDetailsImage3 <", value, "goodsdetailsimage3");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3LessThanOrEqualTo(String value) {
            addCriterion("goodsDetailsImage3 <=", value, "goodsdetailsimage3");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3Like(String value) {
            addCriterion("goodsDetailsImage3 like", value, "goodsdetailsimage3");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3NotLike(String value) {
            addCriterion("goodsDetailsImage3 not like", value, "goodsdetailsimage3");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3In(List<String> values) {
            addCriterion("goodsDetailsImage3 in", values, "goodsdetailsimage3");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3NotIn(List<String> values) {
            addCriterion("goodsDetailsImage3 not in", values, "goodsdetailsimage3");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3Between(String value1, String value2) {
            addCriterion("goodsDetailsImage3 between", value1, value2, "goodsdetailsimage3");
            return (Criteria) this;
        }

        public Criteria andGoodsdetailsimage3NotBetween(String value1, String value2) {
            addCriterion("goodsDetailsImage3 not between", value1, value2, "goodsdetailsimage3");
            return (Criteria) this;
        }

        public Criteria andGoodsclassIsNull() {
            addCriterion("goodsClass is null");
            return (Criteria) this;
        }

        public Criteria andGoodsclassIsNotNull() {
            addCriterion("goodsClass is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsclassEqualTo(String value) {
            addCriterion("goodsClass =", value, "goodsclass");
            return (Criteria) this;
        }

        public Criteria andGoodsclassNotEqualTo(String value) {
            addCriterion("goodsClass <>", value, "goodsclass");
            return (Criteria) this;
        }

        public Criteria andGoodsclassGreaterThan(String value) {
            addCriterion("goodsClass >", value, "goodsclass");
            return (Criteria) this;
        }

        public Criteria andGoodsclassGreaterThanOrEqualTo(String value) {
            addCriterion("goodsClass >=", value, "goodsclass");
            return (Criteria) this;
        }

        public Criteria andGoodsclassLessThan(String value) {
            addCriterion("goodsClass <", value, "goodsclass");
            return (Criteria) this;
        }

        public Criteria andGoodsclassLessThanOrEqualTo(String value) {
            addCriterion("goodsClass <=", value, "goodsclass");
            return (Criteria) this;
        }

        public Criteria andGoodsclassLike(String value) {
            addCriterion("goodsClass like", value, "goodsclass");
            return (Criteria) this;
        }

        public Criteria andGoodsclassNotLike(String value) {
            addCriterion("goodsClass not like", value, "goodsclass");
            return (Criteria) this;
        }

        public Criteria andGoodsclassIn(List<String> values) {
            addCriterion("goodsClass in", values, "goodsclass");
            return (Criteria) this;
        }

        public Criteria andGoodsclassNotIn(List<String> values) {
            addCriterion("goodsClass not in", values, "goodsclass");
            return (Criteria) this;
        }

        public Criteria andGoodsclassBetween(String value1, String value2) {
            addCriterion("goodsClass between", value1, value2, "goodsclass");
            return (Criteria) this;
        }

        public Criteria andGoodsclassNotBetween(String value1, String value2) {
            addCriterion("goodsClass not between", value1, value2, "goodsclass");
            return (Criteria) this;
        }

        public Criteria andGoodsstylenumIsNull() {
            addCriterion("goodsStyleNum is null");
            return (Criteria) this;
        }

        public Criteria andGoodsstylenumIsNotNull() {
            addCriterion("goodsStyleNum is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsstylenumEqualTo(Integer value) {
            addCriterion("goodsStyleNum =", value, "goodsstylenum");
            return (Criteria) this;
        }

        public Criteria andGoodsstylenumNotEqualTo(Integer value) {
            addCriterion("goodsStyleNum <>", value, "goodsstylenum");
            return (Criteria) this;
        }

        public Criteria andGoodsstylenumGreaterThan(Integer value) {
            addCriterion("goodsStyleNum >", value, "goodsstylenum");
            return (Criteria) this;
        }

        public Criteria andGoodsstylenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("goodsStyleNum >=", value, "goodsstylenum");
            return (Criteria) this;
        }

        public Criteria andGoodsstylenumLessThan(Integer value) {
            addCriterion("goodsStyleNum <", value, "goodsstylenum");
            return (Criteria) this;
        }

        public Criteria andGoodsstylenumLessThanOrEqualTo(Integer value) {
            addCriterion("goodsStyleNum <=", value, "goodsstylenum");
            return (Criteria) this;
        }

        public Criteria andGoodsstylenumIn(List<Integer> values) {
            addCriterion("goodsStyleNum in", values, "goodsstylenum");
            return (Criteria) this;
        }

        public Criteria andGoodsstylenumNotIn(List<Integer> values) {
            addCriterion("goodsStyleNum not in", values, "goodsstylenum");
            return (Criteria) this;
        }

        public Criteria andGoodsstylenumBetween(Integer value1, Integer value2) {
            addCriterion("goodsStyleNum between", value1, value2, "goodsstylenum");
            return (Criteria) this;
        }

        public Criteria andGoodsstylenumNotBetween(Integer value1, Integer value2) {
            addCriterion("goodsStyleNum not between", value1, value2, "goodsstylenum");
            return (Criteria) this;
        }

        public Criteria andGoodsparentidIsNull() {
            addCriterion("goodsParentId is null");
            return (Criteria) this;
        }

        public Criteria andGoodsparentidIsNotNull() {
            addCriterion("goodsParentId is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsparentidEqualTo(Integer value) {
            addCriterion("goodsParentId =", value, "goodsparentid");
            return (Criteria) this;
        }

        public Criteria andGoodsparentidNotEqualTo(Integer value) {
            addCriterion("goodsParentId <>", value, "goodsparentid");
            return (Criteria) this;
        }

        public Criteria andGoodsparentidGreaterThan(Integer value) {
            addCriterion("goodsParentId >", value, "goodsparentid");
            return (Criteria) this;
        }

        public Criteria andGoodsparentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("goodsParentId >=", value, "goodsparentid");
            return (Criteria) this;
        }

        public Criteria andGoodsparentidLessThan(Integer value) {
            addCriterion("goodsParentId <", value, "goodsparentid");
            return (Criteria) this;
        }

        public Criteria andGoodsparentidLessThanOrEqualTo(Integer value) {
            addCriterion("goodsParentId <=", value, "goodsparentid");
            return (Criteria) this;
        }

        public Criteria andGoodsparentidIn(List<Integer> values) {
            addCriterion("goodsParentId in", values, "goodsparentid");
            return (Criteria) this;
        }

        public Criteria andGoodsparentidNotIn(List<Integer> values) {
            addCriterion("goodsParentId not in", values, "goodsparentid");
            return (Criteria) this;
        }

        public Criteria andGoodsparentidBetween(Integer value1, Integer value2) {
            addCriterion("goodsParentId between", value1, value2, "goodsparentid");
            return (Criteria) this;
        }

        public Criteria andGoodsparentidNotBetween(Integer value1, Integer value2) {
            addCriterion("goodsParentId not between", value1, value2, "goodsparentid");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeIsNull() {
            addCriterion("goodsDescribe is null");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeIsNotNull() {
            addCriterion("goodsDescribe is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeEqualTo(String value) {
            addCriterion("goodsDescribe =", value, "goodsdescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeNotEqualTo(String value) {
            addCriterion("goodsDescribe <>", value, "goodsdescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeGreaterThan(String value) {
            addCriterion("goodsDescribe >", value, "goodsdescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeGreaterThanOrEqualTo(String value) {
            addCriterion("goodsDescribe >=", value, "goodsdescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeLessThan(String value) {
            addCriterion("goodsDescribe <", value, "goodsdescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeLessThanOrEqualTo(String value) {
            addCriterion("goodsDescribe <=", value, "goodsdescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeLike(String value) {
            addCriterion("goodsDescribe like", value, "goodsdescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeNotLike(String value) {
            addCriterion("goodsDescribe not like", value, "goodsdescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeIn(List<String> values) {
            addCriterion("goodsDescribe in", values, "goodsdescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeNotIn(List<String> values) {
            addCriterion("goodsDescribe not in", values, "goodsdescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeBetween(String value1, String value2) {
            addCriterion("goodsDescribe between", value1, value2, "goodsdescribe");
            return (Criteria) this;
        }

        public Criteria andGoodsdescribeNotBetween(String value1, String value2) {
            addCriterion("goodsDescribe not between", value1, value2, "goodsdescribe");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}