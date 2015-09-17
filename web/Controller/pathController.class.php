<?php
    class pathController{
        function addPath(){

            $path = M('path');
            $data = $path->addPath();

            $result ="OK";
            if($data == 0)
            {
            	$result ="FAIL";
            }
            echo $result;
        }
    }

?>
