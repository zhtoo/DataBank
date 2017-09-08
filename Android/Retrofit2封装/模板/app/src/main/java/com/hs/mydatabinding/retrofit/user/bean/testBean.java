package com.hs.mydatabinding.retrofit.user.bean;

/**
 * 作者：zhanghaitao on 2017/8/29 14:02
 * 邮箱：820159571@qq.com
 * @des bean类示例
 */

public class testBean {


    /**
     * findHomeVisitingCount : 3
     * firstVerifyCount : 3
     * replenishCount : 0
     */

    private int findHomeVisitingCount;
    private int firstVerifyCount;
    private int replenishCount;

    public int getFindHomeVisitingCount() {
        return findHomeVisitingCount;
    }

    public void setFindHomeVisitingCount(int findHomeVisitingCount) {
        this.findHomeVisitingCount = findHomeVisitingCount;
    }

    public int getFirstVerifyCount() {
        return firstVerifyCount;
    }

    public void setFirstVerifyCount(int firstVerifyCount) {
        this.firstVerifyCount = firstVerifyCount;
    }

    public int getReplenishCount() {
        return replenishCount;
    }

    public void setReplenishCount(int replenishCount) {
        this.replenishCount = replenishCount;
    }
}
