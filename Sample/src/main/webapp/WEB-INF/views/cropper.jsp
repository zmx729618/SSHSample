<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>   
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="${ctx}/js/cropper/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/js/cropper/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/js/cropper/css/tooltip.min.css">
    <link rel="stylesheet" href="${ctx}/js/cropper/css/cropper.css">
    <link rel="stylesheet" href="${ctx}/js/cropper/css/main.css">	
    <script type="text/javascript" src="${ctx}/js/jquery-2.1.4/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="${ctx}/js/cropper/js/tooltip.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/bootstrap-3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/cropper/js/cropper.js"></script>
    <script type="text/javascript" src="${ctx}/js/cropper/js/main.js"></script>
	<title>Cropper-测试</title>              
</head>
<body>          
    <div>                 
      <!-- Content -->
	  <div class="container">
	    <div class="row">
	      <div class="col-md-9">
	        <!-- <h3 class="page-header">Demo:</h3> -->
	        <div class="img-container">
	          <img src="${ctx}/images/picture.jpg" alt="Picture">
	        </div>
	      </div>
	      <div class="col-md-3">
	        <!-- <h3 class="page-header">Preview:</h3> -->
	        <div class="docs-preview clearfix">
	          <div class="img-preview preview-lg"></div>
	          <div class="img-preview preview-md"></div>
	          <div class="img-preview preview-sm"></div>
	          <div class="img-preview preview-xs"></div>
	        </div>   
	  
	        <!-- <h3 class="page-header">Data:</h3> -->
	        <div class="docs-data">
	          <div class="input-group">
	            <label class="input-group-addon" for="dataX">X</label>
	            <input type="text" class="form-control" id="dataX" placeholder="x">
	            <span class="input-group-addon">像素</span>
	          </div>
	          <div class="input-group">
	            <label class="input-group-addon" for="dataY">Y</label>
	            <input type="text" class="form-control" id="dataY" placeholder="y">
	            <span class="input-group-addon">像素</span>
	          </div>
	          <div class="input-group">
	            <label class="input-group-addon" for="dataWidth">宽度</label>
	            <input type="text" class="form-control" id="dataWidth" placeholder="width">
	            <span class="input-group-addon">像素</span>
	          </div>
	          <div class="input-group">
	            <label class="input-group-addon" for="dataHeight">高度</label>
	            <input type="text" class="form-control" id="dataHeight" placeholder="height">
	            <span class="input-group-addon">像素</span>
	          </div>    
	          <div class="input-group">
	            <label class="input-group-addon" for="dataRotate">旋转</label>
	            <input type="text" class="form-control" id="dataRotate" placeholder="rotate">
	            <span class="input-group-addon">度</span>
	          </div>
	          <div class="input-group">
	            <label class="input-group-addon" for="dataScaleX">X比例因子</label>
	            <input type="text" class="form-control" id="dataScaleX" placeholder="scaleX">
	          </div>
	          <div class="input-group">
	            <label class="input-group-addon" for="dataScaleY">Y比例因子</label>
	            <input type="text" class="form-control" id="dataScaleY" placeholder="scaleY">
	          </div>
	        </div>
	      </div>
	    </div>
	    <div class="row docs-actions">
	      <div class="col-md-9 docs-buttons">
	        <!-- <h3 class="page-header">Toolbar:</h3> -->
	        <div class="btn-group">
	          <button type="button" class="btn btn-primary" data-method="setDragMode" data-option="move" title="Move">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setDragMode&quot;, &quot;move&quot;)">
	              <span class="fa fa-arrows"></span>
	            </span>
	          </button>
	          <button type="button" class="btn btn-primary" data-method="setDragMode" data-option="crop" title="Crop">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setDragMode&quot;, &quot;crop&quot;)">
	              <span class="fa fa-crop"></span>
	            </span>
	          </button>
	        </div>
	
	        <div class="btn-group">
	          <button type="button" class="btn btn-primary" data-method="zoom" data-option="0.1" title="Zoom In">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;zoom&quot;, 0.1)">
	              <span class="fa fa-search-plus"></span>
	            </span>
	          </button>
	          <button type="button" class="btn btn-primary" data-method="zoom" data-option="-0.1" title="Zoom Out">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;zoom&quot;, -0.1)">
	              <span class="fa fa-search-minus"></span>
	            </span>
	          </button>
	        </div>
	
	        <div class="btn-group">
	          <button type="button" class="btn btn-primary" data-method="move" data-option="-10" data-second-option="0" title="Move Left">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;move&quot;, -10, 0)">
	              <span class="fa fa-arrow-left"></span>
	            </span>
	          </button>
	          <button type="button" class="btn btn-primary" data-method="move" data-option="10" data-second-option="0" title="Move Right">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;move&quot;, 10, 0)">
	              <span class="fa fa-arrow-right"></span>
	            </span>
	          </button>
	          <button type="button" class="btn btn-primary" data-method="move" data-option="0" data-second-option="-10" title="Move Up">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;move&quot;, 0, -10)">
	              <span class="fa fa-arrow-up"></span>
	            </span>
	          </button>
	          <button type="button" class="btn btn-primary" data-method="move" data-option="0" data-second-option="10" title="Move Down">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;move&quot;, 0, 10)">
	              <span class="fa fa-arrow-down"></span>
	            </span>
	          </button>
	        </div>
	
	        <div class="btn-group">
	          <button type="button" class="btn btn-primary" data-method="rotate" data-option="-45" title="Rotate Left">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;rotate&quot;, -45)">
	              <span class="fa fa-rotate-left"></span>
	            </span>
	          </button>
	          <button type="button" class="btn btn-primary" data-method="rotate" data-option="45" title="Rotate Right">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;rotate&quot;, 45)">
	              <span class="fa fa-rotate-right"></span>
	            </span>
	          </button>
	        </div>
	
	        <div class="btn-group">
	          <button type="button" class="btn btn-primary" data-flip="horizontal" data-method="scale" data-option="-1" data-second-option="1" title="Flip Horizontal">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;scale&quot;, -1, 1)">
	              <span class="fa fa-arrows-h"></span>
	            </span>
	          </button>
	          <button type="button" class="btn btn-primary" data-flip="vertical" data-method="scale" data-option="1" data-second-option="-1" title="Flip Vertical">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;scale&quot;, 1, -1)">
	              <span class="fa fa-arrows-v"></span>
	            </span>
	          </button>
	        </div>
	
	        <div class="btn-group">
	          <button type="button" class="btn btn-primary" data-method="crop" title="Crop">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;crop&quot;)">
	              <span class="fa fa-check"></span>
	            </span>
	          </button>
	          <button type="button" class="btn btn-primary" data-method="clear" title="Clear">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;clear&quot;)">
	              <span class="fa fa-remove"></span>
	            </span>
	          </button>
	        </div>
	
	        <div class="btn-group">
	          <button type="button" class="btn btn-primary" data-method="disable" title="Disable">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;disable&quot;)">
	              <span class="fa fa-lock"></span>
	            </span>
	          </button>
	          <button type="button" class="btn btn-primary" data-method="enable" title="Enable">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;enable&quot;)">
	              <span class="fa fa-unlock"></span>
	            </span>
	          </button>
	        </div>
	
	        <div class="btn-group">
	          <button type="button" class="btn btn-primary" data-method="reset" title="Reset">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;reset&quot;)">
	              <span class="fa fa-refresh"></span>
	            </span>
	          </button>
	          <label class="btn btn-primary btn-upload" for="inputImage" title="Upload image file">
	            <input type="file" class="sr-only" id="inputImage" name="file" accept="image/*">
	            <span class="docs-tooltip" data-toggle="tooltip" title="Import image with Blob URLs">
	              <span class="fa fa-upload"></span>
	            </span>
	          </label>
	          <button type="button" class="btn btn-primary" data-method="destroy" title="Destroy">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;destroy&quot;)">
	              <span class="fa fa-power-off"></span>
	            </span>
	          </button>
	        </div>
	
	        <div class="btn-group btn-group-crop">
	          <button type="button" class="btn btn-primary" data-method="getCroppedCanvas">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getCroppedCanvas&quot;)">
	              裁剪保存
	            </span>
	          </button>
	          <button type="button" class="btn btn-primary" data-method="getCroppedCanvas" data-option="{ &quot;width&quot;: 160, &quot;height&quot;: 90 }">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getCroppedCanvas&quot;, { width: 160, height: 90 })">
	              160&times;90
	            </span>
	          </button>
	          <button type="button" class="btn btn-primary" data-method="getCroppedCanvas" data-option="{ &quot;width&quot;: 320, &quot;height&quot;: 180 }">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getCroppedCanvas&quot;, { width: 320, height: 180 })">
	              320&times;180
	            </span>
	          </button>
	        </div>
	
	        <!-- Show the cropped image in modal -->
	        <div class="modal fade docs-cropped" id="getCroppedCanvasModal" aria-hidden="true" aria-labelledby="getCroppedCanvasTitle" role="dialog" tabindex="-1">
	          <div class="modal-dialog">
	            <div class="modal-content">
	              <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="getCroppedCanvasTitle">已裁剪图像</h4>
	              </div>
	              <div class="modal-body"></div>
	              <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <a class="btn btn-primary" id="download" download="cropped.png" href="javascript:void(0);">另存</a>
	              </div>
	            </div>
	          </div>
	        </div><!-- /.modal -->
	
	        <button type="button" class="btn btn-primary" data-method="getData" data-option data-target="#putData">
	          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getData&quot;)">
	            获取裁剪区参数
	          </span>
	        </button>
	        <button type="button" class="btn btn-primary" data-method="setData" data-target="#putData">
	          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setData&quot;, data)">
	            设置裁剪区参数
	          </span>
	        </button>
	        <button type="button" class="btn btn-primary" data-method="getContainerData" data-option data-target="#putData">
	          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getContainerData&quot;)">
	            获取容器参数
	          </span>
	        </button>
	        <button type="button" class="btn btn-primary" data-method="getImageData" data-option data-target="#putData">
	          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getImageData&quot;)">
	            获取图像参数
	          </span>
	        </button>
	        <button type="button" class="btn btn-primary" data-method="getCanvasData" data-option data-target="#putData">
	          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getCanvasData&quot;)">
	            获取画布参数
	          </span>
	        </button>
	        <button type="button" class="btn btn-primary" data-method="setCanvasData" data-target="#putData">
	          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setCanvasData&quot;, data)">
	            设置画布参数
	          </span>
	        </button>
	        <button type="button" class="btn btn-primary" data-method="getCropBoxData" data-option data-target="#putData">
	          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getCropBoxData&quot;)">
	            获取裁剪框参数
	          </span>
	        </button>
	        <button type="button" class="btn btn-primary" data-method="setCropBoxData" data-target="#putData">
	          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setCropBoxData&quot;, data)">
	            设置裁剪框参数
	          </span>
	        </button>
	        <input type="text" class="form-control" id="putData" placeholder="在这里设置参数">
	
	      </div><!-- /.docs-buttons -->
	
	      <div class="col-md-3 docs-toggles">
	        <!-- <h3 class="page-header">Toggles:</h3> -->
	        <div class="btn-group btn-group-justified" data-toggle="buttons">
	          <label class="btn btn-primary active" data-method="setAspectRatio" data-option="1.7777777777777777" title="Set Aspect Ratio">
	            <input type="radio" class="sr-only" id="aspestRatio1" name="aspestRatio" value="1.7777777777777777">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setAspectRatio&quot;, 16 / 9)">
	              16:9
	            </span>
	          </label>
	          <label class="btn btn-primary" data-method="setAspectRatio" data-option="1.3333333333333333" title="Set Aspect Ratio">
	            <input type="radio" class="sr-only" id="aspestRatio2" name="aspestRatio" value="1.3333333333333333">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setAspectRatio&quot;, 4 / 3)">
	              4:3
	            </span>
	          </label>
	          <label class="btn btn-primary" data-method="setAspectRatio" data-option="1" title="Set Aspect Ratio">
	            <input type="radio" class="sr-only" id="aspestRatio3" name="aspestRatio" value="1">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setAspectRatio&quot;, 1 / 1)">
	              1:1
	            </span>
	          </label>
	          <label class="btn btn-primary" data-method="setAspectRatio" data-option="0.6666666666666666" title="Set Aspect Ratio">
	            <input type="radio" class="sr-only" id="aspestRatio4" name="aspestRatio" value="0.6666666666666666">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setAspectRatio&quot;, 2 / 3)">
	              2:3
	            </span>
	          </label>
	          <label class="btn btn-primary" data-method="setAspectRatio" data-option="NaN" title="Set Aspect Ratio">
	            <input type="radio" class="sr-only" id="aspestRatio5" name="aspestRatio" value="NaN">
	            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setAspectRatio&quot;, NaN)">
	              自由
	            </span>
	          </label>
	        </div>
	
	        <div class="dropdown dropup docs-options">
	          <button type="button" class="btn btn-primary btn-block dropdown-toggle" id="toggleOptions" data-toggle="dropdown" aria-expanded="true">
	            开关选项
	            <span class="caret"></span>
	          </button>
	          <ul class="dropdown-menu" aria-labelledby="toggleOptions" role="menu">
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="strict" checked>
	                strict
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="responsive" checked>
	                responsive
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="checkImageOrigin" checked>
	                checkImageOrigin
	              </label>
	            </li>
	
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="modal" checked>
	                modal
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="guides" checked>
	                guides
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="center" checked>
	                center
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="highlight" checked>
	                highlight
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="background" checked>
	                background
	              </label>
	            </li>
	
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="autoCrop" checked>
	                autoCrop
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="dragCrop" checked>
	                dragCrop
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="movable" checked>
	                movable
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="rotatable" checked>
	                rotatable
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="scalable" checked>
	                scalable
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="zoomable" checked>
	                zoomable
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="mouseWheelZoom" checked>
	                mouseWheelZoom
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="touchDragZoom" checked>
	                touchDragZoom
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="cropBoxMovable" checked>
	                cropBoxMovable
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="cropBoxResizable" checked>
	                cropBoxResizable
	              </label>
	            </li>
	            <li role="presentation">
	              <label class="checkbox-inline">
	                <input type="checkbox" name="option" value="doubleClickToggle" checked>
	                doubleClickToggle
	              </label>
	            </li>
	          </ul>
	        </div><!-- /.dropdown -->
	      </div><!-- /.docs-toggles -->
	    </div>
	  </div>
       
    </div>
	<div>
	   hello,${data}!
	</div>
</body>
</html>