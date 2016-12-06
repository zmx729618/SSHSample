<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>indexl.html</title>
    <meta charset="utf-8">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <script type="text/javascript" language="javascript" src="${ctx}js/jquery-2.1.4/jquery-2.1.4.js"></script>
    <style type="text/css">
    #nav{
        list-style-type: none; 
        margin: 10px auto 0px;
        padding-top:10px;
        padding-right:10px;
        width: 80%;
        height: 30px;
        color: #333;
        border: #000 solid 1px;
        font-size: 14px;
        
        
    }
    
    #nav li{
       width: 110px;
       float: left;
       text-align: center;
       border-top: #C946C2 solid 1px;
       border-left: #C946C2 solid 1px;
       border-bottom: #C946C2 solid 1px;
       cursor: pointer;
    }
    
    #nav li:last-child{
    
       border-right: #C946C2 solid 1px;
    }
    
    #nav .liclick{
        border-top: #A7B8A2 solid 1px;
        border-bottom: none;
    }
    
    </style>
    
    <script type="text/javascript">
         $(document).ready(function(){
              $("input[type='range']").change(function(){
                   var red = $("#red").val();
                   var green = $("#green").val();
                   var blue = $("#blue").val();
                   
                   var value = $(this).val();   
                   $(this).next().html(value);   
                   
                   $("body").css("background-color","rgb("+red+","+green+","+blue+")");
              });
              
              
              $("#nav li").click(function(){
                  $(".liclick").removeClass("liclick")
                  $(this).addClass("liclick");
              });
              
            
         });
    
    </script>
  </head>
  
  <body>
  

    <form action="">
    
                 红色<input type="range"  id="red" min="0" max="255" value="255" /><span>255</span> <br >
                 绿色<input type="range"  id="green" min="0" max="255" value="255" /><span>255</span><br >
                  蓝色<input type="range"  id="blue" min="0" max="255" value="255" /><span>255</span><br >
       
	       邮件<input type="email"  name="email" autofocus="autofocus" required="required"  placeholder="请输入邮件地址"/><br />
	       日期<input type="date"  name="date" /><br />
	       数字<input type="number"  name="number" />   <br />    
	       颜色<input type="color"  name="color" /><br />
	       <input type="submit"  value="提交">
    </form>
    <audio src="" autoplay="autoplay" controls="controls">播放音频</audio>  
    
    <audio autoplay="autoplay" controls="controls">
        <source src=""></source>
        <source src=""></source>
    </audio>
    
    
    
    <video src="" autoplay="autoplay" controls="controls">播放视频</video>  
    
    
    
    <ul id="nav">
      <li class="liclick">1-do</li>
      <li>2-ri</li>
      <li>3-mi</li>
      <li>4-fa</li>
      <li>5-so</li>
      <li>6-la</li>
      <li>7-xi</li>
    </ul>
    
    
    
  </body>
  
  
  
</html>

