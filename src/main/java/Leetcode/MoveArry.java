package Leetcode;

import Leetcode.Tree.QueryCwPayRecordResp;
import com.google.gson.Gson;

/**
 * @Description TODO
 * @Author xiamoxin
 * @Date 2020/8/6 10:00
 **/
public class MoveArry {
    /*输入: [1,2,3,4,5,6,7] 和 k = 3
    输出: [5,6,7,1,2,3,4]*/
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int step = k % n;
        reverse(nums,0,n-1);
        reverse(nums,step,n-1);
        reverse(nums,0,step-1);
    }

    private int[] reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
        return nums;
    }

    public static void main(String[] args) {
        String str = "{\"code\":200,\"result\":true,\"message\":\"查询数据成功\",\"data\":[{\"id\":\"0958ee4d8faa4a58ba6b0839384633e6\",\"accountId\":\"10001001\",\"createId\":16267,\"createName\":\"虚拟登录客户\",\"createTime\":\"2021-09-10 12:18:02\",\"updateId\":null,\"updateName\":null,\"updateTime\":null,\"status\":\"20\",\"pkHead\":\"0958ee4d8faa4a58ba6b0839384633e6\",\"pkGroup\":null,\"pkOrgV\":null,\"pkOrg\":\"10001001\",\"vbillcode\":\"SK2109000793\",\"busitype\":null,\"pkBilltypecode\":null,\"pkBilltypeid\":null,\"vtrantypecode\":null,\"ctrantypeid\":null,\"dbilldate\":\"2021-09-10\",\"fstatusflag\":1,\"vnote\":\"\",\"def1\":\"\",\"def2\":\"N\",\"def11\":\"\",\"def12\":\"\",\"csrcid\":\"1001A2100000002HS4EX\",\"csrcbid\":null,\"csrctype\":null,\"direction\":\"收款通知\",\"ispj\":\"N\",\"bankaccount\":\"22844101040022978\",\"bankaccountcode\":null,\"bankid\":null,\"bankcode\":\"103651084413\",\"infodate\":\"2021-09-10\",\"infodatetime\":\"00:00:00\",\"memo\":\"货款\",\"pkCurrtype\":\"RMB\",\"olcrate\":null,\"oppunitcode\":\"m0004682\",\"oppunitname\":\"成都博翔荣贸易有限公司\",\"oppbankaccount\":\"835501040008367\",\"bankrelatedCode\":null,\"noteNo\":null,\"gatherdate\":\"\",\"fbmbillno\":\"\",\"fbmbilltype\":\"\",\"payunit\":\"\",\"paybankacc\":\"\",\"receiveunit\":\"\",\"receivebankacc\":\"\",\"receivebank\":\"\",\"invoiceunit\":\"\",\"invoicedate\":\"\",\"enddate\":\"\",\"money\":1000000.00,\"wrlmoney\":1000000.00,\"receiveAmtRmb\":null,\"isdj\":\"N\",\"ts\":null,\"dr\":0,\"def3\":null,\"def4\":null,\"def5\":null,\"def6\":null,\"def7\":null,\"def8\":null,\"def9\":null,\"def10\":null,\"def13\":null,\"def14\":null,\"def15\":null,\"def16\":null,\"def17\":null,\"def18\":null,\"def19\":null,\"def20\":null,\"def21\":null,\"def22\":null,\"def23\":null,\"def24\":null,\"def25\":null,\"def26\":null,\"def27\":null,\"def28\":null,\"def29\":null,\"def30\":null,\"def31\":null,\"def32\":null,\"def33\":null,\"def34\":null,\"def35\":null,\"def36\":null,\"def37\":null,\"def38\":null,\"def39\":null,\"def40\":null,\"def41\":null,\"def42\":null,\"def43\":null,\"def44\":null,\"def45\":null,\"def46\":null,\"def47\":null,\"def48\":null,\"def49\":null,\"def50\":null,\"bankAccountName\":null,\"bankname\":\"中国农业银行四川省成都市青白江支行\",\"holdunit\":null,\"soursys\":\"NC65\",\"approveDate\":null,\"approveNote\":null,\"approver\":null,\"rlMoney\":1000000.00,\"totalMoney\":0.00,\"pkBill\":\"0958ee4d8faa4a58ba6b0839384633e6\",\"settleTypeCode\":\"50\"}],\"errors\":null,\"status\":\"true\",\"sysDate\":\"2021-10-20 11:04:49\"}";
        QueryCwPayRecordResp resp = new Gson().fromJson(str, QueryCwPayRecordResp.class);
        System.out.println(resp.getCode());
    }
}
