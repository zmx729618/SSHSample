<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>bootstrapIndex01.html</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<link rel="stylesheet" href="../js/bootstrap-3.3.5/css/bootstrap.min.css">
	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="../js/bootstrap-3.3.5/js/bootstrap.min.js"></script>
  </head>
  
  <body>
    
     <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container">

      <div class="starter-template">
        <h1>Bootstrap starter template</h1>
        <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
      </div>
      <!-- 图标使用 -->
      <span class="glyphicon glyphicon-asterisk"></span>  <br>
      <!-- button -->
      <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-asterisk"></span>按钮</button>
      <a class="btn btn-default" href="#" role="button">连接</a>
      
      <!--下拉菜单 -->
      
      <div class="dropdown">
		  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
		    Dropdown
		    <span class="caret"></span>
		  </button>
		  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
		    <li><a href="#">Action</a></li>
		    <li><a href="#">Another action</a></li>
		    <li><a href="#">Something else here</a></li>
		    <li><a href="#">Separated link</a></li>
		  </ul>
	  </div>
	  
	  
	  <!-- Split button -->
		<div class="btn-group">
		  <button type="button" class="btn btn-danger btn-lg">Action</button>
		  <button type="button" class="btn btn-danger dropdown-toggle btn-lg" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    <span class="caret"></span>
		    <span class="sr-only">Toggle Dropdown</span>
		  </button>
		  <ul class="dropdown-menu">
		    <li><a href="#">Action</a></li>
		    <li><a href="#">Another action</a></li>
		    <li><a href="#">Something else here</a></li>
		    <li role="separator" class="divider"></li>
		    <li><a href="#">Separated link</a></li>
		  </ul>
		</div>
		
		<div class="input-group">
		  <span class="input-group-addon" id="basic-addon1">@</span>
		  <input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1">
		</div>
		
		
		<form class="form-inline">
		  <div class="form-group">
		    <label for="exampleInputName2">Name</label>
		    <input type="text" class="form-control" id="exampleInputName2" placeholder="Jane Doe">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputEmail2">Email</label>
		    <input type="email" class="form-control" id="exampleInputEmail2" placeholder="jane.doe@example.com">
		  </div>
		  <button type="submit" class="btn btn-default">提交</button>
		</form>
		
		
		<form class="form-horizontal">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
		    <div class="col-sm-10">
		      <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <div class="checkbox">
		        <label>
		          <input type="checkbox"> Remember me
		        </label>
		      </div>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">Sign in</button>
		    </div>
		  </div>
		</form>
		
		<div class="form-group has-success has-feedback">
		  <label class="control-label sr-only" for="inputSuccess5">Hidden label</label>
		  <input type="text" class="form-control" id="inputSuccess5" aria-describedby="inputSuccess5Status">
		  <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
		  <span id="inputSuccess5Status" class="sr-only">(success)</span>
		</div>
		
        <div class="bg-success visible-xs-block">这是要显示的内容</div>
        <div class="bg-success hidden-xs">这是要隐藏的内容</div>
        
        <nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#">
		        <img alt="Brand" src="...">
		      </a>
		      <a class="navbar-brand" href="#">
		        主页
		      </a>
		      <form class="navbar-form navbar-left" role="search">
				  <div class="form-group">
				    <input type="text" class="form-control" placeholder="Search">
				  </div>
				  <button type="submit" class="btn btn-default">Submit</button>
				</form>
		    </div>
		  </div>
		</nav>
        
        <nav>
		  <ul class="pager">
		    <li class="previous"><a href="#">Previous</a></li>
		    <li class="next"><a href="#">Next</a></li>
		  </ul>
		</nav>
        
        
        <h3>Example heading <span class="label label-default">New</span></h3>
        
        
        <a href="#">Inbox <span class="badge">42</span></a>

		<button class="btn btn-primary" type="button">
		  Messages <span class="badge">4</span>
		</button>
        <div class="jumbotron">
		  <h1>Hello, world!</h1>
		  <p>...</p>
		  <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>
		</div>
        
        <div class="alert alert-danger" style="width: 500px; " role="alert">你输入的用户名有误，请重新输入</div>
        
        <div class="row">
		  <div class="col-xs-2 col-md-3">
		    <a href="#" class="thumbnail">
		      <img src="https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1755238379,1203850710&fm=58" alt="...">
		    </a>
		  </div>
		  
		  		  <div class="col-xs-2 col-md-3">
		    <a href="#" class="thumbnail">
		      <img src="https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1755238379,1203850710&fm=58" alt="...">
		    </a>
		  </div>
		  
		  		  <div class="col-xs-2 col-md-3">
		    <a href="#" class="thumbnail">
		      <img src="https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1755238379,1203850710&fm=58" alt="...">
		    </a>
		  </div>
		</div>
        
        <div class="progress">
		  <div class="progress-bar" role="progressbar"  aria-valuemin="0" aria-valuemax="100" aria-valuenow="60" style="width: 60%;">
		    <span class="sr-only">60% Complete</span>
		  </div>
		</div>
		
		
		<div class="progress">
		  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
		    60%
		  </div>
		</div>
		
		
		<div class="progress">
		  <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%">
		    <span class="sr-only">45% Complete</span>
		  </div>
		</div>
        
        <div class="well"></div>
        
    </div>
  </body>
</html>
