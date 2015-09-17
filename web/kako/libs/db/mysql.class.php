<?php
class mysql{
    /**
     * 报错函数
     * @param string $error
     */
    function err($error){
        die("对不起，您的操作有误，错误原因：".$error);//die有两种作用：输入和终止，相当于echo和exit的组合
    }

    /**
     * 连接数据库
     * @param string $config 配置数组 array($dbhost,$dbuser,$dbpsw,$dbname,$dbcharset)
     */
    function connect($config){
        extract($config);
        if(!($con = mysql_connect($dbhost,$dbuser,$dbpsw))){
            $this->err(mysql_error());
        }
        if(!mysql_select_db($dbname,$con)){
            $this->err(mysql_error());
        }
        mysql_query("set names ".$dbcharset);

    }


    /**
     * 执行sql语句
     * @param string $sql
     * @return bool 返回执行成功，资源或执行失败
     */
    function query($sql){

        if(!($query = mysql_query($sql))){
            $this->err($sql."<br/>".mysql_error());
        }else{
            return $query;
        }
    }

    /**
     * 列表
     * @param source $query sql语句通过mysql_query执行出来的资源
     * @return array 返回列表数组
     */
    function findAll($query){

        while($rs = mysql_fetch_array($query,MYSQL_ASSOC)){
            $list[]=$rs;
        }
        return isset($list)?$list:"";
    }

    /**
     * 单条
     * @param source $query sql语句通过mysql_query执行出来的资源
     * @return array 返回单条信息数组
     */
    function findOne($query){
        $rs = mysql_fetch_array($query,MYSQL_ASSOC);
        return $rs;
    }


    /**
     * 单条
     * @param source $query sql语句通过mysql_query执行出来的资源
     * @return array 返回指定行的指定字段的值
     */
    function findResult($query,$row=0,$field=0){
        $rs = mysql_result($query,$row,$field);
        return $rs;
    }


    /**
     * 单条
     * @param source $table 表明
     * @return array 返回指定行的指定字段的值
     */
    function insert($table,$arr){
        //$sql="insert into 表明(多个字段) values(多个值)";
        foreach($arr as $key=>$value){
            $value=mysql_real_escape_string($value);
//            $keyArr[]="'".$key."'";
            $keyArr[]=$key;
            $valueArr[]="'".$value."'";
        }
        $keys=implode(",",$keyArr);
        $values=implode(",",$valueArr);
        $sql="insert into ".$table."(".$keys.") values(".$values.")";
        $this->query($sql);

        return mysql_insert_id();
    }


    /**
     * 修改函数
     * @param source $table 表明
     * @return array 返回指定行的指定字段的值
     */
    function update($table,$arr,$where){
        //$sql="update 表明 set 字段=字段值 where ...";

        foreach($arr as $key=>$value){
            $value=mysql_real_escape_string($value);
            $keyAndValueArr[]=$key."='".$value."'";

        }
        $keyAndValues=implode(",",$keyAndValueArr);

        $sql="update ".$table." set ".$keyAndValues." where ".$where;
        $this->query($sql);
    }


    /**
     * 删除函数
     * @param source $table 表明 $where 条件
     * @return array 返回指定行的指定字段的值
     */
    function del($table,$where){
        //$sql="update 表明 set 字段=字段值 where ...";

        $sql="delete from ".$table." where ".$where;
        $this->query($sql);
    }

}


?>