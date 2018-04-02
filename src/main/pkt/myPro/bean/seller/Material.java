package myPro.bean.seller;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description: 原材料表
 * @date 2018/4/2  15:33
 */
public class Material {

    int material_id;
    int goods_id;
    String goods_name;
    String material_json;

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getMaterial_json() {
        return material_json;
    }

    public void setMaterial_json(String material_json) {
        this.material_json = material_json;
    }
}
