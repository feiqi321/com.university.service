package com.ovft.configure.sys.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EduOfflineAddresstimeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public EduOfflineAddresstimeExample() {
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

        public Criteria andPaymentAddressIsNull() {
            addCriterion("payment_address is null");
            return (Criteria) this;
        }

        public Criteria andPaymentAddressIsNotNull() {
            addCriterion("payment_address is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentAddressEqualTo(String value) {
            addCriterion("payment_address =", value, "paymentAddress");
            return (Criteria) this;
        }

        public Criteria andPaymentAddressNotEqualTo(String value) {
            addCriterion("payment_address <>", value, "paymentAddress");
            return (Criteria) this;
        }

        public Criteria andPaymentAddressGreaterThan(String value) {
            addCriterion("payment_address >", value, "paymentAddress");
            return (Criteria) this;
        }

        public Criteria andPaymentAddressGreaterThanOrEqualTo(String value) {
            addCriterion("payment_address >=", value, "paymentAddress");
            return (Criteria) this;
        }

        public Criteria andPaymentAddressLessThan(String value) {
            addCriterion("payment_address <", value, "paymentAddress");
            return (Criteria) this;
        }

        public Criteria andPaymentAddressLessThanOrEqualTo(String value) {
            addCriterion("payment_address <=", value, "paymentAddress");
            return (Criteria) this;
        }

        public Criteria andPaymentAddressLike(String value) {
            addCriterion("payment_address like", value, "paymentAddress");
            return (Criteria) this;
        }

        public Criteria andPaymentAddressNotLike(String value) {
            addCriterion("payment_address not like", value, "paymentAddress");
            return (Criteria) this;
        }

        public Criteria andPaymentAddressIn(List<String> values) {
            addCriterion("payment_address in", values, "paymentAddress");
            return (Criteria) this;
        }

        public Criteria andPaymentAddressNotIn(List<String> values) {
            addCriterion("payment_address not in", values, "paymentAddress");
            return (Criteria) this;
        }

        public Criteria andPaymentAddressBetween(String value1, String value2) {
            addCriterion("payment_address between", value1, value2, "paymentAddress");
            return (Criteria) this;
        }

        public Criteria andPaymentAddressNotBetween(String value1, String value2) {
            addCriterion("payment_address not between", value1, value2, "paymentAddress");
            return (Criteria) this;
        }

        public Criteria andPaymentStarttimeIsNull() {
            addCriterion("payment_starttime is null");
            return (Criteria) this;
        }

        public Criteria andPaymentStarttimeIsNotNull() {
            addCriterion("payment_starttime is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentStarttimeEqualTo(Date value) {
            addCriterion("payment_starttime =", value, "paymentStarttime");
            return (Criteria) this;
        }

        public Criteria andPaymentStarttimeNotEqualTo(Date value) {
            addCriterion("payment_starttime <>", value, "paymentStarttime");
            return (Criteria) this;
        }

        public Criteria andPaymentStarttimeGreaterThan(Date value) {
            addCriterion("payment_starttime >", value, "paymentStarttime");
            return (Criteria) this;
        }

        public Criteria andPaymentStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("payment_starttime >=", value, "paymentStarttime");
            return (Criteria) this;
        }

        public Criteria andPaymentStarttimeLessThan(Date value) {
            addCriterion("payment_starttime <", value, "paymentStarttime");
            return (Criteria) this;
        }

        public Criteria andPaymentStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("payment_starttime <=", value, "paymentStarttime");
            return (Criteria) this;
        }

        public Criteria andPaymentStarttimeIn(List<Date> values) {
            addCriterion("payment_starttime in", values, "paymentStarttime");
            return (Criteria) this;
        }

        public Criteria andPaymentStarttimeNotIn(List<Date> values) {
            addCriterion("payment_starttime not in", values, "paymentStarttime");
            return (Criteria) this;
        }

        public Criteria andPaymentStarttimeBetween(Date value1, Date value2) {
            addCriterion("payment_starttime between", value1, value2, "paymentStarttime");
            return (Criteria) this;
        }

        public Criteria andPaymentStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("payment_starttime not between", value1, value2, "paymentStarttime");
            return (Criteria) this;
        }

        public Criteria andPaymentEndtimeIsNull() {
            addCriterion("payment_endtime is null");
            return (Criteria) this;
        }

        public Criteria andPaymentEndtimeIsNotNull() {
            addCriterion("payment_endtime is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentEndtimeEqualTo(Date value) {
            addCriterion("payment_endtime =", value, "paymentEndtime");
            return (Criteria) this;
        }

        public Criteria andPaymentEndtimeNotEqualTo(Date value) {
            addCriterion("payment_endtime <>", value, "paymentEndtime");
            return (Criteria) this;
        }

        public Criteria andPaymentEndtimeGreaterThan(Date value) {
            addCriterion("payment_endtime >", value, "paymentEndtime");
            return (Criteria) this;
        }

        public Criteria andPaymentEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("payment_endtime >=", value, "paymentEndtime");
            return (Criteria) this;
        }

        public Criteria andPaymentEndtimeLessThan(Date value) {
            addCriterion("payment_endtime <", value, "paymentEndtime");
            return (Criteria) this;
        }

        public Criteria andPaymentEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("payment_endtime <=", value, "paymentEndtime");
            return (Criteria) this;
        }

        public Criteria andPaymentEndtimeIn(List<Date> values) {
            addCriterion("payment_endtime in", values, "paymentEndtime");
            return (Criteria) this;
        }

        public Criteria andPaymentEndtimeNotIn(List<Date> values) {
            addCriterion("payment_endtime not in", values, "paymentEndtime");
            return (Criteria) this;
        }

        public Criteria andPaymentEndtimeBetween(Date value1, Date value2) {
            addCriterion("payment_endtime between", value1, value2, "paymentEndtime");
            return (Criteria) this;
        }

        public Criteria andPaymentEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("payment_endtime not between", value1, value2, "paymentEndtime");
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