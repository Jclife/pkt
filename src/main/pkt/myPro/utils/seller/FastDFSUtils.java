package myPro.utils.seller;

/**
 * @author liujun
 * @Title:
 * @Package
 * @Description:
 * @date 2018/2/4  20:19
 */
public class FastDFSUtils {


    /**
     * 上传图片方法
     * @param localImgUrl 本地图片地址
     * @return 服务器图片地址
     * @throws Exception
     */
    public String getPicUrl(String localImgUrl) throws Exception{

        FastDFSClientUtils fastDFSClientUtils = new FastDFSClientUtils("myPro/utils/seller/fdfs_client.conf");
        String str = fastDFSClientUtils.uploadFile(localImgUrl);
        return str;
    }

    public String getPicUrl(byte[] b,String type) throws Exception{
        FastDFSClientUtils fastDFSClientUtils = new FastDFSClientUtils("myPro/utils/seller/fdfs_client.conf");
        String s =fastDFSClientUtils.uploadFile(b,type);
        return s;
    }
}
