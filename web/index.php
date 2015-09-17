<?php
    //url形式 index.php?controller=控制器名&method=方法名

    /*
     * 实现的功能：1 http://localhost/travel/index.php?controller=map&method=show&l=10&r=10&w=40&h=60
     * 2 http://localhost/travel/index.php?controller=arounduser&method=show&district_id=1
    */
    header("Content-type:text/html;charset=utf-8");
    session_start();
    require('config.php');
    require('kako/pc.php');
    PC::run($config);

?>  


