<?php /* Smarty version Smarty-3.1.21-dev, created on 2015-09-04 18:41:40
         compiled from "tpl\index\pole.html" */ ?>
<?php /*%%SmartyHeaderCode:3231055e97564887496-92825806%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_valid = $_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'f3fd157dce85b13b8c8b4dcd6dbbf1ec5c794e1a' => 
    array (
      0 => 'tpl\\index\\pole.html',
      1 => 1441360066,
      2 => 'file',
    ),
  ),
  'nocache_hash' => '3231055e97564887496-92825806',
  'function' => 
  array (
  ),
  'variables' => 
  array (
    'data' => 0,
  ),
  'has_nocache_code' => false,
  'version' => 'Smarty-3.1.21-dev',
  'unifunc' => 'content_55e97564990ed7_19539611',
),false); /*/%%SmartyHeaderCode%%*/?>
<?php if ($_valid && !is_callable('content_55e97564990ed7_19539611')) {function content_55e97564990ed7_19539611($_smarty_tpl) {?><!doctype html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
  <title>塔杆系统</title>
  <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
  <?php echo '<script'; ?>
 src="http://webapi.amap.com/maps?v=1.3&key=b45ac2e2ac21dfacb3bc8aa814671edc"><?php echo '</script'; ?>
>
</head>

<body>
  <div id="mapContainer"></div>
  <!--<div id="tip" class="tip">鼠标移入点标记试试</div>-->
  <?php echo '<script'; ?>
>

  var lat = <?php echo $_smarty_tpl->tpl_vars['data']->value['lat'];?>
;
  var lon = <?php echo $_smarty_tpl->tpl_vars['data']->value['lon'];?>
;
  var poleName = "<?php echo $_smarty_tpl->tpl_vars['data']->value['name'];?>
";


  //alert("NAME :" +poleName);

    map = new AMap.Map('mapContainer', {
      resizeEnable: true,
      center: [lon, lat],
      zoom: 13
    });

    var marker = new AMap.Marker({
      position: AMap.LngLat(lon,lat)
    });
    marker.setMap(map);
 
    // 设置鼠标划过点标记显示的文字提示
    marker.setTitle('我是地图中心点哦~');

    // 设置label标签
    marker.setLabel({
    //label的父div默认蓝框白底右下角显示，样式className为：amap-marker-label
        //offset:new AMap.Pixel(50,50),//修改父div相对于maker的位置
        content:poleName
    });
  <?php echo '</script'; ?>
>
</body>

</html><?php }} ?>
