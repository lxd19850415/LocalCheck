<?php
    function C($name,$method){
        require_once('Controller/'.$name.'Controller.class.php');
        eval('$obj = new '.$name.'Controller();$obj->'.$method.'();');
    }

    function M($name){
        require_once('Model/'.$name.'Model.class.php');
        eval('$obj = new '.$name.'Model();');
        return $obj;
    }

    function V($name){
        require_once('View/'.$name.'View.class.php');
        eval('$obj = new '.$name.'View();');
        return $obj;
    }

    function daddslashes($str){
        return (!get_magic_quotes_gpc())?addslashes($str):$str;
    }


//////////////////////




    function xyInRect($x,$y,$rect_x,$rect_y,$rect_w,$rect_h)
    {
        if(($x >= $rect_x && $x <=$rect_x +$rect_w) && ($y >= $rect_y && $y <=$rect_y +$rect_h) )
        {
            return true;
        }
        return false;
    }
    function rectInRect($rect1_x,$rect1_y,$rect1_w,$rect1_h,$rect2_x,$rect2_y,$rect2_w,$rect2_h)
    {
        if(xyInRect($rect1_x,$rect1_y,$rect2_x,$rect2_y,$rect2_w,$rect2_h)
            ||xyInRect($rect1_x+$rect1_w,$rect1_y,$rect2_x,$rect2_y,$rect2_w,$rect2_h)
            ||xyInRect($rect1_x+$rect1_w,$rect1_y+$rect1_w,$rect2_x,$rect2_y,$rect2_w,$rect2_h)
            ||xyInRect($rect1_x,$rect1_y+$rect1_w,$rect2_x,$rect2_y,$rect2_w,$rect2_h) )
        {
            return true;
        }
        return false;
    }
?>  


