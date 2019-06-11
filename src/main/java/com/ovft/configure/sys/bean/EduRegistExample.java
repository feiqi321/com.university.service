package com.ovft.configure.sys.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EduRegistExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public EduRegistExample() {
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

        public Criteria andRegiststartTimeIsNull() {
            addCriterion("registstart_time is null");
            return (Criteria) this;
        }

        public Criteria andRegiststartTimeIsNotNull() {
            addCriterion("registstart_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegiststartTimeEqualTo(Date value) {
            addCriterion("registstart_time =", value, "registstartTime");
            return (Criteria) this;
        }

        public Criteria andRegiststartTimeNotEqualTo(Date value) {
            addCriterion("registstart_time <>", value, "registstartTime");
            return (Criteria) this;
        }

        public Criteria andRegiststartTimeGreaterThan(Date value) {
            addCriterion("registstart_time >", value, "registstartTime");
            return (Criteria) this;
        }

        public Criteria andRegiststartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("registstart_time >=", value, "registstartTime");
            return (Criteria) this;
        }

        public Criteria andRegiststartTimeLessThan(Date value) {
            addCriterion("registstart_time <", value, "registstartTime");
            return (Criteria) this;
        }

        public Criteria andRegiststartTimeLessThanOrEqualTo(Date value) {
            addCriterion("registstart_time <=", value, "registstartTime");
            return (Criteria) this;
        }

        public Criteria andRegiststartTimeIn(List<Date> values) {
            addCriterion("registstart_time in", values, "registstartTime");
            return (Criteria) this;
        }

        public Criteria andRegiststartTimeNotIn(List<Date> values) {
            addCriterion("registstart_time not in", values, "registstartTime");
            return (Criteria) this;
        }

        public Criteria andRegiststartTimeBetween(Date value1, Date value2) {
            addCriterion("registstart_time between", value1, value2, "registstartTime");
            return (Criteria) this;
        }

        public Criteria andRegiststartTimeNotBetween(Date value1, Date value2) {
            addCriterion("registstart_time not between", value1, value2, "registstartTime");
            return (Criteria) this;
        }

        public Criteria andRegistendTimeIsNull() {
            addCriterion("registend_time is null");
            return (Criteria) this;
        }

        public Criteria andRegistendTimeIsNotNull() {
            addCriterion("registend_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegistendTimeEqualTo(Date value) {
            addCriterion("registend_time =", value, "registendTime");
            return (Criteria) this;
        }

        public Criteria andRegistendTimeNotEqualTo(Date value) {
            addCriterion("registend_time <>", value, "registendTime");
            return (Criteria) this;
        }

        public Criteria andRegistendTimeGreaterThan(Date value) {
            addCriterion("registend_time >", value, "registendTime");
            return (Criteria) this;
        }

        public Criteria andRegistendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("registend_time >=", value, "registendTime");
            return (Criteria) this;
        }

        public Criteria andRegistendTimeLessThan(Date value) {
            addCriterion("registend_time <", value, "registendTime");
            return (Criteria) this;
        }

        public Criteria andRegistendTimeLessThanOrEqualTo(Date value) {
            addCriterion("registend_time <=", value, "registendTime");
            return (Criteria) this;
        }

        public Criteria andRegistendTimeIn(List<Date> values) {
            addCriterion("registend_time in", values, "registendTime");
            return (Criteria) this;
        }

        public Criteria andRegistendTimeNotIn(List<Date> values) {
            addCriterion("registend_time not in", values, "registendTime");
            return (Criteria) this;
        }

        public Criteria andRegistendTimeBetween(Date value1, Date value2) {
            addCriterion("registend_time between", value1, value2, "registendTime");
            return (Criteria) this;
        }

        public Criteria andRegistendTimeNotBetween(Date value1, Date value2) {
            addCriterion("registend_time not between", value1, value2, "registendTime");
            return (Criteria) this;
        }

        public Criteria andStartAgeIsNull() {
            addCriterion("start_age is null");
            return (Criteria) this;
        }

        public Criteria andStartAgeIsNotNull() {
            addCriterion("start_age is not null");
            return (Criteria) this;
        }

        public Criteria andStartAgeEqualTo(Integer value) {
            addCriterion("start_age =", value, "startAge");
            return (Criteria) this;
        }

        public Criteria andStartAgeNotEqualTo(Integer value) {
            addCriterion("start_age <>", value, "startAge");
            return (Criteria) this;
        }

        public Criteria andStartAgeGreaterThan(Integer value) {
            addCriterion("start_age >", value, "startAge");
            return (Criteria) this;
        }

        public Criteria andStartAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("start_age >=", value, "startAge");
            return (Criteria) this;
        }

        public Criteria andStartAgeLessThan(Integer value) {
            addCriterion("start_age <", value, "startAge");
            return (Criteria) this;
        }

        public Criteria andStartAgeLessThanOrEqualTo(Integer value) {
            addCriterion("start_age <=", value, "startAge");
            return (Criteria) this;
        }

        public Criteria andStartAgeIn(List<Integer> values) {
            addCriterion("start_age in", values, "startAge");
            return (Criteria) this;
        }

        public Criteria andStartAgeNotIn(List<Integer> values) {
            addCriterion("start_age not in", values, "startAge");
            return (Criteria) this;
        }

        public Criteria andStartAgeBetween(Integer value1, Integer value2) {
            addCriterion("start_age between", value1, value2, "startAge");
            return (Criteria) this;
        }

        public Criteria andStartAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("start_age not between", value1, value2, "startAge");
            return (Criteria) this;
        }

        public Criteria andEndAgeIsNull() {
            addCriterion("end_age is null");
            return (Criteria) this;
        }

        public Criteria andEndAgeIsNotNull() {
            addCriterion("end_age is not null");
            return (Criteria) this;
        }

        public Criteria andEndAgeEqualTo(Integer value) {
            addCriterion("end_age =", value, "endAge");
            return (Criteria) this;
        }

        public Criteria andEndAgeNotEqualTo(Integer value) {
            addCriterion("end_age <>", value, "endAge");
            return (Criteria) this;
        }

        public Criteria andEndAgeGreaterThan(Integer value) {
            addCriterion("end_age >", value, "endAge");
            return (Criteria) this;
        }

        public Criteria andEndAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("end_age >=", value, "endAge");
            return (Criteria) this;
        }

        public Criteria andEndAgeLessThan(Integer value) {
            addCriterion("end_age <", value, "endAge");
            return (Criteria) this;
        }

        public Criteria andEndAgeLessThanOrEqualTo(Integer value) {
            addCriterion("end_age <=", value, "endAge");
            return (Criteria) this;
        }

        public Criteria andEndAgeIn(List<Integer> values) {
            addCriterion("end_age in", values, "endAge");
            return (Criteria) this;
        }

        public Criteria andEndAgeNotIn(List<Integer> values) {
            addCriterion("end_age not in", values, "endAge");
            return (Criteria) this;
        }

        public Criteria andEndAgeBetween(Integer value1, Integer value2) {
            addCriterion("end_age between", value1, value2, "endAge");
            return (Criteria) this;
        }

        public Criteria andEndAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("end_age not between", value1, value2, "endAge");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneIsNull() {
            addCriterion("regist_category_one is null");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneIsNotNull() {
            addCriterion("regist_category_one is not null");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneEqualTo(String value) {
            addCriterion("regist_category_one =", value, "registCategoryOne");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneNotEqualTo(String value) {
            addCriterion("regist_category_one <>", value, "registCategoryOne");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneGreaterThan(String value) {
            addCriterion("regist_category_one >", value, "registCategoryOne");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneGreaterThanOrEqualTo(String value) {
            addCriterion("regist_category_one >=", value, "registCategoryOne");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneLessThan(String value) {
            addCriterion("regist_category_one <", value, "registCategoryOne");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneLessThanOrEqualTo(String value) {
            addCriterion("regist_category_one <=", value, "registCategoryOne");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneLike(String value) {
            addCriterion("regist_category_one like", value, "registCategoryOne");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneNotLike(String value) {
            addCriterion("regist_category_one not like", value, "registCategoryOne");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneIn(List<String> values) {
            addCriterion("regist_category_one in", values, "registCategoryOne");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneNotIn(List<String> values) {
            addCriterion("regist_category_one not in", values, "registCategoryOne");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneBetween(String value1, String value2) {
            addCriterion("regist_category_one between", value1, value2, "registCategoryOne");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryOneNotBetween(String value1, String value2) {
            addCriterion("regist_category_one not between", value1, value2, "registCategoryOne");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoIsNull() {
            addCriterion("regist_category_two is null");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoIsNotNull() {
            addCriterion("regist_category_two is not null");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoEqualTo(String value) {
            addCriterion("regist_category_two =", value, "registCategoryTwo");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoNotEqualTo(String value) {
            addCriterion("regist_category_two <>", value, "registCategoryTwo");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoGreaterThan(String value) {
            addCriterion("regist_category_two >", value, "registCategoryTwo");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoGreaterThanOrEqualTo(String value) {
            addCriterion("regist_category_two >=", value, "registCategoryTwo");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoLessThan(String value) {
            addCriterion("regist_category_two <", value, "registCategoryTwo");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoLessThanOrEqualTo(String value) {
            addCriterion("regist_category_two <=", value, "registCategoryTwo");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoLike(String value) {
            addCriterion("regist_category_two like", value, "registCategoryTwo");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoNotLike(String value) {
            addCriterion("regist_category_two not like", value, "registCategoryTwo");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoIn(List<String> values) {
            addCriterion("regist_category_two in", values, "registCategoryTwo");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoNotIn(List<String> values) {
            addCriterion("regist_category_two not in", values, "registCategoryTwo");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoBetween(String value1, String value2) {
            addCriterion("regist_category_two between", value1, value2, "registCategoryTwo");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryTwoNotBetween(String value1, String value2) {
            addCriterion("regist_category_two not between", value1, value2, "registCategoryTwo");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeIsNull() {
            addCriterion("regist_category_three is null");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeIsNotNull() {
            addCriterion("regist_category_three is not null");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeEqualTo(String value) {
            addCriterion("regist_category_three =", value, "registCategoryThree");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeNotEqualTo(String value) {
            addCriterion("regist_category_three <>", value, "registCategoryThree");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeGreaterThan(String value) {
            addCriterion("regist_category_three >", value, "registCategoryThree");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeGreaterThanOrEqualTo(String value) {
            addCriterion("regist_category_three >=", value, "registCategoryThree");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeLessThan(String value) {
            addCriterion("regist_category_three <", value, "registCategoryThree");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeLessThanOrEqualTo(String value) {
            addCriterion("regist_category_three <=", value, "registCategoryThree");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeLike(String value) {
            addCriterion("regist_category_three like", value, "registCategoryThree");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeNotLike(String value) {
            addCriterion("regist_category_three not like", value, "registCategoryThree");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeIn(List<String> values) {
            addCriterion("regist_category_three in", values, "registCategoryThree");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeNotIn(List<String> values) {
            addCriterion("regist_category_three not in", values, "registCategoryThree");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeBetween(String value1, String value2) {
            addCriterion("regist_category_three between", value1, value2, "registCategoryThree");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryThreeNotBetween(String value1, String value2) {
            addCriterion("regist_category_three not between", value1, value2, "registCategoryThree");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourIsNull() {
            addCriterion("regist_category_four is null");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourIsNotNull() {
            addCriterion("regist_category_four is not null");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourEqualTo(String value) {
            addCriterion("regist_category_four =", value, "registCategoryFour");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourNotEqualTo(String value) {
            addCriterion("regist_category_four <>", value, "registCategoryFour");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourGreaterThan(String value) {
            addCriterion("regist_category_four >", value, "registCategoryFour");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourGreaterThanOrEqualTo(String value) {
            addCriterion("regist_category_four >=", value, "registCategoryFour");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourLessThan(String value) {
            addCriterion("regist_category_four <", value, "registCategoryFour");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourLessThanOrEqualTo(String value) {
            addCriterion("regist_category_four <=", value, "registCategoryFour");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourLike(String value) {
            addCriterion("regist_category_four like", value, "registCategoryFour");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourNotLike(String value) {
            addCriterion("regist_category_four not like", value, "registCategoryFour");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourIn(List<String> values) {
            addCriterion("regist_category_four in", values, "registCategoryFour");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourNotIn(List<String> values) {
            addCriterion("regist_category_four not in", values, "registCategoryFour");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourBetween(String value1, String value2) {
            addCriterion("regist_category_four between", value1, value2, "registCategoryFour");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFourNotBetween(String value1, String value2) {
            addCriterion("regist_category_four not between", value1, value2, "registCategoryFour");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveIsNull() {
            addCriterion("regist_category_five is null");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveIsNotNull() {
            addCriterion("regist_category_five is not null");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveEqualTo(String value) {
            addCriterion("regist_category_five =", value, "registCategoryFive");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveNotEqualTo(String value) {
            addCriterion("regist_category_five <>", value, "registCategoryFive");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveGreaterThan(String value) {
            addCriterion("regist_category_five >", value, "registCategoryFive");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveGreaterThanOrEqualTo(String value) {
            addCriterion("regist_category_five >=", value, "registCategoryFive");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveLessThan(String value) {
            addCriterion("regist_category_five <", value, "registCategoryFive");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveLessThanOrEqualTo(String value) {
            addCriterion("regist_category_five <=", value, "registCategoryFive");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveLike(String value) {
            addCriterion("regist_category_five like", value, "registCategoryFive");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveNotLike(String value) {
            addCriterion("regist_category_five not like", value, "registCategoryFive");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveIn(List<String> values) {
            addCriterion("regist_category_five in", values, "registCategoryFive");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveNotIn(List<String> values) {
            addCriterion("regist_category_five not in", values, "registCategoryFive");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveBetween(String value1, String value2) {
            addCriterion("regist_category_five between", value1, value2, "registCategoryFive");
            return (Criteria) this;
        }

        public Criteria andRegistCategoryFiveNotBetween(String value1, String value2) {
            addCriterion("regist_category_five not between", value1, value2, "registCategoryFive");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixIsNull() {
            addCriterion("regist_category_six is null");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixIsNotNull() {
            addCriterion("regist_category_six is not null");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixEqualTo(String value) {
            addCriterion("regist_category_six =", value, "registCategorySix");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixNotEqualTo(String value) {
            addCriterion("regist_category_six <>", value, "registCategorySix");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixGreaterThan(String value) {
            addCriterion("regist_category_six >", value, "registCategorySix");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixGreaterThanOrEqualTo(String value) {
            addCriterion("regist_category_six >=", value, "registCategorySix");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixLessThan(String value) {
            addCriterion("regist_category_six <", value, "registCategorySix");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixLessThanOrEqualTo(String value) {
            addCriterion("regist_category_six <=", value, "registCategorySix");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixLike(String value) {
            addCriterion("regist_category_six like", value, "registCategorySix");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixNotLike(String value) {
            addCriterion("regist_category_six not like", value, "registCategorySix");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixIn(List<String> values) {
            addCriterion("regist_category_six in", values, "registCategorySix");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixNotIn(List<String> values) {
            addCriterion("regist_category_six not in", values, "registCategorySix");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixBetween(String value1, String value2) {
            addCriterion("regist_category_six between", value1, value2, "registCategorySix");
            return (Criteria) this;
        }

        public Criteria andRegistCategorySixNotBetween(String value1, String value2) {
            addCriterion("regist_category_six not between", value1, value2, "registCategorySix");
            return (Criteria) this;
        }

        public Criteria andUpateTimeIsNull() {
            addCriterion("upate_time is null");
            return (Criteria) this;
        }

        public Criteria andUpateTimeIsNotNull() {
            addCriterion("upate_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpateTimeEqualTo(Date value) {
            addCriterion("upate_time =", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotEqualTo(Date value) {
            addCriterion("upate_time <>", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeGreaterThan(Date value) {
            addCriterion("upate_time >", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upate_time >=", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeLessThan(Date value) {
            addCriterion("upate_time <", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeLessThanOrEqualTo(Date value) {
            addCriterion("upate_time <=", value, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeIn(List<Date> values) {
            addCriterion("upate_time in", values, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotIn(List<Date> values) {
            addCriterion("upate_time not in", values, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeBetween(Date value1, Date value2) {
            addCriterion("upate_time between", value1, value2, "upateTime");
            return (Criteria) this;
        }

        public Criteria andUpateTimeNotBetween(Date value1, Date value2) {
            addCriterion("upate_time not between", value1, value2, "upateTime");
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

        public Criteria andSchoolIdEqualTo(Integer value) {
            addCriterion("school_id =", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotEqualTo(Integer value) {
            addCriterion("school_id <>", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThan(Integer value) {
            addCriterion("school_id >", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("school_id >=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThan(Integer value) {
            addCriterion("school_id <", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThanOrEqualTo(Integer value) {
            addCriterion("school_id <=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIn(List<Integer> values) {
            addCriterion("school_id in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotIn(List<Integer> values) {
            addCriterion("school_id not in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdBetween(Integer value1, Integer value2) {
            addCriterion("school_id between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotBetween(Integer value1, Integer value2) {
            addCriterion("school_id not between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(Integer value) {
            addCriterion("course_id =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(Integer value) {
            addCriterion("course_id <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(Integer value) {
            addCriterion("course_id >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_id >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(Integer value) {
            addCriterion("course_id <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(Integer value) {
            addCriterion("course_id <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<Integer> values) {
            addCriterion("course_id in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<Integer> values) {
            addCriterion("course_id not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(Integer value1, Integer value2) {
            addCriterion("course_id between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("course_id not between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIsNull() {
            addCriterion("school_name is null");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIsNotNull() {
            addCriterion("school_name is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolNameEqualTo(String value) {
            addCriterion("school_name =", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotEqualTo(String value) {
            addCriterion("school_name <>", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameGreaterThan(String value) {
            addCriterion("school_name >", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("school_name >=", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLessThan(String value) {
            addCriterion("school_name <", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLessThanOrEqualTo(String value) {
            addCriterion("school_name <=", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameLike(String value) {
            addCriterion("school_name like", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotLike(String value) {
            addCriterion("school_name not like", value, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameIn(List<String> values) {
            addCriterion("school_name in", values, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotIn(List<String> values) {
            addCriterion("school_name not in", values, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameBetween(String value1, String value2) {
            addCriterion("school_name between", value1, value2, "schoolName");
            return (Criteria) this;
        }

        public Criteria andSchoolNameNotBetween(String value1, String value2) {
            addCriterion("school_name not between", value1, value2, "schoolName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("course_name is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("course_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("course_name =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("course_name <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("course_name >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_name >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("course_name <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("course_name <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("course_name like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("course_name not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("course_name in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("course_name not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("course_name between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("course_name not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityIsNull() {
            addCriterion("regist_priority is null");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityIsNotNull() {
            addCriterion("regist_priority is not null");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityEqualTo(String value) {
            addCriterion("regist_priority =", value, "registPriority");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityNotEqualTo(String value) {
            addCriterion("regist_priority <>", value, "registPriority");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityGreaterThan(String value) {
            addCriterion("regist_priority >", value, "registPriority");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityGreaterThanOrEqualTo(String value) {
            addCriterion("regist_priority >=", value, "registPriority");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityLessThan(String value) {
            addCriterion("regist_priority <", value, "registPriority");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityLessThanOrEqualTo(String value) {
            addCriterion("regist_priority <=", value, "registPriority");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityLike(String value) {
            addCriterion("regist_priority like", value, "registPriority");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityNotLike(String value) {
            addCriterion("regist_priority not like", value, "registPriority");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityIn(List<String> values) {
            addCriterion("regist_priority in", values, "registPriority");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityNotIn(List<String> values) {
            addCriterion("regist_priority not in", values, "registPriority");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityBetween(String value1, String value2) {
            addCriterion("regist_priority between", value1, value2, "registPriority");
            return (Criteria) this;
        }

        public Criteria andRegistPriorityNotBetween(String value1, String value2) {
            addCriterion("regist_priority not between", value1, value2, "registPriority");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistIsNull() {
            addCriterion("offline_regist is null");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistIsNotNull() {
            addCriterion("offline_regist is not null");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistEqualTo(String value) {
            addCriterion("offline_regist =", value, "offlineRegist");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistNotEqualTo(String value) {
            addCriterion("offline_regist <>", value, "offlineRegist");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistGreaterThan(String value) {
            addCriterion("offline_regist >", value, "offlineRegist");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistGreaterThanOrEqualTo(String value) {
            addCriterion("offline_regist >=", value, "offlineRegist");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistLessThan(String value) {
            addCriterion("offline_regist <", value, "offlineRegist");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistLessThanOrEqualTo(String value) {
            addCriterion("offline_regist <=", value, "offlineRegist");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistLike(String value) {
            addCriterion("offline_regist like", value, "offlineRegist");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistNotLike(String value) {
            addCriterion("offline_regist not like", value, "offlineRegist");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistIn(List<String> values) {
            addCriterion("offline_regist in", values, "offlineRegist");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistNotIn(List<String> values) {
            addCriterion("offline_regist not in", values, "offlineRegist");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistBetween(String value1, String value2) {
            addCriterion("offline_regist between", value1, value2, "offlineRegist");
            return (Criteria) this;
        }

        public Criteria andOfflineRegistNotBetween(String value1, String value2) {
            addCriterion("offline_regist not between", value1, value2, "offlineRegist");
            return (Criteria) this;
        }

        public Criteria andCourseNumIsNull() {
            addCriterion("course_num is null");
            return (Criteria) this;
        }

        public Criteria andCourseNumIsNotNull() {
            addCriterion("course_num is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNumEqualTo(Integer value) {
            addCriterion("course_num =", value, "courseNum");
            return (Criteria) this;
        }

        public Criteria andCourseNumNotEqualTo(Integer value) {
            addCriterion("course_num <>", value, "courseNum");
            return (Criteria) this;
        }

        public Criteria andCourseNumGreaterThan(Integer value) {
            addCriterion("course_num >", value, "courseNum");
            return (Criteria) this;
        }

        public Criteria andCourseNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_num >=", value, "courseNum");
            return (Criteria) this;
        }

        public Criteria andCourseNumLessThan(Integer value) {
            addCriterion("course_num <", value, "courseNum");
            return (Criteria) this;
        }

        public Criteria andCourseNumLessThanOrEqualTo(Integer value) {
            addCriterion("course_num <=", value, "courseNum");
            return (Criteria) this;
        }

        public Criteria andCourseNumIn(List<Integer> values) {
            addCriterion("course_num in", values, "courseNum");
            return (Criteria) this;
        }

        public Criteria andCourseNumNotIn(List<Integer> values) {
            addCriterion("course_num not in", values, "courseNum");
            return (Criteria) this;
        }

        public Criteria andCourseNumBetween(Integer value1, Integer value2) {
            addCriterion("course_num between", value1, value2, "courseNum");
            return (Criteria) this;
        }

        public Criteria andCourseNumNotBetween(Integer value1, Integer value2) {
            addCriterion("course_num not between", value1, value2, "courseNum");
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