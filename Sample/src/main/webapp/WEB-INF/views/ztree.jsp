<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>Ztree测试</title>
		<link rel="stylesheet" href="${ctx}/js/ztree/css/zTreeStyle.css" type="text/css" />		
		<script type="text/javascript" src="${ctx}/js/jquery-2.1.4/jquery-2.1.4.js"></script>
	    <script type="text/javascript" src="${ctx}/js/jquery.form-3.46/jquery.form-3.46.js"></script>		
		<script type="text/javascript" src="${ctx}/js/ztree/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="${ctx}/js/ztree/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="${ctx}/js/ztree/jquery.ztree.exedit-3.5.js"></script>
		<script type="text/javascript" charset="utf-8">
			var key, zTree, rMenu;
			$(document).ready(function(){
				alert('${tree}');
				$.fn.zTree.init($("#treeDemo"), setting, eval('${tree}'));
				zTree = $.fn.zTree.getZTreeObj("treeDemo");
				rMenu = $("#rMenu");				
				
				key = $("#key");
				key.bind("propertychange", searchNode).bind("input", searchNode);
			});
			
			var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
				    }
				},
				view: {
					addHoverDom: addHoverDom,
					removeHoverDom: removeHoverDom,
					fontCss: getFontCss
				},
				edit: {
					enable: true,
					renameTitle: "编辑",
					removeTitle: "删除"
				},
				callback: {
					beforeRemove: beforeRemove,
					beforeExpand: beforeExpand,
					onExpand: onExpand,
					onCheck: onCheck,
					onClick: onClick,
					//onDblClick: onDblClick,
					onRightClick: OnRightClick
				}
			};
			
			var curExpandNode = null;
			function beforeExpand(treeId, treeNode) {
				var pNode = curExpandNode ? curExpandNode.getParentNode():null;
				var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
				//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				for(var i = 0, l = !treeNodeP ? 0 : treeNodeP.children.length; i<l; i++ ) {
					if (treeNode !== treeNodeP.children[i]) {
						zTree.expandNode(treeNodeP.children[i], false);
					}
				}
				while (pNode) {
					if (pNode === treeNode) {
						break;
					}
					pNode = pNode.getParentNode();
				}
				if (!pNode) {
					singlePath(treeNode);
				}
			}
			
			function singlePath(newNode) {
				if (newNode === curExpandNode) return;
	
	            //var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	            var rootNodes, tmpRoot, tmpTId, i, j, n;
	            if (!curExpandNode) {
	                tmpRoot = newNode;
	                while (tmpRoot) {
	                    tmpTId = tmpRoot.tId;
	                    tmpRoot = tmpRoot.getParentNode();
	                }
	                rootNodes = zTree.getNodes();
	                for (i=0, j=rootNodes.length; i<j; i++) {
	                    n = rootNodes[i];
	                    if (n.tId != tmpTId) {
	                        zTree.expandNode(n, false);
	                    }
	                }
	            } else if (curExpandNode && curExpandNode.open) {
					if (newNode.parentTId === curExpandNode.parentTId) {
						zTree.expandNode(curExpandNode, false);
					} else {
						var newParents = [];
						while (newNode) {
							newNode = newNode.getParentNode();
							if (newNode === curExpandNode) {
								newParents = null;
								break;
							} else if (newNode) {
								newParents.push(newNode);
							}
						}
						if (newParents!=null) {
							var oldNode = curExpandNode;
							var oldParents = [];
							while (oldNode) {
								oldNode = oldNode.getParentNode();
								if (oldNode) {
									oldParents.push(oldNode);
								}
							}
							if (newParents.length>0) {
								zTree.expandNode(oldParents[Math.abs(oldParents.length-newParents.length)-1], false);
							} else {
								zTree.expandNode(oldParents[oldParents.length-1], false);
							}
						}
					}
				}
				curExpandNode = newNode;
			}
			
			function onExpand(event, treeId, treeNode) {
				curExpandNode = treeNode;
			}
			
			var newCount = 1;
			function addHoverDom(treeId, treeNode) {
				var sObj = $("#" + treeNode.tId + "_span");
				if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length>0) return;
				var addStr = "<span class='icon-add' id='addBtn_" + treeNode.tId
					+ "' title='添加' onfocus='this.blur();'></span>";
				sObj.after(addStr);
				var btn = $("#addBtn_"+treeNode.tId);
				if (btn) btn.bind("click", function() {
					//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"新节点" + (newCount++)});
					return false;
				});
			};
			
			function removeHoverDom(treeId, treeNode) {
				$("#addBtn_"+treeNode.tId).unbind().remove();
			};
			
			var nodeList = [];
			function searchNode(e) {
				//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var value = $.trim(key.get(0).value);
				if (value === "") return;
				updateNodes(false);
				nodeList = zTree.getNodesByParamFuzzy("name", value);
				updateNodes(true);
				showSelectedNode();
			}
			
			function onCheck() {
				showSelectedNode();
			}
			
			function showSelectedNode() {
				nodeList = zTree.getCheckedNodes(true);
				var str = "";
				for ( var i = 0, l = nodeList.length; i < l; i++) {
					str += "," + nodeList[i].name;
				}
				str = str.substr(1);
				$("#selectedNode").val(str);
			}
			
			function updateNodes(highlight) {
				//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				for ( var i = 0, l = nodeList.length; i < l; i++) {
					nodeList[i].highlight = highlight;
					zTree.updateNode(nodeList[i]);
					zTree.checkNode(nodeList[i], true, true);
				}
			}
			
			function getFontCss(treeId, treeNode) {
				return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
			}
			
			function OnRightClick(event, treeId, treeNode) {
				if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
					zTree.cancelSelectedNode();
					showRMenu("root", event.clientX, event.clientY);
				} else if (treeNode && !treeNode.noR) {
					zTree.selectNode(treeNode);
					showRMenu("node", event.clientX, event.clientY);
				}
			}
			
			function showRMenu(type, x, y) {
				$("#rMenu ul").show();
				if (type=="root") {
					$("#m_del").hide();
					$("#m_check").hide();
					$("#m_unCheck").hide();
				} else {
					$("#m_del").show();
					$("#m_check").show();
					$("#m_unCheck").show();
				}
				rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
	
				$("body").bind("mousedown", onBodyMouseDown);
			}
			
			function hideRMenu() {
				if (rMenu) rMenu.css({"visibility": "hidden"});
				$("body").unbind("mousedown", onBodyMouseDown);
			}
			
			function onBodyMouseDown(event){
				if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
					rMenu.css({"visibility" : "hidden"});
				}
			}
			
			var addCount = 1;
			function addTreeNode() {
				hideRMenu();
				var newNode = {name:"新节点" + (addCount++)};
				if (zTree.getSelectedNodes()[0]) {
					newNode.checked = zTree.getSelectedNodes()[0].checked;
					zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
				} else {
					zTree.addNodes(null, newNode);
				}
			}
			
			function removeTreeNode() {
				hideRMenu();
				var nodes = zTree.getSelectedNodes();
				if (nodes && nodes.length>0) {
					if (nodes[0].children && nodes[0].children.length > 0) {
						var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
						if (confirm(msg)==true){
							zTree.removeNode(nodes[0]);
						}
					} else {
						var msg = "确认删除节点吗？";
						if (confirm(msg)==true){
							zTree.removeNode(nodes[0]);
						}
					}
				}
			}
			
			function onClick() {
				var check = zTree.getSelectedNodes()[0].checked;
				if (check) {
					checkTreeNode(false);
				} else {
					checkTreeNode(true);
				}
			}
			
			function checkTreeNode(checked) {
				var nodes = zTree.getSelectedNodes();
				if (nodes && nodes.length>0) {
					zTree.checkNode(nodes[0], checked, true);
				}
				hideRMenu();
			}
			
			function beforeRemove(treeId, treeNode) {
				zTree.selectNode(treeNode);
				return confirm("确认删除节点“" + treeNode.name + "”吗？");
			}
			
			function findFarmByCompany(companyId){	// 选择公司后自动获取其农场列表
				if (null != companyId && "" != companyId) {
					$.ajax({
						type: "POST",
						url: "${ctx}/base/" + companyId + "/farms",
						dataType: "json",
						success: function(json) {
							$("#farm").empty();
							var html = "<option value=''><fmt:message key="msg.choose"></fmt:message></option>";
							$.each(json, function(i, type){
								html += ("<option value='"+ type['id'] +"'>" + type['name'] +"</option>");
							});
							$("#farm").append(html);
						}
					});
				} else {
					$("#farm").empty();
					$("#farm").append("<option value=''><fmt:message key="msg.choose"></fmt:message></option>");
				}
			}
			
			function gotoPage(id){	// 查询
				var companyId = $("#company").val();
				var farmId = $("#farm").val();
				//var technician = $("#technician").val();
				var begin = $("#begin").val();
				var end = $("#end").val();
				var orderId = $("#orderId").val();
				window.location.href = "${ctx}/storage/draw/query?pageNo=" + id + "&companyId=" + companyId + "&farmId=" + farmId
					+ "&begin=" + begin + "&end=" + end + "&orderId=" + orderId;
			}
			
			function clearAll(){	// 清除
				var userType = "${userType}";
				if (5 != userType) {
					$("#farm").val("");
					if (4 != userType) {
						$("#company").val("");
					}
				}
				$("#technician").val("");
				$("#begin").val("${begin}");
				$("#end").val("${end}");
				$("#orderId").val("");
			}
			
			function see(id){	// 查看记录
				window.location.href = "${ctx}/storage/draw/see?id=" + id;
			}
			
			function del(id){	// 删除
				$.dialog({
					icon: 'confirm.gif',
					title: "<fmt:message key='msg.hint'/>",
					okVal: "<fmt:message key='msg.btn.sure'/>",
					cancelVal: "<fmt:message key='msg.btn.cancel'/>",
					width: '12em',
					height: '8em',
					content: "<fmt:message key='msg.tip.sureDel'/>",
					lock: true,
					ok: function(){
						$.ajax({
							type: "POST",
							url: "${ctx}/storage/draw/del",
							data: "id=" + id,
							success: function(data) {
								if("success" == data) {
									$.dialog.tips("<fmt:message key='msg.tip.success'/>", 2, "success.gif", function() {
										location.reload();
									});
								} else {
									$.dialog({
										title: "<fmt:message key='msg.hint'/>",
										icon: 'error.gif',
										content: "<fmt:message key='msg.tip.fail'/> " + data + ".",
										okVal: "<fmt:message key='msg.btn.sure'/>",
										width: "12em",
										height: "8em",
										lock: true,
										ok: true
									});
								}
							}
						});
					},
					cancel: true
				});
			}
		</script>
		<style type="text/css">
			div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
			div#rMenu ul li{
				margin: 1px 0;
				padding: 0 5px;
				cursor: pointer;
				list-style: none outside none;
				background-color: #DFDFDF;
			}
		</style>
	</head>

	<body>
		<div class="rightcon">
			<div class="list-crm">
				<i class="ico-home"></i>
				<a href="${ctx}/index">资源树demo</a>
			</div>
			<div>
				搜索：<input id="key" name="key"/>
				<ul id="treeDemo" class="ztree"></ul>
				<ul>
					<li>
						选中节点:<br/>
						<textarea id="selectedNode" rows="10" cols="100"></textarea>
					</li>
				</ul>
			</div>
			<div id="rMenu">
				<ul>
					<li id="m_add" onclick="addTreeNode();">增加节点</li>
					<li id="m_del" onclick="removeTreeNode();">删除节点</li>
					<li id="m_check" onclick="checkTreeNode(true);">选中节点</li>
					<li id="m_unCheck" onclick="checkTreeNode(false);">取消勾选</li>
				</ul>
			</div>
		</div>
	</body>
</html>