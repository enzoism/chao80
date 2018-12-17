package cn.smartcandy.application.a.wxmsg.util;


import java.util.concurrent.LinkedBlockingQueue;

import cn.smartcandy.application.a.wxmsg.bean.WxTemplate;


/**
 * 项目名称：zmjema
 * 类名称：TemplateQueue
 * 类描述：微信模板消息阻塞队列
 * 创建人：zhoushuyi
 * 创建时间：2017-6-13 上午12:26
 * 修改人：
 * 修改时间：
 * 修改备注：
 * Company:zmjema & HLS (C) 2016
 * @version 1.0
 */
public class TemplateQueue {

    //单例阻塞队列
    private static volatile LinkedBlockingQueue<WxTemplate> lbQueue = null;


    private TemplateQueue() {
    }

    /**
     * 方法描述：获取队列实例
     * @return
     */
    public static LinkedBlockingQueue<WxTemplate> getInstance() {
        if (null == lbQueue) {
            synchronized (TemplateQueue.class) {
                if (null == lbQueue) {
                    lbQueue = new LinkedBlockingQueue<WxTemplate>();
                }
            }
        }
        return lbQueue;
    }

}
