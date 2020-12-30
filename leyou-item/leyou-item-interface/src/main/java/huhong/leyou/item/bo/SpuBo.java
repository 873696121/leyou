package huhong.leyou.item.bo;

import huhong.leyou.item.pojo.Spu;

public class SpuBo extends Spu {
    private String cname;
    private String bname;

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
