package com.yaojinwei.test.access;

import java.io.FilePermission;
import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;

/**
 * @author jinwei.yjw
 * @date 2018/7/18 16:09
 */
public class Client {
    public   void  doCheck() {
        AccessController.doPrivileged(new  PrivilegedAction()  {
            @Override
            public  Object run()  {
                check();
                return   null ;
            }
        } );
    }

    private   void  check()  {
        Permission perm  =   new FilePermission( "/1.txt" ,  "read" );
        AccessController.checkPermission(perm);
        System.out.println( " TestService has permission " );
    }
}
