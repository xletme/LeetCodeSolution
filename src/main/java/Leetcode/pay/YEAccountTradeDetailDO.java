package Leetcode.pay;

/**
 * @author maoxin
 * @version 1.0
 * @description:
 * @date 2022/8/22 19:16
 */
public class YEAccountTradeDetailDO {

    private String seqNo;

    private String buyerTranNetMemberCode;

    private String buyerSubAccountNo;

    private String balanceAmount;

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getBuyerTranNetMemberCode() {
        return buyerTranNetMemberCode;
    }

    public void setBuyerTranNetMemberCode(String buyerTranNetMemberCode) {
        this.buyerTranNetMemberCode = buyerTranNetMemberCode;
    }

    public String getBuyerSubAccountNo() {
        return buyerSubAccountNo;
    }

    public void setBuyerSubAccountNo(String buyerSubAccountNo) {
        this.buyerSubAccountNo = buyerSubAccountNo;
    }

    public String getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
}
