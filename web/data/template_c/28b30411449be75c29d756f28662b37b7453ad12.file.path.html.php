<?php /* Smarty version Smarty-3.1.21-dev, created on 2015-09-04 18:43:59
         compiled from "tpl\index\path.html" */ ?>
<?php /*%%SmartyHeaderCode:2218055e975ef71b897-47105745%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_valid = $_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '28b30411449be75c29d756f28662b37b7453ad12' => 
    array (
      0 => 'tpl\\index\\path.html',
      1 => 1441360369,
      2 => 'file',
    ),
  ),
  'nocache_hash' => '2218055e975ef71b897-47105745',
  'function' => 
  array (
  ),
  'variables' => 
  array (
    'data' => 0,
  ),
  'has_nocache_code' => false,
  'version' => 'Smarty-3.1.21-dev',
  'unifunc' => 'content_55e975ef85bde9_29119264',
),false); /*/%%SmartyHeaderCode%%*/?>
<?php if ($_valid && !is_callable('content_55e975ef85bde9_29119264')) {function content_55e975ef85bde9_29119264($_smarty_tpl) {?><!doctype html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
  <title>路径管理</title>
  <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
  <?php echo '<script'; ?>
 src="http://cache.amap.com/lbs/static/es5.min.js"><?php echo '</script'; ?>
>
  <?php echo '<script'; ?>
 src="http://webapi.amap.com/maps?v=1.3&key=b45ac2e2ac21dfacb3bc8aa814671edc"><?php echo '</script'; ?>
>
</head>

<body>
  <div id="mapContainer"></div>
  <div id="tip" class="button-group">
    <input type="button" class="button" value="添加线覆盖物" data-method="add" />
    <input type="button" class="button" value="线覆盖物描边" data-method="addBorder" />
    <input type="button" class="button" value="隐藏线覆盖物" data-method="hide" />
    <input type="button" class="button" value="显示线覆盖物" data-method="show" />
    <input type="button" class="button" value="更新线覆盖物" data-method="update" />
    <input type="button" class="button" value="删除线覆盖物" data-method="clear" />
  </div>
  <?php echo '<script'; ?>
>
    var map = new AMap.Map("mapContainer", {
      resizeEnable: true,
      center: [116.397428, 39.90923],
      zoom: 13
    });

  <?php  $_smarty_tpl->tpl_vars['pathPos'] = new Smarty_Variable; $_smarty_tpl->tpl_vars['pathPos']->_loop = false;
 $_from = $_smarty_tpl->tpl_vars['data']->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
foreach ($_from as $_smarty_tpl->tpl_vars['pathPos']->key => $_smarty_tpl->tpl_vars['pathPos']->value) {
$_smarty_tpl->tpl_vars['pathPos']->_loop = true;
?>

  <?php } ?>
    
    var polyline;
    var actions = {
      add: addPolyline,
      addBorder: addBorder,
      hide: function() { return polyline && polyline.hide(); },
      show: function() { return polyline && polyline.show(); },
      clear: function() { return polyline && polyline.setMap(null); },
      update: updatePolyline
    };

    var buttons = document.querySelectorAll('[data-method]');
    for (var i = 0, button; i < buttons.length; i++) {
        button = buttons[i];
        AMap.event.addDomListener(button, 'click', clickListener);
    }
    function clickListener() {
        var action = this.getAttribute('data-method'), method;
        if (method = actions[action]) {
            method();
        }
    }

    function addPolyline() {
      var lineArr = [
        [116.368904,39.913423],
        [116.382122,39.901176],
        [116.387271,39.912501],
        [116.398258,39.904600]
      ];
      polyline = new AMap.Polyline({
        path: lineArr,          //设置线覆盖物路径
        strokeColor: "#3366FF", //线颜色
        strokeOpacity: 1,       //线透明度
        strokeWeight: 5,        //线宽
        strokeStyle: "solid",   //线样式
        strokeDasharray: [10, 5] //补充线样式
      });
      polyline.setMap(map);
    }

    // 更新线覆盖物，除此以外还可通过插件AMap.PolyEditor进行线覆盖物的更新，
    // 详情参看AMap.PolyEditor相关示例介绍
    function updatePolyline() {
        if(!polyline){
            return;
        }
      var lineArrNew = [
        [116.368904,39.923423],
        [116.382122,39.921176],
        [116.387271,39.922501],
        [116.398258,39.914600]
      ];

      // 新线覆盖物属性
      var polylineoptions = {
        visible:  polyline.get('visible'),
        zIndex: 10,
        strokeStyle: "solid",
        strokeColor: "#FF3300",
        strokeOpacity: 0.8,
        strokeWeight: 5,
        isOutline: false,
        path: lineArrNew
      };
      polyline && polyline.setOptions(polylineoptions)
    }

    function addBorder() {
      polyline && polyline.setOptions({
        isOutline: true,
        outlineColor: "#000066"
      });
    }
  <?php echo '</script'; ?>
>
</body>

</html><?php }} ?>
