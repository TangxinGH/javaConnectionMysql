<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>开始使用layui</title>
  <link rel="stylesheet" href="./static/layui/css/layui.css" charset="UTF-8">
</head>
<body>
<%--webinf是放类文件的库文件的，不是放--%>
<%--*注意：WEB-INF目录是受保护的，外界不能直接访问--%>
<% System.out.println("ddfaffasf"); %>
<%--如果访问WEB-INF目录下的html文件，是访问不到的--%>

<%--（虽然连接上了服务器，但资源有误）--%>
<!-- 你的HTML代码 -->

<script src="./static/layui/layui.js" charset="UTF-8"></script>
<script>
    //一般直接写在一个js文件中
    layui.use(['layer', 'form'], function(){
        var layer = layui.layer
            ,form = layui.form;

        layer.msg('Hello World');
    });
</script>
</body>
</html>