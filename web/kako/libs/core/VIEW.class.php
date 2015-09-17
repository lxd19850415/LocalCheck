<?php
class VIEW{
    public static $view;

    public static function init($viewtype,$config){
        self::$view=new $viewtype;
        /*$smarty=new Smarty();
         * $smarty->left_delimiter=$config["left_delimiter";]
         */

        foreach($config as $key=>$value){
            self::$view->$key=$value;
        }
    }

    public static function  assign($data){
        foreach($data as $key=>$value){
//            echo '$key='.$key;
//            echo '$value='.$value;
            self::$view->assign($key,$value);
        }
    }

    public static function  display($template){
        self::$view->display($template);
    }

}


?>