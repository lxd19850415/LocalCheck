<?php
require_once("config.php");
class poleModel{
  
        public $_table='pole';

      function addPole(){
        $postData= file_get_contents("php://input");
        if (is_null($postData) || strlen($postData) < 1) {
            return 0;
        }
        $createtime=date("Y-m-d H:i:s");

        $arr = explode('@',$postData);

        $arr1 = explode(',',$arr[2]);


        $arr2 = explode('#',$arr1[1]);

        $lat = $arr1[0];
        $lon = $arr2[0];
        $name = $arr[1];

        $data = array(
            'create_user_id'=>1,
            'lat'=>$lat,
            'lon'=>$lon,
            'name'=>$name,
            'create_time'=>$createtime,
        );
        return  DB::insert($this->_table,$data);
    }



    function getPoleInfo(){
            $sql='select * from '.$this->_table.' order by id desc LIMIT 1';
            $data = DB::findAll($sql);
            return $data;
        }

}

?>