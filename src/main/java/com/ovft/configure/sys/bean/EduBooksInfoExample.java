package com.ovft.configure.sys.bean;

import java.util.ArrayList;
import java.util.List;

public class EduBooksInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public EduBooksInfoExample() {
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

        public Criteria andBookGoodsIdIsNull() {
            addCriterion("book_goods_id is null");
            return (Criteria) this;
        }

        public Criteria andBookGoodsIdIsNotNull() {
            addCriterion("book_goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookGoodsIdEqualTo(Integer value) {
            addCriterion("book_goods_id =", value, "bookGoodsId");
            return (Criteria) this;
        }

        public Criteria andBookGoodsIdNotEqualTo(Integer value) {
            addCriterion("book_goods_id <>", value, "bookGoodsId");
            return (Criteria) this;
        }

        public Criteria andBookGoodsIdGreaterThan(Integer value) {
            addCriterion("book_goods_id >", value, "bookGoodsId");
            return (Criteria) this;
        }

        public Criteria andBookGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_goods_id >=", value, "bookGoodsId");
            return (Criteria) this;
        }

        public Criteria andBookGoodsIdLessThan(Integer value) {
            addCriterion("book_goods_id <", value, "bookGoodsId");
            return (Criteria) this;
        }

        public Criteria andBookGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("book_goods_id <=", value, "bookGoodsId");
            return (Criteria) this;
        }

        public Criteria andBookGoodsIdIn(List<Integer> values) {
            addCriterion("book_goods_id in", values, "bookGoodsId");
            return (Criteria) this;
        }

        public Criteria andBookGoodsIdNotIn(List<Integer> values) {
            addCriterion("book_goods_id not in", values, "bookGoodsId");
            return (Criteria) this;
        }

        public Criteria andBookGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("book_goods_id between", value1, value2, "bookGoodsId");
            return (Criteria) this;
        }

        public Criteria andBookGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("book_goods_id not between", value1, value2, "bookGoodsId");
            return (Criteria) this;
        }

        public Criteria andPublishAddressIsNull() {
            addCriterion("publish_address is null");
            return (Criteria) this;
        }

        public Criteria andPublishAddressIsNotNull() {
            addCriterion("publish_address is not null");
            return (Criteria) this;
        }

        public Criteria andPublishAddressEqualTo(String value) {
            addCriterion("publish_address =", value, "publishAddress");
            return (Criteria) this;
        }

        public Criteria andPublishAddressNotEqualTo(String value) {
            addCriterion("publish_address <>", value, "publishAddress");
            return (Criteria) this;
        }

        public Criteria andPublishAddressGreaterThan(String value) {
            addCriterion("publish_address >", value, "publishAddress");
            return (Criteria) this;
        }

        public Criteria andPublishAddressGreaterThanOrEqualTo(String value) {
            addCriterion("publish_address >=", value, "publishAddress");
            return (Criteria) this;
        }

        public Criteria andPublishAddressLessThan(String value) {
            addCriterion("publish_address <", value, "publishAddress");
            return (Criteria) this;
        }

        public Criteria andPublishAddressLessThanOrEqualTo(String value) {
            addCriterion("publish_address <=", value, "publishAddress");
            return (Criteria) this;
        }

        public Criteria andPublishAddressLike(String value) {
            addCriterion("publish_address like", value, "publishAddress");
            return (Criteria) this;
        }

        public Criteria andPublishAddressNotLike(String value) {
            addCriterion("publish_address not like", value, "publishAddress");
            return (Criteria) this;
        }

        public Criteria andPublishAddressIn(List<String> values) {
            addCriterion("publish_address in", values, "publishAddress");
            return (Criteria) this;
        }

        public Criteria andPublishAddressNotIn(List<String> values) {
            addCriterion("publish_address not in", values, "publishAddress");
            return (Criteria) this;
        }

        public Criteria andPublishAddressBetween(String value1, String value2) {
            addCriterion("publish_address between", value1, value2, "publishAddress");
            return (Criteria) this;
        }

        public Criteria andPublishAddressNotBetween(String value1, String value2) {
            addCriterion("publish_address not between", value1, value2, "publishAddress");
            return (Criteria) this;
        }

        public Criteria andFormatBookIsNull() {
            addCriterion("format_book is null");
            return (Criteria) this;
        }

        public Criteria andFormatBookIsNotNull() {
            addCriterion("format_book is not null");
            return (Criteria) this;
        }

        public Criteria andFormatBookEqualTo(String value) {
            addCriterion("format_book =", value, "formatBook");
            return (Criteria) this;
        }

        public Criteria andFormatBookNotEqualTo(String value) {
            addCriterion("format_book <>", value, "formatBook");
            return (Criteria) this;
        }

        public Criteria andFormatBookGreaterThan(String value) {
            addCriterion("format_book >", value, "formatBook");
            return (Criteria) this;
        }

        public Criteria andFormatBookGreaterThanOrEqualTo(String value) {
            addCriterion("format_book >=", value, "formatBook");
            return (Criteria) this;
        }

        public Criteria andFormatBookLessThan(String value) {
            addCriterion("format_book <", value, "formatBook");
            return (Criteria) this;
        }

        public Criteria andFormatBookLessThanOrEqualTo(String value) {
            addCriterion("format_book <=", value, "formatBook");
            return (Criteria) this;
        }

        public Criteria andFormatBookLike(String value) {
            addCriterion("format_book like", value, "formatBook");
            return (Criteria) this;
        }

        public Criteria andFormatBookNotLike(String value) {
            addCriterion("format_book not like", value, "formatBook");
            return (Criteria) this;
        }

        public Criteria andFormatBookIn(List<String> values) {
            addCriterion("format_book in", values, "formatBook");
            return (Criteria) this;
        }

        public Criteria andFormatBookNotIn(List<String> values) {
            addCriterion("format_book not in", values, "formatBook");
            return (Criteria) this;
        }

        public Criteria andFormatBookBetween(String value1, String value2) {
            addCriterion("format_book between", value1, value2, "formatBook");
            return (Criteria) this;
        }

        public Criteria andFormatBookNotBetween(String value1, String value2) {
            addCriterion("format_book not between", value1, value2, "formatBook");
            return (Criteria) this;
        }

        public Criteria andEditionBookIsNull() {
            addCriterion("edition_book is null");
            return (Criteria) this;
        }

        public Criteria andEditionBookIsNotNull() {
            addCriterion("edition_book is not null");
            return (Criteria) this;
        }

        public Criteria andEditionBookEqualTo(String value) {
            addCriterion("edition_book =", value, "editionBook");
            return (Criteria) this;
        }

        public Criteria andEditionBookNotEqualTo(String value) {
            addCriterion("edition_book <>", value, "editionBook");
            return (Criteria) this;
        }

        public Criteria andEditionBookGreaterThan(String value) {
            addCriterion("edition_book >", value, "editionBook");
            return (Criteria) this;
        }

        public Criteria andEditionBookGreaterThanOrEqualTo(String value) {
            addCriterion("edition_book >=", value, "editionBook");
            return (Criteria) this;
        }

        public Criteria andEditionBookLessThan(String value) {
            addCriterion("edition_book <", value, "editionBook");
            return (Criteria) this;
        }

        public Criteria andEditionBookLessThanOrEqualTo(String value) {
            addCriterion("edition_book <=", value, "editionBook");
            return (Criteria) this;
        }

        public Criteria andEditionBookLike(String value) {
            addCriterion("edition_book like", value, "editionBook");
            return (Criteria) this;
        }

        public Criteria andEditionBookNotLike(String value) {
            addCriterion("edition_book not like", value, "editionBook");
            return (Criteria) this;
        }

        public Criteria andEditionBookIn(List<String> values) {
            addCriterion("edition_book in", values, "editionBook");
            return (Criteria) this;
        }

        public Criteria andEditionBookNotIn(List<String> values) {
            addCriterion("edition_book not in", values, "editionBook");
            return (Criteria) this;
        }

        public Criteria andEditionBookBetween(String value1, String value2) {
            addCriterion("edition_book between", value1, value2, "editionBook");
            return (Criteria) this;
        }

        public Criteria andEditionBookNotBetween(String value1, String value2) {
            addCriterion("edition_book not between", value1, value2, "editionBook");
            return (Criteria) this;
        }

        public Criteria andPageSizeIsNull() {
            addCriterion("page_size is null");
            return (Criteria) this;
        }

        public Criteria andPageSizeIsNotNull() {
            addCriterion("page_size is not null");
            return (Criteria) this;
        }

        public Criteria andPageSizeEqualTo(Double value) {
            addCriterion("page_size =", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotEqualTo(Double value) {
            addCriterion("page_size <>", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeGreaterThan(Double value) {
            addCriterion("page_size >", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeGreaterThanOrEqualTo(Double value) {
            addCriterion("page_size >=", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeLessThan(Double value) {
            addCriterion("page_size <", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeLessThanOrEqualTo(Double value) {
            addCriterion("page_size <=", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeIn(List<Double> values) {
            addCriterion("page_size in", values, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotIn(List<Double> values) {
            addCriterion("page_size not in", values, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeBetween(Double value1, Double value2) {
            addCriterion("page_size between", value1, value2, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotBetween(Double value1, Double value2) {
            addCriterion("page_size not between", value1, value2, "pageSize");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitIsNull() {
            addCriterion("recommend_unit is null");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitIsNotNull() {
            addCriterion("recommend_unit is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitEqualTo(String value) {
            addCriterion("recommend_unit =", value, "recommendUnit");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitNotEqualTo(String value) {
            addCriterion("recommend_unit <>", value, "recommendUnit");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitGreaterThan(String value) {
            addCriterion("recommend_unit >", value, "recommendUnit");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitGreaterThanOrEqualTo(String value) {
            addCriterion("recommend_unit >=", value, "recommendUnit");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitLessThan(String value) {
            addCriterion("recommend_unit <", value, "recommendUnit");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitLessThanOrEqualTo(String value) {
            addCriterion("recommend_unit <=", value, "recommendUnit");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitLike(String value) {
            addCriterion("recommend_unit like", value, "recommendUnit");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitNotLike(String value) {
            addCriterion("recommend_unit not like", value, "recommendUnit");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitIn(List<String> values) {
            addCriterion("recommend_unit in", values, "recommendUnit");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitNotIn(List<String> values) {
            addCriterion("recommend_unit not in", values, "recommendUnit");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitBetween(String value1, String value2) {
            addCriterion("recommend_unit between", value1, value2, "recommendUnit");
            return (Criteria) this;
        }

        public Criteria andRecommendUnitNotBetween(String value1, String value2) {
            addCriterion("recommend_unit not between", value1, value2, "recommendUnit");
            return (Criteria) this;
        }

        public Criteria andBookSnIsNull() {
            addCriterion("book_sn is null");
            return (Criteria) this;
        }

        public Criteria andBookSnIsNotNull() {
            addCriterion("book_sn is not null");
            return (Criteria) this;
        }

        public Criteria andBookSnEqualTo(String value) {
            addCriterion("book_sn =", value, "bookSn");
            return (Criteria) this;
        }

        public Criteria andBookSnNotEqualTo(String value) {
            addCriterion("book_sn <>", value, "bookSn");
            return (Criteria) this;
        }

        public Criteria andBookSnGreaterThan(String value) {
            addCriterion("book_sn >", value, "bookSn");
            return (Criteria) this;
        }

        public Criteria andBookSnGreaterThanOrEqualTo(String value) {
            addCriterion("book_sn >=", value, "bookSn");
            return (Criteria) this;
        }

        public Criteria andBookSnLessThan(String value) {
            addCriterion("book_sn <", value, "bookSn");
            return (Criteria) this;
        }

        public Criteria andBookSnLessThanOrEqualTo(String value) {
            addCriterion("book_sn <=", value, "bookSn");
            return (Criteria) this;
        }

        public Criteria andBookSnLike(String value) {
            addCriterion("book_sn like", value, "bookSn");
            return (Criteria) this;
        }

        public Criteria andBookSnNotLike(String value) {
            addCriterion("book_sn not like", value, "bookSn");
            return (Criteria) this;
        }

        public Criteria andBookSnIn(List<String> values) {
            addCriterion("book_sn in", values, "bookSn");
            return (Criteria) this;
        }

        public Criteria andBookSnNotIn(List<String> values) {
            addCriterion("book_sn not in", values, "bookSn");
            return (Criteria) this;
        }

        public Criteria andBookSnBetween(String value1, String value2) {
            addCriterion("book_sn between", value1, value2, "bookSn");
            return (Criteria) this;
        }

        public Criteria andBookSnNotBetween(String value1, String value2) {
            addCriterion("book_sn not between", value1, value2, "bookSn");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteIsNull() {
            addCriterion("describe_note is null");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteIsNotNull() {
            addCriterion("describe_note is not null");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteEqualTo(String value) {
            addCriterion("describe_note =", value, "describeNote");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteNotEqualTo(String value) {
            addCriterion("describe_note <>", value, "describeNote");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteGreaterThan(String value) {
            addCriterion("describe_note >", value, "describeNote");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteGreaterThanOrEqualTo(String value) {
            addCriterion("describe_note >=", value, "describeNote");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteLessThan(String value) {
            addCriterion("describe_note <", value, "describeNote");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteLessThanOrEqualTo(String value) {
            addCriterion("describe_note <=", value, "describeNote");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteLike(String value) {
            addCriterion("describe_note like", value, "describeNote");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteNotLike(String value) {
            addCriterion("describe_note not like", value, "describeNote");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteIn(List<String> values) {
            addCriterion("describe_note in", values, "describeNote");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteNotIn(List<String> values) {
            addCriterion("describe_note not in", values, "describeNote");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteBetween(String value1, String value2) {
            addCriterion("describe_note between", value1, value2, "describeNote");
            return (Criteria) this;
        }

        public Criteria andDescribeNoteNotBetween(String value1, String value2) {
            addCriterion("describe_note not between", value1, value2, "describeNote");
            return (Criteria) this;
        }

        public Criteria andDescribeBookIsNull() {
            addCriterion("describe_book is null");
            return (Criteria) this;
        }

        public Criteria andDescribeBookIsNotNull() {
            addCriterion("describe_book is not null");
            return (Criteria) this;
        }

        public Criteria andDescribeBookEqualTo(String value) {
            addCriterion("describe_book =", value, "describeBook");
            return (Criteria) this;
        }

        public Criteria andDescribeBookNotEqualTo(String value) {
            addCriterion("describe_book <>", value, "describeBook");
            return (Criteria) this;
        }

        public Criteria andDescribeBookGreaterThan(String value) {
            addCriterion("describe_book >", value, "describeBook");
            return (Criteria) this;
        }

        public Criteria andDescribeBookGreaterThanOrEqualTo(String value) {
            addCriterion("describe_book >=", value, "describeBook");
            return (Criteria) this;
        }

        public Criteria andDescribeBookLessThan(String value) {
            addCriterion("describe_book <", value, "describeBook");
            return (Criteria) this;
        }

        public Criteria andDescribeBookLessThanOrEqualTo(String value) {
            addCriterion("describe_book <=", value, "describeBook");
            return (Criteria) this;
        }

        public Criteria andDescribeBookLike(String value) {
            addCriterion("describe_book like", value, "describeBook");
            return (Criteria) this;
        }

        public Criteria andDescribeBookNotLike(String value) {
            addCriterion("describe_book not like", value, "describeBook");
            return (Criteria) this;
        }

        public Criteria andDescribeBookIn(List<String> values) {
            addCriterion("describe_book in", values, "describeBook");
            return (Criteria) this;
        }

        public Criteria andDescribeBookNotIn(List<String> values) {
            addCriterion("describe_book not in", values, "describeBook");
            return (Criteria) this;
        }

        public Criteria andDescribeBookBetween(String value1, String value2) {
            addCriterion("describe_book between", value1, value2, "describeBook");
            return (Criteria) this;
        }

        public Criteria andDescribeBookNotBetween(String value1, String value2) {
            addCriterion("describe_book not between", value1, value2, "describeBook");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorIsNull() {
            addCriterion("\" introduction_author\" is null");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorIsNotNull() {
            addCriterion("\" introduction_author\" is not null");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorEqualTo(String value) {
            addCriterion("\" introduction_author\" =", value, "introductionAuthor");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorNotEqualTo(String value) {
            addCriterion("\" introduction_author\" <>", value, "introductionAuthor");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorGreaterThan(String value) {
            addCriterion("\" introduction_author\" >", value, "introductionAuthor");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("\" introduction_author\" >=", value, "introductionAuthor");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorLessThan(String value) {
            addCriterion("\" introduction_author\" <", value, "introductionAuthor");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorLessThanOrEqualTo(String value) {
            addCriterion("\" introduction_author\" <=", value, "introductionAuthor");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorLike(String value) {
            addCriterion("\" introduction_author\" like", value, "introductionAuthor");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorNotLike(String value) {
            addCriterion("\" introduction_author\" not like", value, "introductionAuthor");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorIn(List<String> values) {
            addCriterion("\" introduction_author\" in", values, "introductionAuthor");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorNotIn(List<String> values) {
            addCriterion("\" introduction_author\" not in", values, "introductionAuthor");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorBetween(String value1, String value2) {
            addCriterion("\" introduction_author\" between", value1, value2, "introductionAuthor");
            return (Criteria) this;
        }

        public Criteria andIntroductionAuthorNotBetween(String value1, String value2) {
            addCriterion("\" introduction_author\" not between", value1, value2, "introductionAuthor");
            return (Criteria) this;
        }

        public Criteria andFreightPirceIsNull() {
            addCriterion("freight_pirce is null");
            return (Criteria) this;
        }

        public Criteria andFreightPirceIsNotNull() {
            addCriterion("freight_pirce is not null");
            return (Criteria) this;
        }

        public Criteria andFreightPirceEqualTo(String value) {
            addCriterion("freight_pirce =", value, "freightPirce");
            return (Criteria) this;
        }

        public Criteria andFreightPirceNotEqualTo(String value) {
            addCriterion("freight_pirce <>", value, "freightPirce");
            return (Criteria) this;
        }

        public Criteria andFreightPirceGreaterThan(String value) {
            addCriterion("freight_pirce >", value, "freightPirce");
            return (Criteria) this;
        }

        public Criteria andFreightPirceGreaterThanOrEqualTo(String value) {
            addCriterion("freight_pirce >=", value, "freightPirce");
            return (Criteria) this;
        }

        public Criteria andFreightPirceLessThan(String value) {
            addCriterion("freight_pirce <", value, "freightPirce");
            return (Criteria) this;
        }

        public Criteria andFreightPirceLessThanOrEqualTo(String value) {
            addCriterion("freight_pirce <=", value, "freightPirce");
            return (Criteria) this;
        }

        public Criteria andFreightPirceLike(String value) {
            addCriterion("freight_pirce like", value, "freightPirce");
            return (Criteria) this;
        }

        public Criteria andFreightPirceNotLike(String value) {
            addCriterion("freight_pirce not like", value, "freightPirce");
            return (Criteria) this;
        }

        public Criteria andFreightPirceIn(List<String> values) {
            addCriterion("freight_pirce in", values, "freightPirce");
            return (Criteria) this;
        }

        public Criteria andFreightPirceNotIn(List<String> values) {
            addCriterion("freight_pirce not in", values, "freightPirce");
            return (Criteria) this;
        }

        public Criteria andFreightPirceBetween(String value1, String value2) {
            addCriterion("freight_pirce between", value1, value2, "freightPirce");
            return (Criteria) this;
        }

        public Criteria andFreightPirceNotBetween(String value1, String value2) {
            addCriterion("freight_pirce not between", value1, value2, "freightPirce");
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