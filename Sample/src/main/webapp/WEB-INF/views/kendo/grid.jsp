<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>Basic usage</title>
    <meta charset="utf-8">
    <link href="../../content/shared/styles/examples-offline.css" rel="stylesheet">
    <link href="${ctx}/js/kendoui/styles/web/kendo.common.css" rel="stylesheet">
    <link href="${ctx}/js/kendoui/styles/web/kendo.rtl.css" rel="stylesheet">
    <link href="${ctx}/js/kendoui/styles/web/kendo.default.css" rel="stylesheet">
 
    <script type="text/javascript" src="${ctx}/js/jquery-2.1.4/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="${ctx}/js/kendoui/js/kendo.web.js"></script>
    <script src="../../content/shared/js/console.js"></script>
    <script>
        
    </script>
    
    
</head>
<body>
    
        <a class="offline-button" href="../index.html">Back</a>
    
    
        <div id="example" class="k-content">
            <div id="clientsDb">

                <div id="grid" style="height: 365px"></div>

            </div>

            <style scoped>
                #clientsDb {
                    width: 952px;
                    height: 396px;
                    margin: 20px auto 0;
                    padding: 51px 4px 0 4px;
                    background: url('../../content/web/grid/clientsDb.png') no-repeat 0 0;
                }
            </style>
            <script>
                $(document).ready(function () {
                    $("#grid").kendoGrid({
                        dataSource: {
                            type: "odata",
                            transport: {
                                read: "http://demos.telerik.com/kendo-ui/service/Northwind.svc/Customers"
                            },
                            pageSize: 10
                        },
                        groupable: true,
                        sortable: true,
                        pageable: {
                            refresh: true,
                            pageSizes: true,
                            buttonCount: 5
                        },
                        columns: [{
                            field: "ContactName",
                            title: "Contact Name",
                            width: 200
                        }, {
                            field: "ContactTitle",
                            title: "Contact Title",
                            width: 250
                        }, {
                            field: "CompanyName",
                            title: "Company Name"
                        }, {
                            field: "Country",
                            width: 150
                        }]
                    });
                });
            </script>
        </div>

	
			
</body>
</html>
