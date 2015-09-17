<?php
class DB{
    public static $db;

    public static function init($dbtype,$config){
        self::$db=new $dbtype;
        self::$db->connect($config);
    }

    public static function  query($sql){
        return self::$db->query($sql);
    }


    public static function  findAll($sql){
        $query=self::$db->query($sql);
        return self::$db->findAll($query);
    }

    public static function  findOne($sql){
        $query=self::$db->query($sql);
        return self::$db->findOne($query);
    }


    public static function  findResult($sql,$row,$filed){
        $query=self::$db->query($sql);
        return self::$db->findResult($query,$row,$filed);
    }


    public static function  insert($sql,$arr){
        return self::$db->insert($sql,$arr);
    }


    public static function  update($sql,$arr,$where){
        return self::$db->update($sql,$arr,$where);
    }


    public static function  del($sql,$where){
        return self::$db->del($sql,$where);
    }



}


?>