<?php
class mapView{
    function display($data){

     echo urldecode (stripslashes(json_encode($data)));
    }
}

?>