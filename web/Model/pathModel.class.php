<?php
require_once("config.php");

class pathModel{
        public $_table='path';

    function addPath(){
        $postData= file_get_contents("php://input");
        $createtime=date("Y-m-d H:i:s");

        $arr = explode('@',$postData);

        $data = array(
            'path_position'=>$arr[1],
            'user_id'=>1,
            'create_time'=>$createtime,
        );
        return  DB::insert($this->_table,$data);
    }


    function getPathInfo(){
            $sql='select * from '.$this->_table.' order by id desc LIMIT 1';
            $data = DB::findAll($sql);


            $result = array();

            $pathPos = split(';',$data[0]['path_position']);

            //var_dump($pathPos);


            for ($i=0; $i < $pathPos.size; $i++) { 

                if (is_null($pathPos[i]) || strlen($pathPos[i]) < 1) {
                    continue;
                }
                $latLon =  split(';',$pathPos[i]);

                if (is_null($pathPos[i]) || strlen($pathPos[i]) < 1) {
                    continue;
                }

                $lat = $latLon[0];
                $lon = $latLon[1];
                
                echo "经度："
                echo $lat;
                echo "，纬度："
                echo $lon;
                echo "<br>";
                //$oneArray = array("lat"=>$lat,"lon"=>$lon);

                //$result = array_merge($result,$oneArray);

            }


            //return $data;
            
        }



}

?>