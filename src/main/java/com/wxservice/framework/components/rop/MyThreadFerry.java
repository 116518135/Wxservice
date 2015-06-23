package com.wxservice.framework.components.rop;



import com.rop.ThreadFerry;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
public class MyThreadFerry implements ThreadFerry{

    @Override
    public void doInSrcThread() {
       // System.out.println("doInSrcThread:"+Thread.currentThread().getId());
    }

    @Override
    public void doInDestThread() {
      //  System.out.println("doInSrcThread:"+Thread.currentThread().getId());
    }
}

