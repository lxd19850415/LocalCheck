<?php
    class indexController{


        function index(){

            $poleObj = M('pole');
            $data = $poleObj->getPoleInfo();

            VIEW::assign(array('data'=>$data[0]));
            VIEW::display('index/index.html');
            
        }


        function viewPole(){

            $poleObj = M('pole');
            $data = $poleObj->getPoleInfo();

            VIEW::assign(array('data'=>$data[0]));
            VIEW::display('index/pole.html');
            
        }


        function viewPath(){

            $pathObj = M('path');
            $data = $pathObj->getPathInfo();
           	//var_dump($data);
            //VIEW::assign(array('data'=>$data[0]));
            //VIEW::display('index/path.html');
        }

        function viewPatrol(){


            $poleObj = M('pole');
            $data = $poleObj->getPoleInfo();


            VIEW::assign(array('data'=>$data[0]));


            VIEW::display('index/path.html');
            
        }

    }

?>