<?php
class aroundUserView{
    function display($data){

        echo urldecode (stripslashes(json_encode($data)));
    }
}

?>