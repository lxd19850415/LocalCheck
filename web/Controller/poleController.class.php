<?php
    class poleController{
        function addPole(){

            $path = M('pole');
            $data = $path->addPole();
            
            $result ="OK";
            if($data == 0)
            {
            	$result ="FAIL";
            }
            echo $result;
        }
    }

?>