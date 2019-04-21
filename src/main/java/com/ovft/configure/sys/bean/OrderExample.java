package com.ovft.configure.sys.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public OrderExample() {
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

        public Criteria andOrderSnIsNull() {
            addCriterion("order_sn is null");
            return (Criteria) this;
        }

        public Criteria andOrderSnIsNotNull() {
            addCriterion("order_sn is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSnEqualTo(String value) {
            addCriterion("order_sn =", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotEqualTo(String value) {
            addCriterion("order_sn <>", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnGreaterThan(String value) {
            addCriterion("order_sn >", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnGreaterThanOrEqualTo(String value) {
            addCriterion("order_sn >=", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnLessThan(String value) {
            addCriterion("order_sn <", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnLessThanOrEqualTo(String value) {
            addCriterion("order_sn <=", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnLike(String value) {
            addCriterion("order_sn like", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotLike(String value) {
            addCriterion("order_sn not like", value, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnIn(List<String> values) {
            addCriterion("order_sn in", values, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotIn(List<String> values) {
            addCriterion("order_sn not in", values, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnBetween(String value1, String value2) {
            addCriterion("order_sn between", value1, value2, "orderSn");
            return (Criteria) this;
        }

        public Criteria andOrderSnNotBetween(String value1, String value2) {
            addCriterion("order_sn not between", value1, value2, "orderSn");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Integer value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Integer value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Integer value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Integer value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Integer> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Integer> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNull() {
            addCriterion("expire_time is null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNotNull() {
            addCriterion("expire_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeEqualTo(Date value) {
            addCriterion("expire_time =", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotEqualTo(Date value) {
            addCriterion("expire_time <>", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThan(Date value) {
            addCriterion("expire_time >", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expire_time >=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThan(Date value) {
            addCriterion("expire_time <", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThanOrEqualTo(Date value) {
            addCriterion("expire_time <=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIn(List<Date> values) {
            addCriterion("expire_time in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotIn(List<Date> values) {
            addCriterion("expire_time not in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeBetween(Date value1, Date value2) {
            addCriterion("expire_time between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotBetween(Date value1, Date value2) {
            addCriterion("expire_time not between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeIsNull() {
            addCriterion("payment_time is null");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeIsNotNull() {
            addCriterion("payment_time is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeEqualTo(Date value) {
            addCriterion("payment_time =", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeNotEqualTo(Date value) {
            addCriterion("payment_time <>", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeGreaterThan(Date value) {
            addCriterion("payment_time >", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("payment_time >=", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeLessThan(Date value) {
            addCriterion("payment_time <", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeLessThanOrEqualTo(Date value) {
            addCriterion("payment_time <=", value, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeIn(List<Date> values) {
            addCriterion("payment_time in", values, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeNotIn(List<Date> values) {
            addCriterion("payment_time not in", values, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeBetween(Date value1, Date value2) {
            addCriterion("payment_time between", value1, value2, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andPaymentTimeNotBetween(Date value1, Date value2) {
            addCriterion("payment_time not between", value1, value2, "paymentTime");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNull() {
            addCriterion("total_amount is null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIsNotNull() {
            addCriterion("total_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalAmountEqualTo(Long value) {
            addCriterion("total_amount =", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotEqualTo(Long value) {
            addCriterion("total_amount <>", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThan(Long value) {
            addCriterion("total_amount >", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("total_amount >=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThan(Long value) {
            addCriterion("total_amount <", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountLessThanOrEqualTo(Long value) {
            addCriterion("total_amount <=", value, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountIn(List<Long> values) {
            addCriterion("total_amount in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotIn(List<Long> values) {
            addCriterion("total_amount not in", values, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountBetween(Long value1, Long value2) {
            addCriterion("total_amount between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andTotalAmountNotBetween(Long value1, Long value2) {
            addCriterion("total_amount not between", value1, value2, "totalAmount");
            return (Criteria) this;
        }

        public Criteria andPaymentWayIsNull() {
            addCriterion("payment_way is null");
            return (Criteria) this;
        }

        public Criteria andPaymentWayIsNotNull() {
            addCriterion("payment_way is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentWayEqualTo(String value) {
            addCriterion("payment_way =", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayNotEqualTo(String value) {
            addCriterion("payment_way <>", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayGreaterThan(String value) {
            addCriterion("payment_way >", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayGreaterThanOrEqualTo(String value) {
            addCriterion("payment_way >=", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayLessThan(String value) {
            addCriterion("payment_way <", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayLessThanOrEqualTo(String value) {
            addCriterion("payment_way <=", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayLike(String value) {
            addCriterion("payment_way like", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayNotLike(String value) {
            addCriterion("payment_way not like", value, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayIn(List<String> values) {
            addCriterion("payment_way in", values, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayNotIn(List<String> values) {
            addCriterion("payment_way not in", values, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayBetween(String value1, String value2) {
            addCriterion("payment_way between", value1, value2, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andPaymentWayNotBetween(String value1, String value2) {
            addCriterion("payment_way not between", value1, value2, "paymentWay");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoIsNull() {
            addCriterion("out_trade_no is null");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoIsNotNull() {
            addCriterion("out_trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoEqualTo(String value) {
            addCriterion("out_trade_no =", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoNotEqualTo(String value) {
            addCriterion("out_trade_no <>", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoGreaterThan(String value) {
            addCriterion("out_trade_no >", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoGreaterThanOrEqualTo(String value) {
            addCriterion("out_trade_no >=", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoLessThan(String value) {
            addCriterion("out_trade_no <", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoLessThanOrEqualTo(String value) {
            addCriterion("out_trade_no <=", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoLike(String value) {
            addCriterion("out_trade_no like", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoNotLike(String value) {
            addCriterion("out_trade_no not like", value, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoIn(List<String> values) {
            addCriterion("out_trade_no in", values, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoNotIn(List<String> values) {
            addCriterion("out_trade_no not in", values, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoBetween(String value1, String value2) {
            addCriterion("out_trade_no between", value1, value2, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andOutTradeNoNotBetween(String value1, String value2) {
            addCriterion("out_trade_no not between", value1, value2, "outTradeNo");
            return (Criteria) this;
        }

        public Criteria andTradeBodyIsNull() {
            addCriterion("trade_body is null");
            return (Criteria) this;
        }

        public Criteria andTradeBodyIsNotNull() {
            addCriterion("trade_body is not null");
            return (Criteria) this;
        }

        public Criteria andTradeBodyEqualTo(String value) {
            addCriterion("trade_body =", value, "tradeBody");
            return (Criteria) this;
        }

        public Criteria andTradeBodyNotEqualTo(String value) {
            addCriterion("trade_body <>", value, "tradeBody");
            return (Criteria) this;
        }

        public Criteria andTradeBodyGreaterThan(String value) {
            addCriterion("trade_body >", value, "tradeBody");
            return (Criteria) this;
        }

        public Criteria andTradeBodyGreaterThanOrEqualTo(String value) {
            addCriterion("trade_body >=", value, "tradeBody");
            return (Criteria) this;
        }

        public Criteria andTradeBodyLessThan(String value) {
            addCriterion("trade_body <", value, "tradeBody");
            return (Criteria) this;
        }

        public Criteria andTradeBodyLessThanOrEqualTo(String value) {
            addCriterion("trade_body <=", value, "tradeBody");
            return (Criteria) this;
        }

        public Criteria andTradeBodyLike(String value) {
            addCriterion("trade_body like", value, "tradeBody");
            return (Criteria) this;
        }

        public Criteria andTradeBodyNotLike(String value) {
            addCriterion("trade_body not like", value, "tradeBody");
            return (Criteria) this;
        }

        public Criteria andTradeBodyIn(List<String> values) {
            addCriterion("trade_body in", values, "tradeBody");
            return (Criteria) this;
        }

        public Criteria andTradeBodyNotIn(List<String> values) {
            addCriterion("trade_body not in", values, "tradeBody");
            return (Criteria) this;
        }

        public Criteria andTradeBodyBetween(String value1, String value2) {
            addCriterion("trade_body between", value1, value2, "tradeBody");
            return (Criteria) this;
        }

        public Criteria andTradeBodyNotBetween(String value1, String value2) {
            addCriterion("trade_body not between", value1, value2, "tradeBody");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNull() {
            addCriterion("img_url is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNotNull() {
            addCriterion("img_url is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlEqualTo(String value) {
            addCriterion("img_url =", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotEqualTo(String value) {
            addCriterion("img_url <>", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThan(String value) {
            addCriterion("img_url >", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("img_url >=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThan(String value) {
            addCriterion("img_url <", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThanOrEqualTo(String value) {
            addCriterion("img_url <=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLike(String value) {
            addCriterion("img_url like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotLike(String value) {
            addCriterion("img_url not like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlIn(List<String> values) {
            addCriterion("img_url in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotIn(List<String> values) {
            addCriterion("img_url not in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlBetween(String value1, String value2) {
            addCriterion("img_url between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotBetween(String value1, String value2) {
            addCriterion("img_url not between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andConsigneeIsNull() {
            addCriterion("consignee is null");
            return (Criteria) this;
        }

        public Criteria andConsigneeIsNotNull() {
            addCriterion("consignee is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneeEqualTo(String value) {
            addCriterion("consignee =", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotEqualTo(String value) {
            addCriterion("consignee <>", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeGreaterThan(String value) {
            addCriterion("consignee >", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeGreaterThanOrEqualTo(String value) {
            addCriterion("consignee >=", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLessThan(String value) {
            addCriterion("consignee <", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLessThanOrEqualTo(String value) {
            addCriterion("consignee <=", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLike(String value) {
            addCriterion("consignee like", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotLike(String value) {
            addCriterion("consignee not like", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeIn(List<String> values) {
            addCriterion("consignee in", values, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotIn(List<String> values) {
            addCriterion("consignee not in", values, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeBetween(String value1, String value2) {
            addCriterion("consignee between", value1, value2, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotBetween(String value1, String value2) {
            addCriterion("consignee not between", value1, value2, "consignee");
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