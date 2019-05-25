package com.ovft.configure.sys.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EduBookGoodsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public EduBookGoodsExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCatIdIsNull() {
            addCriterion("cat_id is null");
            return (Criteria) this;
        }

        public Criteria andCatIdIsNotNull() {
            addCriterion("cat_id is not null");
            return (Criteria) this;
        }

        public Criteria andCatIdEqualTo(Integer value) {
            addCriterion("cat_id =", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotEqualTo(Integer value) {
            addCriterion("cat_id <>", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdGreaterThan(Integer value) {
            addCriterion("cat_id >", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cat_id >=", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdLessThan(Integer value) {
            addCriterion("cat_id <", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdLessThanOrEqualTo(Integer value) {
            addCriterion("cat_id <=", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdIn(List<Integer> values) {
            addCriterion("cat_id in", values, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotIn(List<Integer> values) {
            addCriterion("cat_id not in", values, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdBetween(Integer value1, Integer value2) {
            addCriterion("cat_id between", value1, value2, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cat_id not between", value1, value2, "catId");
            return (Criteria) this;
        }

        public Criteria andExtendCatIdIsNull() {
            addCriterion("extend_cat_id is null");
            return (Criteria) this;
        }

        public Criteria andExtendCatIdIsNotNull() {
            addCriterion("extend_cat_id is not null");
            return (Criteria) this;
        }

        public Criteria andExtendCatIdEqualTo(Integer value) {
            addCriterion("extend_cat_id =", value, "extendCatId");
            return (Criteria) this;
        }

        public Criteria andExtendCatIdNotEqualTo(Integer value) {
            addCriterion("extend_cat_id <>", value, "extendCatId");
            return (Criteria) this;
        }

        public Criteria andExtendCatIdGreaterThan(Integer value) {
            addCriterion("extend_cat_id >", value, "extendCatId");
            return (Criteria) this;
        }

        public Criteria andExtendCatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("extend_cat_id >=", value, "extendCatId");
            return (Criteria) this;
        }

        public Criteria andExtendCatIdLessThan(Integer value) {
            addCriterion("extend_cat_id <", value, "extendCatId");
            return (Criteria) this;
        }

        public Criteria andExtendCatIdLessThanOrEqualTo(Integer value) {
            addCriterion("extend_cat_id <=", value, "extendCatId");
            return (Criteria) this;
        }

        public Criteria andExtendCatIdIn(List<Integer> values) {
            addCriterion("extend_cat_id in", values, "extendCatId");
            return (Criteria) this;
        }

        public Criteria andExtendCatIdNotIn(List<Integer> values) {
            addCriterion("extend_cat_id not in", values, "extendCatId");
            return (Criteria) this;
        }

        public Criteria andExtendCatIdBetween(Integer value1, Integer value2) {
            addCriterion("extend_cat_id between", value1, value2, "extendCatId");
            return (Criteria) this;
        }

        public Criteria andExtendCatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("extend_cat_id not between", value1, value2, "extendCatId");
            return (Criteria) this;
        }

        public Criteria andBooksSnIsNull() {
            addCriterion("books_sn is null");
            return (Criteria) this;
        }

        public Criteria andBooksSnIsNotNull() {
            addCriterion("books_sn is not null");
            return (Criteria) this;
        }

        public Criteria andBooksSnEqualTo(String value) {
            addCriterion("books_sn =", value, "booksSn");
            return (Criteria) this;
        }

        public Criteria andBooksSnNotEqualTo(String value) {
            addCriterion("books_sn <>", value, "booksSn");
            return (Criteria) this;
        }

        public Criteria andBooksSnGreaterThan(String value) {
            addCriterion("books_sn >", value, "booksSn");
            return (Criteria) this;
        }

        public Criteria andBooksSnGreaterThanOrEqualTo(String value) {
            addCriterion("books_sn >=", value, "booksSn");
            return (Criteria) this;
        }

        public Criteria andBooksSnLessThan(String value) {
            addCriterion("books_sn <", value, "booksSn");
            return (Criteria) this;
        }

        public Criteria andBooksSnLessThanOrEqualTo(String value) {
            addCriterion("books_sn <=", value, "booksSn");
            return (Criteria) this;
        }

        public Criteria andBooksSnLike(String value) {
            addCriterion("books_sn like", value, "booksSn");
            return (Criteria) this;
        }

        public Criteria andBooksSnNotLike(String value) {
            addCriterion("books_sn not like", value, "booksSn");
            return (Criteria) this;
        }

        public Criteria andBooksSnIn(List<String> values) {
            addCriterion("books_sn in", values, "booksSn");
            return (Criteria) this;
        }

        public Criteria andBooksSnNotIn(List<String> values) {
            addCriterion("books_sn not in", values, "booksSn");
            return (Criteria) this;
        }

        public Criteria andBooksSnBetween(String value1, String value2) {
            addCriterion("books_sn between", value1, value2, "booksSn");
            return (Criteria) this;
        }

        public Criteria andBooksSnNotBetween(String value1, String value2) {
            addCriterion("books_sn not between", value1, value2, "booksSn");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorIsNull() {
            addCriterion("books_author is null");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorIsNotNull() {
            addCriterion("books_author is not null");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorEqualTo(String value) {
            addCriterion("books_author =", value, "booksAuthor");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorNotEqualTo(String value) {
            addCriterion("books_author <>", value, "booksAuthor");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorGreaterThan(String value) {
            addCriterion("books_author >", value, "booksAuthor");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("books_author >=", value, "booksAuthor");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorLessThan(String value) {
            addCriterion("books_author <", value, "booksAuthor");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorLessThanOrEqualTo(String value) {
            addCriterion("books_author <=", value, "booksAuthor");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorLike(String value) {
            addCriterion("books_author like", value, "booksAuthor");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorNotLike(String value) {
            addCriterion("books_author not like", value, "booksAuthor");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorIn(List<String> values) {
            addCriterion("books_author in", values, "booksAuthor");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorNotIn(List<String> values) {
            addCriterion("books_author not in", values, "booksAuthor");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorBetween(String value1, String value2) {
            addCriterion("books_author between", value1, value2, "booksAuthor");
            return (Criteria) this;
        }

        public Criteria andBooksAuthorNotBetween(String value1, String value2) {
            addCriterion("books_author not between", value1, value2, "booksAuthor");
            return (Criteria) this;
        }

        public Criteria andBooksNameIsNull() {
            addCriterion("books_name is null");
            return (Criteria) this;
        }

        public Criteria andBooksNameIsNotNull() {
            addCriterion("books_name is not null");
            return (Criteria) this;
        }

        public Criteria andBooksNameEqualTo(String value) {
            addCriterion("books_name =", value, "booksName");
            return (Criteria) this;
        }

        public Criteria andBooksNameNotEqualTo(String value) {
            addCriterion("books_name <>", value, "booksName");
            return (Criteria) this;
        }

        public Criteria andBooksNameGreaterThan(String value) {
            addCriterion("books_name >", value, "booksName");
            return (Criteria) this;
        }

        public Criteria andBooksNameGreaterThanOrEqualTo(String value) {
            addCriterion("books_name >=", value, "booksName");
            return (Criteria) this;
        }

        public Criteria andBooksNameLessThan(String value) {
            addCriterion("books_name <", value, "booksName");
            return (Criteria) this;
        }

        public Criteria andBooksNameLessThanOrEqualTo(String value) {
            addCriterion("books_name <=", value, "booksName");
            return (Criteria) this;
        }

        public Criteria andBooksNameLike(String value) {
            addCriterion("books_name like", value, "booksName");
            return (Criteria) this;
        }

        public Criteria andBooksNameNotLike(String value) {
            addCriterion("books_name not like", value, "booksName");
            return (Criteria) this;
        }

        public Criteria andBooksNameIn(List<String> values) {
            addCriterion("books_name in", values, "booksName");
            return (Criteria) this;
        }

        public Criteria andBooksNameNotIn(List<String> values) {
            addCriterion("books_name not in", values, "booksName");
            return (Criteria) this;
        }

        public Criteria andBooksNameBetween(String value1, String value2) {
            addCriterion("books_name between", value1, value2, "booksName");
            return (Criteria) this;
        }

        public Criteria andBooksNameNotBetween(String value1, String value2) {
            addCriterion("books_name not between", value1, value2, "booksName");
            return (Criteria) this;
        }

        public Criteria andClickCountIsNull() {
            addCriterion("click_count is null");
            return (Criteria) this;
        }

        public Criteria andClickCountIsNotNull() {
            addCriterion("click_count is not null");
            return (Criteria) this;
        }

        public Criteria andClickCountEqualTo(Integer value) {
            addCriterion("click_count =", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountNotEqualTo(Integer value) {
            addCriterion("click_count <>", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountGreaterThan(Integer value) {
            addCriterion("click_count >", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("click_count >=", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountLessThan(Integer value) {
            addCriterion("click_count <", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountLessThanOrEqualTo(Integer value) {
            addCriterion("click_count <=", value, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountIn(List<Integer> values) {
            addCriterion("click_count in", values, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountNotIn(List<Integer> values) {
            addCriterion("click_count not in", values, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountBetween(Integer value1, Integer value2) {
            addCriterion("click_count between", value1, value2, "clickCount");
            return (Criteria) this;
        }

        public Criteria andClickCountNotBetween(Integer value1, Integer value2) {
            addCriterion("click_count not between", value1, value2, "clickCount");
            return (Criteria) this;
        }

        public Criteria andStoreCountIsNull() {
            addCriterion("store_count is null");
            return (Criteria) this;
        }

        public Criteria andStoreCountIsNotNull() {
            addCriterion("store_count is not null");
            return (Criteria) this;
        }

        public Criteria andStoreCountEqualTo(Short value) {
            addCriterion("store_count =", value, "storeCount");
            return (Criteria) this;
        }

        public Criteria andStoreCountNotEqualTo(Short value) {
            addCriterion("store_count <>", value, "storeCount");
            return (Criteria) this;
        }

        public Criteria andStoreCountGreaterThan(Short value) {
            addCriterion("store_count >", value, "storeCount");
            return (Criteria) this;
        }

        public Criteria andStoreCountGreaterThanOrEqualTo(Short value) {
            addCriterion("store_count >=", value, "storeCount");
            return (Criteria) this;
        }

        public Criteria andStoreCountLessThan(Short value) {
            addCriterion("store_count <", value, "storeCount");
            return (Criteria) this;
        }

        public Criteria andStoreCountLessThanOrEqualTo(Short value) {
            addCriterion("store_count <=", value, "storeCount");
            return (Criteria) this;
        }

        public Criteria andStoreCountIn(List<Short> values) {
            addCriterion("store_count in", values, "storeCount");
            return (Criteria) this;
        }

        public Criteria andStoreCountNotIn(List<Short> values) {
            addCriterion("store_count not in", values, "storeCount");
            return (Criteria) this;
        }

        public Criteria andStoreCountBetween(Short value1, Short value2) {
            addCriterion("store_count between", value1, value2, "storeCount");
            return (Criteria) this;
        }

        public Criteria andStoreCountNotBetween(Short value1, Short value2) {
            addCriterion("store_count not between", value1, value2, "storeCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountIsNull() {
            addCriterion("comment_count is null");
            return (Criteria) this;
        }

        public Criteria andCommentCountIsNotNull() {
            addCriterion("comment_count is not null");
            return (Criteria) this;
        }

        public Criteria andCommentCountEqualTo(Short value) {
            addCriterion("comment_count =", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotEqualTo(Short value) {
            addCriterion("comment_count <>", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountGreaterThan(Short value) {
            addCriterion("comment_count >", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountGreaterThanOrEqualTo(Short value) {
            addCriterion("comment_count >=", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountLessThan(Short value) {
            addCriterion("comment_count <", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountLessThanOrEqualTo(Short value) {
            addCriterion("comment_count <=", value, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountIn(List<Short> values) {
            addCriterion("comment_count in", values, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotIn(List<Short> values) {
            addCriterion("comment_count not in", values, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountBetween(Short value1, Short value2) {
            addCriterion("comment_count between", value1, value2, "commentCount");
            return (Criteria) this;
        }

        public Criteria andCommentCountNotBetween(Short value1, Short value2) {
            addCriterion("comment_count not between", value1, value2, "commentCount");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Integer value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Integer value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Integer value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Integer value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Integer value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Integer> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Integer> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Integer value1, Integer value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNull() {
            addCriterion("market_price is null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNotNull() {
            addCriterion("market_price is not null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceEqualTo(BigDecimal value) {
            addCriterion("market_price =", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotEqualTo(BigDecimal value) {
            addCriterion("market_price <>", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThan(BigDecimal value) {
            addCriterion("market_price >", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("market_price >=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThan(BigDecimal value) {
            addCriterion("market_price <", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("market_price <=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIn(List<BigDecimal> values) {
            addCriterion("market_price in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotIn(List<BigDecimal> values) {
            addCriterion("market_price not in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("market_price between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("market_price not between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceIsNull() {
            addCriterion("shop_price is null");
            return (Criteria) this;
        }

        public Criteria andShopPriceIsNotNull() {
            addCriterion("shop_price is not null");
            return (Criteria) this;
        }

        public Criteria andShopPriceEqualTo(BigDecimal value) {
            addCriterion("shop_price =", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceNotEqualTo(BigDecimal value) {
            addCriterion("shop_price <>", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceGreaterThan(BigDecimal value) {
            addCriterion("shop_price >", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("shop_price >=", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceLessThan(BigDecimal value) {
            addCriterion("shop_price <", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("shop_price <=", value, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceIn(List<BigDecimal> values) {
            addCriterion("shop_price in", values, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceNotIn(List<BigDecimal> values) {
            addCriterion("shop_price not in", values, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shop_price between", value1, value2, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andShopPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shop_price not between", value1, value2, "shopPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNull() {
            addCriterion("cost_price is null");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNotNull() {
            addCriterion("cost_price is not null");
            return (Criteria) this;
        }

        public Criteria andCostPriceEqualTo(BigDecimal value) {
            addCriterion("cost_price =", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotEqualTo(BigDecimal value) {
            addCriterion("cost_price <>", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThan(BigDecimal value) {
            addCriterion("cost_price >", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_price >=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThan(BigDecimal value) {
            addCriterion("cost_price <", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cost_price <=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceIn(List<BigDecimal> values) {
            addCriterion("cost_price in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotIn(List<BigDecimal> values) {
            addCriterion("cost_price not in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_price between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cost_price not between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNull() {
            addCriterion("keywords is null");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNotNull() {
            addCriterion("keywords is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordsEqualTo(String value) {
            addCriterion("keywords =", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotEqualTo(String value) {
            addCriterion("keywords <>", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThan(String value) {
            addCriterion("keywords >", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("keywords >=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThan(String value) {
            addCriterion("keywords <", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThanOrEqualTo(String value) {
            addCriterion("keywords <=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLike(String value) {
            addCriterion("keywords like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotLike(String value) {
            addCriterion("keywords not like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsIn(List<String> values) {
            addCriterion("keywords in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotIn(List<String> values) {
            addCriterion("keywords not in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsBetween(String value1, String value2) {
            addCriterion("keywords between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotBetween(String value1, String value2) {
            addCriterion("keywords not between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkIsNull() {
            addCriterion("books_remark is null");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkIsNotNull() {
            addCriterion("books_remark is not null");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkEqualTo(String value) {
            addCriterion("books_remark =", value, "booksRemark");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkNotEqualTo(String value) {
            addCriterion("books_remark <>", value, "booksRemark");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkGreaterThan(String value) {
            addCriterion("books_remark >", value, "booksRemark");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("books_remark >=", value, "booksRemark");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkLessThan(String value) {
            addCriterion("books_remark <", value, "booksRemark");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkLessThanOrEqualTo(String value) {
            addCriterion("books_remark <=", value, "booksRemark");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkLike(String value) {
            addCriterion("books_remark like", value, "booksRemark");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkNotLike(String value) {
            addCriterion("books_remark not like", value, "booksRemark");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkIn(List<String> values) {
            addCriterion("books_remark in", values, "booksRemark");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkNotIn(List<String> values) {
            addCriterion("books_remark not in", values, "booksRemark");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkBetween(String value1, String value2) {
            addCriterion("books_remark between", value1, value2, "booksRemark");
            return (Criteria) this;
        }

        public Criteria andBooksRemarkNotBetween(String value1, String value2) {
            addCriterion("books_remark not between", value1, value2, "booksRemark");
            return (Criteria) this;
        }

        public Criteria andOriginalImgIsNull() {
            addCriterion("original_img is null");
            return (Criteria) this;
        }

        public Criteria andOriginalImgIsNotNull() {
            addCriterion("original_img is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalImgEqualTo(String value) {
            addCriterion("original_img =", value, "originalImg");
            return (Criteria) this;
        }

        public Criteria andOriginalImgNotEqualTo(String value) {
            addCriterion("original_img <>", value, "originalImg");
            return (Criteria) this;
        }

        public Criteria andOriginalImgGreaterThan(String value) {
            addCriterion("original_img >", value, "originalImg");
            return (Criteria) this;
        }

        public Criteria andOriginalImgGreaterThanOrEqualTo(String value) {
            addCriterion("original_img >=", value, "originalImg");
            return (Criteria) this;
        }

        public Criteria andOriginalImgLessThan(String value) {
            addCriterion("original_img <", value, "originalImg");
            return (Criteria) this;
        }

        public Criteria andOriginalImgLessThanOrEqualTo(String value) {
            addCriterion("original_img <=", value, "originalImg");
            return (Criteria) this;
        }

        public Criteria andOriginalImgLike(String value) {
            addCriterion("original_img like", value, "originalImg");
            return (Criteria) this;
        }

        public Criteria andOriginalImgNotLike(String value) {
            addCriterion("original_img not like", value, "originalImg");
            return (Criteria) this;
        }

        public Criteria andOriginalImgIn(List<String> values) {
            addCriterion("original_img in", values, "originalImg");
            return (Criteria) this;
        }

        public Criteria andOriginalImgNotIn(List<String> values) {
            addCriterion("original_img not in", values, "originalImg");
            return (Criteria) this;
        }

        public Criteria andOriginalImgBetween(String value1, String value2) {
            addCriterion("original_img between", value1, value2, "originalImg");
            return (Criteria) this;
        }

        public Criteria andOriginalImgNotBetween(String value1, String value2) {
            addCriterion("original_img not between", value1, value2, "originalImg");
            return (Criteria) this;
        }

        public Criteria andIsRealIsNull() {
            addCriterion("is_real is null");
            return (Criteria) this;
        }

        public Criteria andIsRealIsNotNull() {
            addCriterion("is_real is not null");
            return (Criteria) this;
        }

        public Criteria andIsRealEqualTo(Byte value) {
            addCriterion("is_real =", value, "isReal");
            return (Criteria) this;
        }

        public Criteria andIsRealNotEqualTo(Byte value) {
            addCriterion("is_real <>", value, "isReal");
            return (Criteria) this;
        }

        public Criteria andIsRealGreaterThan(Byte value) {
            addCriterion("is_real >", value, "isReal");
            return (Criteria) this;
        }

        public Criteria andIsRealGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_real >=", value, "isReal");
            return (Criteria) this;
        }

        public Criteria andIsRealLessThan(Byte value) {
            addCriterion("is_real <", value, "isReal");
            return (Criteria) this;
        }

        public Criteria andIsRealLessThanOrEqualTo(Byte value) {
            addCriterion("is_real <=", value, "isReal");
            return (Criteria) this;
        }

        public Criteria andIsRealIn(List<Byte> values) {
            addCriterion("is_real in", values, "isReal");
            return (Criteria) this;
        }

        public Criteria andIsRealNotIn(List<Byte> values) {
            addCriterion("is_real not in", values, "isReal");
            return (Criteria) this;
        }

        public Criteria andIsRealBetween(Byte value1, Byte value2) {
            addCriterion("is_real between", value1, value2, "isReal");
            return (Criteria) this;
        }

        public Criteria andIsRealNotBetween(Byte value1, Byte value2) {
            addCriterion("is_real not between", value1, value2, "isReal");
            return (Criteria) this;
        }

        public Criteria andIsOnSaleIsNull() {
            addCriterion("is_on_sale is null");
            return (Criteria) this;
        }

        public Criteria andIsOnSaleIsNotNull() {
            addCriterion("is_on_sale is not null");
            return (Criteria) this;
        }

        public Criteria andIsOnSaleEqualTo(Byte value) {
            addCriterion("is_on_sale =", value, "isOnSale");
            return (Criteria) this;
        }

        public Criteria andIsOnSaleNotEqualTo(Byte value) {
            addCriterion("is_on_sale <>", value, "isOnSale");
            return (Criteria) this;
        }

        public Criteria andIsOnSaleGreaterThan(Byte value) {
            addCriterion("is_on_sale >", value, "isOnSale");
            return (Criteria) this;
        }

        public Criteria andIsOnSaleGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_on_sale >=", value, "isOnSale");
            return (Criteria) this;
        }

        public Criteria andIsOnSaleLessThan(Byte value) {
            addCriterion("is_on_sale <", value, "isOnSale");
            return (Criteria) this;
        }

        public Criteria andIsOnSaleLessThanOrEqualTo(Byte value) {
            addCriterion("is_on_sale <=", value, "isOnSale");
            return (Criteria) this;
        }

        public Criteria andIsOnSaleIn(List<Byte> values) {
            addCriterion("is_on_sale in", values, "isOnSale");
            return (Criteria) this;
        }

        public Criteria andIsOnSaleNotIn(List<Byte> values) {
            addCriterion("is_on_sale not in", values, "isOnSale");
            return (Criteria) this;
        }

        public Criteria andIsOnSaleBetween(Byte value1, Byte value2) {
            addCriterion("is_on_sale between", value1, value2, "isOnSale");
            return (Criteria) this;
        }

        public Criteria andIsOnSaleNotBetween(Byte value1, Byte value2) {
            addCriterion("is_on_sale not between", value1, value2, "isOnSale");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingIsNull() {
            addCriterion("is_free_shipping is null");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingIsNotNull() {
            addCriterion("is_free_shipping is not null");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingEqualTo(Byte value) {
            addCriterion("is_free_shipping =", value, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingNotEqualTo(Byte value) {
            addCriterion("is_free_shipping <>", value, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingGreaterThan(Byte value) {
            addCriterion("is_free_shipping >", value, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_free_shipping >=", value, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingLessThan(Byte value) {
            addCriterion("is_free_shipping <", value, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingLessThanOrEqualTo(Byte value) {
            addCriterion("is_free_shipping <=", value, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingIn(List<Byte> values) {
            addCriterion("is_free_shipping in", values, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingNotIn(List<Byte> values) {
            addCriterion("is_free_shipping not in", values, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingBetween(Byte value1, Byte value2) {
            addCriterion("is_free_shipping between", value1, value2, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andIsFreeShippingNotBetween(Byte value1, Byte value2) {
            addCriterion("is_free_shipping not between", value1, value2, "isFreeShipping");
            return (Criteria) this;
        }

        public Criteria andOnTimeIsNull() {
            addCriterion("on_time is null");
            return (Criteria) this;
        }

        public Criteria andOnTimeIsNotNull() {
            addCriterion("on_time is not null");
            return (Criteria) this;
        }

        public Criteria andOnTimeEqualTo(Integer value) {
            addCriterion("on_time =", value, "onTime");
            return (Criteria) this;
        }

        public Criteria andOnTimeNotEqualTo(Integer value) {
            addCriterion("on_time <>", value, "onTime");
            return (Criteria) this;
        }

        public Criteria andOnTimeGreaterThan(Integer value) {
            addCriterion("on_time >", value, "onTime");
            return (Criteria) this;
        }

        public Criteria andOnTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("on_time >=", value, "onTime");
            return (Criteria) this;
        }

        public Criteria andOnTimeLessThan(Integer value) {
            addCriterion("on_time <", value, "onTime");
            return (Criteria) this;
        }

        public Criteria andOnTimeLessThanOrEqualTo(Integer value) {
            addCriterion("on_time <=", value, "onTime");
            return (Criteria) this;
        }

        public Criteria andOnTimeIn(List<Integer> values) {
            addCriterion("on_time in", values, "onTime");
            return (Criteria) this;
        }

        public Criteria andOnTimeNotIn(List<Integer> values) {
            addCriterion("on_time not in", values, "onTime");
            return (Criteria) this;
        }

        public Criteria andOnTimeBetween(Integer value1, Integer value2) {
            addCriterion("on_time between", value1, value2, "onTime");
            return (Criteria) this;
        }

        public Criteria andOnTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("on_time not between", value1, value2, "onTime");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Short value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Short value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Short value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Short value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Short value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Short value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Short> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Short> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Short value1, Short value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Short value1, Short value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIsNull() {
            addCriterion("is_recommend is null");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIsNotNull() {
            addCriterion("is_recommend is not null");
            return (Criteria) this;
        }

        public Criteria andIsRecommendEqualTo(Byte value) {
            addCriterion("is_recommend =", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNotEqualTo(Byte value) {
            addCriterion("is_recommend <>", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendGreaterThan(Byte value) {
            addCriterion("is_recommend >", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_recommend >=", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendLessThan(Byte value) {
            addCriterion("is_recommend <", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendLessThanOrEqualTo(Byte value) {
            addCriterion("is_recommend <=", value, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIn(List<Byte> values) {
            addCriterion("is_recommend in", values, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNotIn(List<Byte> values) {
            addCriterion("is_recommend not in", values, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendBetween(Byte value1, Byte value2) {
            addCriterion("is_recommend between", value1, value2, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNotBetween(Byte value1, Byte value2) {
            addCriterion("is_recommend not between", value1, value2, "isRecommend");
            return (Criteria) this;
        }

        public Criteria andIsNewIsNull() {
            addCriterion("is_new is null");
            return (Criteria) this;
        }

        public Criteria andIsNewIsNotNull() {
            addCriterion("is_new is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewEqualTo(Byte value) {
            addCriterion("is_new =", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotEqualTo(Byte value) {
            addCriterion("is_new <>", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewGreaterThan(Byte value) {
            addCriterion("is_new >", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_new >=", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewLessThan(Byte value) {
            addCriterion("is_new <", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewLessThanOrEqualTo(Byte value) {
            addCriterion("is_new <=", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewIn(List<Byte> values) {
            addCriterion("is_new in", values, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotIn(List<Byte> values) {
            addCriterion("is_new not in", values, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewBetween(Byte value1, Byte value2) {
            addCriterion("is_new between", value1, value2, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotBetween(Byte value1, Byte value2) {
            addCriterion("is_new not between", value1, value2, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsHotIsNull() {
            addCriterion("is_hot is null");
            return (Criteria) this;
        }

        public Criteria andIsHotIsNotNull() {
            addCriterion("is_hot is not null");
            return (Criteria) this;
        }

        public Criteria andIsHotEqualTo(Byte value) {
            addCriterion("is_hot =", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotNotEqualTo(Byte value) {
            addCriterion("is_hot <>", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotGreaterThan(Byte value) {
            addCriterion("is_hot >", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_hot >=", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotLessThan(Byte value) {
            addCriterion("is_hot <", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotLessThanOrEqualTo(Byte value) {
            addCriterion("is_hot <=", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotIn(List<Byte> values) {
            addCriterion("is_hot in", values, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotNotIn(List<Byte> values) {
            addCriterion("is_hot not in", values, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotBetween(Byte value1, Byte value2) {
            addCriterion("is_hot between", value1, value2, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotNotBetween(Byte value1, Byte value2) {
            addCriterion("is_hot not between", value1, value2, "isHot");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIsNull() {
            addCriterion("last_update is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIsNotNull() {
            addCriterion("last_update is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateEqualTo(Integer value) {
            addCriterion("last_update =", value, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateNotEqualTo(Integer value) {
            addCriterion("last_update <>", value, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateGreaterThan(Integer value) {
            addCriterion("last_update >", value, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_update >=", value, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateLessThan(Integer value) {
            addCriterion("last_update <", value, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateLessThanOrEqualTo(Integer value) {
            addCriterion("last_update <=", value, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateIn(List<Integer> values) {
            addCriterion("last_update in", values, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateNotIn(List<Integer> values) {
            addCriterion("last_update not in", values, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateBetween(Integer value1, Integer value2) {
            addCriterion("last_update between", value1, value2, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateNotBetween(Integer value1, Integer value2) {
            addCriterion("last_update not between", value1, value2, "lastUpdate");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNull() {
            addCriterion("goods_type is null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIsNotNull() {
            addCriterion("goods_type is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeEqualTo(Short value) {
            addCriterion("goods_type =", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotEqualTo(Short value) {
            addCriterion("goods_type <>", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThan(Short value) {
            addCriterion("goods_type >", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("goods_type >=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThan(Short value) {
            addCriterion("goods_type <", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeLessThanOrEqualTo(Short value) {
            addCriterion("goods_type <=", value, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIn(List<Short> values) {
            addCriterion("goods_type in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotIn(List<Short> values) {
            addCriterion("goods_type not in", values, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeBetween(Short value1, Short value2) {
            addCriterion("goods_type between", value1, value2, "goodsType");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeNotBetween(Short value1, Short value2) {
            addCriterion("goods_type not between", value1, value2, "goodsType");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIsNull() {
            addCriterion("spec_type is null");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIsNotNull() {
            addCriterion("spec_type is not null");
            return (Criteria) this;
        }

        public Criteria andSpecTypeEqualTo(Short value) {
            addCriterion("spec_type =", value, "specType");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNotEqualTo(Short value) {
            addCriterion("spec_type <>", value, "specType");
            return (Criteria) this;
        }

        public Criteria andSpecTypeGreaterThan(Short value) {
            addCriterion("spec_type >", value, "specType");
            return (Criteria) this;
        }

        public Criteria andSpecTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("spec_type >=", value, "specType");
            return (Criteria) this;
        }

        public Criteria andSpecTypeLessThan(Short value) {
            addCriterion("spec_type <", value, "specType");
            return (Criteria) this;
        }

        public Criteria andSpecTypeLessThanOrEqualTo(Short value) {
            addCriterion("spec_type <=", value, "specType");
            return (Criteria) this;
        }

        public Criteria andSpecTypeIn(List<Short> values) {
            addCriterion("spec_type in", values, "specType");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNotIn(List<Short> values) {
            addCriterion("spec_type not in", values, "specType");
            return (Criteria) this;
        }

        public Criteria andSpecTypeBetween(Short value1, Short value2) {
            addCriterion("spec_type between", value1, value2, "specType");
            return (Criteria) this;
        }

        public Criteria andSpecTypeNotBetween(Short value1, Short value2) {
            addCriterion("spec_type not between", value1, value2, "specType");
            return (Criteria) this;
        }

        public Criteria andGiveIntegralIsNull() {
            addCriterion("give_integral is null");
            return (Criteria) this;
        }

        public Criteria andGiveIntegralIsNotNull() {
            addCriterion("give_integral is not null");
            return (Criteria) this;
        }

        public Criteria andGiveIntegralEqualTo(Integer value) {
            addCriterion("give_integral =", value, "giveIntegral");
            return (Criteria) this;
        }

        public Criteria andGiveIntegralNotEqualTo(Integer value) {
            addCriterion("give_integral <>", value, "giveIntegral");
            return (Criteria) this;
        }

        public Criteria andGiveIntegralGreaterThan(Integer value) {
            addCriterion("give_integral >", value, "giveIntegral");
            return (Criteria) this;
        }

        public Criteria andGiveIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("give_integral >=", value, "giveIntegral");
            return (Criteria) this;
        }

        public Criteria andGiveIntegralLessThan(Integer value) {
            addCriterion("give_integral <", value, "giveIntegral");
            return (Criteria) this;
        }

        public Criteria andGiveIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("give_integral <=", value, "giveIntegral");
            return (Criteria) this;
        }

        public Criteria andGiveIntegralIn(List<Integer> values) {
            addCriterion("give_integral in", values, "giveIntegral");
            return (Criteria) this;
        }

        public Criteria andGiveIntegralNotIn(List<Integer> values) {
            addCriterion("give_integral not in", values, "giveIntegral");
            return (Criteria) this;
        }

        public Criteria andGiveIntegralBetween(Integer value1, Integer value2) {
            addCriterion("give_integral between", value1, value2, "giveIntegral");
            return (Criteria) this;
        }

        public Criteria andGiveIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("give_integral not between", value1, value2, "giveIntegral");
            return (Criteria) this;
        }

        public Criteria andExchangeIntegralIsNull() {
            addCriterion("exchange_integral is null");
            return (Criteria) this;
        }

        public Criteria andExchangeIntegralIsNotNull() {
            addCriterion("exchange_integral is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeIntegralEqualTo(Integer value) {
            addCriterion("exchange_integral =", value, "exchangeIntegral");
            return (Criteria) this;
        }

        public Criteria andExchangeIntegralNotEqualTo(Integer value) {
            addCriterion("exchange_integral <>", value, "exchangeIntegral");
            return (Criteria) this;
        }

        public Criteria andExchangeIntegralGreaterThan(Integer value) {
            addCriterion("exchange_integral >", value, "exchangeIntegral");
            return (Criteria) this;
        }

        public Criteria andExchangeIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("exchange_integral >=", value, "exchangeIntegral");
            return (Criteria) this;
        }

        public Criteria andExchangeIntegralLessThan(Integer value) {
            addCriterion("exchange_integral <", value, "exchangeIntegral");
            return (Criteria) this;
        }

        public Criteria andExchangeIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("exchange_integral <=", value, "exchangeIntegral");
            return (Criteria) this;
        }

        public Criteria andExchangeIntegralIn(List<Integer> values) {
            addCriterion("exchange_integral in", values, "exchangeIntegral");
            return (Criteria) this;
        }

        public Criteria andExchangeIntegralNotIn(List<Integer> values) {
            addCriterion("exchange_integral not in", values, "exchangeIntegral");
            return (Criteria) this;
        }

        public Criteria andExchangeIntegralBetween(Integer value1, Integer value2) {
            addCriterion("exchange_integral between", value1, value2, "exchangeIntegral");
            return (Criteria) this;
        }

        public Criteria andExchangeIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("exchange_integral not between", value1, value2, "exchangeIntegral");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdIsNull() {
            addCriterion("suppliers_id is null");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdIsNotNull() {
            addCriterion("suppliers_id is not null");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdEqualTo(Short value) {
            addCriterion("suppliers_id =", value, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdNotEqualTo(Short value) {
            addCriterion("suppliers_id <>", value, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdGreaterThan(Short value) {
            addCriterion("suppliers_id >", value, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdGreaterThanOrEqualTo(Short value) {
            addCriterion("suppliers_id >=", value, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdLessThan(Short value) {
            addCriterion("suppliers_id <", value, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdLessThanOrEqualTo(Short value) {
            addCriterion("suppliers_id <=", value, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdIn(List<Short> values) {
            addCriterion("suppliers_id in", values, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdNotIn(List<Short> values) {
            addCriterion("suppliers_id not in", values, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdBetween(Short value1, Short value2) {
            addCriterion("suppliers_id between", value1, value2, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdNotBetween(Short value1, Short value2) {
            addCriterion("suppliers_id not between", value1, value2, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSalesSumIsNull() {
            addCriterion("sales_sum is null");
            return (Criteria) this;
        }

        public Criteria andSalesSumIsNotNull() {
            addCriterion("sales_sum is not null");
            return (Criteria) this;
        }

        public Criteria andSalesSumEqualTo(Integer value) {
            addCriterion("sales_sum =", value, "salesSum");
            return (Criteria) this;
        }

        public Criteria andSalesSumNotEqualTo(Integer value) {
            addCriterion("sales_sum <>", value, "salesSum");
            return (Criteria) this;
        }

        public Criteria andSalesSumGreaterThan(Integer value) {
            addCriterion("sales_sum >", value, "salesSum");
            return (Criteria) this;
        }

        public Criteria andSalesSumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sales_sum >=", value, "salesSum");
            return (Criteria) this;
        }

        public Criteria andSalesSumLessThan(Integer value) {
            addCriterion("sales_sum <", value, "salesSum");
            return (Criteria) this;
        }

        public Criteria andSalesSumLessThanOrEqualTo(Integer value) {
            addCriterion("sales_sum <=", value, "salesSum");
            return (Criteria) this;
        }

        public Criteria andSalesSumIn(List<Integer> values) {
            addCriterion("sales_sum in", values, "salesSum");
            return (Criteria) this;
        }

        public Criteria andSalesSumNotIn(List<Integer> values) {
            addCriterion("sales_sum not in", values, "salesSum");
            return (Criteria) this;
        }

        public Criteria andSalesSumBetween(Integer value1, Integer value2) {
            addCriterion("sales_sum between", value1, value2, "salesSum");
            return (Criteria) this;
        }

        public Criteria andSalesSumNotBetween(Integer value1, Integer value2) {
            addCriterion("sales_sum not between", value1, value2, "salesSum");
            return (Criteria) this;
        }

        public Criteria andPromTypeIsNull() {
            addCriterion("prom_type is null");
            return (Criteria) this;
        }

        public Criteria andPromTypeIsNotNull() {
            addCriterion("prom_type is not null");
            return (Criteria) this;
        }

        public Criteria andPromTypeEqualTo(Byte value) {
            addCriterion("prom_type =", value, "promType");
            return (Criteria) this;
        }

        public Criteria andPromTypeNotEqualTo(Byte value) {
            addCriterion("prom_type <>", value, "promType");
            return (Criteria) this;
        }

        public Criteria andPromTypeGreaterThan(Byte value) {
            addCriterion("prom_type >", value, "promType");
            return (Criteria) this;
        }

        public Criteria andPromTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("prom_type >=", value, "promType");
            return (Criteria) this;
        }

        public Criteria andPromTypeLessThan(Byte value) {
            addCriterion("prom_type <", value, "promType");
            return (Criteria) this;
        }

        public Criteria andPromTypeLessThanOrEqualTo(Byte value) {
            addCriterion("prom_type <=", value, "promType");
            return (Criteria) this;
        }

        public Criteria andPromTypeIn(List<Byte> values) {
            addCriterion("prom_type in", values, "promType");
            return (Criteria) this;
        }

        public Criteria andPromTypeNotIn(List<Byte> values) {
            addCriterion("prom_type not in", values, "promType");
            return (Criteria) this;
        }

        public Criteria andPromTypeBetween(Byte value1, Byte value2) {
            addCriterion("prom_type between", value1, value2, "promType");
            return (Criteria) this;
        }

        public Criteria andPromTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("prom_type not between", value1, value2, "promType");
            return (Criteria) this;
        }

        public Criteria andPromIdIsNull() {
            addCriterion("prom_id is null");
            return (Criteria) this;
        }

        public Criteria andPromIdIsNotNull() {
            addCriterion("prom_id is not null");
            return (Criteria) this;
        }

        public Criteria andPromIdEqualTo(Integer value) {
            addCriterion("prom_id =", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotEqualTo(Integer value) {
            addCriterion("prom_id <>", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdGreaterThan(Integer value) {
            addCriterion("prom_id >", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("prom_id >=", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdLessThan(Integer value) {
            addCriterion("prom_id <", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdLessThanOrEqualTo(Integer value) {
            addCriterion("prom_id <=", value, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdIn(List<Integer> values) {
            addCriterion("prom_id in", values, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotIn(List<Integer> values) {
            addCriterion("prom_id not in", values, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdBetween(Integer value1, Integer value2) {
            addCriterion("prom_id between", value1, value2, "promId");
            return (Criteria) this;
        }

        public Criteria andPromIdNotBetween(Integer value1, Integer value2) {
            addCriterion("prom_id not between", value1, value2, "promId");
            return (Criteria) this;
        }

        public Criteria andCommissionIsNull() {
            addCriterion("commission is null");
            return (Criteria) this;
        }

        public Criteria andCommissionIsNotNull() {
            addCriterion("commission is not null");
            return (Criteria) this;
        }

        public Criteria andCommissionEqualTo(BigDecimal value) {
            addCriterion("commission =", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotEqualTo(BigDecimal value) {
            addCriterion("commission <>", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionGreaterThan(BigDecimal value) {
            addCriterion("commission >", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("commission >=", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionLessThan(BigDecimal value) {
            addCriterion("commission <", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("commission <=", value, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionIn(List<BigDecimal> values) {
            addCriterion("commission in", values, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotIn(List<BigDecimal> values) {
            addCriterion("commission not in", values, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commission between", value1, value2, "commission");
            return (Criteria) this;
        }

        public Criteria andCommissionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commission not between", value1, value2, "commission");
            return (Criteria) this;
        }

        public Criteria andSpuIsNull() {
            addCriterion("spu is null");
            return (Criteria) this;
        }

        public Criteria andSpuIsNotNull() {
            addCriterion("spu is not null");
            return (Criteria) this;
        }

        public Criteria andSpuEqualTo(String value) {
            addCriterion("spu =", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuNotEqualTo(String value) {
            addCriterion("spu <>", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuGreaterThan(String value) {
            addCriterion("spu >", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuGreaterThanOrEqualTo(String value) {
            addCriterion("spu >=", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuLessThan(String value) {
            addCriterion("spu <", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuLessThanOrEqualTo(String value) {
            addCriterion("spu <=", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuLike(String value) {
            addCriterion("spu like", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuNotLike(String value) {
            addCriterion("spu not like", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuIn(List<String> values) {
            addCriterion("spu in", values, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuNotIn(List<String> values) {
            addCriterion("spu not in", values, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuBetween(String value1, String value2) {
            addCriterion("spu between", value1, value2, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuNotBetween(String value1, String value2) {
            addCriterion("spu not between", value1, value2, "spu");
            return (Criteria) this;
        }

        public Criteria andSkuIsNull() {
            addCriterion("sku is null");
            return (Criteria) this;
        }

        public Criteria andSkuIsNotNull() {
            addCriterion("sku is not null");
            return (Criteria) this;
        }

        public Criteria andSkuEqualTo(String value) {
            addCriterion("sku =", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotEqualTo(String value) {
            addCriterion("sku <>", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuGreaterThan(String value) {
            addCriterion("sku >", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuGreaterThanOrEqualTo(String value) {
            addCriterion("sku >=", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLessThan(String value) {
            addCriterion("sku <", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLessThanOrEqualTo(String value) {
            addCriterion("sku <=", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLike(String value) {
            addCriterion("sku like", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotLike(String value) {
            addCriterion("sku not like", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuIn(List<String> values) {
            addCriterion("sku in", values, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotIn(List<String> values) {
            addCriterion("sku not in", values, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuBetween(String value1, String value2) {
            addCriterion("sku between", value1, value2, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotBetween(String value1, String value2) {
            addCriterion("sku not between", value1, value2, "sku");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNull() {
            addCriterion("school_id is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNotNull() {
            addCriterion("school_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdEqualTo(String value) {
            addCriterion("school_id =", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotEqualTo(String value) {
            addCriterion("school_id <>", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThan(String value) {
            addCriterion("school_id >", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThanOrEqualTo(String value) {
            addCriterion("school_id >=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThan(String value) {
            addCriterion("school_id <", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThanOrEqualTo(String value) {
            addCriterion("school_id <=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLike(String value) {
            addCriterion("school_id like", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotLike(String value) {
            addCriterion("school_id not like", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIn(List<String> values) {
            addCriterion("school_id in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotIn(List<String> values) {
            addCriterion("school_id not in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdBetween(String value1, String value2) {
            addCriterion("school_id between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotBetween(String value1, String value2) {
            addCriterion("school_id not between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSendPriceIsNull() {
            addCriterion("send_price is null");
            return (Criteria) this;
        }

        public Criteria andSendPriceIsNotNull() {
            addCriterion("send_price is not null");
            return (Criteria) this;
        }

        public Criteria andSendPriceEqualTo(BigDecimal value) {
            addCriterion("send_price =", value, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceNotEqualTo(BigDecimal value) {
            addCriterion("send_price <>", value, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceGreaterThan(BigDecimal value) {
            addCriterion("send_price >", value, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("send_price >=", value, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceLessThan(BigDecimal value) {
            addCriterion("send_price <", value, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("send_price <=", value, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceIn(List<BigDecimal> values) {
            addCriterion("send_price in", values, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceNotIn(List<BigDecimal> values) {
            addCriterion("send_price not in", values, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_price between", value1, value2, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_price not between", value1, value2, "sendPrice");
            return (Criteria) this;
        }
    }

    /**
     */
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