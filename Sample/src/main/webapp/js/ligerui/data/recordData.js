var recordData = [ {
	text : '采集任务管理',
	isexpand : true,
	children : [ {
		url : "record/task1",
		text : "采集任务查看"
	} ]
}, {
	text : '性状数据管理',
	isexpand : true,
	children : [ {
		url : "record/record1/input",
		text : "性状数据录入"
	},{
		url : "record/record1",
		text : "性状数据查看"
	},{
		url : "record/record1/listRecord1Sheet",
		text : "性状数据审查"
	}]
},

{
	text : '数据处理',
	isexpand : true,
	children : [ {
		url : "record/singlePlant/singlePlantRecordList",
		text : "单株数据处理"
	},{
		url : "record/dataprocess/plotRecordList",
		text : "小区数据处理"
	} ,
	{
		url : "record/dataprocess/materialRecordList",
		text : "材料数据处理"
	},
	{
		url : "record/template/manage/listTemplate",
		text : "处理模板管理"
	}]

},

{
	text : '数据查看',
	isexpand : true,
	children : [ {
		url : "record/singlePlant/singlePlantTraitRecordList",
		text : "单株综合数据查看"
	},{
		url : "record/dataprocess/plotTraitRecordList",
		text : "小区综合数据查看"
	}, {
		url : "record/dataprocess/materialTraitRecordList",
		text : "材料综合数据查看"
	} ]
},

{
	text : '档案查看',
	isexpand : true,
	children : [ {
		url : "record/profile/profileList",
		text : "试验档案查看"
	}]
},

{
	text : '图像管理',
	isexpand : true,
	children : [ {
		url : "record/image/PDAImagesUpload",
		text : "PDA图像上传"
	},{
		url : "record/image/viewImage",
		text : "图像查看"
	}]
	
	
}];